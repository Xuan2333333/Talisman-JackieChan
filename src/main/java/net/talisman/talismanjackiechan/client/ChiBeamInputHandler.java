package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.talisman.talismanjackiechan.item.MagicLizardItem;
import net.talisman.talismanjackiechan.item.MagicPufferfishItem;
import net.talisman.talismanjackiechan.network.ChiBeamPacket;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class ChiBeamInputHandler {

    private static boolean leftDown = false;
    private static boolean rightDown = false;
    private static boolean lastLeft = false;
    private static boolean lastRight = false;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(ChiBeamInputHandler.class);
    }

    public static boolean isLeftActive() {
        return leftDown && hasChiItemInHand(true);
    }

    public static boolean isRightActive() {
        return rightDown && hasChiItemInHand(false);
    }

    private static boolean hasChiItemInHand(boolean leftHand) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return false;
        Item item = leftHand ? player.getOffhandItem().getItem() : player.getMainHandItem().getItem();
        return item instanceof MagicPufferfishItem || item instanceof MagicLizardItem;
    }

    private static boolean hasAnyChiItem() {
        return hasChiItemInHand(true) || hasChiItemInHand(false);
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton.Pre event) {
        if (Minecraft.getInstance().screen != null) return;
        if (!hasAnyChiItem()) return;

        int button = event.getButton();
        int action = event.getAction();

        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            leftDown = action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT;
            event.setCanceled(true);
        } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
            rightDown = action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        boolean curLeft = isLeftActive();
        boolean curRight = isRightActive();

        if (curLeft != lastLeft || curRight != lastRight) {
            NetworkHandler.INSTANCE.sendToServer(new ChiBeamPacket(player.getUUID(), curLeft, curRight));
            lastLeft = curLeft;
            lastRight = curRight;
        }

        if ((curLeft || curRight) && player.tickCount % 5 == 0) {
            NetworkHandler.INSTANCE.sendToServer(new ChiBeamPacket(player.getUUID(), curLeft, curRight));
        }
    }
}