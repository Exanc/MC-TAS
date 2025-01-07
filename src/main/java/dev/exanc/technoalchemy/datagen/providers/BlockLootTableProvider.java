package dev.exanc.technoalchemy.datagen.providers;

import java.util.Set;
import java.util.function.Function;

import dev.exanc.technoalchemy.blocks.Blocks;
import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockLootTableProvider extends BlockLootSubProvider {

  public BlockLootTableProvider(
    HolderLookup.Provider registries
  ) {
    super(
      Set.of(),
      FeatureFlags.REGISTRY.allFlags(),
      registries
    );
  }  

  @Override
  protected void generate() {
    var iter = Blocks.BLOCKS.getEntries().iterator();
    while (iter.hasNext())
    { 
      DeferredHolder<Block, ? extends Block> next = iter.next();
      if (next.get() instanceof TechnoAlchemicalBlock block) {
        block.getLootTable(this);
      }
    }

    /*dropSelf(Blocks.BISMUTH_STAIRS.get());
    dropSelf(Blocks.BISMUTH_FENCE.get());
    dropSelf(Blocks.BISMUTH_FENCE_GATE.get());
    dropSelf(Blocks.BISMUTH_WALL.get());
    dropSelf(Blocks.BISMUTH_TRAP_DOOR.get());
    dropSelf(Blocks.BISMUTH_PRESURE_PLATE.get());
    dropSelf(Blocks.BISMUTH_BUTTON.get());
    add(
      Blocks.BISMUTH_SLAB.get(),
      (block) -> createSlabItemTable(Blocks.BISMUTH_SLAB.get())
    );
    add(
      Blocks.BISMUTH_DOOR.get(),
      (block) -> createDoorTable(Blocks.BISMUTH_DOOR.get())
    );*/
  }

  public void dropSelf(TechnoAlchemicalBlock block) {
    super.dropSelf(block);
  }

  @Override
  public void add (Block pBlock, Function<Block, Builder> pFactory)
  {
    super.add(pBlock, pFactory);
  }
  
  public LootTable.Builder createStandardOreDrops(Block block, Item item, int min, int max) {
    HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
    return this.createSilkTouchDispatchTable(
      block,
      (LootPoolEntryContainer.Builder<?>) this.applyExplosionDecay(
        block,
        LootItem
          .lootTableItem(item)
          .apply(SetItemCountFunction
          .setCount(UniformGenerator.between(min, min)))
          .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
      )
    );
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return Blocks.BLOCKS
      .getEntries()
      .stream()
      .map(Holder::value)::iterator;
  }
}
