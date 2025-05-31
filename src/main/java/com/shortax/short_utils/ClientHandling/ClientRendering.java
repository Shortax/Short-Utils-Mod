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
        //transparent blocks
        for(Block b : ModBlocks.BlocksWithTransparency)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getTranslucent());
        }

        //Blocks with cutout holes
        for(Block b : ModBlocks.BlocksWithCutout)
        {
            BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getCutout());
        }

        //tint
        for(LeafStair b : ModBlocks.LEAVES_STAIRS)
        {
            ColorProviderRegistry.BLOCK.register(
                    (state, world, pos, tintIndex) -> world != null && pos != null && b.tint != -1 ? BiomeColors.getFoliageColor(world, pos) :  b.tint,
                    b
            );
        }
    }
}
