package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;

public class Ji1Procedure {
	public static void execute(Entity entity) {
		if (entity == null) return;
		if (entity.level().isClientSide()) return;
		if (!(entity instanceof ServerPlayer player)) return;


		if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE ||
				player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
			return;
		}


		boolean hasRooster = hasRoosterTalisman(player);


		if (player.getAbilities().mayfly != hasRooster) {
			player.getAbilities().mayfly = hasRooster;

			if (!hasRooster && player.getAbilities().flying) {
				player.getAbilities().flying = false;
			}
			player.onUpdateAbilities();
		}
	}

	private static boolean hasRoosterTalisman(ServerPlayer player) {

		for (ItemStack stack : player.getInventory().items) {
			if (stack.getItem() instanceof RoosterTalismanItem) {
				return true;
			}
		}
		if (ModList.get().isLoaded("curios")) {
			var opt = CuriosApi.getCuriosHelper().getCuriosHandler(player);
			if (opt.isPresent()) {
				var handler = opt.orElse(null);
				if (handler != null) {
					for (var slotType : handler.getCurios().keySet()) {
						var stacksHandler = handler.getStacksHandler(slotType);
						if (stacksHandler.isPresent()) {
							for (int i = 0; i < stacksHandler.get().getSlots(); i++) {
								ItemStack stack = stacksHandler.get().getStacks().getStackInSlot(i);
								if (stack.getItem() instanceof RoosterTalismanItem) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}