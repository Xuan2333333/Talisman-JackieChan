package net.talisman.talismanjackiechan.mixin;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.talisman.talismanjackiechan.morph.HouMorphSizes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayerMorphSize {

    @Inject(method = "getDimensions", at = @At("HEAD"), cancellable = true)
    private void hou$getDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        EntityDimensions dims = HouMorphSizes.getDimensions((Player) (Object) this);
        if (dims != null) {
            cir.setReturnValue(dims);
        }
    }

    @Inject(method = "getStandingEyeHeight", at = @At("HEAD"), cancellable = true)
    private void hou$getStandingEyeHeight(Pose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> cir) {
        Float eye = HouMorphSizes.getEyeHeight((Player) (Object) this);
        if (eye != null) {
            cir.setReturnValue(eye);
        }
    }
}