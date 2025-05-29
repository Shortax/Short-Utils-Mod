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

package com.shortax.short_utils.blocks.blockEntities.Projector.ProjectorScreen;

import com.shortax.short_utils.Initializers.ModScreenHandlers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.util.math.BlockPos;
import com.shortax.short_utils.blocks.blockEntities.Projector.projector_Entity;

public class ProjectorScreenHandler extends ScreenHandler {

    private final PropertyDelegate propertyDelegate;
    public final projector_Entity blockEntity;


    public ProjectorScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos)
    {
        this(syncId,inventory,inventory.player.getWorld().getBlockEntity(pos),new ArrayPropertyDelegate(projector_Entity.delegate_size));
    }

    public ProjectorScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.PROJECTOR_SCREEN_HANDLER, syncId);

        this.blockEntity = (projector_Entity) blockEntity;
        this.propertyDelegate = arrayPropertyDelegate;

        this.addProperties(this.propertyDelegate);
    }


    @Override
    @Environment(EnvType.SERVER)
    public void setProperty(int id, int value) {
        super.setProperty(id,value);
        this.blockEntity.markDirty();
        this.sendContentUpdates();
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return true;
    }

    public int get_radius() { return this.get_delegate(0); }
    public int get_thickness() { return this.get_delegate(1); }
    public int get_transparency() { return this.get_delegate(2); }

    public int get_min_radius() { return projector_Entity.MIN_RADIUS; }
    public int get_min_thickness() { return projector_Entity.MIN_THICKNESS; }
    public int get_min_transparency() { return projector_Entity.MIN_TRANSPARENCY; }

    public int get_max_radius() { return projector_Entity.MAX_RADIUS; }
    public int get_max_thickness() { return projector_Entity.MAX_THICKNESS; }
    public int get_max_transparency() { return projector_Entity.MAX_TRANSPARENCY; }

    public int get_default_radius() { return projector_Entity.DEFAULT_RADIUS; }
    public int get_default_thickness() { return projector_Entity.DEFAULT_THICKNESS; }
    public int get_default_transparency() { return projector_Entity.DEFAULT_TRANSPARENCY; }

    public int get_delegate(int index)
    {
        return this.propertyDelegate.get(index);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
