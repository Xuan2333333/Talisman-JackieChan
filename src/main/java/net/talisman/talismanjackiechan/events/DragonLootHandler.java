package net.talisman.talismanjackiechan.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.talisman.talismanjackiechan.config.TalismanJackiechanConfig;
import net.talisman.talismanjackiechan.data.TalismanObtainedData;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DragonLootHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof EnderDragon)) return;
        if (!(event.getEntity().level() instanceof ServerLevel level)) return;
        if (!TalismanJackiechanConfig.UNIQUE_TALISMAN_PER_SAVE.get()) return;

        TalismanObtainedData data = TalismanObtainedData.get(level);

        event.getDrops().removeIf(itemEntity -> {
            ItemStack stack = itemEntity.getItem();
            if (stack == null || stack.isEmpty()) return false;
            if (!TalismanItemProtectionHandler.isProtectedItem(stack)) return false;
            ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
            if (id == null) return false;

            if (data.isObtained(id)) {
                return true;
            } else {
                data.markObtained(id);
                return false;
            }
        });
    }
}