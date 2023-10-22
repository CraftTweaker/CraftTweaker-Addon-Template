package com.blamejared.genericaddon.recipes;

import com.blamejared.genericaddon.SomeGenericAddon;
import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.*;

/**
 * This recipe type is only added here to demonstrate
 * how CrT implementation will work with RecipeManagers
 *
 * It doesn't do anything in the game other than adding recipes that will never be used.
 *
 * See for the CrT side of things:
 * {@link com.blamejared.genericaddon.compat.crafttweaker.managers.InfusionRecipeManager}
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class InfusionRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final ItemStack result;
    private final Ingredient input;
    
    public InfusionRecipe(ResourceLocation id, Ingredient input, ItemStack result) {
        this.id = id;
        this.input = input;
        this.result = result;
    }
    
    @Override
    public NonNullList<Ingredient> getIngredients() {
        final NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(input);
        return ingredients;
    }
    
    @Override
    public boolean matches(Container inv, Level worldIn) {
        return input.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess){
        return getResultItem(registryAccess);
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result;
    }
    
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height > 0;
    }
    
    @Override
    public ResourceLocation getId() {
        return id;
    }
    
    @Override
    public RecipeSerializer<?> getSerializer() {
        return SomeGenericAddon.INFUSION_SERIALIZER.get();
    }
    
    @Override
    public RecipeType<?> getType() {
        return SomeGenericAddon.TYPE_INFUSION.get();
    }

    public Ingredient getInput(){
        //Equivalent to getIngredients().get(0)
        return input;
    }

    @ParametersAreNonnullByDefault
    public static final class InfusionRecipeSerializer implements RecipeSerializer<InfusionRecipe> {

        @Override
        public InfusionRecipe fromJson(ResourceLocation recipeId, JsonObject json){
            final Ingredient input = Ingredient.fromJson(json.get("input"));
            final ItemStack result = CraftingHelper.getItemStack(json.getAsJsonObject("result"), false);
            return new InfusionRecipe(recipeId, input, result);
        }

        @Override
        public @Nullable InfusionRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer){
            final Ingredient input = Ingredient.fromNetwork(buffer);
            final ItemStack result = buffer.readItem();
            return new InfusionRecipe(recipeId, input, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, InfusionRecipe recipe){
            CraftingHelper.write(buffer, recipe.getIngredients().get(0));
            buffer.writeItem(recipe.result);
        }
    }
}
