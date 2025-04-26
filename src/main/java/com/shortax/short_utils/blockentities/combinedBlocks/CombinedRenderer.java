package com.shortax.short_utils.blockentities.combinedBlocks;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public class CombinedRenderer implements BlockEntityRenderer<combined_block_entity>{

    @Override
    public void render(combined_block_entity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {

    }

    @Override
    public boolean rendersOutsideBoundingBox(combined_block_entity blockEntity) {
        return BlockEntityRenderer.super.rendersOutsideBoundingBox(blockEntity);
    }

    @Override
    public int getRenderDistance() {
        return BlockEntityRenderer.super.getRenderDistance();
    }

    @Override
    public boolean isInRenderDistance(combined_block_entity blockEntity, Vec3d pos) {
        return BlockEntityRenderer.super.isInRenderDistance(blockEntity, pos);
    }
}
