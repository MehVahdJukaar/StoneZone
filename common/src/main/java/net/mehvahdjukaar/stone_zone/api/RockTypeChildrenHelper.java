package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.stone_zone.api.set.RockType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RockTypeChildrenHelper {

    public static <T extends RockType> BlockState copyParentBlockStateFromMap(java.util.Map<T, Block> block, T rockType) {
        return block.get(rockType).defaultBlockState();
    }
}
