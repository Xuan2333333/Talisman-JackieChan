package net.talisman.talismanjackiechan.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.talisman.talismanjackiechan.block.StoneStele;
import net.talisman.talismanjackiechan.block.StoneSteleBlockEntity;
import net.talisman.talismanjackiechan.config.TalismanJackiechanConfig;

public class StoneSteleRenderer implements BlockEntityRenderer<StoneSteleBlockEntity> {

    private static final float TEXT_SCALE = 0.0035f;

    private static final float FRONT_AREA_TOP    = 1.78f;
    private static final float FRONT_AREA_BOTTOM = 0.62f;
    private static final float FRONT_COL_GAP     = 2f;

    private static final float BACK_AREA_TOP     = 1.55f;
    private static final float BACK_AREA_BOTTOM  = 1.20f;
    private static final float BACK_COL_GAP      = 5f;

    private static final float AREA_LEFT  = 0.28f;
    private static final float AREA_RIGHT = 0.72f;

    private static final float Z_FRONT = 0.38f;
    private static final float Z_BACK = 0.68f;

    private static int getTextColor() {
        try {
            String raw = TalismanJackiechanConfig.STELE_TEXT_COLOR.get();
            return TalismanJackiechanConfig.parseColor(raw, 0x0A0A0A);
        } catch (Throwable t) {
            return 0x0A0A0A;
        }
    }

    public StoneSteleRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(StoneSteleBlockEntity be, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        BlockState state = be.getBlockState();
        if (!(state.getBlock() instanceof StoneStele)) return;
        Direction facing = state.getValue(StoneStele.FACING);
        Font font = Minecraft.getInstance().font;

        String front = resolveText(be.getFrontText());
        String back  = resolveText(be.getBackText());

        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
        poseStack.translate(-0.5, -0.5, -0.5);

        if (!front.isEmpty()) {
            renderFaceText(poseStack, buffer, font, front, Z_FRONT, true,
                    FRONT_AREA_TOP, FRONT_AREA_BOTTOM, FRONT_COL_GAP);
        }
        if (!back.isEmpty()) {
            renderFaceText(poseStack, buffer, font, back, Z_BACK, false,
                    BACK_AREA_TOP, BACK_AREA_BOTTOM, BACK_COL_GAP);
        }

        poseStack.popPose();
    }

    private String resolveText(String raw) {
        if (raw == null || raw.isEmpty()) return "";
        StringBuilder out = new StringBuilder();
        int i = 0;
        while (i < raw.length()) {
            char c = raw.charAt(i);
            if (c == '{') {
                int close = raw.indexOf('}', i);
                if (close > i) {
                    String key = raw.substring(i + 1, close);
                    if (key.contains(".") && !key.contains(" ") && !key.contains("\n")) {
                        out.append(Component.translatable(key).getString());
                        i = close + 1;
                        continue;
                    }
                }
            }
            out.append(c);
            i++;
        }
        return out.toString();
    }

    private void renderFaceText(PoseStack poseStack, MultiBufferSource buffer, Font font,
                                String text, float z, boolean negativeFace,
                                float areaTop, float areaBottom, float colGap) {
        float charW = 9f * TEXT_SCALE;
        float charH = 9f * TEXT_SCALE;
        float rowStep = charH + 1f * TEXT_SCALE;
        float colStep = charW + colGap * TEXT_SCALE;

        float areaW = AREA_RIGHT - AREA_LEFT;
        float areaH = areaTop - areaBottom;

        int maxRows = Math.max(1, (int) Math.floor(areaH / rowStep));
        int maxCols = Math.max(1, (int) Math.floor(areaW / colStep));

        int col = 0, row = 0;
        String[] paragraphs = text.split("\n", -1);
        outer:
        for (String para : paragraphs) {
            if (col >= maxCols) break;
            if (para.isEmpty()) {
                col++;
                row = 0;
                continue;
            }
            for (int i = 0; i < para.length(); i++) {
                if (row >= maxRows) {
                    row = 0;
                    col++;
                    if (col >= maxCols) break outer;
                }
                char c = para.charAt(i);
                float topY = areaTop - row * rowStep;

                poseStack.pushPose();
                if (negativeFace) {
                    float anchorX = AREA_LEFT + col * colStep + charW;
                    poseStack.translate(anchorX, topY, z);
                    poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    poseStack.scale(TEXT_SCALE, -TEXT_SCALE, TEXT_SCALE);
                } else {
                    float anchorX = AREA_RIGHT - col * colStep - charW;
                    poseStack.translate(anchorX, topY, z);
                    poseStack.scale(TEXT_SCALE, -TEXT_SCALE, TEXT_SCALE);
                }
                font.drawInBatch(String.valueOf(c), 0, 0, getTextColor(), false,
                        poseStack.last().pose(), buffer, Font.DisplayMode.SEE_THROUGH, 0, 0xF000F0);
                poseStack.popPose();

                row++;
            }
            col++;
            row = 0;
        }
    }
}