package dev.exanc.technoalchemy.recipes.slotdisplays;

import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ForBlockStates<T> extends DisplayContentsFactory<T> {

  default T forState (Holder<Block> block) {
    return this.forState(block.value());
  }

  default T forState (Block block) {
    return this.forState(block.defaultBlockState());
  }

  T forState (BlockState state);
}