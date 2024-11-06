package com.iafenvoy.sop.power;

import com.iafenvoy.sop.impl.ComponentManager;
import com.iafenvoy.sop.item.block.AbstractSongCubeBlock;
import com.iafenvoy.sop.power.type.AbstractSongPower;
import com.iafenvoy.sop.power.type.DummySongPower;
import com.iafenvoy.sop.power.type.PersistSongPower;
import com.iafenvoy.sop.util.Serializable;
import com.iafenvoy.sop.util.Tickable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SongPowerData implements Serializable, Tickable {
    private final PlayerEntity player;
    private boolean enabled = false;
    private final Map<String, Serializable> components = new ConcurrentHashMap<>();
    private final Map<PowerCategory, SinglePowerData> byType = new HashMap<>();
    private final SinglePowerData aggressium = this.createSingle(this, PowerCategory.AGGRESSIUM);
    private final SinglePowerData mobilium = this.createSingle(this, PowerCategory.MOBILIUM);
    private final SinglePowerData protisium = this.createSingle(this, PowerCategory.PROTISIUM);
    private final SinglePowerData supportium = this.createSingle(this, PowerCategory.SUPPORTIUM);
    private boolean dirty = false;

    public SongPowerData(PlayerEntity player) {
        this.player = player;
    }

    protected SinglePowerData createSingle(SongPowerData parent, PowerCategory type) {
        SinglePowerData data = new SinglePowerData(parent, type);
        this.components.put(type.getId(), data);
        this.byType.put(type, data);
        return data;
    }

    @Override
    public void encode(NbtCompound nbt) {
        nbt.putBoolean("enabled", this.enabled);
        for (Map.Entry<String, Serializable> entry : this.components.entrySet())
            nbt.put(entry.getKey(), entry.getValue().encode());
    }

    @Override
    public void decode(NbtCompound nbt) {
        this.enabled = nbt.getBoolean("enabled");
        for (Map.Entry<String, Serializable> entry : this.components.entrySet())
            if (nbt.contains(entry.getKey(), NbtElement.COMPOUND_TYPE))
                entry.getValue().decode(nbt.getCompound(entry.getKey()));
    }

    @Override
    public void tick() {
        if (this.player.getEntityWorld() instanceof ServerWorld serverWorld) {
            for (Map.Entry<String, Serializable> entry : this.components.entrySet())
                if (entry.getValue() instanceof Tickable tickable)
                    tickable.tick();
            PowerMergeHelper.run(this, this.player, serverWorld);
        }
    }

    public void dropAll() {
        this.aggressium.drop();
        this.mobilium.drop();
        this.protisium.drop();
        this.supportium.drop();
    }

    public boolean isDirty() {
        boolean dirty = this.dirty;
        this.dirty = false;
        return dirty;
    }

    public void markDirty() {
        this.dirty = true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.markDirty();
    }

    public void enable() {
        this.setEnabled(true);
    }

    public void disable() {
        this.setEnabled(false);
    }

    public SinglePowerData get(PowerCategory category) {
        return this.byType.get(category);
    }

    public void addComponent(String id, Serializable serializable) {
        this.components.put(id, serializable);
    }

    public Serializable getComponent(String id) {
        return this.components.getOrDefault(id, Serializable.EMPTY);
    }

    public void removeComponent(String id) {
        this.components.remove(id);
    }

    public boolean powerEnabled(PowerCategory category, AbstractSongPower<?> power) {
        SinglePowerData data = this.get(category);
        return data.hasPower() && data.getActivePower() == power && data.isEnabled();
    }

    public void disableAllPower() {
        this.byType.values().forEach(SinglePowerData::disable);
    }

    public static SongPowerData byPlayer(PlayerEntity player) {
        return ComponentManager.getSongPowerData(player);
    }

    public static void stop(MinecraftServer server) {//Song power cache will not save to disk, so we need to clear them
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            SongPowerData data = byPlayer(player);
            data.disableAllPower();
        }
    }

    public static class SinglePowerData implements Serializable, Tickable {
        private final SongPowerData parent;
        private final PowerCategory type;
        private AbstractSongPower<?> activePower = DummySongPower.EMPTY;
        private boolean enabled = false;
        private int primaryCooldown = 0;
        private int secondaryCooldown = 0;

        public SinglePowerData(SongPowerData parent, PowerCategory type) {
            this.parent = parent;
            this.type = type;
        }

        @Override
        public void encode(NbtCompound nbt) {
            nbt.putBoolean("enabled", this.enabled);
            nbt.putInt("primaryCooldown", this.primaryCooldown);
            nbt.putInt("secondaryCooldown", this.secondaryCooldown);
            nbt.putString("activePower", this.activePower.getId());
        }

        @Override
        public void decode(NbtCompound nbt) {
            this.enabled = nbt.getBoolean("enabled");
            this.primaryCooldown = nbt.getInt("primaryCooldown");
            this.secondaryCooldown = nbt.getInt("secondaryCooldown");
            this.activePower = AbstractSongPower.BY_ID.getOrDefault(nbt.getString("activePower"), DummySongPower.EMPTY);
        }

        @Override
        public void tick() {
            State state = this.getState();
            if (state == State.DENY) {
                this.primaryCooldown--;
                this.parent.markDirty();
            } else if (state == State.RECOVER) {
                this.secondaryCooldown--;
                this.parent.markDirty();
            }
            if (this.isEnabled() && !this.parent.player.getEntityWorld().isClient && this.activePower instanceof PersistSongPower persistSongPower) {
                if (persistSongPower.tick(this)) this.disable();
                this.parent.markDirty();
            }
        }

        public void keyPress() {
            if (this.activePower.isEmpty()) {
                this.disable();
                return;
            }
            if (this.getState() == State.DENY) return;
            if (this.isEnabled()) {
                if (this.activePower.isPersist()) this.disable();
                else this.activePower.unapply(this);
            } else {
                if (this.activePower.isPersist()) this.enable();
                else {
                    if (this.getState() == State.RECOVER) {
                        this.getPlayer().addExhaustion((float) this.activePower.getExhaustion(this));
                        this.secondaryCooldown = 0;
                    }
                    if (this.getState() == State.ALLOW) this.activePower.apply(this);
                }
            }
        }

        public PowerCategory getType() {
            return this.type;
        }

        public boolean isEnabled() {
            return this.enabled && this.parent.enabled;
        }

        public void setEnabled(boolean enabled) {
            if (!this.enabled && enabled)
                this.activePower.apply(this);
            if (this.enabled && !enabled)
                this.activePower.unapply(this);
            this.enabled = enabled;
            this.parent.markDirty();
        }

        public void enable() {
            this.setEnabled(true);
        }

        public void disable() {
            this.setEnabled(false);
        }

        public AbstractSongPower<?> getActivePower() {
            return this.activePower;
        }

        public void drop() {
            Block.dropStack(this.getPlayer().getEntityWorld(), this.getPlayer().getBlockPos(), this.activePower.getStack());
            this.setActivePower(DummySongPower.EMPTY);
        }

        public void setActivePower(AbstractSongPower<?> activePower) {
            if (!this.activePower.isEmpty() && this.activePower != activePower)
                this.disable();
            this.activePower = activePower;
            this.parent.markDirty();
        }

        public void setHoldItem(ItemStack holdItem) {
            if (holdItem.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSongCubeBlock songCube)
                this.setActivePower(songCube.getPower(holdItem));
            else if (holdItem.isEmpty())
                this.setActivePower(DummySongPower.EMPTY);
            else
                throw new IllegalArgumentException("holdItem should be a song cube.");
        }

        public boolean hasPower() {
            return !this.activePower.isEmpty();
        }

        public PlayerEntity getPlayer() {
            return this.parent.player;
        }

        public int getPrimaryCooldown() {
            return this.primaryCooldown;
        }

        public int getSecondaryCooldown() {
            return this.secondaryCooldown;
        }

        public State getState() {
            if (this.primaryCooldown > 0) return State.DENY;
            if (this.secondaryCooldown > 0) return State.RECOVER;
            return State.ALLOW;
        }

        public void cooldown() {
            this.primaryCooldown = this.activePower.getPrimaryCooldown(this);
            this.secondaryCooldown = this.activePower.getSecondaryCooldown(this);
        }
    }

    public enum State {
        ALLOW, RECOVER, DENY
    }
}
