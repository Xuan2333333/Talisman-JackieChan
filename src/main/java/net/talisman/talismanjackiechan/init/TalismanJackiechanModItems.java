
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.item.TigerTalismanYinItem;
import net.talisman.talismanjackiechan.item.TigerTalismanYangItem;
import net.talisman.talismanjackiechan.item.TigerTalismanItem;
import net.talisman.talismanjackiechan.item.SwordHandleItem;
import net.talisman.talismanjackiechan.item.SslpItem;
import net.talisman.talismanjackiechan.item.SnakeTalismanItem;
import net.talisman.talismanjackiechan.item.SlingItem;
import net.talisman.talismanjackiechan.item.SheepTalismanItem;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;
import net.talisman.talismanjackiechan.item.RatTalismanItem;
import net.talisman.talismanjackiechan.item.RabbitTalismanItem;
import net.talisman.talismanjackiechan.item.PufferPower1Item;
import net.talisman.talismanjackiechan.item.PufferItem;
import net.talisman.talismanjackiechan.item.PowerItem;
import net.talisman.talismanjackiechan.item.PigTalismanItem;
import net.talisman.talismanjackiechan.item.PeerlessSwordItem;
import net.talisman.talismanjackiechan.item.OxTalismanItem;
import net.talisman.talismanjackiechan.item.MonkeyTalismanItem;
import net.talisman.talismanjackiechan.item.MaskItem;
import net.talisman.talismanjackiechan.item.Mask2Item;
import net.talisman.talismanjackiechan.item.MagicPufferfishItem;
import net.talisman.talismanjackiechan.item.MagicLizardItem;
import net.talisman.talismanjackiechan.item.LpmzItem;
import net.talisman.talismanjackiechan.item.LpItem;
import net.talisman.talismanjackiechan.item.HorseTalismanItem;
import net.talisman.talismanjackiechan.item.HairOfEweItem;
import net.talisman.talismanjackiechan.item.ForkelSChocolateItem;
import net.talisman.talismanjackiechan.item.FluteItem;
import net.talisman.talismanjackiechan.item.FlowerFromImmortalWarriorItem;
import net.talisman.talismanjackiechan.item.FanPanelItem;
import net.talisman.talismanjackiechan.item.FanItem;
import net.talisman.talismanjackiechan.item.DriedCocoaBeansItem;
import net.talisman.talismanjackiechan.item.DragonTalismanItem;
import net.talisman.talismanjackiechan.item.DogTalismanItem;
import net.talisman.talismanjackiechan.item.CocoaPowderItem;
import net.talisman.talismanjackiechan.item.ClItem;
import net.talisman.talismanjackiechan.item.CastanetsItem;
import net.talisman.talismanjackiechan.item.BladeItem;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class TalismanJackiechanModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TalismanJackiechanMod.MODID);
	public static final RegistryObject<Item> OX_TALISMAN = REGISTRY.register("ox_talisman", OxTalismanItem::new);
	public static final RegistryObject<Item> RABBIT_TALISMAN = REGISTRY.register("rabbit_talisman", RabbitTalismanItem::new);
	public static final RegistryObject<Item> SNAKE_TALISMAN = REGISTRY.register("snake_talisman", SnakeTalismanItem::new);
	public static final RegistryObject<Item> TIGER_TALISMAN = REGISTRY.register("tiger_talisman", TigerTalismanItem::new);
	public static final RegistryObject<Item> HORSE_TALISMAN = REGISTRY.register("horse_talisman", HorseTalismanItem::new);
	public static final RegistryObject<Item> ROOSTER_TALISMAN = REGISTRY.register("rooster_talisman", RoosterTalismanItem::new);
	public static final RegistryObject<Item> DOG_TALISMAN = REGISTRY.register("dog_talisman", DogTalismanItem::new);
	public static final RegistryObject<Item> DRIEDPUFFER = REGISTRY.register("driedpuffer", PufferItem::new);
	public static final RegistryObject<Item> DRAGON_TALISMAN = REGISTRY.register("dragon_talisman", DragonTalismanItem::new);
	public static final RegistryObject<Item> POWER = REGISTRY.register("power", PowerItem::new);
	public static final RegistryObject<Item> MONKEY_TALISMAN = REGISTRY.register("monkey_talisman", MonkeyTalismanItem::new);
	public static final RegistryObject<Item> PIG_TALISMAN = REGISTRY.register("pig_talisman", PigTalismanItem::new);
	public static final RegistryObject<Item> RAT_TALISMAN = REGISTRY.register("rat_talisman", RatTalismanItem::new);
	public static final RegistryObject<Item> SHEEP_TALISMAN = REGISTRY.register("sheep_talisman", SheepTalismanItem::new);
	public static final RegistryObject<Item> PUFFER_POWER_1 = REGISTRY.register("puffer_power_1", PufferPower1Item::new);
	public static final RegistryObject<Item> MAGIC_PUFFERFISH = REGISTRY.register("magic_pufferfish", MagicPufferfishItem::new);
	public static final RegistryObject<Item> MAGIC_LIZARD = REGISTRY.register("magic_lizard", MagicLizardItem::new);
	public static final RegistryObject<Item> MASK_HELMET = REGISTRY.register("mask_helmet", MaskItem.Helmet::new);
	public static final RegistryObject<Item> MASK_2_HELMET = REGISTRY.register("mask_2_helmet", Mask2Item.Helmet::new);
	public static final RegistryObject<Item> TIGER_TALISMAN_YIN = REGISTRY.register("tiger_talisman_yin", TigerTalismanYinItem::new);
	public static final RegistryObject<Item> TIGER_TALISMAN_YANG = REGISTRY.register("tiger_talisman_yang", TigerTalismanYangItem::new);
	public static final RegistryObject<Item> EVILSELF_SPAWN_EGG = REGISTRY.register("evilself_spawn_egg", () -> new ForgeSpawnEggItem(TalismanJackiechanModEntities.EVILSELF, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> LO_PEI_STATUE = block(TalismanJackiechanModBlocks.LO_PEI_STATUE);
	public static final RegistryObject<Item> LO_PEI_SPAWN_EGG = REGISTRY.register("lo_pei_spawn_egg", () -> new ForgeSpawnEggItem(TalismanJackiechanModEntities.LO_PEI, -16711936, -154, new Item.Properties()));
	public static final RegistryObject<Item> CL = REGISTRY.register("cl", ClItem::new);
	public static final RegistryObject<Item> LP = REGISTRY.register("lp", LpItem::new);
	public static final RegistryObject<Item> LPMZ = REGISTRY.register("lpmz", LpmzItem::new);
	public static final RegistryObject<Item> SSLP = REGISTRY.register("sslp", SslpItem::new);
	public static final RegistryObject<Item> LO_PEI_STATUE_2 = block(TalismanJackiechanModBlocks.LO_PEI_STATUE_2);
	public static final RegistryObject<Item> DRIED_COCOA_BEANS = REGISTRY.register("dried_cocoa_beans", DriedCocoaBeansItem::new);
	public static final RegistryObject<Item> COCOA_POWDER = REGISTRY.register("cocoa_powder", CocoaPowderItem::new);
	public static final RegistryObject<Item> FORKEL_S_CHOCOLATE = REGISTRY.register("forkel_s_chocolate", ForkelSChocolateItem::new);
	public static final RegistryObject<Item> LOTUS_POD = block(TalismanJackiechanModBlocks.LOTUS_POD);
	public static final RegistryObject<Item> FAN = REGISTRY.register("fan", FanItem::new);
	public static final RegistryObject<Item> FAN_PANEL = REGISTRY.register("fan_panel", FanPanelItem::new);
	public static final RegistryObject<Item> DRUM = block(TalismanJackiechanModBlocks.DRUM);
	public static final RegistryObject<Item> HAIR_OF_EWE = REGISTRY.register("hair_of_ewe", HairOfEweItem::new);
	public static final RegistryObject<Item> FLOWER_FROM_IMMORTAL_WARRIOR = REGISTRY.register("flower_from_immortal_warrior", FlowerFromImmortalWarriorItem::new);
	public static final RegistryObject<Item> PEERLESS_SWORD = REGISTRY.register("peerless_sword", PeerlessSwordItem::new);
	public static final RegistryObject<Item> CASTANETS = REGISTRY.register("castanets", CastanetsItem::new);
	public static final RegistryObject<Item> SWORD_HANDLE = REGISTRY.register("sword_handle", SwordHandleItem::new);
	public static final RegistryObject<Item> BLADE = REGISTRY.register("blade", BladeItem::new);
	public static final RegistryObject<Item> FLUTE = REGISTRY.register("flute", FluteItem::new);
	public static final RegistryObject<Item> GOURD = block(TalismanJackiechanModBlocks.GOURD);
	public static final RegistryObject<Item> HSI_WU_SPAWN_EGG = REGISTRY.register("hsi_wu_spawn_egg", () -> new ForgeSpawnEggItem(TalismanJackiechanModEntities.HSI_WU, -13353138, -8473928, new Item.Properties()));
	public static final RegistryObject<Item> SHENDU_SPAWN_EGG = REGISTRY.register("shendu_spawn_egg", () -> new ForgeSpawnEggItem(TalismanJackiechanModEntities.SHENDU, -16738048, -13408768, new Item.Properties()));
	public static final RegistryObject<Item> SLING_CHESTPLATE = REGISTRY.register("sling_chestplate", SlingItem.Chestplate::new);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}