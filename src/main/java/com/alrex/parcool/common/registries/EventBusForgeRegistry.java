package com.alrex.parcool.common.registries;

import com.alrex.parcool.client.hud.HUDManager;
import com.alrex.parcool.client.input.KeyRecorder;
import com.alrex.parcool.common.action.ActionProcessor;
import com.alrex.parcool.common.handlers.*;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;

public class EventBusForgeRegistry {
    
	public static void register(IEventBus bus) {
            bus.register(KeyRecorder.class);
            bus.register(OpenSettingsParCoolHandler.class);
            bus.register(EnableOrDisableParCoolHandler.class);
            bus.register(HUDManager.getInstance());
	}

}
