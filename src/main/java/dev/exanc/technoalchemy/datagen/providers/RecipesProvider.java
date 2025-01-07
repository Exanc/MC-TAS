package dev.exanc.technoalchemy.datagen.providers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.Blocks;
import dev.exanc.technoalchemy.items.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;

public class RecipesProvider extends RecipeProvider {

  public RecipesProvider(
    HolderLookup.Provider provider,
    RecipeOutput output
  ) {
    super(provider, output);
  }

  public static class Runner extends RecipeProvider.Runner {

    public Runner (PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
      super(packOutput, provider);
    }

    @Override
    public String getName ()
    {
      return TechnoAlchemical.MOD_ID;
    }

    @Override
    protected RecipeProvider createRecipeProvider (Provider provider, RecipeOutput recipeOutput)
    {
      return new RecipesProvider(provider, recipeOutput);
    }
  }

  @Override
  protected void buildRecipes ()
  {
    shaped(RecipeCategory.MISC, Blocks.BISMUTH_BLOCK.get())
      .define('B', Items.BISMUTH.get()).pattern("BBB").pattern("BBB")
      .pattern("BBB").unlockedBy("has_bismuth", has(Items.BISMUTH))
      .save(this.output);
    
    shapeless(RecipeCategory.MISC, Items.BISMUTH.get(), 9)
      .requires(Blocks.BISMUTH_BLOCK)
      .unlockedBy("has_bismuth_block", has(Blocks.BISMUTH_BLOCK))
      .save(this.output);

    shapeless(RecipeCategory.MISC, Items.BISMUTH.get(), 9)
      .requires(Blocks.MAGIC_BLOCK)
      .unlockedBy("has_magic_block", has(Blocks.MAGIC_BLOCK))
      .save(this.output, TechnoAlchemical.MOD_ID + ":bismuth_from_magic");

    oreSmelting(
      this.output,
      List.of(Items.RAW_BISMUTH, Blocks.BISMUTH_ORE, Blocks.BISMUTH_DEEPSLATE_ORE),
      RecipeCategory.MISC,
      Items.BISMUTH.get(),
      0.25f,
      200,
      "bismuth"
    );

    /*stairBuilder(Blocks.BISMUTH_STAIRS.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));

    slab(RecipeCategory.BUILDING_BLOCKS, Blocks.BISMUTH_SLAB.get(), Items.BISMUTH.get());

    buttonBuilder(Blocks.BISMUTH_BUTTON.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));

    pressurePlateBuilder(RecipeCategory.REDSTONE, Blocks.BISMUTH_PRESURE_PLATE.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));

    fenceBuilder(Blocks.BISMUTH_FENCE.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));

    fenceGateBuilder(Blocks.BISMUTH_FENCE_GATE.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));

    wall(RecipeCategory.BUILDING_BLOCKS, Blocks.BISMUTH_WALL.get(), Items.BISMUTH);

    doorBuilder(Blocks.BISMUTH_DOOR.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));

    trapdoorBuilder(Blocks.BISMUTH_TRAP_DOOR.get(), Ingredient.of(Items.BISMUTH))
      .group("bismuth")
      .unlockedBy(getHasName(Items.BISMUTH), has(Items.BISMUTH));*/
  }

  protected void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
    oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
  }

  protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
    oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
  }

  protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
    for(ItemLike itemlike : pIngredients) {
        SimpleCookingRecipeBuilder
          .generic(
            Ingredient.of(itemlike),
            pCategory,
            pResult,
            pExperience,
            pCookingTime,
            pCookingSerializer,
            factory)
          .group(pGroup)
          .unlockedBy(getHasName(itemlike), has(itemlike))
          .save(
            recipeOutput, TechnoAlchemical.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)
          );
    }
  }
}
