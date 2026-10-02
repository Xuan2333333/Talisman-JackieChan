package net.talisman.talismanjackiechan.events;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TalismanItemProtectionHandler {

    private static final List<RegistryObject<Item>> REGISTERED = List.of(
            TalismanJackiechanModItems.TIGER_TALISMAN,
            TalismanJackiechanModItems.MONKEY_TALISMAN,
            TalismanJackiechanModItems.RAT_TALISMAN,
            TalismanJackiechanModItems.OX_TALISMAN,
            TalismanJackiechanModItems.RABBIT_TALISMAN,
            TalismanJackiechanModItems.DRAGON_TALISMAN,
            TalismanJackiechanModItems.SNAKE_TALISMAN,
            TalismanJackiechanModItems.HORSE_TALISMAN,
            TalismanJackiechanModItems.SHEEP_TALISMAN,
            TalismanJackiechanModItems.ROOSTER_TALISMAN,
            TalismanJackiechanModItems.DOG_TALISMAN,
            TalismanJackiechanModItems.PIG_TALISMAN,
            TalismanJackiechanModItems.TIGER_TALISMAN_YIN,
            TalismanJackiechanModItems.TIGER_TALISMAN_YANG
    );

    private static final Set<Item> EXTRA = new HashSet<>();

    private static Set<Item> CACHE;

    private static Set<Item> protectedItems() {
        if (CACHE == null) {
            Set<Item> s = new HashSet<>();
            for (RegistryObject<Item> ro : REGISTERED) {
                if (ro.isPresent()) {
                    s.add(ro.get());
                }
            }
            s.addAll(EXTRA);
            CACHE = s;
        }
        return CACHE;
    }

    public static void protect(Item item) {
        if (item == null) return;
        EXTRA.add(item);
        if (CACHE != null) {
            CACHE.add(item);
        }
    }

    public static boolean isProtectedItem(ItemStack stack) {
        return !stack.isEmpty() && protectedItems().contains(stack.getItem());
    }

    private static boolean isProtected(ItemStack stack) {
        return isProtectedItem(stack);
    }

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        if (!isProtected(itemEntity.getItem())) return;

        itemEntity.setInvulnerable(true);
        itemEntity.setExtendedLifetime();
    }

    @SubscribeEvent
    public static void onItemExpire(ItemExpireEvent event) {
        if (isProtected(event.getEntity().getItem())) {
            event.setExtraLife(Integer.MAX_VALUE / 2);
        }
    }
}