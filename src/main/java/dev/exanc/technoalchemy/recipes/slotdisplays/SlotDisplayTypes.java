package dev.exanc.technoalchemy.recipes.slotdisplays;

import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SlotDisplayTypes {

  public static final DeferredRegister<SlotDisplay.Type<?>> SLOT_DISPLAY_TYPES =
    DeferredRegister.create(Registries.SLOT_DISPLAY, TechnoAlchemical.MOD_ID);

  public static final Supplier<SlotDisplay.Type<BlockStateSlotDisplay>> BLOCK_STATE_SLOT_DISPLAY = SLOT_DISPLAY_TYPES.register(
    "block_state",
    () -> new SlotDisplay.Type<>(BlockStateSlotDisplay.CODEC, BlockStateSlotDisplay.STREAM_CODEC)
  );

  public static void register (IEventBus eventBus) {
    SLOT_DISPLAY_TYPES.register(eventBus);
  }
}
