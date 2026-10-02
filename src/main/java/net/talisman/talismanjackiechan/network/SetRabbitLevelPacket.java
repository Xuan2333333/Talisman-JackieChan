package net.talisman.talismanjackiechan.network;

import net.talisman.talismanjackiechan.item.RabbitTalismanItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetRabbitLevelPacket {
    private final int level;
    private final boolean mainHand;

    public SetRabbitLevelPacket(int level, boolean mainHand) {
        this.level = level;
        this.mainHand = mainHand;
    }

    public static void encode(SetRabbitLevelPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.level);
        buf.writeBoolean(msg.mainHand);
    }

    public static SetRabbitLevelPacket decode(FriendlyByteBuf buf) {
        return new SetRabbitLevelPacket(buf.readInt(), buf.readBoolean());
    }

    public static void handle(SetRabbitLevelPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            InteractionHand hand = msg.mainHand ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
            ItemStack stack = player.getItemInHand(hand);

            if (stack.getItem() instanceof RabbitTalismanItem) {
                RabbitTalismanItem.setLevel(stack, msg.level);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}