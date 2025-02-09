package net.mehvahdjukaar.stone_zone.common_classes;

import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CompatChestBlockEntity extends ChestBlockEntity {
    private final StoneType stoneType;
    private final boolean trapped;

    public CompatChestBlockEntity(BlockEntityType<?> arg, BlockPos pos, BlockState state) {
        super(arg, pos, state);
        StoneType stone = StoneTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.stoneType = stone == null ? StoneTypeRegistry.getStoneType() : stone;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
    }

    public StoneType getStoneType() {
        return stoneType;
    }

    @Override
    protected void signalOpenCount(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int eventId, int eventParam) {
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
