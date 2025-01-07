package dev.exanc.technoalchemy.datagen.providers;

import java.util.concurrent.CompletableFuture;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.items.Items;
import dev.exanc.technoalchemy.items.TechnoAlchemicalItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ItemTagProvider  extends ItemTagsProvider {

  public ItemTagProvider(
    PackOutput packOutput,
    CompletableFuture<HolderLookup.Provider> lookupProvider,
    CompletableFuture<TagLookup<Block>> blockTags
  ) {
    super(packOutput, lookupProvider, blockTags, TechnoAlchemical.MOD_ID);
  }

  @Override
  protected void addTags(Provider provider) {
    var iter = Items.ITEMS.getEntries().iterator();
    while (iter.hasNext())
    { 
      DeferredHolder<Item, ? extends Item> next = iter.next();
      if (next.get() instanceof TechnoAlchemicalItem item) {
        var tags = item.getTags();

        for (var tag : tags) {
          tag(tag).add(item);
        }
      }
    }
  }
}
