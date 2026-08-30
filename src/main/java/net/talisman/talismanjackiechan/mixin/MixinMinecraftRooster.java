package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.Minecraft;
import net.talisman.talismanjackiechan.item.RoosterTalismanItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraftRooster {

    @Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
    private void talisman$blockRoosterUse(CallbackInfo ci) {
        Minecraft mc = (Minecraft) (Object) this;
        if (mc.player != null
                && mc.player.getMainHandItem().getItem() instanceof RoosterTalismanItem) {
            ci.cancel();
        }
    }
}