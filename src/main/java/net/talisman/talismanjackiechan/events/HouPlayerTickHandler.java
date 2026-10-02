package net.talisman.talismanjackiechan.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.talisman.talismanjackiechan.morph.MorphAbility;
import net.talisman.talismanjackiechan.morph.MorphEntry;
import net.talisman.talismanjackiechan.morph.MorphRegistry;
import net.talisman.talismanjackiechan.network.HouMorphSyncPacket;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HouPlayerTickHandler {

    private static final UUID MORPH_SPEED_ID  = UUID.fromString("a3c1e9b0-7d24-4c8a-9f11-2b6e4d8a1c77");
    private static final UUID MORPH_SWIM_ID   = UUID.fromString("b4d2f0c1-8e35-4d9b-a022-3c7f5e9b2d88");
    private static final UUID MORPH_ARMOR_ID  = UUID.fromString("c5e3a1d2-9f46-4eac-b133-4d8a6f1c3e99");
    private static final UUID MORPH_DAMAGE_ID = UUID.fromString("d6f4b2e3-a057-4fbd-c244-5e9b7a2d4f00");
    private static final UUID MORPH_JUMP_ID   = UUID.fromString("e7a5c3f4-b168-4ace-d355-6f0c8b3e5a11");

    private static final String TAG_AIR           = "hou_air";
    private static final String TAG_RAM_COOLDOWN  = "hou_ram_cooldown";
    private static final String TAG_RAM_DASH_TICK = "hou_ram_dash";

    private static final long AGGRO_MEMORY_TICKS = 600L;

    private static final int RAM_COOLDOWN_TICKS = 30;
    private static final int RAM_DASH_TICKS = 6;

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) resyncMorph(player);
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) resyncMorph(player);
    }

    @SubscribeEvent
    public static void onChangeDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) resyncMorph(player);
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
            if (other == player) continue;
            if (!Hou1Procedure.isPlayerTransformed(other)) continue;
            String otherType = other.getPersistentData().getString(Hou1Procedure.TAG_PLAYER_FORM_TYPE);
            if (otherType == null || otherType.isEmpty()) otherType = "minecraft:pig";
            NetworkHandler.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> player),
                    new HouMorphSyncPacket(other.getUUID(), true, otherType)
            );
        }
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getEntity() instanceof ServerPlayer watcher)) return;
        if (!(event.getTarget() instanceof ServerPlayer target)) return;
        if (!Hou1Procedure.isPlayerTransformed(target)) return;
        String typeId = target.getPersistentData().getString(Hou1Procedure.TAG_PLAYER_FORM_TYPE);
        if (typeId == null || typeId.isEmpty()) typeId = "minecraft:pig";
        NetworkHandler.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> watcher),
                new HouMorphSyncPacket(target.getUUID(), true, typeId)
        );
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!Hou1Procedure.isPlayerTransformed(player)) return;

        int hou = Hou1Procedure.getPlayerForm(player);
        MorphEntry entry = MorphRegistry.byHou(hou);
        if (entry == null) return;

        if (entry.has(MorphAbility.HIGH_BOUNCE)) {
            Vec3 v = player.getDeltaMovement();
            player.setDeltaMovement(v.x, v.y + 0.12D, v.z);
        }
    }


    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        if (!Hou1Procedure.isPlayerTransformed(player)) return;

        LivingEntity target = event.getEntity();

        CompoundTag data = player.getPersistentData();
        data.putLong("hou_aggro_" + target.getUUID(), player.level().getGameTime());

        int hou = Hou1Procedure.getPlayerForm(player);
        if (hou == 26 && event.getSource().getDirectEntity() == player) {
            int duration;
            Difficulty diff = player.level().getDifficulty();
            if (diff == Difficulty.HARD) duration = 420;
            else if (diff == Difficulty.NORMAL) duration = 300;
            else duration = 140;
            target.addEffect(new MobEffectInstance(MobEffects.POISON, duration, 0));
        }
    }

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity attacker = event.getEntity();
        LivingEntity newTarget = event.getNewTarget();

        if (!(newTarget instanceof ServerPlayer player)) return;
        if (!Hou1Procedure.isPlayerTransformed(player)) return;

        if (!(attacker instanceof Monster)) return;
        if (attacker == player) return;

        int hou = Hou1Procedure.getPlayerForm(player);

        if ((hou == 25 || hou == 26)
                && (attacker instanceof Spider || attacker instanceof CaveSpider)) {
            event.setNewTarget(null);
            return;
        }

        CompoundTag data = player.getPersistentData();
        long lastAttack = data.getLong("hou_aggro_" + attacker.getUUID());
        long now = player.level().getGameTime();
        if (lastAttack <= 0L || now - lastAttack > AGGRO_MEMORY_TICKS) {
            event.setNewTarget(null);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        AttributeInstance swim  = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        AttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance armor = player.getAttribute(Attributes.ARMOR);
        AttributeInstance dmg   = player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance jump  = player.getAttribute(Attributes.JUMP_STRENGTH);

        if (!Hou1Procedure.isPlayerTransformed(player)) {
            clearAllModifiers(swim, speed, armor, dmg, jump);
            player.getPersistentData().remove(TAG_AIR);
            player.getPersistentData().remove(TAG_RAM_COOLDOWN);
            player.getPersistentData().remove(TAG_RAM_DASH_TICK);
            player.removeEffect(MobEffects.GLOWING);
            player.removeEffect(MobEffects.WATER_BREATHING);
            return;
        }

        GameType gt = player.gameMode.getGameModeForPlayer();
        boolean survivalLike = gt == GameType.SURVIVAL || gt == GameType.ADVENTURE;
        int hou = Hou1Procedure.getPlayerForm(player);
        MorphEntry entry = MorphRegistry.byHou(hou);

        net.talisman.talismanjackiechan.item.RoosterTalismanItem.updateFlight(player);

        if (entry == null) {
            clearAllModifiers(swim, speed, armor, dmg, jump);
            player.getPersistentData().remove(TAG_AIR);
            player.removeEffect(MobEffects.GLOWING);
            return;
        }

        if (entry.landSpeedBonus() != 0) {
            addMultiply(speed, MORPH_SPEED_ID, "hou_morph_speed", entry.landSpeedBonus());
        } else {
            removeModifier(speed, MORPH_SPEED_ID);
        }

        if (entry.swimSpeedBonus() != 0 && player.isInWaterOrBubble()) {
            addMultiply(swim, MORPH_SWIM_ID, "hou_morph_swim", entry.swimSpeedBonus());
        } else {
            removeModifier(swim, MORPH_SWIM_ID);
        }

        if (entry.armorBonus() != 0) {
            addFlat(armor, MORPH_ARMOR_ID, "hou_morph_armor", entry.armorBonus());
        } else {
            removeModifier(armor, MORPH_ARMOR_ID);
        }

        if (entry.attackBonus() != 0) {
            addMultiply(dmg, MORPH_DAMAGE_ID, "hou_morph_damage", entry.attackBonus());
        } else {
            removeModifier(dmg, MORPH_DAMAGE_ID);
        }

        if (entry.jumpBonus() != 0) {
            addFlat(jump, MORPH_JUMP_ID, "hou_morph_jump", entry.jumpBonus());
            player.fallDistance = 0.0F;
        } else {
            removeModifier(jump, MORPH_JUMP_ID);
        }

        if (entry.has(MorphAbility.GLOWING)) {
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20, 0, false, false, false));
        } else {
            player.removeEffect(MobEffects.GLOWING);
        }

        if (entry.has(MorphAbility.SLOW_FALL)
                && !player.onGround() && !player.getAbilities().flying
                && player.getDeltaMovement().y < 0.0D) {
            Vec3 v = player.getDeltaMovement();
            player.setDeltaMovement(v.x, v.y * 0.65D, v.z);
            player.fallDistance = 0.0F;
        }

        if (entry.has(MorphAbility.RAM)) {
            tickGoatRam(player);
        } else {
            player.getPersistentData().remove(TAG_RAM_COOLDOWN);
            player.getPersistentData().remove(TAG_RAM_DASH_TICK);
        }

        if (!entry.has(MorphAbility.AQUATIC) || !survivalLike) {
            player.getPersistentData().remove(TAG_AIR);
            return;
        }

        if (player.isInWaterOrBubble()) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.WATER_BREATHING, 20, 0, false, false, false));
        }
        player.getPersistentData().remove(TAG_AIR);
    }

    private static void tickGoatRam(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();

        int dash = data.getInt(TAG_RAM_DASH_TICK);
        if (dash > 0) {
            Vec3 look = player.getLookAngle();
            Vec3 forward = new Vec3(look.x, 0, look.z);
            if (forward.lengthSqr() > 1.0E-4) {
                forward = forward.normalize();
                double dashSpeed = 0.55D;
                Vec3 cur = player.getDeltaMovement();
                player.setDeltaMovement(
                        forward.x * dashSpeed,
                        cur.y,
                        forward.z * dashSpeed
                );
                player.hurtMarked = true;
            }
            data.putInt(TAG_RAM_DASH_TICK, dash - 1);
            player.fallDistance = 0.0F;
        }

        int cd = data.getInt(TAG_RAM_COOLDOWN);
        if (cd > 0) {
            data.putInt(TAG_RAM_COOLDOWN, cd - 1);
            return;
        }

        if (!player.isSprinting()) return;
        if (dash > 0) return;

        Vec3 look = player.getLookAngle();
        Vec3 forward = new Vec3(look.x, 0, look.z);
        if (forward.lengthSqr() < 1.0E-4) return;
        forward = forward.normalize();

        Vec3 origin = player.position().add(forward.scale(0.4D));
        AABB searchBox = new AABB(
                origin.x - 0.7, player.getY() - 0.2, origin.z - 0.7,
                origin.x + 0.7, player.getY() + player.getBbHeight(), origin.z + 0.7
        ).expandTowards(forward.scale(1.4));

        List<Entity> candidates = player.level().getEntities(player, searchBox,
                e -> e instanceof LivingEntity
                        && e.isAlive()
                        && !e.isSpectator()
                        && e != player);

        Entity hit = null;
        for (Entity e : candidates) {
            Vec3 to = new Vec3(e.getX() - player.getX(), 0, e.getZ() - player.getZ());
            if (to.lengthSqr() < 1.0E-4) { hit = e; break; }
            Vec3 toNorm = to.normalize();
            if (toNorm.dot(forward) > 0.5) {
                hit = e;
                break;
            }
        }

        if (hit == null) return;

        double dx = hit.getX() - player.getX();
        double dz = hit.getZ() - player.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (dist < 1.0E-4) { dx = forward.x; dz = forward.z; dist = 1.0; }

        double strength = 1.4D;
        Vec3 kick = new Vec3(dx / dist * strength, 0.45D, dz / dist * strength);
        hit.push(kick.x, kick.y, kick.z);
        hit.hurtMarked = true;

        if (hit instanceof LivingEntity living) {
            living.hurt(player.damageSources().mobAttack(player), 4.0F);
            living.hurtMarked = true;
        }

        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.GOAT_RAM_IMPACT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        data.putInt(TAG_RAM_DASH_TICK, RAM_DASH_TICKS);
        data.putInt(TAG_RAM_COOLDOWN, RAM_COOLDOWN_TICKS);
    }

    private static void clearAllModifiers(AttributeInstance... attrs) {
        UUID[] ids = {
                MORPH_SPEED_ID, MORPH_SWIM_ID, MORPH_ARMOR_ID,
                MORPH_DAMAGE_ID, MORPH_JUMP_ID
        };
        for (AttributeInstance attr : attrs) {
            if (attr == null) continue;
            for (UUID id : ids) {
                if (attr.getModifier(id) != null) attr.removeModifier(id);
            }
        }
    }

    private static void addMultiply(AttributeInstance attr, UUID id, String name, double amount) {
        if (attr == null || attr.getModifier(id) != null) return;
        attr.addTransientModifier(new AttributeModifier(
                id, name, amount, AttributeModifier.Operation.MULTIPLY_TOTAL));
    }

    private static void addFlat(AttributeInstance attr, UUID id, String name, double amount) {
        if (attr == null || attr.getModifier(id) != null) return;
        attr.addTransientModifier(new AttributeModifier(
                id, name, amount, AttributeModifier.Operation.ADDITION));
    }

    private static void removeModifier(AttributeInstance attr, UUID id) {
        if (attr != null && attr.getModifier(id) != null) {
            attr.removeModifier(id);
        }
    }
}