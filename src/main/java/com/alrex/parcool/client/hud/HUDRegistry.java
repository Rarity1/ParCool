package com.alrex.parcool.client.hud;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;


@EventBusSubscriber(bus = Bus.MOD, modid = "parcool")
public class HUDRegistry {
    @SubscribeEvent
    public static void onRegisterGui(RegisterGuiLayersEvent event) {
        HUDManager.getInstance().registerHUD(event);
    }
}
