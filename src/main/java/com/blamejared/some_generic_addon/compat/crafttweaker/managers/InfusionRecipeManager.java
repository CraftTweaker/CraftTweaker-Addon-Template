package com.blamejared.some_generic_addon.compat.crafttweaker.managers;

import com.blamejared.crafttweaker.api.*;
import com.blamejared.crafttweaker.api.annotations.*;
import com.blamejared.crafttweaker.api.item.*;
import com.blamejared.crafttweaker.api.managers.*;
import com.blamejared.crafttweaker.impl.actions.recipes.*;
import com.blamejared.crafttweaker_annotations.annotations.*;
import com.blamejared.some_generic_addon.*;
import com.blamejared.some_generic_addon.recipes.*;
import mcp.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.*;
import org.openzen.zencode.java.*;

/**
 * By default, CraftTweaker creates a {@link com.blamejared.crafttweaker.impl.managers.RecipeManagerWrapper}
 * object per registered {@link IRecipeType}
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
 * In this case, {@code <recipetype:some_generic_addon:infusion>}
 *
 *
 * @docParam this <recipetype:some_generic_addon:infusion>
 */
@ZenRegister
@Document("mods/some_generic_addon/InfusionRecipeManager")
@ZenCodeType.Name("mods.some_generic_addon.InfusionRecipeManager")
public class InfusionRecipeManager implements IRecipeManager {
    
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
    
    
    @Override
    public IRecipeType<InfusionRecipe> getRecipeType() {
        return SomeGenericAddon.TYPE_INFUSION;
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
        //Generally, we recommend using your mod's namespace for any added recipe.
        //This is so that in modpacks faulty recipes won't be reported to the mod authors.
        final ResourceLocation id = new ResourceLocation(SomeGenericAddon.MOD_ID, name);
        
        //Most CrT types have a "getInternal" method to get a CrT equivalent
        final ItemStack resultItemStack = output.getInternal();
        //For IIngredient, the method is named a little differently, but nonetheless present.
        final Ingredient ingredient = input.asVanillaIngredient();
        final InfusionRecipe recipe = new InfusionRecipe(id, ingredient, resultItemStack);
        
        //It's a best practice to always wrap your changes in an IAction.
        //This is done for logging purposes as well as well as allowing some actions to be reverted or only executed once
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, null));
    }
}
