package net.talisman.talismanjackiechan.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
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
	protected boolean canHitEntity(Entity entity) {
		if (entity == this.getOwner()) {
			return false;
		}
		return super.canHitEntity(entity);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (this.level().isClientSide()) return;
		if (result.getEntity() == this.getOwner()) return;

		DamageSource chiMagic = new DamageSource(
				this.level().registryAccess()
						.registryOrThrow(Registries.DAMAGE_TYPE)
						.getHolderOrThrow(ResourceKey.create(
								Registries.DAMAGE_TYPE,
								new ResourceLocation("talisman_jackiechan", "chi_magic"))),
				this,
				this.getOwner()
		);

		if (result.getEntity() instanceof LivingEntity target) {
			target.hurt(chiMagic, (float) this.getBaseDamage());
			target.setArrowCount(Math.max(0, target.getArrowCount() - 1));
			target.setSecondsOnFire(3);
		}

		Vec3 hit = result.getLocation();
		spawnHitParticles(hit.x, hit.y, hit.z);
		this.discard();
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		if (!this.level().isClientSide()) {
			Vec3 hit = result.getLocation();
			spawnHitParticles(hit.x, hit.y, hit.z);
			this.discard();
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.setNoGravity(true);

		if (this.tickCount < 2) {
			return;
		}

		if (this.inGround) {
			spawnHitParticles(this.getX(), this.getY(), this.getZ());
			this.discard();
			return;
		}

		if (this.tickCount > 40) {
			this.discard();
		}
	}

	private void spawnHitParticles(double x, double y, double z) {
		if (!(this.level() instanceof ServerLevel serverLevel)) return;
		for (int i = 0; i < 12; i++) {
			serverLevel.sendParticles(
					ParticleTypes.FLAME,
					x, y, z,
					1,
					(this.random.nextDouble() - 0.5) * 0.5,
					(this.random.nextDouble() - 0.5) * 0.5,
					(this.random.nextDouble() - 0.5) * 0.5,
					0.04
			);
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