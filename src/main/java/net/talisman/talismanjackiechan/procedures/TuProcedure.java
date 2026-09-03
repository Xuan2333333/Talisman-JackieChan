package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class TuProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null) return;
		if (!(entity instanceof LivingEntity living)) return;
		if (living.level().isClientSide) return;

		int tu = (int) TalismanJackiechanModVariables.WorldVariables.get(world).tu;

		if (tu > 0) {
			living.addEffect(new MobEffectInstance(
					MobEffects.MOVEMENT_SPEED,
					20,
					Math.max(0, tu - 1),
					false,
					true,
					true
			));
		} else {
			MobEffectInstance existing = living.getEffect(MobEffects.MOVEMENT_SPEED);
			if (existing != null && existing.getAmplifier() > 0) {
				living.removeEffect(MobEffects.MOVEMENT_SPEED);
			}
		}
	}
}