package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.talisman.talismanjackiechan.entity.EvilselfEntity;
import net.talisman.talismanjackiechan.init.TalismanEffects;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.item.TigerTalismanItem;
import top.theillusivec4.curios.api.CuriosApi;

public class TigerTalismanCombineHelper {

    public static boolean tryCombine(Player player, ItemStack heldStack, boolean isYang) {
        String heldId = heldStack.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID);
        if (heldId.isEmpty()) return false;

        Item targetItem = isYang ?
                TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get() :
                TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get();

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() == targetItem) {
                String id = stack.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID);
                if (id.equals(heldId)) {
                    giveTigerTalisman(player);
                    player.getInventory().setItem(i, ItemStack.EMPTY);
                    heldStack.setCount(0);
                    return true;
                }
            }
        }

        ItemStack offhand = player.getOffhandItem();
        if (offhand.getItem() == targetItem) {
            String id = offhand.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID);
            if (id.equals(heldId)) {
                giveTigerTalisman(player);
                player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                heldStack.setCount(0);
                return true;
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
                            if (stack.getItem() == targetItem) {
                                String id = stack.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID);
                                if (id.equals(heldId)) {
                                    giveTigerTalisman(player);
                                    stacksHandler.getStacks().setStackInSlot(i, ItemStack.EMPTY);
                                    heldStack.setCount(0);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean tryCombineWithEvilSelf(Player player, ItemStack heldStack, EvilselfEntity evilSelf) {
        Item held = heldStack.getItem();
        if (held != TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get()
                && held != TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get()) {
            return false;
        }

        String heldId = heldStack.getOrCreateTag().getString(TigerTalismanItem.PAIR_ID);
        if (heldId.isEmpty()) return false;

        String evilId = evilSelf.getPairId();
        if (evilId.isEmpty() || !heldId.equals(evilId)) return false;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack s = evilSelf.getItemBySlot(slot);
            if (s.isEmpty()) continue;
            evilSelf.setItemSlot(slot, ItemStack.EMPTY);
            if (!player.getInventory().add(s)) player.drop(s, false);
        }

        evilSelf.discard();

        giveTigerTalisman(player);

        heldStack.setCount(0);
        return true;
    }

    private static void giveTigerTalisman(Player player) {
        player.getPersistentData().remove("tiger_split_active");
        player.getPersistentData().remove("tiger_split_yin");

        player.removeEffect(TalismanEffects.LACK_YIN.get());
        player.removeEffect(TalismanEffects.LACK_YANG.get());

        player.setHealth(player.getMaxHealth());

        ItemStack tiger = new ItemStack(TalismanJackiechanModItems.TIGER_TALISMAN.get());
        if (!player.getInventory().add(tiger)) player.drop(tiger, false);
    }
}