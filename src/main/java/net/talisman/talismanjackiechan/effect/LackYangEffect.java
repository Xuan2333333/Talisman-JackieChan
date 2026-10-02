package net.talisman.talismanjackiechan.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LackYangEffect extends MobEffect {

    public LackYangEffect() {
        super(MobEffectCategory.NEUTRAL, 0xEF6C00);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "8b9c0d1e-2222-2222-2222-000000000001",
                0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.MAX_HEALTH,
                "8b9c0d1e-2222-2222-2222-000000000002",
                -0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        return modifier.getAmount();
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }
}