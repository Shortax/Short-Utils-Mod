package com.shortax.short_utils.blockentities.BuildProjector.ProjectorScreen;

import com.shortax.short_utils.Initializers.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.util.math.BlockPos;
import com.shortax.short_utils.blockentities.BuildProjector.build_projector_Entity;

public class ProjectorScreenHandler extends ScreenHandler {

    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final PropertyDelegate propertyDelegate;
    public final build_projector_Entity blockEntity;

    public ProjectorScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos)
    {
        this(syncId,inventory,inventory.player.getWorld().getBlockEntity(pos), new ArrayPropertyDelegate(5));
    }

    public ProjectorScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.PROJECTOR_SCREEN_HANDLER, syncId);

        this.blockEntity = (build_projector_Entity) blockEntity;
        this.propertyDelegate = arrayPropertyDelegate;
    }

    public void updateSettings(int n_radius, int n_thickness, int n_transparency)
    {
        this.propertyDelegate.set(0,n_radius);
        this.propertyDelegate.set(1,n_thickness);
        this.propertyDelegate.set(2,n_transparency);
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
