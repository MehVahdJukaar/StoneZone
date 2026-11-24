package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import net.kikoz.mcwroofs.objects.roofs.*;
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
        return new BaseRoof(stoneType.stone.defaultBlockState(), properties);
    }

    @Override
    public Block newRoofGlass(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new RoofGlass(properties);
    }

    @Override
    public Block newRoofTopNew(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new RoofTopNew(properties);
    }

    @Override
    public Block newSteepRoof(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new SteepRoof(stoneType.stone.defaultBlockState(), properties);
    }

    @Override
    public Block newLower(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Lower(stoneType.stone.defaultBlockState(), properties);
    }

    @Override
    public Block newSteep(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Steep(stoneType.stone.defaultBlockState(), properties);
    }

}
