package com.iafenvoy.sop.world.sound;

import com.iafenvoy.sop.power.PowerCategory;
import net.minecraft.util.math.BlockPos;

public class ServerSongCubeSoundManager implements SongCubeSoundManager {
    @Override
    public boolean nearEnough(BlockPos pos) {
        return false;
    }

    @Override
    public void startPlaying(BlockPos pos, PowerCategory category) {
    }

    @Override
    public void stopPlaying(BlockPos pos) {
    }

    @Override
    public void destroy(BlockPos pos) {
    }
}
