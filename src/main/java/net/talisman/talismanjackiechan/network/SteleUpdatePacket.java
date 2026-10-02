package net.talisman.talismanjackiechan.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.block.StoneSteleBlockEntity;

import java.util.function.Supplier;

public class SteleUpdatePacket {

    private final BlockPos pos;
    private final boolean isFront;
    private final String text;

    public SteleUpdatePacket(BlockPos pos, boolean isFront, String text) {
        this.pos = pos;
        this.isFront = isFront;
        this.text = text == null ? "" : text;
    }

    public static void encode(SteleUpdatePacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeBoolean(msg.isFront);
        buf.writeUtf(msg.text, 4096);
    }

    public static SteleUpdatePacket decode(FriendlyByteBuf buf) {
        return new SteleUpdatePacket(buf.readBlockPos(), buf.readBoolean(), buf.readUtf(4096));
    }

    public static void handle(SteleUpdatePacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Level level = player.level();
            if (!level.isLoaded(msg.pos)) return;
            BlockEntity be = level.getBlockEntity(msg.pos);
            if (!(be instanceof StoneSteleBlockEntity stele)) return;
            if (player.distanceToSqr(msg.pos.getX() + 0.5, msg.pos.getY() + 0.5, msg.pos.getZ() + 0.5) > 64.0) return;
            if (msg.isFront) stele.setFrontText(msg.text);
            else stele.setBackText(msg.text);
            stele.setChanged();
            level.sendBlockUpdated(msg.pos, level.getBlockState(msg.pos), level.getBlockState(msg.pos), 3);
        });
        ctx.setPacketHandled(true);
    }
}