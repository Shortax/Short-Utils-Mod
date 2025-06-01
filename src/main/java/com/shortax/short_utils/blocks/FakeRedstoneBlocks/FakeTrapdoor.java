/*
 *
 *  * This file is part of Short Utils mod
 *  *
 *  * Copyright (C) 2025 Shortax
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Lesser General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.shortax.short_utils.blocks.FakeRedstoneBlocks;

import com.shortax.short_utils.Initializers.Utils;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class FakeTrapdoor extends TrapdoorBlock {
    public Block ORIGINAL;

    private static final float strength = 0.1f;
    public static final Utils.custom_tooltip_bundle TOOLTIP_BUNDLE = new Utils.custom_tooltip_bundle("")
            .addTag("fake_redstone")
            .addFormatting(Formatting.GRAY);

    public static class OAK_TYPE extends FakeTrapdoor{
        public static BlockSetType type = BlockSetType.OAK;
        private static final Block reference = Blocks.OAK_TRAPDOOR;
        public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(reference);

        public OAK_TYPE(Settings settings) {
            super(type, settings);
            this.ORIGINAL = reference;
        }
    }

    public static class SPRUCE_TYPE extends FakeTrapdoor{
        public static BlockSetType type = BlockSetType.SPRUCE;
        private static final Block reference = Blocks.SPRUCE_TRAPDOOR;
        public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(reference);

        public SPRUCE_TYPE(Settings settings) {
            super(type, settings);
            this.ORIGINAL = reference;
        }
    }

    public static class IRON_TYPE extends FakeTrapdoor{
        public static BlockSetType type = BlockSetType.IRON;
        private static final Block reference = Blocks.IRON_TRAPDOOR;
        public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.copy(reference);

        public IRON_TYPE(Settings settings) {
            super(type, settings);
            this.ORIGINAL = reference;
        }
    }

    public FakeTrapdoor(BlockSetType type, Settings settings) {
        super(type, settings.nonOpaque().strength(strength));
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
