package com.alrex.parcool.client.animation;

import com.alrex.parcool.common.action.Parkourability;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public abstract class Animator {
	private int tick = 0;

    public void tick(Player player) {
		tick++;
	}

	protected int getTick() {
		return tick;
	}

	public abstract boolean shouldRemoved(Player player, Parkourability parkourability);

	/**
	 * @return You should return true if you want to cancel vanilla animation to control all about rendering
	 */

	 @OnlyIn(Dist.CLIENT)
	public boolean animatePre(
			Player player,
			Parkourability parkourability,
			PlayerModelTransformer transformer
	) {
		return false;
	}

	/**
	 * Called after vanilla animation is done
	 * You can utilize this to use partially vanilla animation
	 */
	@OnlyIn(Dist.CLIENT)
	public void animatePost(
			Player player,
			Parkourability parkourability,
			PlayerModelTransformer transformer
	) {
	}
	@OnlyIn(Dist.CLIENT)
    public boolean rotatePre(
            Player player,
            Parkourability parkourability,
            PlayerModelRotator rotator
    ) {
        return false;
    }
	@OnlyIn(Dist.CLIENT)
    public void rotatePost(
			Player player,
			Parkourability parkourability,
			PlayerModelRotator rotator
	) {
	}
	@OnlyIn(Dist.CLIENT)
	public void onCameraSetUp(
			ViewportEvent.ComputeCameraAngles event,
			Player clientPlayer,
			Parkourability parkourability
	) {
	}
	@OnlyIn(Dist.CLIENT)
	public void onRenderTick(
            RenderFrameEvent event,
			Player player,
			Parkourability parkourability
	) {
	}

}
