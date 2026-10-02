package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.talisman.talismanjackiechan.client.gui.StoneSteleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {

    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    private void tjc$blockHotkeysDuringSteleEdit(long window, int key, int scancode,
                                                 int action, int modifiers, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.screen == null) return;
        if (!(mc.screen instanceof StoneSteleScreen screen)) return;
        if (!screen.isEditModeActive()) return;
        if (window != mc.getWindow().getWindow()) return;

        if (action == 1 || action == 2) {
            screen.keyPressed(key, scancode, modifiers);
        } else if (action == 0) {
            screen.keyReleased(key, scancode, modifiers);
        }

        ci.cancel();
    }
}