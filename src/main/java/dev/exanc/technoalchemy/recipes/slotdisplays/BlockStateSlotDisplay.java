package dev.exanc.technoalchemy.recipes.slotdisplays;

import java.util.stream.Stream;

import com.mojang.serialization.MapCodec;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory.ForStacks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record BlockStateSlotDisplay (BlockState state) implements SlotDisplay {

  public static final MapCodec<BlockStateSlotDisplay> CODEC = BlockState.CODEC
    .fieldOf("state")
    .xmap(BlockStateSlotDisplay::new, BlockStateSlotDisplay::state);

  public static final StreamCodec<RegistryFriendlyByteBuf, BlockStateSlotDisplay> STREAM_CODEC =
    StreamCodec.composite(
      ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY),
      BlockStateSlotDisplay::state,
      BlockStateSlotDisplay::new
    );

  @Override
  public <T> Stream<T> resolve (ContextMap context, DisplayContentsFactory<T> factory)
  {
    return switch (factory) {
      case ForBlockStates<T> states ->
        Stream.of(states.forState(this.state));

      case ForStacks<T> stacks ->
        Stream.of(stacks.forStack(this.state.getBlock().asItem()));

      default -> Stream.empty();
    };
  }

  @Override
  public Type<? extends SlotDisplay> type ()
  {
    return SlotDisplayTypes.BLOCK_STATE_SLOT_DISPLAY.get();
  }
}
