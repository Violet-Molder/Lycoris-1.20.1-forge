package com.linweiyun.lycoris.recipetype;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class Lv2InjectingMagicAltarRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;//配方ID
    private final ItemStack output;//输出的物品
    private final NonNullList<Ingredient> recipeItems;//用来合成的物品
    private final Ingredient catalyst;//催化剂

    public Lv2InjectingMagicAltarRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, Ingredient catalyst) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.catalyst = catalyst;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
