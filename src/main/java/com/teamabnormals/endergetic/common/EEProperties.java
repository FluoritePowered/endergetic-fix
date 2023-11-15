package com.teamabnormals.endergetic.common;

import com.teamabnormals.endergetic.common.block.EetleEggBlock;
import com.teamabnormals.endergetic.core.registry.EESoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public final class EEProperties {
	public static final BlockBehaviour.Properties POISE_CLUSTER = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_PINK).noOcclusion().strength(0.15F);
	public static final BlockBehaviour.Properties POISE_WOOD = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2);
	public static final BlockBehaviour.Properties POISE_WOOD_NOT_SOLID = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).noOcclusion().strength(2, 10);
	public static final BlockBehaviour.Properties POISE_LOG_GLOWING = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2, 10).lightLevel((state) -> 15);
	public static final BlockBehaviour.Properties BOOF_BLOCK = BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.WOOL).strength(0.85F);
	public static final BlockBehaviour.Properties EUMUS = BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_PURPLE).strength(0.5F).sound(SoundType.GRAVEL);
	public static final BlockBehaviour.Properties EUMUS_POISMOSS = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_PURPLE).randomTicks().strength(0.6F).sound(SoundType.GRASS);
	public static final BlockBehaviour.Properties EUMUS_POISMOSS_PATH = BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_PURPLE).strength(0.6F).sound(SoundType.GRASS).isViewBlocking(EEProperties::blocksVision);
	public static final BlockBehaviour.Properties POISMOSS_PATH = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(3.0F, 9.0F).isViewBlocking(EEProperties::blocksVision);
	public static final BlockBehaviour.Properties EUMUS_BRICKS = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.STONE).strength(2, 30);
	public static final BlockBehaviour.Properties MYSTICAL_OBSIDIAN = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.PODZOL).strength(-1.0F, 3600000.0F).noLootTable();
	public static final BlockBehaviour.Properties ACIDIAN_LANTERN = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.PODZOL).strength(50F, 6000.0F).lightLevel(state -> 15).requiresCorrectToolForDrops();
	public static final BlockBehaviour.Properties BOLLOOM_CRATE = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(1.5F).sound(SoundType.WOOD);
	public static final BlockBehaviour.Properties EETLE_EGG = BlockBehaviour.Properties.of(Material.WATER_PLANT, MaterialColor.COLOR_PURPLE).lightLevel(state -> 7 + state.getValue(EetleEggBlock.SIZE)).strength(1.0F).sound(EESoundEvents.EESoundTypes.EETLE_EGG);
	public static final BlockBehaviour.Properties INFESTED_CORROCK = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).lightLevel(state -> 12).randomTicks().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);
	public static final BlockBehaviour.Properties PETRIFIED_INFESTED_CORROCK = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).lightLevel(state -> 12).strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);

	public static BlockBehaviour.Properties getCorrockBase(MaterialColor color, boolean isFullBlock) {
		return isFullBlock ? BlockBehaviour.Properties.of(Material.STONE, color).strength(1.5F, 6.0F) : BlockBehaviour.Properties.of(Material.WATER_PLANT, color).strength(0F).noCollission();
	}

	public static BlockBehaviour.Properties getGlowingCorrockBase(MaterialColor color) {
		return BlockBehaviour.Properties.of(Material.WATER_PLANT, color).lightLevel((state) -> 12).strength(0F).noCollission();
	}

	public static BlockBehaviour.Properties getPoiseGrass(boolean isPlant) {
		return !isPlant ? BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(3.0F, 9.0F).randomTicks() : BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_PURPLE).sound(SoundType.GRASS).strength(0F).noCollission();
	}

	public static BlockBehaviour.Properties getPoiseWood(boolean ticksRandomly, boolean doesNotBlockMovement) {
		Block.Properties poise = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2, 10);
		if (ticksRandomly) {
			poise.randomTicks();
		}
		if (doesNotBlockMovement) {
			poise.noCollission();
		}
		return poise;
	}

	public static BlockBehaviour.Properties getPuffBugHive(boolean hive) {
		return !hive ? BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.WOOL).noOcclusion().noCollission() : BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOL).requiresCorrectToolForDrops().strength(2.5F);
	}

	private static boolean blocksVision(BlockState state, BlockGetter access, BlockPos pos) {
		return true;
	}
}