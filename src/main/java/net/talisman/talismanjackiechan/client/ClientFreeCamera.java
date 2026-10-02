package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientFreeCamera {

    private static CameraEntity camera;
    private static boolean active = false;
    private static boolean registered = false;
    private static int activateGuardTicks = 0;

    private static float lockedYaw;
    private static float lockedPitch;

    private static int lockedHotbarSlot = 0;

    public static boolean isActive() {
        return active;
    }

    public static CameraEntity getCamera() {
        return camera;
    }

    public static void activate() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || active) {
            return;
        }
        if (activateGuardTicks > 0) {
            return;
        }

        LocalPlayer player = mc.player;

        lockedYaw = player.getYRot();
        lockedPitch = player.getXRot();
        lockedHotbarSlot = player.getInventory().selected;

        camera = new CameraEntity(mc.level);
        camera.copyFromPlayer();

        player.setDeltaMovement(Vec3.ZERO);
        player.setSprinting(false);
        player.setJumping(false);
        if (player.input != null) {
            player.input.forwardImpulse = 0.0F;
            player.input.leftImpulse = 0.0F;
            player.input.jumping = false;
        }

        mc.setCameraEntity(camera);
        active = true;

        if (!registered) {
            MinecraftForge.EVENT_BUS.register(ClientFreeCamera.class);
            registered = true;
        }
    }

    public static void deactivate() {
        Minecraft mc = Minecraft.getInstance();
        if (!active) {
            return;
        }

        if (mc.player != null) {
            mc.player.setYRot(lockedYaw);
            mc.player.setXRot(lockedPitch);
            mc.player.yHeadRot = lockedYaw;
            mc.player.yBodyRot = lockedYaw;
            mc.setCameraEntity(mc.player);
        }

        camera = null;
        active = false;
        activateGuardTicks = 5;
    }

    public static void toggle() {
        if (active) {
            deactivate();
        } else {
            activate();
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (activateGuardTicks > 0) {
            activateGuardTicks--;
        }

        if (!active) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || camera == null || mc.level == null) {
            deactivate();
            return;
        }

        LocalPlayer player = mc.player;

        camera.updateLastTickValues();

        player.setYRot(lockedYaw);
        player.setXRot(lockedPitch);
        player.yHeadRot = lockedYaw;
        player.yBodyRot = lockedYaw;
        player.getInventory().selected = lockedHotbarSlot;
        player.setSprinting(false);
        double speed = mc.options.keySprint.isDown() ? 1.5 : 0.5;

        float yaw = camera.getYRot();
        float yawRad = yaw * ((float) Math.PI / 180.0F);

        double forwardX = -Mth.sin(yawRad);
        double forwardZ =  Mth.cos(yawRad);
        double rightX =  forwardZ;
        double rightZ = -forwardX;

        double dx = 0.0;
        double dy = 0.0;
        double dz = 0.0;

        if (mc.options.keyUp.isDown()) {
            dx += forwardX;
            dz += forwardZ;
        }
        if (mc.options.keyDown.isDown()) {
            dx -= forwardX;
            dz -= forwardZ;
        }

        if (mc.options.keyLeft.isDown()) {
            dx += rightX;
            dz += rightZ;
        }
        if (mc.options.keyRight.isDown()) {
            dx -= rightX;
            dz -= rightZ;
        }

        if (mc.options.keyJump.isDown()) {
            dy += 1.0;
        }
        if (mc.options.keyShift.isDown()) {
            dy -= 1.0;
        }

        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len > 1.0E-4) {
            dx = dx / len * speed;
            dy = dy / len * speed;
            dz = dz / len * speed;
            camera.setPos(camera.getX() + dx, camera.getY() + dy, camera.getZ() + dz);
        }
        camera.setDeltaMovement(Vec3.ZERO);
        while (mc.options.keyUse.consumeClick()) {
            deactivate();
            return;
        }
    }
}