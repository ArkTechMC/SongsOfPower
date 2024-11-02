package com.iafenvoy.sop.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class SopProjectileEntity extends PersistentProjectileEntity {
    private float damageMultiplier = 1f;
    @Nullable
    protected Entity owner;

    protected SopProjectileEntity(EntityType<? extends SopProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setOwner(@Nullable Entity owner) {
        this.owner = owner;
    }

    @NotNull
    protected Entity ownerOrSelf() {
        return this.owner == null ? this : this.owner;
    }

    public float transformDamage(float base) {
        return base * this.damageMultiplier;
    }

    public void setCritical() {
        this.damageMultiplier = 1.5f;
    }
}
