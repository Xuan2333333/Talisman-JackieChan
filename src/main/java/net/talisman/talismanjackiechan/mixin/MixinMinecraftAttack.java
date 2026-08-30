package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.Minecraft;
import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraftAttack {

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void talisman$blockStartAttack(CallbackInfoReturnable<Boolean> cir) {
        if (ClientFreeCamera.isActive()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void talisman$blockContinueAttack(CallbackInfo ci) {
        if (ClientFreeCamera.isActive()) {
            ci.cancel();
        }
    }

    @Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
    private void talisman$blockStartUse(CallbackInfo ci) {
        if (ClientFreeCamera.isActive()) {
            ClientFreeCamera.deactivate();
            ci.cancel();
        }
    }
}