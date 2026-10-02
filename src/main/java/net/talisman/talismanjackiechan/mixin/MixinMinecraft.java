package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.talisman.talismanjackiechan.client.ClientFreeCamera;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Shadow @Final public Options options;

    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void talisman$blockInventoryKeys(CallbackInfo ci) {
        if (!ClientFreeCamera.isActive()) {
            return;
        }

        while (this.options.keyAttack.consumeClick()) {
        }

        while (this.options.keyUse.consumeClick()) {
            ClientFreeCamera.deactivate();
        }
        while (this.options.keyDrop.consumeClick()) {}

        while (this.options.keyInventory.consumeClick()) {}

        while (this.options.keySwapOffhand.consumeClick()) {}

        KeyMapping[] hotbar = this.options.keyHotbarSlots;
        if (hotbar != null) {
            for (KeyMapping key : hotbar) {
                if (key != null) {
                    while (key.consumeClick()) {}
                }
            }
        }
    }
}