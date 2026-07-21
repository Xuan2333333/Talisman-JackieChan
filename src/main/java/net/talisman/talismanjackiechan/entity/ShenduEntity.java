package net.talisman.talismanjackiechan.entity;

import net.talisman.talismanjackiechan.init.TalismanJackiechanModItems;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

public class ShenduEntity extends Monster {
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.GREEN, ServerBossEvent.BossBarOverlay.PROGRESS);

	public ShenduEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(TalismanJackiechanModEntities.SHENDU.get(), world);
	}

	public ShenduEntity(EntityType<ShenduEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.RAT_TALISMAN.get()), false));
		this.goalSelector.addGoal(2, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.OX_TALISMAN.get()), false));
		this.goalSelector.addGoal(3, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.TIGER_TALISMAN.get()), false));
		this.goalSelector.addGoal(4, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.RABBIT_TALISMAN.get()), false));
		this.goalSelector.addGoal(5, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.DRAGON_TALISMAN.get()), false));
		this.goalSelector.addGoal(6, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.SNAKE_TALISMAN.get()), false));
		this.goalSelector.addGoal(7, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.HORSE_TALISMAN.get()), false));
		this.goalSelector.addGoal(8, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.SHEEP_TALISMAN.get()), false));
		this.goalSelector.addGoal(9, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.MONKEY_TALISMAN.get()), false));
		this.goalSelector.addGoal(10, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.ROOSTER_TALISMAN.get()), false));
		this.goalSelector.addGoal(11, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.DOG_TALISMAN.get()), false));
		this.goalSelector.addGoal(12, new TemptGoal(this, 3, Ingredient.of(TalismanJackiechanModItems.PIG_TALISMAN.get()), false));
		this.goalSelector.addGoal(13, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.goalSelector.addGoal(14, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(15, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(16, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.goalSelector.addGoal(17, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(18, new FloatGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
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
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}
}