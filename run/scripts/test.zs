import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.recipe.replacement.Replacer;
import mods.genericaddon.MyStaticIntegrationClass;

MyStaticIntegrationClass.doPrint(<item:minecraft:tnt>);

<recipetype:genericaddon:infusion>.addRecipe("script_test", <item:minecraft:diamond>, <item:minecraft:dirt>);

/*
Before the Replacer call is executed. Feel free to comment the replacer out to check yourself.
[INFO][CraftTweaker-Commands]: Recipe type: '<recipetype:genericaddon:infusion>'
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_a", <item:minecraft:bedrock>, <item:minecraft:iron_axe>);
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_b", <item:minecraft:redstone>, <item:minecraft:bedrock>);
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_c", <item:minecraft:iron_axe>, <item:minecraft:redstone>);
  <recipetype:genericaddon:infusion>.addRecipe("crafttweaker:script_test", <item:minecraft:diamond>, <item:minecraft:dirt>);
*/

Replacer.create()
.replace<IIngredient>(<recipecomponent:crafttweaker:input/ingredients>, <item:minecraft:bedrock>, <item:minecraft:stone>)
.execute();

/*
After it is executed:
[INFO][CraftTweaker-Commands]: Recipe type: '<recipetype:genericaddon:infusion>'
  <recipetype:genericaddon:infusion>.addRecipe("crafttweaker:autogenerated/replacer/genericaddon.recipe_b.1", <item:minecraft:redstone>, <item:minecraft:stone>);
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_a", <item:minecraft:bedrock>, <item:minecraft:iron_axe>);
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_c", <item:minecraft:iron_axe>, <item:minecraft:redstone>);
  <recipetype:genericaddon:infusion>.addRecipe("crafttweaker:script_test", <item:minecraft:diamond>, <item:minecraft:dirt>);
*/

<recipetype:genericaddon:infusion>.removeByName("genericaddon:recipe_a");

/*
Final state:

[INFO][CraftTweaker-Commands]: Recipe type: '<recipetype:genericaddon:infusion>'
  <recipetype:genericaddon:infusion>.addRecipe("crafttweaker:autogenerated/replacer/genericaddon.recipe_b.1", <item:minecraft:redstone>, <item:minecraft:stone>);
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_a", <item:minecraft:bedrock>, <item:minecraft:iron_axe>);
  <recipetype:genericaddon:infusion>.addRecipe("genericaddon:recipe_c", <item:minecraft:iron_axe>, <item:minecraft:redstone>);
  <recipetype:genericaddon:infusion>.addRecipe("crafttweaker:script_test", <item:minecraft:diamond>, <item:minecraft:dirt>);

*/