package com.blamejared.some_generic_addon;

import com.blamejared.some_generic_addon.recipes.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.*;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.registries.*;

@Mod(SomeGenericAddon.MOD_ID)
public class SomeGenericAddon {
    public static final String MOD_ID = "some_generic_addon";
    public static final IRecipeType<InfusionRecipe> TYPE_INFUSION;
    public static final InfusionRecipeSerializer INFUSION_SERIALIZER;
    static {
        final ResourceLocation recipeNameInfusion = new ResourceLocation(MOD_ID, "infusion");
        INFUSION_SERIALIZER = (InfusionRecipeSerializer) new InfusionRecipeSerializer().setRegistryName(recipeNameInfusion);
        TYPE_INFUSION = IRecipeType.register(recipeNameInfusion.toString());
    }
    
    public SomeGenericAddon() {
        ;
        ForgeRegistries.RECIPE_SERIALIZERS.register(INFUSION_SERIALIZER);
    }
}
