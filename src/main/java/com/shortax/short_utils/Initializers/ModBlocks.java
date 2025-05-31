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
import com.shortax.short_utils.blocks.Crafters.Combiner.combiner_block;
import com.shortax.short_utils.blocks.FakeRedstoneBlocks.FakeTrapdoor;
import com.shortax.short_utils.blocks.blockEntities.Projector.gen_projected_block;
import com.shortax.short_utils.blocks.blockEntities.Projector.projector_block;
import com.shortax.short_utils.blocks.blockEntities.mixedBlocks.mixed_block;
import com.shortax.short_utils.blocks.stairs.LeafStair;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import com.shortax.short_utils.blocks.plates.Obsid_Plate;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ModBlocks {

    public static Block OBSID_PRESSURE_PLATE;
    public static String OBSID_PRESSURE_PLATE_ID = "obsid_pressure_plate";
    public static Map<String, Block> COLORED_REDSTONE_LAMPS;
    public static FakeTrapdoor FAKE_OAK_TRAPDOOR;
    public static String FAKE_OAK_TRAPDOOR_ID = "fake_oak_trapdoor";
    public static FakeTrapdoor FAKE_IRON_TRAPDOOR;
    public static String FAKE_IRON_TRAPDOOR_ID = "fake_iron_trapdoor";
    public static FakeTrapdoor FAKE_SPRUCE_TRAPDOOR;
    public static String FAKE_SPRUCE_TRAPDOOR_ID = "fake_spruce_trapdoor";
    public static List<LeafStair> LEAVES_STAIRS;
    public static combiner_block COMBINER_BLOCK;
    public static String COMBINER_BLOCK_ID = "combiner_block";
    public static String GEN_PROJECTED_BLOCK_ID = "projected_block";
    public static gen_projected_block GEN_PROJECTED_BLOCK;

    public static List<Block> BlocksWithCutout = new ArrayList<>();
    public static List<Block> BlocksWithTransparency = new ArrayList<>();
    public static Collection<ItemConvertible> blocksListCustomTab;

    public static void init()
    {
        List<ItemConvertible> blocksListRedstone = new ArrayList<>();
        List<ItemConvertible> blocksListColored = new ArrayList<>();
        List<ItemConvertible> blocksListBuilding = new ArrayList<>();
        List<ItemConvertible> blocksListFunctional = new ArrayList<>();

        blocksListCustomTab = new ArrayList<>();

        OBSID_PRESSURE_PLATE = Utils.registerBlock_C(OBSID_PRESSURE_PLATE_ID, Obsid_Plate::new,ModBlockSettings.OBSID_PLATE);
        blocksListRedstone.add(OBSID_PRESSURE_PLATE);

        COLORED_REDSTONE_LAMPS = colRedstoneLamp.get_Lamps(ShortUtils.MOD_ID);
        for(String col : COLORED_REDSTONE_LAMPS.keySet())
        {
            COLORED_REDSTONE_LAMPS.compute(col, (k, b) -> Utils.registerBlock(getLampID(col), b, getLampIdent(ShortUtils.MOD_ID,col)));
            blocksListColored.add(COLORED_REDSTONE_LAMPS.get(col));
        }

        FAKE_OAK_TRAPDOOR = (FakeTrapdoor) Utils.registerBlock_C(FAKE_OAK_TRAPDOOR_ID, FakeTrapdoor.OAK_TYPE::new,FakeTrapdoor.OAK_TYPE.DEFAULT_SETTINGS);
        blocksListBuilding.add(FAKE_OAK_TRAPDOOR);
        BlocksWithCutout.add(FAKE_OAK_TRAPDOOR);

        FAKE_SPRUCE_TRAPDOOR = (FakeTrapdoor) Utils.registerBlock_C(FAKE_SPRUCE_TRAPDOOR_ID, FakeTrapdoor.SPRUCE_TYPE::new,FakeTrapdoor.SPRUCE_TYPE.DEFAULT_SETTINGS);
        blocksListBuilding.add(FAKE_SPRUCE_TRAPDOOR);

        FAKE_IRON_TRAPDOOR = (FakeTrapdoor) Utils.registerBlock_C(FAKE_IRON_TRAPDOOR_ID, FakeTrapdoor.IRON_TYPE::new,FakeTrapdoor.IRON_TYPE.DEFAULT_SETTINGS);
        blocksListBuilding.add(FAKE_IRON_TRAPDOOR);
        BlocksWithCutout.add(FAKE_IRON_TRAPDOOR);

        LEAVES_STAIRS = new ArrayList<>();
        for(LeafStair ls : LeafStair.get_leaves_stairs())
        {
            LEAVES_STAIRS.add((LeafStair) Utils.registerBlock(ls.ID,ls,Identifier.of(ShortUtils.MOD_ID,ls.ID)));
        }
        BlocksWithCutout.addAll(LEAVES_STAIRS);
        blocksListBuilding.addAll(LEAVES_STAIRS);

        COMBINER_BLOCK = (combiner_block) Utils.registerBlock_C(COMBINER_BLOCK_ID,combiner_block::new, ModBlockSettings.COMBINER_BLOCK);
        blocksListFunctional.add(COMBINER_BLOCK);

        GEN_PROJECTED_BLOCK = (gen_projected_block) Utils.registerBlock_C(GEN_PROJECTED_BLOCK_ID,gen_projected_block::new,ModBlockSettings.GEN_PROJECTED_BLOCK);
        BlocksWithTransparency.add(GEN_PROJECTED_BLOCK);

        blocksListCustomTab.addAll(blocksListRedstone);
        blocksListCustomTab.addAll(blocksListColored);
        blocksListCustomTab.addAll(blocksListBuilding);
        blocksListCustomTab.addAll(blocksListFunctional);
        blocksListCustomTab.add(GEN_PROJECTED_BLOCK);


        Utils.Log_registering("Blocks",ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.REDSTONE,blocksListRedstone);
        Utils.registerItemGroupEntry(ItemGroups.COLORED_BLOCKS,blocksListColored);
        Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS,blocksListBuilding);
        Utils.registerItemGroupEntry(ItemGroups.FUNCTIONAL,blocksListFunctional);
    }

    public static class WithEntities{
        public static mixed_block MIXED_BLOCK;
        public static projector_block PROJECTOR_BLOCK;
        public static String PROJECTOR_BLOCK_ID = "projector_block";

        public static void init()
        {
            List<ItemConvertible> bEntityListBuilding = new ArrayList<>();
            List<ItemConvertible> bEntityListFunctional = new ArrayList<>();


            String MIXED_BLOCK_ID = "mixed_block";
            MIXED_BLOCK = (mixed_block) Utils.registerBlock_C(MIXED_BLOCK_ID, mixed_block::new, mixed_block.DEFAULT_SETTINGS);
            blocksListCustomTab.add(MIXED_BLOCK);

            PROJECTOR_BLOCK = (projector_block) Utils.registerBlock_C(PROJECTOR_BLOCK_ID, projector_block::new, ModBlockSettings.PROJECTOR_BLOCK);
            bEntityListFunctional.add(PROJECTOR_BLOCK);

            blocksListCustomTab.addAll(bEntityListBuilding);
            blocksListCustomTab.addAll(bEntityListFunctional);

            Utils.Log_registering("Block Entities",ShortUtils.MOD_ID);
            Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS, bEntityListBuilding);
            Utils.registerItemGroupEntry(ItemGroups.FUNCTIONAL, bEntityListFunctional);
        }
    }

    public static Identifier getLampIdent(String ModID, String color) { return colRedstoneLamp.getIdent(ModID,color); }
    public static String getLampID(String color) { return colRedstoneLamp.getID(color); }
}
