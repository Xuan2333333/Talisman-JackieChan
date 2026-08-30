package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinClientPlayerEntity {

    @Shadow public Input input;

    @Unique
    private Input talisman$realInput;

    @Inject(method = "tick", at = @At("HEAD"))
    private void talisman$disableInputPre(CallbackInfo ci) {
        if (ClientFreeCamera.isActive()) {
            this.talisman$realInput = this.input;
            this.input = new Input();
        }
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void talisman$disableInputPost(CallbackInfo ci) {
        if (this.talisman$realInput != null) {
            this.input = this.talisman$realInput;
            this.talisman$realInput = null;
        }
    }
}