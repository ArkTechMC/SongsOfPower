package com.iafenvoy.sop.impl.forge;

import com.iafenvoy.sop.forge.component.SongPowerDataProvider;
import com.iafenvoy.sop.forge.component.SongPowerDataStorage;
import com.iafenvoy.sop.power.SongPowerData;
import net.minecraft.entity.player.PlayerEntity;

public class ComponentManagerImpl {
    public static SongPowerData getSongPowerData(PlayerEntity player) {
        return player.getCapability(SongPowerDataProvider.CAPABILITY).resolve().map(SongPowerDataStorage::getData).orElse(null);
    }
}
