package com.shortax.short_utils.blocks.FakeRedstoneBlocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;


public class FakeTrapdoor extends TrapdoorBlock {
    public Block ORIGINAL;

    public static class OAK_TYPE extends FakeTrapdoor{
        public static BlockSetType type = BlockSetType.OAK;
        private static final Block reference = Blocks.OAK_TRAPDOOR;
        public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(reference);

        public OAK_TYPE(Settings settings) {
            super(type, settings.nonOpaque());
            this.ORIGINAL = reference;
        }
    }

    public static class SPRUCE_TYPE extends FakeTrapdoor{
        public static BlockSetType type = BlockSetType.SPRUCE;
        private static final Block reference = Blocks.SPRUCE_TRAPDOOR;
        public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(reference);

        public SPRUCE_TYPE(Settings settings) {
            super(type, settings.nonOpaque());
            this.ORIGINAL = reference;
        }
    }

    public static class IRON_TYPE extends FakeTrapdoor{
        public static BlockSetType type = BlockSetType.IRON;
        private static final Block reference = Blocks.IRON_TRAPDOOR;
        public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(reference);

        public IRON_TYPE(Settings settings) {
            super(type, settings.nonOpaque());
            this.ORIGINAL = reference;
        }
    }

    public FakeTrapdoor(BlockSetType type, Settings settings) {
        super(type, settings);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!world.isClient) {
            if (state.get(WATERLOGGED)) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
        }
    }
}
