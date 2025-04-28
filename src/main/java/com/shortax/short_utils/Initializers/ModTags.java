package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks{
        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ShortUtils.MOD_ID,name));
        }
    }

    public static class Items{
        public static final TagKey<Item> COL_RED_LAMP_ITEM = createTag("col_red_lamp");
        public static final TagKey<Item> CUSTOM_PRESS_PLATES = createTag("custom_pressure_plates");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ShortUtils.MOD_ID,name));
        }

    }
}
