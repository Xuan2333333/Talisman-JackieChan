package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.entity.EvilselfEntity;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;
import net.talisman.talismanjackiechan.item.TigerTalismanItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

import java.util.UUID;

public class Hu2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null) return;
		if (!(entity instanceof Player player)) return;
		if (!(world instanceof ServerLevel serverLevel)) return;
		boolean hasTiger = player.getInventory().hasAnyMatching(p -> p.getItem() == TalismanJackiechanModItems.TIGER_TALISMAN.get());
		if (!hasTiger) return;
		Entity evil = TalismanJackiechanModEntities.EVILSELF.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
		if (evil instanceof EvilselfEntity evilSelf) {
			evilSelf.setOwnerProfile(player.getGameProfile());
		}


		if (evil == null) return;
		String pairId = UUID.randomUUID().toString();
		ItemStack yinStack = new ItemStack(TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get());
		CompoundTag yinTag = yinStack.getOrCreateTag();
		yinTag.putString(TigerTalismanItem.PAIR_ID, pairId);
		yinStack.setTag(yinTag);
		evil.setItemSlot(EquipmentSlot.MAINHAND, yinStack);
		ItemStack yangStack = new ItemStack(TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get());
		CompoundTag yangTag = yangStack.getOrCreateTag();
		yangTag.putString(TigerTalismanItem.PAIR_ID, pairId);
		yangStack.setTag(yangTag);
		if (!player.addItem(yangStack)) {
			player.spawnAtLocation(yangStack, 0.5F);
		}
		player.getInventory().clearOrCountMatchingItems(
				p -> p.getItem() == TalismanJackiechanModItems.TIGER_TALISMAN.get(), 1,
				player.getInventory());
	}
}