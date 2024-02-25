package com.linweiyun.lycoris.datagen;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.worldgen.LycorisBiomeModifiers;
import com.linweiyun.lycoris.worldgen.LycorisOreConfiguredFeatures;
import com.linweiyun.lycoris.worldgen.LycorisPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LycorisWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, LycorisOreConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, LycorisPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, LycorisBiomeModifiers::bootstrap);
    public LycorisWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(LycorisMod.MOD_ID));
    }
}
