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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;
import net.minecraft.client.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.talisman.talismanjackiechan.item.MagicLizardItem;
import net.talisman.talismanjackiechan.item.MagicPufferfishItem;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@OnlyIn(Dist.CLIENT)
public class ChiBeamClient {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/entity/beacon_beam.png");

    private static final float MAX_RANGE = 64.0F;
    private static final int SEGMENTS = 10;
    private static final int TIMEOUT_TICKS = 12;

    private static final Map<UUID, BeamState> BEAMS = new ConcurrentHashMap<>();

    private static class BeamState {
        boolean left;
        boolean right;
        int ticksSinceUpdate;

        BeamState(boolean left, boolean right) {
            this.left = left;
            this.right = right;
            this.ticksSinceUpdate = 0;
        }
    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(ChiBeamClient.class);
    }

    public static void updateRemote(UUID id, boolean left, boolean right) {
        if (!left && !right) {
            BEAMS.remove(id);
        } else {
            BEAMS.put(id, new BeamState(left, right));
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Iterator<Map.Entry<UUID, BeamState>> it = BEAMS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, BeamState> e = it.next();
            BeamState s = e.getValue();
            s.ticksSinceUpdate++;
            if (s.ticksSinceUpdate > TIMEOUT_TICKS) {
                it.remove();
            }
        }
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        float pt = event.getPartialTick();
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource.BufferSource buffer = mc.renderBuffers().bufferSource();
        Camera camera = event.getCamera();
        Vec3 camPos = camera.getPosition();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        for (Map.Entry<UUID, BeamState> e : BEAMS.entrySet()) {
            Player player = mc.level.getPlayerByUUID(e.getKey());
            if (!(player instanceof AbstractClientPlayer)) continue;
            BeamState state = e.getValue();
            if (state.left) renderHandBeam(player, pt, poseStack, buffer, true);
            if (state.right) renderHandBeam(player, pt, poseStack, buffer, false);
        }

        Player local = mc.player;
        if (local != null) {
            boolean leftActive = ChiBeamInputHandler.isLeftActive();
            boolean rightActive = ChiBeamInputHandler.isRightActive();
            if (leftActive) renderHandBeam(local, pt, poseStack, buffer, true);
            if (rightActive) renderHandBeam(local, pt, poseStack, buffer, false);
        }
        try {
            buffer.endBatch(RenderType.eyes(TEXTURE));
        } catch (Throwable t) {
            buffer.endBatch();
        }
        poseStack.popPose();
    }

