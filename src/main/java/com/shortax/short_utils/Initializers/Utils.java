package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {

    public static void registerItemGroupEntry(String LogType, String ModID, RegistryKey<ItemGroup> ItemGroup, Collection<ItemConvertible> entriesToAdd)
    {
        ShortUtils.LOGGER.info("Registering {}: {}", LogType, ModID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroup).register(entries -> {
            for(ItemConvertible ItemBlock : entriesToAdd)
            {
                entries.add(ItemBlock);
            }
        });
    }

    public static Block registerBlock_C(String nameID, Function<AbstractBlock.Settings, Block> blockConstructor, AbstractBlock.Settings settings)
    {
        Identifier ident = Identifier.of(ShortUtils.MOD_ID,nameID);

        RegistryKey<Block> keyBlock = RegistryKey.of(RegistryKeys.BLOCK,ident);
        Block block = blockConstructor.apply(settings.registryKey(keyBlock));

        return registerBlock(nameID,block,ident);
    }

    public static Block registerBlock(String nameID, Block block, Identifier ident)
    {
        RegistryKey<Item> keyItem = RegistryKey.of(RegistryKeys.ITEM, ident);
        registerBlockItem(nameID,block,keyItem);
        return Registry.register(Registries.BLOCK, Identifier.of(ShortUtils.MOD_ID,nameID),block);
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

    @SuppressWarnings("unused")
    public static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(ShortUtils.MOD_ID,name),item);
    }

    public static Item getDye(String color){ return colRedstoneLamp.getDye(color); }
}
