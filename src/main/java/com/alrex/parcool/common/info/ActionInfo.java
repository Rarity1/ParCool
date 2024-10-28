package com.alrex.parcool.common.info;

import com.alrex.parcool.common.action.Action;
import com.alrex.parcool.common.stamina.LocalStamina;
import com.alrex.parcool.common.stamina.StaminaType;
import com.alrex.parcool.config.ParCoolConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ActionInfo {
    public ActionInfo() {
    }

    public ClientSetting getClientSetting() {
        return clientSetting;
    }

    public void setClientSetting(ClientSetting clientSetting) {
        this.clientSetting = clientSetting;
	}

    private ClientSetting clientSetting = ClientSetting.UNSYNCED_INSTANCE;

    public ServerLimitation getServerLimitation() {
        return serverLimitation;
	}

    public void setServerLimitation(ServerLimitation serverLimitation) {
        this.serverLimitation = serverLimitation;
    }

    private ServerLimitation serverLimitation = ServerLimitation.UNSYNCED_INSTANCE;

	public boolean can(Class<? extends Action> action) {
        return getClientSetting().get(ParCoolConfig.Client.Booleans.ParCoolIsActive)
                && getClientSetting().getPossibilityOf(action)
                && getServerLimitation().isPermitted(action);
	}

    public StaminaType getStaminaType() {
        var forcedStamina = getServerLimitation().getForcedStamina();
        if (forcedStamina == StaminaType.NONE) {
            var requestedStamina = getClientSetting().getRequestedStamina();
            if (requestedStamina == StaminaType.NONE) {
                return isInfiniteStaminaPermitted() ? StaminaType.NONE : StaminaType.PARCOOL;
            }
            return requestedStamina;
        }
        return forcedStamina;
    }

	public int getStaminaConsumptionOf(Class<? extends Action> action) {
        return Math.max(
                getClientSetting().getStaminaConsumptionOf(action),
                getServerLimitation().getStaminaConsumptionOf(action)
        );
	}

    public int getStaminaRecoveryLimit() {
        return getServerLimitation().get(ParCoolConfig.Server.Integers.MaxStaminaRecovery);
	}

    public int getMaxStaminaLimit() {
        return getServerLimitation().get(ParCoolConfig.Server.Integers.MaxStaminaLimit);
	}

    @OnlyIn(Dist.CLIENT)
    public boolean isStaminaInfinite() {
        var stamina = LocalStamina.get();
        if (stamina == null) return false;
        return stamina.isInfinite();
	}

	public boolean isInfiniteStaminaPermitted() {
        return serverLimitation.get(ParCoolConfig.Server.Booleans.AllowInfiniteStamina);
	}

    @OnlyIn(Dist.CLIENT)
    public void updateStaminaType() {
        var stamina = LocalStamina.get();
        if (stamina == null) return;
        stamina.changeType(getStaminaType());
    }
}
