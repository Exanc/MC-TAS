package dev.exanc.technoalchemy.recipes.slotdisplays;

import net.minecraft.world.level.block.state.BlockState;

public class BlockStateContentsFactory implements ForBlockStates<BlockState> {

  public static final BlockStateContentsFactory INSTANCE = new BlockStateContentsFactory();

  private BlockStateContentsFactory () {}

  @Override
  public BlockState forState (BlockState state)
  {
    return state;
  }
}
