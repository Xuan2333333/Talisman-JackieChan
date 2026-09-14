package net.talisman.talismanjackiechan.client.gui;

import net.talisman.talismanjackiechan.item.RabbitTalismanItem;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.SetRabbitLevelPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

@OnlyIn(Dist.CLIENT)
public class RabbitTalismanScreen extends Screen {
    private final ItemStack stack;
    private final InteractionHand hand;
    private int currentLevel;

    public RabbitTalismanScreen(ItemStack stack, InteractionHand hand) {
        super(Component.literal("item.talisman_jackiechan.rabbit_talisman"));
        this.stack = stack;
        this.hand = hand;
        this.currentLevel = RabbitTalismanItem.getLevel(stack);
    }

    @Override
    protected void init() {
        super.init();
        int cx = this.width / 2;
        int cy = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("0"), b -> {
            setLevelAndSync(0);
            this.onClose();
        }).bounds(cx - 100, cy - 70, 200, 20).build());

        for (int i = 1; i <= 15; i++) {
            final int level = i;
            int row = (i - 1) / 5;
            int col = (i - 1) % 5;

            this.addRenderableWidget(Button.builder(
                    Component.literal(String.valueOf(level)),
                    b -> setLevelAndSync(level)
            ).bounds(cx - 112 + col * 46, cy - 35 + row * 28, 42, 22).build());
        }

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.talisman_jackiechan.rabbit_talisman.confirm"),
                b -> this.onClose()
        ).bounds(cx - 40, cy + 65, 80, 20).build());
    }

    private void setLevelAndSync(int level) {
        RabbitTalismanItem.setLevel(stack, level);
        currentLevel = level;

        boolean mainHand = hand == InteractionHand.MAIN_HAND;
        NetworkHandler.INSTANCE.send(
                PacketDistributor.SERVER.noArg(),
                new SetRabbitLevelPacket(level, mainHand)
        );
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partial) {
        this.renderBackground(g);
        super.render(g, mouseX, mouseY, partial);

        g.drawCenteredString(this.font, "§e" + currentLevel, this.width / 2, this.height / 2 + 95, 0x55FFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}