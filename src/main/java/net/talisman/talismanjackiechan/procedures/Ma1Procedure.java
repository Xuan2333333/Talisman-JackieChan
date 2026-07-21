package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class Ma1Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 5));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 40, 5));
		if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(MobEffects.BLINDNESS)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.BLINDNESS);
		}
		if (entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(MobEffects.DARKNESS)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.DARKNESS);
		}
		if (entity instanceof LivingEntity _livEnt6 && _livEnt6.hasEffect(MobEffects.HUNGER)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.HUNGER);
		}
		if (entity instanceof LivingEntity _livEnt8 && _livEnt8.hasEffect(MobEffects.DIG_SLOWDOWN)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.DIG_SLOWDOWN);
		}
		if (entity instanceof LivingEntity _livEnt10 && _livEnt10.hasEffect(MobEffects.DIG_SLOWDOWN)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.DIG_SLOWDOWN);
		}
		if (entity instanceof LivingEntity _livEnt12 && _livEnt12.hasEffect(MobEffects.POISON)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.POISON);
		}
		if (entity instanceof LivingEntity _livEnt14 && _livEnt14.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
		}
		if (entity instanceof LivingEntity _livEnt16 && _livEnt16.hasEffect(MobEffects.WEAKNESS)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.WEAKNESS);
		}
		if (entity instanceof LivingEntity _livEnt18 && _livEnt18.hasEffect(MobEffects.WITHER)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.WITHER);
		}
		if (entity instanceof LivingEntity _livEnt20 && _livEnt20.hasEffect(MobEffects.UNLUCK)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.UNLUCK);
		}
		if (entity instanceof LivingEntity _livEnt22 && _livEnt22.hasEffect(MobEffects.CONFUSION)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.CONFUSION);
		}
	}
}