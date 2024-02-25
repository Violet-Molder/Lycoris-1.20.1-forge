package com.linweiyun.lycoris;

import com.linweiyun.lycoris.block.custom.AcheronCrystalCluster;
import com.linweiyun.lycoris.block.DecorativeBlocks;
import com.linweiyun.lycoris.block.OreBlock;
import com.linweiyun.lycoris.block.custom.Lv1InjectingMagicAltarCore;
import com.linweiyun.lycoris.items.LycorisItems;
import com.linweiyun.lycoris.items.LycorisTools;
import com.linweiyun.lycoris.items.custom.MetalDetectorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemGroups {
    public static final String ORE_GROUP = "creativetab.lycoris.ore_tab";
    public static final String INJECTING_MAGIC_CORE_GROUP = "creativetab.lycoris.decorative_blocks_tab";
    public static final String TOOL_GROUP = "creativetab.lycoris.tools_tab";

    public static final String ITEM_GROUP = "creativetab.lycoris.item_tab";
    public static final DeferredRegister<CreativeModeTab> ITEM_GROUPS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LycorisMod.MOD_ID);

    //矿物
    public static final RegistryObject<CreativeModeTab> LYCORIS_ORE = ITEM_GROUPS.register("lycoris_ore",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(LycorisItems.RED_CRYSTAL_FRAGMENT.get()))
                    .title(     Component.translatable(ORE_GROUP))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(LycorisItems.YELLOW_CRYSTAL_FRAGMENT.get());
                        pOutput.accept(LycorisItems.GREEN_CRYSTAL_FRAGMENT.get());
                        pOutput.accept(LycorisItems.BLUE_CRYSTAL_FRAGMENT.get());
                        pOutput.accept(LycorisItems.RED_CRYSTAL_FRAGMENT.get());

                        pOutput.accept(OreBlock.NATURAL_YELLOW_CRYSTAL_ORE.get());
                        pOutput.accept(OreBlock.NATURAL_GREEN_CRYSTAL_ORE.get());
                        pOutput.accept(OreBlock.NATURAL_BLUE_CRYSTAL_ORE.get());
                        pOutput.accept(OreBlock.NATURAL_RED_CRYSTAL_ORE.get());
                        pOutput.accept(AcheronCrystalCluster.ACHERON_CRYSTAL_CLUSTER.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> LYCORIS_FUNCTIONAL_BLOCKS = ITEM_GROUPS.register("lycoris_decorative_blocks",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(DecorativeBlocks.LV1_INJECTING_MAGIC_ALTAR_CORE_1.get()))
                    .title(Component.translatable(INJECTING_MAGIC_CORE_GROUP))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(DecorativeBlocks.LV1_INJECTING_MAGIC_ALTAR_CORE_1.get());
                        pOutput.accept(DecorativeBlocks.ACHERON_CRYSTAL.get());
                        pOutput.accept(DecorativeBlocks.LV1_INJECTING_MAGIC_ALTAR_CORE_GREEN_BODY.get());
                        pOutput.accept(Lv1InjectingMagicAltarCore.LV1_INJECTING_MAGIC_ALTAR_CORE.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> LYCORIS_TOOLS = ITEM_GROUPS.register("lycoris_tools",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(LycorisTools.RED_CRYSTAL_PICKAXE.get()))
                    .title(Component.translatable(TOOL_GROUP))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(LycorisTools.RED_CRYSTAL_PICKAXE.get());
                        pOutput.accept(LycorisTools.RED_CRYSTAL_SHOVE.get());


                    }).build());

    public static final RegistryObject<CreativeModeTab> LYCORIS_ITEMS = ITEM_GROUPS.register("lycoris_items",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(MetalDetectorItem.METAL_DETECTOR.get()))
                    .title(Component.translatable(ITEM_GROUP))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(MetalDetectorItem.METAL_DETECTOR.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> RAW_MATERIAL = ITEM_GROUPS.register("raw_material",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(LycorisItems.LV1_INJECTING_MAGIC_CORE_GREEN_BODY_MOLD.get()))
                    .title(Component.translatable(ITEM_GROUP))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(LycorisItems.LV1_INJECTING_MAGIC_CORE_GREEN_BODY_MOLD.get());
                    }).build());
    public static void register(IEventBus eventBus)
    {
        ITEM_GROUPS.register(eventBus);
    }
}
