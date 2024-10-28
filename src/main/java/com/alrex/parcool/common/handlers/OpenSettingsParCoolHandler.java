package com.alrex.parcool.common.handlers;

import com.alrex.parcool.client.gui.SettingActionLimitationScreen;
import com.alrex.parcool.client.input.KeyRecorder;
import com.alrex.parcool.common.action.Parkourability;
import com.alrex.parcool.config.ParCoolConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@OnlyIn(Dist.CLIENT)
public class OpenSettingsParCoolHandler {
	@SubscribeEvent
    public static void onTick(ClientTickEvent.Pre event) {

		if (KeyRecorder.keyOpenSettingsState.isPressed()) {
			Player player = Minecraft.getInstance().player;
			if (player == null) return;
			Parkourability parkourability = Parkourability.get(player);
			if (parkourability == null) return;
            Minecraft.getInstance().setScreen(new SettingActionLimitationScreen(Component.literal("ParCool Setting"), parkourability.getActionInfo(), ParCoolConfig.Client.GUIColorTheme.get()));
		}
	}
}
