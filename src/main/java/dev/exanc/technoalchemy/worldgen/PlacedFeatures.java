package dev.exanc.technoalchemy.worldgen;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.worldgen.util.OrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class PlacedFeatures {

  public static final ResourceKey<PlacedFeature> OVERWORLD_BISMUTH_ORE = registerKey("overworld_bismuth_ore_placed");
  public static final ResourceKey<PlacedFeature> NETHER_BISMUTH_ORE = registerKey("nether_bismuth_ore_placed");
  public static final ResourceKey<PlacedFeature> END_BISMUTH_ORE = registerKey("end_bismuth_ore_placed");

  public static void bootstrap (BootstrapContext<PlacedFeature> context) {
    HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

    register(
      context,
      OVERWORLD_BISMUTH_ORE,
      configuredFeatures.getOrThrow(ConfiguredFeatures.OVERWORLD_BISMUTH_ORE),
      OrePlacement.commonOrePlacements(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)))
    );

    register(
      context,
      NETHER_BISMUTH_ORE,
      configuredFeatures.getOrThrow(ConfiguredFeatures.NETHER_BISMUTH_ORE),
      OrePlacement.commonOrePlacements(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)))
    );

    register(
      context,
      END_BISMUTH_ORE,
      configuredFeatures.getOrThrow(ConfiguredFeatures.END_BISMUTH_ORE),
      OrePlacement.commonOrePlacements(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)))
    );
  }

  public static ResourceKey<PlacedFeature> registerKey (String name) {
    return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, name));
  }

  private static void register (
    BootstrapContext<PlacedFeature> context,
    ResourceKey<PlacedFeature> key,
    Holder<ConfiguredFeature<?, ?>> configuredFeature,
    List<PlacementModifier> modifiers
  ) {
    context.register(key, new PlacedFeature(configuredFeature, List.copyOf(modifiers)));
  }
}
