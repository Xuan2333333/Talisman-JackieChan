package net.talisman.talismanjackiechan.item;

import net.talisman.talismanjackiechan.client.gui.RabbitTalismanScreen;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.SetRabbitLevelPacket;
import net.talisman.talismanjackiechan.procedures.TuProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

public class RabbitTalismanItem extends Item {
	public static final String TAG_LEVEL = "TuLevel";

	public RabbitTalismanItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	public static int getLevel(ItemStack stack) {
		return stack.getOrCreateTag().getInt(TAG_LEVEL);
	}

	public static void setLevel(ItemStack stack, int level) {
		level = Math.max(0, Math.min(15, level));
		stack.getOrCreateTag().putInt(TAG_LEVEL, level);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(stack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.rabbit_talisman.description_0"));
		int current = getLevel(stack);
		list.add(Component.literal("§b当前速度等级: §e" + current + " §7(0=关闭)"));
		list.add(Component.literal("§7右键打开选择界面 | Shift+右键重置 | 滚轮快速切换"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (player.isShiftKeyDown()) {
			if (!world.isClientSide) {
				setLevel(stack, 0);
			} else {
				setLevel(stack, 0);
			}
			return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
		}

		if (world.isClientSide) {
			openScreen(stack, hand);
		}

		return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
	}

	@OnlyIn(Dist.CLIENT)
	private void openScreen(ItemStack stack, InteractionHand hand) {
		Minecraft.getInstance().setScreen(new RabbitTalismanScreen(stack, hand));
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(stack, world, entity, slot, selected);
		TuProcedure.execute(world, entity, stack);
	}
}