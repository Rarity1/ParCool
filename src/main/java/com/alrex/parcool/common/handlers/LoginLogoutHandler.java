package com.alrex.parcool.common.handlers;

import com.alrex.parcool.client.animation.Animation;
import com.alrex.parcool.common.action.Parkourability;
import com.alrex.parcool.common.stamina.LocalStamina;
import com.alrex.parcool.server.limitation.Limitations;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.UUID;
import net.neoforged.fml.common.EventBusSubscriber;


@EventBusSubscriber(modid = "parcool")
public class LoginLogoutHandler {


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLocalPlayerLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        LocalStamina.unload();
    }
    
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLocalPlayerLogin(ClientPlayerNetworkEvent.LoggingIn event) {
        LocalStamina.setup(event.getPlayer());
    }


    @SubscribeEvent
    public static void onLogoutInServer(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        Parkourability parkourability = Parkourability.get(player);
        if (parkourability == null) return;
        if (player instanceof ServerPlayer) {
            Limitations.unload(player.getUUID());
        }
        Parkourability.Registry.unloadInServer(player.getUUID());
    }

    @SubscribeEvent
    public static void onLoginInServer(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        Parkourability.Registry.setupInServer(player.getUUID());
    }

    
    @OnlyIn(Dist.CLIENT)
    public static void onLogoutInClient(UUID playerID) {
        Parkourability.Registry.unloadInClient(playerID);
        Animation.Registry.unload(playerID);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onLoginInClient(UUID playerID) {
        Parkourability.Registry.setupInClient(playerID);
        Animation.Registry.setup(playerID);
    }
}
