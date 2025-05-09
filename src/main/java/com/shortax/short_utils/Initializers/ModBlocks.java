package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.BlockSettings.ModBlockSettings;
import com.shortax.short_utils.blocks.Crafters.Combiner.combiner_block;
import com.shortax.short_utils.blocks.FakeRedstoneBlocks.FakeTrapdoor;
import com.shortax.short_utils.blocks.stairs.LeafStair;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import com.shortax.short_utils.blocks.plates.Obsid_Plate;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;

import java.util.ArrayList;
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

    public static List<Block> BlocksWithTransparency = new ArrayList<>();

    public static void init()
    {
        List<ItemConvertible> blocksListRedstone = new ArrayList<>();
        List<ItemConvertible> blocksListColored = new ArrayList<>();
        List<ItemConvertible> blocksListBuilding = new ArrayList<>();
        List<ItemConvertible> blocksListFunctional = new ArrayList<>();


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
        BlocksWithTransparency.add(FAKE_OAK_TRAPDOOR);

        FAKE_SPRUCE_TRAPDOOR = (FakeTrapdoor) Utils.registerBlock_C(FAKE_SPRUCE_TRAPDOOR_ID, FakeTrapdoor.SPRUCE_TYPE::new,FakeTrapdoor.SPRUCE_TYPE.DEFAULT_SETTINGS);
        blocksListBuilding.add(FAKE_SPRUCE_TRAPDOOR);


        FAKE_IRON_TRAPDOOR = (FakeTrapdoor) Utils.registerBlock_C(FAKE_IRON_TRAPDOOR_ID, FakeTrapdoor.IRON_TYPE::new,FakeTrapdoor.IRON_TYPE.DEFAULT_SETTINGS);
        blocksListBuilding.add(FAKE_IRON_TRAPDOOR);
        BlocksWithTransparency.add(FAKE_IRON_TRAPDOOR);

        LEAVES_STAIRS = new ArrayList<>();
        for(LeafStair ls : LeafStair.get_leaves_stairs())
        {
            LEAVES_STAIRS.add((LeafStair) Utils.registerBlock(ls.ID,ls,Identifier.of(ShortUtils.MOD_ID,ls.ID)));
        }
        BlocksWithTransparency.addAll(LEAVES_STAIRS);
        blocksListBuilding.addAll(LEAVES_STAIRS);


        COMBINER_BLOCK = (combiner_block) Utils.registerBlock_C(COMBINER_BLOCK_ID,combiner_block::new, ModBlockSettings.COMBINER_BLOCK);
        blocksListFunctional.add(COMBINER_BLOCK);


        Utils.registering("Blocks",ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.REDSTONE,blocksListRedstone);
        Utils.registerItemGroupEntry(ItemGroups.COLORED_BLOCKS,blocksListColored);
        Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS,blocksListBuilding);
        Utils.registerItemGroupEntry(ItemGroups.FUNCTIONAL,blocksListFunctional);
    }

    public static Identifier getLampIdent(String ModID, String color) { return colRedstoneLamp.getIdent(ModID,color); }
    public static String getLampID(String color) { return colRedstoneLamp.getID(color); }
}
