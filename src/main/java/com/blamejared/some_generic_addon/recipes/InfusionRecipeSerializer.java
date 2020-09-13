package com.blamejared.some_generic_addon.recipes;

import com.google.gson.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraftforge.common.crafting.*;
import net.minecraftforge.items.*;
import net.minecraftforge.registries.*;

import javax.annotation.*;

@ParametersAreNonnullByDefault
public final class InfusionRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InfusionRecipe> {
    
    @Override
    public InfusionRecipe read(ResourceLocation recipeId, JsonObject json) {
        final Ingredient input = Ingredient.deserialize(json.get("input"));
        final ItemStack result = CraftingHelper.getItemStack(json.getAsJsonObject("result"), false);
        return new InfusionRecipe(recipeId, input, result);
    }
    
    @Override
    public InfusionRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        final Ingredient input = Ingredient.read(buffer);
        final ItemStack result = buffer.readItemStack();
        return new InfusionRecipe(recipeId, input, result);
    }
    
    @Override
    public void write(PacketBuffer buffer, InfusionRecipe recipe) {
        CraftingHelper.write(buffer, recipe.getIngredients().get(0));
        buffer.writeItemStack(recipe.getRecipeOutput());
    }
    
    
}
