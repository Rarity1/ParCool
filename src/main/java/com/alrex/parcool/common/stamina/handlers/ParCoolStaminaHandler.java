package com.alrex.parcool.common.stamina.handlers;

import com.alrex.parcool.api.Attributes;
import com.alrex.parcool.common.action.Parkourability;
import com.alrex.parcool.common.attachment.stamina.ReadonlyStamina;
import com.alrex.parcool.common.stamina.IParCoolStaminaHandler;
import net.minecraft.world.entity.player.Player;



public class ParCoolStaminaHandler implements IParCoolStaminaHandler {
    private int recoveryCoolDown = 0;

    @Override
    public ReadonlyStamina initializeStamina(Player player, ReadonlyStamina current) {
        return current;
    }

    @Override
    public ReadonlyStamina consume(Player player, ReadonlyStamina current, int value) {
        recoveryCoolDown = 30;
        return current.consumed(value);
    }

    @Override
    public ReadonlyStamina recover(Player player, ReadonlyStamina current, int value) {
        return current.recovered(value);
    }

    @Override
    public ReadonlyStamina onTick(Player player, ReadonlyStamina current) {
        if (recoveryCoolDown > 0) {
            recoveryCoolDown--;
        }
        current = current.updateMax(player);
        if (recoveryCoolDown <= 0) {
            var parkourability = Parkourability.get(player);
            if (parkourability == null) return current;
            var attr = player.getAttribute(Attributes.STAMINA_RECOVERY);
            if (attr == null) return current;
            int recoverValue = (int) Math.min(parkourability.getActionInfo().getStaminaRecoveryLimit(), attr.getValue());
            if (player.onGround()) {
                current = current.recovered(recoverValue);
            } else {
                current = current.recovered(recoverValue / 5);
            }
        }
        return current;
    }
}
