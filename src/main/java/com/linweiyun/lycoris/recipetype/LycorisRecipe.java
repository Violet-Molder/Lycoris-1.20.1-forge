package com.linweiyun.lycoris.recipetype;

import com.linweiyun.lycoris.LycorisMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LycorisRecipe {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LycorisMod.MOD_ID);


    public static final RegistryObject<RecipeSerializer<Lv1InjectingMagicAltarRecipe>> LV1_INJECTING_MAGIC_ALTAR_RECIPE_TYPE =
            SERIALIZERS.register("lv1_injecting_magic_altar", () -> Lv1InjectingMagicAltarRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
