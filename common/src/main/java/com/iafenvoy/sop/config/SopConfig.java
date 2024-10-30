package com.iafenvoy.sop.config;

import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer;
import com.iafenvoy.jupiter.config.entry.DoubleEntry;
import com.iafenvoy.jupiter.config.entry.IntegerEntry;
import com.iafenvoy.jupiter.interfaces.IConfigEntry;
import com.iafenvoy.sop.SongsOfPower;
import net.minecraft.util.Identifier;

public class SopConfig extends AutoInitConfigContainer {
    public static final SopConfig INSTANCE = new SopConfig();

    public final AggressiumPowerConfig aggressium = new AggressiumPowerConfig();
    public final MobiliumPowerConfig mobilium = new MobiliumPowerConfig();
    public final ProtisiumPowerConfig protisium = new ProtisiumPowerConfig();
    public final SupporiumPowerConfig supportium = new SupporiumPowerConfig();

    public SopConfig() {
        super(new Identifier(SongsOfPower.MOD_ID, "sop_config"), "config.sop.config.server.title", "./config/sow/songs-of-power.json");
    }

    @Override
    protected boolean shouldCompressKey() {
        return false;
    }

    public static class AggressiumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Integer> aggrospherePrimaryCooldown = new IntegerEntry("config.sop.power.aggrosphere.cooldown.primary", 10, 0, Integer.MAX_VALUE).json("aggrosphere.cooldown.primary");
        public final IConfigEntry<Integer> aggrosphereSecondaryCooldown = new IntegerEntry("config.sop.power.aggrosphere.cooldown.secondary", 10, 0, Integer.MAX_VALUE).json("aggrosphere.cooldown.secondary");
        public final IConfigEntry<Double> aggrosphereExhaustion = new DoubleEntry("config.sop.power.aggrosphere.exhaustion", 1, 0, Integer.MAX_VALUE).json("aggrosphere.exhaustion");
        public final IConfigEntry<Double> aggrosphereSpeed = new DoubleEntry("config.sop.power.aggrosphere.speed", 3, 0, Integer.MAX_VALUE).json("aggrosphere.speed");
        public final IConfigEntry<Double> aggrosphereDamage = new DoubleEntry("config.sop.power.aggrosphere.damage", 5, 0, Integer.MAX_VALUE).json("aggrosphere.damage");
        public final IConfigEntry<Integer> aggroquakePrimaryCooldown = new IntegerEntry("config.sop.power.aggroquake.cooldown.primary", 300, 0, Integer.MAX_VALUE).json("aggroquake.cooldown.primary");
        public final IConfigEntry<Integer> aggroquakeSecondaryCooldown = new IntegerEntry("config.sop.power.aggroquake.cooldown.secondary", 200, 0, Integer.MAX_VALUE).json("aggroquake.cooldown.secondary");
        public final IConfigEntry<Double> aggroquakeExhaustion = new DoubleEntry("config.sop.power.aggroquake.exhaustion", 4, 0, Integer.MAX_VALUE).json("aggroquake.exhaustion");
        public final IConfigEntry<Double> aggroquakeRange = new DoubleEntry("config.sop.power.aggroquake.range", 10, 0, Integer.MAX_VALUE).json("aggroquake.range");
        public final IConfigEntry<Double> aggroquakeDamage = new DoubleEntry("config.sop.power.aggroquake.damage", 5, 0, Integer.MAX_VALUE).json("aggroquake.damage");
        public final IConfigEntry<Integer> aggroshockPrimaryCooldown = new IntegerEntry("config.sop.power.aggroshock.cooldown.primary", 300, 0, Integer.MAX_VALUE).json("aggroshock.cooldown.primary");
        public final IConfigEntry<Integer> aggroshockSecondaryCooldown = new IntegerEntry("config.sop.power.aggroshock.cooldown.secondary", 300, 0, Integer.MAX_VALUE).json("aggroshock.cooldown.secondary");
        public final IConfigEntry<Double> aggroshockExhaustion = new DoubleEntry("config.sop.power.aggroshock.exhaustion", 4, 0, Integer.MAX_VALUE).json("aggroshock.exhaustion");
        public final IConfigEntry<Integer> aggroshockDistance = new IntegerEntry("config.sop.power.aggroshock.distance", 8, 0, Integer.MAX_VALUE).json("aggroshock.distance");
        public final IConfigEntry<Double> aggrostormExhaustion = new DoubleEntry("config.sop.power.aggrostorm.exhaustion", 4, 0, Integer.MAX_VALUE).json("aggrostorm.exhaustion");
        public final IConfigEntry<Double> aggrostormStrength = new DoubleEntry("config.sop.power.aggrostorm.strength", 0.02, 0, Integer.MAX_VALUE).json("aggrostorm.strength");
        public final IConfigEntry<Double> aggrostormRange = new DoubleEntry("config.sop.power.aggrostorm.range", 15, 0, Integer.MAX_VALUE).json("aggrostorm.range");
        public final IConfigEntry<Double> aggrostormDamage = new DoubleEntry("config.sop.power.aggrostorm.damage", 1, 0, Integer.MAX_VALUE).json("aggrostorm.damage");

