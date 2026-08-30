package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.MouseHandler;
import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MixinMouseHandlerScroll {
    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void talisman$blockHotbarScroll(long window, double xOffset, double yOffset, CallbackInfo ci) {
        if (ClientFreeCamera.isActive()) {
            ci.cancel();
        }
    }
}