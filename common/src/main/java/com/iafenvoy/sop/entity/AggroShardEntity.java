package com.iafenvoy.sop.entity;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.sop.config.SopConfig;
import com.iafenvoy.sop.registry.SopDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AggroShardEntity extends SopProjectileEntity implements SupporekesisControllable {
    public AggroShardEntity(EntityType<? extends SopProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.getEntityWorld().getClosestEntity(LivingEntity.class, TargetPredicate.DEFAULT, null, this.getX(), this.getY(), this.getZ(), new Box(this.getPos().add(1, 1, 1), this.getPos().subtract(1, 1, 1)));
        if (target != null) {
            target.damage(DamageUtil.build(this.ownerOrSelf(), SopDamageTypes.AGGROSHARD), SopConfig.INSTANCE.aggressium.aggroshardDamage.getValue().floatValue());
            this.remove(RemovalReason.DISCARDED);
        } else if (this.isOnGround() || this.inGround || !this.getEntityWorld().getBlockState(this.getBlockPos()).isAir())
            this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
