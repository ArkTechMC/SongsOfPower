package com.iafenvoy.sop.power;

import com.iafenvoy.sop.entity.SopProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

public class SongPowerDataHolder {
    private final SongPowerData.SinglePowerData data;
    private boolean cancelled = false;

    public SongPowerDataHolder(SongPowerData.SinglePowerData data) {
        this.data = data;
    }

    public SongPowerData.SinglePowerData getData() {
        return this.data;
    }

    public PlayerEntity getPlayer() {
        return this.data.getPlayer();
    }

    public World getWorld() {
        return this.data.getPlayer().getEntityWorld();
    }

    public boolean usingWeapon() {
        return this.getPlayer().getMainHandStack().getItem() instanceof SwordItem;
    }

    public void processProjectile(SopProjectileEntity projectile) {
        PlayerEntity player = this.getPlayer();
        projectile.setOwner(player);
        projectile.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
        projectile.refreshPositionAndAngles(player.getX(), player.getY() + 1, player.getZ(), 0, 0);
        if (this.usingWeapon()) projectile.setCritical();
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void cooldown() {
        this.data.cooldown();
    }
}
