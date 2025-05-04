package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blockentities.mixedBlocks.mixed_block_entity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {

    public static final BlockEntityType<mixed_block_entity> MIXED_BLOCK_TYPE = register(
            "combined_block",
            FabricBlockEntityTypeBuilder.create(mixed_block_entity::new, ModBlockEntities.MIXED_BLOCK).build()
    );

    public static void init() {

    }

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ShortUtils.MOD_ID, path), blockEntityType);
    }
}
