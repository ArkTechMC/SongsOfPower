package com.iafenvoy.sop.item.block;

import com.iafenvoy.sop.item.block.entity.AbstractSongCubeBlockEntity;
import com.iafenvoy.sop.item.block.entity.ProtisiumSongCubeBlockEntity;
import com.iafenvoy.sop.power.PowerCategory;
import com.iafenvoy.sop.registry.SopBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProtisiumSongCubeBlock extends AbstractSongCubeBlock {
    public ProtisiumSongCubeBlock() {
        super(PowerCategory.PROTISIUM);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProtisiumSongCubeBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SopBlockEntities.PROTISIUM_SONG_TYPE.get(), AbstractSongCubeBlockEntity::tick);
    }
}
