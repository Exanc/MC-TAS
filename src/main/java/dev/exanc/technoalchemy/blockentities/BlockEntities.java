package dev.exanc.technoalchemy.blockentities;

import java.util.Set;
import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.Blocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntities {

  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
    DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TechnoAlchemical.MOD_ID);

  /*public static final Supplier<BlockEntityType<DemoBlockEntity>> DEMO_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
    "my_block_entity",
    () -> new BlockEntityType<>(
      DemoBlockEntity::new, 
      Set.of(Blocks.DEMO_BLOCK_1.get()),
      null
    )
  );*/

  public static void register (IEventBus eventBus) {
    BLOCK_ENTITY_TYPES.register(eventBus);
  }
}
