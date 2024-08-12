package com.teamabnormals.endergetic.core.mixin.chorus;

import com.teamabnormals.endergetic.core.registry.other.tags.EEBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author SmellyModder (Luke Tonon)
 */
@Mixin(ChorusFlowerBlock.class)
public final class ChorusFlowerBlockMixin {

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 0), method = "canSurvive")
	private boolean canSurvive(BlockState state, Block block) {
		return state.is(EEBlockTags.CHORUS_PLANTABLE);
	}

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", ordinal = 0), method = "randomTick")
	private BlockState isEndStone(ServerLevel level, BlockPos pos) {
		BlockState blockState = level.getBlockState(pos);
		return blockState.is(EEBlockTags.CHORUS_PLANTABLE) ? Blocks.END_STONE.defaultBlockState() : blockState;
	}
}
