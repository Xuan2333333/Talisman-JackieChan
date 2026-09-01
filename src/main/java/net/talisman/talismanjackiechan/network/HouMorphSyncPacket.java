package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.client.HouMorphClient;

import java.util.UUID;
import java.util.function.Supplier;

public class HouMorphSyncPacket {
    private final UUID playerId;
    private final boolean active;
    private final String entityTypeId;

    public HouMorphSyncPacket(UUID playerId, boolean active, String entityTypeId) {
        this.playerId = playerId;
        this.active = active;
        this.entityTypeId = entityTypeId == null ? "minecraft:empty" : entityTypeId;
    }

    public static void encode(HouMorphSyncPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.playerId);
        buf.writeBoolean(msg.active);
        buf.writeUtf(msg.entityTypeId);
    }

    public static HouMorphSyncPacket decode(FriendlyByteBuf buf) {
        return new HouMorphSyncPacket(buf.readUUID(), buf.readBoolean(), buf.readUtf());
    }

    public static void handle(HouMorphSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                        HouMorphClient.handleSync(msg.playerId, msg.active, msg.entityTypeId))
        );
        ctx.get().setPacketHandled(true);
    }
}