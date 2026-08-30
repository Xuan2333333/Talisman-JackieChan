package net.talisman.talismanjackiechan.item;

import net.minecraft.world.entity.Entity;
import net.talisman.talismanjackiechan.procedures.Ma2Procedure;
import net.talisman.talismanjackiechan.procedures.Ma1Procedure;
import net.talisman.talismanjackiechan.procedures.Ma3Procedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import java.util.List;

public class HorseTalismanItem extends Item {
	public HorseTalismanItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.horse_talisman.description_0"));
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (player.isShiftKeyDown()) {
			if (!player.level().isClientSide()) {
				Ma3Procedure.execute(target);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		if (!world.isClientSide() && !entity.isShiftKeyDown()) {
			Ma2Procedure.execute(entity, ar.getObject());
		}
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		Ma1Procedure.execute(entity);
	}
}