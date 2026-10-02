package net.talisman.talismanjackiechan.client.gui;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.talisman.talismanjackiechan.compat.IMBlockerCompat;
import net.talisman.talismanjackiechan.menu.StoneSteleMenu;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.SteleUpdatePacket;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class StoneSteleScreen extends AbstractContainerScreen<StoneSteleMenu> {

    private static final int GUI_W = 300;
    private static final int GUI_H = 230;

    private static final int COLOR_TITLE = 0xFFFFFF;
    private static final int COLOR_LABEL = 0xFFD070;
    private static final int COLOR_STELE = 0xE8D8B0;
    private static final int COLOR_TRANS = 0x9CC8FF;
    private static final int COLOR_EMPTY = 0x808080;
    private static final int COLOR_INDICATOR = 0xFFAA44;

    private static final int TITLE_Y   = 6;
    private static final int LABEL_Y   = 22;
    private static final int CONTENT_TOP = 40;
    private static final int CONTENT_H   = 142;
    private static final int STELE_AREA_W = 160;
    private static final int GAP = 5;

    private final StoneSteleMenu menu;
    private String text;
    private boolean editMode;
    private MultiLineEditBox editBox;
    private EditBox imeProxy;
    private Button toggleButton;
    private Button saveButton;

    private int steleScroll = 0;
    private int transScroll = 0;

    public StoneSteleScreen(StoneSteleMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.menu = menu;
        this.text = menu.getCurrentText();
        this.editMode = false;
        this.imageWidth = GUI_W;
        this.imageHeight = GUI_H;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;

        this.imeProxy = new EditBox(this.font, -100, -100, 1, 1, Component.empty());
        this.addRenderableWidget(this.imeProxy);

        this.editBox = new MultiLineEditBox(
                this.font,
                x + 10, y + CONTENT_TOP,
                GUI_W - 20, CONTENT_H,
                Component.translatable("gui.talisman_jackiechan.stele.edit_hint"),
                Component.translatable("gui.talisman_jackiechan.stele.edit_hint"));
        this.editBox.setValue(text);
        this.editBox.visible = editMode;
        this.editBox.active = editMode;
        this.addRenderableWidget(this.editBox);

        this.toggleButton = Button.builder(
                        Component.translatable(editMode
                                ? "gui.talisman_jackiechan.stele.lock"
                                : "gui.talisman_jackiechan.stele.unlock"),
                        b -> toggleEditMode())
                .bounds(x + 10, y + 190, 130, 20).build();
        this.addRenderableWidget(toggleButton);

        this.saveButton = Button.builder(
                        Component.translatable("gui.talisman_jackiechan.stele.save"),
                        b -> save())
                .bounds(x + 160, y + 190, 130, 20).build();
        this.saveButton.active = editMode;
        this.addRenderableWidget(saveButton);

        if (editMode) this.setInitialFocus(this.editBox);
    }

    public boolean isEditModeActive() {
        return this.editMode;
    }

    @Override
    public void setFocused(@Nullable GuiEventListener listener) {
        super.setFocused(listener);
        if (this.imeProxy != null) {
            this.imeProxy.setFocused(listener == this.editBox && this.editMode);
        }
    }

    @Override
    public GuiEventListener getFocused() {
        GuiEventListener real = super.getFocused();
        if (real == this.editBox && this.editMode && this.imeProxy != null) {
            return this.imeProxy;
        }
        return real;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.editMode && this.editBox != null) {
            if (this.editBox.keyPressed(keyCode, scanCode, modifiers)) return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.editMode && this.editBox != null) {
            if (this.editBox.charTyped(codePoint, modifiers)) return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    private void toggleEditMode() {
        editMode = !editMode;
        editBox.visible = editMode;
        editBox.active = editMode;
        if (editMode) {
            this.setInitialFocus(editBox);
        } else {
            editBox.setFocused(false);
            this.setFocused(null);
        }
        saveButton.active = editMode;
        toggleButton.setMessage(Component.translatable(
                editMode
                        ? "gui.talisman_jackiechan.stele.lock"
                        : "gui.talisman_jackiechan.stele.unlock"));
    }

    private void save() {
        text = editBox.getValue();
        NetworkHandler.INSTANCE.sendToServer(new SteleUpdatePacket(
                menu.getPos(), menu.isFront(), text));
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;
        g.fill(x, y, x + imageWidth, y + imageHeight, 0xD0101010);
        g.fill(x + 1, y + 1, x + imageWidth - 1, y + imageHeight - 1, 0xD0282818);
        g.fill(x + 1, y + 1, x + imageWidth - 1, y + 2, 0xFF8B7355);
        g.fill(x + 1, y + imageHeight - 2, x + imageWidth - 1, y + imageHeight - 1, 0xFF8B7355);
        g.fill(x + 1, y + 1, x + 2, y + imageHeight - 1, 0xFF8B7355);
        g.fill(x + imageWidth - 2, y + 1, x + imageWidth - 1, y + imageHeight - 1, 0xFF8B7355);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        if (editMode && editBox != null && editBox.visible && editBox.active) {
            IMBlockerCompat.captureTick(editBox, true);
        }
        this.renderBackground(g);
        super.render(g, mouseX, mouseY, partialTick);

        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;

        g.drawString(this.font,
                menu.isFront()
                        ? Component.translatable("gui.talisman_jackiechan.stele.front")
                        : Component.translatable("gui.talisman_jackiechan.stele.back"),
                x + 10, y + TITLE_Y, COLOR_TITLE, false);

        if (!editMode) {
            renderContent(g, x, y);
        }

        this.renderTooltip(g, mouseX, mouseY);
    }

    private void renderContent(GuiGraphics g, int x, int y) {
        int leftX = x + 10;
        int topY = y + CONTENT_TOP;
        int bottomY = topY + CONTENT_H;

        int steleX = leftX;
        int steleRight = steleX + STELE_AREA_W;
        int transX = steleRight + GAP;
        int transW = GUI_W - 20 - STELE_AREA_W - GAP;
        int transRight = x + imageWidth - 10;

        g.drawString(this.font,
                Component.translatable("gui.talisman_jackiechan.stele.original"),
                steleX, y + LABEL_Y, COLOR_LABEL, false);
        g.drawString(this.font,
                Component.translatable("gui.talisman_jackiechan.stele.translation"),
                transX, y + LABEL_Y, COLOR_LABEL, false);

        String displayText = resolveText(text);
        if (displayText.isEmpty()) {
            g.drawString(this.font,
                    Component.translatable("gui.talisman_jackiechan.stele.empty"),
                    steleX, topY, COLOR_EMPTY, false);
        } else {
            g.enableScissor(steleX, topY, steleRight, bottomY);
            drawVerticalText(g, displayText, steleRight, topY,
                    STELE_AREA_W, CONTENT_H, COLOR_STELE, steleScroll);
            g.disableScissor();
            drawScrollIndicator(g, steleX, topY, STELE_AREA_W, CONTENT_H,
                    computeTotalCols(displayText), computeMaxCols(), steleScroll);
        }

        String translation = resolveTranslation(text);
        if (!translation.isEmpty()) {
            g.enableScissor(transX, topY, transRight, bottomY);
            drawTranslation(g, translation, transX, topY, transW, CONTENT_H,
                    COLOR_TRANS, transScroll);
            g.disableScissor();
            drawScrollIndicator(g, transX, topY, transW, CONTENT_H,
                    computeTotalTransLines(translation, transW), computeMaxTransLines(), transScroll);
        }
    }

    private int computeMaxRows() {
        return Math.max(1, CONTENT_H / (this.font.lineHeight + 1));
    }

    private int computeMaxCols() {
        return Math.max(1, STELE_AREA_W / (this.font.lineHeight + 2));
    }

    private int computeMaxTransLines() {
        return Math.max(1, CONTENT_H / (this.font.lineHeight + 1));
    }

    private List<String> buildColumns(String text, int maxRows) {
        List<String> cols = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (String para : text.split("\n", -1)) {
            if (para.isEmpty()) {
                if (cur.length() > 0) { cols.add(cur.toString()); cur = new StringBuilder(); }
                cols.add("");
                continue;
            }
            for (int i = 0; i < para.length(); i++) {
                if (cur.length() >= maxRows) { cols.add(cur.toString()); cur = new StringBuilder(); }
                cur.append(para.charAt(i));
            }
            if (cur.length() > 0) { cols.add(cur.toString()); cur = new StringBuilder(); }
        }
        if (cur.length() > 0) cols.add(cur.toString());
        return cols;
    }

    private int computeTotalCols(String text) {
        return buildColumns(text, computeMaxRows()).size();
    }

    private int computeTotalTransLines(String text, int width) {
        return this.font.split(Component.literal(text), width).size();
    }

    private void drawVerticalText(GuiGraphics g, String text, int rightX, int topY,
                                  int maxWidth, int maxHeight, int color, int scrollCols) {
        int charH = this.font.lineHeight;
        int cell = charH + 1;
        int colW = charH + 2;
        int maxRows = Math.max(1, maxHeight / cell);
        int maxCols = Math.max(1, maxWidth / colW);

        List<String> cols = buildColumns(text, maxRows);
        for (int i = 0; i < maxCols && i + scrollCols < cols.size(); i++) {
            String col = cols.get(i + scrollCols);
            int colRight = rightX - i * colW;
            int colLeft = colRight - charH;
            for (int j = 0; j < col.length(); j++) {
                int cy = topY + j * cell;
                g.drawString(this.font, String.valueOf(col.charAt(j)), colLeft, cy, color, false);
            }
        }
    }

    private void drawTranslation(GuiGraphics g, String text, int x, int y,
                                 int width, int height, int color, int scrollLines) {
        List<FormattedCharSequence> lines = this.font.split(Component.literal(text), width);
        int lineH = this.font.lineHeight + 1;
        int maxLines = Math.max(1, height / lineH);
        for (int i = 0; i < maxLines && i + scrollLines < lines.size(); i++) {
            g.drawString(this.font, lines.get(i + scrollLines), x, y + i * lineH, color, false);
        }
    }

    private void drawScrollIndicator(GuiGraphics g, int x, int y, int width, int height,
                                     int totalLines, int maxVisibleLines, int currentScroll) {
        if (totalLines <= maxVisibleLines) return;
        int barX = x + width - 2;
        int trackTop = y;
        int trackBottom = y + height;
        int trackH = trackBottom - trackTop;
        int barH = Math.max(8, trackH * maxVisibleLines / totalLines);
        int maxScroll = totalLines - maxVisibleLines;
        int curScroll = Math.min(currentScroll, maxScroll);
        int barTop = trackTop + (trackH - barH) * curScroll / Math.max(1, maxScroll);
        g.fill(barX, trackTop, barX + 2, trackBottom, 0x40FFFFFF);
        g.fill(barX, barTop, barX + 2, barTop + barH, COLOR_INDICATOR);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;
        int contentTop = y + CONTENT_TOP;
        int contentBottom = contentTop + CONTENT_H;

        if (mouseY < contentTop || mouseY > contentBottom) {
            return super.mouseScrolled(mouseX, mouseY, delta);
        }

        int steleLeft = x + 10;
        int steleRight = steleLeft + STELE_AREA_W;
        int transLeft = steleRight + GAP;
        int transRight = x + imageWidth - 10;

        int dir = (int) -Math.signum(delta);

        if (mouseX >= steleLeft && mouseX <= steleRight) {
            String displayText = resolveText(text);
            int total = computeTotalCols(displayText);
            int maxCols = computeMaxCols();
            int maxScroll = Math.max(0, total - maxCols);
            steleScroll = Math.max(0, Math.min(maxScroll, steleScroll + dir));
            return true;
        }
        if (mouseX >= transLeft && mouseX <= transRight) {
            String translation = resolveTranslation(text);
            int total = computeTotalTransLines(translation, transRight - transLeft);
            int maxLines = computeMaxTransLines();
            int maxScroll = Math.max(0, total - maxLines);
            transScroll = Math.max(0, Math.min(maxScroll, transScroll + dir));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, delta);
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

    private String resolveTranslation(String raw) {
        if (raw == null || raw.isEmpty()) return "";
        StringBuilder out = new StringBuilder();
        int i = 0;
        while (i < raw.length()) {
            int start = raw.indexOf('{', i);
            if (start < 0) break;
            int end = raw.indexOf('}', start);
            if (end < 0) break;
            String key = raw.substring(start + 1, end);
            if (key.contains(".") && !key.contains(" ") && !key.contains("\n")) {
                String transKey = key + ".translation";
                String trans = Component.translatable(transKey).getString();
                if (!trans.equals(transKey) && !trans.isEmpty()) {
                    if (out.length() > 0) out.append("\n\n");
                    out.append(trans);
                }
            }
            i = end + 1;
        }
        return out.toString();
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
    }
    @Override
    public void onClose() {
        this.editMode = false;
        if (editBox != null) editBox.setFocused(false);
        clearAllKeyMappingClicks();
        super.onClose();
    }

    private static void clearAllKeyMappingClicks() {
        try {
            Field mapField = KeyMapping.class.getDeclaredField("MAP");
            mapField.setAccessible(true);
            Object mapObj = mapField.get(null);
            if (!(mapObj instanceof java.util.Map<?, ?> map)) return;

            Field countField = KeyMapping.class.getDeclaredField("clickCount");
            countField.setAccessible(true);

            for (Object value : map.values()) {
                if (value instanceof KeyMapping km) {
                    countField.setInt(km, 0);
                }
            }
        } catch (Throwable ignored) {
        }
    }
}