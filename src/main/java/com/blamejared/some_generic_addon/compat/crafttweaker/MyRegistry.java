package com.blamejared.some_generic_addon.compat.crafttweaker;

import com.blamejared.crafttweaker.api.item.*;
import com.blamejared.crafttweaker_annotations.annotations.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.*;
import org.openzen.zencode.java.*;

/**
 * This class shows how to use the {@link AsIAction} annotation.
 *
 * The AsIAction annotation is used to create an {@link com.blamejared.crafttweaker.api.actions.IAction}.
 * That IAction is created based on a given static method, which is annotated with AsIAction
 *
 * That method can have Vanilla Types that will be wrapped automatically.
 * Careful, only Types that have a Wrapper (that is, a class annotated with {@link ZenWrapper}
 * are allowed here!
 *
 * So far, even Methods with IItemStack would fail.
 *
 * The scope of this Annotation is to allow you to annotate your already existing static registry methods
 * As this is annotation based, it will not break your API even if CraftTweaker is not loaded!
 *
 * I recommend doing a proper implementation class though.
 */
@ZenCodeType.Name("mods.my.super.Registry") //We need this here to show where it should be generated.
public class MyRegistry {
    
    /**
     * Since the asIAction class will create a fully fledged ZenClass, it will also automatically create documentation entries.
     * So there will be a md file with this beautiful entry.
     *
     * So you can also use docParams to provide examples.
     * Though, that may also clutter your Api, so maybe it's an indicator to switch to an actual class?
     *
     * @param output The recipe result
     * @docParam output <item:minecraft:bedrock>
     * @param input The input of the recipe
     * @docParam input <item:minecraft:diamond>
     */
    //Generally you only need the logFormat parameter
    @AsIAction(logFormat = "Adding super recipe %2$s -> %1$s")
    public static void addASuperRecipe(ItemStack output, Ingredient input) {
        //Your code to add to your super recipe.
        System.out.printf("%2$s -> %1$s%n", output, input);
    }
    
    /**
     * As you can see, you can use Strings.
     * Generally you can use Strings, primitives or even lists/arrays of them
     *
     * Though I have to admit Lists/Arrays haven't been tested that well, so be careful with the generated code.
     * @param name The recipe name to remove
     * @docParam name "some_generic_addon:bad_recipe"
     */
    @AsIAction(
            logFormat = "Removing recipe with name '%s'", //How the log entry in the crafttweaker.log file will look like
            methodName = "", //The method name inside ZC. Defaults to the actual method name
            repeatable = true //Can this action can be applied in /reload as well or only during initial server start
    )
    public static void removeABadRecipe(String name) {
        //Your code to remove your bad recipe
        System.out.printf("Removing recipe with name '%s'%n", name);
    }
}
