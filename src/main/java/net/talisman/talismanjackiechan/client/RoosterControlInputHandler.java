package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.RoosterControlPacket;
import net.talisman.talismanjackiechan.procedures.RoosterControlHandler;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class RoosterControlInputHandler {

    private static boolean wasRightDown = false;
    private static boolean controlling = false;
    private static float controlDistance = 5.0F;
    private static UUID targetUUID = null;

    private static final double RAY_RANGE = 32.0;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(RoosterControlInputHandler.class);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            return;
        }

        try {
            if (ClientFreeCamera.isActive()) {
                if (controlling) {
                    stopControl();
                }
                wasRightDown = false;
                return;
            }
        } catch (Throwable ignored) {
        }

        boolean holding = mc.player.getMainHandItem().getItem() instanceof RoosterTalismanItem;
        boolean rightDown = mc.options.keyUse.isDown();

        if (holding && rightDown) {
            if (!wasRightDown) {
                Entity target = findLookedEntity(mc);
                if (target != null) {
                    targetUUID = target.getUUID();
                    controlDistance = Mth.clamp(
                            (float) mc.player.getEyePosition().distanceTo(target.getEyePosition()),
                            RoosterControlHandler.MIN_DISTANCE,
                            RoosterControlHandler.MAX_DISTANCE
                    );
                    controlling = true;
                    NetworkHandler.INSTANCE.sendToServer(
                            new RoosterControlPacket(targetUUID, controlDistance, true)
                    );
                }
            }
        } else if (wasRightDown && !rightDown) {
            stopControl();
        }

        if (!holding && controlling) {
            stopControl();
        }

        wasRightDown = rightDown && holding;
    }

    private static void stopControl() {
        if (controlling || targetUUID != null) {
            NetworkHandler.INSTANCE.sendToServer(
                    new RoosterControlPacket(targetUUID, controlDistance, false)
            );
        }
        controlling = false;
        targetUUID = null;
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }
        if (!(mc.player.getMainHandItem().getItem() instanceof RoosterTalismanItem)) {
            return;
        }
        if (!mc.options.keyUse.isDown() || !controlling || targetUUID == null) {
            return;
        }

        double delta = event.getScrollDelta();
        controlDistance = Mth.clamp(
                controlDistance + (float) delta * 0.75F,
                RoosterControlHandler.MIN_DISTANCE,
                RoosterControlHandler.MAX_DISTANCE
        );

        NetworkHandler.INSTANCE.sendToServer(
                new RoosterControlPacket(targetUUID, controlDistance, true)
        );
        event.setCanceled(true);
    }

    private static Entity findLookedEntity(Minecraft mc) {
        Entity camera = mc.getCameraEntity() == null ? mc.player : mc.getCameraEntity();
        Vec3 start = camera.getEyePosition(1.0F);
        Vec3 look = camera.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(RAY_RANGE));

        AABB box = camera.getBoundingBox().expandTowards(look.scale(RAY_RANGE)).inflate(1.0);
        EntityHitResult hit = ProjectileUtil.getEntityHitResult(
                camera,
                start,
                end,
                box,
                e -> e instanceof LivingEntity && e.isPickable() && e.isAlive() && e != mc.player,
                RAY_RANGE * RAY_RANGE
        );
        return hit == null ? null : hit.getEntity();
    }
}