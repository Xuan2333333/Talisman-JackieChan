package net.talisman.talismanjackiechan.client.gui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.talisman.talismanjackiechan.morph.MorphEntry;
import net.talisman.talismanjackiechan.morph.MorphRegistry;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.SetHouPacket;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class MonkeyTalismanScreen extends Screen {

    private static final int SLOT_SIZE = 34;
    private static final float ICON_BOX = 24.0F;

    private final int initialHou;
    private int selectedHou;

    private int[] slotHou;
    private int[] slotX;
    private int[] slotY;
    private int hoveredIdx = -1;

    private final Map<Integer, Entity> previews = new HashMap<>();

    public MonkeyTalismanScreen(int initialHou) {
        super(Component.translatable("gui.talisman_jackiechan.monkey_talisman"));
        this.initialHou = initialHou;
        this.selectedHou = initialHou;
    }

    @Override
    protected void init() {
        super.init();

        var all = MorphRegistry.all();
        int count = all.size();
        slotHou = new int[count];
        slotX = new int[count];
        slotY = new int[count];

        for (int i = 0; i < count; i++) {
            slotHou[i] = all.get(i).hou();
        }

        layoutRing();

        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null) {
            for (MorphEntry entry : all) {
                Entity e = entry.type().create(mc.level);
                if (e != null) {
                    e.setYRot(0);
                    e.setXRot(0);
                    e.yRotO = 0;
                    e.xRotO = 0;
                    e.setYBodyRot(0);
                    e.setYHeadRot(0);
                    previews.put(entry.hou(), e);
                }
            }
        }
    }

    @Override
    public void removed() {
        super.removed();
        for (Entity e : previews.values()) {
            e.discard();
        }
        previews.clear();
    }

    private void layoutRing() {
        int count = slotX.length;
        int cx = this.width / 2;
        int cy = this.height / 2;

        float minRadius = (count * SLOT_SIZE) / (float) (2.0 * Math.PI);
        float maxRadius = Math.min(this.width, this.height) * 0.42f;
        float radius = Mth.clamp(minRadius + 8f, 90f, maxRadius);

        for (int i = 0; i < count; i++) {
            double angle = -Math.PI / 2.0 + (2.0 * Math.PI * i / count);
            slotX[i] = (int) (cx + Math.cos(angle) * radius);
            slotY[i] = (int) (cy + Math.sin(angle) * radius);
        }
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(gfx);

        hoveredIdx = -1;

        for (int i = 0; i < slotHou.length; i++) {
            int hou = slotHou[i];
            int x = slotX[i];
            int y = slotY[i];
            int half = SLOT_SIZE / 2;
            int sx = x - half;
            int sy = y - half;

            boolean hovered = mouseX >= sx && mouseX < sx + SLOT_SIZE
                    && mouseY >= sy && mouseY < sy + SLOT_SIZE;
            if (hovered) hoveredIdx = i;

            boolean selected = hou == selectedHou;

            int bg;
            if (hovered)       bg = 0xB0FFE080;
            else if (selected) bg = 0x90FFD700;
            else               bg = 0x60000000;
            gfx.fill(sx, sy, sx + SLOT_SIZE, sy + SLOT_SIZE, bg);

            if (hovered || selected) {
                int borderColor = hovered ? 0xFFFFFFFF : 0xFFFFD700;
                drawBorder(gfx, sx, sy, SLOT_SIZE, borderColor);
            }

            Entity preview = previews.get(hou);
            if (preview != null) {
                renderEntityPreview(gfx, preview, x, y, partialTick);
            }
        }

        gfx.drawCenteredString(this.font, this.title,
                this.width / 2, this.height / 2 - 4, 0xFFFFFF);

        if (hoveredIdx >= 0) {
            Component name = mobName(slotHou[hoveredIdx]);
            int tw = this.font.width(name);
            int tx = slotX[hoveredIdx] + SLOT_SIZE / 2 + 6;
            int ty = slotY[hoveredIdx] - 6;
            if (tx + tw + 8 > this.width) tx = slotX[hoveredIdx] - SLOT_SIZE / 2 - tw - 10;
            if (ty < 4) ty = 4;

            gfx.fill(tx - 4, ty - 2, tx + tw + 4, ty + 10, 0xA0000000);
            gfx.drawString(this.font, name, tx, ty, 0xFFFFFF, false);
        }

        super.render(gfx, mouseX, mouseY, partialTick);
    }

    private static void drawBorder(GuiGraphics gfx, int sx, int sy, int size, int color) {
        gfx.fill(sx, sy, sx + size, sy + 1, color);
        gfx.fill(sx, sy + size - 1, sx + size, sy + size, color);
        gfx.fill(sx, sy, sx + 1, sy + size, color);
        gfx.fill(sx + size - 1, sy, sx + size, sy + size, color);
    }

    private static void renderEntityPreview(GuiGraphics gfx, Entity entity, int cx, int cy,
                                            float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();

        if (mc.player != null) {
            entity.tickCount = mc.player.tickCount;
        }

        float h = entity.getBbHeight();
        float w = entity.getBbWidth();
        float maxDim = Math.max(h, Math.max(w, 0.4F));
        float scale = ICON_BOX / maxDim;

        PoseStack pose = gfx.pose();
        pose.pushPose();

        pose.translate(cx, cy, 100.0F);

        pose.scale(-scale, scale, scale);
        pose.mulPose(Axis.ZP.rotationDegrees(180.0F));

        pose.translate(0.0F, -h / 2.0F, 0.0F);

        var buffer = mc.renderBuffers().bufferSource();
        int light = LightTexture.FULL_BRIGHT;

        dispatcher.setRenderShadow(false);
        Lighting.setupForEntityInInventory();

        @SuppressWarnings("unchecked")
        EntityRenderer<Entity> renderer = (EntityRenderer<Entity>) dispatcher.getRenderer(entity);
        renderer.render(entity, 0.0F, partialTick, pose, buffer, light);

        buffer.endBatch();
        Lighting.setupFor3DItems();
        dispatcher.setRenderShadow(true);

        pose.popPose();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int hou = hoveredIdx >= 0 ? slotHou[hoveredIdx] : selectedHou;
            selectAndClose(hou);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (delta > 0) {
            selectedHou = selectedHou >= MorphRegistry.MAX_HOU
                    ? MorphRegistry.MIN_HOU : selectedHou + 1;
        } else if (delta < 0) {
            selectedHou = selectedHou <= MorphRegistry.MIN_HOU
                    ? MorphRegistry.MAX_HOU : selectedHou - 1;
        }
        return true;
    }

    private void selectAndClose(int hou) {
        NetworkHandler.INSTANCE.sendToServer(new SetHouPacket(hou));
        this.onClose();
    }

    private static Component mobName(int hou) {
        EntityType<?> type = MorphRegistry.typeOf(hou);
        return type == null ? Component.empty() : type.getDescription();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}