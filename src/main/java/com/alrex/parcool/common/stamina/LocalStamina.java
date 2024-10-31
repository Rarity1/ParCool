package com.alrex.parcool.common.stamina;

import com.alrex.parcool.common.attachment.Attachments;
import com.alrex.parcool.common.stamina.handlers.InfiniteStaminaHandler;

import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class LocalStamina {
    
    @Nullable
    private static LocalStamina instance = null;

    public static void setup(Player player){
        instance = new LocalStamina(player);
    }
    public static void unload(){
        instance = null;
    }

    public static LocalStamina get(){
        return instance;
    }

    public LocalStamina(Player player) {
        this.player = player;
    }

    private final Player player;
    @Nullable
    private StaminaType currentType = null;
    @Nullable
    private IParCoolStaminaHandler handler = null;


    public boolean isAvailable() {

        return handler != null && currentType != null;
    }

    public boolean isInfinite() {

        return player.isCreative() || player.isSpectator() || handler instanceof InfiniteStaminaHandler;
    }

    public void changeType(StaminaType type) {
        currentType = type;
        handler = type.newHandler();
        player.setData(Attachments.STAMINA, handler.initializeStamina(player, player.getData(Attachments.STAMINA)));
    }

    public boolean isExhausted() {
        return player.getData(Attachments.STAMINA).isExhausted();
    }

    public int getValue() {
        return player.getData(Attachments.STAMINA).value();
    }

    public int getMax() {
        return player.getData(Attachments.STAMINA).max();
    }

    public void consume(int value) {
        if (player.isCreative() || player.isSpectator()) return;
        if (handler == null) return;
        player.setData(
                Attachments.STAMINA,
                handler.consume(player, player.getData(Attachments.STAMINA), value)
        );
    }

    public void recover(int value) {
        if (player.isCreative() || player.isSpectator()) return;
        if (handler == null) return;
        player.setData(
                Attachments.STAMINA,
                handler.recover(player, player.getData(Attachments.STAMINA), value)
        );
    }

    public void onTick() {
        if (handler == null) return;
        player.setData(
                Attachments.STAMINA,
                handler.onTick(player, player.getData(Attachments.STAMINA))
        );
    }

    public void sync() {
        player.getData(Attachments.STAMINA).sync();
    }
}
