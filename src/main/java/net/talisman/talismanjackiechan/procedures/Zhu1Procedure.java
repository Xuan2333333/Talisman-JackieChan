package net.talisman.talismanjackiechan.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.talisman.talismanjackiechan.entity.PigTalismanPowerEntity;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;

public class Zhu1Procedure {
	public static void execute(net.minecraft.world.entity.Entity entity) {
		if (entity == null) return;
		net.minecraft.world.level.Level projectileLevel = entity.level();
		if (!projectileLevel.isClientSide()) {

			for (int index0 = 0; index0 < 2; index0++) {
				PigTalismanPowerEntity _entityToSpawn = new PigTalismanPowerEntity(
						TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(), 0, 0, 0, projectileLevel);
				_entityToSpawn.setOwner(entity);
				_entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
				_entityToSpawn.setBaseDamage(12.0);
				_entityToSpawn.setKnockback(0);
				_entityToSpawn.shoot(
						entity.getLookAngle().x,
						entity.getLookAngle().y,
						entity.getLookAngle().z,
						20.0F,
						0.0F
				);
				_entityToSpawn.setSilent(true);
				_entityToSpawn.setNoGravity(true);
				projectileLevel.addFreshEntity(_entityToSpawn);
			}


			if (projectileLevel instanceof ServerLevel serverLevel) {
				for (int i = 0; i < 10; i++) {
					serverLevel.sendParticles(
							ParticleTypes.FLAME,
							entity.getX(),
							entity.getEyeY(),
							entity.getZ(),
							1,
							(projectileLevel.random.nextDouble() - 0.5) * 0.5,
							(projectileLevel.random.nextDouble() - 0.5) * 0.5,
							(projectileLevel.random.nextDouble() - 0.5) * 0.5,
							0.3
					);
				}
			}
		}
	}
}