package com.shortax.short_utils.blockentities.mixedBlocks;

import com.shortax.short_utils.Initializers.ModBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class mixed_block_entity extends BlockEntity {

    public mixed_block_entity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.MIXED_BLOCK_TYPE, pos, state);
    }
}
