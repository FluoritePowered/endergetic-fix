package com.minecraftabnormals.endergetic.client.renderers.entity;

import com.minecraftabnormals.endergetic.client.EERenderTypes;
import com.minecraftabnormals.endergetic.client.models.purpoid.PurpoidModel;
import com.minecraftabnormals.endergetic.client.renderers.entity.layer.EmissiveLayerRenderer;
import com.minecraftabnormals.endergetic.client.renderers.entity.layer.PurpoidGelLayer;
import com.minecraftabnormals.endergetic.common.entities.purpoid.PurpoidEntity;
import com.minecraftabnormals.endergetic.core.EndergeticExpansion;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;

public class PurpoidRenderer extends MobRenderer<PurpoidEntity, PurpoidModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EndergeticExpansion.MOD_ID, "textures/entity/purpoid/purpoid.png");
	public static final ResourceLocation EMISSIVE_TEXTURE = new ResourceLocation(EndergeticExpansion.MOD_ID, "textures/entity/purpoid/purpoid_emissive.png");

	public PurpoidRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PurpoidModel(), 0.6F);
		this.addLayer(new EmissiveLayerRenderer<>(this, EMISSIVE_TEXTURE));
		this.addLayer(new PurpoidGelLayer(this));
	}

	@Override
	public void render(PurpoidEntity purpoid, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		this.shadowRadius = 0.6F * purpoid.getSize().getScale();
		super.render(purpoid, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	protected void scale(PurpoidEntity purpoid, MatrixStack matrixStack, float partialTickTime) {
		float scale = purpoid.getSize().getScale();
		matrixStack.scale(scale, scale, scale);
	}

	@Override
	protected void setupRotations(PurpoidEntity purpoid, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		Entity ridingEntity = purpoid.getVehicle();
		if (ridingEntity != null) {
			if (ridingEntity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) ridingEntity;
				matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - MathHelper.rotLerp(partialTicks, livingEntity.yHeadRotO, livingEntity.yHeadRot)));
			} else {
				matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
			}
		} else {
			Vector3d pull = purpoid.getPull(partialTicks);

			double pulledX = MathHelper.lerp(partialTicks, purpoid.xo, purpoid.getX()) - pull.x();
			double pulledY = MathHelper.lerp(partialTicks, purpoid.yo, purpoid.getY()) - pull.y();
			double pulledZ = MathHelper.lerp(partialTicks, purpoid.zo, purpoid.getZ()) - pull.z();

			float rotationOffset = 0.5F * purpoid.getSize().getScale();
			float yRot = (float) MathHelper.atan2(pulledZ, pulledX);
			matrixStack.translate(0.0F, rotationOffset, 0.0F);
			matrixStack.mulPose(Vector3f.YP.rotation(-yRot));
			matrixStack.mulPose(Vector3f.ZP.rotation((float) ((MathHelper.atan2(MathHelper.sqrt(pulledX * pulledX + pulledZ * pulledZ), -pulledY)) - Math.PI)));
			matrixStack.mulPose(Vector3f.YP.rotation(yRot));
			matrixStack.translate(0.0F, -rotationOffset, 0.0F);
		}
		if (purpoid.hasCustomName()) {
			String name = TextFormatting.stripFormatting(purpoid.getName().getString());
			if (name.equals("Dinnerbone") || name.equals("Grumm")) {
				matrixStack.translate(0.0D, purpoid.getBbHeight() + 0.1D, 0.0D);
				matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
			}
		}
	}

	@Nullable
	@Override
	protected RenderType getRenderType(PurpoidEntity purpoidEntity, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
		ResourceLocation texture = this.getTextureLocation(purpoidEntity);
		if (p_230496_3_) {
			return RenderType.itemEntityTranslucentCull(texture);
		} else if (p_230496_2_) {
			return EERenderTypes.createUnshadedEntity(texture);
		} else {
			return p_230496_4_ ? RenderType.outline(texture) : null;
		}
	}

	@Override
	public ResourceLocation getTextureLocation(PurpoidEntity entity) {
		return TEXTURE;
	}
}
