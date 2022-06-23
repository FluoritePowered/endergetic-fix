package com.minecraftabnormals.endergetic.common.network.entity.booflo;

import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.endergetic.common.entities.booflo.BoofloEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Message that requests the server to slam the Booflo the sending player is riding.
 *
 * @author SmellyModder (Luke Tonon)
 */
public final class C2SSlamMessage {

	public final void serialize(FriendlyByteBuf buf) {
	}

	public static C2SSlamMessage deserialize(FriendlyByteBuf buf) {
		return new C2SSlamMessage();
	}

	public static void handle(C2SSlamMessage message, Supplier<NetworkEvent.Context> ctx) {
		NetworkEvent.Context context = ctx.get();
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				Player player = context.getSender();
				if (player != null) {
					Entity ridingEntity = player.getVehicle();
					if (ridingEntity instanceof BoofloEntity) {
						BoofloEntity booflo = (BoofloEntity) ridingEntity;
						if (booflo.isBoofed() && booflo.getBoostPower() <= 0 && booflo.isNoEndimationPlaying()) {
							NetworkUtil.setPlayingAnimationMessage(booflo, BoofloEntity.CHARGE);
							booflo.setBoostExpanding(true);
							booflo.setBoostLocked(true);
						}
					}
				}
			});
			context.setPacketHandled(true);
		}
	}

}