package net.talisman.talismanjackiechan.item;

import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import java.util.List;

public class SheepTalismanItem extends Item {
	public SheepTalismanItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.sheep_talisman.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		if (world.isClientSide()) {
			if (ClientFreeCamera.isActive()) {
				ClientFreeCamera.deactivate();
			} else {
				ClientFreeCamera.activate();
			}
		}
		return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
	}



}