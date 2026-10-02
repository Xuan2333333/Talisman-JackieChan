package net.talisman.talismanjackiechan.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;
import net.talisman.talismanjackiechan.block.StoneSteleBlockEntity;

public class TalismanJackiechanModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TalismanJackiechanMod.MODID);

    public static final RegistryObject<BlockEntityType<StoneSteleBlockEntity>> STONE_STELE =
            REGISTRY.register("stone_stele",
                    () -> BlockEntityType.Builder.of(StoneSteleBlockEntity::new,
                            TalismanJackiechanModBlocks.STONE_STELE.get()).build(null));
}