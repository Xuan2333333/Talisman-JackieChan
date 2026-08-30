package net.talisman.talismanjackiechan.procedures;

import net.minecraft.world.entity.player.Player;
import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class Hou1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null || world.isClientSide()) return;
		if (!(entity instanceof LivingEntity target)) return;
		if (target instanceof Player) return;

		int hou = (int) TalismanJackiechanModVariables.WorldVariables.get(world).hou;


		if (target.getPersistentData().contains("hou_original_entity_type")) {
			restoreOriginalEntity(world, target);
			return;
		}


		if (hou == 17) return;


		EntityType<?> targetType = getEntityType(hou);
		if (targetType == null) return;


		CompoundTag originalNBT = target.serializeNBT();
		String originalTypeKey = ForgeRegistries.ENTITY_TYPES.getKey(target.getType()).toString();
		float originalHealth = target.getHealth();


		target.discard();


		if (world instanceof ServerLevel serverLevel) {
			Entity newEntity = targetType.spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
			if (newEntity != null) {
				newEntity.setYRot(world.getRandom().nextFloat() * 360F);
				newEntity.setXRot(target.getXRot());


				CompoundTag data = newEntity.getPersistentData();
				data.putString("hou_original_entity_type", originalTypeKey);
				data.put("hou_original_nbt", originalNBT);
				data.putFloat("hou_original_health", originalHealth);


				copyBasicProperties(target, newEntity);
			}
		}
	}

	private static void restoreOriginalEntity(LevelAccessor world, LivingEntity transformedEntity) {
		if (!(world instanceof ServerLevel serverLevel)) return;

		CompoundTag data = transformedEntity.getPersistentData();
		String typeKey = data.getString("hou_original_entity_type");
		EntityType<?> originalType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(typeKey));
		if (originalType == null) return;


		Entity original = originalType.spawn(serverLevel, transformedEntity.blockPosition(), MobSpawnType.MOB_SUMMONED);
		if (original == null) return;


		CompoundTag originalNBT = data.getCompound("hou_original_nbt");
		originalNBT.remove("Pos");
		originalNBT.remove("UUID");
		originalNBT.remove("Motion");
		originalNBT.remove("Dimension");
		originalNBT.remove("id");
		originalNBT.remove("Passengers");
		originalNBT.remove("Leash");


		original.load(originalNBT);


		original.moveTo(transformedEntity.getX(), transformedEntity.getY(), transformedEntity.getZ(), transformedEntity.getYRot(), transformedEntity.getXRot());


		if (original instanceof LivingEntity originalLiving) {
			float health = data.getFloat("hou_original_health");
			if (health > 0 && health <= originalLiving.getMaxHealth()) {
				originalLiving.setHealth(health);
			}
		}


		transformedEntity.discard();
	}


	private static void copyBasicProperties(LivingEntity source, Entity target) {
		if (source.hasCustomName()) {
			target.setCustomName(source.getCustomName());
			target.setCustomNameVisible(source.isCustomNameVisible());
		}
		if (source instanceof TamableAnimal && target instanceof TamableAnimal) {
			((TamableAnimal) target).setTame(((TamableAnimal) source).isTame());
			((TamableAnimal) target).setOwnerUUID(((TamableAnimal) source).getOwnerUUID());
		}
		if (source instanceof AgeableMob && target instanceof AgeableMob) {
			((AgeableMob) target).setAge(((AgeableMob) source).getAge());
		}
	}

	private static EntityType<?> getEntityType(int hou) {
		return switch (hou) {
			case 1 -> EntityType.PIG;
			case 2 -> EntityType.HORSE;
			case 3 -> EntityType.DONKEY;
			case 4 -> EntityType.MULE;
			case 5 -> EntityType.COW;
			case 6 -> EntityType.SHEEP;
			case 7 -> EntityType.CHICKEN;
			case 8 -> EntityType.WOLF;
			case 10 -> EntityType.CAT;
			case 11 -> EntityType.COD;
			case 12 -> EntityType.DOLPHIN;
			case 13 -> EntityType.TURTLE;
			case 14 -> EntityType.PARROT;
			case 15 -> EntityType.BEE;
			case 16 -> EntityType.FROG;
			default -> null;
		};
	}
}