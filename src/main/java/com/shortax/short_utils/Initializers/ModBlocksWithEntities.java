package com.shortax.short_utils.Initializers;


import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blockentities.BuildProjector.build_projector_block;
import com.shortax.short_utils.blockentities.mixedBlocks.mixed_block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroups;

import java.util.ArrayList;
import java.util.List;

public class ModBlocksWithEntities {

    public static mixed_block MIXED_BLOCK;
    public static build_projector_block BUILD_PROJECTOR;

    public static void init()
    {
        List<ItemConvertible> buildingBlockEntities = new ArrayList<>();
        List<ItemConvertible> functionalBlockEntities = new ArrayList<>();


        String MIXED_BLOCK_ID = "mixed_block";
        MIXED_BLOCK = (mixed_block) Utils.registerBlock_C(MIXED_BLOCK_ID, mixed_block::new, mixed_block.DEFAULT_SETTINGS);
        buildingBlockEntities.add(MIXED_BLOCK);


        String BUILD_PROJECTOR_ID = "build_projector";
        BUILD_PROJECTOR = (build_projector_block) Utils.registerBlock_C(BUILD_PROJECTOR_ID,build_projector_block::new,build_projector_block.DEFAULT_SETTINGS);
        functionalBlockEntities.add(BUILD_PROJECTOR);


        Utils.registering("Block Entities",ShortUtils.MOD_ID);
        Utils.registerItemGroupEntry(ItemGroups.BUILDING_BLOCKS,buildingBlockEntities);
        Utils.registerItemGroupEntry(ItemGroups.FUNCTIONAL,functionalBlockEntities);

        //Blocks.SMITHING_TABLE
        //Blocks.CRAFTING_TABLE
    }
}
