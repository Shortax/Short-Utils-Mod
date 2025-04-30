package com.shortax.short_utils.Initializers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientRendering {

    public static void init()
    {
        for(Block b : ModBlocks.BlocksWithTransparency)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getCutout());
        }
    }
}
