package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.ArrayList;
import java.util.Collection;

public class Ma3Procedure {
    public static void execute(LivingEntity target) {
        if (target == null || target.level().isClientSide()) return;

        Collection<MobEffectInstance> activeEffects = new ArrayList<>(target.getActiveEffects());
        for (MobEffectInstance effectInstance : activeEffects) {
            MobEffect effect = effectInstance.getEffect();
            if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                target.removeEffect(effect);
            }
        }

        target.setHealth(target.getMaxHealth());
    }
}