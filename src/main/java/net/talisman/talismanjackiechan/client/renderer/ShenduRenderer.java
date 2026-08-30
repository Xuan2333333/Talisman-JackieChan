package net.talisman.talismanjackiechan.client.renderer;

import net.talisman.talismanjackiechan.entity.ShenduEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

public class ShenduRenderer extends HumanoidMobRenderer<ShenduEntity, HumanoidModel<ShenduEntity>> {
	public ShenduRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel<ShenduEntity>(context.bakeLayer(ModelLayers.PLAYER)), 1f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(ShenduEntity entity) {
		return new ResourceLocation("talisman_jackiechan:textures/entities/shendu.png");
	}
}