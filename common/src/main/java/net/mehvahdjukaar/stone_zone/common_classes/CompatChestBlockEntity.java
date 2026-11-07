package net.mehvahdjukaar.stone_zone.common_classes;

import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class CompatChestBlockEntity extends ChestBlockEntity {
    private final StoneType stoneType;
    private final boolean trapped;

    public CompatChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
        StoneType stoneType = StoneTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.stoneType = stoneType == null ? VanillaStoneTypes.STONE : stoneType;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
    }

    public StoneType getStoneType() {
        return stoneType;
    }

    @Override
    protected void signalOpenCount(@NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        if (trapped && eventId != eventParam) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }

    public boolean isTrapped() {
        return trapped;
    }

}
