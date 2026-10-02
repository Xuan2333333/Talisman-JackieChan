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

    private static boolean continuousActive = false;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(PigLaserInputHandler.class);
    }

    public static void toggleContinuous() {
        continuousActive = !continuousActive;
        if (!continuousActive) {
            clearBeams(true);
        }
    }

    public static boolean isContinuousActive() {
        return continuousActive;
    }

    public static void forceStopContinuous() {
        if (continuousActive) {
            continuousActive = false;
            clearBeams(true);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            continuousActive = false;
            clearBeams(false);
            return;
        }

        if (mc.screen != null) {
            continuousActive = false;
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
            continuousActive = false;
            clearBeams(true);
            return;
        }

        if (TalismanHotkeyHandler.isSelecting()) {
            if (continuousActive && hotkeyPig) {
                updateBeams(player, true, true);
                tryShootBoth(player);
            } else {
                clearBeams(true);
            }
            return;
        }

        if (leftCooldown > 0) leftCooldown--;
        if (rightCooldown > 0) rightCooldown--;

        if (holdingPig) {
            boolean leftDown = mc.options.keyAttack.isDown();
            boolean rightDown = mc.options.keyUse.isDown();

            updateBeams(player, leftDown, rightDown);

            if (leftDown && leftCooldown == 0) {
                NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), true));
                leftCooldown = COOLDOWN_TICKS;
            }
            if (rightDown && rightCooldown == 0) {
                NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), false));
                rightCooldown = COOLDOWN_TICKS;
            }
            return;
        }

        if (hotkeyPig && continuousActive) {
            updateBeams(player, true, true);
            tryShootBoth(player);
        } else {
            clearBeams(true);
        }
    }

    private static void tryShootBoth(Player player) {
        if (leftCooldown == 0) {
            NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), true));
            leftCooldown = COOLDOWN_TICKS;
        }
        if (rightCooldown == 0) {
            NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), false));
            rightCooldown = COOLDOWN_TICKS;
        }
    }

    private static void updateBeams(Player player, boolean left, boolean right) {
        if (left != lastLeft || right != lastRight) {
            PigLaserBeamClient.updateRemote(player.getUUID(), left, right);
            NetworkHandler.INSTANCE.sendToServer(
                    new PigLaserBeamPacket(player.getUUID(), left, right));
            lastLeft = left;
            lastRight = right;
        } else if (left || right) {
            PigLaserBeamClient.updateRemote(player.getUUID(), left, right);
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