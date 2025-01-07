package dev.exanc.technoalchemy.recipes.recipedisplays;

import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeDisplayTypes {

  public static final DeferredRegister<RecipeDisplay.Type<?>> RECIPE_DISPLAY_TYPES =
    DeferredRegister.create(Registries.RECIPE_DISPLAY, TechnoAlchemical.MOD_ID);

  public static final Supplier<RecipeDisplay.Type<RightClickBlockRecipeDisplay>> RIGHT_CLICK_BLOCK_RECIPE_DISPLAY = RECIPE_DISPLAY_TYPES.register(
    "right_click_block",
    () -> new RecipeDisplay.Type<>(RightClickBlockRecipeDisplay.MAP_CODEC, RightClickBlockRecipeDisplay.STREAM_CODEC)
  );

  public static void register (IEventBus eventBus) {
    RECIPE_DISPLAY_TYPES.register(eventBus);
  }
}
