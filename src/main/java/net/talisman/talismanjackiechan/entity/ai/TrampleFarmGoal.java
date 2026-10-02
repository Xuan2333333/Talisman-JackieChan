package net.talisman.talismanjackiechan.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class TrampleFarmGoal extends Goal {
    private final Mob mob;
    private BlockPos target;
    private int cooldown = 0;

    public TrampleFarmGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (mob.getTarget() != null && mob.getTarget().isAlive()) return false;
        if (cooldown > 0) { cooldown--; return false; }

        this.cooldown = 100 + mob.getRandom().nextInt(200);
        if (mob.getRandom().nextFloat() > 0.35F) return false;

        this.target = findFarmland();
        return this.target != null;
    }

    private BlockPos findFarmland() {
        BlockPos origin = mob.blockPosition();
        for (int i = 0; i < 24; i++) {
            int dx = mob.getRandom().nextInt(17) - 8;
            int dy = mob.getRandom().nextInt(5) - 2;
            int dz = mob.getRandom().nextInt(17) - 8;
            BlockPos base = origin.offset(dx, dy, dz);
            BlockState state = mob.level().getBlockState(base);
            if (state.is(Blocks.FARMLAND)
                    && mob.level().getBlockState(base.above()).getBlock() instanceof CropBlock) {
                return base.above();
            }
        }
        return null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null
                && mob.getTarget() == null
                && mob.distanceToSqr(Vec3.atCenterOf(target)) > 1.2D;
    }

    @Override
    public void start() {
        if (target != null) {
            mob.getNavigation().moveTo(
                    target.getX() + 0.5, target.getY(), target.getZ() + 0.5, 1.1D);
        }
    }

    @Override
    public void tick() {
        if (target == null) return;
        if (mob.distanceToSqr(Vec3.atCenterOf(target)) <= 4.0D) {
            BlockState state = mob.level().getBlockState(target);
            if (state.getBlock() instanceof CropBlock) {
                mob.level().destroyBlock(target, true, mob);
            }
            this.target = null;
        }
    }

    @Override
    public void stop() { this.target = null; }
}