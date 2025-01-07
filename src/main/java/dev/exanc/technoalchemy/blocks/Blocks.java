package dev.exanc.technoalchemy.blocks;

import java.util.Collection;
import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.blocktypes.MagicBlock;
import dev.exanc.technoalchemy.blocks.blocktypes.OreBlock;
import dev.exanc.technoalchemy.blocks.blocktypes.SimpleBlock;
import dev.exanc.technoalchemy.items.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Blocks {

  public static final BlocksRegister BLOCKS = new BlocksRegister();

  public static final DeferredBlock<SimpleBlock> BISMUTH_BLOCK = registerBlock(
    "bismuth_block", () -> new SimpleBlock(
      "bismuth_block",
      BlockBehaviour.Properties.of()
        .strength(4)
        .sound(SoundType.AMETHYST)
    )
  );

  public static final DeferredBlock<OreBlock> BISMUTH_ORE = registerBlock(
    "bismuth_ore",
    () -> new OreBlock(
      "bismuth_ore",
      BlockBehaviour.Properties.of()
        .strength(2)
        .sound(SoundType.AMETHYST),
      UniformInt.of(2, 4),
      OreBlock.ToolTier.IRON,
      Items.RAW_BISMUTH,
      2, 3
    )
  );

  public static final DeferredBlock<OreBlock> BISMUTH_DEEPSLATE_ORE = registerBlock(
    "bismuth_deepslate_ore",
    () -> new OreBlock(
      "bismuth_deepslate_ore",
      BlockBehaviour.Properties.of()
        .strength(2)
        .sound(SoundType.AMETHYST),
      UniformInt.of(2, 4),
      OreBlock.ToolTier.DIAMOND,
      Items.RAW_BISMUTH,
      2, 3
    )
  );

  public static final DeferredBlock<OreBlock> BISMUTH_NETHER_ORE = registerBlock(
    "bismuth_nether_ore",
    () -> new OreBlock(
      "bismuth_nether_ore",
      BlockBehaviour.Properties.of()
        .strength(2)
        .sound(SoundType.AMETHYST),
      UniformInt.of(2, 4),
      OreBlock.ToolTier.DIAMOND,
      Items.RAW_BISMUTH,
      2, 3
    )
  );

  public static final DeferredBlock<OreBlock> BISMUTH_END_ORE = registerBlock(
    "bismuth_end_ore",
    () -> new OreBlock(
      "bismuth_end_ore",
      BlockBehaviour.Properties.of()
        .strength(2)
        .sound(SoundType.AMETHYST),
      UniformInt.of(2, 4),
      OreBlock.ToolTier.DIAMOND,
      Items.RAW_BISMUTH,
      2, 3
    )
  );

  public static final DeferredBlock<MagicBlock> MAGIC_BLOCK = registerBlock("magic_block", () -> new MagicBlock("magic_block"));

  /*public static final DeferredBlock<StairBlock> BISMUTH_STAIRS = oldregisterBlock(
    "bismuth_stairs", () -> new StairBlock(
      BISMUTH_BLOCK.get().defaultBlockState(),
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_stairs"
        )))
    )
  );

  public static final DeferredBlock<SlabBlock> BISMUTH_SLAB = oldregisterBlock(
    "bismuth_slab", () -> new SlabBlock(
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_slab"
        )))
    )
  );

  public static final DeferredBlock<PressurePlateBlock> BISMUTH_PRESURE_PLATE = oldregisterBlock(
    "bismuth_presure_plate", () -> new PressurePlateBlock(
      BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_presure_plate"
        )))
    )
  );

  public static final DeferredBlock<ButtonBlock> BISMUTH_BUTTON = oldregisterBlock(
    "bismuth_button", () -> new ButtonBlock(
      BlockSetType.IRON,
      30,
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops().noCollission()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_button"
        )))
    )
  );

  public static final DeferredBlock<FenceBlock> BISMUTH_FENCE = oldregisterBlock(
    "bismuth_fence", () -> new FenceBlock(
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismtuh_fence"
        )))
    )
  );

  public static final DeferredBlock<FenceGateBlock> BISMUTH_FENCE_GATE = oldregisterBlock(
    "bismuth_fence_gate", () -> new FenceGateBlock(
      WoodType.ACACIA,
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_fence_gate"
        )))
    )
  );

  public static final DeferredBlock<WallBlock> BISMUTH_WALL = oldregisterBlock(
    "bismuth_wall", () -> new WallBlock(
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_wall"
        )))
    )
  );

  public static final DeferredBlock<DoorBlock> BISMUTH_DOOR = oldregisterBlock(
    "bismuth_door", () -> new DoorBlock(
      BlockSetType.IRON,
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops().noOcclusion()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_door"
        )))
    )
  );

  public static final DeferredBlock<TrapDoorBlock> BISMUTH_TRAP_DOOR = oldregisterBlock(
    "bismuth_trap_door", () -> new TrapDoorBlock(
      BlockSetType.IRON,
      BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops().noOcclusion()
        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(
          TechnoAlchemical.MOD_ID, "bismuth_trap_door"
        )))
    )
  );

  public static final DeferredBlock<LampBlock> BISMUTH_LAMP = registerBlock(
    "bismuth_lamp", () -> new LampBlock("bismtuh_lamp", "bismuth_lamp_on", "bismuth_lamp_off")
  );*/

  //public static final DeferredBlock<AbstractEntityBlock> DEMO_BLOCK_1 = BLOCKS.register(
  //  "demo_block_1", () -> new AbstractEntityBlock(BlockBehaviour.Properties.of())
  //);

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }

  private static <T extends TechnoAlchemicalBlock> DeferredBlock<T> registerBlock (String name, Supplier<T> block) {
    DeferredBlock<T> result = BLOCKS.register(name, block);
    registerBlockItem(name, result);
    return result;
  }

  private static void registerBlockItem(String name, DeferredBlock<?> block) {
    Items.ITEMS.registerBlockItem(
      name,
      () -> new BlockItem(
        block.get(),
        new Item.Properties()
          .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(
            TechnoAlchemical.MOD_ID, name
          )))
          .useBlockDescriptionPrefix()
      )
    );
  }

  public static class BlocksRegister
  {
    private DeferredRegister<Block> blocks;

    public BlocksRegister () {
      this.blocks = DeferredRegister.createBlocks(TechnoAlchemical.MOD_ID);
    }

    public void register (IEventBus eventBus) {
      this.blocks.register(eventBus);
    }

    public <T extends TechnoAlchemicalBlock> DeferredBlock<T> register (String name, Supplier<T> block) {
      return (DeferredBlock<T>) this.blocks.register(name, block);
    }

    @Deprecated
    public <T extends Block> DeferredBlock<T> oldregister (String name, Supplier<T> block) {
      return (DeferredBlock<T>) this.blocks.register(name, block);
    }

    public Collection<DeferredHolder<Block, ? extends Block>> getEntries() {
      return this.blocks.getEntries();
    }
  }
}
