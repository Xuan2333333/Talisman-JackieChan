package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.item.*;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.PigLaserBeamPacket;
import net.talisman.talismanjackiechan.network.RoosterControlPacket;
import net.talisman.talismanjackiechan.network.TalismanUsePacket;
import net.talisman.talismanjackiechan.procedures.RoosterControlHandler;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class TalismanHotkeyHandler {

    public enum Selectable {
        RABBIT(TalismanJackiechanModItems.RABBIT_TALISMAN.get()),
        SNAKE(TalismanJackiechanModItems.SNAKE_TALISMAN.get()),
        HORSE(TalismanJackiechanModItems.HORSE_TALISMAN.get()),
        SHEEP(TalismanJackiechanModItems.SHEEP_TALISMAN.get()),
        ROOSTER(TalismanJackiechanModItems.ROOSTER_TALISMAN.get()),
        PIG(TalismanJackiechanModItems.PIG_TALISMAN.get());

        public final Item item;

        Selectable(Item item) {
            this.item = item;
        }
    }

    private static boolean holding = false;
    private static int holdTicks = 0;
    private static final int HOLD_THRESHOLD = 20;

    private static final List<Selectable> available = new ArrayList<>();
    private static int selectedIndex = 0;

    private static Selectable confirmed = null;

    private static boolean roosterControlling = false;
    private static UUID roosterTarget = null;
    private static float roosterDistance = 8.0F;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(TalismanHotkeyHandler.class);
    }

    public static boolean isSelecting() {
        return holding && holdTicks >= HOLD_THRESHOLD;
    }

    public static List<Selectable> getAvailable() {
        return available;
    }

    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static Selectable getConfirmed() {
        return confirmed;
    }

    public static boolean isRoosterControlling() {
        return roosterControlling;
    }

    public static float getRoosterDistance() {
        return roosterDistance;
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.screen != null) {
            if (holding) resetHold();
            return;
        }

        boolean keyDown = ModKeyBindings.TALISMAN_HOTKEY.isDown();

        if (keyDown) {
            if (!holding) {
                holding = true;
                holdTicks = 0;
                scanTalismans(mc.player);
                restorePreviousSelection();
            } else {
                holdTicks++;
            }

            if (roosterControlling && confirmed == Selectable.ROOSTER) {
                NetworkHandler.INSTANCE.sendToServer(
                        new RoosterControlPacket(roosterTarget, roosterDistance, true, true));
            }
        } else {
            if (holding) {
                if (holdTicks >= HOLD_THRESHOLD) {
                    if (!available.isEmpty()) {
                        confirmed = available.get(selectedIndex);
                    }
                } else {
                    if (confirmed != null) {
                        useSelected(mc, confirmed);
                    }
                }
                resetHold();
            }
        }
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (isSelecting() && !available.isEmpty()) {
            double delta = event.getScrollDelta();
            if (delta > 0) {
                selectedIndex = (selectedIndex - 1 + available.size()) % available.size();
            } else if (delta < 0) {
                selectedIndex = (selectedIndex + 1) % available.size();
            }
            event.setCanceled(true);
            return;
        }

        if (confirmed == Selectable.ROOSTER && roosterControlling) {
            double delta = event.getScrollDelta();
            if (delta > 0) {
                roosterDistance = Math.min(RoosterControlHandler.MAX_DISTANCE, roosterDistance + 1.5F);
            } else if (delta < 0) {
                roosterDistance = Math.max(RoosterControlHandler.MIN_DISTANCE, roosterDistance - 1.5F);
            }
            NetworkHandler.INSTANCE.sendToServer(
                    new RoosterControlPacket(roosterTarget, roosterDistance, true, true));
            event.setCanceled(true);
        }
    }

    private static void scanTalismans(LocalPlayer player) {
        available.clear();
        for (Selectable s : Selectable.values()) {
            if (hasItem(player, s.item)) {
                available.add(s);
            }
        }
    }

    private static void restorePreviousSelection() {
        if (confirmed == null || available.isEmpty()) {
            selectedIndex = 0;
            return;
        }
        int idx = available.indexOf(confirmed);
        selectedIndex = idx >= 0 ? idx : 0;
    }

    private static boolean hasItem(LocalPlayer player, Item item) {
        for (ItemStack stack : player.getInventory().armor) {
            if (stack.getItem() == item) return true;
        }
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() == item) return true;
        }
        if (player.getOffhandItem().getItem() == item) return true;

        if (ModList.get().isLoaded("curios")) {
            return CuriosApi.getCuriosHelper().getCuriosHandler(player)
                    .map(handler -> {
                        for (var sh : handler.getCurios().values()) {
                            for (int i = 0; i < sh.getSlots(); i++) {
                                if (sh.getStacks().getStackInSlot(i).getItem() == item) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).orElse(false);
        }
        return false;
    }

    private static void useSelected(Minecraft mc, Selectable sel) {
        LocalPlayer player = mc.player;
        if (player == null) return;

        switch (sel) {
            case RABBIT -> {
                if (player.isShiftKeyDown()) {
                    NetworkHandler.INSTANCE.sendToServer(
                            new TalismanUsePacket(TalismanUsePacket.Type.RABBIT_OFF));
                } else {
                    NetworkHandler.INSTANCE.sendToServer(
                            new TalismanUsePacket(TalismanUsePacket.Type.RABBIT));
                }
            }

            case SNAKE -> NetworkHandler.INSTANCE.sendToServer(
                    new TalismanUsePacket(TalismanUsePacket.Type.SNAKE));

            case HORSE -> {
                if (player.isShiftKeyDown()) {
                    NetworkHandler.INSTANCE.sendToServer(
                            new TalismanUsePacket(TalismanUsePacket.Type.HORSE_TARGET));
                } else {
                    NetworkHandler.INSTANCE.sendToServer(
                            new TalismanUsePacket(TalismanUsePacket.Type.HORSE_SELF));
                }
            }

            case SHEEP -> ClientFreeCamera.toggle();

            case ROOSTER -> toggleRoosterControl(mc);

            case PIG -> {
                PigLaserInputHandler.toggleContinuous();
            }
        }
    }

    private static void toggleRoosterControl(Minecraft mc) {
        if (roosterControlling) {
            NetworkHandler.INSTANCE.sendToServer(
                    new RoosterControlPacket(null, 0, false, true));
            roosterControlling = false;
            roosterTarget = null;
        } else {
            HitResult hit = mc.hitResult;
            if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
                Entity target = ((EntityHitResult) hit).getEntity();
                if (target instanceof LivingEntity && target != mc.player) {
                    roosterTarget = target.getUUID();
                    roosterDistance = (float) mc.player.distanceTo(target);
                    roosterDistance = Math.max(RoosterControlHandler.MIN_DISTANCE,
                            Math.min(RoosterControlHandler.MAX_DISTANCE, roosterDistance));
                    roosterControlling = true;
                    NetworkHandler.INSTANCE.sendToServer(
                            new RoosterControlPacket(roosterTarget, roosterDistance, true, true));
                }
            }
        }
    }

    private static void resetHold() {
        holding = false;
        holdTicks = 0;
    }
}