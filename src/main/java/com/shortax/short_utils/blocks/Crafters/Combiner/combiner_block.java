package com.shortax.short_utils.blocks.Crafters.Combiner;

import com.mojang.serialization.MapCodec;
import com.shortax.short_utils.ScreenHandlers.Combiner_Screen.CombinerScreenHandler;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class combiner_block extends Block {
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<combiner_block> CODEC = createCodec(combiner_block::new);

    //volume, pitch, break, step, place, hit, fall
    private static final BlockSoundGroup customSound = new BlockSoundGroup(
            1.0F, 0.75F, SoundEvents.BLOCK_IRON_BREAK, SoundEvents.BLOCK_IRON_STEP, SoundEvents.BLOCK_IRON_PLACE, SoundEvents.BLOCK_IRON_HIT, SoundEvents.BLOCK_IRON_FALL
    );

    public static final Settings DEFAULT_SETTINGS = AbstractBlock.Settings.create()
            .strength(3.2F, 6.0F)
            .sounds(customSound).mapColor(MapColor.LIGHT_GRAY)
            .requiresTool();

    private static final Text TITLE = Text.of("Combining");


    public combiner_block(Settings settings)
    {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory(
                (syncId, inventory, player) -> new CombinerScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), TITLE
        );
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public MapCodec<? extends combiner_block > getCodec() {
        return CODEC;
    }
}
