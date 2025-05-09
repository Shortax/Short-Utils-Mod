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
import org.jetbrains.annotations.Nullable;

public class projector_Entity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos> {

    protected final PropertyDelegate propertyDelegate;

    private int radius = 0;
    private int thickness = 1;
    private int transparency = 25;

    private int MaxRadius = 51;
    private int thicknessMax = MaxRadius-1;

    public projector_Entity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BUILD_PROJECTOR_TYPE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> projector_Entity.this.radius;
                    case 1 -> projector_Entity.this.thickness;
                    case 2 -> projector_Entity.this.transparency;

                    case 3 -> projector_Entity.this.thicknessMax;
                    case 4 -> projector_Entity.this.MaxRadius;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> projector_Entity.this.radius = value;
                    case 1 -> projector_Entity.this.thickness = value;
                    case 2 -> projector_Entity.this.transparency = value;

                    case 3 -> projector_Entity.this.thicknessMax = value;
                    case 4 -> projector_Entity.this.MaxRadius = value;
                }
            }

            @Override
            public int size() {
                return 5;
            }
        };
    }

    private boolean shouldRender()
    {
        return this.radius > 0;
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
        radius = nbt.getInt("radius",0);
        transparency = nbt.getInt("transparency",25);
        thickness = nbt.getInt("thickness",1);
        super.readNbt(nbt, registryLookup);
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
}
