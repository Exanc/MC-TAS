package dev.exanc.technoalchemy.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.datagen.providers.BlockLootTableProvider;
import dev.exanc.technoalchemy.datagen.providers.BlockTagProvider;
import dev.exanc.technoalchemy.datagen.providers.DataPackProvider;
import dev.exanc.technoalchemy.datagen.providers.ItemTagProvider;
import dev.exanc.technoalchemy.datagen.providers.ModelProvider;
import dev.exanc.technoalchemy.datagen.providers.RecipesProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = TechnoAlchemical.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DataGenerators {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent.Client event) {

    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    event.getResourceManager(PackType.CLIENT_RESOURCES);
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    generator.addProvider(
      true,
      new LootTableProvider(
        packOutput,
        Collections.emptySet(),
        List.of(new LootTableProvider.SubProviderEntry(BlockLootTableProvider::new, LootContextParamSets.BLOCK)),
        lookupProvider
      )
    );

    generator.addProvider(true, new ModelProvider(packOutput));

    BlockTagProvider blockTagProvider = new BlockTagProvider(packOutput, lookupProvider);
    
    generator.addProvider(true, blockTagProvider);
    generator.addProvider(true, new ItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter()));

    generator.addProvider(true, new RecipesProvider.Runner(packOutput, lookupProvider));

    generator.addProvider(true, new DataPackProvider(packOutput, lookupProvider));
  }
}
