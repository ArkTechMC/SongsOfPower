package com.iafenvoy.sop.world.sound;

import com.iafenvoy.neptune.network.PacketBufferUtils;
import com.iafenvoy.sop.Static;
import com.iafenvoy.sop.item.block.entity.AbstractSongCubeBlockEntity;
import dev.architectury.networking.NetworkManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class ServerSongCubeEntityDataHelper {
    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, Static.SONG_CUBE_DATA_SYNC, (buf, context) -> {
            BlockPos pos = buf.readBlockPos();
            PlayerEntity player = context.getPlayer();
            context.queue(() -> {
                if (player instanceof ServerPlayerEntity serverPlayer && serverPlayer.getEntityWorld().getBlockEntity(pos) instanceof AbstractSongCubeBlockEntity blockEntity)
                    NetworkManager.sendToPlayer(serverPlayer, Static.SONG_CUBE_DATA_SYNC, PacketBufferUtils.create().writeBlockPos(pos).writeString(blockEntity.getPower().getId()));
            });
        });
    }
}
