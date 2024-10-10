package com.iafenvoy.sop.forge;

import com.iafenvoy.sop.SongsOfPowerClient;
import com.iafenvoy.sop.Static;
import com.iafenvoy.sop.forge.component.SongPowerDataProvider;
import com.iafenvoy.sop.screen.PowerHudRenderer;
import dev.architectury.networking.NetworkManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SongsOfPowerForgeClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(SongsOfPowerClient::process);
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, Static.CAPABILITY_SYNC, (buf, context) -> {
            NbtCompound compound = buf.readNbt();
            context.queue(() -> {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null)
                    player.getCapability(SongPowerDataProvider.CAPABILITY).resolve().ifPresent(x -> x.deserializeNBT(compound));
            });
        });
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void renderPowerHud(RenderGuiEvent.Post event) {
            PowerHudRenderer.render(MinecraftClient.getInstance(), event.getGuiGraphics());
        }
    }
}
