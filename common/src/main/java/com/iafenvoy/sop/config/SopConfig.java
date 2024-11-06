package com.iafenvoy.sop.config;

import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer;
import com.iafenvoy.jupiter.config.entry.BooleanEntry;
import com.iafenvoy.jupiter.config.entry.DoubleEntry;
import com.iafenvoy.jupiter.config.entry.IntegerEntry;
import com.iafenvoy.jupiter.config.entry.SeparatorEntry;
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

    @SuppressWarnings("unused")
    public static class AggressiumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Integer> aggrospherePrimaryCooldown = new IntegerEntry("config.sop.power.aggrosphere.cooldown.primary", 10, 0, Integer.MAX_VALUE).json("aggrosphere.cooldown.primary");
        public final IConfigEntry<Integer> aggrosphereSecondaryCooldown = new IntegerEntry("config.sop.power.aggrosphere.cooldown.secondary", 10, 0, Integer.MAX_VALUE).json("aggrosphere.cooldown.secondary");
        public final IConfigEntry<Double> aggrosphereExhaustion = new DoubleEntry("config.sop.power.aggrosphere.exhaustion", 1, 0, Integer.MAX_VALUE).json("aggrosphere.exhaustion");
        public final IConfigEntry<Double> aggrosphereSpeed = new DoubleEntry("config.sop.power.aggrosphere.speed", 3, 0, Integer.MAX_VALUE).json("aggrosphere.speed");
        public final IConfigEntry<Double> aggrosphereDamage = new DoubleEntry("config.sop.power.aggrosphere.damage", 5, 0, Integer.MAX_VALUE).json("aggrosphere.damage");
        public final SeparatorEntry s1 = new SeparatorEntry();
        public final IConfigEntry<Integer> aggroquakePrimaryCooldown = new IntegerEntry("config.sop.power.aggroquake.cooldown.primary", 300, 0, Integer.MAX_VALUE).json("aggroquake.cooldown.primary");
        public final IConfigEntry<Integer> aggroquakeSecondaryCooldown = new IntegerEntry("config.sop.power.aggroquake.cooldown.secondary", 200, 0, Integer.MAX_VALUE).json("aggroquake.cooldown.secondary");
        public final IConfigEntry<Double> aggroquakeExhaustion = new DoubleEntry("config.sop.power.aggroquake.exhaustion", 4, 0, Integer.MAX_VALUE).json("aggroquake.exhaustion");
        public final IConfigEntry<Double> aggroquakeRange = new DoubleEntry("config.sop.power.aggroquake.range", 10, 0, Integer.MAX_VALUE).json("aggroquake.range");
        public final IConfigEntry<Double> aggroquakeDamage = new DoubleEntry("config.sop.power.aggroquake.damage", 5, 0, Integer.MAX_VALUE).json("aggroquake.damage");
        public final SeparatorEntry s2 = new SeparatorEntry();
        public final IConfigEntry<Integer> aggroshockPrimaryCooldown = new IntegerEntry("config.sop.power.aggroshock.cooldown.primary", 300, 0, Integer.MAX_VALUE).json("aggroshock.cooldown.primary");
        public final IConfigEntry<Integer> aggroshockSecondaryCooldown = new IntegerEntry("config.sop.power.aggroshock.cooldown.secondary", 300, 0, Integer.MAX_VALUE).json("aggroshock.cooldown.secondary");
        public final IConfigEntry<Double> aggroshockExhaustion = new DoubleEntry("config.sop.power.aggroshock.exhaustion", 4, 0, Integer.MAX_VALUE).json("aggroshock.exhaustion");
        public final IConfigEntry<Integer> aggroshockDistance = new IntegerEntry("config.sop.power.aggroshock.distance", 8, 0, Integer.MAX_VALUE).json("aggroshock.distance");
        public final SeparatorEntry s3 = new SeparatorEntry();
        public final IConfigEntry<Double> aggrostormExhaustion = new DoubleEntry("config.sop.power.aggrostorm.exhaustion", 4, 0, Integer.MAX_VALUE).json("aggrostorm.exhaustion");
        public final IConfigEntry<Double> aggrostormStrength = new DoubleEntry("config.sop.power.aggrostorm.strength", 0.02, 0, Integer.MAX_VALUE).json("aggrostorm.strength");
        public final IConfigEntry<Double> aggrostormRange = new DoubleEntry("config.sop.power.aggrostorm.range", 15, 0, Integer.MAX_VALUE).json("aggrostorm.range");
        public final IConfigEntry<Double> aggrostormDamage = new DoubleEntry("config.sop.power.aggrostorm.damage", 1, 0, Integer.MAX_VALUE).json("aggrostorm.damage");
        public final SeparatorEntry s4 = new SeparatorEntry();
        public final IConfigEntry<Integer> aggrodetonatePrimaryCooldown = new IntegerEntry("config.sop.power.aggrodetonate.cooldown.primary", 30, 0, Integer.MAX_VALUE).json("aggrodetonate.cooldown.primary");
        public final IConfigEntry<Integer> aggrodetonateSecondaryCooldown = new IntegerEntry("config.sop.power.aggrodetonate.cooldown.secondary", 50, 0, Integer.MAX_VALUE).json("aggrodetonate.cooldown.secondary");
        public final IConfigEntry<Double> aggrodetonateExhaustion = new DoubleEntry("config.sop.power.aggrodetonate.exhaustion", 2, 0, Integer.MAX_VALUE).json("aggrodetonate.exhaustion");
        public final IConfigEntry<Double> aggrodetonatePower = new DoubleEntry("config.sop.power.aggrodetonate.power", 1, 0, Integer.MAX_VALUE).json("aggrodetonate.power");
        public final IConfigEntry<Double> aggrodetonateSpeed = new DoubleEntry("config.sop.power.aggrodetonate.speed", 2, 0, Integer.MAX_VALUE).json("aggrodetonate.speed");
        public final SeparatorEntry s5 = new SeparatorEntry();
        public final IConfigEntry<Integer> aggroshardPrimaryCooldown = new IntegerEntry("config.sop.power.aggroshard.cooldown.primary", 60, 0, Integer.MAX_VALUE).json("aggroshard.cooldown.primary");
        public final IConfigEntry<Integer> aggroshardSecondaryCooldown = new IntegerEntry("config.sop.power.aggroshard.cooldown.secondary", 100, 0, Integer.MAX_VALUE).json("aggroshard.cooldown.secondary");
        public final IConfigEntry<Double> aggroshardExhaustion = new DoubleEntry("config.sop.power.aggroshard.exhaustion", 5, 0, Integer.MAX_VALUE).json("aggroshard.exhaustion");
        public final IConfigEntry<Double> aggroshardDamage = new DoubleEntry("config.sop.power.aggroshard.damage", 1, 0, Integer.MAX_VALUE).json("aggroshard.damage");
        public final IConfigEntry<Double> aggroshardSpeed = new DoubleEntry("config.sop.power.aggroshard.speed", 1.5, 0, Integer.MAX_VALUE).json("aggroshard.speed");
        public final IConfigEntry<Integer> aggroshardCount = new IntegerEntry("config.sop.power.aggroshard.count", 20, 0, Integer.MAX_VALUE).json("aggroshard.count");

        public AggressiumPowerConfig() {
            super("aggressium", "config.sop.category.power.aggressium");
        }
    }

    @SuppressWarnings("unused")
    public static class MobiliumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Integer> mobiliflashPrimaryCooldown = new IntegerEntry("config.sop.power.mobiliflash.cooldown.primary", 40, 0, Integer.MAX_VALUE).json("mobiliflash.cooldown.primary");
        public final IConfigEntry<Integer> mobiliflashSecondaryCooldown = new IntegerEntry("config.sop.power.mobiliflash.cooldown.secondary", 40, 0, Integer.MAX_VALUE).json("mobiliflash.cooldown.secondary");
        public final IConfigEntry<Double> mobiliflashExhaustion = new DoubleEntry("config.sop.power.mobiliflash.exhaustion", 2, 0, Integer.MAX_VALUE).json("mobiliflash.exhaustion");
        public final IConfigEntry<Double> mobiliflashSpeed = new DoubleEntry("config.sop.power.mobiliflash.speed", 8, 0, 50).json("mobiliflash.speed");
        public final SeparatorEntry s1 = new SeparatorEntry();
        public final IConfigEntry<Double> mobiliwingsExhaustion = new DoubleEntry("config.sop.power.mobiliwings.exhaustion", 1.0 / 10, 0, Integer.MAX_VALUE).json("mobiliwings.exhaustion");
        public final SeparatorEntry s2 = new SeparatorEntry();
        public final IConfigEntry<Double> mobiliglideExhaustion = new DoubleEntry("config.sop.power.mobiliglide.exhaustion", 2.0 / 10, 0, Integer.MAX_VALUE).json("mobiliglide.exhaustion");
        public final SeparatorEntry s3 = new SeparatorEntry();
        public final IConfigEntry<Integer> mobilibouncePrimaryCooldown = new IntegerEntry("config.sop.power.mobilibounce.cooldown.primary", 50, 0, Integer.MAX_VALUE).json("mobilibounce.cooldown.primary");
        public final IConfigEntry<Integer> mobilibounceSecondaryCooldown = new IntegerEntry("config.sop.power.mobilibounce.cooldown.secondary", 50, 0, Integer.MAX_VALUE).json("mobilibounce.cooldown.secondary");
        public final IConfigEntry<Double> mobilibounceExhaustion = new DoubleEntry("config.sop.power.mobilibounce.exhaustion", 0.5, 0, Integer.MAX_VALUE).json("mobilibounce.exhaustion");
        public final IConfigEntry<Integer> mobilibounceExistTime = new IntegerEntry("config.sop.power.mobilibounce.exist_time", 5, 0, Integer.MAX_VALUE).json("mobilibounce.exist_time");
        public final SeparatorEntry s4 = new SeparatorEntry();
        public final IConfigEntry<Integer> mobiliburstPrimaryCooldown = new IntegerEntry("config.sop.power.mobiliburst.cooldown.primary", 40, 0, Integer.MAX_VALUE).json("mobiliburst.cooldown.primary");
        public final IConfigEntry<Integer> mobiliburstSecondaryCooldown = new IntegerEntry("config.sop.power.mobiliburst.cooldown.secondary", 80, 0, Integer.MAX_VALUE).json("mobiliburst.cooldown.secondary");
        public final IConfigEntry<Double> mobiliburstExhaustion = new DoubleEntry("config.sop.power.mobiliburst.exhaustion", 4, 0, Integer.MAX_VALUE).json("mobiliburst.exhaustion");
        public final IConfigEntry<Double> mobiliburstSpeed = new DoubleEntry("config.sop.power.mobiliburst.speed", 8, 0, 50).json("mobiliburst.speed");

        public MobiliumPowerConfig() {
            super("mobilium", "config.sop.category.power.mobilium");
        }
    }

    @SuppressWarnings("unused")
    public static class ProtisiumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Double> protesphereExhaustion = new DoubleEntry("config.sop.power.protesphere.exhaustion", 2.0 / 10, 0, Integer.MAX_VALUE).json("protesphere.exhaustion");
        public final SeparatorEntry s1 = new SeparatorEntry();
        public final IConfigEntry<Double> protepointExhaustion = new DoubleEntry("config.sop.power.protepoint.exhaustion", 1.0 / 10, 0, Integer.MAX_VALUE).json("protepoint.exhaustion");
        public final SeparatorEntry s2 = new SeparatorEntry();
        public final IConfigEntry<Integer> protehealPrimaryCooldown = new IntegerEntry("config.sop.power.proteheal.cooldown.primary", 100, 0, Integer.MAX_VALUE).json("proteheal.cooldown.primary");
        public final IConfigEntry<Integer> protehealSecondaryCooldown = new IntegerEntry("config.sop.power.proteheal.cooldown.secondary", 200, 0, Integer.MAX_VALUE).json("proteheal.cooldown.secondary");
        public final IConfigEntry<Double> protehealExhaustion = new DoubleEntry("config.sop.power.proteheal.exhaustion", 2, 0, Integer.MAX_VALUE).json("proteheal.exhaustion");
        public final SeparatorEntry s3 = new SeparatorEntry();
        public final IConfigEntry<Integer> protearmorPrimaryCooldown = new IntegerEntry("config.sop.power.protearmor.cooldown.primary", 40, 0, Integer.MAX_VALUE).json("protearmor.cooldown.primary");
        public final IConfigEntry<Integer> protearmorSecondaryCooldown = new IntegerEntry("config.sop.power.protearmor.cooldown.secondary", 80, 0, Integer.MAX_VALUE).json("protearmor.cooldown.secondary");
        public final IConfigEntry<Double> protearmorExhaustion = new DoubleEntry("config.sop.power.protearmor.exhaustion", 2.0 / 10, 0, Integer.MAX_VALUE).json("protearmor.exhaustion");
        public final IConfigEntry<Double> protearmorMaxReduceDamage = new DoubleEntry("config.sop.power.protearmor.maxReduceDamage", 20, 0, Integer.MAX_VALUE).json("protearmor.maxReduceDamage");

        public ProtisiumPowerConfig() {
            super("protisium", "config.sop.category.power.protisium");
        }
    }

    @SuppressWarnings("unused")
    public static class SupporiumPowerConfig extends AutoInitConfigCategoryBase {
        public final IConfigEntry<Integer> supporoliftPrimaryCooldown = new IntegerEntry("config.sop.power.supporolift.cooldown.primary", 100, 0, Integer.MAX_VALUE).json("supporolift.cooldown.primary");
        public final IConfigEntry<Integer> supporoliftSecondaryCooldown = new IntegerEntry("config.sop.power.supporolift.cooldown.secondary", 200, 0, Integer.MAX_VALUE).json("supporolift.cooldown.secondary");
        public final IConfigEntry<Double> supporoliftExhaustion = new DoubleEntry("config.sop.power.supporolift.exhaustion", 2, 0, Integer.MAX_VALUE).json("supporolift.exhaustion");
        public final IConfigEntry<Double> supporoliftRange = new DoubleEntry("config.sop.power.supporolift.range", 20, 0, 16 * 16).json("supporolift.range");
        public final SeparatorEntry s1 = new SeparatorEntry();
        public final IConfigEntry<Integer> supporekesisPrimaryCooldown = new IntegerEntry("config.sop.power.supporekesis.cooldown.primary", 50, 0, Integer.MAX_VALUE).json("supporekesis.cooldown.primary");
        public final IConfigEntry<Integer> supporekesisSecondaryCooldown = new IntegerEntry("config.sop.power.supporekesis.cooldown.secondary", 150, 0, Integer.MAX_VALUE).json("supporekesis.cooldown.secondary");
        public final IConfigEntry<Double> supporekesisExhaustion = new DoubleEntry("config.sop.power.supporekesis.exhaustion", 2, 0, Integer.MAX_VALUE).json("supporekesis.exhaustion");
        public final IConfigEntry<Double> supporekesisRange = new DoubleEntry("config.sop.power.supporekesis.range", 20, 0, 16 * 16).json("supporekesis.range");
        public final IConfigEntry<Boolean> supporekesisControlSelf = new BooleanEntry("config.sop.power.supporekesis.control_self", false).json("supporekesis.control_self");

        public SupporiumPowerConfig() {
            super("supportium", "config.sop.category.power.supportium");
        }
    }
}
