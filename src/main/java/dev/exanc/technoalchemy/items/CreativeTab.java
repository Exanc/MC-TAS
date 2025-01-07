package dev.exanc.technoalchemy.items;

import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.blocks.Blocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTab {

  public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(
    Registries.CREATIVE_MODE_TAB, TechnoAlchemical.MOD_ID
  );

  public static final Supplier<CreativeModeTab> TECHNO_ALCHMEICAL_TAB = CREATIVE_TAB.register(
    "techno_alchemical_tab",
    () -> CreativeModeTab.builder()
      .icon(() -> new ItemStack(Items.BISMUTH.get()))
      .title(Component.translatable("creative_tab."+ TechnoAlchemical.MOD_ID +".techno_alchemical_tab"))
      .displayItems(
        (itemDisplayParameters, output) -> {
          output.accept(Items.CHISEL);
          output.accept(Items.BISMUTH);
          output.accept(Items.RAW_BISMUTH);
          output.accept(Blocks.BISMUTH_BLOCK);
          output.accept(Blocks.BISMUTH_ORE);
          output.accept(Blocks.BISMUTH_DEEPSLATE_ORE);
          //output.accept(Blocks.BISMUTH_BUTTON);
          //output.accept(Blocks.BISMUTH_STAIRS);
          //output.accept(Blocks.BISMUTH_DOOR);
          //output.accept(Blocks.BISMUTH_TRAP_DOOR);
          //output.accept(Blocks.BISMUTH_FENCE);
          //output.accept(Blocks.BISMUTH_FENCE_GATE);
          //output.accept(Blocks.BISMUTH_PRESURE_PLATE);
          //output.accept(Blocks.BISMUTH_SLAB);
          //output.accept(Blocks.BISMUTH_WALL);
          output.accept(Blocks.MAGIC_BLOCK);
          //output.accept(Blocks.BISMUTH_LAMP);
        }
      )
      .build()
  );

  public static void register(IEventBus eventBus) {
    CREATIVE_TAB.register(eventBus);
  }

}
