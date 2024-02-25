package com.linweiyun.lycoris.recipetype;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.linweiyun.lycoris.LycorisMod;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

// recipe类仅描述了配方数据和执行逻辑，
// 通过container子类提供数据
// 任何输入的Container都应该是不可变的，任何的操作都应该通过copy输入副本。

public class Lv1InjectingMagicAltarRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final Ingredient catalyst;
    public Lv1InjectingMagicAltarRecipe(ResourceLocation id, ItemStack output,
                                        NonNullList<Ingredient> recipeItems, Ingredient catalyst){
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.catalyst = catalyst;
    }
    // 为了能够通过管理器获得配方，match必须返回true
    // 此方法用于管理容器是否输入有效。
    // 通过代用test检测
    // 检查容器内的物品和配方是否匹配。
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        StackedContents stackedContents = new StackedContents();
        int i = 0;
        for (int j = 1; j < pContainer.getContainerSize() -1; ++j){
            ItemStack itemStack = pContainer.getItem(j);
            if (!itemStack.isEmpty())
            {
                ++i;
                stackedContents.accountStack(itemStack, 1);
            }
        }
        return i == this.recipeItems.size() && stackedContents.canCraft(this, (IntList) null)
                && this.catalyst.test(pContainer.getItem(0));
    }
    // 获得合成表所需要的item stacks
    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    // 构建配方
    // 返回了合成表的结果output
    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess registryAccess) {
        return output;
    }
    // 这个方法用于判断合成表是否可以在指定的dimensions合成。
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }
    // 获得合成表物品的copy()
    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    //
    @Override
    public ResourceLocation getId() {
        return id;
    }
    // 返回Serializer 必须返回
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    // 返回type
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    // 注册新的合成的type
    public static class Type implements RecipeType<Lv1InjectingMagicAltarRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        // 标识了合成的类型，和json文件中的type一致
        public static final String ID = "lv1_injecting_magic_altar";
    }

    // 负责解码JSON并通过网络通信
    // 需要注册
    public static class Serializer implements RecipeSerializer<Lv1InjectingMagicAltarRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final  ResourceLocation ID =
                new ResourceLocation(LycorisMod.MOD_ID,"lv1_injecting_magic_altar");
        // 将JSON解码为recipe子类型
        @Override
        public Lv1InjectingMagicAltarRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe,"output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe,"ingredients");

            //读取催化剂
            JsonElement catalyst = pSerializedRecipe.getAsJsonArray("catalyst");
            Ingredient   catalystIngredient = Ingredient.fromJson(catalyst);

            NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(),Ingredient.EMPTY);

            for(int i =0;i<inputs.size();i++){
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }
            return new Lv1InjectingMagicAltarRecipe(pRecipeId,output,inputs, catalystIngredient);
        }
        // 从服务器中发送的数据中解码recipe，配方标识符不需要解码。
        @Override
        public @Nullable Lv1InjectingMagicAltarRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(),Ingredient.EMPTY);
            for (int i=0;i < inputs.size();i++){
                inputs.set(i,Ingredient.fromNetwork(pBuffer));
            }
            ItemStack output = pBuffer.readItem();
            Ingredient catalyst = Ingredient.fromNetwork(pBuffer);
            return new Lv1InjectingMagicAltarRecipe(pRecipeId,output,inputs, catalyst);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, Lv1InjectingMagicAltarRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            for (Ingredient ing : pRecipe.getIngredients()){
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
            pRecipe.getCatalyst().toNetwork(pBuffer);
        }
    }
    private Ingredient getCatalyst(){
        return this.catalyst;
    }


}