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

package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.BlockSettings.ModBlockSettings;
import com.shortax.short_utils.blocks.blockEntities.Projector.projector_block;
import com.shortax.short_utils.blocks.blockEntities.mixedBlocks.mixed_block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;

import java.util.ArrayList;
import java.util.List;

public class ModBlocksWithEntities {

    public static mixed_block MIXED_BLOCK;
    public static projector_block PROJECTOR_BLOCK;
    public static String PROJECTOR_BLOCK_ID = "projector_block";

    public static void init()
    {
        List<ItemConvertible> buildingBlockEntities = new ArrayList<>();
        List<ItemConvertible> functionalBlockEntities = new ArrayList<>();


        String MIXED_BLOCK_ID = "mixed_block";
        MIXED_BLOCK = (mixed_block) Utils.registerBlock_C(MIXED_BLOCK_ID, mixed_block::new, mixed_block.DEFAULT_SETTINGS);
        buildingBlockEntities.add(MIXED_BLOCK);

        PROJECTOR_BLOCK = (projector_block) Utils.registerBlock_C(PROJECTOR_BLOCK_ID, projector_block::new, ModBlockSettings.PROJECTOR_BLOCK);
        functionalBlockEntities.add(PROJECTOR_BLOCK);


        Utils.registering("Block Entities",ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS,buildingBlockEntities);
        Utils.registerItemGroupEntry(ItemGroups.FUNCTIONAL,functionalBlockEntities);

        //Blocks.SMITHING_TABLE
        //Blocks.CRAFTING_TABLE
    }
}
