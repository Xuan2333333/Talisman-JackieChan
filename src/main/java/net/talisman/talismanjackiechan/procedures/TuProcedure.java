package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.item.RabbitTalismanItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public class TuProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack stack) {
		if (entity == null || !(entity instanceof LivingEntity living)) return;
		if (living.level().isClientSide) return;

		int tu = RabbitTalismanItem.getLevel(stack);

		if (tu > 0) {
			living.addEffect(new MobEffectInstance(
					MobEffects.MOVEMENT_SPEED,
					40,
					Math.max(0, tu - 1),
					false,
					true,
					true
			));
		} else {
			MobEffectInstance existing = living.getEffect(MobEffects.MOVEMENT_SPEED);
			if (existing != null) {
				living.removeEffect(MobEffects.MOVEMENT_SPEED);
			}
		}
	}
}