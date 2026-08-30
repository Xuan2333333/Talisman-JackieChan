package net.talisman.talismanjackiechan.client.renderer;

import net.talisman.talismanjackiechan.entity.TalismanPowerEntity;
import net.talisman.talismanjackiechan.client.model.Modelmodel_Converted;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class TalismanPowerRenderer extends EntityRenderer<TalismanPowerEntity> {
	private static final ResourceLocation texture = new ResourceLocation("talisman_jackiechan:textures/entities/45.png");
	private final Modelmodel_Converted model;

	public TalismanPowerRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelmodel_Converted(context.bakeLayer(Modelmodel_Converted.LAYER_LOCATION));
	}

	@Override
	public void render(TalismanPowerEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.setupAnim(entityIn, 0, 0, entityIn.tickCount + partialTicks, entityIn.getYRot(), entityIn.getXRot());
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(TalismanPowerEntity entity) {
		return texture;
	}
}