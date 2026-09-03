package net.talisman.talismanjackiechan.item;

import net.talisman.talismanjackiechan.procedures.TuProcedure;
import net.talisman.talismanjackiechan.procedures.Tu2Procedure;
import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import java.util.List;

public class RabbitTalismanItem extends Item {
	public RabbitTalismanItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.rabbit_talisman.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!world.isClientSide) {
			if (player.isShiftKeyDown()) {
				TalismanJackiechanModVariables.WorldVariables vars =
						TalismanJackiechanModVariables.WorldVariables.get(world);
				vars.tu = 0;
				vars.syncData(world);
			} else {
				Tu2Procedure.execute(world);
			}
		}
		return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		TuProcedure.execute(world, entity);
	}
}