package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import com.shortax.short_utils.blocks.plates.Obsid_Plate;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;

import java.util.Map;

public class ModBlocks {

    public static Identifier OBSID_PRESSURE_PLATE_IDENT;
    public static Block OBSID_PRESSURE_PLATE;
    public static Map<String, Block> COLORED_REDSTONE_LAMPS;

    public static void init()
    {
        String OBSID_PRESSURE_PLATE_ID = "obsid_pressure_plate";

        OBSID_PRESSURE_PLATE_IDENT = Identifier.of(ShortUtils.MOD_ID,OBSID_PRESSURE_PLATE_ID);
        RegistryKey<Block> OBSID_PRESSURE_PLATE_KEY = RegistryKey.of(RegistryKeys.BLOCK,OBSID_PRESSURE_PLATE_IDENT);
        OBSID_PRESSURE_PLATE = Utils.registerBlock(OBSID_PRESSURE_PLATE_ID, new Obsid_Plate(OBSID_PRESSURE_PLATE_KEY),OBSID_PRESSURE_PLATE_IDENT);

        COLORED_REDSTONE_LAMPS = colRedstoneLamp.init(ShortUtils.MOD_ID);
        for(String col : COLORED_REDSTONE_LAMPS.keySet())
        {
            COLORED_REDSTONE_LAMPS.compute(col, (k, b) -> Utils.registerBlock(getLampID(col), b, getLampIdent(ShortUtils.MOD_ID,col)));
        }

        registerBlocks();
    }

    public static void registerBlocks()
    {
        ShortUtils.LOGGER.info("Registering Blocks: " + ShortUtils.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {

            entries.add(ModBlocks.OBSID_PRESSURE_PLATE);

            for(String col : COLORED_REDSTONE_LAMPS.keySet())
                entries.add(COLORED_REDSTONE_LAMPS.get(col));
        });
    }

    public static Identifier getLampIdent(String ModID, String color) { return colRedstoneLamp.getIdent(ModID,color); }
    public static String getLampID(String color) { return colRedstoneLamp.getID(color); }
}
