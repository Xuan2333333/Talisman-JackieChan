package net.talisman.talismanjackiechan.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;
import net.talisman.talismanjackiechan.network.HouMorphSyncPacket;
import net.talisman.talismanjackiechan.network.NetworkHandler;
import net.talisman.talismanjackiechan.network.TalismanJackiechanModVariables;

public class Hou1Procedure {

	public static final String TAG_PLAYER_ACTIVE = "hou_player_active";
	public static final String TAG_PLAYER_FORM = "hou_player_form";
	public static final String TAG_PLAYER_OLD_MAX = "hou_player_old_max";
	public static final String TAG_PLAYER_FORM_TYPE = "hou_player_form_type";
	public static final String TAG_AIR = "hou_air";

	public static final String TAG_ORIGINAL_TYPE = "hou_original_entity_type";
	public static final String TAG_ORIGINAL_NBT = "hou_original_nbt";
	public static final String TAG_ORIGINAL_MAX = "hou_original_max_health";
	public static final String TAG_ORIGINAL_HP = "hou_original_health";

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null || world.isClientSide()) return;
		if (!(entity instanceof LivingEntity target)) return;

		if (target instanceof ServerPlayer player) {
			handlePlayer(world, player);
			return;
		}

		if (target.getPersistentData().contains(TAG_ORIGINAL_TYPE)) {
			restoreOriginalEntity(world, target);
			return;
		}

		int hou = (int) TalismanJackiechanModVariables.WorldVariables.get(world).hou;
		EntityType<?> targetType = getEntityType(hou);
		if (targetType == null) return;

		float oldMax = target.getMaxHealth();
		float oldHp = target.getHealth();

		CompoundTag originalNBT = target.serializeNBT();
		ResourceLocation typeKey = ForgeRegistries.ENTITY_TYPES.getKey(target.getType());
		if (typeKey == null) return;
		String originalTypeKey = typeKey.toString();

		float yRot = target.getYRot();
		float xRot = target.getXRot();

		target.discard();

		if (!(world instanceof ServerLevel serverLevel)) return;

		Entity spawned = targetType.spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
		if (spawned == null) return;

		spawned.moveTo(x, y, z, yRot, xRot);

		if (spawned instanceof LivingEntity newLiving) {
			float newMax = newLiving.getMaxHealth();
			float newHp = scaleHealth(oldHp, oldMax, newMax);
			newLiving.setHealth(Math.min(newHp, newMax));

			CompoundTag data = newLiving.getPersistentData();
			data.putString(TAG_ORIGINAL_TYPE, originalTypeKey);
			data.put(TAG_ORIGINAL_NBT, originalNBT);
			data.putFloat(TAG_ORIGINAL_MAX, oldMax);
			data.putFloat(TAG_ORIGINAL_HP, oldHp);
		}

		copyBasicProperties(target, spawned);
	}

	private static void handlePlayer(LevelAccessor world, ServerPlayer player) {
		CompoundTag data = player.getPersistentData();

		if (data.getBoolean(TAG_PLAYER_ACTIVE)) {
			restorePlayer(player);
			return;
		}

		int hou = (int) TalismanJackiechanModVariables.WorldVariables.get(world).hou;
		EntityType<?> formType = getEntityType(hou);
		if (formType == null) return;

		float newMax = getDefaultMaxHealth(world, formType);
		if (newMax <= 0.0F) newMax = 20.0F;

		float oldMax = player.getMaxHealth();
		float oldHp = player.getHealth();
		float newHp = scaleHealth(oldHp, oldMax, newMax);

		data.putBoolean(TAG_PLAYER_ACTIVE, true);
		data.putInt(TAG_PLAYER_FORM, hou);
		data.putFloat(TAG_PLAYER_OLD_MAX, oldMax);

		ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(formType);
		String typeStr = key != null ? key.toString() : "minecraft:pig";
		data.putString(TAG_PLAYER_FORM_TYPE, typeStr);

		setPlayerMaxHealth(player, newMax);
		player.setHealth(Math.min(newHp, newMax));
		applyFormAbilities(player, hou);

		syncMorph(player, true, typeStr);
		player.refreshDimensions();
	}

	public static void restorePlayer(ServerPlayer player) {
		CompoundTag data = player.getPersistentData();
		if (!data.getBoolean(TAG_PLAYER_ACTIVE)) return;

		float oldMax = data.contains(TAG_PLAYER_OLD_MAX) ? data.getFloat(TAG_PLAYER_OLD_MAX) : 20.0F;
		float curMax = player.getMaxHealth();
		float curHp = player.getHealth();
		float restoredHp = scaleHealth(curHp, curMax, oldMax);

		setPlayerMaxHealth(player, oldMax);
		player.setHealth(Math.min(restoredHp, oldMax));

		data.putBoolean(TAG_PLAYER_ACTIVE, false);
		data.remove(TAG_PLAYER_FORM);
		data.remove(TAG_PLAYER_FORM_TYPE);
		data.remove(TAG_PLAYER_OLD_MAX);
		data.remove(TAG_AIR);

		RoosterTalismanItem.updateFlight(player);

		syncMorph(player, false, "minecraft:empty");
		player.refreshDimensions();
	}

	public static void syncMorph(ServerPlayer player, boolean active, String typeId) {
		NetworkHandler.INSTANCE.send(
				PacketDistributor.ALL.noArg(),
				new HouMorphSyncPacket(player.getUUID(), active, typeId)
		);
	}

	private static void setPlayerMaxHealth(ServerPlayer player, float max) {
		var attr = player.getAttribute(Attributes.MAX_HEALTH);
		if (attr != null) {
			attr.setBaseValue(max);
		}
	}

	private static void applyFormAbilities(ServerPlayer player, int hou) {
		RoosterTalismanItem.updateFlight(player);
	}

	public static boolean isPlayerTransformed(net.minecraft.world.entity.player.Player player) {
		return player.getPersistentData().getBoolean(TAG_PLAYER_ACTIVE);
	}

	public static int getPlayerForm(net.minecraft.world.entity.player.Player player) {
		return player.getPersistentData().getInt(TAG_PLAYER_FORM);
	}

	public static boolean canFlyForm(int hou) {
		return hou == 14 || hou == 15;
	}

	public static boolean aquaticForm(int hou) {
		return hou == 11 || hou == 12 || hou == 13 || hou == 17;
	}

	private static void restoreOriginalEntity(LevelAccessor world, LivingEntity transformedEntity) {
		if (!(world instanceof ServerLevel serverLevel)) return;

		CompoundTag data = transformedEntity.getPersistentData();
		String typeKey = data.getString(TAG_ORIGINAL_TYPE);
		EntityType<?> originalType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(typeKey));
		if (originalType == null) return;

		Entity original = originalType.spawn(serverLevel, transformedEntity.blockPosition(), MobSpawnType.MOB_SUMMONED);
		if (original == null) return;

		CompoundTag originalNBT = data.getCompound(TAG_ORIGINAL_NBT);
		originalNBT.remove("Pos");
		originalNBT.remove("UUID");
		originalNBT.remove("Motion");
		originalNBT.remove("Dimension");
		originalNBT.remove("id");
		originalNBT.remove("Passengers");
		originalNBT.remove("Leash");

		original.load(originalNBT);
		original.moveTo(
				transformedEntity.getX(), transformedEntity.getY(), transformedEntity.getZ(),
				transformedEntity.getYRot(), transformedEntity.getXRot()
		);

		if (original instanceof LivingEntity originalLiving) {
			float originalMax = data.contains(TAG_ORIGINAL_MAX)
					? data.getFloat(TAG_ORIGINAL_MAX)
					: originalLiving.getMaxHealth();
			float restoredHp = scaleHealth(
					transformedEntity.getHealth(),
					transformedEntity.getMaxHealth(),
					originalMax
			);
			originalLiving.setHealth(Math.min(restoredHp, originalLiving.getMaxHealth()));
		}

		transformedEntity.discard();
	}

	public static float scaleHealth(float currentHp, float oldMax, float newMax) {
		if (newMax <= 0.0F) return 1.0F;
		if (oldMax <= 0.0F) return Math.max(1.0F, newMax);
		float ratio = Mth.clamp(currentHp / oldMax, 0.0F, 1.0F);
		return (float) Math.max(1, Math.round(ratio * newMax));
	}

	private static float getDefaultMaxHealth(LevelAccessor world, EntityType<?> type) {
		if (!(world instanceof ServerLevel level)) return 20.0F;
		Entity tmp = type.create(level);
		if (!(tmp instanceof LivingEntity living)) {
			if (tmp != null) tmp.discard();
			return 20.0F;
		}
		float max = living.getMaxHealth();
		tmp.discard();
		return max;
	}

	private static void copyBasicProperties(LivingEntity source, Entity target) {
		if (source.hasCustomName()) {
			target.setCustomName(source.getCustomName());
			target.setCustomNameVisible(source.isCustomNameVisible());
		}
		if (source instanceof TamableAnimal s && target instanceof TamableAnimal t) {
			t.setTame(s.isTame());
			t.setOwnerUUID(s.getOwnerUUID());
		}
		if (source instanceof AgeableMob s && target instanceof AgeableMob t) {
			t.setAge(s.getAge());
		}
	}

	public static EntityType<?> getEntityType(int hou) {
		return switch (hou) {
			case 1 -> EntityType.PIG;
			case 2 -> EntityType.HORSE;
			case 3 -> EntityType.DONKEY;
			case 4 -> EntityType.MULE;
			case 5 -> EntityType.COW;
			case 6 -> EntityType.SHEEP;
			case 7 -> EntityType.CHICKEN;
			case 8 -> EntityType.WOLF;
			case 9 -> EntityType.FOX;
			case 10 -> EntityType.CAT;
			case 11 -> EntityType.COD;
			case 12 -> EntityType.DOLPHIN;
			case 13 -> EntityType.TURTLE;
			case 14 -> EntityType.PARROT;
			case 15 -> EntityType.BEE;
			case 16 -> EntityType.FROG;
			case 17 -> EntityType.SALMON;
			case 18 -> EntityType.RABBIT;
			default -> null;
		};
	}
}