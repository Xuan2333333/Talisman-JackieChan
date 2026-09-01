package net.talisman.talismanjackiechan.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.talisman.talismanjackiechan.item.MonkeyTalismanItem;
import net.talisman.talismanjackiechan.network.MonkeyTransformPacket;
import net.talisman.talismanjackiechan.network.NetworkHandler;

@OnlyIn(Dist.CLIENT)
public class MonkeyTalismanInputHandler {

    private static final double RANGE = 24.0;
    private static boolean wasAttackDown = false;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(MonkeyTalismanInputHandler.class);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.screen != null) {
            wasAttackDown = false;
            return;
        }

        boolean holding = mc.player.getMainHandItem().getItem() instanceof MonkeyTalismanItem;
        boolean attackDown = mc.options.keyAttack.isDown();

        if (holding && attackDown && !wasAttackDown) {
            if (mc.player.isShiftKeyDown()) {
                NetworkHandler.INSTANCE.sendToServer(
                        new MonkeyTransformPacket(mc.player.getUUID())
                );
            } else {
                Entity target = findLookedEntity(mc);
                if (target != null) {
                    NetworkHandler.INSTANCE.sendToServer(
                            new MonkeyTransformPacket(target.getUUID())
                    );
                }
            }
        }
        wasAttackDown = attackDown && holding;
    }

    private static Entity findLookedEntity(Minecraft mc) {
        Entity camera = mc.getCameraEntity() == null ? mc.player : mc.getCameraEntity();
        Vec3 start = camera.getEyePosition(1.0F);
        Vec3 look = camera.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(RANGE));

        AABB box = camera.getBoundingBox().expandTowards(look.scale(RANGE)).inflate(1.0);
        EntityHitResult hit = ProjectileUtil.getEntityHitResult(
                camera,
                start,
                end,
                box,
                e -> e instanceof LivingEntity
                        && e.isPickable()
                        && e.isAlive()
                        && e != mc.player,
                RANGE * RANGE
        );
        return hit == null ? null : hit.getEntity();
    }
}