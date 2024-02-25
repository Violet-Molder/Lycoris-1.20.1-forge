package com.linweiyun.lycoris.datagen;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.repository.Pack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = LycorisMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new LycorisBlockStateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new LycorisItemModelProvider(output, existingFileHelper));

        LycorisBlockTagProvider blockTagGenerator = generator.addProvider(event.includeServer(),
                new LycorisBlockTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeClient(), new LycorisItemTagProvider(output, lookupProvider,
                blockTagGenerator.contentsGetter(), existingFileHelper));


        generator.addProvider(event.includeServer(), new LycorisWorldGenProvider(output, lookupProvider));
    }
}
