
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class TalismanJackiechanModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TalismanJackiechanMod.MODID);
	public static final RegistryObject<SoundEvent> DIZI1 = REGISTRY.register("dizi1", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("talisman_jackiechan", "dizi1")));
	public static final RegistryObject<SoundEvent> DIZI2 = REGISTRY.register("dizi2", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("talisman_jackiechan", "dizi2")));
}