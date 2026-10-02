package net.talisman.talismanjackiechan.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TalismanSelectOverlay {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(TalismanSelectOverlay.class);
    }

    @SubscribeEvent
    public static void onRender(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay() != VanillaGuiOverlay.HOTBAR.type()) return;
        if (!TalismanHotkeyHandler.isSelecting()) return;

        List<TalismanHotkeyHandler.Selectable> list = TalismanHotkeyHandler.getAvailable();
        if (list.isEmpty()) return;

        Minecraft mc = Minecraft.getInstance();
        GuiGraphics g = event.getGuiGraphics();
        int screenW = mc.getWindow().getGuiScaledWidth();
        int screenH = mc.getWindow().getGuiScaledHeight();

        int size = 24;
        int gap = 10;
        int totalW = list.size() * size + (list.size() - 1) * gap;
        int startX = (screenW - totalW) / 2;
        int y = screenH / 2 - 48;

        int bgPadding = 10;
        g.fill(startX - bgPadding, y - bgPadding,
                startX + totalW + bgPadding, y + size + bgPadding,
                0x99000000);

        for (int i = 0; i < list.size(); i++) {
            int x = startX + i * (size + gap);

            if (i == TalismanHotkeyHandler.getSelectedIndex()) {
                g.fill(x - 3, y - 3, x + size + 3, y + size + 3, 0xFFFFFFFF);
                g.fill(x - 2, y - 2, x + size + 2, y + size + 2, 0xFF1A1A1A);
            }

            ItemStack stack = new ItemStack(list.get(i).item);
            int iconOffset = (size - 16) / 2;
            RenderSystem.enableBlend();
            g.renderItem(stack, x + iconOffset, y + iconOffset);
            g.renderItemDecorations(mc.font, stack, x + iconOffset, y + iconOffset);
        }

        Component tip = Component.translatable("gui.talisman_jackiechan.select_tip");
        int tipW = mc.font.width(tip);
        g.drawString(mc.font, tip, (screenW - tipW) / 2, y + size + 16, 0xFFFFFF, true);
    }
}