package com.iafenvoy.sop.registry;

import net.minecraft.world.GameRules;

public final class SopGameRules {
    public static final GameRules.Key<GameRules.BooleanRule> SHOW_PARTICLE = GameRules.register("sop:show_particle", GameRules.Category.PLAYER, GameRules.BooleanRule.create(true));

    public static void init() {
    }
}
