package dev.exanc.technoalchemy.event;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEvents {
  
  @EventBusSubscriber(modid = TechnoAlchemical.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value =  Dist.CLIENT)
  public class ModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      //CustomItemProperties.register();
    }
  }

  //@EventBusSubscriber(modid = TechnoAlchemical.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value =  Dist.CLIENT)
  public class GameEvents {}
}