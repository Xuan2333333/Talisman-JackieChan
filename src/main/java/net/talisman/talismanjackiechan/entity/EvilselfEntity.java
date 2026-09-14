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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.talisman.talismanjackiechan.entity.ai.TrampleFarmGoal;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.item.TigerTalismanItem;

import java.util.Optional;
import java.util.UUID;

public class EvilselfEntity extends Monster {

	private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.OPTIONAL_UUID);
	private static final EntityDataAccessor<String> OWNER_NAME =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<String> PAIR_ID =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Boolean> ENRAGED =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DROPS_YANG =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SPLIT_YIN =
			SynchedEntityData.defineId(EvilselfEntity.class, EntityDataSerializers.BOOLEAN);

	private int enrageTicks = 0;

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
		setCanPickUpLoot(true);
	}

	private void refreshDisplayName() {
		String name = getOwnerName();
		if (name.isEmpty()) return;

		String key = isSplitYin()
				? "entity.talisman_jackiechan.evilself.name.yin"
				: "entity.talisman_jackiechan.evilself.name.yang";

		this.setCustomName(Component.translatable(key, name));
		this.setCustomNameVisible(true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(OWNER_UUID, Optional.empty());
		this.entityData.define(OWNER_NAME, "");
		this.entityData.define(PAIR_ID, "");
		this.entityData.define(ENRAGED, false);
		this.entityData.define(DROPS_YANG, false);
		this.entityData.define(SPLIT_YIN, true);
	}

	public void setOwnerProfile(GameProfile profile) {
		if (profile != null) {
			this.entityData.set(OWNER_UUID, Optional.of(profile.getId()));
			this.entityData.set(OWNER_NAME, profile.getName());
			refreshDisplayName();
		}
	}

	public Optional<UUID> getOwnerUUID() { return this.entityData.get(OWNER_UUID); }
	public String getOwnerName()          { return this.entityData.get(OWNER_NAME); }

	public void setPairId(String id) { this.entityData.set(PAIR_ID, id); }
	public String getPairId()        { return this.entityData.get(PAIR_ID); }

	public boolean isEnraged() { return this.entityData.get(ENRAGED); }
	private void setEnraged(boolean b) { this.entityData.set(ENRAGED, b); }

	public void setDropsYang(boolean b) { this.entityData.set(DROPS_YANG, b); }
	public boolean dropsYang()          { return this.entityData.get(DROPS_YANG); }

	public void setSplitYin(boolean b) {
		this.entityData.set(SPLIT_YIN, b);
		refreshDisplayName();
	}
	public boolean isSplitYin() { return this.entityData.get(SPLIT_YIN); }

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.hasUUID("OwnerUUID")) {
			this.entityData.set(OWNER_UUID, Optional.of(tag.getUUID("OwnerUUID")));
		}
		if (tag.contains("OwnerName")) {
			this.entityData.set(OWNER_NAME, tag.getString("OwnerName"));
		}
		if (tag.contains("TigerPairId")) {
			this.entityData.set(PAIR_ID, tag.getString("TigerPairId"));
		}
		if (tag.contains("DropsYang")) {
			this.entityData.set(DROPS_YANG, tag.getBoolean("DropsYang"));
		}
		if (tag.contains("SplitYin")) {
			this.entityData.set(SPLIT_YIN, tag.getBoolean("SplitYin"));
		}
		refreshDisplayName();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		this.getOwnerUUID().ifPresent(uuid -> tag.putUUID("OwnerUUID", uuid));
		String name = this.getOwnerName();
		if (!name.isEmpty()) tag.putString("OwnerName", name);
		String pid = getPairId();
		if (!pid.isEmpty()) tag.putString("TigerPairId", pid);
		tag.putBoolean("DropsYang", dropsYang());
		tag.putBoolean("SplitYin", isSplitYin());
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity e) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + e.getBbWidth();
			}
		});
		this.goalSelector.addGoal(2, new TrampleFarmGoal(this));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(
				this, Player.class, true,
				e -> this.isEnraged()));

		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
				this, TamableAnimal.class, true,
				e -> this.isEnraged()));

		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.CACTUS)) return false;
		if (source.is(DamageTypes.DROWN)) return false;
		if (source.is(DamageTypes.DRAGON_BREATH)) return false;
		if (source.is(DamageTypes.WITHER) || source.is(DamageTypes.WITHER_SKULL)) return false;

		boolean result = super.hurt(source, amount);
		if (result && !this.level().isClientSide()) {
			Entity attacker = source.getEntity();
			if (attacker instanceof Player) {
				this.setEnraged(true);
				this.enrageTicks = 20 * 60 * 5;
				this.setTarget((LivingEntity) attacker);
			}
		}
		return result;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.level().isClientSide()) return;

		LivingEntity target = this.getTarget();
		if (target != null && !target.isAlive()) {
			this.setTarget(null);
		}

		if (this.enrageTicks > 0) {
			if (--this.enrageTicks == 0) {
				this.setEnraged(false);
				this.setTarget(null);
			}
		}
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
		super.dropCustomDeathLoot(source, looting, recentlyHit);
		String pid = getPairId();
		if (pid.isEmpty()) return;

		Item dropItem = dropsYang()
				? TalismanJackiechanModItems.TIGER_TALISMAN_YANG.get()
				: TalismanJackiechanModItems.TIGER_TALISMAN_YIN.get();

		ItemStack stack = new ItemStack(dropItem);
		stack.getOrCreateTag().putString(TigerTalismanItem.PAIR_ID, pid);
		this.spawnAtLocation(stack);
		setPairId("");
	}

	@Override public MobType getMobType() { return MobType.UNDEAD; }
	@Override public boolean removeWhenFarAway(double d) { return false; }
	@Override public double getMyRidingOffset() { return -0.35D; }
	@Override public boolean shouldDespawnInPeaceful() { return false; }

	public static void init() {}

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