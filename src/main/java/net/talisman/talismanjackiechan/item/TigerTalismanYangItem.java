package net.talisman.talismanjackiechan.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.talisman.talismanjackiechan.procedures.TigerTalismanCombineHelper;
import net.talisman.talismanjackiechan.procedures.YangdingweiProcedure;

public class TigerTalismanYangItem extends Item {

	public TigerTalismanYangItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack held = player.getItemInHand(hand);
		if (!world.isClientSide()) {
			if (TigerTalismanCombineHelper.tryCombine(player, held, true)) {
				return InteractionResultHolder.success(held);
			}
			YangdingweiProcedure.execute(world, player, held);
		}
		return InteractionResultHolder.success(held);
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
	}
}