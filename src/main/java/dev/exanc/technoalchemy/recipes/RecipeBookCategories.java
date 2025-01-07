package dev.exanc.technoalchemy.recipes;

import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.ExtendedRecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRecipeBookSearchCategoriesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeBookCategories {

  public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES =
    DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, TechnoAlchemical.MOD_ID);

  public static final Supplier<RecipeBookCategory> RIGHT_CLICK_BLOCK_CATEGORY = RECIPE_BOOK_CATEGORIES.register(
    "right_click_block", RecipeBookCategory::new
  );

  public static void register (IEventBus eventBus) {
    RECIPE_BOOK_CATEGORIES.register(eventBus);
  }

  @EventBusSubscriber(modid = TechnoAlchemical.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value =  Dist.DEDICATED_SERVER)
  public static class ExtendedRecipeBookCategories {

    public static final ExtendedRecipeBookCategory RIGHT_CLICK_BLOCK_SEARCH_CATEGORY = new ExtendedRecipeBookCategory() {};

    // On the mod event bus
    @SubscribeEvent
    public static void registerSearchCategories(RegisterRecipeBookSearchCategoriesEvent event) {
      event.register(
        // The search category
        RIGHT_CLICK_BLOCK_SEARCH_CATEGORY,
        // All recipe categories within the search category as varargs
        RIGHT_CLICK_BLOCK_CATEGORY.get()
      );
    }
  }
}
