package com.blamejared.genericaddon.compat.crafttweaker;

import com.blamejared.crafttweaker.api.*;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.*;
import org.openzen.zencode.java.*;

/**
 * A class with static members that allow us to do stuff!
 */
@Document("mods/GenericAddon/MyStaticIntegrationClass")
@ZenCodeType.Name("mods.genericaddon.MyStaticIntegrationClass")
@ZenRegister
public class MyStaticIntegrationClass {
    
    /**
     * Prints the given ingredient to the console, yay!
     *
     * @param ingredient The ingredient to be printed
     * @docParam ingredient <item:minecraft:iron_ingot>
     */
    @ZenCodeType.Method
    public static void doPrint(IIngredient ingredient) {
        //While this has some usage, if what you're doing should be undoable, do refer to the IActionExample in InfusionRecipeManager.
        //If you need an action for non recipe manipulation, refer to IUndoableAction
        //see ActionSetCompostable for an example.
        CraftTweakerAPI.getLogger("GenericAddon").info("Printing '%s'", ingredient.getCommandString());
    }
}
