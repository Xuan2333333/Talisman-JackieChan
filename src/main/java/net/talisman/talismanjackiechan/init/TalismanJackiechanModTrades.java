
package net.talisman.talismanjackiechan.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TalismanJackiechanModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Blocks.GOLD_BLOCK, 8), new ItemStack(Items.APPLE), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), 10, 20, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), 10, 15, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.APPLE), new ItemStack(Items.GOLDEN_APPLE), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.TOTEM_OF_UNDYING), new ItemStack(Items.AMETHYST_SHARD), new ItemStack(Items.DRAGON_BREATH), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD, 50), new ItemStack(TalismanJackiechanModItems.MAGIC_PUFFERFISH.get()), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.DRAGON_BREATH), new ItemStack(Items.EMERALD, 6), new ItemStack(Items.EXPERIENCE_BOTTLE), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.ENDER_EYE, 4), new ItemStack(Items.EMERALD, 10), new ItemStack(Items.END_CRYSTAL), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD, 45), new ItemStack(Items.DRAGON_HEAD), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.PIGLIN_HEAD), new ItemStack(TalismanJackiechanModItems.PIG_TALISMAN.get()), 1, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.ENDER_PEARL, 10), new ItemStack(Items.END_CRYSTAL), new ItemStack(Blocks.END_PORTAL_FRAME), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Blocks.EMERALD_BLOCK, 20), new ItemStack(Blocks.DIAMOND_BLOCK, 5), new ItemStack(Blocks.NETHERITE_BLOCK, 2), 10, 50, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Blocks.EMERALD_BLOCK, 9), new ItemStack(Items.WITHER_SKELETON_SKULL), 10, 20, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(Blocks.SCULK_SHRIEKER), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.BREWING_STAND), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 9), new ItemStack(Blocks.ENCHANTING_TABLE), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.EMERALD_BLOCK, 2), new ItemStack(Items.DIAMOND, 4), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.CAULDRON), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 4), new ItemStack(Items.ENDER_EYE), new ItemStack(Blocks.ENDER_CHEST), 10, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.CHORUS_FRUIT, 64), new ItemStack(TalismanJackiechanModItems.RAT_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.DRAGON_BREATH), new ItemStack(TalismanJackiechanModItems.HORSE_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.TOTEM_OF_UNDYING), new ItemStack(TalismanJackiechanModItems.DOG_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.FEATHER, 64), new ItemStack(TalismanJackiechanModItems.ROOSTER_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.NETHERITE_INGOT, 5), new ItemStack(TalismanJackiechanModItems.TIGER_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.RABBIT_FOOT, 15), new ItemStack(TalismanJackiechanModItems.RABBIT_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.LEATHER, 64), new ItemStack(TalismanJackiechanModItems.OX_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.EXPERIENCE_BOTTLE, 8), new ItemStack(TalismanJackiechanModItems.MONKEY_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.GOLDEN_CARROT, 64), new ItemStack(TalismanJackiechanModItems.SNAKE_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.MUTTON, 64), new ItemStack(TalismanJackiechanModItems.SHEEP_TALISMAN.get()), 1, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.DIAMOND_HORSE_ARMOR), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 9), new ItemStack(Items.FIRE_CHARGE), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 5), new ItemStack(Items.GOLD_NUGGET), new ItemStack(Items.GLISTERING_MELON_SLICE), 20, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(Items.QUARTZ, 5), new ItemStack(Blocks.END_ROD), 10, 10, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 3), new ItemStack(Blocks.SCULK_VEIN), 20, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR, 2), new ItemStack(Items.GOLD_NUGGET), new ItemStack(TalismanJackiechanModItems.LO_PEI_STATUE.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.DIAMOND), new ItemStack(TalismanJackiechanModItems.MASK_HELMET.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.NETHER_STAR, 2), new ItemStack(Items.DRAGON_EGG), new ItemStack(TalismanJackiechanModItems.DRAGON_TALISMAN.get()), 1, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 16), new ItemStack(TalismanJackiechanModBlocks.LOTUS_POD.get()), 10, 5, 0.05f));
		}
		if (event.getType() == TalismanJackiechanModVillagerProfessions.ACHMAGE.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 27), new ItemStack(TalismanJackiechanModBlocks.GOURD.get()), 10, 5, 0.05f));
		}
	}
}