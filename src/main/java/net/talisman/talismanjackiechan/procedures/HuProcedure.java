package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class HuProcedure {
	public static void execute(Entity entity) {
		if (entity == null) return;
		if (entity.level().isClientSide()) return;
		if (!(entity instanceof Player player)) return;
		if (player.isCreative() || player.isSpectator()) return;


		if (player.tickCount % 2 != 0) return;

		int food = player.getFoodData().getFoodLevel();
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();

		float diff = food - health;

		if (Math.abs(diff) > 1.0F) {

			if (diff > 0 && health < maxHealth) {
				player.setHealth(Math.min(health + 1.0F, maxHealth));
				player.getFoodData().setFoodLevel(food - 1);
			}
			else if (diff < 0 && food < 20) {
				player.setHealth(Math.max(health - 1.0F, 0.0F));
				player.getFoodData().setFoodLevel(food + 1);
			}
		}
	}
}