package net.talisman.talismanjackiechan.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;

import java.util.Optional;
import java.util.UUID;

public class EvilselfEntity extends Monster {

	private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	private static final EntityDataAccessor<String> OWNER_NAME =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.STRING);

	public EvilselfEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(TalismanJackiechanModEntities.EVILSELF.get(), world);
	}

	public EvilselfEntity(EntityType<EvilselfEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		this.setDropChance(EquipmentSlot.MAINHAND, 1.0f);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(OWNER_UUID, Optional.empty());
		this.entityData.define(OWNER_NAME, "");
	}

	public void setOwnerProfile(GameProfile profile) {
		if (profile != null) {
			this.entityData.set(OWNER_UUID, Optional.of(profile.getId()));
			this.entityData.set(OWNER_NAME, profile.getName());
			this.setCustomName(Component.translatable("entity.talisman_jackiechan.evilself.name", profile.getName()));
			this.setCustomNameVisible(true);
		}
	}

	public Optional<UUID> getOwnerUUID() {
		return this.entityData.get(OWNER_UUID);
	}

	public String getOwnerName() {
		return this.entityData.get(OWNER_NAME);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.hasUUID("OwnerUUID")) {
			this.entityData.set(OWNER_UUID, Optional.of(tag.getUUID("OwnerUUID")));
		}
		if (tag.contains("OwnerName")) {
			String name = tag.getString("OwnerName");
			this.entityData.set(OWNER_NAME, name);
			if (!name.isEmpty()) {
				this.setCustomName(Component.translatable("entity.talisman_jackiechan.evilself.name", name));
				this.setCustomNameVisible(true);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.getOwnerUUID().ifPresent(uuid -> tag.putUUID("OwnerUUID", uuid));
		String name = this.getOwnerName();
		if (!name.isEmpty()) {
			tag.putString("OwnerName", name);
		}
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public double getMyRidingOffset() {
		return -0.35D;
	}

	@Override
	public boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.CACTUS))
			return false;
		if (damagesource.is(DamageTypes.DROWN))
			return false;
		if (damagesource.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(damagesource, amount);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
		builder = builder.add(Attributes.MAX_HEALTH, 20);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}
}