package com.iafenvoy.sop.forge;

import com.iafenvoy.neptune.forge.component.CapabilitySyncHelper;
import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.SongsOfPowerClient;
import com.iafenvoy.sop.forge.component.SongPowerDataProvider;
import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SongsOfPower.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SongsOfPowerForge {
    public SongsOfPowerForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(SongsOfPower.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        SongsOfPower.init();
        CapabilitySyncHelper.register(new Identifier(SongsOfPower.MOD_ID, "song_power"), SongPowerDataProvider.CAPABILITY, SongPowerDataProvider::new);
        if (Platform.getEnv() == Dist.CLIENT)
            SongsOfPowerClient.init();
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(SongsOfPower::process);
    }
}
