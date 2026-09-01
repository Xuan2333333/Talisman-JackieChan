package net.talisman.talismanjackiechan.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.talisman.talismanjackiechan.network.HouMorphSyncPacket;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HouPlayerTickHandler {

    private static final UUID DOLPHIN_SWIM_ID = UUID.fromString("a3c1e9b0-7d24-4c8a-9f11-2b6e4d8a1c77");
    private static final UUID HORSE_SPEED_ID = UUID.fromString("b4d2f0c1-8e35-4d9b-a022-3c7f5e9b2d88");
    private static final String TAG_AIR = "hou_air";

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            resyncMorph(player);
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            resyncMorph(player);
        }
    }

    @SubscribeEvent
    public static void onChangeDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            resyncMorph(player);
        }
    }

    public static void resyncMorph(ServerPlayer player) {
        boolean active = Hou1Procedure.isPlayerTransformed(player);
        String typeId = active
                ? player.getPersistentData().getString(Hou1Procedure.TAG_PLAYER_FORM_TYPE)
                : "minecraft:empty";
        if (active && (typeId == null || typeId.isEmpty())) {
            typeId = "minecraft:pig";
        }
        Hou1Procedure.syncMorph(player, active, typeId);

        if (active) {
            net.talisman.talismanjackiechan.item.RoosterTalismanItem.updateFlight(player);
        }
        player.refreshDimensions();

        for (ServerPlayer other : player.server.getPlayerList().getPlayers()) {
            if (other == player) {
                continue;
            }
            if (!Hou1Procedure.isPlayerTransformed(other)) {
                continue;
            }
            String otherType = other.getPersistentData().getString(Hou1Procedure.TAG_PLAYER_FORM_TYPE);
            if (otherType == null || otherType.isEmpty()) {
                otherType = "minecraft:pig";
            }
            NetworkHandler.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> player),
                    new HouMorphSyncPacket(other.getUUID(), true, otherType)
            );
        }
    }
    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getEntity() instanceof ServerPlayer watcher)) {
            return;
        }
        if (!(event.getTarget() instanceof ServerPlayer target)) {
            return;
        }
        if (!Hou1Procedure.isPlayerTransformed(target)) {
            return;
        }
        String typeId = target.getPersistentData().getString(Hou1Procedure.TAG_PLAYER_FORM_TYPE);
        if (typeId == null || typeId.isEmpty()) {
            typeId = "minecraft:pig";
        }
        NetworkHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> watcher),
                new HouMorphSyncPacket(target.getUUID(), true, typeId)
        );
    }
    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (!Hou1Procedure.isPlayerTransformed(player)) {
            return;
        }
        if (Hou1Procedure.getPlayerForm(player) != 18) {
            return;
        }
        Vec3 v = player.getDeltaMovement();
        player.setDeltaMovement(v.x, v.y + 0.12D, v.z);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (!(event.player instanceof ServerPlayer player)) {
            return;
        }

        AttributeInstance swim = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (!Hou1Procedure.isPlayerTransformed(player)) {
            removeModifier(swim, DOLPHIN_SWIM_ID);
            removeModifier(speed, HORSE_SPEED_ID);
            player.getPersistentData().remove(TAG_AIR);
            return;
        }

        GameType gt = player.gameMode.getGameModeForPlayer();
        boolean survivalLike = gt == GameType.SURVIVAL || gt == GameType.ADVENTURE;
        int hou = Hou1Procedure.getPlayerForm(player);

        net.talisman.talismanjackiechan.item.RoosterTalismanItem.updateFlight(player);

        if (hou == 12 && player.isInWaterOrBubble()) {
            addMultiply(swim, DOLPHIN_SWIM_ID, "hou_dolphin_swim", 1.2D);
        } else {
            removeModifier(swim, DOLPHIN_SWIM_ID);
        }

        if (hou == 2) {
            addMultiply(speed, HORSE_SPEED_ID, "hou_horse_speed", 0.28D);
        } else {
            removeModifier(speed, HORSE_SPEED_ID);
        }

        if (hou == 7 && !player.onGround() && !player.getAbilities().flying
                && player.getDeltaMovement().y < 0.0D) {
            Vec3 v = player.getDeltaMovement();
            player.setDeltaMovement(v.x, v.y * 0.65D, v.z);
            player.fallDistance = 0.0F;
        }

        if (!Hou1Procedure.aquaticForm(hou) || !survivalLike) {
            player.getPersistentData().remove(TAG_AIR);
            return;
        }

        CompoundTag data = player.getPersistentData();
        int max = player.getMaxAirSupply();

        if (player.isInWaterOrBubble()) {
            data.putInt(TAG_AIR, max);
            player.setAirSupply(max);
        } else {
            int air = data.contains(TAG_AIR) ? data.getInt(TAG_AIR) : max;
            air = Math.max(-20, air - 1);
            data.putInt(TAG_AIR, air);
            player.setAirSupply(air);
            if (air <= -20) {
                data.putInt(TAG_AIR, 0);
                player.setAirSupply(0);
                player.hurt(player.damageSources().drown(), 2.0F);
            }
        }
    }

    private static void addMultiply(AttributeInstance attr, UUID id, String name, double amount) {
        if (attr == null || attr.getModifier(id) != null) {
            return;
        }
        attr.addTransientModifier(new AttributeModifier(
                id, name, amount, AttributeModifier.Operation.MULTIPLY_TOTAL
        ));
    }

    private static void removeModifier(AttributeInstance attr, UUID id) {
        if (attr != null && attr.getModifier(id) != null) {
            attr.removeModifier(id);
        }
    }
}