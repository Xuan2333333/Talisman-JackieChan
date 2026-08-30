package net.talisman.talismanjackiechan.procedures;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.talisman.talismanjackiechan.entity.PigTalismanPowerEntity;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;

public class PigLaserHandler {
    private static final int COOLDOWN = 2;

    public static void tryShoot(Player player, boolean leftEye) {
        if (player.level().isClientSide()) return;

        String key = leftEye ? "PigLaserLeftCD" : "PigLaserRightCD";
        long now = player.level().getGameTime();
        long next = player.getPersistentData().getLong(key);
        if (now < next) return;
        player.getPersistentData().putLong(key, now + COOLDOWN);

        shootLaser(player, leftEye);
    }

    public static void shootLaser(Player player, boolean leftEye) {
        if (player.level().isClientSide()) return;

        Vec3 eyePos = player.getEyePosition(1.0f);

        Vec3 look = player.getLookAngle();
        Vec3 right = new Vec3(-look.z, 0, look.x).normalize();

        double offsetAmount = 0.2;
        Vec3 spawnPos;
        if (leftEye) {
            spawnPos = eyePos.add(right.scale(-offsetAmount));
        } else {
            spawnPos = eyePos.add(right.scale(offsetAmount));
        }

        PigTalismanPowerEntity laser = new PigTalismanPowerEntity(
                TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(),
                spawnPos.x, spawnPos.y, spawnPos.z,
                player.level()
        );
        laser.setOwner(player);
        laser.setBaseDamage(12.0);
        laser.setKnockback(0);
        laser.shoot(look.x, look.y, look.z, 20.0F, 0.0F);
        laser.setSilent(true);
        laser.setNoGravity(true);
        player.level().addFreshEntity(laser);

        if (player.level() instanceof ServerLevel serverLevel) {
        }
    }
}