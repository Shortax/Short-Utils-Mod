package com.shortax.short_utils;

import com.shortax.short_utils.ClientHandling.ClientRendering;
import net.fabricmc.api.ClientModInitializer;

public class ShortUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientRendering.init();
    }
}
