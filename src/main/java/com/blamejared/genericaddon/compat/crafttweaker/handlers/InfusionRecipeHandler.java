package com.blamejared.genericaddon.compat.crafttweaker.handlers;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.DecomposedRecipeBuilder;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.component.IRecipeComponent;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.IngredientUtil;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.blamejared.genericaddon.recipes.InfusionRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Optional;

/**
 * An {@link IRecipeHandler} is a class that adds compatibility between a Recipe and CraftTweaker features.
 *
 * As of now, these include: conflict checking, dumping recipes to log, and replacing.
 *
 */
@IRecipeHandler.For(InfusionRecipe.class)
public class InfusionRecipeHandler implements IRecipeHandler<InfusionRecipe> {


    /**
     * Creates a String representation of a valid {@code addRecipe} (or alternative) call for the given subclass of
     * {@link Recipe}.
     *
     * <p>Recipe dumps are triggered by the {@code /ct recipes} or {@code /ct recipes hand} commands.</p>
     *
     * <p>All newlines added to either the start or the end of the string will be automatically trimmed.</p>
     *
     * @param manager The recipe manager responsible for this kind of recipes.
     * @param recipe  The recipe that is currently being dumped.
     * @return A String representing a {@code addRecipe} (or similar) call.
     * @since 9.0.0
     */
    @Override
    public String dumpToCommandString(IRecipeManager<? super InfusionRecipe> manager, InfusionRecipe recipe){

        //Generates
        // <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_a", <item:minecraft:iron_axe>, <item:minecraft:bedrock>);
        // which is the exact method call used to add the recipe
        return String.format("%s.addRecipe(%s, %s, %s);",
                manager.getCommandString(),
                StringUtil.quoteAndEscape(recipe.getId()),
                ItemStackUtil.getCommandString(recipe.getResultItem(ServerLifecycleHooks.getCurrentServer().registryAccess())),
                IIngredient.fromIngredient(recipe.getInput()).getCommandString()
                );
    }

    /**
     * Checks if the two recipes conflict with each other.
     *
     * <p>In this case, a conflict is defined as the two recipes being made in the exact same way (e.g. with the same
     * shape and the same ingredients if the two recipes are shaped crafting table ones).</p>
     *
     * <p>Conflicts are also considered symmetrical in this implementation, which means that if {@code firstRecipe}
     * conflicts with {@code secondRecipe}, the opposite is also true.</p>
     *
     * @param manager      The recipe manager responsible for this kind of recipes.
     * @param firstRecipe  The recipe which should be checked for conflict.
     * @param secondRecipe The other recipe which {@code firstRecipe} should be checked against. The recipe may or may
     *                     not be of the same type of {@code firstRecipe}. See the API note section for more details.
     * @return Whether the {@code firstRecipe} conflicts with {@code secondRecipe} or not.
     * @apiNote The reason for which {@code secondRecipe} is specified as simply {@link Recipe} instead of as the
     * generic parameter {@code T} is to allow more flexibility in the conflict checking. In fact, this choice allows
     * for checking to also occur between different types of recipes (e.g. shaped vs shapeless crafting table recipes),
     * allowing for a broader range of checking. Nevertheless, the two recipes are <strong>ensured</strong> to be of the
     * same {@link RecipeType recipe type} (i.e.
     * {@code firstRecipe.getType() == secondRecipe.getType()}).
     * @since 9.0.0
     */
    @Override
    public <U extends Recipe<?>> boolean doesConflict(IRecipeManager<? super InfusionRecipe> manager, InfusionRecipe firstRecipe, U secondRecipe){
        //Since we know both recipes must be of InfusionRecipe, we check it like this.
        //In the event you have multiple types of recipes, check other CrT examples.
        return IngredientUtil.canConflict(firstRecipe.getInput(), secondRecipe.getIngredients().get(0));
    }

