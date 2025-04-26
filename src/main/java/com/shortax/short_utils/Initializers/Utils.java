package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.function.Consumer;

public class Utils {


    public static Block registerBlock(String name, Block block, Identifier id)
    {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        registerBlockItem(name,block,key);
        return Registry.register(Registries.BLOCK, Identifier.of(ShortUtils.MOD_ID,name),block);
    }

    public static void registerBlockItem(String name, Block block,RegistryKey<Item> key)
    {
        Item.Settings settings = new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key);
        Registry.register(Registries.ITEM, Identifier.of(ShortUtils.MOD_ID,name),new BlockItem(block,settings));
    }

    public static <T> void applyToEach(Collection<T> list, Consumer<T> action) {
        for (T item : list) {
            action.accept(item);
        }
    }

    public static Item getDye(String color){ return colRedstoneLamp.getDye(color); }
}
