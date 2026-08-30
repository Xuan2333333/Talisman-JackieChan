package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.Minecraft;
import net.talisman.talismanjackiechan.item.PigTalismanItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraftPigLaser {

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void talisman$blockStartAttack(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = (Minecraft) (Object) this;
        if (mc.player != null
                && mc.player.getMainHandItem().getItem() instanceof PigTalismanItem) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void talisman$blockContinueAttack(CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;
        if (mc.player != null
                && mc.player.getMainHandItem().getItem() instanceof PigTalismanItem) {
            ci.cancel();
        }
    }

    @Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
    private void talisman$blockStartUse(CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;
        if (mc.player != null
                && mc.player.getMainHandItem().getItem() instanceof PigTalismanItem) {
            ci.cancel();
        }
    }
}