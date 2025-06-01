/*
 *
 *  * This file is part of Short Utils mod
 *  *
 *  * Copyright (C) 2025 Shortax
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Lesser General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.cRedstoneLamp.colRedstoneLamp;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {

    public static void registerItemGroupEntry(RegistryKey<ItemGroup> itemGroup, Collection<ItemConvertible> entriesToAdd)
    {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> {
            for(ItemConvertible ItemBlock : entriesToAdd)
            {
                entries.add(ItemBlock);
            }
        });
    }

    public static RegistryKey<ItemGroup> registerItemGroup(String ID, Item icon, String TabName)
    {
        Identifier ident = Identifier.of(ShortUtils.MOD_ID,ID);
        RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP,ident);
        Registry.register(Registries.ITEM_GROUP,key, FabricItemGroup.builder().displayName(Text.literal(TabName)).icon(() -> new ItemStack(icon)).build());

        return key;
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
        RegistryKey<Item> keyItem = RegistryKey.of(RegistryKeys.ITEM,ident);
        registerBlockItem(nameID,block,keyItem);
        return Registry.register(Registries.BLOCK, Identifier.of(ShortUtils.MOD_ID, nameID),block);
    }

    public static void registerBlockItem(String name, Block block,RegistryKey<Item> key)
    {
        Item.Settings settings = new Item.Settings().useBlockPrefixedTranslationKey().registryKey(key);
        Registry.register(Registries.ITEM, Identifier.of(ShortUtils.MOD_ID, name),new BlockItem(block,settings));
    }

    public static <T> void applyToEach(Collection<T> list, Consumer<T> action) {
        for (T o : list) {
            action.accept(o);
        }
    }

    public static <T,E> Collection<E> applyToEachReturn(Collection<T> list, Function<T,E> action){
        Collection<E> ret = new ArrayList<>();
        for(T o : list){
            ret.add(action.apply(o));
        }
        return ret;
    }

    public static Item registerItem_C(String nameID, Function<Item.Settings, Item> itemConstructor, Item.Settings settings)
    {
        Identifier ident = Identifier.of(ShortUtils.MOD_ID,nameID);

        RegistryKey<Item> keyBlock = RegistryKey.of(RegistryKeys.ITEM,ident);
        Item item = itemConstructor.apply(settings.registryKey(keyBlock));

        return registerItem(item,ident);
    }

    public static Item registerItem(Item item, Identifier ident)
    {
        return Registry.register(Registries.ITEM,ident,item);
    }

    @SuppressWarnings("unused")
    public static void LogToChat(String message, World world){
        if(!world.isClient){
            Objects.requireNonNull(world.getServer()).getPlayerManager().broadcast(Text.of(message),false);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void addTooltip(Item item, Collection<Formatting> formattings)
    {
        add_tagged_Tooltip(item,Registries.ITEM.getId(item).getPath(), formattings);
    }

    @Environment(EnvType.CLIENT)
    public static void add_tagged_Tooltip(Item item, String tag, Collection<Formatting> formattings)
    {
        add_String_tooltip( item , Text.translatable(
                get_tooltip_path(
                        tag
                )),
                formattings
        );
    }

    @Environment(EnvType.CLIENT)
    public static void add_String_tooltip(Item item, MutableText text, Collection<Formatting> formattings)
    {
        for(Formatting f : formattings){
            text = text.formatted(f);
        }
        MutableText formattedText = text;
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (!itemStack.isOf(item)) {
                return;
            }
            list.add(formattedText);
        });
    }

    public static String get_tooltip_path(String path)
    {
        return "item.short_utils." + path + ".tooltip";
    }

    public static Item getDye(String color){ return colRedstoneLamp.getDye(color); }

    public static void Log_registering(String LogType, String modID)
    {
        ShortUtils.LOGGER.info("Registering {}: {}", LogType, modID);
    }

    public static class custom_tooltip_bundle{
        private final List<Formatting> formattings;
        public final String text;
        public String tag;

        public custom_tooltip_bundle(String t){
            formattings = new ArrayList<>();
            text = t;
            tag = null;
        }

        public custom_tooltip_bundle addFormatting(Formatting format){
            formattings.add(format);
            return this;
        }

        public List<Formatting> getFormattings(){
            return this.formattings;
        }

        public custom_tooltip_bundle addTag(String tag)
        {
            this.tag = tag;
            return this;
        }
    }
}
