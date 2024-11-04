package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.SongsOfPower;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class SopSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(SongsOfPower.MOD_ID, RegistryKeys.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> AGGROQUAKE = register("aggroquake");
    public static final RegistrySupplier<SoundEvent> AGGROSPHERE = register("aggrosphere");
    public static final RegistrySupplier<SoundEvent> AGGROSHARD = register("aggroshard");
    public static final RegistrySupplier<SoundEvent> MOBILIBOUNCE = register("mobilibounce");
    public static final RegistrySupplier<SoundEvent> MOBILIFLASH = register("mobiliflash");
    public static final RegistrySupplier<SoundEvent> PROTEPOINT = register("protepoint");
    public static final RegistrySupplier<SoundEvent> PROTESPHERE = register("protesphere");
    public static final RegistrySupplier<SoundEvent> PROTESPHERE_UNAPPLY = register("protesphere_unapply");
    public static final RegistrySupplier<SoundEvent> PROTEARMOR = register("protearmor");
    public static final RegistrySupplier<SoundEvent> PROTEARMOR_UNAPPLY = register("protearmor_unapply");

    public static final RegistrySupplier<SoundEvent> AGGRESSIUM = register("aggressium");
    public static final RegistrySupplier<SoundEvent> MOBILIUM = register("mobilium");
    public static final RegistrySupplier<SoundEvent> PROTISIUM = register("protisium");
    public static final RegistrySupplier<SoundEvent> SUPPORTIUM = register("supportium");

    private static RegistrySupplier<SoundEvent> register(String id) {
        return REGISTRY.register(id, () -> SoundEvent.of(Identifier.of(SongsOfPower.MOD_ID, id)));
    }
}
