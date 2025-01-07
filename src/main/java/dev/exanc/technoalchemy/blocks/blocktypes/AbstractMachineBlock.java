package dev.exanc.technoalchemy.blocks.blocktypes;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

public abstract class AbstractMachineBlock extends AbstractEntityBlock {

  public static final EnumProperty<Direction> PROPERTY_FACING = HorizontalDirectionalBlock.FACING;

  protected AbstractMachineBlock (String id, Properties properties) {
    super(id, properties);

    this.registerDefaultState(
      this.stateDefinition
        .any()
        .setValue(PROPERTY_FACING, Direction.NORTH)
    );
  }

  protected abstract MapCodec<? extends AbstractMachineBlock> codec();

  @Override
  protected InteractionResult useWithoutItem (
    BlockState state,
    Level level,
    BlockPos pos,
    Player player,
    BlockHitResult hitResult
  ) {
    if (level.isClientSide) {
      return InteractionResult.SUCCESS;
    }
    this.openContainer(level, pos, player);
    return InteractionResult.CONSUME;
  }

  protected abstract void openContainer(Level level, BlockPos pos, Player player);

  @Override
  public BlockState getStateForPlacement (BlockPlaceContext context)
  {
    return this.defaultBlockState()
      .setValue(PROPERTY_FACING, context.getHorizontalDirection().getOpposite());
  }

  @Override
  protected void onRemove (
    BlockState state,
    Level level,
    BlockPos pos,
    BlockState newState,
    boolean movedByPiston
  ) {
    /*if (!state.is(newState.getBlock()))
    {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof AbstractMachineBlockEntity abstractMachineBlockEntity) {

        if (level instanceof ServerLevel serverLevel) {
          Containers.dropContents(serverLevel, pos, abstractMachineBlockEntity);
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
        level.updateNeighbourForOutputSignal(pos, this);
      } else {
        super.onRemove(state, level, pos, newState, movedByPiston);
      }
    }*/
  }

  @Override
  protected RenderShape getRenderShape (BlockState state)
  {
    return RenderShape.MODEL;
  }

  @Override
  protected BlockState rotate (BlockState state, Rotation rotation)
  {
    return state.setValue(
      PROPERTY_FACING,
      rotation.rotate(state.getValue(PROPERTY_FACING))
    );
  }

  @Override
  protected BlockState mirror (BlockState state, Mirror mirror)
  {
    return this.rotate(
      state,
      mirror.getRotation(state.getValue(PROPERTY_FACING))
    );
  }


  // TODO: Is it necessary ?
  @Override
  protected void createBlockStateDefinition (Builder<Block, BlockState> builder)
  {
    builder.add(new Property[]{PROPERTY_FACING});
  }

  /*@Nullable
  protected static <T extends BlockEntity> BlockEntityTicker<T> createAbstractMachineTicker (
    Level level,
    BlockEntityType<T> serverType,
    BlockEntityType<? extends AbstractMachineBlockEntity> clientType
  ) {
    if (level.isClientSide) {
      return null;
    }
    return createTickerHelper(serverType, clientType, AbstractMachineBlockEntity::serverTick);
  }*/
}
