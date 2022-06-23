package com.minecraftabnormals.endergetic.common.blocks;

import java.util.Random;

import com.minecraftabnormals.endergetic.common.tileentities.EnderCampfireTileEntity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EnderCampfireBlock extends CampfireBlock {

	public EnderCampfireBlock(Properties properties) {
		super(false, 3, properties);
	}

	public BlockEntity newBlockEntity(BlockGetter worldIn) {
		return new EnderCampfireTileEntity();
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		if (stateIn.getValue(LIT)) {
			if (rand.nextInt(10) == 0) {
				worldIn.playLocalSound((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + rand.nextFloat(),
						rand.nextFloat() * 0.7F + 0.6F, false);
			}
		}
	}
}
