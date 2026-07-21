package net.talisman.talismanjackiechan.client.renderer;

import net.talisman.talismanjackiechan.entity.HsiWuEntity;
import net.talisman.talismanjackiechan.client.model.Model西木;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HsiWuRenderer extends MobRenderer<HsiWuEntity, Model西木<HsiWuEntity>> {
	public HsiWuRenderer(EntityRendererProvider.Context context) {
		super(context, new Model西木<HsiWuEntity>(context.bakeLayer(Model西木.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(HsiWuEntity entity) {
		return new ResourceLocation("talisman_jackiechan:textures/entities/ximu.png");
	}
}