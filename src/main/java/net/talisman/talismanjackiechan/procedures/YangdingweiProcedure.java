package net.talisman.talismanjackiechan.procedures;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;

public class YangdingweiProcedure {
	public static void execute(Level world, Player player, ItemStack heldStack) {
		if (player == null || world.isClientSide()) return;

		if (heldStack.getItem() != TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get()) return;

		Object[] result = TigerLocator.locate(world, heldStack, true);
		if (result == null) {
			player.displayClientMessage(Component.translatable("message.talisman_jackiechan.pair_not_found"), true);
			return;
		}

		if (!result[0].equals(player.level().dimension())) {
			player.displayClientMessage(Component.translatable("message.talisman_jackiechan.pair_different_dimension"), true);
			return;
		}

		double dx = (double) result[1] - player.getX();
		double dy = (double) result[2] - player.getY();
		double dz = (double) result[3] - player.getZ();
		int distance = (int) Math.round(Math.sqrt(dx*dx + dy*dy + dz*dz));
		player.displayClientMessage(Component.translatable("message.talisman_jackiechan.pair_distance", distance), true);
	}
}