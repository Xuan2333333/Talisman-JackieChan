package net.talisman.talismanjackiechan.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.talisman.talismanjackiechan.client.gui.MonkeyTalismanScreen;
import net.talisman.talismanjackiechan.network.SetHouPacket;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;

import java.util.List;

public class MonkeyTalismanItem extends Item {

	public MonkeyTalismanItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.translatable("item.talisman_jackiechan.monkey_talisman.description_0"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

		if (world.isClientSide()) {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> openGui(stack));
		}

		return ar;
	}

	private static void openGui(ItemStack stack) {
		int currentHou = stack.getOrCreateTag().getInt(SetHouPacket.NBT_HOU);

		if (currentHou < Hou1Procedure.HOU_MIN || currentHou > Hou1Procedure.HOU_MAX) {
			Minecraft mc = Minecraft.getInstance();
			if (mc.player != null) {
				currentHou = Hou1Procedure.getSelectedHou(mc.player);
			} else {
				currentHou = Hou1Procedure.HOU_MIN;
			}
		}

		Minecraft.getInstance().setScreen(new MonkeyTalismanScreen(currentHou));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		return super.hurtEnemy(itemstack, entity, sourceentity);
	}
}