    /**
     * Decomposes a recipe from its complete form into an {@link IDecomposedRecipe}.
     *
     * <p>The decomposition needs to be complete, meaning that any meaningful part of the recipe should be present in
     * the returned decomposed recipe. The only exception is the name, as decomposed recipes only track
     * {@link IRecipeComponent}s, and names aren't one.</p>
     *
     * <p>It is allowed for an implementation to specify that the given recipe cannot be properly decomposed. Examples
     * of this occurrence might be recipes whose behavior is completely determined by code, such as map cloning in
     * vanilla. In this context, it is allowed to return {@link Optional#empty()}.</p>
     *
     * <p>It is mandatory for a recipe handler to produce a decomposed recipe that can then be converted back into its
     * complete form in {@link #recompose(IRecipeManager, ResourceLocation, IDecomposedRecipe)}. In other words, if
     * the return value of this method isn't empty, then
     * {@code recompose(manager, name, decompose(manager, recipe).get())} must not return an empty optional.</p>
     *
     * @param manager The recipe manager responsible for this kind of recipes.
     * @param recipe  The recipe that should be decomposed.
     * @return An {@link Optional} wrapping {@linkplain IDecomposedRecipe decomposed recipe}, or an empty one if need be
     * as specified above.
     * @since 10.0.0
     */
    @Override
    public Optional<IDecomposedRecipe> decompose(IRecipeManager<? super InfusionRecipe> manager, InfusionRecipe recipe){
        DecomposedRecipeBuilder decomposedRecipe = IDecomposedRecipe.builder();
        decomposedRecipe.with(BuiltinRecipeComponents.Input.INGREDIENTS, IIngredient.fromIngredient(recipe.getInput()));
        decomposedRecipe.with(BuiltinRecipeComponents.Output.ITEMS,
                IItemStack.of(recipe.getResultItem(ServerLifecycleHooks.getCurrentServer().registryAccess())));
        //Other components are available in BuiltinRecipeComponents.
        //Should you need to create a new one see ICraftTweakerPlugin#registerRecipeComponents
        //Don't forget to Document those in the Recipe Class javadocs.
        return Optional.of(decomposedRecipe.build());
    }

    /**
     * Reconstructs a complete recipe from its {@linkplain IDecomposedRecipe decomposed form}.
     *
     * <p>The recomposition should be as complete as possible, making sure that all
     * {@link IRecipeComponent}s that are necessary to properly rebuild
     * the recipe are present in the given {@link IDecomposedRecipe}. If the recipe presents unknown components, i.e.
     * components that this handler doesn't know how to convert, the handler is allowed to throw an exception as
     * detailed in the following paragraphs.</p>
     *
     * <p>It is allowed for an implementation to return {@link Optional#empty()} in case the recomposition fails for
     * any reason, or if no decomposed recipe can be used to rebuild a recipe in its complete form, e.g. for map cloning
     * in vanilla.</p>
     *
     * <p>It is mandatory for a recipe handler that knows how to decompose a recipe to also know how to recompose it. In
     * other words, if {@link #decompose(IRecipeManager, Recipe)} returns a non-empty {@code Optional}, then
     * {@code recompose(manager, name, decompose(manager, recipe).get())} must not return an empty optional nor throw
     * an exception.</p>
     *
     * @param manager The recipe manager responsible for this kind of recipes.
     * @param name    The name to give to the recomposed recipe once it has been built. It is mandatory that
     *                {@link T#getId()} and this parameter represent the same name.
     * @param recipe  The {@link IDecomposedRecipe} that should be recomposed back into a complete form.
     * @return An {@link Optional} wrapping the complete form of the recipe, or an empty one if need be as specified
     * above.
     * @throws IllegalArgumentException If any of the required recipe components are not present in the recipe, or they
     *                                  have invalid or meaningless values (e.g. an empty output). Optionally, if any
     *                                  unknown component is present in the decomposed recipe.
     * @since 10.0.0
     */
    @Override
    public Optional<InfusionRecipe> recompose(IRecipeManager<? super InfusionRecipe> manager, ResourceLocation name, IDecomposedRecipe recipe){
        final IIngredient input = recipe.getOrThrowSingle(BuiltinRecipeComponents.Input.INGREDIENTS);
        final IItemStack output = recipe.getOrThrowSingle(BuiltinRecipeComponents.Output.ITEMS);

        if (input.isEmpty()) throw new IllegalArgumentException("Invalid input: empty ingredient");
        if (output.isEmpty()) throw new IllegalArgumentException("Invalid output: empty stack");
        return Optional.of(new InfusionRecipe(name, input.asVanillaIngredient(), output.getInternal()));
    }
}
