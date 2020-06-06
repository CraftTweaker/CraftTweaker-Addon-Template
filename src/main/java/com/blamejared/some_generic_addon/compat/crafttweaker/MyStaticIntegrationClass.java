package com.blamejared.some_generic_addon.compat.crafttweaker;

import com.blamejared.crafttweaker.api.*;
import com.blamejared.crafttweaker.api.annotations.*;
import com.blamejared.crafttweaker.api.item.*;
import com.blamejared.crafttweaker_annotations.annotations.*;
import org.openzen.zencode.java.*;

/**
 * A class with static members that allow us to do stuff!
 */
@ZenRegister
@ZenCodeType.Name("mods.some_generic_addon.MyStaticIntegrationClass")
@Document("mods/some_generic_addon/MyStaticIntegrationClass")
public class MyStaticIntegrationClass {
    
    /**
     * Prints the given ingredient to the console, yay!
     *
     * @param ingredient The ingredient to be printed
     * @docParam ingredient <item:minecraft:iron_ingot>
     */
    @ZenCodeType.Method
    public static void doPrint(IIngredient ingredient) {
        CraftTweakerAPI.logInfo("Printing '%s'", ingredient.getCommandString());
    }
}
