package net.talisman.talismanjackiechan.morph;

import net.minecraft.world.entity.EntityType;

import java.util.Set;

public record MorphEntry(
        int hou,
        EntityType<?> type,
        Set<MorphAbility> abilities,
        float landSpeedBonus,
        float swimSpeedBonus,
        float armorBonus,
        float attackBonus,
        float jumpBonus
) {
    public boolean has(MorphAbility ability) {
        return abilities.contains(ability);
    }
}