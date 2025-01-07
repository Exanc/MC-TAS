package dev.exanc.technoalchemy.component;

import java.util.function.UnaryOperator;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentTypes {

  public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(
    Registries.DATA_COMPONENT_TYPE,
    TechnoAlchemical.MOD_ID
  );

  public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = registerType(
    "coordinates", (builder) -> builder.persistent(BlockPos.CODEC)
  );

  public static void register (IEventBus eventBus) {
    DATA_COMPONENT_TYPES.register(eventBus);
  }

  private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> registerType (
    String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator
  ) {
    return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
  }
}
