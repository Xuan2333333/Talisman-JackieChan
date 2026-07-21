
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TalismanJackiechanModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TalismanJackiechanMod.MODID);
	public static final RegistryObject<CreativeModeTab> TALISMANS = REGISTRY.register("talismans",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.talisman_jackiechan.talismans")).icon(() -> new ItemStack(TalismanJackiechanModItems.DRAGON_TALISMAN.get())).displayItems((parameters, tabData) -> {
				tabData.accept(TalismanJackiechanModItems.OX_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.RABBIT_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.SNAKE_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.TIGER_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.HORSE_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.ROOSTER_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.DOG_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.DRAGON_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.MONKEY_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.PIG_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.RAT_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.SHEEP_TALISMAN.get());
				tabData.accept(TalismanJackiechanModItems.MAGIC_PUFFERFISH.get());
				tabData.accept(TalismanJackiechanModItems.MAGIC_LIZARD.get());
				tabData.accept(TalismanJackiechanModItems.MASK_HELMET.get());
				tabData.accept(TalismanJackiechanModItems.MASK_2_HELMET.get());
				tabData.accept(TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get());
				tabData.accept(TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get());
				tabData.accept(TalismanJackiechanModBlocks.LO_PEI_STATUE.get().asItem());
				tabData.accept(TalismanJackiechanModItems.LO_PEI_SPAWN_EGG.get());
				tabData.accept(TalismanJackiechanModBlocks.LO_PEI_STATUE_2.get().asItem());
				tabData.accept(TalismanJackiechanModItems.DRIED_COCOA_BEANS.get());
				tabData.accept(TalismanJackiechanModItems.COCOA_POWDER.get());
				tabData.accept(TalismanJackiechanModItems.FORKEL_S_CHOCOLATE.get());
				tabData.accept(TalismanJackiechanModBlocks.LOTUS_POD.get().asItem());
				tabData.accept(TalismanJackiechanModItems.FAN.get());
				tabData.accept(TalismanJackiechanModItems.FAN_PANEL.get());
				tabData.accept(TalismanJackiechanModBlocks.DRUM.get().asItem());
				tabData.accept(TalismanJackiechanModItems.HAIR_OF_EWE.get());
				tabData.accept(TalismanJackiechanModItems.FLOWER_FROM_IMMORTAL_WARRIOR.get());
				tabData.accept(TalismanJackiechanModItems.PEERLESS_SWORD.get());
				tabData.accept(TalismanJackiechanModItems.CASTANETS.get());
				tabData.accept(TalismanJackiechanModItems.SWORD_HANDLE.get());
				tabData.accept(TalismanJackiechanModItems.BLADE.get());
				tabData.accept(TalismanJackiechanModItems.FLUTE.get());
				tabData.accept(TalismanJackiechanModBlocks.GOURD.get().asItem());
				tabData.accept(TalismanJackiechanModItems.HSI_WU_SPAWN_EGG.get());
				tabData.accept(TalismanJackiechanModItems.SHENDU_SPAWN_EGG.get());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(TalismanJackiechanModItems.EVILSELF_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(TalismanJackiechanModItems.SLING_CHESTPLATE.get());
		}
	}
}