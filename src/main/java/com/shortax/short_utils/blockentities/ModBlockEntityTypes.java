package com.shortax.short_utils.blockentities;

import com.shortax.short_utils.Initializers.ModBlockEntities;
import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blockentities.combinedBlocks.combined_block_entity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ShortUtils.MOD_ID, path), blockEntityType);
    }

    public static final BlockEntityType<combined_block_entity> COMBINED_BLOCK_TYPE = register(
            "combined_block",
            FabricBlockEntityTypeBuilder.create(combined_block_entity::new, ModBlockEntities.COMBINED_BLOCK).build()
    );

    public static void init() {

    }
}
