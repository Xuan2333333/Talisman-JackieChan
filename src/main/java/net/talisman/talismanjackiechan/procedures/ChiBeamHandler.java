package net.talisman.talismanjackiechan.procedures;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.talisman.talismanjackiechan.item.MagicLizardItem;
import net.talisman.talismanjackiechan.item.MagicPufferfishItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "talisman_jackiechan", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChiBeamHandler {

    private static final float MAX_RANGE = 64.0F;
    private static final int DAMAGE_COOLDOWN_TICKS = 3;
    private static final float DAMAGE = 4.0F;

    private static final Map<UUID, Boolean> leftActive = new HashMap<>();
    private static final Map<UUID, Boolean> rightActive = new HashMap<>();

    private static final Map<UUID, Integer> leftCooldown = new HashMap<>();
    private static final Map<UUID, Integer> rightCooldown = new HashMap<>();


    public static void handleBeamState(ServerPlayer player, boolean left, boolean right) {
        UUID id = player.getUUID();
        leftActive.put(id, left);
        rightActive.put(id, right);

        if (!left) leftCooldown.remove(id);
        if (!right) rightCooldown.remove(id);
    }


    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Map<UUID, Boolean> leftCopy = new HashMap<>(leftActive);
        Map<UUID, Boolean> rightCopy = new HashMap<>(rightActive);

        for (Map.Entry<UUID, Boolean> e : leftCopy.entrySet()) {
            if (!e.getValue()) continue;
            ServerPlayer player = event.getServer().getPlayerList().getPlayer(e.getKey());
            if (player == null || !player.isAlive()) {
                leftActive.remove(e.getKey());
                leftCooldown.remove(e.getKey());
                continue;
            }
            tryDamage(player, true);
        }

        for (Map.Entry<UUID, Boolean> e : rightCopy.entrySet()) {
            if (!e.getValue()) continue;
            ServerPlayer player = event.getServer().getPlayerList().getPlayer(e.getKey());
            if (player == null || !player.isAlive()) {
                rightActive.remove(e.getKey());
                rightCooldown.remove(e.getKey());
                continue;
            }
            tryDamage(player, false);
        }
    }

    private static void tryDamage(ServerPlayer player, boolean leftHand) {
        Item item = leftHand ? player.getOffhandItem().getItem() : player.getMainHandItem().getItem();
        if (!(item instanceof MagicPufferfishItem || item instanceof MagicLizardItem)) {
            if (leftHand) leftActive.put(player.getUUID(), false);
            else rightActive.put(player.getUUID(), false);
            return;
        }

        Map<UUID, Integer> cdMap = leftHand ? leftCooldown : rightCooldown;
        int cd = cdMap.getOrDefault(player.getUUID(), 0);
        if (cd > 0) {
            cdMap.put(player.getUUID(), cd - 1);
            return;
        }

        Vec3 look = player.getViewVector(1.0F);
        Vec3 start = getHandTip(player, look, leftHand);
        Vec3 endIdeal = start.add(look.scale(MAX_RANGE));

        BlockHitResult blockHit = player.level().clip(new ClipContext(
                start, endIdeal,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));
        double maxLen = blockHit.getType() != HitResult.Type.MISS
                ? start.distanceTo(blockHit.getLocation()) : MAX_RANGE;

        EntityHitResult entityHit = getEntityHit(player, start, look, maxLen);
        if (entityHit != null && entityHit.getEntity() instanceof LivingEntity target) {
            target.hurt(new DamageSource(
                    player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE,
                                    new ResourceLocation("talisman_jackiechan:chi_magic")))
            ), DAMAGE);

            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
            cdMap.put(player.getUUID(), DAMAGE_COOLDOWN_TICKS);
        }
    }

    private static Vec3 getHandTip(Player player, Vec3 look, boolean leftHand) {
        Vec3 eye = player.getEyePosition(1.0F);
        float yawRad = player.getYRot() * ((float) Math.PI / 180.0F);
        Vec3 right = new Vec3(-Math.cos(yawRad), 0.0, -Math.sin(yawRad));

        double side = leftHand ? -0.38 : 0.38;
        return eye.add(look.scale(0.52)).add(right.scale(side)).add(0, -0.35, 0);
    }

    private static EntityHitResult getEntityHit(Player player, Vec3 start, Vec3 look, double maxLen) {
        Vec3 end = start.add(look.scale(maxLen));
        AABB box = player.getBoundingBox().expandTowards(look.scale(maxLen)).inflate(1.0);

        EntityHitResult result = null;
        double closest = maxLen;

        for (Entity e : player.level().getEntities(player, box, ent ->
                ent instanceof LivingEntity && ent.isAlive() && !ent.isSpectator() && ent != player)) {
            AABB bb = e.getBoundingBox().inflate(0.25);
            var opt = bb.clip(start, end);
            if (opt.isPresent()) {
                double dist = start.distanceTo(opt.get());
                if (dist < closest) {
                    closest = dist;
                    result = new EntityHitResult(e, opt.get());
                }
            }
        }
        return result;
    }
}