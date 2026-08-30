package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(method = "renderItemInHand", at = @At("HEAD"), cancellable = true)
    private void talisman$hideHand(CallbackInfo ci) {
        if (ClientFreeCamera.isActive()) {
            ci.cancel();
        }
    }
}