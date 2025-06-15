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
import com.shortax.short_utils.Initializers.Utils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.OBSID_PRESSURE_PLATE);
        addDrop(ModBlocks.STAR_BLOCK_LIGHT);
        addDrop(ModBlocks.FAKE_OAK_TRAPDOOR);
        addDrop(ModBlocks.FAKE_SPRUCE_TRAPDOOR);
        addDrop(ModBlocks.FAKE_IRON_TRAPDOOR);
        Utils.applyToEach(ModBlocks.COLORED_REDSTONE_LAMPS.values(),this::addDrop);
        Utils.applyToEach(ModBlocks.LEAVES_STAIRS, leafStair -> this.addDrop(leafStair,dropsWithSilkTouchOrShears(leafStair)));
        addDrop(ModBlocks.COMBINER_BLOCK);

        addDrop(ModBlocks.WithEntities.MIXED_BLOCK);
        addDrop(ModBlocks.WithEntities.PROJECTOR_BLOCK);

    }
}
