package net.talisman.talismanjackiechan.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@OnlyIn(Dist.CLIENT)
public class PigLaserBeamClient {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/entity/beacon_beam.png");

    private static final float MAX_RANGE = 48.0F;
    private static final int SEGMENTS = 12;
    private static final int TIMEOUT_TICKS = 10;
    private static final int PULSE_TICKS = 3;

    private static final Map<UUID, BeamState> BEAMS = new ConcurrentHashMap<>();

    private static class BeamState {
        boolean left;
        boolean right;
        int ticksSinceUpdate;
        int pulseRemain;

        BeamState(boolean left, boolean right) {
            this.left = left;
            this.right = right;
            this.ticksSinceUpdate = 0;
            this.pulseRemain = 0;
        }
    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(PigLaserBeamClient.class);
    }

    public static void updateRemote(UUID id, boolean left, boolean right) {
        if (!left && !right) {
            BeamState existing = BEAMS.get(id);
            if (existing != null && existing.pulseRemain > 0) {
                return;
            }
            BEAMS.remove(id);
        } else {
            BEAMS.put(id, new BeamState(left, right));
        }
    }

    public static void pulse(UUID id, boolean left, boolean right, int durationTicks) {
        BeamState s = new BeamState(left, right);
        s.pulseRemain = Math.max(1, durationTicks);
        BEAMS.put(id, s);
    }

    public static void pulse(UUID id, boolean left, boolean right) {
        pulse(id, left, right, PULSE_TICKS);
    }

