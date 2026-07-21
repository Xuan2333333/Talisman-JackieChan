package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraft.world.level.LevelAccessor;

public class YangdingweiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		TalismanJackiechanModVariables.WorldVariables.get(world).yangx = x;
		TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		TalismanJackiechanModVariables.WorldVariables.get(world).yangy = y;
		TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		TalismanJackiechanModVariables.WorldVariables.get(world).yangz = z;
		TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		TalismanJackiechanMod.LOGGER.info(Math.round(Math.pow(Math.pow(TalismanJackiechanModVariables.WorldVariables.get(world).yangx - TalismanJackiechanModVariables.WorldVariables.get(world).yinx, 2)
				+ Math.pow(TalismanJackiechanModVariables.WorldVariables.get(world).yangy - TalismanJackiechanModVariables.WorldVariables.get(world).yiny, 2)
				+ Math.pow(TalismanJackiechanModVariables.WorldVariables.get(world).yangz - TalismanJackiechanModVariables.WorldVariables.get(world).yinz, 2), 0.5)));
	}
}