    private static void renderHandBeam(Player player, float pt,
                                       PoseStack poseStack, MultiBufferSource buffer,
                                       boolean leftHand) {
        Minecraft mc = Minecraft.getInstance();
        boolean firstPerson = mc.options.getCameraType().isFirstPerson() && player == mc.player;

        float yaw = Mth.lerp(pt, player.yHeadRotO, player.yHeadRot);
        float pitch = Mth.lerp(pt, player.xRotO, player.getXRot());
        Vec3 look = Vec3.directionFromRotation(pitch, yaw);

        InteractionHand hand = leftHand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        Item item = player.getItemInHand(hand).getItem();
        if (!(item instanceof MagicPufferfishItem || item instanceof MagicLizardItem)) {
            return;
        }

        Vec3 start = getHandTipPosition(player, pt, look, hand, firstPerson);
        Vec3 endIdeal = start.add(look.scale(MAX_RANGE));

        BlockHitResult blockHit = player.level().clip(new ClipContext(
                start, endIdeal,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));
        Vec3 end = blockHit.getType() != HitResult.Type.MISS ? blockHit.getLocation() : endIdeal;
        double maxLen = start.distanceTo(end);

        EntityHitResult entityHit = getEntityHit(player, start, look, maxLen);
        if (entityHit != null) {
            end = entityHit.getLocation();
        }

        Vec3 dir = end.subtract(start);
        double length = dir.length();
        if (length < 0.08) return;
        dir = dir.normalize();

        poseStack.pushPose();
        poseStack.translate(start.x, start.y, start.z);
        alignYAxisTo(poseStack, dir, yaw);

        PoseStack.Pose pose = poseStack.last();
        Matrix4f mat = pose.pose();
        Matrix3f normal = pose.normal();

        VertexConsumer consumer = buffer.getBuffer(RenderType.eyes(TEXTURE));

        float[] radii = {0.042F, 0.020F, 0.009F};
        int[][] colors = {
                {15, 140, 45, 100},
                {25, 190, 70, 160},
                {60, 230, 110, 230}
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


    private static Vec3 getHandTipPosition(Player player, float pt, Vec3 look,
                                           InteractionHand hand, boolean firstPerson) {
        boolean isOffHand = hand == InteractionHand.OFF_HAND;

        if (firstPerson) {
            Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
            Vec3 cam = camera.getPosition();
            float yawRad = camera.getYRot() * ((float) Math.PI / 180.0F);
            Vec3 right = new Vec3(-Mth.cos(yawRad), 0.0, -Mth.sin(yawRad));

            double side    = isOffHand ? -0.14 : 0.14;
            double forward = 0.22;
            double down    = -0.18;

            return cam.add(right.scale(side)).add(look.scale(forward)).add(0, down, 0);
        }

        try {
            if (!(player instanceof AbstractClientPlayer clientPlayer)) {
                return fallbackHandPos(player, pt, look, isOffHand);
            }

            var dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
            var renderer = dispatcher.getRenderer(clientPlayer);

            if (!(renderer instanceof net.minecraft.client.renderer.entity.player.PlayerRenderer playerRenderer)) {
                return fallbackHandPos(player, pt, look, isOffHand);
            }

            PlayerModel<AbstractClientPlayer> model = playerRenderer.getModel();

            PoseStack poseStack = new PoseStack();
            poseStack.pushPose();

            float bodyYaw = Mth.rotLerp(pt, clientPlayer.yBodyRotO, clientPlayer.yBodyRot);
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - bodyYaw));

            float limbSwing       = clientPlayer.walkAnimation.position(pt);
            float limbSwingAmount = clientPlayer.walkAnimation.speed(pt);
            float ageInTicks      = clientPlayer.tickCount + pt;
            float netHeadYaw      = Mth.rotLerp(pt, clientPlayer.yHeadRotO, clientPlayer.yHeadRot) - bodyYaw;
            float headPitch       = Mth.lerp(pt, clientPlayer.xRotO, clientPlayer.getXRot());

            model.prepareMobModel(clientPlayer, limbSwing, limbSwingAmount, pt);
            model.setupAnim(clientPlayer, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            var arm = isOffHand ? model.rightArm : model.leftArm;
            arm.translateAndRotate(poseStack);

            poseStack.translate(
                    isOffHand ? -0.05F : 0.05F,
                    0.55F,
                    0.0F
            );

            Matrix4f matrix = poseStack.last().pose();
            Vector3f local = new Vector3f(0, 0, 0);
            matrix.transformPosition(local);

            double px = Mth.lerp(pt, clientPlayer.xo, clientPlayer.getX());
            double py = Mth.lerp(pt, clientPlayer.yo, clientPlayer.getY());
            double pz = Mth.lerp(pt, clientPlayer.zo, clientPlayer.getZ());

            poseStack.popPose();

            return new Vec3(px + local.x, py + local.y, pz + local.z);

        } catch (Throwable t) {
            return fallbackHandPos(player, pt, look, isOffHand);
        }
    }

    private static Vec3 fallbackHandPos(Player player, float pt, Vec3 look, boolean isOffHand) {
        double x = Mth.lerp(pt, player.xo, player.getX());
        double y = Mth.lerp(pt, player.yo, player.getY()) + player.getEyeHeight() * 0.75;
        double z = Mth.lerp(pt, player.zo, player.getZ());
        Vec3 base = new Vec3(x, y, z);

        float bodyYaw = Mth.lerp(pt, player.yBodyRotO, player.yBodyRot);
        float yawRad = bodyYaw * ((float) Math.PI / 180.0F);
        Vec3 right = new Vec3(-Mth.cos(yawRad), 0.0, -Mth.sin(yawRad));
        Vec3 forwardVec = new Vec3(-Mth.sin(yawRad), 0.0, Mth.cos(yawRad));

        double side    = isOffHand ? -0.36 : 0.36;
        double forward = 0.48;
        double down    = -0.42;

        return base.add(forwardVec.scale(forward)).add(right.scale(side)).add(0, down, 0);
    }

    private static EntityHitResult getEntityHit(Player player, Vec3 start, Vec3 look, double maxLen) {
        Vec3 end = start.add(look.scale(maxLen));
        AABB box = player.getBoundingBox().expandTowards(look.scale(maxLen)).inflate(1.0);

        EntityHitResult result = null;
        double closest = maxLen;

        for (Entity e : player.level().getEntities(player, box, ent ->
                ent instanceof LivingEntity && ent.isAlive() && !ent.isSpectator() && ent != player)) {
            AABB bb = e.getBoundingBox().inflate(0.25);
            var opt = bb.clip(start, end);
            if (opt.isPresent()) {
                double dist = start.distanceTo(opt.get());
                if (dist < closest) {
                    closest = dist;
                    result = new EntityHitResult(e, opt.get());
                }
            }
        }
        return result;
    }

    private static void alignYAxisTo(PoseStack poseStack, Vec3 dir, float yawDeg) {
        Vector3f d = new Vector3f((float) dir.x, (float) dir.y, (float) dir.z);
        if (d.lengthSquared() < 1.0E-8F) return;
        d.normalize();

        if (Math.abs(d.y) > 0.995F) {
            if (d.y < 0.0F) poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(-yawDeg));
            return;
        }

        Vector3f up = new Vector3f(0.0F, 1.0F, 0.0F);
        Vector3f axis = up.cross(d, new Vector3f());
        float len = axis.length();
        if (len < 1.0E-5F) {
            if (d.y < 0.0F) poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
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