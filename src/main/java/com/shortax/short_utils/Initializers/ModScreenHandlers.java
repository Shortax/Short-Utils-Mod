package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.blocks.blockentities.Projector.ProjectorScreen.ProjectorScreenHandler;
import com.shortax.short_utils.blocks.Crafters.Combiner.CombinerScreen.CombinerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CombinerScreenHandler> COMBINER_SCREEN_HANDLER = Registry
            .register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(
                            ShortUtils.MOD_ID,
                            "combiner_screen_handler"
                    ),
                    new ScreenHandlerType<>(CombinerScreenHandler::new,
                            FeatureSet.empty())
            );

    public static final ScreenHandlerType<ProjectorScreenHandler> PROJECTOR_SCREEN_HANDLER = Registry
            .register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(
                            ShortUtils.MOD_ID,
                            "projector_screen_handler"
                    ),
                    new ExtendedScreenHandlerType<>(
                            ProjectorScreenHandler::new,
                            BlockPos.PACKET_CODEC)
            );


    public static void init()
    {

    }

}

