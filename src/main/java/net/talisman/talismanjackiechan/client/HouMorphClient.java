package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.WalkAnimationState;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.talisman.talismanjackiechan.mixin.EntityAccessor;
import net.talisman.talismanjackiechan.mixin.RabbitAccessor;
import net.talisman.talismanjackiechan.mixin.WalkAnimationStateAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class HouMorphClient {

    private static final Map<UUID, LivingEntity> SHADOWS = new HashMap<>();
    private static final Map<UUID, Boolean> ACTIVE = new HashMap<>();
    private static final Map<UUID, Boolean> WAS_ON_GROUND = new HashMap<>();

    private static final Map<UUID, Boolean> PREV_ACTIVE = new HashMap<>();

    private static final int RABBIT_HOP_DURATION = 10;
    private static final double RABBIT_MOVE_THRESHOLD = 4.0E-4;

    private static final double SPIDER_CLIMB_SPEED = 0.2D;
    private static final double WALL_PROBE = 0.05D;

    private static int clientAir = 300;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(HouMorphClient.class);
    }

    public static LivingEntity getShadow(UUID id) {
        if (!Boolean.TRUE.equals(ACTIVE.get(id))) return null;
        return SHADOWS.get(id);
    }

    public static boolean isActive(UUID id) {
        return Boolean.TRUE.equals(ACTIVE.get(id));
    }

    public static void handleSync(UUID playerId, boolean active, String entityTypeId) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        if (!active || entityTypeId == null || "minecraft:empty".equals(entityTypeId)) {
            ACTIVE.put(playerId, false);
            SHADOWS.remove(playerId);
            WAS_ON_GROUND.remove(playerId);
            if (mc.player != null && mc.player.getUUID().equals(playerId)) {
                clientAir = mc.player.getMaxAirSupply();
            }

            Player target = mc.level.getPlayerByUUID(playerId);
            if (target != null) {
                target.refreshDimensions();
            }
            return;
        }

        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(entityTypeId));
        if (type == null) {
            ACTIVE.put(playerId, false);
            SHADOWS.remove(playerId);
            return;
        }

        LivingEntity shadow = SHADOWS.get(playerId);
        if (shadow == null || shadow.getType() != type) {
            var created = type.create(mc.level);
            if (!(created instanceof LivingEntity living)) {
                if (created != null) created.discard();
                ACTIVE.put(playerId, false);
                return;
            }
            living.setNoGravity(true);
            living.setSilent(true);
            living.setInvulnerable(true);
            SHADOWS.put(playerId, living);
        }

        ACTIVE.put(playerId, true);
        if (mc.player != null && mc.player.getUUID().equals(playerId)) {
            clientAir = mc.player.getMaxAirSupply();
        }

        Player target = mc.level.getPlayerByUUID(playerId);
        if (target != null) {
            target.refreshDimensions();
        }
    }

    @SubscribeEvent
    public static void onLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        SHADOWS.clear();
        ACTIVE.clear();
        WAS_ON_GROUND.clear();
        PREV_ACTIVE.clear();
    }

    @SubscribeEvent
    public static void onRenderArm(RenderArmEvent event) {
        if (isActive(event.getPlayer().getUUID())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        LocalPlayer local = mc.player;
        if (local != null) {
            UUID id = local.getUUID();

            boolean nowActive = isActive(id);
            boolean prevActive = Boolean.TRUE.equals(PREV_ACTIVE.get(id));
            if (nowActive != prevActive) {
                local.refreshDimensions();
                PREV_ACTIVE.put(id, nowActive);
            }

            if (nowActive) {
                LivingEntity localShadow = SHADOWS.get(id);

                if (localShadow != null && isAquaticType(localShadow.getType())) {
                    int max = local.getMaxAirSupply();
                    if (local.isInWaterOrBubble()) {
                        clientAir = max;
                    } else {
                        clientAir = Math.max(-20, clientAir - 1);
                    }
                    local.setAirSupply(clientAir);
                }

                if (localShadow != null && isSpiderType(localShadow.getType())) {
                    tickSpiderClimbClient(local);
                }

                if (localShadow != null
                        && (Math.abs(local.getBbHeight() - localShadow.getBbHeight()) > 0.01F
                        || Math.abs(local.getBbWidth() - localShadow.getBbWidth()) > 0.01F)) {
                    local.refreshDimensions();
                }
            }
        }

        for (Map.Entry<UUID, LivingEntity> entry : SHADOWS.entrySet()) {
            if (!Boolean.TRUE.equals(ACTIVE.get(entry.getKey()))) continue;
            Player player = mc.level.getPlayerByUUID(entry.getKey());
            if (player == null) continue;
            LivingEntity shadow = entry.getValue();
            if (Math.abs(player.getBbHeight() - shadow.getBbHeight()) > 0.01F
                    || Math.abs(player.getBbWidth() - shadow.getBbWidth()) > 0.01F) {
                player.refreshDimensions();
            }
            tickShadowAnimations(player, shadow);
        }
    }

    private static void tickSpiderClimbClient(LocalPlayer player) {
        if (!isTouchingWallClient(player)) return;

        Vec3 v = player.getDeltaMovement();
        double vy;
        if (player.isShiftKeyDown()) {
            vy = -SPIDER_CLIMB_SPEED;
        } else if (player.zza > 0.0F) {
            vy = SPIDER_CLIMB_SPEED;
        } else {
            vy = 0.0D;
        }

        player.setDeltaMovement(v.x, vy, v.z);
        player.fallDistance = 0.0F;
        player.hurtMarked = true;
    }

    private static boolean isTouchingWallClient(LocalPlayer player) {
        AABB base = player.getBoundingBox();
        AABB probe = new AABB(
                base.minX - WALL_PROBE, base.minY, base.minZ - WALL_PROBE,
                base.maxX + WALL_PROBE, base.maxY, base.maxZ + WALL_PROBE
        );
        return !player.level().noCollision(player, probe);
    }

    private static boolean isSpiderType(EntityType<?> type) {
        return type == EntityType.SPIDER || type == EntityType.CAVE_SPIDER;
    }

    private static void tickShadowAnimations(Player player, LivingEntity shadow) {
        UUID id = player.getUUID();
        boolean wasOnGround = Boolean.TRUE.equals(WAS_ON_GROUND.getOrDefault(id, true));
        boolean flying = player.getAbilities().flying || player.isFallFlying() || !player.onGround();

        if (shadow instanceof Parrot parrot) {
            parrot.setOnGround(!flying);
            parrot.oFlap = parrot.flap;
            parrot.oFlapSpeed = parrot.flapSpeed;
            parrot.flapSpeed += (flying ? 4.0F : -1.0F) * 0.3F;
            parrot.flapSpeed = Mth.clamp(parrot.flapSpeed, 0.0F, 1.0F);
            if (flying && parrot.flapSpeed < 1.0F) parrot.flapSpeed = 1.0F;
            parrot.flap += parrot.flapSpeed * 2.0F;
        }

        if (shadow instanceof Chicken chicken) {
            boolean inAir = !player.onGround() && !player.getAbilities().flying;
            chicken.setOnGround(!inAir);
            chicken.oFlap = chicken.flap;
            chicken.oFlapSpeed = chicken.flapSpeed;
            chicken.flapSpeed += (inAir ? 4.0F : -1.0F) * 0.3F;
            chicken.flapSpeed = Mth.clamp(chicken.flapSpeed, 0.0F, 1.0F);
            if (inAir && chicken.flapSpeed < 1.0F) chicken.flapSpeed = 1.0F;
            chicken.flap += chicken.flapSpeed * 2.0F;
        }

        if (shadow instanceof Rabbit rabbit) {
            tickRabbitAnimation(player, rabbit, wasOnGround);
        }

        WAS_ON_GROUND.put(id, player.onGround());
    }

    private static void tickRabbitAnimation(Player player, Rabbit rabbit, boolean wasOnGround) {
        RabbitAccessor acc = (RabbitAccessor) rabbit;

        boolean onGround = player.onGround();
        boolean movedHorizontally =
                player.getDeltaMovement().horizontalDistanceSqr() > RABBIT_MOVE_THRESHOLD;
        boolean jumpedNow = wasOnGround && !onGround && player.getDeltaMovement().y > 0.0D;

        int duration = acc.talisman$getJumpDuration();
        int ticks = acc.talisman$getJumpTicks();

        if (duration > 0) {
            ticks++;
            if (ticks >= duration) {
                if (onGround && movedHorizontally) {
                    ticks = 0;
                    rabbit.setJumping(true);
                } else {
                    ticks = 0;
                    duration = 0;
                    rabbit.setJumping(false);
                }
            }
            acc.talisman$setJumpTicks(ticks);
            acc.talisman$setJumpDuration(duration);
        } else if (jumpedNow || (onGround && movedHorizontally)) {
            duration = RABBIT_HOP_DURATION;
            ticks = 0;
            rabbit.setJumping(true);
            acc.talisman$setJumpDuration(duration);
            acc.talisman$setJumpTicks(ticks);
        }

        rabbit.setOnGround(onGround);
    }

    private static boolean isAquaticType(EntityType<?> type) {
        return type == EntityType.COD
                || type == EntityType.SALMON
                || type == EntityType.DOLPHIN
                || type == EntityType.TURTLE
                || type == EntityType.AXOLOTL;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        if (!(event.getEntity() instanceof AbstractClientPlayer player)) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && player == mc.player && mc.options.getCameraType().isFirstPerson()) {
            return;
        }

        UUID id = player.getUUID();
        if (!isActive(id)) return;

        LivingEntity shadow = SHADOWS.get(id);
        if (shadow == null || shadow.isRemoved()) {
            ACTIVE.put(id, false);
            SHADOWS.remove(id);
            return;
        }

        boolean flying = player.getAbilities().flying || player.isFallFlying() || !player.onGround();
        boolean inWater = player.isInWater();

        shadow.copyPosition(player);
        shadow.xo = player.xo;
        shadow.yo = player.yo;
        shadow.zo = player.zo;
        shadow.setYRot(player.getYRot());
        shadow.setXRot(player.getXRot());
        shadow.yRotO = player.yRotO;
        shadow.xRotO = player.xRotO;
        shadow.yBodyRot = player.yBodyRot;
        shadow.yBodyRotO = player.yBodyRotO;
        shadow.yHeadRot = player.yHeadRot;
        shadow.yHeadRotO = player.yHeadRotO;
        shadow.tickCount = player.tickCount;
        shadow.setPose(player.getPose());
        shadow.setSprinting(player.isSprinting());
        shadow.setShiftKeyDown(player.isShiftKeyDown());
        shadow.setOnGround(!flying && !inWater && player.onGround());
        shadow.setDeltaMovement(player.getDeltaMovement());
        shadow.hurtDuration = player.hurtDuration;
        shadow.hurtTime = player.hurtTime;

        ((EntityAccessor) shadow).talisman$setWasTouchingWater(inWater);

        float scale = walkAnimScale(shadow.getType());
        syncWalkAnimation(player.walkAnimation, shadow.walkAnimation, scale);

        event.setCanceled(true);

        EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();
        @SuppressWarnings("unchecked")
        EntityRenderer<LivingEntity> renderer =
                (EntityRenderer<LivingEntity>) dispatcher.getRenderer(shadow);

        renderer.render(
                shadow,
                player.getYRot(),
                event.getPartialTick(),
                event.getPoseStack(),
                event.getMultiBufferSource(),
                event.getPackedLight()
        );
    }

    private static float walkAnimScale(EntityType<?> type) {
        if (type == EntityType.TURTLE) return 0.18F;
        if (type == EntityType.SALMON || type == EntityType.COD) return 0.35F;
        return 1.0F;
    }

    private static void syncWalkAnimation(WalkAnimationState src, WalkAnimationState dst, float scale) {
        dst.setSpeed(src.speed() * scale);
        WalkAnimationStateAccessor aSrc = (WalkAnimationStateAccessor) (Object) src;
        WalkAnimationStateAccessor aDst = (WalkAnimationStateAccessor) (Object) dst;
        aDst.talisman$setPosition(aSrc.talisman$getPosition() * scale);
        aDst.talisman$setSpeedOld(aSrc.talisman$getSpeedOld() * scale);
    }
}