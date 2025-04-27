package com.shortax.short_utils.blockentities.combinedBlocks;

import com.shortax.short_utils.blockentities.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class combined_block_entity extends BlockEntity {

    public combined_block_entity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.COMBINED_BLOCK_TYPE, pos, state);
    }
}
