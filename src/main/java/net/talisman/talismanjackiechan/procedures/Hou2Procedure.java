package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.LevelAccessor;

public class Hou2Procedure {
	public static void execute(LevelAccessor world) {
		if (TalismanJackiechanModVariables.WorldVariables.get(world).hou < 17) {
			TalismanJackiechanModVariables.WorldVariables.get(world).hou = TalismanJackiechanModVariables.WorldVariables.get(world).hou + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (TalismanJackiechanModVariables.WorldVariables.get(world).hou >= 17) {
			TalismanJackiechanModVariables.WorldVariables.get(world).hou = 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}