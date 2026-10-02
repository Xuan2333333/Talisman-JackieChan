package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

public class Yang2Procedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (getEntityGameType(entity) == GameType.SPECTATOR) {
			{
				Entity _ent = entity;
				_ent.teleportTo(TalismanJackiechanModVariables.WorldVariables.get(world).x, TalismanJackiechanModVariables.WorldVariables.get(world).y, TalismanJackiechanModVariables.WorldVariables.get(world).z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(TalismanJackiechanModVariables.WorldVariables.get(world).x, TalismanJackiechanModVariables.WorldVariables.get(world).y, TalismanJackiechanModVariables.WorldVariables.get(world).z, _ent.getYRot(),
							_ent.getXRot());
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).moshi == 0) {
				if (entity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.SURVIVAL);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).moshi == 1) {
				if (entity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.CREATIVE);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).moshi == 3) {
				if (entity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.ADVENTURE);
			}
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