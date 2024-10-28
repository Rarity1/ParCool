package com.alrex.parcool.common.handlers;


import com.alrex.parcool.api.Attributes;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = "parcool")
public class AddAttributesHandler {
    @SubscribeEvent
    public static void onAddAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, Attributes.MAX_STAMINA);
        event.add(EntityType.PLAYER, Attributes.STAMINA_RECOVERY);
    }
}
