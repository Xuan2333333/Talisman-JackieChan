package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;
import net.talisman.talismanjackiechan.entity.PigTalismanPowerEntity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.Entity;

public class Zhu1Procedure {
	public static void execute(Entity entity) {
		if (entity == null) return;
		for (int index0 = 0; index0 < 2; index0++) {
			Entity _shootFrom = entity;
			Level projectileLevel = _shootFrom.level();
			if (!projectileLevel.isClientSide()) {
				Projectile _entityToSpawn = new PigTalismanPowerEntity(
						TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(), 0, 0, 0, projectileLevel);
				_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
				_entityToSpawn.shoot(_shootFrom.getLookAngle().x,
						_shootFrom.getLookAngle().y,
						_shootFrom.getLookAngle().z,
						20.0F,   // 高速
						0.0F);   // 无散射
				projectileLevel.addFreshEntity(_entityToSpawn);
			}
		}
	}
}