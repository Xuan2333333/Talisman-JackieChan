package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.talisman.talismanjackiechan.client.CameraEntity;
import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MixinMouseHandler {

    @Shadow @Final private Minecraft minecraft;
    @Shadow private double accumulatedDX;
    @Shadow private double accumulatedDY;

    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void talisman$redirectLookToCamera(CallbackInfo ci) {
        if (!ClientFreeCamera.isActive()) {
            return;
        }

        CameraEntity camera = ClientFreeCamera.getCamera();
        if (camera == null || this.minecraft.player == null) {
            return;
        }

        double sensitivity = this.minecraft.options.sensitivity().get() * 0.6F + 0.2F;
        double factor = sensitivity * sensitivity * sensitivity * 8.0D;
        double yaw = this.accumulatedDX * factor;
        double pitch = this.accumulatedDY * factor;

        this.accumulatedDX = 0.0D;
        this.accumulatedDY = 0.0D;

        int invert = this.minecraft.options.invertYMouse().get() ? -1 : 1;

        camera.turn(yaw, pitch * invert);

        ci.cancel();
    }
}