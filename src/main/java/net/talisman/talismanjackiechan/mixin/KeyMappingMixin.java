package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.talisman.talismanjackiechan.client.gui.StoneSteleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {

    @Inject(method = "consumeClick", at = @At("HEAD"), cancellable = true)
    private void tjc$blockConsume(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null) return;
        if (mc.screen instanceof StoneSteleScreen s && s.isEditModeActive()) {
            cir.setReturnValue(false);
        }
    }
}