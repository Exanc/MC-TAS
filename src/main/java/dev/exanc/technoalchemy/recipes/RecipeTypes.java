package dev.exanc.technoalchemy.recipes;

import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeTypes {

  public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
    DeferredRegister.create(Registries.RECIPE_TYPE, TechnoAlchemical.MOD_ID);

  public static final Supplier<RecipeType<RightClickBlockRecipe>> RIGHT_CLICK_BLOCK_TYPE =
    RECIPE_TYPES.register(
        "right_click_block",
        // We need the qualifying generic here due to generics being generics.
        registryName -> new RecipeType<RightClickBlockRecipe>() {

          @Override
          public String toString() {
            return registryName.toString();
          }
        }
    );

  public static void register (IEventBus eventBus) {
    RECIPE_TYPES.register(eventBus);
  }
}
