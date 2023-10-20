# MyStaticIntegrationClass

A class with static members that allow us to do stuff!

## Importing the class

It might be required for you to import the package if you encounter any issues (like casting an Array), so better be safe than sorry and add the import at the very top of the file.
```zenscript
import mods.genericaddon.MyStaticIntegrationClass;
```


## Static Methods

:::group{name=doPrint}

Prints the given ingredient to the console, yay!

```zenscript
// MyStaticIntegrationClass.doPrint(ingredient as IIngredient)

MyStaticIntegrationClass.doPrint(<item:minecraft:iron_ingot>);
```

| Parameter  |                        Type                        |         Description          |
|------------|----------------------------------------------------|------------------------------|
| ingredient | [IIngredient](/vanilla/api/ingredient/IIngredient) | The ingredient to be printed |


:::

