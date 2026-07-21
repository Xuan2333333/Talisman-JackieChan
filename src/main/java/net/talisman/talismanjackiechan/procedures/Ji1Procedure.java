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
		if (entity.level().isClientSide()) return;        // 只在服务端处理
		if (!(entity instanceof ServerPlayer player)) return;

		// 创造/旁观模式不干预
		if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE ||
				player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
			return;
		}

		// 1. 检测是否持有鸡符咒（背包 + Curios）
		boolean hasRooster = hasRoosterTalisman(player);

		// 2. 强制更新飞行能力（确保丢弃后关闭）
		if (player.getAbilities().mayfly != hasRooster) {
			player.getAbilities().mayfly = hasRooster;
			// 如果关闭飞行且玩家正在飞行，则强制退出飞行状态
			if (!hasRooster && player.getAbilities().flying) {
				player.getAbilities().flying = false;
			}
			player.onUpdateAbilities();
		}
	}

	private static boolean hasRoosterTalisman(ServerPlayer player) {
		// 检查主物品栏（含快捷栏）
		for (ItemStack stack : player.getInventory().items) {
			if (stack.getItem() instanceof RoosterTalismanItem) {
				return true;
			}
		}
		// 检查 Curios 饰品栏（如果 Curios 已装载）
		if (ModList.get().isLoaded("curios")) {
			// 使用 LazyOptional 的正确检测方式
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