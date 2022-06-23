package com.minecraftabnormals.endergetic.client.renderers.entity;

import com.minecraftabnormals.endergetic.client.models.PoiseClusterModel;
import com.minecraftabnormals.endergetic.common.entities.PoiseClusterEntity;
import com.minecraftabnormals.endergetic.core.EndergeticExpansion;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class PoiseClusterRender extends LivingEntityRenderer<PoiseClusterEntity, PoiseClusterModel<PoiseClusterEntity>> {

	public PoiseClusterRender(EntityRenderDispatcher renderManager) {
		super(renderManager, new PoiseClusterModel<>(), 0.0F);
	}

	@Override
	protected RenderType getRenderType(PoiseClusterEntity cluster, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		return RenderType.entityTranslucent(this.getTextureLocation(cluster));
	}

	@Override
	public ResourceLocation getTextureLocation(PoiseClusterEntity entity) {
		return new ResourceLocation(EndergeticExpansion.MOD_ID, "textures/entity/poise_cluster.png");
	}

	protected boolean shouldShowName(PoiseClusterEntity entity) {
		return false;
	}

}