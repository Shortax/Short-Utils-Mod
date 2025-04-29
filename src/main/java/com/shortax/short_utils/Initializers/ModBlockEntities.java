package com.shortax.short_utils.Initializers;


import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blockentities.combinedBlocks.combined_block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {

    public static combined_block COMBINED_BLOCK;

    public static void init()
    {
        List<ItemConvertible> toAdd = new ArrayList<>();

        String COMBINED_BLOCK_ID = "combined_block";
        COMBINED_BLOCK = (combined_block) Utils.registerBlock_C(COMBINED_BLOCK_ID, combined_block::new,combined_block.DEFAULT_SETTINGS);
        toAdd.add(COMBINED_BLOCK);

        Utils.registering("Block Entities",ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS,toAdd);
    }
}
