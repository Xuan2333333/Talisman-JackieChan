package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class Ma2Procedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null) return;
		if (entity instanceof Player player && !player.level().isClientSide()) {
			player.setHealth(player.getMaxHealth());
		}
	}
}