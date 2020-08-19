# MyStaticIntegrationClass

A class with static members that allow us to do stuff!

This class was added by a mod with mod-id `some_generic_addon`. So you need to have this mod installed if you want to use this feature.

## Importing the class
It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import.  
```zenscript
mods.some_generic_addon.MyStaticIntegrationClass
```

## Methods
### doPrint

Prints the given ingredient to the console, yay!

```zenscript
mods.some_generic_addon.MyStaticIntegrationClass.doPrint(ingredient as crafttweaker.api.item.IIngredient);
mods.some_generic_addon.MyStaticIntegrationClass.doPrint(<item:minecraft:iron_ingot>);
```

| Parameter | Type | Description |
|-----------|------|-------------|
| ingredient | [crafttweaker.api.item.IIngredient](/vanilla/api/items/IIngredient) | The ingredient to be printed |



