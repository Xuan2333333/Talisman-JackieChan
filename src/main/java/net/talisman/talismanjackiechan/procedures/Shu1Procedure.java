package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModBlocks;
import net.talisman.talismanjackiechan.entity.LoPeiEntity;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class Shu1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ArmorStand) {
			if (!entity.level().isClientSide())
				entity.discard();
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.VILLAGER.spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (entityToSpawn != null) {
				}
			}
		}
		if (entity instanceof LoPeiEntity && TalismanJackiechanModVariables.WorldVariables.get(world).lpxf == 1) {
			if (!entity.level().isClientSide())
				entity.discard();
			world.setBlock(BlockPos.containing(x, y, z), TalismanJackiechanModBlocks.LO_PEI_STATUE_2.get().defaultBlockState(), 3);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(TalismanJackiechanModItems.RAT_TALISMAN.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
		if (entity instanceof LoPeiEntity && TalismanJackiechanModVariables.WorldVariables.get(world).lpxf == 0) {
			if (!entity.level().isClientSide())
				entity.discard();
			world.setBlock(BlockPos.containing(x, y, z), TalismanJackiechanModBlocks.LO_PEI_STATUE.get().defaultBlockState(), 3);
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(TalismanJackiechanModItems.RAT_TALISMAN.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}