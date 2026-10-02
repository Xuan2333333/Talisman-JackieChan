package net.talisman.talismanjackiechan.mixin;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.talisman.talismanjackiechan.client.HouMorphClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRendererMorphArm {

    @Inject(method = "renderRightHand", at = @At("HEAD"), cancellable = true)
    private void hou$hideRightHand(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                                   AbstractClientPlayer player, CallbackInfo ci) {
        if (HouMorphClient.isActive(player.getUUID())) {
            ci.cancel();
        }
    }

    @Inject(method = "renderLeftHand", at = @At("HEAD"), cancellable = true)
    private void hou$hideLeftHand(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                                  AbstractClientPlayer player, CallbackInfo ci) {
        if (HouMorphClient.isActive(player.getUUID())) {
            ci.cancel();
        }
    }
}