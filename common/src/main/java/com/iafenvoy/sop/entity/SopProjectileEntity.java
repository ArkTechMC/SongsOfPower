package com.iafenvoy.sop.entity;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.sop.world.FakeExplosionBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SopProjectileEntity extends PersistentProjectileEntity implements SupporekesisControllable {
    private static final TrackedData<Integer> DISAPPEAR_CD = DataTracker.registerData(SopProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> EXPLODE = DataTracker.registerData(SopProjectileEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private float damageMultiplier = 1f;

    protected SopProjectileEntity(EntityType<? extends SopProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setNoClip(true);
        this.setNoGravity(true);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DISAPPEAR_CD, 0);
        this.dataTracker.startTracking(EXPLODE, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("disappear_cd", this.getDisappearCd());
        nbt.putBoolean("explode", this.getExplode());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setDisappearCd(nbt.getInt("disappear_cd"));
        this.setExplode(nbt.getBoolean("explode"));
    }

    @NotNull
    protected Entity ownerOrSelf() {
        return this.getOwner() == null ? this : this.getOwner();
    }

    public float transformDamage(float base) {
        return base * this.damageMultiplier;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        double d = vec3d.horizontalLength();
        this.setYaw((float) (MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875));
        this.setPitch((float) (MathHelper.atan2(vec3d.y, d) * 57.2957763671875));
        if (this.getY() > 1000 || this.age > 20 * 60)
            this.remove(RemovalReason.DISCARDED);
        int cd = this.getDisappearCd();
        if (cd == 1) {
            if (this.getExplode())
                this.getEntityWorld().createExplosion(this, DamageUtil.build(this.ownerOrSelf(), DamageTypes.EXPLOSION), new FakeExplosionBehavior(), this.getPos(), 0, false, World.ExplosionSourceType.NONE);
            this.remove(RemovalReason.DISCARDED);
        } else if (cd > 1)
            this.setDisappearCd(cd - 1);
    }

    public boolean getExplode() {
        return this.dataTracker.get(EXPLODE);
    }

    public int getDisappearCd() {
        return this.dataTracker.get(DISAPPEAR_CD);
    }

    public void setExplode(boolean explode) {
        this.dataTracker.set(EXPLODE, explode);
    }

    public void setDisappearCd(int cd) {
        this.dataTracker.set(DISAPPEAR_CD, cd, true);
    }

    public void setCritical() {
        this.damageMultiplier = 1.5f;
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.FIRE_CHARGE);
    }

    @Override
    public void setDisappearCd(int disappearDelay, boolean explode) {
        this.setVelocity(0, 0, 0);
        this.velocityModified = true;
        this.setDisappearCd(disappearDelay + 1);
        this.setExplode(explode);
    }
}
