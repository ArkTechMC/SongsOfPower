package com.iafenvoy.sop.registry;

import com.iafenvoy.neptune.event.LivingEntityEvents;
import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import com.iafenvoy.sop.Static;
import com.iafenvoy.sop.config.SopConfig;
import com.iafenvoy.sop.entity.AggroDetonateEntity;
import com.iafenvoy.sop.entity.AggroShardEntity;
import com.iafenvoy.sop.entity.AggroSphereEntity;
import com.iafenvoy.sop.entity.SupporekesisControllable;
import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.power.SongPowerData;
import com.iafenvoy.sop.power.type.*;
import com.iafenvoy.sop.util.SopMath;
import com.iafenvoy.sop.util.WorldUtil;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
public final class SopPowers {
    //Aggressium
    public static final DelaySongPower AGGROSPHERE = new DelaySongPower("aggrosphere", PowerCategory.AGGRESSIUM)
            .setApplySound(SopSounds.AGGROSPHERE)
            .setDelay(6)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggrospherePrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggrosphereSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.aggressium.aggrosphereExhaustion.getValue())
            .onApply(holder -> {
                World world = holder.getWorld();
                PlayerEntity player = holder.getPlayer();
                AggroSphereEntity aggroSphere = SopEntities.AGGRO_SPHERE.get().create(world);
                if (aggroSphere != null) {
                    final Vec3d dir = SopMath.getRotationVectorUnit(player.getPitch(), player.getHeadYaw());
                    aggroSphere.setVelocity(dir.multiply(SopConfig.INSTANCE.aggressium.aggrosphereSpeed.getValue()));
                    holder.processProjectile(aggroSphere);
                    world.spawnEntity(aggroSphere);
                }
            });
    public static final DelaySongPower AGGROQUAKE = new DelaySongPower("aggroquake", PowerCategory.AGGRESSIUM)
            .setApplySound(SopSounds.AGGROQUAKE)
            .setDelay(8)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggroquakePrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggroquakeSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.aggressium.aggroquakeExhaustion.getValue())
            .onApply(holder -> {
                PlayerEntity player = holder.getPlayer();
                double r = SopConfig.INSTANCE.aggressium.aggroquakeRange.getValue();
                Vec3d range = new Vec3d(r, r, r);
                List<LivingEntity> entities = holder.getWorld().getEntitiesByClass(LivingEntity.class, new Box(player.getPos().add(range), player.getPos().subtract(range)), x -> x != player);
                for (LivingEntity living : entities) {
                    Vec3d dir = SopMath.reverse(player.getPos().subtract(living.getPos()), r).multiply(-0.5);
                    living.damage(DamageUtil.build(living, SopDamageTypes.AGGROQUAKE), SopConfig.INSTANCE.aggressium.aggroquakeDamage.getValue().floatValue());
                    living.setVelocity(dir.add(0, 0.5, 0));
                    living.velocityModified = true;
                }
            });
    public static final InstantSongPower AGGROSHOCK = new InstantSongPower("aggroshock", PowerCategory.AGGRESSIUM).experimental()
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggroshockPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggroshockSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.aggressium.aggroshockExhaustion.getValue())
            .onApply(holder -> {
                if (!(holder.getWorld() instanceof ServerWorld serverWorld)) return;
                PlayerEntity player = holder.getPlayer();
                final Vec3d dir = SopMath.getRotationVectorUnit(player.getPitch(), player.getHeadYaw());
                Vec3d pos = player.getPos();
                for (int i = 0; i < SopConfig.INSTANCE.aggressium.aggroshockDistance.getValue(); i++) {
                    pos = pos.add(dir);
                    EntityUtil.lightening(serverWorld, pos.x, pos.y, pos.z, false);
                }
            });
    public static final PersistSongPower AGGROSTORM = new PersistSongPower("aggrostorm", PowerCategory.AGGRESSIUM).experimental()
            .setExhaustion(holder -> SopConfig.INSTANCE.aggressium.aggrostormExhaustion.getValue())
            .onTick(holder -> {
                PlayerEntity player = holder.getPlayer();
                double r = SopConfig.INSTANCE.aggressium.aggrostormRange.getValue();
                Vec3d range = new Vec3d(r, r, r);
                List<LivingEntity> entities = holder.getWorld().getEntitiesByClass(LivingEntity.class, new Box(player.getPos().add(range), player.getPos().subtract(range)), x -> x != player);
                for (LivingEntity living : entities) {
                    Vec3d v = player.getPos().subtract(living.getPos());
                    Vec3d dir = SopMath.reverse(v, r).multiply(SopConfig.INSTANCE.aggressium.aggrostormStrength.getValue());
                    if (v.length() <= r / 2)
                        living.damage(DamageUtil.build(living, SopDamageTypes.AGGROSTORM), SopConfig.INSTANCE.aggressium.aggrostormDamage.getValue().floatValue() / 20);
                    living.setVelocity(dir);
                    living.velocityModified = true;
                }
            });
    public static final InstantSongPower AGGRODETONATE = new InstantSongPower("aggrodetonate", PowerCategory.AGGRESSIUM)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggrodetonatePrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggrodetonateSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.aggressium.aggrodetonateExhaustion.getValue())
            .onApply(holder -> {
                World world = holder.getWorld();
                PlayerEntity player = holder.getPlayer();
                AggroDetonateEntity aggroDetonate = SopEntities.AGGRO_DETONATE.get().create(world);
                if (aggroDetonate != null) {
                    Vec3d dir = SopMath.getRotationVectorUnit(player.getPitch(), player.getHeadYaw());
                    aggroDetonate.setVelocity(dir.multiply(SopConfig.INSTANCE.aggressium.aggrodetonateSpeed.getValue()));
                    holder.processProjectile(aggroDetonate);
                    world.spawnEntity(aggroDetonate);
                }
            });
    public static final DelaySongPower AGGROSHARD = new DelaySongPower("aggroshard", PowerCategory.AGGRESSIUM)
            .setApplySound(SopSounds.AGGROSHARD)
            .setDelay(12)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggroshardPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.aggressium.aggroshardSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.aggressium.aggroshardExhaustion.getValue())
            .onApply(holder -> {
                World world = holder.getWorld();
                PlayerEntity player = holder.getPlayer();
                Random random = new Random();
                for (int i = 0; i < SopConfig.INSTANCE.aggressium.aggroshardCount.getValue(); i++) {
                    Timeout.create(random.nextInt(25), () -> {
                        AggroShardEntity aggroShard = SopEntities.AGGRO_SHARD.get().create(world);
                        if (aggroShard != null) {
                            Vec3d dir = SopMath.getRotationVectorUnit(player.getPitch(), player.getHeadYaw());
                            final double MUL = 0.2, speed = SopConfig.INSTANCE.aggressium.aggroshardSpeed.getValue();
                            aggroShard.setVelocity(dir.multiply(speed).add(RandomHelper.nextDouble(-speed * MUL, speed * MUL), RandomHelper.nextDouble(-speed * MUL, speed * MUL), RandomHelper.nextDouble(-speed * MUL, speed * MUL)));
                            holder.processProjectile(aggroShard);
                            world.spawnEntity(aggroShard);
                        }
                    });
                }
            });
    //Mobilium
    public static final DelaySongPower MOBILIFLASH = new DelaySongPower("mobiliflash", PowerCategory.MOBILIUM)
            .setApplySound(SopSounds.MOBILIFLASH)
            .setDelay(20)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.mobilium.mobiliflashPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.mobilium.mobiliflashSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.mobilium.mobiliflashExhaustion.getValue())
            .onApply(holder -> {
                World world = holder.getWorld();
                PlayerEntity player = holder.getPlayer();
                final Vec3d dir = SopMath.getRotationVectorUnit(MathHelper.clamp(player.getPitch(), -15, 15), player.getHeadYaw());
                player.setVelocity(dir.multiply(SopConfig.INSTANCE.mobilium.mobiliflashSpeed.getValue()));
                player.velocityModified = true;
            });
    public static final PersistSongPower MOBILIWINGS = new PersistSongPower("mobiliwings", PowerCategory.MOBILIUM)
            .setApplySound(() -> SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA)
            .setExhaustion(holder -> SopConfig.INSTANCE.mobilium.mobiliwingsExhaustion.getValue())
            .onApply(holder -> holder.getPlayer().startFallFlying())
            .onTick(holder -> {
                PlayerEntity player = holder.getPlayer();
                if (player.isOnGround() || player.getAbilities().flying) holder.cancel();
            });
    public static final PersistSongPower MOBILIGLIDE = new PersistSongPower("mobiliglide", PowerCategory.MOBILIUM)
            .setExhaustion(holder -> SopConfig.INSTANCE.mobilium.mobiliglideExhaustion.getValue())
            .onApply(holder -> {//GRAVITY attribute not available before 1.20.5
                EntityAttributeInstance instance = holder.getPlayer().getAttributes().getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (instance != null)
                    instance.addTemporaryModifier(new EntityAttributeModifier(Static.MOBILIGLIDE_UUID, "mobiliglide", 1, EntityAttributeModifier.Operation.ADDITION));
            })
            .onTick(holder -> {
                if (holder.getPlayer().isOnGround() || holder.getPlayer().getAbilities().flying) holder.cancel();
            })
            .onUnapply(holder -> {
                EntityAttributeInstance instance = holder.getPlayer().getAttributes().getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (instance != null) instance.removeModifier(Static.MOBILIGLIDE_UUID);
            });
    public static final InstantSongPower MOBILIBOUNCE = new InstantSongPower("mobilibounce", PowerCategory.MOBILIUM).experimental()
            .setApplySound(SopSounds.MOBILIBOUNCE)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.mobilium.mobilibouncePrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.mobilium.mobilibounceSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.mobilium.mobilibounceExhaustion.getValue())
            .onApply(holder -> {
                PlayerEntity player = holder.getPlayer();
                World world = holder.getWorld();
                BlockPos below = player.getBlockPos().down();
                BlockState state = world.getBlockState(below);
                if (state.isSolidBlock(world, below) || player.isOnGround()) holder.cancel();
                world.setBlockState(below, SopBlocks.MOBILIBOUNCE_PLATFORM.get().getDefaultState(), 2, 0);
                player.setVelocity(0, 0, 0);
                player.velocityModified = true;
                Timeout.create(20 * SopConfig.INSTANCE.mobilium.mobilibounceExistTime.getValue(), () -> world.setBlockState(below, state, 2, 0));
            });
    //Protisium
    public static final PersistSongPower PROTESPHERE = new PersistSongPower("protesphere", PowerCategory.PROTISIUM)
            .setApplySound(SopSounds.PROTESPHERE)
            .setExhaustion(holder -> SopConfig.INSTANCE.protisium.protesphereExhaustion.getValue())
            .onApply(holder -> {
                EntityAttributeInstance instance = holder.getPlayer().getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR);
                if (instance != null)
                    instance.addTemporaryModifier(new EntityAttributeModifier(Static.PROTESPHERE_UUID, "protesphere", 50, EntityAttributeModifier.Operation.ADDITION));
            })
            .setUnapplySound(SopSounds.PROTESPHERE_UNAPPLY)
            .onUnapply(holder -> {
                EntityAttributeInstance instance = holder.getPlayer().getAttributes().getCustomInstance(EntityAttributes.GENERIC_ARMOR);
                if (instance != null) instance.removeModifier(Static.PROTESPHERE_UUID);
            });
    public static final PersistSongPower PROTEPOINT = new PersistSongPower("protepoint", PowerCategory.PROTISIUM)
            .setApplySound(SopSounds.PROTEPOINT)
            .setExhaustion(holder -> SopConfig.INSTANCE.protisium.protepointExhaustion.getValue())
            .onApply(holder -> {
                ItemStack stack = new ItemStack(SopItems.PROTEPOINT_SHIELD.get());
                holder.getPlayer().setStackInHand(Hand.OFF_HAND, stack);
            }).onTick(holder -> {
                if (!holder.getPlayer().getOffHandStack().isOf(SopItems.PROTEPOINT_SHIELD.get())) holder.cancel();
            }).onUnapply(holder -> {
                if (holder.getPlayer().getOffHandStack().isOf(SopItems.PROTEPOINT_SHIELD.get()))
                    holder.getPlayer().setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY.copy());
            });
    public static final IntervalSongPower PROTEHEAL = new IntervalSongPower("proteheal", PowerCategory.PROTISIUM)
            .setInterval(10)
            .setTimes(10)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.protisium.protehealPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.protisium.protehealSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.protisium.protehealExhaustion.getValue())
            .onApply(holder -> {
                PlayerEntity player = holder.getPlayer();
                if (player.getHealth() >= player.getMaxHealth()) {
                    holder.cancel();
                    return;
                }
                player.heal(1);
            });
    public static final PersistSongPower PROTEARMOR = new PersistSongPower("protearmor", PowerCategory.PROTISIUM)
            .setApplySound(SopSounds.PROTEARMOR)
            .setUnapplySound(SopSounds.PROTEARMOR_UNAPPLY)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.protisium.protearmorPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.protisium.protearmorSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.protisium.protearmorExhaustion.getValue())
            .onInit(self -> LivingEntityEvents.DAMAGE.register((entity, source, amount) -> {
                if (entity instanceof PlayerEntity player) {
                    SongPowerData data = SongPowerData.byPlayer(player);
                    if (data.powerEnabled(PowerCategory.PROTISIUM, self)) {
                        data.get(PowerCategory.PROTISIUM).disable();
                        return Math.max(amount - SopConfig.INSTANCE.protisium.protearmorMaxReduceDamage.getValue().floatValue(), 0);
                    }
                }
                return amount;
            }));
    //Supportium
    public static final InstantSongPower SUPPOROLIFT = new InstantSongPower("supporolift", PowerCategory.SUPPORTIUM)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.supportium.supporoliftPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.supportium.supporoliftSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.supportium.supporoliftExhaustion.getValue())
            .onApply(holder -> {
                PlayerEntity player = holder.getPlayer();
                EntityHitResult result = WorldUtil.raycastEntity(player, SopConfig.INSTANCE.supportium.supporoliftRange.getValue());
                if (result != null && result.getEntity() instanceof LivingEntity living) {
                    Vec3d dir = player.getPos().subtract(living.getPos()).multiply(0.2);
                    living.setVelocity(dir.add(0, 0.3, 0));
                    living.velocityModified = true;
                } else holder.cancel();
            });
    public static final InstantSongPower SUPPOREKESIS = new InstantSongPower("supporekesis", PowerCategory.SUPPORTIUM)
            .setPrimaryCooldown(holder -> SopConfig.INSTANCE.supportium.supporekesisPrimaryCooldown.getValue())
            .setSecondaryCooldown(holder -> SopConfig.INSTANCE.supportium.supporekesisSecondaryCooldown.getValue())
            .setExhaustion(holder -> SopConfig.INSTANCE.supportium.supporekesisExhaustion.getValue())
            .onApply(holder -> {
                PlayerEntity player = holder.getPlayer();
                World world = holder.getWorld();
                double r = SopConfig.INSTANCE.supportium.supporekesisRange.getValue();
                List<Entity> controllables = world.getEntitiesByClass(Entity.class, new Box(player.getX() - r, player.getY() - r, player.getZ() - r, player.getX() + r, player.getY() + r, player.getZ() + r), c -> c instanceof SupporekesisControllable);
                for (Entity c : controllables) {
                    if (c instanceof Ownable ownable && ownable.getOwner() != null && Objects.equals(ownable.getOwner().getUuid(), player.getUuid()) && !SopConfig.INSTANCE.supportium.supporekesisControlSelf.getValue())
                        continue;
                    if (c instanceof SupporekesisControllable controllable)
                        controllable.setDisappearCd(10, true);
                }
            });

    public static void init() {
        for (AbstractSongPower<?> power : AbstractSongPower.POWERS) {
            power.init();
            CreativeTabRegistry.appendStack(SopItemGroups.MAIN, power::getStack);
        }
    }
}
