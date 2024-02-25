package com.linweiyun.lycoris.datagen;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.block.custom.AcheronCrystalCluster;
import com.linweiyun.lycoris.block.DecorativeBlocks;
import com.linweiyun.lycoris.block.OreBlock;
import com.linweiyun.lycoris.util.LycorisTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LycorisBlockTagProvider extends BlockTagsProvider {
    public LycorisBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,@Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LycorisMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(LycorisTags.Blocks.NEEDS_RED_CRYSTAL_TOOL)
                .add(
                        AcheronCrystalCluster.ACHERON_CRYSTAL_CLUSTER.get()
                );

        //forge提供的tag
        this.tag(Tags.Blocks.ORES)
                .add(
                        OreBlock.NATURAL_RED_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_BLUE_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_GREEN_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_YELLOW_CRYSTAL_ORE.get()
                );

        //this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
          //      .add();


        //原版提供的tag
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        OreBlock.NATURAL_RED_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_BLUE_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_GREEN_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_YELLOW_CRYSTAL_ORE.get(),
                        DecorativeBlocks.LV1_INJECTING_MAGIC_ALTAR_CORE_1.get()
                );



        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        OreBlock.NATURAL_RED_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_BLUE_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_GREEN_CRYSTAL_ORE.get(),
                        OreBlock.NATURAL_YELLOW_CRYSTAL_ORE.get(),
                        DecorativeBlocks.LV1_INJECTING_MAGIC_ALTAR_CORE_1.get(),
                        AcheronCrystalCluster.ACHERON_CRYSTAL_CLUSTER.get()
                );

    }

}
