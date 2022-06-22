package com.minecraftabnormals.endergetic.common.entities.eetle.flying;

import com.minecraftabnormals.abnormals_core.client.ClientInfo;
import net.minecraft.util.math.MathHelper;

public final class FlyingRotations {
	private float prevFlyPitch, flyPitch;
	private float prevFlyRoll, flyRoll;
	private boolean looking;

	public void tick(TargetFlyingRotations targetRotations) {
		this.prevFlyPitch = this.flyPitch;
		this.prevFlyRoll = this.flyRoll;

		while (this.flyPitch - this.prevFlyPitch < -180.0F) {
			this.prevFlyPitch -= 360.0F;
		}

		while (this.flyPitch - this.prevFlyPitch >= 180.0F) {
			this.prevFlyPitch += 360.0F;
		}

		while (this.flyRoll - this.prevFlyRoll < -180.0F) {
			this.prevFlyRoll -= 360.0F;
		}

		while (this.flyRoll - this.prevFlyRoll >= 180.0F) {
			this.prevFlyRoll += 360.0F;
		}

		if (this.looking) {
			this.flyPitch = clampedRotate(this.flyPitch, targetRotations.getTargetFlyPitch(), 5.0F);
			this.flyRoll = clampedRotate(this.flyRoll, targetRotations.getTargetFlyRoll(), 5.0F);
			this.looking = false;
		} else {
			this.flyPitch = clampedRotate(this.flyPitch, 0.0F, 2.5F);
			this.flyRoll = clampedRotate(this.flyRoll, 0.0F, 2.5F);
		}
	}

	public void setLooking(boolean looking) {
		this.looking = looking;
	}

	public float getFlyPitch() {
		return this.flyPitch;
	}

	public float getFlyRoll() {
		return this.flyRoll;
	}

	public float getRenderFlyPitch() {
		return MathHelper.lerp(ClientInfo.getPartialTicks(), this.prevFlyPitch, this.flyPitch);
	}

	public float getRenderFlyRoll() {
		return MathHelper.lerp(ClientInfo.getPartialTicks(), this.prevFlyRoll, this.flyRoll);
	}

	public static float clampedRotate(float from, float to, float delta) {
		return from + MathHelper.clamp(MathHelper.degreesDifference(from, to), -delta, delta);
	}
}
