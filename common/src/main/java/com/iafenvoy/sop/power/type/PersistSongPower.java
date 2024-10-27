package com.iafenvoy.sop.power.type;

import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerData;
import com.iafenvoy.sop.power.SongPowerDataHolder;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class PersistSongPower extends AbstractSongPower<PersistSongPower> {
    private Consumer<SongPowerDataHolder> unapply = data -> {
    }, tick = data -> {
    };
    @Nullable
    private Supplier<SoundEvent> unapplySound;

    public PersistSongPower(String id, PowerCategory category) {
        super(id, category);
    }

    public PersistSongPower setUnapplySound(Supplier<SoundEvent> unapplySound) {
        this.unapplySound = unapplySound;
        return this;
    }

    public PersistSongPower onUnapply(Consumer<SongPowerDataHolder> unapply) {
        this.unapply = unapply;
        return this;
    }

    public PersistSongPower onTick(Consumer<SongPowerDataHolder> tick) {
        this.tick = tick;
        return this;
    }

    @Override
    public void unapply(SongPowerData.SinglePowerData data) {
        SongPowerDataHolder holder = new SongPowerDataHolder(data);
        playSound(holder, this.unapplySound);
        this.unapply.accept(holder);
        holder.cooldown();
    }

    public boolean tick(SongPowerData.SinglePowerData data) {
        SongPowerDataHolder holder = new SongPowerDataHolder(data);
        this.tick.accept(holder);
        if (!holder.isCancelled()) data.getPlayer().addExhaustion((float) (this.getExhaustion(data) / 20));
        return holder.isCancelled();
    }

    @Override
    protected void applyInternal(SongPowerDataHolder holder) {
        playSound(holder, this.applySound);
        this.apply.accept(holder);
    }

    @Override
    protected PowerType getType() {
        return PowerType.PERSIST;
    }

    @Override
    protected PersistSongPower get() {
        return this;
    }
}
