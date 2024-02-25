package com.linweiyun.lycoris.init;

import com.linweiyun.lycoris.block.custom.AcheronCrystalCluster;
import com.linweiyun.lycoris.block.DecorativeBlocks;
import com.linweiyun.lycoris.block.OreBlock;
import com.linweiyun.lycoris.block.custom.Lv1InjectingMagicAltarCore;
import com.linweiyun.lycoris.blockentity.Lv1InjectingMagicCoreAltarEntity;
import com.linweiyun.lycoris.items.LycorisItems;
import com.linweiyun.lycoris.items.LycorisTools;
import com.linweiyun.lycoris.items.custom.MetalDetectorItem;
import com.linweiyun.lycoris.recipetype.LycorisRecipe;
import com.linweiyun.lycoris.screen.LycorisMenuType;
import com.linweiyun.lycoris.screen.Lv1InjectingMagicAltarStationScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;

public class LycorisInit {
    public static void registerLycorisItems(IEventBus eventBus) {
        LycorisItems.register(eventBus);
        LycorisTools.register(eventBus);
        MetalDetectorItem.register(eventBus);
    }
    public static void registerLycorisBlocks(IEventBus eventBus) {
        OreBlock.register(eventBus);
        DecorativeBlocks.register(eventBus);
        AcheronCrystalCluster.register(eventBus);
        Lv1InjectingMagicAltarCore.register(eventBus);
    }

    public static void registerLycorisBlockEntities(IEventBus eventBus) {
        Lv1InjectingMagicCoreAltarEntity.register(eventBus);

    }

    public static void registerLycorisRecipes(IEventBus eventBus) {
        LycorisRecipe.register(eventBus);
    }

    public static void registerLycorisMenuType(IEventBus eventBus) {
        LycorisMenuType.register(eventBus);
    }
    public static void registerLycorisScreens() {
        MenuScreens.register(LycorisMenuType.LV1_INJECTING_MAGIC_ALTAR_MENU_TYPE.get(), Lv1InjectingMagicAltarStationScreen::new);
    }
}
