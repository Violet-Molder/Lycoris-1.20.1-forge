package com.linweiyun.lycoris.items;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.init.LycorisRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LycorisTools {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LycorisMod.MOD_ID);
    public static final RegistryObject<Item> RED_CRYSTAL_SHOVE = ITEMS.register("red_crystal_shove",
            () -> new ShovelItem(LycorisTiers.RED_CRYSTAL, 1f, -3.0f ,new Item.Properties()));

    public static final RegistryObject<Item> RED_CRYSTAL_PICKAXE = ITEMS.register("red_crystal_pickaxe",
            () -> new PickaxeItem(LycorisTiers.RED_CRYSTAL, 1, -2.8f ,new Item.Properties()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);
    }
}
