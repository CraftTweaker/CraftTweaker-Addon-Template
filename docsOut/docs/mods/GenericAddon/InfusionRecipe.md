# InfusionRecipe

A class exposing your Recipe to the Zen Code engine. 

 This is useful to scripters as having the explicit type allows them to access specific members of the class, rather
 than going through Recipe methods. 

 You're expected to also document the [IRecipeComponent](/vanilla/api/recipe/IRecipeComponent)&lt;T&gt;s your
 recipe can break into. For more information, see the appropriate handler class:
 **invalid**

 ## Recipe Components

 - `&lt;recipecomponent:crafttweaker:input/ingredients&gt;`, of type [IIngredient](/vanilla/api/ingredient/IIngredient)
 - `&lt;recipecomponent:crafttweaker:output/items&gt;`, of type [IItemStack](/vanilla/api/item/IItemStack)

## Importing the class

It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import at the very top of the file.
```zenscript
import mods.genericaddon.InfusionRecipe;
```


## Implemented Interfaces
InfusionRecipe implements the following interfaces. That means all methods defined in these interfaces are also available in InfusionRecipe

- [Recipe](/vanilla/api/recipe/type/Recipe)&lt;[Container](/vanilla/api/world/Container)&gt;

