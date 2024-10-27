package com.iafenvoy.sop.fabric;

import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.power.SongPowerData;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class SongsOfPowerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SongsOfPower.init();
        SongsOfPower.process();
        ServerLifecycleEvents.SERVER_STOPPING.register(SongPowerData::stop);
    }
}
