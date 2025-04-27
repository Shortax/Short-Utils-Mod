package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
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
    public static Map<String, Block> COLORED_REDSTONE_LAMPS;

    public static void init()
    {
        List<ItemConvertible> toAdd = new ArrayList<>();


        String OBSID_PRESSURE_PLATE_ID = "obsid_pressure_plate";
        OBSID_PRESSURE_PLATE = Utils.registerBlock_C(OBSID_PRESSURE_PLATE_ID, Obsid_Plate::new,Obsid_Plate.DEFAULT_SETTINGS);
        toAdd.add(OBSID_PRESSURE_PLATE);

        COLORED_REDSTONE_LAMPS = colRedstoneLamp.get_Lamps(ShortUtils.MOD_ID);
        for(String col : COLORED_REDSTONE_LAMPS.keySet())
        {
            COLORED_REDSTONE_LAMPS.compute(col, (k, b) -> Utils.registerBlock(getLampID(col), b, getLampIdent(ShortUtils.MOD_ID,col)));
            toAdd.add(COLORED_REDSTONE_LAMPS.get(col));
        }


        Utils.registerItemGroupEntry("Blocks",ShortUtils.MOD_ID,ItemGroups.REDSTONE,toAdd);
    }

    public static Identifier getLampIdent(String ModID, String color) { return colRedstoneLamp.getIdent(ModID,color); }
    public static String getLampID(String color) { return colRedstoneLamp.getID(color); }
}
