package com.iafenvoy.sop.power.component;

import com.iafenvoy.sop.Static;
import com.iafenvoy.sop.power.SongPowerData;
import com.iafenvoy.sop.util.Serializable;
import com.iafenvoy.sop.util.SopMath;
import com.iafenvoy.sop.util.Tickable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class MobiliBurstComponent implements Serializable, Tickable {
    private final PlayerEntity player;
    private boolean activate;
    private int tick = -1;

    public MobiliBurstComponent(PlayerEntity player) {
        this.player = player;
    }

    public boolean isActivate() {
        return this.activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void setMaxTick(int tick) {
        this.tick = tick;
    }

    @Override
    public void encode(NbtCompound nbt) {
        nbt.putBoolean("activate", this.activate);
    }

    @Override
    public void decode(NbtCompound nbt) {
        this.activate = nbt.getBoolean("activate");
    }

    @Override
    public void tick() {
        if (!this.activate) return;
        if (this.player.getVelocity().length() <= 0.1) this.tick = 0;
        else {
            Vec3d pos = this.player.getPos();
            double r = 1;
            List<Entity> entities = this.player.getWorld().getOtherEntities(this.player, new Box(pos.add(new Vec3d(r, r, r)), pos.subtract(new Vec3d(r, r, r))), entity -> true);
            entities.forEach(entity -> {
                Vec3d dir = entity.getPos().subtract(this.player.getPos()).add(this.player.getVelocity());
                entity.setVelocity(SopMath.toUnit(dir.add(0, 0.5, 0)).multiply(this.player.getVelocity().length()));
                entity.velocityModified = true;
            });
        }
        if (this.tick == 0) {
            this.activate = false;
            SongPowerData.byPlayer(this.player).removeComponent(Static.MOBILIBURST);
        }
        this.tick--;
    }
}
