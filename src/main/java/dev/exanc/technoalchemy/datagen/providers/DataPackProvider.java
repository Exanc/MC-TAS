package dev.exanc.technoalchemy.datagen.providers;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.worldgen.FeatureBiomeModifiers;
import dev.exanc.technoalchemy.worldgen.ConfiguredFeatures;
import dev.exanc.technoalchemy.worldgen.PlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DataPackProvider extends DatapackBuiltinEntriesProvider {

  public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
    .add(Registries.CONFIGURED_FEATURE, ConfiguredFeatures::bootstrap)
    .add(Registries.PLACED_FEATURE, PlacedFeatures::bootstrap)
    .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, FeatureBiomeModifiers::bootstrap)
    ;

  public DataPackProvider (PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries, BUILDER, Set.of(TechnoAlchemical.MOD_ID));
  }
}
