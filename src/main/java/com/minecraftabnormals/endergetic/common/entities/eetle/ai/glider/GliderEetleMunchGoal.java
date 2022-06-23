package com.minecraftabnormals.endergetic.common.entities.eetle.ai.glider;

import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatedGoal;
import com.minecraftabnormals.endergetic.common.entities.eetle.GliderEetleEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;

public class GliderEetleMunchGoal extends EndimatedGoal<GliderEetleEntity> {
	private int munchCooldown;

	public GliderEetleMunchGoal(GliderEetleEntity glider) {
		super(glider, GliderEetleEntity.MUNCH);
	}

	@Override
	public boolean canUse() {
		if (this.munchCooldown > 0) {
			this.munchCooldown--;
		} else if (this.entity.getRandom().nextFloat() < 0.05F) {
			LivingEntity attackTarget = this.entity.getTarget();
			if (attackTarget != null) {
				return this.entity.getPassengers().contains(attackTarget) && this.entity.isFlying() && this.isNoEndimationPlaying();
			}
		}
		return false;
	}

	@Override
	public void start() {
		this.playEndimation();
	}

	@Override
	public boolean canContinueToUse() {
		return this.isEndimationPlaying() && this.entity.isFlying();
	}

	@Override
	public void tick() {
		LivingEntity attackTarget = this.entity.getTarget();
		if (attackTarget != null && this.entity.getPassengers().contains(attackTarget)) {
			if (this.isEndimationAtTick(8) || this.isEndimationAtTick(18)) {
				attackTarget.hurt(causeMunchDamage(this.entity), (float) this.entity.getAttributeValue(Attributes.ATTACK_DAMAGE) + attackTarget.getRandom().nextInt(2));
			}
		}
	}

	@Override
	public void stop() {
		this.munchCooldown = this.entity.getRandom().nextInt(41) + 20;
	}

	public static DamageSource causeMunchDamage(GliderEetleEntity glider) {
		return new EntityDamageSource("endergetic.munch", glider);
	}
}
