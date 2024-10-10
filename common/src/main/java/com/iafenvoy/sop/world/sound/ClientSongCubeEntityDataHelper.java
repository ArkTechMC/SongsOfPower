package com.iafenvoy.sop.world.sound;

import com.iafenvoy.neptune.network.PacketBufferUtils;
import com.iafenvoy.sop.Static;
import com.iafenvoy.sop.item.block.entity.AbstractSongCubeBlockEntity;
import com.iafenvoy.sop.power.type.AbstractSongPower;
import com.iafenvoy.sop.power.type.DummySongPower;
import dev.architectury.networking.NetworkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class ClientSongCubeEntityDataHelper {
    private static final Set<BlockPos> REQUESTING = new HashSet<>();

    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, Static.SONG_CUBE_DATA_SYNC, (buf, context) -> {
            BlockPos pos = buf.readBlockPos();
            AbstractSongPower<?> power = AbstractSongPower.BY_ID.getOrDefault(buf.readString(), DummySongPower.EMPTY);
            if (power.isEmpty()) return;//Do not sync empty one again
            REQUESTING.remove(pos);
            World world = context.getPlayer().getEntityWorld();
            context.queue(() -> {
                if (world != null && world.getBlockEntity(pos) instanceof AbstractSongCubeBlockEntity blockEntity)
                    blockEntity.setPower(power);
            });
        });
    }

    public static void request(BlockPos pos) {
        if (REQUESTING.contains(pos)) return;
        REQUESTING.add(pos);
        NetworkManager.sendToServer(Static.SONG_CUBE_DATA_SYNC, PacketBufferUtils.create().writeBlockPos(pos));
    }
}
