package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import com.mcwstairs.kikoz.objects.BalconyRailing;
import com.mcwstairs.kikoz.objects.StairPlatform;
import com.mcwstairs.kikoz.objects.StairRailing;
import com.mcwstairs.kikoz.objects.stair_types.*;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawStairsModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

//See MacawStairsModuleAbstract's SUPPORTED VERSION
public class MacawStairsModule extends MacawStairsModuleAbstract {

    public MacawStairsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newTerraceStairs(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new TerraceStairs(standardProperties(stoneType));
    }

    @Override
    public Block newSkylineStairs(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new SkylineStairs(standardProperties(stoneType));
    }

    @Override
    public Block newCompactStairs(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new CompactStairs(standardProperties(stoneType));
    }

    @Override
    public Block newBulkStairs(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new BulkStairs(standardProperties(stoneType));
    }

    @Override
    public Block newLoftStairs(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new LoftStairs(standardProperties(stoneType));
    }

    @Override
    public Block newBalconyRailing(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new BalconyRailing(standardProperties(stoneType));
    }

    @Override
    public Block newStairRailing(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new StairRailing(standardProperties(stoneType));
    }

    @Override
    public Block newStairPlatform(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new StairPlatform(standardProperties(stoneType));
    }
}
