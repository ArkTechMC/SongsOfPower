package com.iafenvoy.sop.item.block;

import com.iafenvoy.sop.item.block.entity.AbstractSongCubeBlockEntity;
import com.iafenvoy.sop.item.block.entity.MobiliumSongCubeBlockEntity;
import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.registry.SopBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MobiliumSongCubeBlock extends AbstractSongCubeBlock {
    public MobiliumSongCubeBlock() {
        super(PowerCategory.MOBILIUM);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MobiliumSongCubeBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SopBlockEntities.MOBILIUM_SONG_TYPE.get(), AbstractSongCubeBlockEntity::tick);
    }
}
