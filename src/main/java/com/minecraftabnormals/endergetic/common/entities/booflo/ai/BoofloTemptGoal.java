package com.minecraftabnormals.endergetic.common.entities.booflo.ai;

import java.util.EnumSet;

import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.endergetic.common.entities.booflo.BoofloEntity;
import com.minecraftabnormals.endergetic.common.entities.booflo.BoofloEntity.GroundMoveHelperController;
import com.minecraftabnormals.endergetic.core.registry.EEItems;

import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Mth;

import net.minecraft.world.entity.ai.goal.Goal.Flag;

public class BoofloTemptGoal extends Goal {
	private static final TargetingConditions SHOULD_FOLLOW = (new TargetingConditions()).range(10.0D).allowSameTeam().allowNonAttackable().allowInvulnerable();
	private BoofloEntity booflo;
	private Player tempter;

	public BoofloTemptGoal(BoofloEntity booflo) {
		this.booflo = booflo;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		this.tempter = this.booflo.level.getNearestPlayer(SHOULD_FOLLOW, this.booflo);
		if (this.tempter == null) {
			return false;
		} else {
			return !this.booflo.isTamed() && !this.booflo.hasCaughtFruit() && !this.booflo.isInLove() && this.booflo.getMoveControl() instanceof GroundMoveHelperController && !this.booflo.isBoofed() && this.booflo.isOnGround() && this.isTemptedBy(this.tempter.getMainHandItem()) || this.isTemptedBy(this.tempter.getOffhandItem());
		}
	}

	@Override
	public boolean canContinueToUse() {
		this.tempter = this.booflo.level.getNearestPlayer(SHOULD_FOLLOW, this.booflo);
		if (this.tempter == null) {
			return false;
		} else {
			return this.booflo.getMoveControl() instanceof GroundMoveHelperController && !this.booflo.isTamed() && !this.booflo.isInLove() && !this.booflo.isBoofed();
		}
	}

	@Override
	public void tick() {
		if (this.booflo.hopDelay == 0 && this.booflo.isNoEndimationPlaying()) {
			NetworkUtil.setPlayingAnimationMessage(this.booflo, BoofloEntity.HOP);
		}

		if (this.booflo.getMoveControl() instanceof GroundMoveHelperController) {
			((GroundMoveHelperController) this.booflo.getMoveControl()).setSpeed(1.0D);
		}

		double dx = this.tempter.getX() - this.booflo.getX();
		double dz = this.tempter.getZ() - this.booflo.getZ();

		float angle = (float) (Mth.atan2(dz, dx) * (double) (180F / Math.PI)) - 90.0F;

		if (this.booflo.getMoveControl() instanceof GroundMoveHelperController) {
			((GroundMoveHelperController) this.booflo.getMoveControl()).setDirection(angle, false);
		}

		this.booflo.getNavigation().moveTo(this.booflo, 1.0D);
	}

	@Override
	public void stop() {
		this.tempter = null;
	}

	private boolean isTemptedBy(ItemStack stack) {
		return stack.getItem() == EEItems.BOLLOOM_FRUIT.get();
	}
}