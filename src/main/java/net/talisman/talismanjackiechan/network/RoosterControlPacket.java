package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.procedures.RoosterControlHandler;

import java.util.UUID;
import java.util.function.Supplier;

public class RoosterControlPacket {
    private final UUID targetUUID;
    private final float distance;
    private final boolean start;

    public RoosterControlPacket(UUID targetUUID, float distance, boolean start) {
        this.targetUUID = targetUUID == null ? new UUID(0L, 0L) : targetUUID;
        this.distance = distance;
        this.start = start;
    }

    public static void encode(RoosterControlPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.targetUUID);
        buf.writeFloat(msg.distance);
        buf.writeBoolean(msg.start);
    }

    public static RoosterControlPacket decode(FriendlyByteBuf buf) {
        return new RoosterControlPacket(buf.readUUID(), buf.readFloat(), buf.readBoolean());
    }

    public static void handle(RoosterControlPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender == null) {
                return;
            }
            if (msg.start) {
                RoosterControlHandler.startOrUpdate(sender, msg.targetUUID, msg.distance);
            } else {
                RoosterControlHandler.stop(sender);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}