package com.blamejared.some_generic_addon.recipes;

import com.blamejared.some_generic_addon.*;
import mcp.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import javax.annotation.*;

/**
 * This recipe type is only added here to demonstrate
 * how CrT implementation will work with RecipeManagers
 *
 * It doesn't do anything in the game other than adding recipes that will never be used.
 *
 * See for the CrT side of things:
 * {@link com.blamejared.some_generic_addon.compat.crafttweaker.managers.InfusionRecipeManager}
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class InfusionRecipe implements IRecipe<IInventory> {
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
    public boolean matches(IInventory inv, World worldIn) {
        return input.test(inv.getStackInSlot(0));
    }
    
    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return getRecipeOutput();
    }
    
    @Override
    public boolean canFit(int width, int height) {
        return width * height > 0;
    }
    
    @Override
    public ItemStack getRecipeOutput() {
        return result.copy();
    }
    
    @Override
    public ResourceLocation getId() {
        return id;
    }
    
    @Override
    public InfusionRecipeSerializer getSerializer() {
        return SomeGenericAddon.INFUSION_SERIALIZER;
    }
    
    @Override
    public IRecipeType<?> getType() {
        return SomeGenericAddon.TYPE_INFUSION;
    }
    
}
