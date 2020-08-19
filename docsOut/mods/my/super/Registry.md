# Registry

This class was added by a mod with mod-id `some_generic_addon`. So you need to have this mod installed if you want to use this feature.

## Importing the class
It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import.  
```zenscript
mods.my.super.Registry
```

## Methods
### addASuperRecipe

Since the asIAction class will create a fully fledged ZenClass, it will also automatically create documentation entries.
 So there will be a md file with this beautiful entry.

 So you can also use docParams to provide examples.
 Though, that may also clutter your Api, so maybe it's an indicator to switch to an actual class?

```zenscript
mods.my.super.Registry.addASuperRecipe(output as crafttweaker.api.item.IItemStack, input as crafttweaker.api.item.IIngredient);
mods.my.super.Registry.addASuperRecipe(<item:minecraft:bedrock>, <item:minecraft:diamond>);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| output | [crafttweaker.api.item.IItemStack](/vanilla/api/items/IItemStack) | The recipe result |
| input | [crafttweaker.api.item.IIngredient](/vanilla/api/items/IIngredient) | The input of the recipe |


### removeABadRecipe

As you can see, you can use Strings.
 Generally you can use Strings, primitives or even lists/arrays of them

 Though I have to admit Lists/Arrays haven't been tested that well, so be careful on the generated code.

```zenscript
mods.my.super.Registry.removeABadRecipe(name as String);
mods.my.super.Registry.removeABadRecipe("some_generic_addon:bad_recipe");
```

| Parameter | Type | Description |
|-----------|------|-------------|
| name | String | The recipe name to remove |



