# InfusionRecipeManager

By default, CraftTweaker creates a [RecipeManagerWrapper](/vanilla/api/recipe/manager/RecipeManagerWrapper)
 object per registered **invalid**
 unless a mod adds a custom implementation of [IRecipeManager](/vanilla/api/recipe/manager/IRecipeManager)&lt;T&gt;.
 
 In that case, the implementation will be used.
 That is also one of the reasons why you need to implement [this](.)#getRecipeType().
 
 The default methods of IRecipeManger already allow you to remove recipes.
 They also have a primitive way of getting recipes based on their output.
 
 If you were to completely remove this class, users would still be able to remove recipes from this type.
 We recommend adding a custom manager nonetheless, since you probably also want to _add_ types.
 
 If you don't need any special logic for recipe removal, you could leave them be and only implement recipe additions.
 
 In Script, users will be able to call a manager using `<recipetype:YOUR_RECIPE_TYPE_NAME>`
 In this case, `<recipetype:genericaddon:infusion>`

## Importing the class

It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import at the very top of the file.
```zenscript
import mods.genericaddon.InfusionRecipeManager;
```


## Implemented Interfaces
InfusionRecipeManager implements the following interfaces. That means all methods defined in these interfaces are also available in InfusionRecipeManager

- [IRecipeManager](/vanilla/api/recipe/manager/IRecipeManager)&lt;[InfusionRecipe](/mods/GenericAddon/InfusionRecipe)&gt;

## Methods

:::group{name=addJsonRecipe}

```zenscript
InfusionRecipeManager.addJsonRecipe(name as string, mapData as MapData)
```

| Parameter |                 Type                 |
|-----------|--------------------------------------|
| name      | string                               |
| mapData   | [MapData](/vanilla/api/data/MapData) |


:::

:::group{name=addRecipe}

Any added methods should be instance methods,
 as the recipetype BEP already returns an instance of the manager
 
 Like for every other method, this documentation will be exported by the Annotation processor
 if the AP was added to the compiler's classpath.

```zenscript
// InfusionRecipeManager.addRecipe(name as string, output as IItemStack, input as IIngredient)

<recipetype:genericaddon:infusion>.addRecipe("my_recipe", <item:minecraft:diamond> * 16, <item:minecraft:diamond_pickaxe>);
```

| Parameter |                        Type                        |                         Description                         |
|-----------|----------------------------------------------------|-------------------------------------------------------------|
| name      | string                                             | The recipe name, only the resource path (without namespace) |
| output    | [IItemStack](/vanilla/api/item/IItemStack)         | The recipe result                                           |
| input     | [IIngredient](/vanilla/api/ingredient/IIngredient) | The recipe's input                                          |


:::

:::group{name=getAllRecipes}

Return Type: stdlib.List&lt;T&gt;

```zenscript
// InfusionRecipeManager.getAllRecipes() as stdlib.List<T>

<recipetype:genericaddon:infusion>.getAllRecipes();
```

:::

:::group{name=getRecipeByName}

Return Type: T

```zenscript
InfusionRecipeManager.getRecipeByName(name as string) as T
```

| Parameter |  Type  |
|-----------|--------|
| name      | string |


:::

:::group{name=getRecipeMap}

Return Type: T[[ResourceLocation](/vanilla/api/resource/ResourceLocation)]

```zenscript
// InfusionRecipeManager.getRecipeMap() as T[ResourceLocation]

<recipetype:genericaddon:infusion>.getRecipeMap();
```

:::

:::group{name=getRecipesByOutput}

Return Type: stdlib.List&lt;T&gt;

```zenscript
InfusionRecipeManager.getRecipesByOutput(output as IIngredient) as stdlib.List<T>
```

| Parameter |                        Type                        |
|-----------|----------------------------------------------------|
| output    | [IIngredient](/vanilla/api/ingredient/IIngredient) |


:::

:::group{name=remove}

```zenscript
InfusionRecipeManager.remove(output as IIngredient)
```

| Parameter |                        Type                        |
|-----------|----------------------------------------------------|
| output    | [IIngredient](/vanilla/api/ingredient/IIngredient) |


:::

:::group{name=removeAll}

```zenscript
// InfusionRecipeManager.removeAll()

<recipetype:genericaddon:infusion>.removeAll();
```

:::

:::group{name=removeByInput}

```zenscript
InfusionRecipeManager.removeByInput(input as IItemStack)
```

| Parameter |                    Type                    |
|-----------|--------------------------------------------|
| input     | [IItemStack](/vanilla/api/item/IItemStack) |


:::

:::group{name=removeByModid}

```zenscript
InfusionRecipeManager.removeByModid(modid as string, exclude as Predicate<string>)
```

| Parameter |          Type           | Optional |           Default Value           |
|-----------|-------------------------|----------|-----------------------------------|
| modid     | string                  | false    |                                   |
| exclude   | Predicate&lt;string&gt; | true     | (name as string) as bool => false |


:::

:::group{name=removeByName}

```zenscript
InfusionRecipeManager.removeByName(names as string[])
```

| Parameter |   Type   |
|-----------|----------|
| names     | string[] |


:::

:::group{name=removeByRegex}

```zenscript
InfusionRecipeManager.removeByRegex(regex as string, exclude as Predicate<string>)
```

| Parameter |          Type           | Optional |           Default Value           |
|-----------|-------------------------|----------|-----------------------------------|
| regex     | string                  | false    |                                   |
| exclude   | Predicate&lt;string&gt; | true     | (name as string) as bool => false |


:::


## Properties

|    Name    |                             Type                              | Has Getter | Has Setter |
|------------|---------------------------------------------------------------|------------|------------|
| allRecipes | stdlib.List&lt;T&gt;                                          | true       | false      |
| recipeMap  | T[[ResourceLocation](/vanilla/api/resource/ResourceLocation)] | true       | false      |

