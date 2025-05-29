package com.shortax.short_utils.blocks.blockEntities.mixedBlocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class mixed_block extends BlockWithEntity {

    public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE);

    public mixed_block(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new mixed_block_entity(pos,state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    protected MapCodec<? extends mixed_block> getCodec() {
        return createCodec(mixed_block::new);
    }

}
