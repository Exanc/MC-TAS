package dev.exanc.technoalchemy.items;

import java.util.Collection;
import java.util.function.Supplier;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.items.itemtypes.ChiselItem;
import dev.exanc.technoalchemy.items.itemtypes.SimpleItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items {

  public static final ItemsRegister ITEMS = new ItemsRegister();

  public static final DeferredItem<SimpleItem> BISMUTH = ITEMS.register(
    "bismuth", () -> new SimpleItem("bismuth", new Item.Properties())
  );

  public static final DeferredItem<SimpleItem> RAW_BISMUTH = ITEMS.register(
    "raw_bismuth", () -> new SimpleItem("raw_bismuth", new Item.Properties())
  );

  public static final DeferredItem<ChiselItem> CHISEL = ITEMS.register(
    "chisel", () -> new ChiselItem("chisel", new Item.Properties())
  );

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
  
  public static class ItemsRegister
  {
    private DeferredRegister<Item> items;

    public ItemsRegister () {
      this.items = DeferredRegister.createItems(TechnoAlchemical.MOD_ID);
    }

    public void register (IEventBus eventBus) {
      this.items.register(eventBus);
    }

    public <T extends TechnoAlchemicalItem> DeferredItem<T> register (String name, Supplier<T> item) {
      return (DeferredItem<T>) this.items.register(name, item);
    }

    public <T extends BlockItem> DeferredItem<T> registerBlockItem (String name, Supplier<T> item) {
      return (DeferredItem<T>) this.items.register(name, item);
    }

    public Collection<DeferredHolder<Item, ? extends Item>> getEntries() {
      return this.items.getEntries();
    }
  }
}
