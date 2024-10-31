package com.alrex.parcool.client.hud;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.api.distmarker.OnlyIn;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT ,modid = "parcool")
@OnlyIn(Dist.CLIENT)
public class HUDRegistry {
    @SubscribeEvent
    public static void onRegisterGui(RegisterGuiLayersEvent event) {
        HUDManager.getInstance().registerHUD(event);
    }
}
