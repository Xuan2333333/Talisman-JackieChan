package net.talisman.talismanjackiechan.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;

import java.util.List;

public class RoosterTalismanItem extends Item implements ICurioItem {

	public static final String TAG_GRANTED_MAYFLY = "talisman_granted_mayfly";

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
		return InteractionResultHolder.pass(entity.getItemInHand(hand));
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
		if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE
				|| player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
			return;
		}

		boolean morphFly = Hou1Procedure.isPlayerTransformed(player)
				&& Hou1Procedure.canFlyForm(Hou1Procedure.getPlayerForm(player));
		boolean shouldGrant = hasRoosterTalisman(player) || morphFly;

		CompoundTag data = player.getPersistentData();

		if (shouldGrant) {
			if (!player.getAbilities().mayfly) {
				player.getAbilities().mayfly = true;
				player.onUpdateAbilities();
			}
			data.putBoolean(TAG_GRANTED_MAYFLY, true);
			return;
		}

		if (!data.getBoolean(TAG_GRANTED_MAYFLY)) {
			return;
		}

		data.remove(TAG_GRANTED_MAYFLY);
		if (player.getAbilities().mayfly) {
			player.getAbilities().mayfly = false;
			player.getAbilities().flying = false;
			player.onUpdateAbilities();
		}
	}

	public static boolean hasRoosterTalisman(@NotNull ServerPlayer player) {
		for (ItemStack stack : player.getInventory().armor) {
			if (stack.getItem() instanceof RoosterTalismanItem) {
				return true;
			}
		}
		for (ItemStack stack : player.getInventory().items) {
			if (stack.getItem() instanceof RoosterTalismanItem) {
				return true;
			}
		}
		if (player.getOffhandItem().getItem() instanceof RoosterTalismanItem) {
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