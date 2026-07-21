package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class HuProcedure {
	public static void execute(Entity entity) {
		if (entity == null) return;
		if (entity.level().isClientSide()) return;
		if (!(entity instanceof Player player)) return;
		if (player.isCreative() || player.isSpectator()) return;

		// 每 2 tick 运行一次（约每秒 1 次转移，速度适中）
		if (player.tickCount % 2 != 0) return;

		int food = player.getFoodData().getFoodLevel();
		float health = player.getHealth();
		float maxHealth = player.getMaxHealth();

		float diff = food - health;   // 正数表示饱食度更高，负数表示生命值更高

		// 只有差距大于 1 时才进行转移（避免差值很小的时候反复横跳）
		if (Math.abs(diff) > 1.0F) {

			if (diff > 0 && health < maxHealth) {
				// 饱食度高于生命值 → 消耗 1 饱食度，回复 1 生命值
				player.setHealth(Math.min(health + 1.0F, maxHealth));
				player.getFoodData().setFoodLevel(food - 1);
			}
			else if (diff < 0 && food < 20) {
				// 生命值高于饱食度 → 消耗 1 生命值，补充 1 饱食度
				// 但当生命值超过 20 时禁止扣血（防止与生命提升效果形成循环）
				if (health <= 20) {
					player.setHealth(Math.max(health - 1.0F, 0.0F));
					player.getFoodData().setFoodLevel(food + 1);
				}
			}
		}
		// 差值 ≤ 1 时什么都不做，稳定维持
	}
}