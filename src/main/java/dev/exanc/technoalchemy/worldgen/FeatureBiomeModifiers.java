package dev.exanc.technoalchemy.worldgen;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class FeatureBiomeModifiers {

  public static final ResourceKey<BiomeModifier> OVERWORLD_BISMUTH_ORE = registerKey("overworld_bismuth_ore_biomemod");
  public static final ResourceKey<BiomeModifier> NETHER_BISMUTH_ORE = registerKey("nether_bismuth_ore_biomemod");
  public static final ResourceKey<BiomeModifier> END_BISMUTH_ORE = registerKey("end_bismuth_ore_biomemod");

  public static void bootstrap (BootstrapContext<BiomeModifier> context) {
    HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
    HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

    context.register(OVERWORLD_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
      biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
      HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.OVERWORLD_BISMUTH_ORE)),
      GenerationStep.Decoration.UNDERGROUND_ORES
    ));
    context.register(NETHER_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
      biomes.getOrThrow(BiomeTags.IS_NETHER),
      HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.NETHER_BISMUTH_ORE)),
      GenerationStep.Decoration.UNDERGROUND_ORES
    ));
    context.register(END_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
      biomes.getOrThrow(BiomeTags.IS_END),
      HolderSet.direct(placedFeatures.getOrThrow(PlacedFeatures.END_BISMUTH_ORE)),
      GenerationStep.Decoration.UNDERGROUND_ORES
    ));
  }

  private static ResourceKey<BiomeModifier> registerKey (String name) {
    return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, name));
  }
}
