package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

public class Yang1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getEntityGameType(entity) == GameType.SURVIVAL) {
			TalismanJackiechanModVariables.WorldVariables.get(world).x = x;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).y = y;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).z = z;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).moshi = 0;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.SPECTATOR);
		}
		if (getEntityGameType(entity) == GameType.CREATIVE) {
			TalismanJackiechanModVariables.WorldVariables.get(world).x = x;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).y = y;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).z = z;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).moshi = 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.SPECTATOR);
		}
		if (getEntityGameType(entity) == GameType.ADVENTURE) {
			TalismanJackiechanModVariables.WorldVariables.get(world).x = x;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).y = y;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).z = z;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			TalismanJackiechanModVariables.WorldVariables.get(world).moshi = 3;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.SPECTATOR);
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}