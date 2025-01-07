package dev.exanc.technoalchemy.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.exanc.technoalchemy.recipes.recipedisplays.RightClickBlockRecipeDisplay;
import dev.exanc.technoalchemy.recipes.slotdisplays.BlockStateSlotDisplay;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RightClickBlockRecipe
  implements Recipe<RecipeInputRightClickBlock>
{
  private final BlockState inputState;
  private final Ingredient inputIngredient;
  private final ItemStack result;
  private PlacementInfo info;

  public RightClickBlockRecipe (
    BlockState inputState,
    Ingredient inputIngredient,
    ItemStack result
  ) {
    this.inputIngredient = inputIngredient;
    this.inputState = inputState;
    this.result = result;
  }

  @Override
  public boolean matches (
    RecipeInputRightClickBlock input, Level level
  ) {
    return (this.inputState == input.state())
      && (this.inputIngredient.test(input.stack()));
  }

  // Return the result of the recipe here, based on the given input. The first parameter matches the generic.
  // IMPORTANT: Always call .copy() if you use an existing result! If you don't, things can and will break,
  // as the result exists once per recipe, but the assembled stack is created each time the recipe is crafted.
  @Override
  public ItemStack assemble (
    RecipeInputRightClickBlock input, Provider provider
  ) {
    return this.result.copy();
  }

  // When true, will prevent the recipe from being synced within the recipe book or awarded on use/unlock.
  // This should only be true if the recipe shouldn't appear in a recipe book, such as map extending.
  // Although this recipe takes in an input state, it could still be used in a custom recipe book using
  // the methods below.
  @Override
  public boolean isSpecial() {
    return true;
  }

  @Override
  public RecipeBookCategory recipeBookCategory ()
  {
    return RecipeBookCategories.RIGHT_CLICK_BLOCK_CATEGORY.get();
  }

  @Override
  public PlacementInfo placementInfo ()
  {
    if (this.info == null) {
      List<Optional<Ingredient>> ingredients = new ArrayList<>();
      Item stateItem = this.inputState.getBlock().asItem();
      
      ingredients.add(
        stateItem != Items.AIR
          ? Optional.of(Ingredient.of(stateItem))
          : Optional.empty()
      );
      ingredients.add(Optional.of(this.inputIngredient));

      this.info = PlacementInfo.createFromOptionals(ingredients);
    }

    return this.info;
  }

  @Override
  public List<RecipeDisplay> display ()
  {
    return List.of(
      new RightClickBlockRecipeDisplay(
        new BlockStateSlotDisplay(inputState),
        this.inputIngredient.display(),
        new SlotDisplay.ItemStackSlotDisplay(result),
        new SlotDisplay.ItemSlotDisplay(Items.GRASS_BLOCK)
      )
    );
  }

  @Override
  public RecipeType<? extends Recipe<RecipeInputRightClickBlock>> getType ()
  {
    return RecipeTypes.RIGHT_CLICK_BLOCK_TYPE.get();
  }

  public static class Serializer implements RecipeSerializer<RightClickBlockRecipe> {

    public static final MapCodec<RightClickBlockRecipe> CODEC = RecordCodecBuilder.mapCodec(
      inst -> inst
        .group(
          BlockState.CODEC.fieldOf("state").forGetter(RightClickBlockRecipe::getInputState),
          Ingredient.CODEC.fieldOf("ingredient").forGetter(RightClickBlockRecipe::getIngredient),
          ItemStack.CODEC.fieldOf("result").forGetter(RightClickBlockRecipe::getResult)
        )
        .apply(inst, RightClickBlockRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, RightClickBlockRecipe> STREAM_CODEC =
      StreamCodec.composite(
          ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY), RightClickBlockRecipe::getInputState,
          Ingredient.CONTENTS_STREAM_CODEC, RightClickBlockRecipe::getIngredient,
          ItemStack.STREAM_CODEC, RightClickBlockRecipe::getResult,
          RightClickBlockRecipe::new
      );


    @Override
    public MapCodec<RightClickBlockRecipe> codec ()
    {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RightClickBlockRecipe> streamCodec ()
    {
      return STREAM_CODEC;
    }
  }

  @Override
  public RecipeSerializer<? extends Recipe<RecipeInputRightClickBlock>> getSerializer ()
  {
    return RecipeSerializers.RIGHT_CLICK_BLOCK.get();
  }

  public BlockState getInputState() {
    return this.inputState;
  }
  public Ingredient getIngredient() {
    return this.inputIngredient;
  }
  public ItemStack getResult() {
    return this.result;
  }
}
