package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.entity.LoPeiEntity;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class Gou2Procedure {
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		execute(event, event.getLevel(), event.getTarget(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		execute(null, world, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.DOG_TALISMAN.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 999999999, 254));
			TalismanJackiechanModVariables.WorldVariables.get(world).goulp = TalismanJackiechanModVariables.WorldVariables.get(world).goulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.OX_TALISMAN.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 999999999, 40));
			TalismanJackiechanModVariables.WorldVariables.get(world).niulp = TalismanJackiechanModVariables.WorldVariables.get(world).niulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.TIGER_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).hulp = TalismanJackiechanModVariables.WorldVariables.get(world).hulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.RABBIT_TALISMAN.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 999999999, 10));
			TalismanJackiechanModVariables.WorldVariables.get(world).tulp = TalismanJackiechanModVariables.WorldVariables.get(world).tulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.DRAGON_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).longlp = TalismanJackiechanModVariables.WorldVariables.get(world).longlp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.SNAKE_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).shelp = TalismanJackiechanModVariables.WorldVariables.get(world).shelp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.HORSE_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).malp = TalismanJackiechanModVariables.WorldVariables.get(world).malp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 999999999, 254));
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.SHEEP_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).yanglp = TalismanJackiechanModVariables.WorldVariables.get(world).yanglp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.MONKEY_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).houlp = TalismanJackiechanModVariables.WorldVariables.get(world).houlp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.ROOSTER_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).jilp = TalismanJackiechanModVariables.WorldVariables.get(world).jilp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.PIG_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).zhulp = TalismanJackiechanModVariables.WorldVariables.get(world).zhulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.DOG_TALISMAN.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 999999999, 254));
			TalismanJackiechanModVariables.WorldVariables.get(world).goulp = TalismanJackiechanModVariables.WorldVariables.get(world).goulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.OX_TALISMAN.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 999999999, 40));
			TalismanJackiechanModVariables.WorldVariables.get(world).niulp = TalismanJackiechanModVariables.WorldVariables.get(world).niulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.TIGER_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).hulp = TalismanJackiechanModVariables.WorldVariables.get(world).hulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.RABBIT_TALISMAN.get()) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 999999999, 10));
			TalismanJackiechanModVariables.WorldVariables.get(world).tulp = TalismanJackiechanModVariables.WorldVariables.get(world).tulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.DRAGON_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).longlp = TalismanJackiechanModVariables.WorldVariables.get(world).longlp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.SNAKE_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).shelp = TalismanJackiechanModVariables.WorldVariables.get(world).shelp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.HORSE_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).malp = TalismanJackiechanModVariables.WorldVariables.get(world).malp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 999999999, 254));
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.SHEEP_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).yanglp = TalismanJackiechanModVariables.WorldVariables.get(world).yanglp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.MONKEY_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).houlp = TalismanJackiechanModVariables.WorldVariables.get(world).houlp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.ROOSTER_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).jilp = TalismanJackiechanModVariables.WorldVariables.get(world).jilp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof LoPeiEntity && (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TalismanJackiechanModItems.PIG_TALISMAN.get()) {
			TalismanJackiechanModVariables.WorldVariables.get(world).zhulp = TalismanJackiechanModVariables.WorldVariables.get(world).zhulp + 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}

	}

}