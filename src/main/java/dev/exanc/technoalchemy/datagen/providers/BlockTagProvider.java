package dev.exanc.technoalchemy.datagen.providers;

import java.util.concurrent.CompletableFuture;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.Blocks;
import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockTagProvider extends BlockTagsProvider {

  public BlockTagProvider(
    PackOutput packOutput,
    CompletableFuture<HolderLookup.Provider> lookupProvider
  ) {
    super(packOutput, lookupProvider, TechnoAlchemical.MOD_ID);
  }

  @Override
  protected void addTags(Provider arg0) {

    var iter = Blocks.BLOCKS.getEntries().iterator();
    while (iter.hasNext())
    { 
      DeferredHolder<Block, ? extends Block> next = iter.next();
      if (next.get() instanceof TechnoAlchemicalBlock block) {
        var tags = block.getTags();

        for (var tag : tags) {
          tag(tag).add(block);
        }
      }
    }
  }

  @Override
  public IntrinsicTagAppender<Block> tag (TagKey<Block> tag)
  {
    return super.tag(tag);
  }
}
