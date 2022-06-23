package com.minecraftabnormals.endergetic.common.world.features;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.minecraftabnormals.endergetic.common.entities.puffbug.PuffBugEntity;
import com.minecraftabnormals.endergetic.core.registry.EEBlocks;
import com.minecraftabnormals.endergetic.core.registry.EEEntities;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PuffBugHiveFeature extends Feature<NoneFeatureConfiguration> {

	private Supplier<BlockState> HIVE_STATE(boolean hanger) {
		return hanger ? () -> EEBlocks.HIVE_HANGER.get().defaultBlockState() : () -> EEBlocks.PUFFBUG_HIVE.get().defaultBlockState();
	}

	public PuffBugHiveFeature(Codec<NoneFeatureConfiguration> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(WorldGenLevel world, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
		if (rand.nextFloat() < 0.1F) return false;
		BlockPos hivePos = pos.below();

		if (world.getBlockState(pos.above()).getBlock() == EEBlocks.POISE_STEM.get() || world.getBlockState(pos.above()).getBlock() == EEBlocks.GLOWING_POISE_STEM.get()) {
			if (world.getBlockState(pos).getMaterial().isReplaceable() && world.getBlockState(pos).getMaterial().isReplaceable()) {
				world.setBlock(pos, this.HIVE_STATE(true).get(), 2);
				world.setBlock(hivePos, this.HIVE_STATE(false).get(), 2);
				this.spawnPuffBugs(world, hivePos, rand);
				return true;
			}
		} else {
			if (world.getBlockState(pos.above()).getBlock() == EEBlocks.POISE_CLUSTER.get() && world.getMaxBuildHeight() > 90) {
				if (world.getBlockState(pos).getMaterial().isReplaceable() && world.getBlockState(pos).getMaterial().isReplaceable()) {
					world.setBlock(pos, this.HIVE_STATE(true).get(), 2);
					world.setBlock(hivePos, this.HIVE_STATE(false).get(), 2);
					this.spawnPuffBugs(world, hivePos, rand);
					return true;
				}
			}
		}
		return false;
	}

	private void spawnPuffBugs(WorldGenLevel world, BlockPos pos, Random rand) {
		int maxPuffBugs = rand.nextInt(4) + 2;

		List<Direction> openSides = this.getOpenSides(world, pos);
		for (Direction openSide : openSides) {
			BlockPos offset = pos.relative(openSide);
			PuffBugEntity puffbug = EEEntities.PUFF_BUG.get().create(world.getLevel());
			if (puffbug != null) {
				puffbug.moveTo(offset.getX() + 0.5F, offset.getY() + 0.5F, offset.getZ() + 0.5F, 0.0F, 0.0F);
				puffbug.finalizeSpawn(world, world.getCurrentDifficultyAt(pos), MobSpawnType.STRUCTURE, null, null);
				puffbug.setHivePos(pos);
				world.addFreshEntity(puffbug);
			}
			if (maxPuffBugs-- <= 0) break;
		}
	}

	private List<Direction> getOpenSides(LevelAccessor world, BlockPos pos) {
		List<Direction> openDirections = Lists.newArrayList();
		for (Direction directions : Direction.values()) {
			if (directions != Direction.UP) {
				BlockPos offsetPos = pos.relative(directions);
				if (world.isEmptyBlock(offsetPos) && world.isEmptyBlock(offsetPos.above())) {
					openDirections.add(directions);
				}
			}
		}
		return openDirections;
	}

}
