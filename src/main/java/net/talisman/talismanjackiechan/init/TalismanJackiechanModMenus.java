package net.talisman.talismanjackiechan.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.talisman.talismanjackiechan.TalismanJackiechanMod;
import net.talisman.talismanjackiechan.menu.StoneSteleMenu;

public class TalismanJackiechanModMenus {

    public static final DeferredRegister<MenuType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, TalismanJackiechanMod.MODID);

    public static final RegistryObject<MenuType<StoneSteleMenu>> STONE_STELE =
            REGISTRY.register("stone_stele",
                    () -> IForgeMenuType.create(StoneSteleMenu::new));
}