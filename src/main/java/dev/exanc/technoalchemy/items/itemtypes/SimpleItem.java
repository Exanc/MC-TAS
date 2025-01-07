package dev.exanc.technoalchemy.items.itemtypes;

import java.util.List;

import dev.exanc.technoalchemy.items.TechnoAlchemicalItem;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SimpleItem extends TechnoAlchemicalItem {
  
  public SimpleItem (String id, Properties properties) {
    super(id, properties);
  }

  @Override
  public void getModel (ItemModelGenerators generators)
  {
    generators.generateFlatItem(this, ModelTemplates.FLAT_ITEM);
  }

  @Override
  public List<TagKey<Item>> getTags ()
  {
    return List.of();
  }
}
