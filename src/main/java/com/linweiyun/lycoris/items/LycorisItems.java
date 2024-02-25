package com.linweiyun.lycoris.items;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LycorisItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LycorisMod.MOD_ID);
    public static final RegistryObject<Item> RED_CRYSTAL_FRAGMENT = ITEMS.register("red_crystal_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CRYSTAL_FRAGMENT = ITEMS.register("blue_crystal_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_CRYSTAL_FRAGMENT = ITEMS.register("green_crystal_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_CRYSTAL_FRAGMENT = ITEMS.register("yellow_crystal_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LV1_INJECTING_MAGIC_CORE_GREEN_BODY_MOLD = ITEMS.register("lv1_injecting_magic_core_green_body_mold",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);
    }
}
