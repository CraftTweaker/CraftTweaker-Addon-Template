package com.blamejared.genericaddon.compat.crafttweaker.natives;


import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.genericaddon.recipes.InfusionRecipe;

/**
 * <p>A class exposing your Recipe to the Zen Code engine. </p>
 *
 * <p>This is useful to scripters as having the explicit type allows them to access specific members of the class, rather
 * than going through Recipe methods. </p>
 *
 * <p>You're expected to also document the {@link com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent}s your
 * recipe can break into. For more information, see the appropriate handler class:
 * {@link com.blamejared.genericaddon.compat.crafttweaker.handlers.InfusionRecipeHandler}
 *
 * ## Recipe Components
 *
 * - `&lt;recipecomponent:crafttweaker:input/ingredients&gt;`, of type {@link com.blamejared.crafttweaker.api.ingredient.IIngredient}
 * - `&lt;recipecomponent:crafttweaker:output/items&gt;`, of type {@link com.blamejared.crafttweaker.api.item.IItemStack}
 *
 *
 */
@Document("mods/GenericAddon/InfusionRecipe")
@NativeTypeRegistration(value = InfusionRecipe.class, zenCodeName = "mods.genericaddon.InfusionRecipe")
public class ExposeInfusionRecipe {
}
