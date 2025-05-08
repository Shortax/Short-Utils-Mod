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

        BUILD_PROJECTOR_TYPE = register("build_projector",FabricBlockEntityTypeBuilder
                .create(projector_Entity::new, ModBlocksWithEntities.BUILD_PROJECTOR).build());
    }

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ShortUtils.MOD_ID, path), blockEntityType);
    }
}
