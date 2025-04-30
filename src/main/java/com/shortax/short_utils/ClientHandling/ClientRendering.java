package com.shortax.short_utils.ClientHandling;

import com.shortax.short_utils.Initializers.ModBlocks;
import com.shortax.short_utils.blocks.stairs.LeafStair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientRendering {

    public static void init()
    {
        for(Block b : ModBlocks.BlocksWithTransparency)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getCutout());
        }

        for(LeafStair b : ModBlocks.LEAVES_STAIRS)
        {
            ColorProviderRegistry.BLOCK.register(
                    (state, world, pos, tintIndex) -> world != null && pos != null && b.tint != -1 ? BiomeColors.getFoliageColor(world, pos) :  b.tint,
                    b
            );
        }
    }
}
