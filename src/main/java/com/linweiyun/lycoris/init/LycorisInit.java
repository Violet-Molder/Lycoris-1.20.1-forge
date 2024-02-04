package com.linweiyun.lycoris.init;

import com.linweiyun.lycoris.block.OreBlock;
import com.linweiyun.lycoris.items.Items;
import net.minecraftforge.eventbus.api.IEventBus;

public class LycorisInit {
    public static void registerLycorisItems(IEventBus eventBus) {
        Items.register(eventBus);
    }
    public static void registerLycorisBlocks(IEventBus eventBus) {
        OreBlock.register(eventBus);

    }

    public static void registerLycorisBlockEntities(IEventBus eventBus) {

    }
    public static void registerLycorisScreens(IEventBus eventBus) {

    }
}
