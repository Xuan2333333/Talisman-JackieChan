package net.talisman.talismanjackiechan.events;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.talisman.talismanjackiechan.procedures.RoosterControlHandler;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            RoosterControlHandler.tickAll(event.getServer());
        }
    }
}