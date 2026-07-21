package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Explosion;
import org.joml.Vector3f;

public class DragonLaserProcedure {

    private static final double RANGE = 70.0;
    private static final float DAMAGE = 14.0f;
    private static final float EXPLOSION_POWER = 2.8f;

    public static void execute(Player player) {
        Level level = player.level();
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookDir = player.getLookAngle().normalize();
        Vec3 endPos = eyePos.add(lookDir.scale(RANGE));

        ClipContext context = new ClipContext(eyePos, endPos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player);
        BlockHitResult blockHit = level.clip(context);

        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                player, eyePos, endPos, new AABB(eyePos, endPos).inflate(1.5),
                e -> e instanceof LivingEntity && !e.isSpectator() && e != player, RANGE * RANGE);

        HitResult finalHit = entityHit != null ? entityHit : blockHit;
        Vec3 hitPos = finalHit.getLocation();


        if (entityHit != null && entityHit.getEntity() instanceof LivingEntity target) {
            target.hurt(level.damageSources().indirectMagic(player, player), DAMAGE);
            target.setSecondsOnFire(8);
        }


        if (!level.isClientSide()) {
            level.explode(null, hitPos.x, hitPos.y, hitPos.z, EXPLOSION_POWER, false, Level.ExplosionInteraction.NONE);
        }

        // 粒子
        spawnDragonLaserParticles(level, eyePos, lookDir, hitPos);

    }

    private static void spawnDragonLaserParticles(Level level, Vec3 start, Vec3 dir, Vec3 hitPos) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        double distance = start.distanceTo(hitPos);
        int steps = (int) (distance * 3.0);

        for (int i = 0; i < steps; i++) {
            double progress = i / (double) steps;
            Vec3 pos = start.add(dir.scale(distance * progress));

            serverLevel.sendParticles(ParticleTypes.LAVA, pos.x, pos.y, pos.z, 4, 0.12, 0.12, 0.12, 0.04);
            serverLevel.sendParticles(ParticleTypes.FLAME, pos.x, pos.y, pos.z, 5, 0.1, 0.1, 0.1, 0.03);
            serverLevel.sendParticles(ParticleTypes.SMALL_FLAME, pos.x, pos.y, pos.z, 5, 0.1, 0.1, 0.1, 0.03);
            serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, pos.x, pos.y, pos.z, 3, 0.08, 0.08, 0.08, 0);

            Vector3f color = new Vector3f(1.0f, 0.5f, 0.0f);
            serverLevel.sendParticles(new DustParticleOptions(color, 2.2f), pos.x, pos.y, pos.z, 5, 0.15, 0.15, 0.15, 0.06);
        }

        serverLevel.sendParticles(ParticleTypes.EXPLOSION, hitPos.x, hitPos.y, hitPos.z, 8, 0.4, 0.4, 0.4, 0.2);
    }
}