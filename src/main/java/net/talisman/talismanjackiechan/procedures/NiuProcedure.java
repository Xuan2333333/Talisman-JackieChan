package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class NiuProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 60, (int) Math.round(Math.pow(entity instanceof Player _plr ? _plr.experienceLevel : 0, 0.25) + 1)));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, (int) Math.round(Math.pow(entity instanceof Player _plr ? _plr.experienceLevel : 0, 0.25) + 2)));
	}
}