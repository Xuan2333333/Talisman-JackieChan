package net.talisman.talismanjackiechan.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class PufferItem extends Item {
	public PufferItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}
}