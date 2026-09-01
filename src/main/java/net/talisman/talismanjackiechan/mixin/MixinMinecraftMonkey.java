package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.Minecraft;
import net.talisman.talismanjackiechan.item.MonkeyTalismanItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraftMonkey {

    private static boolean holdingMonkey(Minecraft mc) {
        return mc.player != null
                && mc.player.getMainHandItem().getItem() instanceof MonkeyTalismanItem;
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void talisman$monkeyBlockStartAttack(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = (Minecraft) (Object) this;
        if (holdingMonkey(mc)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void talisman$monkeyBlockContinueAttack(CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;
        if (holdingMonkey(mc)) {
            ci.cancel();
        }
    }
}