package net.mehvahdjukaar.stone_zone.modules.neoforge.macaws;

import com.mcwroofs.kikoz.objects.roofs.*;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawRoofsModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


//See MacawRoofsModuleAbstract's SUPPORTED VERSION
public class MacawRoofsModule extends MacawRoofsModuleAbstract {

    public MacawRoofsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newBaseRoof(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new BaseRoof(stoneType.stone.defaultBlockState(), standardProperties(stoneType));
    }

    @Override
    public Block newRoofGlass(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new RoofGlass(standardProperties(stoneType));
    }

    @Override
    public Block newRoofTopNew(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new RoofTopNew(standardProperties(stoneType));
    }

    @Override
    public Block newSteepRoof(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new SteepRoof(stoneType.stone.defaultBlockState(), standardProperties(stoneType));
    }

    @Override
    public Block newLower(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Lower(stoneType.stone.defaultBlockState(), standardProperties(stoneType));
    }

    @Override
    public Block newSteep(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Steep(stoneType.stone.defaultBlockState(), standardProperties(stoneType));
    }

}
