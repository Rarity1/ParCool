package com.alrex.parcool.common.stamina;

import com.alrex.parcool.common.attachment.stamina.ReadonlyStamina;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public interface IParCoolStaminaHandler {
    public ReadonlyStamina initializeStamina(Player player, ReadonlyStamina current);

    public ReadonlyStamina consume(Player player, ReadonlyStamina current, int value);

    public ReadonlyStamina recover(Player player, ReadonlyStamina current, int value);

    public default ReadonlyStamina onTick(Player player, ReadonlyStamina current) {
        return current;
    }

    public default void processOnServer(Player player, int value) {
    }
}
