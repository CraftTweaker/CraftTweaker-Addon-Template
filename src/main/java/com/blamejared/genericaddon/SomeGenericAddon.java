package com.blamejared.genericaddon;

import com.blamejared.genericaddon.recipes.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

@Mod(SomeGenericAddon.MOD_ID)
public class SomeGenericAddon {
    public static final String MOD_ID = "genericaddon";

    public static final DeferredRegister<RecipeType<?>> RECIPE_REGISTRY =
            DeferredRegister.create(Registries.RECIPE_TYPE, MOD_ID);

    public static final RegistryObject<RecipeType<InfusionRecipe>> TYPE_INFUSION =
            RECIPE_REGISTRY.register("infusion", () -> RecipeType.simple(new ResourceLocation(MOD_ID, "infusion")));

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTRY =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MOD_ID);

    public static final RegistryObject<RecipeSerializer<InfusionRecipe>> INFUSION_SERIALIZER =
            SERIALIZER_REGISTRY.register("infusion", InfusionRecipe.InfusionRecipeSerializer::new);
    
    public SomeGenericAddon() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        SERIALIZER_REGISTRY.register(modBus);
        RECIPE_REGISTRY.register(modBus);
    }
}
