package com.iafenvoy.sop.registry;

import com.iafenvoy.neptune.util.EntityBuildHelper;
import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.entity.AggroSphereEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public final class SopEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(SongsOfPower.MOD_ID, RegistryKeys.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<AggroSphereEntity>> AGGRO_SPHERE = register("aggro_sphere", EntityBuildHelper.build("aggro_sphere", AggroSphereEntity::new, SpawnGroup.MISC, 64, 1, false, new EntityBuildHelper.Dimension(0.5F, 0.5F)));

    private static <T extends Entity> RegistrySupplier<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
        return REGISTRY.register(name, type);
    }
}
