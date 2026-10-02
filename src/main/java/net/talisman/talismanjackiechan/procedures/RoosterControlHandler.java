package net.talisman.talismanjackiechan.procedures;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RoosterControlHandler {

    public static final float MIN_DISTANCE = 1.0F;
    public static final float MAX_DISTANCE = 72.0F;

    private static final Map<UUID, ControlData> ACTIVE = new ConcurrentHashMap<>();
    private static final Map<UUID, ChunkPos> FORCE_TICKETS = new ConcurrentHashMap<>();
    private static final int FORCE_RADIUS = 1;

    public static void startOrUpdate(ServerPlayer player, UUID targetUUID, float distance) {
        startOrUpdate(player, targetUUID, distance, false);
    }

    public static void startOrUpdate(ServerPlayer player, UUID targetUUID, float distance, boolean fromHotkey) {
        if (player == null || targetUUID == null) {
            return;
        }

        boolean allowed;
        if (fromHotkey) {
            allowed = RoosterTalismanItem.hasRoosterTalisman(player);
        } else {
            allowed = player.getMainHandItem().getItem() instanceof RoosterTalismanItem;
        }

        if (!allowed) {
            return;
        }

        distance = Mth.clamp(distance, MIN_DISTANCE, MAX_DISTANCE);

        Entity target = player.serverLevel().getEntity(targetUUID);
        if (target == null || !target.isAlive() || target.isRemoved() || target == player) {
            return;
        }
        if (!(target instanceof LivingEntity)) {
            return;
        }

        ControlData existing = ACTIVE.get(player.getUUID());
        if (existing == null) {
            ACTIVE.put(player.getUUID(), new ControlData(targetUUID, distance, fromHotkey));
            if (target instanceof Mob mob) {
                mob.setNoAi(true);
            }
            maintainForceLoad(player, target);
        } else if (existing.targetUUID.equals(targetUUID)) {
            existing.distance = distance;
            existing.fromHotkey = fromHotkey;
        }
    }

    public static void stop(ServerPlayer player) {
        if (player == null) {
            return;
        }
        ControlData data = ACTIVE.remove(player.getUUID());
        clearForceLoad(player);

        if (data == null) {
            return;
        }
        Entity target = player.serverLevel().getEntity(data.targetUUID);
        restoreAi(target);
    }

    public static void tickAll(MinecraftServer server) {
        if (server == null) {
            return;
        }

        Iterator<Map.Entry<UUID, ControlData>> it = ACTIVE.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, ControlData> entry = it.next();
            UUID playerId = entry.getKey();
            ControlData data = entry.getValue();

            ServerPlayer player = server.getPlayerList().getPlayer(playerId);

            boolean stillAllowed = false;
            if (player != null) {
                if (data.fromHotkey) {
                    stillAllowed = RoosterTalismanItem.hasRoosterTalisman(player);
                } else {
                    stillAllowed = player.getMainHandItem().getItem() instanceof RoosterTalismanItem;
                }
            }

            if (player == null || !stillAllowed) {
                if (player != null) {
                    Entity t = player.serverLevel().getEntity(data.targetUUID);
                    restoreAi(t);
                    clearForceLoad(player);
                }
                it.remove();
                continue;
            }

            Entity target = player.serverLevel().getEntity(data.targetUUID);

            if (target == null || !target.isAlive() || target.isRemoved()) {
                restoreAi(target);
                clearForceLoad(player);
                it.remove();
                continue;
            }

            try {
                fixEntityPosition(player, target, data.distance);
                maintainForceLoad(player, target);
            } catch (Exception e) {
                restoreAi(target);
                clearForceLoad(player);
                it.remove();
            }
        }
    }

    private static void fixEntityPosition(ServerPlayer player, Entity target, float distance) {
        if (player == null || target == null || !target.isAlive() || target.isRemoved()) {
            return;
        }
        if (player.level() != target.level()) {
            return;
        }

        distance = Mth.clamp(distance, MIN_DISTANCE, MAX_DISTANCE);

        Vec3 eye = player.getEyePosition(1.0F);
        Vec3 look = player.getLookAngle();
        Vec3 ideal = eye.add(look.scale(distance));

        ClipContext ctx = new ClipContext(
                eye, ideal,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        );
        BlockHitResult hit = player.level().clip(ctx);
        Vec3 point = ideal;
        if (hit.getType() != HitResult.Type.MISS) {
            double margin = Math.max(0.3, target.getBbWidth() * 0.5 + 0.05);
            point = hit.getLocation().subtract(look.scale(margin));
        }

        double x = point.x;
        double y = point.y - target.getBbHeight() * 0.5;
        double z = point.z;

        for (int i = 0; i < 6; i++) {
            AABB box = target.getDimensions(target.getPose()).makeBoundingBox(x, y, z);
            if (!player.level().noCollision(target, box)) {
                distance = Math.max(MIN_DISTANCE, distance - 0.35F);
                point = eye.add(look.scale(distance));
                ClipContext ctx2 = new ClipContext(
                        eye, point,
                        ClipContext.Block.COLLIDER,
                        ClipContext.Fluid.NONE,
                        player
                );
                BlockHitResult hit2 = player.level().clip(ctx2);
                if (hit2.getType() != HitResult.Type.MISS) {
                    double margin = Math.max(0.3, target.getBbWidth() * 0.5 + 0.05);
                    point = hit2.getLocation().subtract(look.scale(margin));
                }
                x = point.x;
                y = point.y - target.getBbHeight() * 0.5;
                z = point.z;
            } else {
                break;
            }
        }

        target.moveTo(x, y, z, target.getYRot(), target.getXRot());
        target.setDeltaMovement(Vec3.ZERO);
        target.fallDistance = 0.0F;
        target.hurtMarked = true;

        if (target instanceof Mob mob) {
            mob.setNoAi(true);
        }
    }

    private static void maintainForceLoad(ServerPlayer player, Entity target) {
        if (player == null || target == null) {
            return;
        }
        if (!(player.level() instanceof ServerLevel level)) {
            return;
        }
        if (target.level() != level) {
            return;
        }

        ChunkPos newPos = new ChunkPos(target.blockPosition());
        ChunkPos oldPos = FORCE_TICKETS.get(player.getUUID());
        var source = level.getChunkSource();

        if (oldPos != null && !oldPos.equals(newPos)) {
            source.removeRegionTicket(TicketType.FORCED, oldPos, FORCE_RADIUS, oldPos);
        }

        if (oldPos == null || !oldPos.equals(newPos)) {
            source.addRegionTicket(TicketType.FORCED, newPos, FORCE_RADIUS, newPos);
            FORCE_TICKETS.put(player.getUUID(), newPos);
        }
    }

    private static void clearForceLoad(ServerPlayer player) {
        if (player == null) {
            return;
        }
        ChunkPos pos = FORCE_TICKETS.remove(player.getUUID());
        if (pos == null) {
            return;
        }
        if (player.level() instanceof ServerLevel level) {
            level.getChunkSource().removeRegionTicket(TicketType.FORCED, pos, FORCE_RADIUS, pos);
        }
    }

    private static void restoreAi(Entity target) {
        if (target instanceof Mob mob) {
            mob.setNoAi(false);
        }
    }

    private static class ControlData {
        final UUID targetUUID;
        float distance;
        boolean fromHotkey;

        ControlData(UUID targetUUID, float distance, boolean fromHotkey) {
            this.targetUUID = targetUUID;
            this.distance = distance;
            this.fromHotkey = fromHotkey;
        }
    }
}