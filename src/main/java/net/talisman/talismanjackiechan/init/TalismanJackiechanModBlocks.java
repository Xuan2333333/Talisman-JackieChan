
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.block.LotusPodBlock;
import net.talisman.talismanjackiechan.block.LoPeiStatueBlock;
import net.talisman.talismanjackiechan.block.LoPeiStatue2Block;
import net.talisman.talismanjackiechan.block.GourdBlock;
import net.talisman.talismanjackiechan.block.DrumBlock;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class TalismanJackiechanModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, TalismanJackiechanMod.MODID);
	public static final RegistryObject<Block> LO_PEI_STATUE = REGISTRY.register("lo_pei_statue", LoPeiStatueBlock::new);
	public static final RegistryObject<Block> LO_PEI_STATUE_2 = REGISTRY.register("lo_pei_statue_2", LoPeiStatue2Block::new);
	public static final RegistryObject<Block> LOTUS_POD = REGISTRY.register("lotus_pod", LotusPodBlock::new);
	public static final RegistryObject<Block> DRUM = REGISTRY.register("drum", DrumBlock::new);
	public static final RegistryObject<Block> GOURD = REGISTRY.register("gourd", GourdBlock::new);
	// Start of user code block custom blocks
	// End of user code block custom blocks
}