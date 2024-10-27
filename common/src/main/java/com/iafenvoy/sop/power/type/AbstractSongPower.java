package com.iafenvoy.sop.power.type;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.item.block.AbstractSongCubeBlock;
import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerData;
import com.iafenvoy.sop.power.SongPowerDataHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public sealed abstract class AbstractSongPower<T extends AbstractSongPower<T>> permits DelaySongPower, DummySongPower, InstantSongPower, IntervalSongPower, PersistSongPower {
    public static final List<AbstractSongPower<?>> POWERS = new ArrayList<>();
    public static final Map<String, AbstractSongPower<?>> BY_ID = new HashMap<>();
    private final String id;
    private final PowerCategory category;
    private Consumer<AbstractSongPower<?>> init = self -> {
    };
    private ToIntFunction<SongPowerDataHolder> primaryCooldownSupplier = data -> 0, secondaryCooldownSupplier = data -> 0;
    private ToDoubleFunction<SongPowerDataHolder> exhaustion = data -> 0;
    protected Consumer<SongPowerDataHolder> apply = holder -> {
    };
    @Nullable
    protected Supplier<SoundEvent> applySound;
    private boolean experimental = false;

    public AbstractSongPower(String id, PowerCategory category) {
        this.id = id;
        this.category = category;
        if (category != null) {
            POWERS.add(this);
            BY_ID.put(id, this);
            category.registerPower(this);
        }
    }

    public ItemStack getStack() {
        ItemStack stack = new ItemStack(AbstractSongCubeBlock.SONGS.getOrDefault(this.category, Items.AIR));
        stack.getOrCreateNbt().putString(AbstractSongCubeBlock.POWER_TYPE_KEY, this.id);
        return stack;
    }

    public String getId() {
        return this.id;
    }

    public PowerCategory getCategory() {
        return this.category;
    }

    public Identifier getIconTexture() {
        if (this.isEmpty()) return new Identifier("textures/item/barrier.png");
        return new Identifier(SongsOfPower.MOD_ID, "textures/power/" + this.id + ".png");
    }

    public T onInit(Consumer<AbstractSongPower<?>> init) {
        this.init = init;
        return this.get();
    }

    public void init() {
        this.init.accept(this);
    }

    public T onApply(Consumer<SongPowerDataHolder> apply) {
        this.apply = apply;
        return this.get();
    }

    public T setApplySound(Supplier<SoundEvent> applySound) {
        this.applySound = applySound;
        return this.get();
    }

    public int getPrimaryCooldown(SongPowerData.SinglePowerData data) {
        return this.getPrimaryCooldown(new SongPowerDataHolder(data));
    }

    public int getPrimaryCooldown(SongPowerDataHolder data) {
        return this.primaryCooldownSupplier.applyAsInt(data);
    }

    public T setPrimaryCooldown(int ticks) {
        return this.setPrimaryCooldown(data -> ticks);
    }

    public T setPrimaryCooldown(ToIntFunction<SongPowerDataHolder> supplier) {
        this.primaryCooldownSupplier = supplier;
        return this.get();
    }

    public int getSecondaryCooldown(SongPowerData.SinglePowerData data) {
        return this.getSecondaryCooldown(new SongPowerDataHolder(data));
    }

    public int getSecondaryCooldown(SongPowerDataHolder data) {
        return this.secondaryCooldownSupplier.applyAsInt(data);
    }

    public T setSecondaryCooldown(int ticks) {
        return this.setSecondaryCooldown(data -> ticks);
    }

    public T setSecondaryCooldown(ToIntFunction<SongPowerDataHolder> supplier) {
        this.secondaryCooldownSupplier = supplier;
        return this.get();
    }

    public double getExhaustion(SongPowerData.SinglePowerData data) {
        return this.getExhaustion(new SongPowerDataHolder(data));
    }

    public double getExhaustion(SongPowerDataHolder data) {
        return this.exhaustion.applyAsDouble(data);
    }

    public T setExhaustion(float exhaustion) {
        return this.setExhaustion(data -> exhaustion);
    }

    public T setExhaustion(ToDoubleFunction<SongPowerDataHolder> exhaustion) {
        this.exhaustion = exhaustion;
        return this.get();
    }

    public boolean isPersist() {
        return this.getType() == PowerType.PERSIST;
    }

    public boolean cancellable() {
        return this.getType() == PowerType.INSTANT;
    }

    public void apply(SongPowerData.SinglePowerData data) {
        this.applyInternal(new SongPowerDataHolder(data));
    }

    public void unapply(SongPowerData.SinglePowerData data) {
    }

    public boolean isEmpty() {
        return this == DummySongPower.EMPTY || this.id.isEmpty();
    }

    public String getTranslateKey() {
        return "power." + SongsOfPower.MOD_ID + "." + this.id;
    }

    protected abstract void applyInternal(SongPowerDataHolder holder);

    protected abstract PowerType getType();

    protected abstract T get();

    protected static void playSound(SongPowerDataHolder holder, @Nullable Supplier<SoundEvent> sound) {
        if (sound != null)
            SoundUtil.playSound(holder.getWorld(), holder.getPlayer().getX(), holder.getPlayer().getY(), holder.getPlayer().getZ(), sound.get().getId(), 0.5f, 1);
    }

    public T experimental() {
        this.experimental = true;
        return this.get();
    }

    public boolean isExperimental() {
        return this.experimental;
    }

    protected enum PowerType {
        INSTANT, INTERVAL, PERSIST, DELAY, DUMMY;
    }
}
