package com.shortax.short_utils.Initializers;

import com.shortax.short_utils.ShortUtils;
import com.shortax.short_utils.ScreenHandlers.Combiner_Screen.CombinerScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CombinerScreenHandler> COMBINER_SCREEN_HANDLER = Registry
            .register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(ShortUtils.MOD_ID, "combiner_block"),
                    new ScreenHandlerType<>(CombinerScreenHandler::new,
                            FeatureSet.empty())
            );

    public static void init()
    {

    }
}
