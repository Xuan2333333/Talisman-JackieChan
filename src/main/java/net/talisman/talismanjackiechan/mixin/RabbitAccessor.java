package net.talisman.talismanjackiechan.mixin;

import net.minecraft.world.entity.animal.Rabbit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Rabbit.class)
public interface RabbitAccessor {

    @Accessor("jumpTicks")
    int talisman$getJumpTicks();

    @Accessor("jumpTicks")
    void talisman$setJumpTicks(int jumpTicks);

    @Accessor("jumpDuration")
    int talisman$getJumpDuration();

    @Accessor("jumpDuration")
    void talisman$setJumpDuration(int jumpDuration);
}