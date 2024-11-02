package com.iafenvoy.sop.entity;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.sop.config.SopConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;

public class AggroDetonateEntity extends SopProjectileEntity implements SupporekesisControllable {
    private static final TrackedData<Float> PERSIST_PITCH = DataTracker.registerData(AggroDetonateEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> PERSIST_YAW = DataTracker.registerData(AggroDetonateEntity.class, TrackedDataHandlerRegistry.FLOAT);

    public AggroDetonateEntity(EntityType<? extends SopProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(PERSIST_PITCH, 0F);
        this.dataTracker.startTracking(PERSIST_YAW, 0F);
    }

    @Override
    public void tick() {
        super.tick();
        this.updateRotation();
        this.setNoClip(true);
        this.setNoGravity(true);
        if (this.getY() > 1000 || this.age > 20 * 60)
            this.remove(RemovalReason.DISCARDED);
        LivingEntity target = this.getEntityWorld().getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT, null, this.getX(), this.getY(), this.getZ(), new Box(this.getPos().add(1, 1, 1), this.getPos().subtract(1, 1, 1)));
        if (target != null) this.explode();
        else if (this.isOnGround() || this.inGround || !this.getEntityWorld().getBlockState(this.getBlockPos()).isAir())
            this.explode();
    }

    public void explode() {
        this.getEntityWorld().createExplosion(this, DamageUtil.build(this.ownerOrSelf(), DamageTypes.MOB_ATTACK), new ExplosionBehavior(), this.getPos(), SopConfig.INSTANCE.aggressium.aggrodetonatePower.getValue().floatValue(), false, World.ExplosionSourceType.MOB);
        this.remove(RemovalReason.DISCARDED);
    }

    public void setPersistAngle(float persistPitch, float persistYaw) {
        this.setPersistPitch(persistPitch);
        this.setPersistYaw(persistYaw);
    }

    public void setPersistPitch(float persistPitch) {
        this.dataTracker.set(PERSIST_PITCH, persistPitch);
    }

    public void setPersistYaw(float persistYaw) {
        this.dataTracker.set(PERSIST_YAW, persistYaw);
    }

    public float getPersistPitch() {
        return this.dataTracker.get(PERSIST_PITCH);
    }

    public float getPersistYaw() {
        return this.dataTracker.get(PERSIST_YAW);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putFloat("persistPitch", this.getPersistPitch());
        nbt.putFloat("persistYaw", this.getPersistYaw());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setPersistPitch(nbt.getFloat("persistPitch"));
        this.setPersistYaw(nbt.getFloat("persistYaw"));
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
