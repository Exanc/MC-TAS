package dev.exanc.technoalchemy.items;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public abstract class TechnoAlchemicalItem extends Item {

  public TechnoAlchemicalItem (String id, Properties properties) {
    super(
      properties.setId(ResourceKey.create(
        Registries.ITEM,
        ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, id)
      ))
    );
  }

  public abstract void getModel (ItemModelGenerators generators);
  public abstract List<TagKey<Item>> getTags ();
}
