package dev.exanc.technoalchemy.blocks.blocktypes;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import dev.exanc.technoalchemy.datagen.providers.BlockLootTableProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class LampBlock extends TechnoAlchemicalBlock {

  public static final BooleanProperty PROPERTY_CLICKED = BooleanProperty.create("clicked");

  private String modelOn;
  private String modelOff;

  public LampBlock (
    String id,
    String modelOn,
    String modelOff
  ) {
    super(
      id,
      BlockBehaviour.Properties.of()
        .strength(1)
        .requiresCorrectToolForDrops()
        .lightLevel(LampBlock::getLightLevel)
    );

    this.modelOn = modelOn;
    this.modelOff = modelOff;

    this.registerDefaultState(
      this.defaultBlockState().setValue(PROPERTY_CLICKED, false)
    );
  }

  @Override
  protected void createBlockStateDefinition (Builder<Block, BlockState> builder)
  {
    builder.add(PROPERTY_CLICKED);
  }

  public static int getLightLevel(BlockState state) {
    return state.getValue(LampBlock.PROPERTY_CLICKED) ? 15 : 0;
  }

  @Override
  protected InteractionResult useWithoutItem (
    BlockState state,
    Level level,
    BlockPos pos,
    Player player,
    BlockHitResult hitResult
  ) {
    if (!level.isClientSide()) {
      boolean propertyClicked = state.getValue(PROPERTY_CLICKED);

      level.setBlockAndUpdate(pos, state.setValue(PROPERTY_CLICKED, !propertyClicked));
    }

    return InteractionResult.SUCCESS;
  }

  @Override
  public void getLootTable (BlockLootTableProvider provider)
  {
    provider.dropSelf(this);
  }

  @Override
  public void getStates (BlockModelGenerators generators)
  {
    generators.blockStateOutput.accept(
      MultiVariantGenerator
        .multiVariant(this)
        .with(
          BlockModelGenerators.createBooleanModelDispatch(
            PROPERTY_CLICKED,
            ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "block/" + this.modelOn),
            ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "block/" + this.modelOff)
          )
        )
    );
    
    /*provider.getVariantBuilder(Blocks.BISMUTH_LAMP.get())
      .forAllStates((state) -> {
        if (state.getValue(LampBlock.PROPERTY_CLICKED)) {
          
          return new ConfiguredModel[] { new ConfiguredModel(
            provider.models().cubeAll(
              this.modelOn,
              ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "block/" + this.modelOn)
            )
          ) };

        } else {
          
          return new ConfiguredModel[] { new ConfiguredModel(
            provider.models().cubeAll(this.modelOff, ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "block/" + this.modelOff))
          ) };
        }
      });

    provider.simpleBlockItem(
      Blocks.BISMUTH_LAMP.get(),
      provider.models().cubeAll(
        this.modelOn,
        ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "block/" + this.modelOn)
      )
    );*/
  }

  @Override
  public List<TagKey<Block>> getTags ()
  {
    return List.of();
  }
}
