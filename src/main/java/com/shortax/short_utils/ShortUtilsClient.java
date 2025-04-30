package com.shortax.short_utils;

import com.shortax.short_utils.Initializers.ClientRendering;
import net.fabricmc.api.ClientModInitializer;

public class ShortUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientRendering.init();
    }
}
