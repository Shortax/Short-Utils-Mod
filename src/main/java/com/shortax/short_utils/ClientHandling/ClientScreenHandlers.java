package com.shortax.short_utils.ClientHandling;

import com.shortax.short_utils.Initializers.ModScreenHandlers;
import com.shortax.short_utils.blocks.blockentities.Projector.ProjectorScreen.ProjectorScreen;
import com.shortax.short_utils.blocks.Crafters.Combiner.CombinerScreen.Combiner_Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ClientScreenHandlers {
    public static void init()
    {
        HandledScreens.register(ModScreenHandlers.COMBINER_SCREEN_HANDLER, Combiner_Screen::new);
        HandledScreens.register(ModScreenHandlers.PROJECTOR_SCREEN_HANDLER, ProjectorScreen::new);
    }
}
