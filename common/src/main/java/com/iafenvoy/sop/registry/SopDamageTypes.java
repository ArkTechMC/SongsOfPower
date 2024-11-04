package com.iafenvoy.sop.registry;

import com.iafenvoy.sop.SongsOfPower;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class SopDamageTypes {
    public static final RegistryKey<DamageType> AGGRODETONATE = of("aggrodetonate");
    public static final RegistryKey<DamageType> AGGROQUAKE = of("aggroquake");
    public static final RegistryKey<DamageType> AGGROSPHERE = of("aggrosphere");
    public static final RegistryKey<DamageType> AGGROSTORM = of("aggrostorm");
    public static final RegistryKey<DamageType> AGGROSHARD = of("aggroshard");

    private static RegistryKey<DamageType> of(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(SongsOfPower.MOD_ID, id));
    }
}
