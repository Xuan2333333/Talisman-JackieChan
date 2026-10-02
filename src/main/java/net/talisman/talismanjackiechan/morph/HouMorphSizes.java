package net.talisman.talismanjackiechan.morph;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.talisman.talismanjackiechan.client.HouMorphClient;
import net.talisman.talismanjackiechan.procedures.Hou1Procedure;

public class HouMorphSizes {

    public static EntityDimensions getDimensions(Player player) {
        if (player.level().isClientSide()) {
            return DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> clientDimensions(player));
        }
        return serverDimensions(player);
    }

    public static Float getEyeHeight(Player player) {
        if (player.level().isClientSide()) {
            return DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> clientEyeHeight(player));
        }
        return serverEyeHeight(player);
    }

    private static EntityDimensions serverDimensions(Player player) {
        if (!Hou1Procedure.isPlayerTransformed(player)) {
            return null;
        }
        EntityType<?> type = Hou1Procedure.getEntityType(Hou1Procedure.getPlayerForm(player));
        if (type == null) {
            return null;
        }
        return EntityDimensions.scalable(type.getWidth(), type.getHeight());
    }

    private static Float serverEyeHeight(Player player) {
        EntityDimensions d = serverDimensions(player);
        if (d == null) {
            return null;
        }
        return d.height * 0.85F;
    }

    private static EntityDimensions clientDimensions(Player player) {
        LivingEntity shadow = HouMorphClient.getShadow(player.getUUID());
        if (shadow == null) {
            return null;
        }
        return EntityDimensions.scalable(shadow.getBbWidth(), shadow.getBbHeight());
    }

    private static Float clientEyeHeight(Player player) {
        LivingEntity shadow = HouMorphClient.getShadow(player.getUUID());
        if (shadow == null) {
            return null;
        }
        return shadow.getEyeHeight();
    }
}