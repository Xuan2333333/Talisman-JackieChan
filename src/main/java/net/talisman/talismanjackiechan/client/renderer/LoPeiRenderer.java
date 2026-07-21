package net.talisman.talismanjackiechan.client.renderer;

import net.talisman.talismanjackiechan.entity.LoPeiEntity;
import net.talisman.talismanjackiechan.client.model.Modellpdx;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

public class LoPeiRenderer extends MobRenderer<LoPeiEntity, Modellpdx<LoPeiEntity>> {
	public LoPeiRenderer(EntityRendererProvider.Context context) {
		super(context, new Modellpdx<LoPeiEntity>(context.bakeLayer(Modellpdx.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(LoPeiEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getScale(), entity.getScale(), entity.getScale());
	}

	@Override
	public ResourceLocation getTextureLocation(LoPeiEntity entity) {
		return new ResourceLocation("talisman_jackiechan:textures/entities/lplplp4.png");
	}
}