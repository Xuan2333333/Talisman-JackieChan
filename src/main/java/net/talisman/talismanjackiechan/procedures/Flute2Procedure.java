package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.LevelAccessor;

public class Flute2Procedure {
	public static void execute(LevelAccessor world) {
		if (TalismanJackiechanModVariables.WorldVariables.get(world).dizi == 0) {
			TalismanJackiechanModVariables.WorldVariables.get(world).dizi = 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (TalismanJackiechanModVariables.WorldVariables.get(world).dizi == 1) {
			TalismanJackiechanModVariables.WorldVariables.get(world).dizi = 0;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}