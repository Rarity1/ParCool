package com.alrex.parcool.common.handlers;

import com.alrex.parcool.common.action.Parkourability;
import com.alrex.parcool.common.info.ClientSetting;
import com.alrex.parcool.common.network.payload.ClientInformationPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = "parcool")
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            Parkourability parkourability = Parkourability.get(player);
            if (parkourability == null) return;
            parkourability.getActionInfo().setClientSetting(ClientSetting.readFromLocalConfig());
            PacketDistributor.sendToServer(new ClientInformationPayload(player.getUUID(), true, parkourability.getClientInfo()));
        }
    }
}
