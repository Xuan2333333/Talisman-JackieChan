package net.talisman.talismanjackiechan.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;
import net.talisman.talismanjackiechan.client.gui.StoneSteleScreen;
import net.talisman.talismanjackiechan.client.renderer.EvilselfRenderer;
import net.talisman.talismanjackiechan.client.renderer.StoneSteleRenderer;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModBlockEntities;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModMenus;

@Mod.EventBusSubscriber(modid = TalismanJackiechanMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(TalismanJackiechanModEntities.EVILSELF.get(), EvilselfRenderer::new);

            MenuScreens.register(TalismanJackiechanModMenus.STONE_STELE.get(), StoneSteleScreen::new);
        });

        PigLaserInputHandler.init();
        RoosterControlInputHandler.init();
        MonkeyTalismanInputHandler.init();
        HouMorphClient.init();
        TalismanHotkeyHandler.init();
        TalismanSelectOverlay.init();
        PigLaserBeamClient.init();
        ChiBeamClient.init();
        ChiBeamInputHandler.init();
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TalismanJackiechanModBlockEntities.STONE_STELE.get(),
                StoneSteleRenderer::new);
    }
}