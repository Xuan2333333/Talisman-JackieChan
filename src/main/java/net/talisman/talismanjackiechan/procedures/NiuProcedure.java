package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.talisman.talismanjackiechan.init.TalismanEffects;

public class NiuProcedure {
	public static void execute(Entity entity) {
		if (entity == null) return;
		if (entity instanceof LivingEntity living && !living.level().isClientSide()) {
			int exp = entity instanceof Player plr ? plr.experienceLevel : 0;

			int amplifier = (int) Math.round(Math.pow(exp, 0.25));

			living.addEffect(new MobEffectInstance(TalismanEffects.OX_POWER.get(), 60, amplifier));
		}
	}
}