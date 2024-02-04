package com.linweiyun.lycoris.items;

import com.linweiyun.lycoris.init.LycorisRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final RegistryObject<Item> RED_CRYSTAL_FRAGMENT = LycorisRegistry.ITEMS.register("red_crystal_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_CRYSTAL_FRAGMENT = LycorisRegistry.ITEMS.register("blue_crystal_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_CRYSTAL_FRAGMENT = LycorisRegistry.ITEMS.register("green_crystal_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_CRYSTAL_FRAGMENT = LycorisRegistry.ITEMS.register("yellow_crystal_fragment",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {LycorisRegistry.ITEMS.register(eventBus);
    }
}
