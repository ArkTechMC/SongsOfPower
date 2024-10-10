package com.iafenvoy.sop.forge;

import com.iafenvoy.neptune.network.PacketBufferUtils;
import com.iafenvoy.sop.SongsOfPower;
import com.iafenvoy.sop.SongsOfPowerClient;
import com.iafenvoy.sop.Static;
import com.iafenvoy.sop.forge.component.SongPowerDataProvider;
import com.iafenvoy.sop.forge.component.SongPowerDataStorage;
import com.iafenvoy.sop.power.SongPowerData;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Optional;

@Mod(SongsOfPower.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SongsOfPowerForge {
    public SongsOfPowerForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(SongsOfPower.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        SongsOfPower.init();
        if (Platform.getEnv() == Dist.CLIENT)
            SongsOfPowerClient.init();
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(SongsOfPower::process);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity player) {
                boolean isServerNotFake = player instanceof ServerPlayerEntity && !(player instanceof FakePlayer);
                if ((isServerNotFake || player instanceof AbstractClientPlayerEntity) && !player.getCapability(SongPowerDataProvider.CAPABILITY).isPresent())
                    event.addCapability(new Identifier(SongsOfPower.MOD_ID, "song_power"), new SongPowerDataProvider(player));
            }
        }

        @SubscribeEvent
        public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayerEntity serverPlayer) {
                Optional<SongPowerDataStorage> optional = serverPlayer.getCapability(SongPowerDataProvider.CAPABILITY).resolve();
                optional.ifPresent(storage -> NetworkManager.sendToPlayer(serverPlayer, Static.CAPABILITY_SYNC, PacketBufferUtils.create().writeNbt(storage.getData().encode())));
            }
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            PlayerEntity player = event.player;
            Optional<SongPowerDataStorage> optional = player.getCapability(SongPowerDataProvider.CAPABILITY).resolve();
            if (optional.isEmpty()) return;
            SongPowerData data = optional.get().getData();
            data.tick();
            if (data.isDirty() && player instanceof ServerPlayerEntity serverPlayer)
                NetworkManager.sendToPlayer(serverPlayer, Static.CAPABILITY_SYNC, PacketBufferUtils.create().writeNbt(data.encode()));
        }
    }
}
