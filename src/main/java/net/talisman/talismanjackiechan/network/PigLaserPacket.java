package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

            if (!hasPigTalisman(sender)) {
                return;
            }

            PigLaserHandler.tryShoot(sender, msg.leftEye);
        });
        ctx.get().setPacketHandled(true);
    }

    private static boolean hasPigTalisman(ServerPlayer player) {
        if (player.getMainHandItem().getItem() instanceof PigTalismanItem) {
            return true;
        }
        if (player.getOffhandItem().getItem() instanceof PigTalismanItem) {
            return true;
        }
        for (ItemStack stack : player.getInventory().armor) {
            if (stack.getItem() instanceof PigTalismanItem) return true;
        }
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof PigTalismanItem) return true;
        }
        if (net.minecraftforge.fml.ModList.get().isLoaded("curios")) {
            return top.theillusivec4.curios.api.CuriosApi.getCuriosHelper()
                    .getCuriosHandler(player)
                    .map(handler -> {
                        for (var stacksHandler : handler.getCurios().values()) {
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                if (stacksHandler.getStacks().getStackInSlot(i).getItem() instanceof PigTalismanItem) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).orElse(false);
        }
        return false;
    }
}