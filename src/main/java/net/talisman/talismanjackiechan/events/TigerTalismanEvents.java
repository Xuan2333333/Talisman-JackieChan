package net.talisman.talismanjackiechan.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.talisman.talismanjackiechan.entity.EvilselfEntity;

@Mod.EventBusSubscriber(
        modid = "talisman_jackiechan",
        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TigerTalismanEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        Entity attacker = event.getSource().getEntity();
        if (!(attacker instanceof Player player)) return;

        LivingEntity victim = event.getEntity();

        if (victim instanceof EvilselfEntity) return;

        MinecraftServer server = player.getServer();
        if (server == null) return;

        for (ServerLevel level : server.getAllLevels()) {
            for (Entity e : level.getAllEntities()) {
                if (!(e instanceof EvilselfEntity evil)) continue;

                boolean isOwner = evil.getOwnerUUID()
                        .map(u -> u.equals(player.getUUID()))
                        .orElse(false);
                if (!isOwner) continue;
                if (evil.isEnraged()) continue;
                if (evil.level() != player.level()) continue;

                LivingEntity current = evil.getTarget();
                if (current == null || !current.isAlive()) {
                    evil.setTarget(victim);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(
            net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract event) {

        if (event.getEntity().level().isClientSide()) return;

        if (!event.getEntity().isShiftKeyDown()) return;

        net.minecraft.world.entity.Entity target = event.getTarget();
        if (!(target instanceof net.talisman.talismanjackiechan.entity.EvilselfEntity evilSelf)) return;

        net.minecraft.world.entity.player.Player player = event.getEntity();
        net.minecraft.world.item.ItemStack held = event.getItemStack();

        if (net.talisman.talismanjackiechan.procedures.TigerTalismanCombineHelper
                .tryCombineWithEvilSelf(player, held, evilSelf)) {
            event.setCanceled(true);
            event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(net.minecraftforge.event.TickEvent.PlayerTickEvent event) {
        if (event.phase != net.minecraftforge.event.TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        net.minecraft.world.entity.player.Player player = event.player;
        net.minecraft.nbt.CompoundTag data = player.getPersistentData();

        if (!data.getBoolean("tiger_split_active")) return;

        boolean splitYin = data.getBoolean("tiger_split_yin");
        net.minecraft.world.effect.MobEffect want = splitYin
                ? net.talisman.talismanjackiechan.init.TalismanEffects.LACK_YIN.get()
                : net.talisman.talismanjackiechan.init.TalismanEffects.LACK_YANG.get();
        net.minecraft.world.effect.MobEffect other = splitYin
                ? net.talisman.talismanjackiechan.init.TalismanEffects.LACK_YANG.get()
                : net.talisman.talismanjackiechan.init.TalismanEffects.LACK_YIN.get();

        if (player.hasEffect(other)) player.removeEffect(other);

        if (!player.hasEffect(want)) {
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                    want, -1, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        net.minecraft.nbt.CompoundTag oldData = event.getOriginal().getPersistentData();
        if (!oldData.getBoolean("tiger_split_active")) return;

        net.minecraft.nbt.CompoundTag newData = event.getEntity().getPersistentData();
        newData.putBoolean("tiger_split_active", true);
        newData.putBoolean("tiger_split_yin", oldData.getBoolean("tiger_split_yin"));

    }
}