package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import net.talisman.talismanjackiechan.client.PigLaserBeamClient;

import java.util.UUID;
import java.util.function.Supplier;

public class PigLaserBeamPacket {
    private final UUID playerId;
    private final boolean left;
    private final boolean right;

    public PigLaserBeamPacket(UUID playerId, boolean left, boolean right) {
        this.playerId = playerId;
        this.left = left;
        this.right = right;
    }

    public static void encode(PigLaserBeamPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.playerId);
        buf.writeBoolean(msg.left);
        buf.writeBoolean(msg.right);
    }

    public static PigLaserBeamPacket decode(FriendlyByteBuf buf) {
        return new PigLaserBeamPacket(buf.readUUID(), buf.readBoolean(), buf.readBoolean());
    }

    public static void handle(PigLaserBeamPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender != null) {
                NetworkHandler.INSTANCE.send(
                        PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> sender),
                        new PigLaserBeamPacket(sender.getUUID(), msg.left, msg.right)
                );
            } else {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                        PigLaserBeamClient.updateRemote(msg.playerId, msg.left, msg.right));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}