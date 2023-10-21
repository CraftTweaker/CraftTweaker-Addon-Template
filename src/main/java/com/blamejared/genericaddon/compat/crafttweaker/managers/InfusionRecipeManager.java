package com.blamejared.genericaddon.compat.crafttweaker.managers;


import com.blamejared.crafttweaker.CraftTweakerCommon;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.genericaddon.SomeGenericAddon;
import com.blamejared.genericaddon.recipes.InfusionRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.*;

/**
 * By default, CraftTweaker creates a {@link com.blamejared.crafttweaker.api.recipe.manager.RecipeManagerWrapper}
 * object per registered {@link RecipeType}
 * unless a mod adds a custom implementation of {@link IRecipeManager}.
 * <p>
 * In that case, the implementation will be used.
 * That is also one of the reasons why you need to implement {@link #getRecipeType()}.
 * <p>
 * The default methods of IRecipeManger already allow you to remove recipes.
 * They also have a primitive way of getting recipes based on their output.
 * <p>
 * If you were to completely remove this class, users would still be able to remove recipes from this type.
 * We recommend adding a custom manager nonetheless, since you probably also want to _add_ types.
 * <p>
 * If you don't need any special logic for recipe removal, you could leave them be and only implement recipe additions.
 * <p>
 * In Script, users will be able to call a manager using {@code <recipetype:YOUR_RECIPE_TYPE_NAME>}
 * In this case, {@code <recipetype:genericaddon:infusion>}
 *
 *
 * @docParam this <recipetype:genericaddon:infusion>
 */

// All of the javadocs you see here are turned into markdown files for the website. In other words, the CraftTweaker
// Annotation Processor will pickup files annotated with @Document and generate files for them if it is properly
// configured. See the build.gradle for more details.

// @docParam Is required to have the AP add an example of what you're adding. This is applicable to both method
// parameters, as well as to recipetypes on the class level.

// A convention on the docs website requires mods to be capitalised and use PascalCase if possible:
// GenericAddon is okay as a path, Genericaddon and genericaddon are discouraged.

@Document("mods/GenericAddon/InfusionRecipeManager")
@ZenCodeType.Name("mods.genericaddon.InfusionRecipeManager") //This must always be prefixed with mods
@ZenRegister
public class InfusionRecipeManager implements IRecipeManager<InfusionRecipe> {
    
    /**
     * We need either a public static Field that hold a reference to this recipe manager
     * or a public no-arg constructor.
     * <p>
     * It will check for a field first.
     * I recommend making the field final as well.
     * CraftTweaker will cache the found instance internally, and therefore always use the same instance.
     * If the field was not final and you were to replace the instance with another one, CrT would not pick up upon it!
     */
    public static final InfusionRecipeManager INSTANCE = new InfusionRecipeManager();
    
    public InfusionRecipeManager() {
    }

    
    /**
     * Any added methods should be instance methods,
     * as the recipetype BEP already returns an instance of the manager
     * <p>
     * Like for every other method, this documentation will be exported by the Annotation processor
     * if the AP was added to the compiler's classpath.
     *
     * @param name   The recipe name, only the resource path (without namespace)
     * @param input  The recipe's input
     * @param output The recipe result
     * @docParam name "my_recipe"
     * @docParam input <item:minecraft:diamond_pickaxe>
     * @docParam output <item:minecraft:diamond> * 16
     */
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient input, IItemStack output) {
        //Your recipes should be using CraftTweaker's namespace, so that anyone reading this can pick up that this recipe
        //was added by a CraftTweaker scripts.
        final ResourceLocation id = CraftTweakerConstants.rl(fixRecipeName(name));
        
        //Most CrT types have a "getInternal" method to get a CrT equivalent
        final ItemStack resultItemStack = output.getInternal();
        //For IIngredient, the method is named a little differently, but nonetheless present.
        final Ingredient ingredient = input.asVanillaIngredient();
        final InfusionRecipe recipe = new InfusionRecipe(id, ingredient, resultItemStack);
        
        //It's a best practice to always wrap your changes in an IAction.
        //This is done for logging purposes as well as allowing some actions to be reverted or only executed once
        //This takes care of recipes being removed when the script adding it is removed or updated.
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe, null));
    }

    /**
     * Gets the recipe type for the registry to remove from.
     *
     * @return IRecipeType of this registry.
     */
    @Override
    public RecipeType<InfusionRecipe> getRecipeType(){
        return SomeGenericAddon.TYPE_INFUSION.get();
    }
}
