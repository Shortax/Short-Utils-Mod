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
import com.shortax.short_utils.blocks.blockentities.Projector.projector_Entity;
import com.shortax.short_utils.blocks.blockentities.mixedBlocks.mixed_block_entity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {

    public static BlockEntityType<mixed_block_entity> MIXED_BLOCK_TYPE;

    public static BlockEntityType<projector_Entity> BUILD_PROJECTOR_TYPE;

    public static void init() {
        MIXED_BLOCK_TYPE = register("mixed_block", FabricBlockEntityTypeBuilder
                .create(mixed_block_entity::new, ModBlocksWithEntities.MIXED_BLOCK).build());

        BUILD_PROJECTOR_TYPE = register(ModBlocksWithEntities.PROJECTOR_BLOCK_ID,FabricBlockEntityTypeBuilder
                .create(projector_Entity::new, ModBlocksWithEntities.PROJECTOR_BLOCK).build());
    }

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ShortUtils.MOD_ID, path), blockEntityType);
    }
}