        public AggressiumPowerConfig() {
            super("aggressium", "config.sop.category.power.aggressium");
        }
    }

    public static class MobiliumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Integer> mobiliflashPrimaryCooldown = new IntegerEntry("config.sop.power.mobiliflash.cooldown.primary", 40, 0, Integer.MAX_VALUE).json("mobiliflash.cooldown.primary");
        public final IConfigEntry<Integer> mobiliflashSecondaryCooldown = new IntegerEntry("config.sop.power.mobiliflash.cooldown.secondary", 40, 0, Integer.MAX_VALUE).json("mobiliflash.cooldown.secondary");
        public final IConfigEntry<Double> mobiliflashExhaustion = new DoubleEntry("config.sop.power.mobiliflash.exhaustion", 2, 0, Integer.MAX_VALUE).json("mobiliflash.exhaustion");
        public final IConfigEntry<Double> mobiliflashSpeed = new DoubleEntry("config.sop.power.mobiliflash.speed", 8, 0, 50).json("mobiliflash.speed");
        public final IConfigEntry<Double> mobiliwingsExhaustion = new DoubleEntry("config.sop.power.mobiliwings.exhaustion", 1.0 / 10, 0, Integer.MAX_VALUE).json("mobiliwings.exhaustion");
        public final IConfigEntry<Double> mobiliglideExhaustion = new DoubleEntry("config.sop.power.mobiliglide.exhaustion", 2.0 / 10, 0, Integer.MAX_VALUE).json("mobiliglide.exhaustion");
        public final IConfigEntry<Double> mobilibounceExhaustion = new DoubleEntry("config.sop.power.mobilibounce.exhaustion", 0.5, 0, Integer.MAX_VALUE).json("mobilibounce.exhaustion");
        public final IConfigEntry<Integer> mobilibounceExistTime = new IntegerEntry("config.sop.power.mobilibounce.exist_time", 5, 0, Integer.MAX_VALUE).json("mobilibounce.exist_time");

        public MobiliumPowerConfig() {
            super("mobilium", "config.sop.category.power.mobilium");
        }
    }

    public static class ProtisiumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Double> protesphereExhaustion = new DoubleEntry("config.sop.power.protesphere.exhaustion", 2.0 / 10, 0, Integer.MAX_VALUE).json("protesphere.exhaustion");
        public final IConfigEntry<Double> protepointExhaustion = new DoubleEntry("config.sop.power.protepoint.exhaustion", 1.0 / 10, 0, Integer.MAX_VALUE).json("protepoint.exhaustion");
        public final IConfigEntry<Integer> protehealPrimaryCooldown = new IntegerEntry("config.sop.power.proteheal.cooldown.primary", 100, 0, Integer.MAX_VALUE).json("proteheal.cooldown.primary");
        public final IConfigEntry<Integer> protehealSecondaryCooldown = new IntegerEntry("config.sop.power.proteheal.cooldown.secondary", 200, 0, Integer.MAX_VALUE).json("proteheal.cooldown.secondary");
        public final IConfigEntry<Double> protehealExhaustion = new DoubleEntry("config.sop.power.proteheal.exhaustion", 2, 0, Integer.MAX_VALUE).json("proteheal.exhaustion");
        public final IConfigEntry<Integer> protearmorPrimaryCooldown = new IntegerEntry("config.sop.power.protearmor.cooldown.primary", 40, 0, Integer.MAX_VALUE).json("protearmor.cooldown.primary");
        public final IConfigEntry<Integer> protearmorSecondaryCooldown = new IntegerEntry("config.sop.power.protearmor.cooldown.secondary", 80, 0, Integer.MAX_VALUE).json("protearmor.cooldown.secondary");
        public final IConfigEntry<Double> protearmorExhaustion = new DoubleEntry("config.sop.power.protearmor.exhaustion", 2.0 / 10, 0, Integer.MAX_VALUE).json("protearmor.exhaustion");
        public final IConfigEntry<Double> protearmorMaxReduceDamage = new DoubleEntry("config.sop.power.protearmor.maxReduceDamage", 20, 0, Integer.MAX_VALUE).json("protearmor.maxReduceDamage");

        public ProtisiumPowerConfig() {
            super("protisium", "config.sop.category.power.protisium");
        }
    }

    public static class SupporiumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Integer> supporoliftPrimaryCooldown = new IntegerEntry("config.sop.power.supporolift.cooldown.primary", 100, 0, Integer.MAX_VALUE).json("supporolift.cooldown.primary");
        public final IConfigEntry<Integer> supporoliftSecondaryCooldown = new IntegerEntry("config.sop.power.supporolift.cooldown.secondary", 200, 0, Integer.MAX_VALUE).json("supporolift.cooldown.secondary");
        public final IConfigEntry<Double> supporoliftExhaustion = new DoubleEntry("config.sop.power.supporolift.exhaustion", 2, 0, Integer.MAX_VALUE).json("supporolift.exhaustion");
        public final IConfigEntry<Double> supporoliftRange = new DoubleEntry("config.sop.power.supporolift.range", 20, 0, 16 * 16).json("supporolift.range");

        public SupporiumPowerConfig() {
            super("supportium", "config.sop.category.power.supportium");
        }
    }
}
