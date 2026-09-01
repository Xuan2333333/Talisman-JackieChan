package net.talisman.talismanjackiechan.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;
import net.talisman.talismanjackiechan.client.renderer.EvilselfRenderer;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;


@Mod.EventBusSubscriber(modid = TalismanJackiechanMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EntityRenderers.register(TalismanJackiechanModEntities.EVILSELF.get(), EvilselfRenderer::new);
        });

        PigLaserInputHandler.init();
        RoosterControlInputHandler.init();
        MonkeyTalismanInputHandler.init();
        HouMorphClient.init();
        TalismanHotkeyHandler.init();
        TalismanSelectOverlay.init();
    }
}