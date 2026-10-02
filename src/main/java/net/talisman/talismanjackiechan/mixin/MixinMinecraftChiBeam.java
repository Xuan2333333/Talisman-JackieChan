package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.talisman.talismanjackiechan.item.MagicLizardItem;
import net.talisman.talismanjackiechan.item.MagicPufferfishItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraftChiBeam {

    private static boolean isHoldingChiItem() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return false;
        Item main = mc.player.getMainHandItem().getItem();
        Item off = mc.player.getOffhandItem().getItem();
        return main instanceof MagicPufferfishItem || main instanceof MagicLizardItem
                || off instanceof MagicPufferfishItem || off instanceof MagicLizardItem;
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void chi$blockStartAttack(CallbackInfoReturnable<Boolean> cir) {
        if (isHoldingChiItem()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void chi$blockContinueAttack(CallbackInfo ci) {
        if (isHoldingChiItem()) {
            ci.cancel();
        }
    }

    @Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
    private void chi$blockStartUse(CallbackInfo ci) {
        if (isHoldingChiItem()) {
            ci.cancel();
        }
    }
}