package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.item.PigTalismanItem;
import net.talisman.talismanjackiechan.procedures.PigLaserHandler;

import java.util.UUID;
import java.util.function.Supplier;

public class PigLaserPacket {
    private final UUID playerUUID;
    private final boolean leftEye;

    public PigLaserPacket(UUID playerUUID, boolean leftEye) {
        this.playerUUID = playerUUID;
        this.leftEye = leftEye;
    }

    public static void encode(PigLaserPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.playerUUID);
        buf.writeBoolean(msg.leftEye);
    }

    public static PigLaserPacket decode(FriendlyByteBuf buf) {
        return new PigLaserPacket(buf.readUUID(), buf.readBoolean());
    }

    public static void handle(PigLaserPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender == null) return;

            if (!(sender.getMainHandItem().getItem() instanceof PigTalismanItem)) {
                return;
            }

            PigLaserHandler.tryShoot(sender, msg.leftEye);
        });
        ctx.get().setPacketHandled(true);
    }
}