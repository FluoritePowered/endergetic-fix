package com.teamabnormals.endergetic.core.data.server.tags;

import com.teamabnormals.endergetic.core.EndergeticExpansion;
import com.teamabnormals.endergetic.core.registry.EEBiomes;
import com.teamabnormals.endergetic.core.registry.other.tags.EEBiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public final class EEBiomeTagsProvider extends BiomeTagsProvider {

	public EEBiomeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, EndergeticExpansion.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(BiomeTags.IS_END).add(EEBiomes.POISE_FOREST.get());
		this.tag(Tags.Biomes.IS_COLD).add(EEBiomes.POISE_FOREST.get());
		this.tag(Tags.Biomes.IS_COLD_END).add(EEBiomes.POISE_FOREST.get());
		this.tag(Tags.Biomes.IS_SPARSE).add(EEBiomes.POISE_FOREST.get());
		this.tag(Tags.Biomes.IS_SPARSE_END).add(EEBiomes.POISE_FOREST.get());
		this.tag(Tags.Biomes.IS_MAGICAL).add(EEBiomes.POISE_FOREST.get());
		this.tag(EEBiomeTags.HAS_EETLE_NEST);
	}

}
