package net.talisman.talismanjackiechan.client.renderer;

import net.talisman.talismanjackiechan.entity.HsiWuEntity;
import net.talisman.talismanjackiechan.client.model.Modelhsiwu;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class HsiWuRenderer extends MobRenderer<HsiWuEntity, Modelhsiwu<HsiWuEntity>> {
	public HsiWuRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelhsiwu<HsiWuEntity>(context.bakeLayer(Modelhsiwu.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(HsiWuEntity entity) {
		return new ResourceLocation("talisman_jackiechan:textures/entities/ximu.png");
	}
}