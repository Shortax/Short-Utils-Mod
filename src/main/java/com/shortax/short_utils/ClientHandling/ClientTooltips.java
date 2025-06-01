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

package com.shortax.short_utils.ClientHandling;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.ModItems;
import com.shortax.short_utils.Initializers.Utils;
import com.shortax.short_utils.Initializers.constants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ClientTooltips {

    public static void init(){

        for(ItemConvertible i : ModItems.itemsListCustomTab){
            Utils.custom_tooltip_bundle bundle = ModItems.tooltip.get(i.asItem());
            handle_tooltip(i.asItem(),bundle);
        }

        for(ItemConvertible i : ModBlocks.blocksListCustomTab){
            Utils.custom_tooltip_bundle bundle = ModBlocks.tooltip.get(i.asItem());
            handle_tooltip(i.asItem(),bundle);
        }
    }

    private static void handle_tooltip(Item item, Utils.custom_tooltip_bundle bundle){
        if(bundle != null) {
            if (bundle.tag != null) {
                Utils.add_tagged_Tooltip(item, bundle.tag, bundle.getFormattings());
            } else if (bundle.text != null) {
                if (bundle.text.equals(constants.translated_tooltip_flag)) {
                    Utils.addTooltip(item, bundle.getFormattings());
                } else {
                    Utils.add_String_tooltip(item, Text.literal(bundle.text), bundle.getFormattings());
                }
            }
        }
    }
}
