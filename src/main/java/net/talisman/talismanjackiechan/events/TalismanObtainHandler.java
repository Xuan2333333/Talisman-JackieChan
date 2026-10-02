package net.talisman.talismanjackiechan.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.talisman.talismanjackiechan.config.TalismanJackiechanConfig;
import net.talisman.talismanjackiechan.data.TalismanObtainedData;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TalismanObtainHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;
        if (player.tickCount % 5 != 0) return;
        if (!TalismanJackiechanConfig.UNIQUE_TALISMAN_PER_SAVE.get()) return;

        scanPlayerInventory(player);
    }

    private static void scanPlayerInventory(ServerPlayer player) {
        ServerLevel overworld = player.server.overworld();
        TalismanObtainedData data = TalismanObtainedData.get(overworld);

        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            tryMarkStack(data, stack);
        }

        ItemStack offhand = player.getOffhandItem();
        tryMarkStack(data, offhand);
    }

    private static void tryMarkStack(TalismanObtainedData data, ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;
        if (!TalismanItemProtectionHandler.isProtectedItem(stack)) return;
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
        if (id != null) {
            data.markObtained(id);
        }
    }
}