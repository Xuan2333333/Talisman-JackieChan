package net.talisman.talismanjackiechan.procedures;

import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModBlocks;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

public class LoPei2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if ((entity instanceof TamableAnimal _tamIsTamedBy && sourceentity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) && TalismanJackiechanModVariables.WorldVariables.get(world).lpxf == 1
				&& sourceentity.isShiftKeyDown()) {
			if (TalismanJackiechanModVariables.WorldVariables.get(world).niulp >= 1) {
				for (int index0 = 0; index0 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).niulp; index0++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.OX_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).niulp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).hulp >= 1) {
				for (int index1 = 0; index1 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).hulp; index1++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.TIGER_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).hulp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).tulp >= 1) {
				for (int index2 = 0; index2 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).tulp; index2++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.RABBIT_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).tulp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).longlp >= 1) {
				for (int index3 = 0; index3 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).longlp; index3++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.DRAGON_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).longlp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).shelp >= 1) {
				for (int index4 = 0; index4 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).shelp; index4++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.SNAKE_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).shelp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).malp >= 1) {
				for (int index5 = 0; index5 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).malp; index5++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.HORSE_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).malp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).yanglp >= 1) {
				for (int index6 = 0; index6 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).yanglp; index6++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.SHEEP_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).yanglp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).houlp >= 1) {
				for (int index7 = 0; index7 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).houlp; index7++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.MONKEY_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).houlp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).jilp >= 1) {
				for (int index8 = 0; index8 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).jilp; index8++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.ROOSTER_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).jilp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).goulp >= 1) {
				for (int index9 = 0; index9 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).goulp; index9++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.DOG_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).goulp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (TalismanJackiechanModVariables.WorldVariables.get(world).zhulp >= 1) {
				for (int index10 = 0; index10 < (int) TalismanJackiechanModVariables.WorldVariables.get(world).zhulp; index10++) {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(TalismanJackiechanModItems.PIG_TALISMAN.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
				TalismanJackiechanModVariables.WorldVariables.get(world).zhulp = 0;
				TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
			}
			if (!entity.level().isClientSide())
				entity.discard();
			world.setBlock(BlockPos.containing(x, y, z), TalismanJackiechanModBlocks.LO_PEI_STATUE_2.get().defaultBlockState(), 3);
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(TalismanJackiechanModItems.RAT_TALISMAN.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			TalismanJackiechanModVariables.WorldVariables.get(world).lpxf = 0;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
		if (entity instanceof TamableAnimal _tamIsTamedBy && sourceentity instanceof LivingEntity _livEnt ? _tamIsTamedBy.isOwnedBy(_livEnt) : false) {
			if (sourceentity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("talisman_jackiechan:friends_should_give_thumbs_up"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
			TalismanJackiechanModVariables.WorldVariables.get(world).lpxf = 1;
			TalismanJackiechanModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}