
package net.talisman.talismanjackiechan.init;

import net.talisman.talismanjackiechan.block.*;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class TalismanJackiechanModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, TalismanJackiechanMod.MODID);
	public static final RegistryObject<Block> LO_PEI_STATUE = REGISTRY.register("lo_pei_statue", LoPeiStatue::new);
	public static final RegistryObject<Block> LO_PEI_STATUE_2 = REGISTRY.register("lo_pei_statue_2", LoPeiStatue2::new);
	public static final RegistryObject<Block> LOTUS_POD = REGISTRY.register("lotus_pod", LotusPod::new);
	public static final RegistryObject<Block> DRUM = REGISTRY.register("drum", Drum::new);
	public static final RegistryObject<Block> GOURD = REGISTRY.register("gourd", Gourd::new);
	public static final RegistryObject<Block> ZHONGWU_HALL_PLAQUE = REGISTRY.register("zhongwu_hall_plaque", ZhongwuHallPlaque::new);
	public static final RegistryObject<Block> SHRINE_OF_VENERABLE_LO_PEI_PLAQUE = REGISTRY.register("shrine_of_venerable_lo_pei_plaque", ShrineOfVenerableLoPeiPlaque::new);
	public static final RegistryObject<Block> ZHONGWU_HALL_UPPER_COUPLET = REGISTRY.register("zhongwu_hall_upper_couplet", ZhongwuHallUpperCouplet::new);
	public static final RegistryObject<Block> ZHONGWU_HALL_LOWER_COUPLET = REGISTRY.register("zhongwu_hall_lower_couplet", ZhongwuHallLowerCouplet::new);
	public static final RegistryObject<Block> SMALL_CENSER = REGISTRY.register("small_censer", SmallCenser::new);
	public static final RegistryObject<Block> LO_PEI_TABLET = REGISTRY.register("lo_pei_tablet", LoPeiTablet::new);
	public static final RegistryObject<Block> CENSER = REGISTRY.register("censer", Censer::new);
	public static final RegistryObject<Block> STONE_STELE = REGISTRY.register("stone_stele", StoneStele::new);

}