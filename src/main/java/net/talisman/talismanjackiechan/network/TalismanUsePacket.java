package net.talisman.talismanjackiechan.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.item.*;
import net.talisman.talismanjackiechan.procedures.*;

import java.util.function.Supplier;

public class TalismanUsePacket {

    public enum Type {
        RABBIT, RABBIT_OFF, SNAKE, HORSE_SELF, HORSE_TARGET, SHEEP, ROOSTER_TOGGLE, PIG
    }

    private final Type type;

    public TalismanUsePacket(Type type) {
        this.type = type;
    }

    public static void encode(TalismanUsePacket msg, FriendlyByteBuf buf) {
        buf.writeEnum(msg.type);
    }

    public static TalismanUsePacket decode(FriendlyByteBuf buf) {
        return new TalismanUsePacket(buf.readEnum(Type.class));
    }

    public static void handle(TalismanUsePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            switch (msg.type) {
                case RABBIT -> {
                    if (hasTalisman(player, RabbitTalismanItem.class)) {
                        Tu2Procedure.execute(player.level());
                    }
                }
                case RABBIT_OFF -> {
                    if (hasTalisman(player, RabbitTalismanItem.class)) {
                        TalismanJackiechanModVariables.WorldVariables vars =
                                TalismanJackiechanModVariables.WorldVariables.get(player.level());
                        vars.tu = 0;
                        vars.syncData(player.level());
                    }
                }
                case SNAKE -> {
                    if (hasTalisman(player, SnakeTalismanItem.class)) {
                        SheProcedure.execute(player, ItemStack.EMPTY);
                    }
                }
                case HORSE_SELF -> {
                    if (hasTalisman(player, HorseTalismanItem.class)) {
                        Ma2Procedure.execute(player, ItemStack.EMPTY);
                    }
                }
                case HORSE_TARGET -> {
                    if (hasTalisman(player, HorseTalismanItem.class)) {
                        Entity target = getLookedEntity(player, 6.0);
                        if (target instanceof LivingEntity living) {
                            Ma3Procedure.execute(living);
                        }
                    }
                }
                case ROOSTER_TOGGLE -> {
                }
                case PIG -> {
                    if (hasTalisman(player, PigTalismanItem.class)) {
                        PigLaserHandler.tryShoot(player, true);
                        PigLaserHandler.tryShoot(player, false);
                    }
                }
                default -> {}
            }
        });
        ctx.get().setPacketHandled(true);
    }

    private static boolean hasTalisman(ServerPlayer player, Class<? extends Item> clazz) {
        for (ItemStack stack : player.getInventory().armor) {
            if (clazz.isInstance(stack.getItem())) return true;
        }
        for (ItemStack stack : player.getInventory().items) {
            if (clazz.isInstance(stack.getItem())) return true;
        }
        if (clazz.isInstance(player.getOffhandItem().getItem())) return true;

        if (net.minecraftforge.fml.ModList.get().isLoaded("curios")) {
            return top.theillusivec4.curios.api.CuriosApi.getCuriosHelper()
                    .getCuriosHandler(player)
                    .map(handler -> {
                        for (var stacksHandler : handler.getCurios().values()) {
                            for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                if (clazz.isInstance(stacksHandler.getStacks().getStackInSlot(i).getItem())) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }).orElse(false);
        }
        return false;
    }

    private static Entity getLookedEntity(ServerPlayer player, double range) {
        HitResult hit = player.pick(range, 1.0F, false);
        if (hit.getType() == HitResult.Type.ENTITY) {
            return ((EntityHitResult) hit).getEntity();
        }
        return null;
    }
}