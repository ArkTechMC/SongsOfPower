package com.iafenvoy.sop.item.block.entity;

import com.iafenvoy.sop.registry.SopBlockEntities;
import com.iafenvoy.sop.registry.SopBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class SupportiumSongCubeBlockEntity extends AbstractSongCubeBlockEntity {
    public SupportiumSongCubeBlockEntity(BlockPos pos, BlockState state) {
        super(SopBlockEntities.SUPPORTIUM_SONG_TYPE.get(), pos, state);
    }
}
