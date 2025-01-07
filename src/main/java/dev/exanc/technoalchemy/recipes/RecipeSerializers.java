package dev.exanc.technoalchemy.recipes;

import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeSerializers {

  public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
    DeferredRegister.create(Registries.RECIPE_SERIALIZER, TechnoAlchemical.MOD_ID);

  public static final Supplier<RecipeSerializer<RightClickBlockRecipe>> RIGHT_CLICK_BLOCK =
    RECIPE_SERIALIZERS.register("right_click_block_serializer", RightClickBlockRecipe.Serializer::new);

  public static void register (IEventBus eventBus) {
    RECIPE_SERIALIZERS.register(eventBus);
  }
}
