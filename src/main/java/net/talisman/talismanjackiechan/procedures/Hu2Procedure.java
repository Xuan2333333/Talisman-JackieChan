package net.talisman.talismanjackiechan.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.talisman.talismanjackiechan.entity.EvilselfEntity;
import net.talisman.talismanjackiechan.init.TalismanEffects;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.item.TigerTalismanItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hu2Procedure {

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;
		if (!(entity instanceof Player player)) return;
		if (!(world instanceof ServerLevel serverLevel)) return;

		boolean hasTiger = player.getInventory().hasAnyMatching(
				p -> p.getItem() == TalismanJackiechanModItems.TIGER_TALISMAN.get());
		if (!hasTiger) return;

		if (player.getPersistentData().getBoolean("tiger_split_active")) {
			return;
		}

		Entity evil = TalismanJackiechanModEntities.EVILSELF.get()
				.spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
		if (!(evil instanceof EvilselfEntity evilSelf)) return;

		evilSelf.setOwnerProfile(player.getGameProfile());
		evilSelf.setCanPickUpLoot(true);

		String pairId = UUID.randomUUID().toString();
		evilSelf.setPairId(pairId);

		boolean splitYin = player.getRandom().nextBoolean();

		List<EquipmentSlot> armorSlots = List.of(
				EquipmentSlot.HEAD, EquipmentSlot.CHEST,
				EquipmentSlot.LEGS, EquipmentSlot.FEET);

		List<EquipmentSlot> owned = new ArrayList<>();
		for (EquipmentSlot slot : armorSlots) {
			if (!player.getItemBySlot(slot).isEmpty()) owned.add(slot);
		}

		int splitCount = switch (owned.size()) {
			case 0 -> 0;
			case 1 -> player.getRandom().nextBoolean() ? 1 : 0;
			case 2 -> 1;
			case 3 -> 1 + player.getRandom().nextInt(2);
			default -> 2;
		};

		for (int i = 0; i < splitCount && !owned.isEmpty(); i++) {
			int idx = player.getRandom().nextInt(owned.size());
			EquipmentSlot slot = owned.remove(idx);

			ItemStack s = player.getItemBySlot(slot);
			player.setItemSlot(slot, ItemStack.EMPTY);
			evilSelf.setItemSlot(slot, s);
			evilSelf.setDropChance(slot, 1.0F);
		}

		double baseMax = player.getMaxHealth();
		float  curHp   = player.getHealth();
		float  ratio   = baseMax > 0 ? curHp / (float) baseMax : 1.0F;

		MobEffect playerEffect = splitYin
				? TalismanEffects.LACK_YIN.get()
				: TalismanEffects.LACK_YANG.get();
		MobEffect evilEffect = splitYin
				? TalismanEffects.LACK_YANG.get()
				: TalismanEffects.LACK_YIN.get();

		player.addEffect(new MobEffectInstance(playerEffect, -1, 0, false, false, true));
		evilSelf.addEffect(new MobEffectInstance(evilEffect, -1, 0, false, false, true));

		float newPlayerMax = player.getMaxHealth();
		player.setHealth(Math.min(newPlayerMax * ratio, newPlayerMax));

		var evilHp = evilSelf.getAttribute(Attributes.MAX_HEALTH);
		if (evilHp != null) {
			evilHp.setBaseValue(baseMax);
		}
		float evilMax = evilSelf.getMaxHealth();
		evilSelf.setHealth(Math.min(evilMax * ratio, evilMax));

		evilSelf.setSplitYin(splitYin);
		evilSelf.setDropsYang(!splitYin);

		Item giveItem = splitYin
				? TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get()
				: TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get();
		ItemStack halfStack = new ItemStack(giveItem);
		halfStack.getOrCreateTag().putString(TigerTalismanItem.PAIR_ID, pairId);
		if (!player.addItem(halfStack)) {
			player.spawnAtLocation(halfStack, 0.5F);
		}

		player.getInventory().clearOrCountMatchingItems(
				p -> p.getItem() == TalismanJackiechanModItems.TIGER_TALISMAN.get(),
				1, player.getInventory());

		player.getPersistentData().putBoolean("tiger_split_active", true);
		player.getPersistentData().putBoolean("tiger_split_yin", splitYin);
	}
}