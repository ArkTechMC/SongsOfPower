package com.iafenvoy.sop.impl.fabric;

import com.iafenvoy.sop.fabric.component.SongPowerComponent;
import com.iafenvoy.sop.power.SongPowerData;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

public class ComponentManagerImpl {
    public static SongPowerData getSongPowerData(PlayerEntity player) {
        return SongPowerComponent.SONG_POWER_COMPONENT.get(player).getData();
    }
}
