package com.linweiyun.lycoris.block;

import com.linweiyun.lycoris.init.LycorisRegistry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class OreBlock  {

    public static final RegistryObject<Block> NATURAL_YELLOW_CRYSTAL_ORE = LycorisRegistry.registerBlock("natural_yellow_crystal_ore",
            LycorisRegistry.BLOCKS, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(6f),
                    UniformInt.of(1, 7)));

    public static final RegistryObject<Block> NATURAL_GREEN_CRYSTAL_ORE = LycorisRegistry.registerBlock("natural_green_crystal_ore",
            LycorisRegistry.BLOCKS, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(6f),
                    UniformInt.of(1, 7)));
    public static final RegistryObject<Block> NATURAL_BLUE_CRYSTAL_ORE = LycorisRegistry.registerBlock("natural_blue_crystal_ore",
            LycorisRegistry.BLOCKS, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(6f),
                    UniformInt.of(1, 7)));

    public static final RegistryObject<Block> NATURAL_RED_CRYSTAL_ORE = LycorisRegistry.registerBlock("natural_red_crystal_ore",
            LycorisRegistry.BLOCKS, () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(6f),
                    UniformInt.of(1, 7)));


    public static void register(IEventBus eventBus)
    {
        LycorisRegistry.BLOCKS.register(eventBus);
    }




}
