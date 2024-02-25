package com.linweiyun.lycoris.util;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LycorisTags {
    public static class Items {


        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(LycorisMod.MOD_ID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> NEEDS_RED_CRYSTAL_TOOL = tag("needs_red_crystal_tool");
        public static final TagKey<Block> METAL_DETECTOR_NORMAL = tag("metal_detector_normal");
        public static final TagKey<Block> METAL_DETECTOR_RARE = tag("metal_detector_rare");
        public static final TagKey<Block> METAL_DETECTOR_PRECIOUS = tag("metal_detector_precious");
        public static final TagKey<Block> METAL_DETECTOR_LYCORIS = tag("metal_detector_lycoris");



        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(LycorisMod.MOD_ID, name));
        }
    }
}
