package net.talisman.talismanjackiechan.item;

import net.talisman.talismanjackiechan.procedures.DragonLaserProcedure;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import java.util.List;

public class DragonTalismanItem extends Item {

	public DragonTalismanItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.dragon_talisman.description_0"));
	}

	// 右键发射 + 长按连续
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (hand == InteractionHand.MAIN_HAND) {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(player.getItemInHand(hand));
		}
		return super.use(world, player, hand);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000; // 支持长按
	}

	@Override
	public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remaining) {
		if (entity instanceof Player player) {
			DragonLaserProcedure.execute(player);
		}
	}
}