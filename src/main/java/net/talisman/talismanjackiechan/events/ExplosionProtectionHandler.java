package net.talisman.talismanjackiechan.events;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExplosionProtectionHandler {

    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        if (event.getLevel().isClientSide()) return;
        event.getAffectedEntities().removeIf(entity -> {
            if (!(entity instanceof ItemEntity item)) return false;
            return TalismanItemProtectionHandler.isProtectedItem(item.getItem());
        });
    }
}