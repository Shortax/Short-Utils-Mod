package com.shortax.short_utils.ClientHandling;

import com.shortax.short_utils.Initializers.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.FoliageColors;

@Environment(EnvType.CLIENT)
public class ClientRendering {

    public static void init()
    {
        for(Block b : ModBlocks.BlocksWithTransparency)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getCutout());
        }

        for(Block b : ModBlocks.LEAVES_STAIRS)
        {
            ColorProviderRegistry.BLOCK.register(
                    (state, world, pos, tintIndex) -> world != null && pos != null ?
                            BiomeColors.getFoliageColor(world, pos) :
                            FoliageColors.getColor(0.7,0.8),
                    b
            );
        }
    }
}
