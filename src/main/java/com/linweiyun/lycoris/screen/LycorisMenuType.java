package com.linweiyun.lycoris.screen;
import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LycorisMenuType {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, LycorisMod.MOD_ID);


    public static final RegistryObject<MenuType<Lv1InjectingMagicAltarMenu>> LV1_INJECTING_MAGIC_ALTAR_MENU_TYPE =
            registerMenuType(Lv1InjectingMagicAltarMenu::new,"lvj_injecting_magic_altar_menu_type");
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name){
        return MENU_TYPES.register(name,()-> IForgeMenuType.create(factory));
    }
    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }
}
