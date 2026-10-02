package net.talisman.talismanjackiechan.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = TalismanJackiechanMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeyBindings {

    public static final String CATEGORY = "key.categories.talisman_jackiechan";

    public static final KeyMapping TALISMAN_HOTKEY = new KeyMapping(
            "key.talisman_jackiechan.talisman_select",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            CATEGORY
    );

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(TALISMAN_HOTKEY);
    }
}