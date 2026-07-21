
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.client.renderer.TalismanPowerRenderer;
import net.talisman.talismanjackiechan.client.renderer.ShenduRenderer;
import net.talisman.talismanjackiechan.client.renderer.PufferPowerRenderer;
import net.talisman.talismanjackiechan.client.renderer.LoPeiRenderer;
import net.talisman.talismanjackiechan.client.renderer.HsiWuRenderer;
import net.talisman.talismanjackiechan.client.renderer.EvilselfRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TalismanJackiechanModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TalismanJackiechanModEntities.TALISMAN_POWER.get(), TalismanPowerRenderer::new);
		event.registerEntityRenderer(TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(TalismanJackiechanModEntities.PUFFER_POWER.get(), PufferPowerRenderer::new);
		event.registerEntityRenderer(TalismanJackiechanModEntities.EVILSELF.get(), EvilselfRenderer::new);
		event.registerEntityRenderer(TalismanJackiechanModEntities.LO_PEI.get(), LoPeiRenderer::new);
		event.registerEntityRenderer(TalismanJackiechanModEntities.HSI_WU.get(), HsiWuRenderer::new);
		event.registerEntityRenderer(TalismanJackiechanModEntities.SHENDU.get(), ShenduRenderer::new);
	}
}