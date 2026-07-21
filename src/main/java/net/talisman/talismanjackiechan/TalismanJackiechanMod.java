package net.talisman.talismanjackiechan;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.talisman.talismanjackiechan.init.TalismanJackiechanModVillagerProfessions;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModTabs;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModSounds;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModBlocks;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.InterModComms;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.talisman.talismanjackiechan.item.SlingItem;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import com.mojang.serialization.Codec;

import net.talisman.talismanjackiechan.loot.AddItemLootModifier; // 从 loot 包导入

@Mod("talisman_jackiechan")
public class TalismanJackiechanMod {
	public static final Logger LOGGER = LogManager.getLogger(TalismanJackiechanMod.class);
	public static final String MODID = "talisman_jackiechan";

	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
			DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

	public static final RegistryObject<Codec<AddItemLootModifier>> ADD_ITEM =
			LOOT_MODIFIERS.register("add_item", () -> AddItemLootModifier.CODEC);

	public TalismanJackiechanMod() {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		TalismanJackiechanModSounds.REGISTRY.register(bus);
		TalismanJackiechanModBlocks.REGISTRY.register(bus);
		TalismanJackiechanModItems.REGISTRY.register(bus);
		TalismanJackiechanModEntities.REGISTRY.register(bus);
		TalismanJackiechanModTabs.REGISTRY.register(bus);
		TalismanJackiechanModVillagerProfessions.PROFESSIONS.register(bus);
		LOOT_MODIFIERS.register(bus);

		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
				() -> new SlotTypeMessage.Builder("talisman")
						.size(0)
						.icon(new ResourceLocation("curios", "slot/empty_charm_slot"))
						.build());
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
				() -> new SlotTypeMessage.Builder("shoulder")
						.size(1)
						.icon(new ResourceLocation("curios", "slot/empty_body_slot"))
						.build());
	}

	@SubscribeEvent
	public void onEquipmentChange(LivingEquipmentChangeEvent event) {
		if (event.getSlot() != EquipmentSlot.CHEST) return;
		if (!(event.getEntity() instanceof ServerPlayer player)) return;

		ItemStack oldStack = event.getFrom();
		ItemStack newStack = event.getTo();

		boolean oldIsSling = oldStack.getItem() instanceof SlingItem;
		boolean newIsSling = newStack.getItem() instanceof SlingItem;

		if (oldIsSling && !newIsSling) {
			SlingItem.ensureTalismanDeactivated(player);
		} else if (!oldIsSling && newIsSling) {
			SlingItem.ensureTalismanActivated(player);
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer player)) return;
		boolean hasSling = false;
		if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof SlingItem) {
			hasSling = true;
		}
		if (!hasSling && net.minecraftforge.fml.ModList.get().isLoaded("curios")) {
			hasSling = CuriosApi.getCuriosHelper().getCuriosHandler(player)
					.map(handler -> {
						for (String slot : new String[]{"talisman", "shoulder"}) {
							var stacksHandler = handler.getStacksHandler(slot);
							if (stacksHandler.isPresent()) {
								for (int i = 0; i < stacksHandler.get().getSlots(); i++) {
									ItemStack stack = stacksHandler.get().getStacks().getStackInSlot(i);
									if (stack.getItem() instanceof SlingItem) {
										return true;
									}
								}
							}
						}
						return false;
					}).orElse(false);
		}

		if (hasSling) {
			SlingItem.ensureTalismanActivated(player);
		} else {
			SlingItem.ensureTalismanDeactivated(player);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.END) return;
		RoosterTalismanItem.updateFlight(event.player);
	}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(MODID, MODID),
			() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder,
											 Function<FriendlyByteBuf, T> decoder,
											 BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}