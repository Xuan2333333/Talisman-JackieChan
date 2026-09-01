package net.talisman.talismanjackiechan.mixin;

import net.minecraft.world.entity.WalkAnimationState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WalkAnimationState.class)
public interface WalkAnimationStateAccessor {

    @Accessor("position")
    float talisman$getPosition();

    @Accessor("position")
    void talisman$setPosition(float position);

    @Accessor("speedOld")
    float talisman$getSpeedOld();

    @Accessor("speedOld")
    void talisman$setSpeedOld(float speedOld);
}