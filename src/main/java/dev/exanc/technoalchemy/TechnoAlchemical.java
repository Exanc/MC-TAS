package dev.exanc.technoalchemy;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.exanc.technoalchemy.blockentities.BlockEntities;
import dev.exanc.technoalchemy.blocks.Blocks;
import dev.exanc.technoalchemy.component.DataComponentTypes;
import dev.exanc.technoalchemy.items.CreativeTab;
import dev.exanc.technoalchemy.items.Items;
import dev.exanc.technoalchemy.recipes.RecipeBookCategories;
import dev.exanc.technoalchemy.recipes.RecipeSerializers;
import dev.exanc.technoalchemy.recipes.RecipeTypes;
import dev.exanc.technoalchemy.recipes.recipedisplays.RecipeDisplayTypes;
import dev.exanc.technoalchemy.recipes.slotdisplays.SlotDisplayTypes;
import dev.exanc.technoalchemy.sound.Sounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TechnoAlchemical.MOD_ID)
public class TechnoAlchemical {
  public static final String MOD_ID = "tec_alc_supreme";

  private static final Logger LOGGER = LogUtils.getLogger();

  public TechnoAlchemical(IEventBus modEventBus, ModContainer modContainer) {
    modEventBus.addListener(this::commonSetup);

    // Register ourselves for server and other game events we are interested in.
    // Note that this is necessary if and only if we want *this* class (ExampleMod)
    // to respond directly to events.
    // Do not add this line if there are no @SubscribeEvent-annotated functions in
    // this class, like onServerStarting() below.
    NeoForge.EVENT_BUS.register(this);

    CreativeTab.register(modEventBus);
    Items.register(modEventBus);
    Blocks.register(modEventBus);
    BlockEntities.register(modEventBus);
    DataComponentTypes.register(modEventBus);
    Sounds.register(modEventBus);

    // Recipes
    RecipeBookCategories.register(modEventBus);
    SlotDisplayTypes.register(modEventBus);
    RecipeDisplayTypes.register(modEventBus);
    RecipeTypes.register(modEventBus);
    RecipeSerializers.register(modEventBus);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {}

  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {}
}
