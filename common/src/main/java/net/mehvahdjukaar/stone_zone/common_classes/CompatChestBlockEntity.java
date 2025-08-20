package net.mehvahdjukaar.stone_zone.common_classes;

import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneTypeRegistry;
import net.minecraft.client.Minecraft;
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
    private float[] tint = null;

    public CompatChestBlockEntity(BlockEntityType<?> arg, BlockPos pos, BlockState state) {
        super(arg, pos, state);
        StoneType stone = StoneTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.stoneType = stone == null ? StoneTypeRegistry.getStoneType() : stone;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
    }

    public float[] getTint() {
        if (this.tint == null) {
            int color = Minecraft.getInstance().getBlockColors().getColor(stoneType.stone.defaultBlockState(), level, worldPosition, 0);
            this.tint = new float[]{
                    (color >> 16 & 255) / 255F,
                    (color >> 8 & 255) / 255F,
                    (color & 255) / 255F,
                    1.0F
            };
        }
        return this.tint;
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
