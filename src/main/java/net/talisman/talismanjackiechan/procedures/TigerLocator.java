package net.talisman.talismanjackiechan.procedures;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.talisman.talismanjackiechan.entity.EvilselfEntity;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.item.TigerTalismanItem;
import top.theillusivec4.curios.api.CuriosApi;

public class TigerLocator {

    public static Object[] locate(Level level, ItemStack heldStack, boolean isYang) {
        String pairId = heldStack.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID);
        if (pairId.isEmpty()) return null;

        Item targetItem = isYang ?
                TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get() :
                TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get();

        for (ServerLevel serverLevel : level.getServer().getAllLevels()) {
            for (Entity entity : serverLevel.getAllEntities()) {

                if (entity instanceof EvilselfEntity evilSelf) {
                    if (pairId.equals(evilSelf.getPairId())) {
                        return new Object[]{
                                evilSelf.level().dimension(),
                                evilSelf.getX(), evilSelf.getY(), evilSelf.getZ()
                        };
                    }
                }

                for (ItemStack stack : entity.getAllSlots()) {
                    if (matches(stack, targetItem, pairId)) {
                        return new Object[]{
                                entity.level().dimension(),
                                entity.getX(), entity.getY(), entity.getZ()
                        };
                    }
                }

                if (entity instanceof ItemEntity itemEntity) {
                    ItemStack stack = itemEntity.getItem();
                    if (matches(stack, targetItem, pairId)) {
                        return new Object[]{
                                itemEntity.level().dimension(),
                                itemEntity.getX(), itemEntity.getY(), itemEntity.getZ()
                        };
                    }
                }

                if (entity instanceof Player player) {
                    for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                        ItemStack stack = player.getInventory().getItem(i);
                        if (matches(stack, targetItem, pairId)) {
                            return new Object[]{
                                    player.level().dimension(),
                                    player.getX(), player.getY(), player.getZ()
                            };
                        }
                    }

                    if (ModList.get().isLoaded("curios")) {
                        var handlerOpt = CuriosApi.getCuriosHelper().getCuriosHandler(player);
                        if (handlerOpt.isPresent()) {
                            var handler = handlerOpt.orElse(null);
                            if (handler != null) {
                                for (var stacksHandler : handler.getCurios().values()) {
                                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                                        ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                                        if (matches(stack, targetItem, pairId)) {
                                            return new Object[]{
                                                    player.level().dimension(),
                                                    player.getX(), player.getY(), player.getZ()
                                            };
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean matches(ItemStack stack, Item targetItem, String pairId) {
        return stack.getItem() == targetItem &&
                stack.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID).equals(pairId);
    }
}