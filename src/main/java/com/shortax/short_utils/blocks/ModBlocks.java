package com.shortax.short_utils.blocks;

import com.shortax.short_utils.ShortUtils;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import com.shortax.short_utils.blocks.plates.Obsid_Plate;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public class ModBlocks {

    public static Block OBSID_PRESSURE_PLATE;
    public static Identifier OBSID_PRESSURE_PLATE_IDENT;

    public static Map<String, Block> COLORED_REDSTONE_LAMPS;

    public static String[] Colors = colRedstoneLamp.COLORS;

    public static void init()
    {
        String OBSID_PRESSURE_PLATE_ID = "obsid_pressure_plate";

        OBSID_PRESSURE_PLATE_IDENT = Identifier.of(ShortUtils.MOD_ID,OBSID_PRESSURE_PLATE_ID);
        RegistryKey<Block> OBSID_PRESSURE_PLATE_KEY = RegistryKey.of(RegistryKeys.BLOCK,OBSID_PRESSURE_PLATE_IDENT);
        OBSID_PRESSURE_PLATE = registerBlock(OBSID_PRESSURE_PLATE_ID, new Obsid_Plate(OBSID_PRESSURE_PLATE_KEY),OBSID_PRESSURE_PLATE_IDENT);

        COLORED_REDSTONE_LAMPS = colRedstoneLamp.init(ShortUtils.MOD_ID);
        for(String col : COLORED_REDSTONE_LAMPS.keySet())
        {
            COLORED_REDSTONE_LAMPS.compute(col, (k, b) -> registerBlock(getLampID(col), b, getLampIdent(ShortUtils.MOD_ID,col)));
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


    private static Block registerBlock(String name, Block block, Identifier id)
    {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        registerBlockItem(name,block,key);
        return Registry.register(Registries.BLOCK, Identifier.of(ShortUtils.MOD_ID,name),block);
    }

    private static void registerBlockItem(String name, Block block,RegistryKey<Item> key)
    {
        Item.Settings settings = new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key);
        Registry.register(Registries.ITEM, Identifier.of(ShortUtils.MOD_ID,name),new BlockItem(block,settings));
    }

    public static <T> void applyToEach(Collection<T> list, Consumer<T> action) {
        for (T item : list) {
            action.accept(item);
        }
    }

    public static Identifier getLampIdent(String ModID, String color) { return colRedstoneLamp.getIdent(ModID,color); }
    public static String getLampID(String color) { return colRedstoneLamp.getID(color); }
    public static Item getDye(String color){ return colRedstoneLamp.getDye(color); }
}
