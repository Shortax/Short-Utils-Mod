package com.shortax.short_utils.Initializers;


import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.BlockSettings.ModBlockSettings;
import com.shortax.short_utils.blocks.blockentities.Projector.projector_block;
import com.shortax.short_utils.blocks.blockentities.mixedBlocks.mixed_block;
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
