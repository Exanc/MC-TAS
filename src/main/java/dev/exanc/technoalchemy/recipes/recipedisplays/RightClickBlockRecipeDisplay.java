package dev.exanc.technoalchemy.recipes.recipedisplays;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record RightClickBlockRecipeDisplay (
  SlotDisplay inputState,
  SlotDisplay inputItem,
  SlotDisplay result,
  SlotDisplay craftingStation
) implements RecipeDisplay {

  public static final MapCodec<RightClickBlockRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
    instance -> instance
      .group(
        SlotDisplay.CODEC.fieldOf("inputState").forGetter(RightClickBlockRecipeDisplay::inputState),
        SlotDisplay.CODEC.fieldOf("inputState").forGetter(RightClickBlockRecipeDisplay::inputItem),
        SlotDisplay.CODEC.fieldOf("result").forGetter(RightClickBlockRecipeDisplay::result),
        SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(RightClickBlockRecipeDisplay::craftingStation)
      )
      .apply(instance, RightClickBlockRecipeDisplay::new)
  );

  public static final StreamCodec<RegistryFriendlyByteBuf, RightClickBlockRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
    SlotDisplay.STREAM_CODEC,
    RightClickBlockRecipeDisplay::inputState,
    SlotDisplay.STREAM_CODEC,
    RightClickBlockRecipeDisplay::inputItem,
    SlotDisplay.STREAM_CODEC,
    RightClickBlockRecipeDisplay::result,
    SlotDisplay.STREAM_CODEC,
    RightClickBlockRecipeDisplay::craftingStation,
    RightClickBlockRecipeDisplay::new
  );

  @Override
  public Type<? extends RecipeDisplay> type ()
  {
    return RecipeDisplayTypes.RIGHT_CLICK_BLOCK_RECIPE_DISPLAY.get();
  }
}
