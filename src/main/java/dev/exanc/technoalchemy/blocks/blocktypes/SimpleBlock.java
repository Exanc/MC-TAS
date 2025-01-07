package dev.exanc.technoalchemy.blocks.blocktypes;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import dev.exanc.technoalchemy.datagen.providers.BlockLootTableProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class SimpleBlock extends TechnoAlchemicalBlock {

  public SimpleBlock (String id, Properties properties) {
    super(id, properties);
  }

  @Override
  public void getLootTable (BlockLootTableProvider provider)
  {
    provider.dropSelf(this);
  }

  @Override
  public void getStates (BlockModelGenerators generators)
  {
    generators.createTrivialCube(this);
    generators.registerSimpleItemModel(this, ResourceLocation.fromNamespaceAndPath(
      TechnoAlchemical.MOD_ID, "block/" + this.id
    ));
  }

  @Override
  public List<TagKey<Block>> getTags ()
  {
    return List.of();
  }
}
