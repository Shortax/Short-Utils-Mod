package com.shortax.short_utils.Initializers;


import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.Crafters.Combiner.combiner_block;
import com.shortax.short_utils.blockentities.mixedBlocks.mixed_block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {

    public static mixed_block MIXED_BLOCK;
    public static combiner_block COMBINER_BLOCK;

    public static void init()
    {
        List<ItemConvertible> buildingBlockEntities = new ArrayList<>();
        String MIXED_BLOCK_ID = "mixed_block";
        MIXED_BLOCK = (mixed_block) Utils.registerBlock_C(MIXED_BLOCK_ID, mixed_block::new, mixed_block.DEFAULT_SETTINGS);
        buildingBlockEntities.add(MIXED_BLOCK);

        Utils.registering("Block Entities",ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS,buildingBlockEntities);

        //Blocks.SMITHING_TABLE
        //Blocks.CRAFTING_TABLE
    }
}
