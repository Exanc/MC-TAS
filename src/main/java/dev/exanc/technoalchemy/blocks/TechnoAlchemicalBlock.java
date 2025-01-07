package dev.exanc.technoalchemy.blocks;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.datagen.providers.BlockLootTableProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * Represents the minimal amount of information a block should provide
 */
public abstract class TechnoAlchemicalBlock extends Block {

  protected final String id;

  public TechnoAlchemicalBlock (String id, Properties properties) {
    super(
      properties.setId(ResourceKey.create(
        Registries.BLOCK,
        ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, id)
      ))
    );

    this.id = id;
  }

  public abstract void getLootTable (BlockLootTableProvider provider);
  public abstract void getStates (BlockModelGenerators generators);
  public abstract List<TagKey<Block>> getTags ();
}
