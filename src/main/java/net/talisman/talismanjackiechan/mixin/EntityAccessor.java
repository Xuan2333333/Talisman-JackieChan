package net.talisman.talismanjackiechan.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {

    @Accessor("wasTouchingWater")
    void talisman$setWasTouchingWater(boolean value);

    @Accessor("wasTouchingWater")
    boolean talisman$getWasTouchingWater();
}