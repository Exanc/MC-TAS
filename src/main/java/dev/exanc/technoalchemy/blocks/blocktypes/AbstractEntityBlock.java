package dev.exanc.technoalchemy.blocks.blocktypes;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import dev.exanc.technoalchemy.blocks.TechnoAlchemicalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractEntityBlock
  extends TechnoAlchemicalBlock
  implements EntityBlock
{
  protected AbstractEntityBlock (String id, Properties properties) {
    super(id, properties);
  }

  protected abstract MapCodec<? extends AbstractEntityBlock> codec();

  @Override
  protected RenderShape getRenderShape (BlockState state)
  {
    return RenderShape.INVISIBLE;
  }

  @Override
  protected boolean triggerEvent (
    BlockState state,
    Level level,
    BlockPos pos,
    int id,
    int param
  ) {
    super.triggerEvent(state, level, pos, id, param);

    BlockEntity blockEntity = level.getBlockEntity(pos);
    if (blockEntity == null) {
      return false;
    }
    return blockEntity.triggerEvent(id, param);
  }

  @Override
  @Nullable
  protected MenuProvider getMenuProvider (
    BlockState state,
    Level level,
    BlockPos pos
  ) {
    BlockEntity blockEntity = level.getBlockEntity(pos);
    if (blockEntity instanceof MenuProvider provider) {
      return provider;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper (
    BlockEntityType<A> serverType,
    BlockEntityType<E> clientType,
    BlockEntityTicker<? super E> ticker
  ) {
    return clientType == serverType ? (BlockEntityTicker<A>) ticker : null;
  }
}
