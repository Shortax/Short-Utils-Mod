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

package com.shortax.short_utils.blocks.blockentities.Projector;

import com.shortax.short_utils.Initializers.ModBlockEntityTypes;
import com.shortax.short_utils.blocks.blockentities.Projector.ProjectorScreen.ProjectorScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class projector_Entity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos> {

    protected final PropertyDelegate propertyDelegate;

    public static int DEFAULT_RADIUS = 5;
    public static int DEFAULT_THICKNESS = 1;
    public static int DEFAULT_TRANSPARENCY = 50;

    public static int MIN_RADIUS = 3;
    public static int MIN_THICKNESS = 1;
    public static int MIN_TRANSPARENCY = 0;

    public static int MAX_RADIUS = 51;
    public static int MAX_THICKNESS = MAX_RADIUS -1;
    public static int MAX_TRANSPARENCY = 100;

    private int radius = MIN_RADIUS;
    private int thickness = MIN_THICKNESS;
    private int transparency = MIN_TRANSPARENCY;

    private int i_tick = 0;

    public static int delegate_size = 3;

    public projector_Entity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BUILD_PROJECTOR_TYPE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> projector_Entity.this.radius;
                    case 1 -> projector_Entity.this.thickness;
                    case 2 -> projector_Entity.this.transparency;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> projector_Entity.this.radius = value;
                    case 1 -> projector_Entity.this.thickness = value;
                    case 2 -> projector_Entity.this.transparency = value;
                }
            }

            @Override
            public int size() {
                return delegate_size;
            }
        };

        this.propertyDelegate.set(0,DEFAULT_RADIUS);
        this.propertyDelegate.set(1,DEFAULT_THICKNESS);
        this.propertyDelegate.set(2,DEFAULT_TRANSPARENCY);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.short_utils.projector_block");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("radius", radius);
        nbt.putInt("transparency", transparency );
        nbt.putInt("thickness", thickness );
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        radius = nbt.getInt("radius",DEFAULT_RADIUS);
        transparency = nbt.getInt("transparency",DEFAULT_TRANSPARENCY);
        thickness = nbt.getInt("thickness",DEFAULT_THICKNESS);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ProjectorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @SuppressWarnings({"Unused", "unused"})
    public void tick(World world1, BlockPos pos, BlockState state1) {
        if(i_tick % 100 == 0)
        {
            //System.out.println(pos + " | " + state1 + " | " + world1 +  " | " + "Radius Value = " + this.propertyDelegate.get(0));
            if(i_tick >= 100000)
                i_tick = 0;
        }
        i_tick++;
    }
}
