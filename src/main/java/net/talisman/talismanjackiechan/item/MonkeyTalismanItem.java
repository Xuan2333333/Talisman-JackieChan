package net.talisman.talismanjackiechan.item;

import net.talisman.talismanjackiechan.procedures.Hou2Procedure;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;
import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
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
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		Hou2Procedure.execute(world);

		double hou = TalismanJackiechanModVariables.WorldVariables.get(world).hou;
		entity.displayClientMessage(
				Component.translatable("message.talisman_jackiechan.monkey_mode",
						Component.translatable(getAnimalTranslationKey((int) hou))),
				true
		);
		return ar;
	}

	private String getAnimalTranslationKey(int hou) {
		return switch (hou) {
			case 1 -> "entity.minecraft.pig";
			case 2 -> "entity.minecraft.horse";
			case 3 -> "entity.minecraft.donkey";
			case 4 -> "entity.minecraft.mule";
			case 5 -> "entity.minecraft.cow";
			case 6 -> "entity.minecraft.sheep";
			case 7 -> "entity.minecraft.chicken";
			case 8 -> "entity.minecraft.wolf";
			case 10 -> "entity.minecraft.cat";
			case 11 -> "entity.minecraft.cod";
			case 12 -> "entity.minecraft.dolphin";
			case 13 -> "entity.minecraft.turtle";
			case 14 -> "entity.minecraft.parrot";
			case 15 -> "entity.minecraft.bee";
			case 16 -> "entity.minecraft.frog";
			case 17 -> "message.talisman_jackiechan.monkey_restore_mode";
			default -> "message.talisman_jackiechan.monkey_unknown";
		};
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		Hou1Procedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		return retval;
	}
}