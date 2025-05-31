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

package com.shortax.short_utils.items;

import com.shortax.short_utils.blocks.blockEntities.Projector.gen_projected_block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import net.minecraft.world.World;

public class projected_remover extends Item {

    public projected_remover(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(hand.equals(Hand.MAIN_HAND))
        {
            BlockPos inRay = custom_raycast(world,user,7,gen_projected_block.class);
            if(inRay != null)
            {

                world.removeBlock(inRay,false);
            }

            return ActionResult.SUCCESS;
        }
        else{
            return ActionResult.FAIL;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private <T extends AbstractBlock> BlockPos custom_raycast(World world, PlayerEntity origin, int distance, Class<T> filter)
    {
        Vec3d vec0 = origin.getCameraPosVec(1.0f);

        Vec3d direction = origin.getRotationVec(1.0F);

        double x;
        double y;
        double z;

        for(int i = 1; i <= distance; i++)
        {
            x = vec0.x + direction.x * i;
            y = vec0.y + direction.y * i;
            z = vec0.z + direction.z * i;

            BlockPos pos = new BlockPos((int) Math.floor(x),(int) Math.floor(y),(int) Math.floor(z));

            Block b = world.getBlockState(pos).getBlock();

            if(filter.isInstance(b))
            {
                return pos;
            }
        }
        return null;
    }
}
