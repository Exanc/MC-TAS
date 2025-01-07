package dev.exanc.technoalchemy.recipes.slotdisplays;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BlockStateStackContentFactory implements ForBlockStates<ItemStack> {

  public static final BlockStateStackContentFactory INSTANCE = new BlockStateStackContentFactory();

  private BlockStateStackContentFactory () {}

  @Override
  public ItemStack forState (BlockState state)
  {
    return new ItemStack(state.getBlock()); 
  }
}
