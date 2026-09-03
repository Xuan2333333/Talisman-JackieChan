package net.talisman.talismanjackiechan.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.talisman.talismanjackiechan.entity.PigTalismanPowerEntity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class PigTalismanPowerRenderer extends EntityRenderer<PigTalismanPowerEntity> {

    private static final ResourceLocation BEAM_TEXTURE =
            new ResourceLocation("minecraft", "textures/misc/white.png");

    private static final int SEGMENTS = 10;
    private static final int LAYERS = 3;

    public PigTalismanPowerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(PigTalismanPowerEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        double ex = Mth.lerp(partialTicks, entity.xOld, entity.getX());
        double ey = Mth.lerp(partialTicks, entity.yOld, entity.getY());
        double ez = Mth.lerp(partialTicks, entity.zOld, entity.getZ());
        Vec3 end = new Vec3(ex, ey, ez);

        Vec3 start = getBeamStart(entity, partialTicks);
        Vec3 dir = end.subtract(start);
        double length = dir.length();
        if (length < 0.15) {
            return;
        }
        dir = dir.normalize();

        poseStack.pushPose();
        poseStack.translate(start.x - ex, start.y - ey, start.z - ez);

        alignYAxisTo(poseStack, dir);

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityTranslucentEmissive(BEAM_TEXTURE));
        Matrix4f matrix = poseStack.last().pose();
        Matrix3f normalMat = poseStack.last().normal();

        float[] radii = {0.09F, 0.055F, 0.028F};
        int[][] colors = {
                {255, 90, 20, 70},
                {255, 160, 40, 140},
                {255, 240, 180, 220}
        };

        for (int i = 0; i < LAYERS; i++) {
            drawCylinder(consumer, matrix, normalMat,
                    radii[i], (float) length,
                    colors[i][0], colors[i][1], colors[i][2], colors[i][3],
                    packedLight);
        }

        poseStack.popPose();
    }

    private static Vec3 getBeamStart(PigTalismanPowerEntity entity, float partialTicks) {
        Entity owner = entity.getOwner();
        if (owner != null) {
            Vec3 eye = owner.getEyePosition(partialTicks);
            Vec3 look = owner.getViewVector(partialTicks);
            eye = eye.add(look.scale(0.25));

            Vec3 right = new Vec3(-look.z, 0, look.x).normalize();
            Vec3 toEntity = entity.position().subtract(owner.getEyePosition(partialTicks));
            double side = toEntity.dot(right);
            double offset = side >= 0 ? 0.2 : -0.2;
            return eye.add(right.scale(offset));
        }

        Vec3 pos = entity.getPosition(partialTicks);
        Vec3 motion = entity.getDeltaMovement();
        if (motion.lengthSqr() > 1.0E-6) {
            return pos.subtract(motion.normalize().scale(0.5));
        }
        return pos;
    }

    private static void alignYAxisTo(PoseStack poseStack, Vec3 dir) {
        Vector3f d = new Vector3f((float) dir.x, (float) dir.y, (float) dir.z);
        d.normalize();

        Vector3f up = new Vector3f(0F, 1F, 0F);
        Vector3f axis = up.cross(d, new Vector3f());
        float axisLen = axis.length();
        if (axisLen < 1.0E-4F) {
            if (d.y < 0) {
                poseStack.mulPose(Axis.XP.rotationDegrees(180F));
            }
            return;
        }
        axis.mul(1F / axisLen);
        float dot = Mth.clamp(up.dot(d), -1F, 1F);
        float angle = (float) Math.acos(dot);
        poseStack.mulPose(com.mojang.math.Axis.of(axis).rotation(angle));
    }

    private static void drawCylinder(VertexConsumer consumer, Matrix4f mat, Matrix3f normal,
                                     float radius, float length,
                                     int r, int g, int b, int a,
                                     int light) {
        for (int i = 0; i < SEGMENTS; i++) {
            float a0 = (float) (i * Math.PI * 2.0 / SEGMENTS);
            float a1 = (float) ((i + 1) * Math.PI * 2.0 / SEGMENTS);

            float x0 = Mth.cos(a0) * radius;
            float z0 = Mth.sin(a0) * radius;
            float x1 = Mth.cos(a1) * radius;
            float z1 = Mth.sin(a1) * radius;

            vertex(consumer, mat, normal, x0, 0F, z0, r, g, b, a, 0F, 0F, light);
            vertex(consumer, mat, normal, x0, length, z0, r, g, b, a, 0F, 1F, light);
            vertex(consumer, mat, normal, x1, length, z1, r, g, b, a, 1F, 1F, light);
            vertex(consumer, mat, normal, x1, 0F, z1, r, g, b, a, 1F, 0F, light);
        }
    }

    private static void vertex(VertexConsumer consumer, Matrix4f mat, Matrix3f normal,
                               float x, float y, float z,
                               int r, int g, int b, int a,
                               float u, float v, int light) {
        consumer.vertex(mat, x, y, z)
                .color(r, g, b, a)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0F, 1F, 0F)
                .endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(PigTalismanPowerEntity entity) {
        return BEAM_TEXTURE;
    }
}