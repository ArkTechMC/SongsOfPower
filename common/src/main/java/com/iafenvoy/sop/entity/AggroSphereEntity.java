package com.iafenvoy.sop.entity;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.sop.config.SopConfig;
import com.iafenvoy.sop.registry.SopDamageTypes;
import com.iafenvoy.sop.world.FakeExplosionBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AggroSphereEntity extends SopProjectileEntity implements SupporekesisControllable {
    public AggroSphereEntity(EntityType<? extends AggroSphereEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isOnGround() || this.inGround || !this.getEntityWorld().getBlockState(this.getBlockPos()).isAir()) {
            this.getEntityWorld().createExplosion(this, DamageUtil.build(this.ownerOrSelf(), SopDamageTypes.AGGROSPHERE), new FakeExplosionBehavior(), this.getPos(), 2, false, World.ExplosionSourceType.NONE);
            this.remove(RemovalReason.DISCARDED);
        }
        LivingEntity target = this.getEntityWorld().getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT, null, this.getX(), this.getY(), this.getZ(), new Box(this.getPos().add(1, 1, 1), this.getPos().subtract(1, 1, 1)));
        if (target != null) {
            target.damage(DamageUtil.build(this.ownerOrSelf(), SopDamageTypes.AGGROSPHERE), this.transformDamage(SopConfig.INSTANCE.aggressium.aggrosphereDamage.getValue().floatValue()));
            this.remove(RemovalReason.DISCARDED);
        }
        for (int i = 0; i < 9; i++)
            this.getEntityWorld().addParticle(ParticleTypes.FLAME,
                    RandomHelper.rangeRand(this.getX(), 0.3),
                    RandomHelper.rangeRand(this.getY() + 0.25, 0.3),
                    RandomHelper.rangeRand(this.getZ(), 0.3),
                    this.getVelocity().getX(),
                    this.getVelocity().getY(),
                    this.getVelocity().getZ());
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
