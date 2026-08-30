package net.talisman.talismanjackiechan.procedures;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import net.talisman.talismanjackiechan.item.SnakeTalismanItem;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
public class SheProcedure {


	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null) return;
		if (entity.level().isClientSide()) return;
		if (!(entity instanceof Player player)) return;


		MobEffectInstance existing = player.getEffect(MobEffects.INVISIBILITY);
		if (existing != null && existing.getDuration() == MobEffectInstance.INFINITE_DURATION) {
			player.removeEffect(MobEffects.INVISIBILITY);
		} else {

			player.addEffect(new MobEffectInstance(
					MobEffects.INVISIBILITY,
					MobEffectInstance.INFINITE_DURATION,
					0,
					false,
					false,
					true
			));
		}
	}

	public static void updateInvisibility(Entity entity) {
		if (entity == null) return;
		if (entity.level().isClientSide()) return;
		if (!(entity instanceof ServerPlayer player)) return;

		boolean hasSnake = hasSnakeTalisman(player);
		if (!hasSnake) {
			MobEffectInstance invis = player.getEffect(MobEffects.INVISIBILITY);
			if (invis != null && invis.getDuration() == MobEffectInstance.INFINITE_DURATION) {
				player.removeEffect(MobEffects.INVISIBILITY);
			}
		}
	}


	private static boolean hasSnakeTalisman(@NotNull ServerPlayer player) {

		for (ItemStack stack : player.getInventory().armor) {
			if (stack.getItem() instanceof SnakeTalismanItem) {
				return true;
			}
		}

		for (ItemStack stack : player.getInventory().items) {
			if (stack.getItem() instanceof SnakeTalismanItem) {
				return true;
			}
		}

		if (player.getOffhandItem().getItem() instanceof SnakeTalismanItem) {
			return true;
		}

		if (ModList.get().isLoaded("curios")) {
			LazyOptional<ICuriosItemHandler> opt = CuriosApi.getCuriosHelper().getCuriosHandler(player);
			if (opt.isPresent()) {
				ICuriosItemHandler handler = opt.orElse(null);
				if (handler != null) {
					for (ICurioStacksHandler stacksHandler : handler.getCurios().values()) {
						for (int i = 0; i < stacksHandler.getSlots(); i++) {
							ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
							if (stack.getItem() instanceof SnakeTalismanItem) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}