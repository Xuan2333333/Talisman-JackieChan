package net.talisman.talismanjackiechan.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.talisman.talismanjackiechan.morph.MorphAbility;
import net.talisman.talismanjackiechan.morph.MorphRegistry;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinSpiderClimb {

    private static final double PROBE = 0.05D;

    @Inject(method = "onClimbable", at = @At("HEAD"), cancellable = true)
    private void hou$spiderClimb(CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof Player player)) return;
        if (!Hou1Procedure.isPlayerTransformed(player)) return;

        int hou = Hou1Procedure.getPlayerForm(player);
        if (!MorphRegistry.hasAbility(hou, MorphAbility.CLIMB)) return;

        if (isTouchingWall(player)) {
            cir.setReturnValue(true);
        }
    }

    private static boolean isTouchingWall(Player player) {
        AABB base = player.getBoundingBox();
        AABB probe = new AABB(
                base.minX - PROBE, base.minY, base.minZ - PROBE,
                base.maxX + PROBE, base.maxY, base.maxZ + PROBE
        );
        return !player.level().noCollision(player, probe);
    }
}