package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.talisman.talismanjackiechan.item.PigTalismanItem;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.PigLaserBeamPacket;
import net.talisman.talismanjackiechan.network.PigLaserPacket;

@OnlyIn(Dist.CLIENT)
public class PigLaserInputHandler {

    private static int leftCooldown = 0;
    private static int rightCooldown = 0;
    private static final int COOLDOWN_TICKS = 2;

    private static boolean lastLeft = false;
    private static boolean lastRight = false;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(PigLaserInputHandler.class);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            clearBeams(false);
            return;
        }

        if (mc.screen != null) {
            clearBeams(true);
            return;
        }

        Player player = mc.player;
        ItemStack held = player.getMainHandItem();
        boolean holdingPig = held.getItem() instanceof PigTalismanItem;

        boolean hotkeyPig = false;
        try {
            hotkeyPig = TalismanHotkeyHandler.getConfirmed() == TalismanHotkeyHandler.Selectable.PIG;
        } catch (Throwable ignored) {
        }

        if (!holdingPig && !hotkeyPig) {
            clearBeams(true);
            return;
        }

        if (leftCooldown > 0) leftCooldown--;
        if (rightCooldown > 0) rightCooldown--;

        boolean leftDown = mc.options.keyAttack.isDown();
        boolean rightDown = mc.options.keyUse.isDown();

        if (leftDown || rightDown) {
            PigLaserBeamClient.updateRemote(player.getUUID(), leftDown, rightDown);
            if (leftDown != lastLeft || rightDown != lastRight) {
                NetworkHandler.INSTANCE.sendToServer(
                        new PigLaserBeamPacket(player.getUUID(), leftDown, rightDown));
                lastLeft = leftDown;
                lastRight = rightDown;
            }
        } else {
            if (!PigLaserBeamClient.hasPulse(player.getUUID())) {
                if (lastLeft || lastRight) {
                    PigLaserBeamClient.updateRemote(player.getUUID(), false, false);
                    NetworkHandler.INSTANCE.sendToServer(
                            new PigLaserBeamPacket(player.getUUID(), false, false));
                    lastLeft = false;
                    lastRight = false;
                }
            }
        }

        if (holdingPig) {
            if (leftDown && leftCooldown == 0) {
                NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), true));
                leftCooldown = COOLDOWN_TICKS;
            }
            if (rightDown && rightCooldown == 0) {
                NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), false));
                rightCooldown = COOLDOWN_TICKS;
            }
        }
    }

    private static void clearBeams(boolean sendPacket) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            lastLeft = false;
            lastRight = false;
            return;
        }
        if (PigLaserBeamClient.hasPulse(mc.player.getUUID())) {
            return;
        }
        PigLaserBeamClient.updateRemote(mc.player.getUUID(), false, false);
        if (sendPacket && (lastLeft || lastRight)) {
            NetworkHandler.INSTANCE.sendToServer(
                    new PigLaserBeamPacket(mc.player.getUUID(), false, false));
        }
        lastLeft = false;
        lastRight = false;
    }
}