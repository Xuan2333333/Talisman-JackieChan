
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.entity.TalismanPowerEntity;
import net.talisman.talismanjackiechan.entity.ShenduEntity;
import net.talisman.talismanjackiechan.entity.PufferPowerEntity;
import net.talisman.talismanjackiechan.entity.PigTalismanPowerEntity;
import net.talisman.talismanjackiechan.entity.LoPeiEntity;
import net.talisman.talismanjackiechan.entity.HsiWuEntity;
import net.talisman.talismanjackiechan.entity.EvilselfEntity;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TalismanJackiechanModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TalismanJackiechanMod.MODID);
	public static final RegistryObject<EntityType<TalismanPowerEntity>> TALISMAN_POWER = register("talisman_power",
			EntityType.Builder.<TalismanPowerEntity>of(TalismanPowerEntity::new, MobCategory.MISC).setCustomClientFactory(TalismanPowerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.1f, 0.1f));
	public static final RegistryObject<EntityType<PigTalismanPowerEntity>> PIG_TALISMAN_POWER = register("pig_talisman_power", EntityType.Builder.<PigTalismanPowerEntity>of(PigTalismanPowerEntity::new, MobCategory.MISC)
			.setCustomClientFactory(PigTalismanPowerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<PufferPowerEntity>> PUFFER_POWER = register("puffer_power",
			EntityType.Builder.<PufferPowerEntity>of(PufferPowerEntity::new, MobCategory.MISC).setCustomClientFactory(PufferPowerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<EvilselfEntity>> EVILSELF = register("evilself",
			EntityType.Builder.<EvilselfEntity>of(EvilselfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(EvilselfEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<LoPeiEntity>> LO_PEI = register("lo_pei",
			EntityType.Builder.<LoPeiEntity>of(LoPeiEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(LoPeiEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<HsiWuEntity>> HSI_WU = register("hsi_wu",
			EntityType.Builder.<HsiWuEntity>of(HsiWuEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HsiWuEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ShenduEntity>> SHENDU = register("shendu",
			EntityType.Builder.<ShenduEntity>of(ShenduEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ShenduEntity::new)

					.sized(1f, 3f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			EvilselfEntity.init();
			LoPeiEntity.init();
			HsiWuEntity.init();
			ShenduEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(EVILSELF.get(), EvilselfEntity.createAttributes().build());
		event.put(LO_PEI.get(), LoPeiEntity.createAttributes().build());
		event.put(HSI_WU.get(), HsiWuEntity.createAttributes().build());
		event.put(SHENDU.get(), ShenduEntity.createAttributes().build());
	}
}