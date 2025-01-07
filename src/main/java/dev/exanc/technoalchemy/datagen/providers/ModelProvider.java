package dev.exanc.technoalchemy.datagen.providers;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.Blocks;
import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import dev.exanc.technoalchemy.items.Items;
import dev.exanc.technoalchemy.items.TechnoAlchemicalItem;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModelProvider extends net.minecraft.client.data.models.ModelProvider {

  public ModelProvider (PackOutput packOutput) {
    super(packOutput, TechnoAlchemical.MOD_ID);
  }

  @Override
  protected void registerModels (
    BlockModelGenerators blockModels,
    ItemModelGenerators itemModels
  ) {
    BlockModelProvider.registerBlockModels(blockModels);
    ItemModelProvider.registerItemsModels(itemModels);  
  }

  private static class BlockModelProvider
  {
    public static void registerBlockModels (BlockModelGenerators generators)
    {
      var iter = Blocks.BLOCKS.getEntries().iterator();
      while (iter.hasNext())
      { 
        DeferredHolder<Block, ? extends Block> next = iter.next();
        if (next.get() instanceof TechnoAlchemicalBlock block) {
          block.getStates(generators);
        }
      }
    }
  }

  private static class ItemModelProvider
  {
    public static void registerItemsModels (ItemModelGenerators generators)
    {
      var iter = Items.ITEMS.getEntries().iterator();
      while (iter.hasNext())
      { 
        DeferredHolder<Item, ? extends Item> next = iter.next();
        if (next.get() instanceof TechnoAlchemicalItem item) {
          item.getModel(generators);
        }
      }

      /*generators.itemModelOutput.accept(
        Blocks.BISMUTH_BUTTON.asItem(),
        ItemModelUtils.plainModel(
          ModelTemplates.BUTTON.create(
            Blocks.BISMUTH_BLOCK.get(),
            TextureMapping.layer0(Blocks.BISMUTH_BLOCK.get()),
            generators.modelOutput
          )
        )
      );*/
    }
  }

}
