package com.shortax.short_utils.ClientHandling;

import com.shortax.short_utils.Initializers.ModScreenHandlers;
import com.shortax.short_utils.ScreenHandlers.Combiner_Screen.Combiner_Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ClientScreenHandlers {
    public static void init()
    {
        HandledScreens.register(ModScreenHandlers.COMBINER_SCREEN_HANDLER, Combiner_Screen::new);
    }
}
