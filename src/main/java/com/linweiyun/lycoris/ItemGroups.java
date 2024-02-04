package com.linweiyun.lycoris;

import com.linweiyun.lycoris.block.OreBlock;
import com.linweiyun.lycoris.items.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemGroups {
    public static final String ORE_GROUP = "creativetab.lycoris.ore_tab";
    public static final String INJECTING_MAGIC_CORE_GROUP = "creativetab.lycoris.injecting_magic_core_tab";
    public static final DeferredRegister<CreativeModeTab> ITEM_GROUPS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LycorisMod.MOD_ID);

    //矿物
    public static final RegistryObject<CreativeModeTab> LYCORIS_ORE = ITEM_GROUPS.register("lycoris_ore",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(Items.RED_CRYSTAL_FRAGMENT.get()))
                    .title(Component.translatable(ORE_GROUP))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(Items.YELLOW_CRYSTAL_FRAGMENT.get());
                        pOutput.accept(Items.GREEN_CRYSTAL_FRAGMENT.get());
                        pOutput.accept(Items.BLUE_CRYSTAL_FRAGMENT.get());
                        pOutput.accept(Items.RED_CRYSTAL_FRAGMENT.get());

                        pOutput.accept(OreBlock.NATURAL_YELLOW_CRYSTAL_ORE.get());
                        pOutput.accept(OreBlock.NATURAL_GREEN_CRYSTAL_ORE.get());
                        pOutput.accept(OreBlock.NATURAL_BLUE_CRYSTAL_ORE.get());
                        pOutput.accept(OreBlock.NATURAL_RED_CRYSTAL_ORE.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> LYCORIS_FUNCTIONAL_BLOCKS = ITEM_GROUPS.register("lycoris_function_blocks",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(Items.YELLOW_CRYSTAL_FRAGMENT.get()))
                    .title(Component.translatable(INJECTING_MAGIC_CORE_GROUP))
                    .displayItems((pParameters, pOutput) ->{

                    }).build());
    public static void register(IEventBus eventBus)
    {
        ITEM_GROUPS.register(eventBus);
    }
}
