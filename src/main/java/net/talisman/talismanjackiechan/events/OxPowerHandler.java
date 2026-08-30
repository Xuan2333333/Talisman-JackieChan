package net.talisman.talismanjackiechan.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.talisman.talismanjackiechan.init.TalismanEffects;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;

public class OxPowerHandler {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker
                && attacker.hasEffect(TalismanEffects.OX_POWER.get())) {

            int amplifier = attacker.getEffect(TalismanEffects.OX_POWER.get()).getAmplifier();
            int level = amplifier + 1;

            float multiplier = (float) Math.pow(1.1, level);

            float newDamage = (event.getAmount() + 2.0F * level) * multiplier;

            event.setAmount(newDamage);
        }
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        if (player.hasEffect(TalismanEffects.OX_POWER.get())) {
            int amplifier = player.getEffect(TalismanEffects.OX_POWER.get()).getAmplifier();

            float hasteMultiplier = 1.0F + 0.2F * amplifier;

            event.setNewSpeed(event.getNewSpeed() * hasteMultiplier);
        }
    }
    @SubscribeEvent
    public void onLivingKnockBack(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(TalismanEffects.OX_POWER.get())) {
            int amplifier = entity.getEffect(TalismanEffects.OX_POWER.get()).getAmplifier();

            double factor = Math.pow(0.78, amplifier);
            event.setStrength(event.getStrength() * (float) factor);
        }
    }
}