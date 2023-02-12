package com.alrex.parcool.common.action.impl;

import com.alrex.parcool.ParCoolConfig;
import com.alrex.parcool.client.animation.impl.CrawlAnimator;
import com.alrex.parcool.client.animation.impl.SlidingAnimator;
import com.alrex.parcool.client.input.KeyRecorder;
import com.alrex.parcool.common.action.Action;
import com.alrex.parcool.common.capability.Animation;
import com.alrex.parcool.common.capability.Parkourability;
import com.alrex.parcool.common.capability.Stamina;
import com.alrex.parcool.utilities.EntityUtil;
import com.alrex.parcool.utilities.VectorUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;

import java.nio.ByteBuffer;

public class Slide extends Action {
	private Vector3d slidingVec = null;

	@Override
	public boolean canStart(PlayerEntity player, Parkourability parkourability, Stamina stamina, ByteBuffer startInfo) {
		return (parkourability.getPermission().canCrawl()
				&& KeyRecorder.keyCrawlState.isPressed()
				&& !parkourability.getRoll().isDoing()
				&& !parkourability.getTap().isDoing()
				&& !player.isInWaterOrBubble()
				&& (player.isOnGround() || !ParCoolConfig.CONFIG_CLIENT.disableCrawlInAir.get())
				&& parkourability.getFastRun().getDashTick(parkourability.getAdditionalProperties()) > 5
		);
	}

	@Override
	public boolean canContinue(PlayerEntity player, Parkourability parkourability, Stamina stamina) {
		return getDoingTick() < parkourability.getActionInfo().getMaxSlidingTick()
				&& parkourability.getCrawl().isDoing();
	}

	@Override
	public void onStartInLocalClient(PlayerEntity player, Parkourability parkourability, Stamina stamina, ByteBuffer startData) {
		slidingVec = player.getLookAngle().multiply(1, 0, 1).normalize();
		Animation animation = Animation.get(player);
		if (animation != null) {
			animation.setAnimator(new SlidingAnimator());
		}
	}

	@Override
	public void onStartInOtherClient(PlayerEntity player, Parkourability parkourability, ByteBuffer startData) {
		Animation animation = Animation.get(player);
		if (animation != null && !animation.hasAnimator()) {
			animation.setAnimator(new SlidingAnimator());
		}
	}

	@Override
	public void onWorkingTickInClient(PlayerEntity player, Parkourability parkourability, Stamina stamina) {
		if (player.isOnGround()) {
			Vector3d vec = slidingVec.scale(0.2);
			EntityUtil.addVelocity(player, vec);
		}
	}

	@Override
	public void onStopInLocalClient(PlayerEntity player) {
		Animation animation = Animation.get(player);
		if (animation != null && !animation.hasAnimator()) {
			animation.setAnimator(new CrawlAnimator());
		}
	}

	@Override
	public void onStopInOtherClient(PlayerEntity player) {
		Animation animation = Animation.get(player);
		if (animation != null && !animation.hasAnimator()) {
			animation.setAnimator(new CrawlAnimator());
		}
	}

	@Override
	public void onRenderTick(TickEvent.RenderTickEvent event, PlayerEntity player, Parkourability parkourability) {
		if (slidingVec == null || !isDoing()) return;
		player.yRot = (float) VectorUtil.toYawDegree(slidingVec);
	}

	@Override
	public void restoreSynchronizedState(ByteBuffer buffer) {
	}

	@Override
	public void saveSynchronizedState(ByteBuffer buffer) {
	}
}
