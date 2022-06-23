package com.minecraftabnormals.endergetic.core.mixin;

import com.minecraftabnormals.endergetic.common.entities.bolloom.BolloomBalloonEntity;
import com.minecraftabnormals.endergetic.core.interfaces.BalloonHolder;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public abstract class ClientWorldMixin {

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isPassenger()Z"), method = "tickEntities")
	private boolean shouldNotTick(Entity entity) {
		if (entity.isPassenger() || entity instanceof BolloomBalloonEntity && ((BolloomBalloonEntity) entity).isAttachedToEntity()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;inChunk:Z", ordinal = 1, shift = At.Shift.AFTER), method = "tickNonPassenger")
	private void updateBalloons(Entity entity, CallbackInfo info) {
		BalloonHolder balloonHolder = (BalloonHolder) entity;
		ClientChunkCache chunkProvider = ((ClientLevel) (Object) this).getChunkSource();
		for (BolloomBalloonEntity balloon : balloonHolder.getBalloons()) {
			if (!balloon.removed && balloon.getAttachedEntity() == entity) {
				if (chunkProvider.isEntityTickingChunk(balloon)) {
					balloon.setPosAndOldPos(balloon.getX(), balloon.getY(), balloon.getZ());
					balloon.yRotO = balloon.yRot;
					balloon.xRotO = balloon.xRot;
					if (balloon.inChunk) {
						balloon.tickCount++;
						balloon.updateAttachedPosition();
					}
					this.callUpdateChunkPos(balloon);
				}
			} else {
				balloon.detachFromEntity();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;rideTick()V", shift = At.Shift.AFTER), method = "tickPassenger")	private void updateEntityRiddenBalloons(Entity ridingEntity, Entity passenger, CallbackInfo info) {
		BalloonHolder balloonHolder = (BalloonHolder) passenger;
		ClientChunkCache chunkProvider = ((ClientLevel) (Object) this).getChunkSource();
		for (BolloomBalloonEntity balloon : balloonHolder.getBalloons()) {
			if (!balloon.removed && balloon.getAttachedEntity() == passenger) {
				if (chunkProvider.isEntityTickingChunk(balloon)) {
					balloon.setPosAndOldPos(balloon.getX(), balloon.getY(), balloon.getZ());
					balloon.yRotO = balloon.yRot;
					balloon.xRotO = balloon.xRot;
					if (balloon.inChunk) {
						balloon.tickCount++;
						balloon.updateAttachedPosition();
					}
					this.callUpdateChunkPos(balloon);
				}
			} else {
				balloon.detachFromEntity();
			}
		}
	}

	@Invoker
	public abstract void callUpdateChunkPos(Entity entity);

}