    public static boolean hasPulse(UUID id) {
        BeamState s = BEAMS.get(id);
        return s != null && s.pulseRemain > 0;
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Iterator<Map.Entry<UUID, BeamState>> it = BEAMS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, BeamState> e = it.next();
            BeamState s = e.getValue();
            if (s.pulseRemain > 0) {
                s.pulseRemain--;
                if (s.pulseRemain <= 0) {
                    it.remove();
                }
            } else {
                s.ticksSinceUpdate++;
                if (s.ticksSinceUpdate > TIMEOUT_TICKS) {
                    it.remove();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            return;
        }
        if (BEAMS.isEmpty()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }

        float pt = event.getPartialTick();
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
        Camera camera = event.getCamera();
        Vec3 camPos = camera.getPosition();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        for (Map.Entry<UUID, BeamState> e : BEAMS.entrySet()) {
            Player player = mc.level.getPlayerByUUID(e.getKey());
            if (!(player instanceof AbstractClientPlayer)) {
                continue;
            }

            BeamState state = e.getValue();
            if (state.left) {
                renderEyeBeam(player, pt, poseStack, buffer, true);
            }
            if (state.right) {
                renderEyeBeam(player, pt, poseStack, buffer, false);
            }
        }

        try {
            buffer.endBatch(RenderType.eyes(TEXTURE));
        } catch (Throwable t) {
            buffer.endBatch();
        }
        poseStack.popPose();
    }

    private static void renderEyeBeam(Player player, float pt,
                                      PoseStack poseStack, MultiBufferSource buffer,
                                      boolean leftEye) {
        Minecraft mc = Minecraft.getInstance();
        boolean firstPerson = mc.options.getCameraType().isFirstPerson();
        Camera camera = mc.gameRenderer.getMainCamera();

        float yaw, pitch;
        Vec3 start, end;

        if (firstPerson && player == mc.player) {
            yaw = camera.getYRot();
            pitch = camera.getXRot();
            Vec3 look = Vec3.directionFromRotation(pitch, yaw);

            float yawRad = yaw * ((float) Math.PI / 180.0F);
            Vec3 right = new Vec3(-Mth.cos(yawRad), 0.0, -Mth.sin(yawRad));

            double side = leftEye ? -0.10 : 0.10;
            start = camera.getPosition().add(right.scale(side)).add(look.scale(0.05));

            Vec3 endIdeal = start.add(look.scale(MAX_RANGE));
            BlockHitResult hit = player.level().clip(new ClipContext(
                    start,
                    endIdeal,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    player
            ));
            end = hit.getType() != HitResult.Type.MISS ? hit.getLocation() : endIdeal;
        } else {
            yaw = Mth.lerp(pt, player.yHeadRotO, player.yHeadRot);
            pitch = Mth.lerp(pt, player.xRotO, player.getXRot());
            Vec3 look = Vec3.directionFromRotation(pitch, yaw);

            float yawRad = yaw * ((float) Math.PI / 180.0F);
            Vec3 right = new Vec3(-Mth.cos(yawRad), 0.0, -Mth.sin(yawRad));

            double x = Mth.lerp(pt, player.xo, player.getX());
            double y = Mth.lerp(pt, player.yo, player.getY()) + player.getEyeHeight();
            double z = Mth.lerp(pt, player.zo, player.getZ());
            Vec3 eye = new Vec3(x, y, z);
            double side = leftEye ? -0.10 : 0.10;
            start = eye.add(right.scale(side)).add(look.scale(0.01));

            Vec3 endIdeal = start.add(look.scale(MAX_RANGE));
            BlockHitResult hit = player.level().clip(new ClipContext(
                    start,
                    endIdeal,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    player
            ));
            end = hit.getType() != HitResult.Type.MISS ? hit.getLocation() : endIdeal;
        }

        Vec3 dir = end.subtract(start);
        double length = dir.length();
        if (length < 0.05) {
            return;
        }
        dir = dir.normalize();

        poseStack.pushPose();
        poseStack.translate(start.x, start.y, start.z);
        alignYAxisTo(poseStack, dir, yaw);

        PoseStack.Pose pose = poseStack.last();
        Matrix4f mat = pose.pose();
        Matrix3f normal = pose.normal();

        VertexConsumer consumer = buffer.getBuffer(RenderType.eyes(TEXTURE));

        float[] radii = {0.07F, 0.032F};
        int[][] colors = {
                {255, 180, 60, 180},
                {255, 250, 220, 255}
        };

        for (int i = 0; i < radii.length; i++) {
            drawCylinder(consumer, mat, normal, radii[i], (float) length,
                    colors[i][0], colors[i][1], colors[i][2], colors[i][3],
                    LightTexture.FULL_BRIGHT, false);
            drawCylinder(consumer, mat, normal, radii[i], (float) length,
                    colors[i][0], colors[i][1], colors[i][2], colors[i][3],
                    LightTexture.FULL_BRIGHT, true);
        }

        poseStack.popPose();
    }

    private static void alignYAxisTo(PoseStack poseStack, Vec3 dir, float yawDeg) {
        Vector3f d = new Vector3f((float) dir.x, (float) dir.y, (float) dir.z);
        if (d.lengthSquared() < 1.0E-8F) {
            return;
        }
        d.normalize();

        if (Math.abs(d.y) > 0.995F) {
            if (d.y < 0.0F) {
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            }
            poseStack.mulPose(Axis.YP.rotationDegrees(-yawDeg));
            return;
        }

        Vector3f up = new Vector3f(0.0F, 1.0F, 0.0F);
        Vector3f axis = up.cross(d, new Vector3f());
        float len = axis.length();
        if (len < 1.0E-5F) {
            if (d.y < 0.0F) {
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            }
            return;
        }
        axis.mul(1.0F / len);
        float dot = Mth.clamp(up.dot(d), -1.0F, 1.0F);
        float angle = (float) Math.acos(dot);
        poseStack.mulPose(Axis.of(axis).rotation(angle));
    }

    private static void drawCylinder(VertexConsumer consumer, Matrix4f mat, Matrix3f normal,
                                     float radius, float length,
                                     int r, int g, int b, int a, int light,
                                     boolean reversed) {
        for (int i = 0; i < SEGMENTS; i++) {
            float a0 = (float) (i * Math.PI * 2.0 / SEGMENTS);
            float a1 = (float) ((i + 1) * Math.PI * 2.0 / SEGMENTS);
            float x0 = Mth.cos(a0) * radius;
            float z0 = Mth.sin(a0) * radius;
            float x1 = Mth.cos(a1) * radius;
            float z1 = Mth.sin(a1) * radius;

            if (!reversed) {
                vertex(consumer, mat, normal, x0, 0, z0, r, g, b, a, light);
                vertex(consumer, mat, normal, x0, length, z0, r, g, b, a, light);
                vertex(consumer, mat, normal, x1, length, z1, r, g, b, a, light);
                vertex(consumer, mat, normal, x1, 0, z1, r, g, b, a, light);
            } else {
                vertex(consumer, mat, normal, x1, 0, z1, r, g, b, a, light);
                vertex(consumer, mat, normal, x1, length, z1, r, g, b, a, light);
                vertex(consumer, mat, normal, x0, length, z0, r, g, b, a, light);
                vertex(consumer, mat, normal, x0, 0, z0, r, g, b, a, light);
            }
        }
    }

    private static void vertex(VertexConsumer c, Matrix4f mat, Matrix3f n,
                               float x, float y, float z,
                               int r, int g, int b, int a, int light) {
        c.vertex(mat, x, y, z)
                .color(r, g, b, a)
                .uv(0.5F, 0.5F)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(n, 0F, 1F, 0F)
                .endVertex();
    }
}