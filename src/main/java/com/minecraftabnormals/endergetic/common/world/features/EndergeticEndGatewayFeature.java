package com.minecraftabnormals.endergetic.common.world.features;

import java.util.Random;
import java.util.function.Supplier;

import com.minecraftabnormals.endergetic.api.util.GenerationUtils;
import com.minecraftabnormals.endergetic.common.blocks.AcidianLanternBlock;
import com.minecraftabnormals.endergetic.core.registry.EEBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.EndGatewayConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

public class EndergeticEndGatewayFeature extends Feature<EndGatewayConfiguration> {
	private static final Supplier<BlockState> MYSTICAL_OBSIDIAN = () -> EEBlocks.MYSTICAL_OBSIDIAN.get().defaultBlockState();
	private static final Supplier<BlockState> MYSTICAL_OBSIDIAN_WALL = () -> EEBlocks.MYSTICAL_OBSIDIAN_WALL.get().defaultBlockState();
	private static final Supplier<BlockState> ACIDIAN_LANTERN = () -> EEBlocks.ACIDIAN_LANTERN.get().defaultBlockState();

	public EndergeticEndGatewayFeature(Codec<EndGatewayConfiguration> config) {
		super(config);
	}

	public boolean place(WorldGenLevel worldIn, ChunkGenerator generator, Random rand, BlockPos pos, EndGatewayConfiguration config) {
		if (!GenerationUtils.isAreaReplacable(worldIn, pos.getX() - 1, pos.getY() - 4, pos.getZ() - 1, pos.getX() + 1, pos.getY() + 4, pos.getZ() + 1))
			return false;

		for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-1, -2, -1), pos.offset(1, 2, 1))) {
			boolean flag = blockpos.getX() == pos.getX();
			boolean flag1 = blockpos.getY() == pos.getY();
			boolean flag2 = blockpos.getZ() == pos.getZ();
			boolean flag3 = Math.abs(blockpos.getY() - pos.getY()) == 2;
			if (flag && flag1 && flag2) {
				BlockPos blockpos1 = blockpos.immutable();
				worldIn.setBlock(blockpos1, Blocks.END_GATEWAY.defaultBlockState(), 2);
				config.getExit().ifPresent((p_214624_3_) -> {
					BlockEntity tileentity = worldIn.getBlockEntity(blockpos1);
					if (tileentity instanceof TheEndGatewayBlockEntity) {
						TheEndGatewayBlockEntity endgatewaytileentity = (TheEndGatewayBlockEntity) tileentity;
						endgatewaytileentity.setExitPosition(p_214624_3_, config.isExitExact());
						tileentity.setChanged();
					}
				});
			} else if (flag1) {
				worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
			} else if (flag3 && flag && flag2) {
				worldIn.setBlock(blockpos, MYSTICAL_OBSIDIAN.get(), 2);
			} else if ((flag || flag2) && !flag3) {
				worldIn.setBlock(blockpos, MYSTICAL_OBSIDIAN.get(), 2);
			} else {
				worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
			}
		}

		worldIn.setBlock(pos.below(3), MYSTICAL_OBSIDIAN_WALL.get(), 2);
		worldIn.setBlock(pos.above(3), MYSTICAL_OBSIDIAN_WALL.get(), 2);

		worldIn.setBlock(pos.north().east().above(), MYSTICAL_OBSIDIAN_WALL.get().setValue(WallBlock.SOUTH_WALL, WallSide.LOW).setValue(WallBlock.WEST_WALL, WallSide.LOW), 2);
		worldIn.setBlock(pos.north().west().above(), MYSTICAL_OBSIDIAN_WALL.get().setValue(WallBlock.SOUTH_WALL, WallSide.LOW).setValue(WallBlock.EAST_WALL, WallSide.LOW), 2);
		worldIn.setBlock(pos.south().east().above(), MYSTICAL_OBSIDIAN_WALL.get().setValue(WallBlock.NORTH_WALL, WallSide.LOW).setValue(WallBlock.WEST_WALL, WallSide.LOW), 2);
		worldIn.setBlock(pos.south().west().above(), MYSTICAL_OBSIDIAN_WALL.get().setValue(WallBlock.NORTH_WALL, WallSide.LOW).setValue(WallBlock.EAST_WALL, WallSide.LOW), 2);

		worldIn.setBlock(pos.above(4), ACIDIAN_LANTERN.get().setValue(AcidianLanternBlock.FACING, Direction.UP), 2);
		worldIn.setBlock(pos.below(4), ACIDIAN_LANTERN.get().setValue(AcidianLanternBlock.FACING, Direction.DOWN), 2);
		return true;
	}
}