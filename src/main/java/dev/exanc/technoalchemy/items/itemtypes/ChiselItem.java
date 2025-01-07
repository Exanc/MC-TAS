package dev.exanc.technoalchemy.items.itemtypes;

import java.util.List;
import java.util.Map;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.component.DataComponentTypes;
import dev.exanc.technoalchemy.items.TechnoAlchemicalItem;
import dev.exanc.technoalchemy.sound.Sounds;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ChiselItem extends TechnoAlchemicalItem {

  private static final Map<Block, Block> CHISEL_MAP = Map.of(
    Blocks.STONE, Blocks.STONE_BRICKS,
    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
    Blocks.END_STONE, Blocks.END_STONE_BRICKS
  );

  public ChiselItem (String id, Properties properties) {
    super(
      id,
      properties.durability(100)
    );
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

    if (!level.isClientSide() && CHISEL_MAP.containsKey(clickedBlock)) {

      level.setBlockAndUpdate(
        context.getClickedPos(),
        CHISEL_MAP.get(clickedBlock).defaultBlockState()
      );

      context.getItemInHand()
        .hurtAndBreak(
          1,
          ((ServerLevel) level),
          context.getPlayer(),
          item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
        );

      level.playSound(null, context.getClickedPos(), Sounds.CHISEL_USE.get(), SoundSource.BLOCKS);

      context.getItemInHand().set(DataComponentTypes.COORDINATES, context.getClickedPos());
    }
    
    return super.useOn(context);
  }

  @Override
  public void appendHoverText(
    ItemStack stack,
    TooltipContext context,
    List<Component> tooltipComponents,
    TooltipFlag tooltipFlag
  ) {

    if (Screen.hasShiftDown()) {
      tooltipComponents.add(Component.translatable("tooltip."+TechnoAlchemical.MOD_ID+".chisel.long"));

      if (stack.get(DataComponentTypes.COORDINATES) != null) {
        tooltipComponents.add(Component.empty());
        tooltipComponents.add(Component.keybind("Last block changed at " + stack.get(DataComponentTypes.COORDINATES)));
      }
    } else {
      tooltipComponents.add(Component.translatable("tooltip."+TechnoAlchemical.MOD_ID+".chisel.short"));
    }

    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }

  @Override
  public void getModel (ItemModelGenerators generators)
  {
    ResourceLocation CHISEL = generators.createFlatItemModel(this, ModelTemplates.FLAT_ITEM);
    ResourceLocation CHISEL_USED = generators.createFlatItemModel(this, "_used", ModelTemplates.FLAT_ITEM);

    generators.generateBooleanDispatch(
      this,
      ItemModelUtils.hasComponent(DataComponentTypes.COORDINATES.get()),
      ItemModelUtils.plainModel(CHISEL),
      ItemModelUtils.plainModel(CHISEL_USED)
    );

    /*provider.getBuilder("chisel")
      .parent(new ModelFile.UncheckedModelFile("item/generated"))
      .texture("layer0", ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "item/" + "chisel"))
      .override()
        .predicate(ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "used"), 1)
        .model(new ModelFile.UncheckedModelFile(
          ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "item/" + "chisel_used")
        ))
        .end();

    provider.getBuilder("chisel_used")
      .parent(new ModelFile.UncheckedModelFile("item/generated"))
      .texture("layer0", ResourceLocation.fromNamespaceAndPath(TechnoAlchemical.MOD_ID, "item/" + "chisel_used"));*/
  }

  @Override
  public List<TagKey<Item>> getTags ()
  {
    return List.of();
  }
}
