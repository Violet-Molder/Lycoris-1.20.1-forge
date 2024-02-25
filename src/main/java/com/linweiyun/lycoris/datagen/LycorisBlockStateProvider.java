package com.linweiyun.lycoris.datagen;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.block.DecorativeBlocks;
import com.linweiyun.lycoris.block.OreBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class LycorisBlockStateProvider extends BlockStateProvider {
    public LycorisBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LycorisMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(OreBlock.NATURAL_RED_CRYSTAL_ORE);
        blockWithItem(OreBlock.NATURAL_BLUE_CRYSTAL_ORE);
        blockWithItem(OreBlock.NATURAL_GREEN_CRYSTAL_ORE);
        blockWithItem(OreBlock.NATURAL_YELLOW_CRYSTAL_ORE);
        blockWithItem(OreBlock.DEEPSLATE_NATURAL_RED_CRYSTAL_ORE);
        blockWithItem(OreBlock.DEEPSLATE_NATURAL_BLUE_CRYSTAL_ORE);
        blockWithItem(OreBlock.DEEPSLATE_NATURAL_GREEN_CRYSTAL_ORE);
        blockWithItem(OreBlock.DEEPSLATE_NATURAL_YELLOW_CRYSTAL_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
