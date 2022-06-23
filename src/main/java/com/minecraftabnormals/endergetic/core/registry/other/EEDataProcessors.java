package com.minecraftabnormals.endergetic.core.registry.other;

import com.google.common.collect.Maps;
import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.*;
import com.minecraftabnormals.endergetic.common.entities.bolloom.BalloonOrder;
import com.minecraftabnormals.endergetic.core.EndergeticExpansion;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.Constants;

import java.util.Map;
import java.util.UUID;

public final class EEDataProcessors {
	private static final IDataProcessor<Map<UUID, BalloonOrder>> ORDER_PROCESSOR = new IDataProcessor<Map<UUID, BalloonOrder>>() {

		@Override
		public CompoundTag write(Map<UUID, BalloonOrder> map) {
			ListTag entries = new ListTag();
			map.forEach((uuid, balloonOrder) -> {
				CompoundTag entry = new CompoundTag();
				entry.putUUID("UUID", uuid);
				entry.putInt("Order", balloonOrder.ordinal());
				entries.add(entry);
			});
			CompoundTag compound = new CompoundTag();
			compound.put("Entries", entries);
			return compound;
		}

		@Override
		public Map<UUID, BalloonOrder> read(CompoundTag compound) {
			Map<UUID, BalloonOrder> map = Maps.newHashMap();
			compound.getList("Entries", Constants.NBT.TAG_COMPOUND).forEach(nbt -> {
				CompoundTag entry = (CompoundTag) nbt;
				if (entry.contains("UUID", Constants.NBT.TAG_INT_ARRAY) && entry.contains("Order", Constants.NBT.TAG_INT)) {
					map.put(entry.getUUID("UUID"), BalloonOrder.byOrdinal(entry.getInt("Order")));
				}
			});
			return map;
		}

	};

	public static final TrackedData<Map<UUID, BalloonOrder>> ORDER_DATA = TrackedData.Builder.create(ORDER_PROCESSOR, Maps::newHashMap).build();
	public static final TrackedData<Integer> CATCHING_COOLDOWN = TrackedData.Builder.create(DataProcessors.INT, () -> 0).setSyncType(SyncType.NOPE).build();

	public static void registerTrackedData() {
		TrackedDataManager.INSTANCE.registerData(new ResourceLocation(EndergeticExpansion.MOD_ID, "ballooon_order"), ORDER_DATA);
		TrackedDataManager.INSTANCE.registerData(new ResourceLocation(EndergeticExpansion.MOD_ID, "catching_cooldown"), CATCHING_COOLDOWN);
	}
}
