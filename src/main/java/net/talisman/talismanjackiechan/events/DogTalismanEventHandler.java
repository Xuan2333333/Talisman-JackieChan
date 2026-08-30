package net.talisman.talismanjackiechan.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;
import net.talisman.talismanjackiechan.item.DogTalismanItem;
import net.talisman.talismanjackiechan.procedures.GouProcedure;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicBoolean;

@Mod.EventBusSubscriber(modid = TalismanJackiechanMod.MODID)
public class DogTalismanEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if (hasDogTalisman(player)) {
                event.setCanceled(true);
                player.setHealth(1.0F);
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 254, false, false, true));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (player.level().isClientSide()) return;

        if (hasDogTalisman(player)) {
            GouProcedure.execute(player);
        }
    }

    private static boolean hasDogTalisman(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof DogTalismanItem) {
                return true;
            }
        }

        if (ModList.get().isLoaded("curios")) {
            AtomicBoolean found = new AtomicBoolean(false);
            CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
                handler.getCurios().forEach((id, stacksHandler) -> {
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                        if (stack.getItem() instanceof DogTalismanItem) {
                            found.set(true);
                            break;
                        }
                    }
                });
            });
            if (found.get()) {
                return true;
            }
        }

        return false;
    }
}