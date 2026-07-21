package net.talisman.talismanjackiechan.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import net.talisman.talismanjackiechan.procedures.Ji2Procedure;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.List;

public class RoosterTalismanItem extends Item implements ICurioItem {

	public RoosterTalismanItem() {
		super(new Item.Properties().stacksTo(1).durability(0).rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.rooster_talisman.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		return super.use(world, entity, hand);
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		Ji2Procedure.execute(entity);
		return retval;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
		super.inventoryTick(stack, level, entity, slot, selected);
	}

	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		updateFlight(slotContext.entity());
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		updateFlight(slotContext.entity());
	}

	@Override
	public boolean canEquip(SlotContext slotContext, ItemStack stack) {
		return true;
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
	}

	public static void updateFlight(Entity entity) {
		if (!(entity instanceof ServerPlayer player)) {
			return;
		}
		if (player.level().isClientSide()) {
			return;
		}
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
		// Check armor slots
		for (ItemStack stack : player.getInventory().armor) {
			if (stack.getItem() instanceof RoosterTalismanItem) {
				return true;
			}
		}

		// Check Curios slots
		if (ModList.get().isLoaded("curios")) {
			LazyOptional<ICuriosItemHandler> opt = CuriosApi.getCuriosHelper().getCuriosHandler(player);
			if (opt.isPresent()) {
				ICuriosItemHandler handler = opt.orElse(null);
				if (handler != null) {
					for (ICurioStacksHandler stacksHandler : handler.getCurios().values()) {
						for (int i = 0; i < stacksHandler.getSlots(); i++) {
							ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
							if (stack.getItem() instanceof RoosterTalismanItem) {
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