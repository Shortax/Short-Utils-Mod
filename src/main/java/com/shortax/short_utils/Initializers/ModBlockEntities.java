package com.shortax.short_utils.Initializers;


import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blockentities.combinedBlocks.combined_block;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static combined_block COMBINED_BLOCK;

    public static void init()
    {
        String COMBINED_BLOCK_ID = "combined_block";
        Identifier COMBINED_BLOCK_IDENT = Identifier.of(ShortUtils.MOD_ID,COMBINED_BLOCK_ID);
        RegistryKey<Block> COMBINED_BLOCK_KEY = RegistryKey.of(RegistryKeys.BLOCK,COMBINED_BLOCK_IDENT);
        COMBINED_BLOCK = (combined_block) Utils.registerBlock(COMBINED_BLOCK_ID, new combined_block(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE).registryKey(COMBINED_BLOCK_KEY)),COMBINED_BLOCK_IDENT);

        registerBlocks();
    }

    public static void registerBlocks()
    {
        ShortUtils.LOGGER.info("Registering Block Entities: " + ShortUtils.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlockEntities.COMBINED_BLOCK);
            //
        });
    }
}
