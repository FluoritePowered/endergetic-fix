package com.minecraftabnormals.endergetic.common.blocks;

import com.minecraftabnormals.endergetic.client.particles.EEParticles;
import com.minecraftabnormals.endergetic.client.particles.data.CorrockCrownParticleData;
import com.minecraftabnormals.endergetic.common.tileentities.CorrockCrownTileEntity;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particles.ParticleType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.function.Supplier;

public abstract class CorrockCrownBlock extends ContainerBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected final DimensionalType dimensionalType;
	public final boolean petrified;

	protected CorrockCrownBlock(Properties builder, DimensionalType dimensionalType, boolean petrified) {
		super(builder);
		this.dimensionalType = dimensionalType;
		this.petrified = petrified;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new CorrockCrownTileEntity();
	}

	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.FALSE), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.TRUE), 3);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public enum DimensionalType {
		OVERWORLD(EEParticles.OVERWORLD_CROWN),
		NETHER(EEParticles.NETHER_CROWN),
		END(EEParticles.END_CROWN);

		protected final Supplier<ParticleType<CorrockCrownParticleData>> particle;

		DimensionalType(Supplier<ParticleType<CorrockCrownParticleData>> particle) {
			this.particle = particle;
		}
	}
}