package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.talisman.talismanjackiechan.item.PigTalismanItem;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.PigLaserPacket;

@OnlyIn(Dist.CLIENT)
public class PigLaserInputHandler {
    private static int leftCooldown = 0;
    private static int rightCooldown = 0;
    private static final int COOLDOWN_TICKS = 2;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(PigLaserInputHandler.class);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        Player player = mc.player;
        ItemStack held = player.getMainHandItem();
        if (!(held.getItem() instanceof PigTalismanItem)) return;

        if (leftCooldown > 0) leftCooldown--;
        if (rightCooldown > 0) rightCooldown--;

        if (mc.options.keyAttack.isDown() && leftCooldown == 0) {
            NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), true));
            leftCooldown = COOLDOWN_TICKS;
        }

        if (mc.options.keyUse.isDown() && rightCooldown == 0) {
            NetworkHandler.INSTANCE.sendToServer(new PigLaserPacket(player.getUUID(), false));
            rightCooldown = COOLDOWN_TICKS;
        }
    }
}