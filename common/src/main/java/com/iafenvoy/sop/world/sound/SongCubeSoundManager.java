package com.iafenvoy.sop.world.sound;

import com.iafenvoy.sop.power.PowerCategory;
import net.minecraft.util.math.BlockPos;

public interface SongCubeSoundManager {
    boolean nearEnough(BlockPos pos);

    void startPlaying(BlockPos pos, PowerCategory category);

    void stopPlaying(BlockPos pos);

    void destroy(BlockPos pos);
}
