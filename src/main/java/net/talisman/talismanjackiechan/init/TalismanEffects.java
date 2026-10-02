package net.talisman.talismanjackiechan.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.talisman.talismanjackiechan.effect.OxPowerEffect;
import net.talisman.talismanjackiechan.effect.LackYinEffect;
import net.talisman.talismanjackiechan.effect.LackYangEffect;

public class TalismanEffects {
    public static final DeferredRegister<MobEffect> REGISTRY =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "talisman_jackiechan");

    public static final RegistryObject<MobEffect> OX_POWER =
            REGISTRY.register("ox_power", () -> new OxPowerEffect(MobEffectCategory.BENEFICIAL, 0xFFD700));

    public static final RegistryObject<MobEffect> LACK_YIN =
            REGISTRY.register("lack_yin", LackYinEffect::new);

    public static final RegistryObject<MobEffect> LACK_YANG =
            REGISTRY.register("lack_yang", LackYangEffect::new);
}