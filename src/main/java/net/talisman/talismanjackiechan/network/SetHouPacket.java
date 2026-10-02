package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.item.MonkeyTalismanItem;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class SetHouPacket {

    public static final String NBT_HOU = "hou";

    private final int hou;

    public SetHouPacket(int hou) {
        this.hou = hou;
    }

    public static void encode(SetHouPacket msg, FriendlyByteBuf buf) {
        buf.writeVarInt(msg.hou);
    }

    public static SetHouPacket decode(FriendlyByteBuf buf) {
        return new SetHouPacket(buf.readVarInt());
    }

    public static void handle(SetHouPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            boolean holding = player.getMainHandItem().getItem() instanceof MonkeyTalismanItem
                    || player.getOffhandItem().getItem() instanceof MonkeyTalismanItem;
            if (!holding) return;

            if (msg.hou < Hou1Procedure.HOU_MIN || msg.hou > Hou1Procedure.HOU_MAX) return;

            Hou1Procedure.setSelectedHou(player, msg.hou);

            updateTalismanNbt(player, msg.hou);

            EntityType<?> type = Hou1Procedure.getEntityType(msg.hou);
            Component name = type != null ? type.getDescription() : Component.empty();
            player.displayClientMessage(
                    Component.translatable("message.talisman_jackiechan.monkey_mode", name),
                    true
            );
        });
        ctx.get().setPacketHandled(true);
    }

    private static void updateTalismanNbt(ServerPlayer player, int hou) {
        ItemStack main = player.getMainHandItem();
        if (main.getItem() instanceof MonkeyTalismanItem) {
            main.getOrCreateTag().putInt(NBT_HOU, hou);
            return;
        }
        ItemStack off = player.getOffhandItem();
        if (off.getItem() instanceof MonkeyTalismanItem) {
            off.getOrCreateTag().putInt(NBT_HOU, hou);
            return;
        }
        if (updateCuriosNbt(player, hou)) return;
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack s = inv.getItem(i);
            if (s.getItem() instanceof MonkeyTalismanItem) {
                s.getOrCreateTag().putInt(NBT_HOU, hou);
                return;
            }
        }
    }

    private static boolean updateCuriosNbt(ServerPlayer player, int hou) {
        if (!ModList.get().isLoaded("curios")) return false;

        var handlerOpt = CuriosApi.getCuriosHelper().getCuriosHandler(player);
        if (handlerOpt.isPresent()) return false;

        var handler = handlerOpt.orElse(null);
        if (handler == null) return false;

        for (var stacksHandler : handler.getCurios().values()) {
            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                if (stack.getItem() instanceof MonkeyTalismanItem) {
                    stack.getOrCreateTag().putInt(NBT_HOU, hou);
                    return true;
                }
            }
        }
        return false;
    }
}