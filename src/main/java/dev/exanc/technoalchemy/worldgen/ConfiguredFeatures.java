package dev.exanc.technoalchemy.worldgen;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.Blocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ConfiguredFeatures {

  public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BISMUTH_ORE = registerKey("overworld_bismuth_ore");
  public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BISMUTH_ORE = registerKey("nether_bismuth_ore");
  public static final ResourceKey<ConfiguredFeature<?, ?>> END_BISMUTH_ORE = registerKey("end_bismuth_ore");

  public static void bootstrap (BootstrapContext<ConfiguredFeature<?, ?>> context) {

    RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    RuleTest netherReplaceable = new BlockMatchTest(net.minecraft.world.level.block.Blocks.NETHERRACK);
    RuleTest endReplaceable = new BlockMatchTest(net.minecraft.world.level.block.Blocks.END_STONE);

    List<OreConfiguration.TargetBlockState> overworldBismuthOres = List.of(
      OreConfiguration.target(stoneReplaceable, Blocks.BISMUTH_ORE.get().defaultBlockState()),
      OreConfiguration.target(deepslateReplaceable, Blocks.BISMUTH_DEEPSLATE_ORE.get().defaultBlockState())
    );

    register(context, OVERWORLD_BISMUTH_ORE, Feature.ORE, new OreConfiguration(overworldBismuthOres, 5));
    register(
      context,
      NETHER_BISMUTH_ORE,
      Feature.ORE,
      new OreConfiguration(netherReplaceable, Blocks.BISMUTH_NETHER_ORE.get().defaultBlockState(), 5)
    );
    register(
      context,
      END_BISMUTH_ORE,
      Feature.ORE,
      new OreConfiguration(endReplaceable, Blocks.BISMUTH_END_ORE.get().defaultBlockState(), 5)
    );
  }

  public static  ResourceKey<ConfiguredFeature<?, ?>> registerKey (String name) {
    return ResourceKey.create(
      Registries.CONFIGURED_FEATURE,
      ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, name)
    );
  }

  private static <
    FC extends FeatureConfiguration,
    F extends Feature<FC>
  > void register (
    BootstrapContext<ConfiguredFeature<?, ?>> context,
    ResourceKey<ConfiguredFeature<?, ?>> key,
    F feature,
    FC configuration
  ) {
    context.register(key, new ConfiguredFeature<>(feature,  configuration));
  }
}
