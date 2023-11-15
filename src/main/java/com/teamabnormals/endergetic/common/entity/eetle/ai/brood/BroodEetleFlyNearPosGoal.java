package com.teamabnormals.endergetic.common.entity.eetle.ai.brood;

import com.teamabnormals.endergetic.common.entity.eetle.BroodEetle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BroodEetleFlyNearPosGoal extends Goal {
	private final BroodEetle broodEetle;
	private double x;
	private double y;
	private double z;

	public BroodEetleFlyNearPosGoal(BroodEetle broodEetle) {
		this.broodEetle = broodEetle;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		BroodEetle broodEetle = this.broodEetle;
		if (broodEetle.isFlying() && broodEetle.hasWokenUp() && broodEetle.getRandom().nextFloat() < 0.1F) {
			BlockPos takeoffPos = broodEetle.takeoffPos;
			if (takeoffPos != null) {
				Vec3 vector3d = findPos(broodEetle, Vec3.atCenterOf(broodEetle.takeoffPos));
				if (vector3d == null) {
					return false;
				} else {
					this.x = vector3d.x;
					this.y = vector3d.y;
					this.z = vector3d.z;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void start() {
		this.broodEetle.getNavigation().moveTo(this.x, this.y, this.z, 1.0F);
	}

	@Override
	public boolean canContinueToUse() {
		return !this.broodEetle.getNavigation().isDone();
	}

	@Override
	public void stop() {
		this.broodEetle.getNavigation().stop();
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Nullable
	private static Vec3 findPos(BroodEetle broodEetle, Vec3 takeoffPos) {
		boolean verticalFarAway = Math.abs(broodEetle.getY() - takeoffPos.y()) >= 6;
		return DefaultRandomPos.getPosTowards(broodEetle, 8, verticalFarAway ? 0 : 6, takeoffPos, ((float) Math.PI / 2.0F));
	}
}
