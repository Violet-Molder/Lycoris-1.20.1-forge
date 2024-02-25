package com.linweiyun.lycoris.worldgen;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class LycorisBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_NATURAL_RED_CRYSTAL_ORE = registerKey("add_natural_red_crystal_ore");
    public static final ResourceKey<BiomeModifier> ADD_NATURAL_BLUE_CRYSTAL_ORE = registerKey("add_natural_blue_crystal_ore");
    public static final ResourceKey<BiomeModifier> ADD_NATURAL_GREEN_CRYSTAL_ORE = registerKey("add_natural_green_crystal_ore");
    public static final ResourceKey<BiomeModifier> ADD_NATURAL_YELLOW_CRYSTAL_ORE = registerKey("add_natural_yellow_crystal_ore");
    public static final ResourceKey<BiomeModifier> ADD_ACHERON_CRYSTAL_CLUSTER = registerKey("add_acheron_crystal_cluster");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_NATURAL_RED_CRYSTAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),//选择生成在什么生物群落。这里选择的是整个主世界
                HolderSet.direct(placedFeature.getOrThrow(LycorisPlacedFeatures.NATURAL_RED_CRYSTAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NATURAL_BLUE_CRYSTAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(LycorisPlacedFeatures.NATURAL_BLUE_CRYSTAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NATURAL_GREEN_CRYSTAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(LycorisPlacedFeatures.NATURAL_GREEN_CRYSTAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NATURAL_YELLOW_CRYSTAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(LycorisPlacedFeatures.NATURAL_YELLOW_CRYSTAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ACHERON_CRYSTAL_CLUSTER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(LycorisPlacedFeatures.ACHERON_CRYSTAL_CLUSTER_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(LycorisMod.MOD_ID, name));
    }
}
