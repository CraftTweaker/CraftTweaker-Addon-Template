# InfusionRecipeManager

By default, CraftTweaker creates a com.blamejared.crafttweaker.impl.managers.RecipeManagerWrapper
 object per registered net.minecraft.item.crafting.IRecipeType&lt;T&gt;
 unless a mod adds a custom implementation of [crafttweaker.api.registries.IRecipeManager](/vanilla/api/managers/IRecipeManager).
 <p>
 In that case, the implementation will be used.
 That is also one of the reasons why you need to implement [mods.some_generic_addon.InfusionRecipeManager#getRecipeType()](/mods/some_generic_addon/InfusionRecipeManager/#getrecipetype).
 <p>
 The default methods of IRecipeManger already allow you to remove recipes.
 They also have a primitive way of getting recipes based on their output.
 <p>
 If you were to completely remove this class, users would still be able to remove recipes from this type.
 We recommend adding a custom manager nonetheless, since you probably also want to _add_ types.
 <p>
 If you don't need any special logic for recipe removal, you could leave them be and only implement recipe additions.
 <p>
 In Script, users will be able to call a manager using {@code <recipetype:YOUR_RECIPE_TYPE_NAME>}
 In this case, {@code <recipetype:some_generic_addon:infusion>}

This class was added by a mod with mod-id `some_generic_addon`. So you need to have this mod installed if you want to use this feature.

## Importing the class
It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import.  
```zenscript
mods.some_generic_addon.InfusionRecipeManager
```

## Implemented Interfaces
InfusionRecipeManager implements the following interfaces. That means any method available to them can also be used on this class.  
- [crafttweaker.api.brackets.CommandStringDisplayable](/vanilla/api/brackets/CommandStringDisplayable)
- [crafttweaker.api.registries.IRecipeManager](/vanilla/api/managers/IRecipeManager)

## Methods
### addJSONRecipe

```zenscript
<recipetype:some_generic_addon:infusion>.addJSONRecipe(name as String, data as crafttweaker.api.data.IData);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| name | String | No description provided |
| data | [crafttweaker.api.data.IData](/vanilla/api/data/IData) | No description provided |


### addRecipe

Any added methods should be instance methods,
 as the recipetype BEP already returns an instance of the manager
 <p>
 Like for every other method, this documentation will be exported by the Annotation processor
 if the AP was added to the compiler's classpath.

```zenscript
<recipetype:some_generic_addon:infusion>.addRecipe(name as String, input as crafttweaker.api.item.IIngredient, output as crafttweaker.api.item.IItemStack);
<recipetype:some_generic_addon:infusion>.addRecipe("my_recipe", <item:minecraft:diamond_pickaxe>, <item:minecraft:diamond> * 16);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| name | String | The recipe name, only the resource path (without namespace) |
| input | [crafttweaker.api.item.IIngredient](/vanilla/api/items/IIngredient) | The recipe's input |
| output | [crafttweaker.api.item.IItemStack](/vanilla/api/items/IItemStack) | The recipe result |


### getRecipeByName

Return type: [crafttweaker.api.recipes.WrapperRecipe](/crafttweaker/api/recipes/WrapperRecipe)

```zenscript
<recipetype:some_generic_addon:infusion>.getRecipeByName(name as String);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| name | String | No description provided |


### getRecipesByOutput

Return type: List&lt;[crafttweaker.api.recipes.WrapperRecipe](/crafttweaker/api/recipes/WrapperRecipe)&gt;

```zenscript
<recipetype:some_generic_addon:infusion>.getRecipesByOutput(output as crafttweaker.api.item.IIngredient);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| output | [crafttweaker.api.item.IIngredient](/vanilla/api/items/IIngredient) | No description provided |


### removeAll

```zenscript
<recipetype:some_generic_addon:infusion>.removeAll();
```

### removeByModid

```zenscript
<recipetype:some_generic_addon:infusion>.removeByModid(modid as String);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| modid | String | No description provided |



```zenscript
<recipetype:some_generic_addon:infusion>.removeByModid(modid as String, exclude as crafttweaker.api.recipe.RecipeFilter);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| modid | String | No description provided |
| exclude | [crafttweaker.api.recipe.RecipeFilter](/vanilla/api/recipe/RecipeFilter) | No description provided |


### removeByName

```zenscript
<recipetype:some_generic_addon:infusion>.removeByName(name as String);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| name | String | No description provided |


### removeByRegex

```zenscript
<recipetype:some_generic_addon:infusion>.removeByRegex(regex as String);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| regex | String | No description provided |


### removeRecipe

```zenscript
<recipetype:some_generic_addon:infusion>.removeRecipe(output as crafttweaker.api.item.IItemStack);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| output | [crafttweaker.api.item.IItemStack](/vanilla/api/items/IItemStack) | No description provided |



## Properties

| Name | Type | Has Getter | Has Setter |
|------|------|------------|------------|
| commandString | String | true | false |

