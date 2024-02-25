package com.linweiyun.lycoris.datagen;

import com.linweiyun.lycoris.LycorisMod;
import com.linweiyun.lycoris.items.LycorisItems;
import com.linweiyun.lycoris.items.LycorisTools;
import com.linweiyun.lycoris.items.custom.MetalDetectorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class LycorisItemModelProvider extends ItemModelProvider {
    public LycorisItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, LycorisMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItemModel(LycorisItems.RED_CRYSTAL_FRAGMENT);
        simpleItemModel(LycorisItems.BLUE_CRYSTAL_FRAGMENT);
        simpleItemModel(LycorisItems.GREEN_CRYSTAL_FRAGMENT);
        simpleItemModel(LycorisItems.YELLOW_CRYSTAL_FRAGMENT);
        simpleItemModel(LycorisItems.LV1_INJECTING_MAGIC_CORE_GREEN_BODY_MOLD);
        handheldItemModel(LycorisTools.RED_CRYSTAL_SHOVE);
        handheldItemModel(LycorisTools.RED_CRYSTAL_PICKAXE);
        handheldItemModel(MetalDetectorItem.METAL_DETECTOR);
    }

    private ItemModelBuilder simpleItemModel(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LycorisMod.MOD_ID, "item/" + item.getId().getPath()));
    }



    private ItemModelBuilder handheldItemModel(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(LycorisMod.MOD_ID, "item/" + item.getId().getPath()));
    }


}
