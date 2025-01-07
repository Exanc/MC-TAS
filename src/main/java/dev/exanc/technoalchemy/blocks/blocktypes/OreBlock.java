package dev.exanc.technoalchemy.blocks.blocktypes;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import dev.exanc.technoalchemy.datagen.providers.BlockLootTableProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredItem;

public class OreBlock extends TechnoAlchemicalBlock {

  public static enum ToolTier {
    STONE,
    IRON,
    DIAMOND
  }

  private IntProvider experience;
  private ToolTier miningTier;
  private DeferredItem<?> resource;
  private int minDrops;
  private int maxDrops;

  public OreBlock (
    String id,
    Properties properties,
    IntProvider experience,
    ToolTier tier,
    DeferredItem<?> resource,
    int minDrops,
    int maxDrops
  ) {
    super(id, properties.requiresCorrectToolForDrops());

    this.experience = experience;
    this.miningTier = tier;
    this.resource = resource;
    this.minDrops = minDrops;
    this.maxDrops = maxDrops;
  }

  @Override
  public int getExpDrop (
    BlockState state,
    LevelAccessor level,
    BlockPos pos,
    @Nullable BlockEntity blockEntity,
    @Nullable Entity breaker,
    ItemStack tool)
  {
    return this.experience.sample(level.getRandom());
  }

  @Override
  public void getLootTable (BlockLootTableProvider provider)
  {
    provider.add(
      this,
      (block) -> provider.createStandardOreDrops(
        block, resource.asItem(), this.minDrops, this.maxDrops
      )
    );
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
    List<TagKey<Block>> tags = new ArrayList<>(5);
    tags.add(BlockTags.MINEABLE_WITH_PICKAXE);

    switch (this.miningTier) {
      case STONE:
        tags.add(BlockTags.NEEDS_STONE_TOOL);
        break;
      case IRON:
        tags.add(BlockTags.NEEDS_IRON_TOOL);
        break;
      case DIAMOND:
        tags.add(BlockTags.NEEDS_DIAMOND_TOOL);
        break;
    
      default:
        break;
    }

    return tags;
  }
}
