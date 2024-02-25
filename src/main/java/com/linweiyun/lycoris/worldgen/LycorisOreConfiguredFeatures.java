package com.linweiyun.lycoris.worldgen;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.block.custom.AcheronCrystalCluster;
import com.linweiyun.lycoris.block.OreBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class LycorisOreConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_RED_CRYSTAL_ORE_KEY = registerKey("natural_red_crystal_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_BLUE_CRYSTAL_ORE_KEY = registerKey("natural_blue_crystal_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_GREEN_CRYSTAL_ORE_KEY = registerKey("natural_green_crystal_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATURAL_YELLOW_CRYSTAL_ORE_KEY = registerKey("natural_yellow_crystal_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACHERON_CRYSTAL_CLUSTER_KEY = registerKey("acheron_crystal_cluster_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> naturalRedCrystalOres = List.of(
                OreConfiguration.target(stoneReplaceable,
                        OreBlock.NATURAL_RED_CRYSTAL_ORE.get().defaultBlockState()),
                //如果要加深层的就在这后面写逗号加。记住替换物不能是相同的！如果都替换石头只要第一个会有效
                OreConfiguration.target(deepReplaceable,
                        OreBlock.DEEPSLATE_NATURAL_RED_CRYSTAL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> naturalBlueCrystalOres = List.of(
                OreConfiguration.target(stoneReplaceable,
                        OreBlock.NATURAL_BLUE_CRYSTAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepReplaceable,
                        OreBlock.DEEPSLATE_NATURAL_BLUE_CRYSTAL_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> naturalGreenCrystalOres = List.of(
                OreConfiguration.target(stoneReplaceable,
                        OreBlock.NATURAL_GREEN_CRYSTAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepReplaceable,
                        OreBlock.DEEPSLATE_NATURAL_GREEN_CRYSTAL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> naturalYellowCrystalOres = List.of(
                OreConfiguration.target(stoneReplaceable,
                        OreBlock.NATURAL_YELLOW_CRYSTAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepReplaceable,
                        OreBlock.DEEPSLATE_NATURAL_YELLOW_CRYSTAL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> acheronCrystalClusters = List.of(
                OreConfiguration.target(stoneReplaceable,
                        AcheronCrystalCluster.ACHERON_CRYSTAL_CLUSTER.get().defaultBlockState()),
                OreConfiguration.target(deepReplaceable,
                        AcheronCrystalCluster.ACHERON_CRYSTAL_CLUSTER.get().defaultBlockState())
        );
        register(context, NATURAL_RED_CRYSTAL_ORE_KEY, Feature.ORE, new OreConfiguration(naturalRedCrystalOres, 10));
        //在末地和地狱生成略有不同。https://www.youtube.com/watch?v=GRJyJWtSnvQ&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-&index=40
        // 6：50左右
        register(context, NATURAL_BLUE_CRYSTAL_ORE_KEY, Feature.ORE, new OreConfiguration(naturalBlueCrystalOres,10));
        register(context, NATURAL_GREEN_CRYSTAL_ORE_KEY, Feature.ORE, new OreConfiguration(naturalGreenCrystalOres,10));
        register(context, NATURAL_YELLOW_CRYSTAL_ORE_KEY, Feature.ORE, new OreConfiguration(naturalYellowCrystalOres,10));
        register(context, ACHERON_CRYSTAL_CLUSTER_KEY, Feature.ORE, new OreConfiguration(acheronCrystalClusters, 2));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LycorisMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key,
                                                                                          F feature, FC configuration   ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
