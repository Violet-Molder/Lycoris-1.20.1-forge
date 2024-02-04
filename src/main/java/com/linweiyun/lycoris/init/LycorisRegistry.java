package com.linweiyun.lycoris.init;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LycorisRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LycorisMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LycorisMod.MOD_ID);


    public static <T extends Block> RegistryObject<T> registerBlock(String name, DeferredRegister<Block> RBlock , Supplier<T> block)
    {
        RegistryObject<T> blockObject = RBlock.register(name, block);
        registerBlcokItem(name, blockObject);
        return blockObject;
    }
    private static <T extends Block>  RegistryObject<Item> registerBlcokItem(String name, RegistryObject<T> block){
        return ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));

    }
}
