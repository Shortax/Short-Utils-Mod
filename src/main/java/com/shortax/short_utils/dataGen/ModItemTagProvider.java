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

package com.shortax.short_utils.dataGen;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.Initializers.ModTags;
import com.shortax.short_utils.Initializers.Utils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {



    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        Utils.applyToEach(
                Utils.applyToEachReturn(
                        ModBlocks.COLORED_REDSTONE_LAMPS.values(),
                        Block::asItem), //->
                getOrCreateTagBuilder(ModTags.Items.COL_RED_LAMP_ITEM)::add
        );

        getOrCreateTagBuilder(ModTags.Items.CUSTOM_PRESS_PLATES).add(ModBlocks.OBSID_PRESSURE_PLATE.asItem());

        getOrCreateTagBuilder(ModTags.Items.FAKE_REDSTONE_BLOCKS).add(ModBlocks.FAKE_OAK_TRAPDOOR.asItem());
        getOrCreateTagBuilder(ModTags.Items.FAKE_REDSTONE_BLOCKS).add(ModBlocks.FAKE_SPRUCE_TRAPDOOR.asItem());
        getOrCreateTagBuilder(ModTags.Items.FAKE_REDSTONE_BLOCKS).add(ModBlocks.FAKE_IRON_TRAPDOOR.asItem());
    }
}
