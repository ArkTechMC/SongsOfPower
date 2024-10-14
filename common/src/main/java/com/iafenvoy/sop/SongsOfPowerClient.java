package com.iafenvoy.sop;

import com.iafenvoy.sop.registry.SopKeybindings;
import com.iafenvoy.sop.registry.SopRenderers;
import com.iafenvoy.sop.world.sound.ClientSongCubeEntityDataHelper;
import com.iafenvoy.sop.world.sound.ClientSongCubeSoundManager;

public class SongsOfPowerClient {
    public static void init() {
        SopKeybindings.init();
        SopRenderers.registerEntityRenderer();
        SopRenderers.registerParticleRenderer();
    }

    public static void process() {
        SopRenderers.registerBlockEntityRenderer();
        SopRenderers.registerRenderType();
        ClientSongCubeEntityDataHelper.init();
        Static.songCubeSoundManager = new ClientSongCubeSoundManager();
    }
}
