package com.linweiyun.lycoris.worldgen;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class LycorisPlacedFeatures {

    public static final ResourceKey<PlacedFeature> NATURAL_RED_CRYSTAL_ORE_PLACED_KEY = registerKey("natural_red_crystal_ore_placed");
    public static final ResourceKey<PlacedFeature> NATURAL_BLUE_CRYSTAL_ORE_PLACED_KEY = registerKey("natural_blue_crystal_ore_placed");
    public static final ResourceKey<PlacedFeature> NATURAL_GREEN_CRYSTAL_ORE_PLACED_KEY = registerKey("natural_green_crystal_ore_placed");
    public static final ResourceKey<PlacedFeature> NATURAL_YELLOW_CRYSTAL_ORE_PLACED_KEY = registerKey("natural_yellow_crystal_ore_placed");
    public static final ResourceKey<PlacedFeature> ACHERON_CRYSTAL_CLUSTER_PLACED_KEY = registerKey("acheron_crystal_cluster_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, NATURAL_RED_CRYSTAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(LycorisOreConfiguredFeatures.NATURAL_RED_CRYSTAL_ORE_KEY),
                LycorisOrePlacement.commonOrePlacement(12,//数量        //uniform表示均匀生成
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(120))));

        register(context, NATURAL_BLUE_CRYSTAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(LycorisOreConfiguredFeatures.NATURAL_BLUE_CRYSTAL_ORE_KEY),
                LycorisOrePlacement.commonOrePlacement(12,//数量        //uniform表示均匀生成
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(120))));

        register(context, NATURAL_GREEN_CRYSTAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(LycorisOreConfiguredFeatures.NATURAL_GREEN_CRYSTAL_ORE_KEY),
                LycorisOrePlacement.commonOrePlacement(12,//数量        //uniform表示均匀生成
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(120))));

        register(context, NATURAL_YELLOW_CRYSTAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(LycorisOreConfiguredFeatures.NATURAL_YELLOW_CRYSTAL_ORE_KEY),
                LycorisOrePlacement.commonOrePlacement(12,//数量        //uniform表示均匀生成
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(120))));
        register(context, ACHERON_CRYSTAL_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(LycorisOreConfiguredFeatures.ACHERON_CRYSTAL_CLUSTER_KEY),
                LycorisOrePlacement.commonOrePlacement(1,//数量        //uniform表示均匀生成
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(10))));


    }
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(LycorisMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
