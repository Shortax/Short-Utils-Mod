package com.shortax.short_utils.Initializers;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientRendering {

    public static void init()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FAKE_OAK_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FAKE_IRON_TRAPDOOR, RenderLayer.getCutout());
    }
}
