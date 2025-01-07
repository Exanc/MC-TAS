package dev.exanc.technoalchemy.blocks.blocktypes;

import java.util.List;

import dev.exanc.technoalchemy.TechnoAlchemical;
import dev.exanc.technoalchemy.items.Items;
import dev.exanc.technoalchemy.sound.Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends SimpleBlock {

  public MagicBlock(String id) {
    super(
      id,
      Block.Properties.of()
        .strength(1)
        .sound(Sounds.MAGIC_BLOCK_SOUNDS)
    );
  }

  @Override
  protected InteractionResult useWithoutItem(
    BlockState state,
    Level level,
    BlockPos pos,
    Player player,
    BlockHitResult hitResult
  ) {
    level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS);

    return super.useWithoutItem(state, level, pos, player, hitResult);
  }

  @Override
  public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
    
    if (entity instanceof ItemEntity itemEntity) {

      ItemStack currenStack = itemEntity.getItem();

      if (currenStack.getItem() == Items.RAW_BISMUTH.get()) {
        itemEntity.setItem(new ItemStack(
          net.minecraft.world.item.Items.DIAMOND, currenStack.getCount()
        ));
      }
    }

    super.stepOn(level, pos, state, entity);
  }

  @Override
  public void appendHoverText(
    ItemStack stack,
    TooltipContext context,
    List<Component> tooltipComponents,
    TooltipFlag tooltipFlag
  ) {

    tooltipComponents.add(Component.translatable("tooltip."+TechnoAlchemical.MOD_ID+".magic_block"));

    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
  }
}
