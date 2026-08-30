package net.talisman.talismanjackiechan.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.talisman.talismanjackiechan.init.TalismanJackiechanModEntities;

public class PigTalismanPowerEntity extends AbstractArrow implements net.minecraft.world.entity.projectile.ItemSupplier {

	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Blocks.AIR);

	public PigTalismanPowerEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(), world);
		this.setSilent(true);
		this.setNoGravity(true);
	}

	public PigTalismanPowerEntity(EntityType<? extends PigTalismanPowerEntity> type, Level world) {
		super(type, world);
		this.setSilent(true);
		this.setNoGravity(true);
	}

	public PigTalismanPowerEntity(EntityType<? extends PigTalismanPowerEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
		this.setSilent(true);
		this.setNoGravity(true);
	}

	public PigTalismanPowerEntity(EntityType<? extends PigTalismanPowerEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
		this.setSilent(true);
		this.setNoGravity(true);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (!this.level().isClientSide()) {
			if (result.getEntity() == this.getOwner()) {
				return;
			}

			DamageSource chiMagic = new DamageSource(
					this.level().registryAccess()
							.registryOrThrow(Registries.DAMAGE_TYPE)
							.getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("talisman_jackiechan", "chi_magic"))),
					this,
					this.getOwner()
			);

			if (result.getEntity() instanceof LivingEntity target) {
				target.hurt(chiMagic, (float) this.getBaseDamage());
				target.setArrowCount(target.getArrowCount() - 1);
			}
			this.spawnHitParticles();
			this.discard();
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.setNoGravity(true);


		if (!this.level().isClientSide()) {
			if (this.level() instanceof ServerLevel serverLevel) {
				double prevX = this.xOld;
				double prevY = this.yOld;
				double prevZ = this.zOld;
				double currX = this.getX();
				double currY = this.getY();
				double currZ = this.getZ();
				double dx = currX - prevX;
				double dy = currY - prevY;
				double dz = currZ - prevZ;
				double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

				int particleCount = Math.max(1, (int) Math.ceil(distance / 0.2));


				for (int i = 0; i < particleCount; i++) {
					double t = (i + 1) / (double) particleCount;
					double px = prevX + dx * t;
					double py = prevY + dy * t;
					double pz = prevZ + dz * t;

					serverLevel.sendParticles(
							ParticleTypes.FLAME,
							px, py, pz,
							1,
							0.0, 0.0, 0.0,
							0.1
					);
				}
			}
		}

		if (this.inGround) {
			this.spawnHitParticles();
			this.discard();
		}
	}

	private void spawnHitParticles() {
		if (!this.level().isClientSide()) {
			if (this.level() instanceof ServerLevel serverLevel) {
				for (int i = 0; i < 12; i++) {
					serverLevel.sendParticles(
							ParticleTypes.FLAME,
							this.getX(),
							this.getY(),
							this.getZ(),
							1,
							(this.random.nextDouble() - 0.5) * 0.8,
							(this.random.nextDouble() - 0.5) * 0.8,
							(this.random.nextDouble() - 0.5) * 0.8,
							0.5
					);
				}
			}
		}
	}

	public static PigTalismanPowerEntity shoot(Level world, LivingEntity entity, net.minecraft.util.RandomSource source) {
		return shoot(world, entity, source, 1f, 5, 5);
	}

	public static PigTalismanPowerEntity shoot(Level world, LivingEntity entity, net.minecraft.util.RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 1f, 5, 5);
	}

	public static PigTalismanPowerEntity shoot(Level world, LivingEntity entity, net.minecraft.util.RandomSource random, float power, double damage, int knockback) {
		PigTalismanPowerEntity entityarrow = new PigTalismanPowerEntity(TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(true);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		return entityarrow;
	}

	public static PigTalismanPowerEntity shoot(LivingEntity entity, LivingEntity target) {
		PigTalismanPowerEntity entityarrow = new PigTalismanPowerEntity(TalismanJackiechanModEntities.PIG_TALISMAN_POWER.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(12);
		entityarrow.setKnockback(0);
		entityarrow.setCritArrow(true);
		entity.level().addFreshEntity(entityarrow);
		return entityarrow;
	}
}