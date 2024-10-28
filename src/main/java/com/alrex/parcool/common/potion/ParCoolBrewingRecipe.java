package com.alrex.parcool.common.potion;


import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;


@EventBusSubscriber(modid = "parcool")
public class ParCoolBrewingRecipe {
    @SubscribeEvent
    public static void onRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(
                        Potions.AWKWARD,
                        Items.POISONOUS_POTATO,
                        com.alrex.parcool.common.potion.Potions.POOR_ENERGY_DRINK
                );
        builder.addMix(
                        Potions.AWKWARD,
                        Items.CHICKEN,
                        com.alrex.parcool.common.potion.Potions.POOR_ENERGY_DRINK
                );
        builder.addMix(
                        Potions.AWKWARD,
                        Items.QUARTZ,
                        com.alrex.parcool.common.potion.Potions.ENERGY_DRINK
                );
        builder.addMix(
                        com.alrex.parcool.common.potion.Potions.POOR_ENERGY_DRINK,
                        Items.QUARTZ,
                        com.alrex.parcool.common.potion.Potions.ENERGY_DRINK
                );
	}
}
