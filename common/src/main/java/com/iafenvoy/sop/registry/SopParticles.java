package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.SongsOfPower;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public final class SopParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(SongsOfPower.MOD_ID, RegistryKeys.PARTICLE_TYPE);

    public static final RegistrySupplier<DefaultParticleType> SONG_EFFECT = register("song_effect", () -> new DefaultParticleType(false) {
    });

    private static RegistrySupplier<DefaultParticleType> register(String id, Supplier<DefaultParticleType> type) {
        return REGISTRY.register(id, type);
    }
}
