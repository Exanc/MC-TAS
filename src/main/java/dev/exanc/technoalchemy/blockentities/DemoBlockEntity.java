package dev.exanc.technoalchemy.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DemoBlockEntity /* extends BlockEntity */ {

  /*private int value;

  public DemoBlockEntity (BlockPos pos, BlockState state) {
    super(BlockEntities.DEMO_BLOCK_ENTITY.get(), pos, state);
  }

  public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T block)
  {
    // tick...
  }

  @Override
  protected void loadAdditional (CompoundTag tag, Provider registries)
  {
    super.loadAdditional(tag, registries);
    this.value = tag.getInt("value");
  }

  @Override
  protected void saveAdditional (CompoundTag tag, Provider registries)
  {
    super.saveAdditional(tag, registries);
    tag.putInt("value", this.value);
  }

  @Override
  public CompoundTag getUpdateTag (Provider registries)
  {
    CompoundTag tag = new CompoundTag();
    this.saveAdditional(tag, registries);
    return tag;
  }

  // Handle a received update tag here. The default implementation calls #loadAdditional here,
  // so you do not need to override this method if you don't plan to do anything beyond that.
  @Override
  public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
      super.handleUpdateTag(tag, registries);
  }

  // Return our packet here. This method returning a non-null result tells the game to use this packet for syncing.
  @Override
  public Packet<ClientGamePacketListener> getUpdatePacket() {
    // The packet uses the CompoundTag returned by #getUpdateTag. An alternative overload of #create exists
    // that allows you to specify a custom update tag, including the ability to omit data the client might not need.
    return ClientboundBlockEntityDataPacket.create(this);
  }

  // Optionally: Run some custom logic when the packet is received.
  // The super/default implementation forwards to #loadAdditional.
  @Override
  public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
    super.onDataPacket(connection, packet, registries);
    // Do whatever you need to do here.
  }*/
}
