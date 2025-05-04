package com.shortax.short_utils;

import com.shortax.short_utils.ClientHandling.ClientRendering;
import com.shortax.short_utils.ClientHandling.ClientScreenHandlers;
import net.fabricmc.api.ClientModInitializer;

public class ShortUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientRendering.init();
        ClientScreenHandlers.init();

    }
}
