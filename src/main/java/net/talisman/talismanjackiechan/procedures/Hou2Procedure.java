package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.minecraft.world.level.LevelAccessor;

public class Hou2Procedure {
	public static void execute(LevelAccessor world) {
		double hou = TalismanJackiechanModVariables.WorldVariables.get(world).hou;
		if (hou < 17) {
			hou = hou + 1;
		} else {
			hou = 1;
		}
		TalismanJackiechanModVariables.WorldVariables.get(world).hou = hou;
		TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
	}
}