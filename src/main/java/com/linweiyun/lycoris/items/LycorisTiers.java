package com.linweiyun.lycoris.items;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.util.LycorisTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class LycorisTiers {
    public static final Tier RED_CRYSTAL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 1500, 15, 4, 25,
                    LycorisTags.Blocks.NEEDS_RED_CRYSTAL_TOOL, ()-> Ingredient.of(LycorisItems.RED_CRYSTAL_FRAGMENT.get())),
            new ResourceLocation(LycorisMod.MOD_ID, "red_crystal"), List.of(Tiers.NETHERITE), List.of()
    );
}
