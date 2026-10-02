package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.ArrayList;
import java.util.Collection;

public class Ma1Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity living && !living.level().isClientSide()) {
			Collection<MobEffectInstance> activeEffects = new ArrayList<>(living.getActiveEffects());
			for (MobEffectInstance effectInstance : activeEffects) {
				MobEffect effect = effectInstance.getEffect();
				if (effect.getCategory() == MobEffectCategory.HARMFUL) {
					living.removeEffect(effect);
				}
			}

			living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 5));
			living.addEffect(new MobEffectInstance(MobEffects.SATURATION, 40, 5));
		}
	}
}