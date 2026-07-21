
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.client.model.Model西木;
import net.talisman.talismanjackiechan.client.model.Modelshendu_Converted;
import net.talisman.talismanjackiechan.client.model.Modelmodel_Converted;
import net.talisman.talismanjackiechan.client.model.Modellplp;
import net.talisman.talismanjackiechan.client.model.Modellpdx;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class TalismanJackiechanModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelshendu_Converted.LAYER_LOCATION, Modelshendu_Converted::createBodyLayer);
		event.registerLayerDefinition(Model西木.LAYER_LOCATION, Model西木::createBodyLayer);
		event.registerLayerDefinition(Modellpdx.LAYER_LOCATION, Modellpdx::createBodyLayer);
		event.registerLayerDefinition(Modelmodel_Converted.LAYER_LOCATION, Modelmodel_Converted::createBodyLayer);
		event.registerLayerDefinition(Modellplp.LAYER_LOCATION, Modellplp::createBodyLayer);
	}
}