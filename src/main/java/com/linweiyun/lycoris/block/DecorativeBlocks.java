package com.linweiyun.lycoris.block;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.init.LycorisRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DecorativeBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LycorisMod.MOD_ID);
    public static final RegistryObject<Block> LV1_INJECTING_MAGIC_ALTAR_CORE_1 = LycorisRegistry.registerBlock("lv1_injecting_magic_altar_core_1",
        BLOCKS, () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .strength(6f)));
    public static final RegistryObject<Block> LV1_INJECTING_MAGIC_ALTAR_CORE_GREEN_BODY = LycorisRegistry.registerBlock("lv1_injecting_magic_altar_core_green_body",
            BLOCKS, () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .strength(6f)));


    public static final RegistryObject<Block> ACHERON_CRYSTAL = LycorisRegistry.registerFireproofBlock("acheron_crystal",
            BLOCKS, () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
                    .noOcclusion()
                    .strength(6f, 3000f)));

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
