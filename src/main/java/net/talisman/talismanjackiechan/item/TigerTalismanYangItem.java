package net.talisman.talismanjackiechan.item;

import net.talisman.talismanjackiechan.procedures.YangdingweiProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

public class TigerTalismanYangItem extends Item {
	public TigerTalismanYangItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		YangdingweiProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
	}
}