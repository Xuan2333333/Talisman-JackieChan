package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.LevelAccessor;

public class Tu2Procedure {
	public static void execute(LevelAccessor world) {
		if (TalismanJackiechanModVariables.WorldVariables.get(world).tu < 11) {
			TalismanJackiechanModVariables.WorldVariables.get(world).tu = TalismanJackiechanModVariables.WorldVariables.get(world).tu + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (TalismanJackiechanModVariables.WorldVariables.get(world).tu >= 11) {
			TalismanJackiechanModVariables.WorldVariables.get(world).tu = 0;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}