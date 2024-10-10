package com.iafenvoy.sop.impl;

import com.iafenvoy.sop.power.SongPowerData;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.player.PlayerEntity;

public class ComponentManager {
    @ExpectPlatform
    public static SongPowerData getSongPowerData(PlayerEntity player) {
        throw new AssertionError("This method should be replaced by Architectury");
    }
}
