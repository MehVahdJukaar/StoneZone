package net.mehvahdjukaar.stone_zone.modules.forge.create;

import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.decoration.palettes.LayeredBlock;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModuleAbstract;
import net.minecraft.world.level.block.Block;

//See CreateModuleAbstract's SUPPORTED VERSION
public class CreateModule extends CreateModuleAbstract {

    public CreateModule(String modId) {
        super(modId);
    }

    @Override
    public Block newConnectedPillarBlock(StoneType stoneType) {
        return new ConnectedPillarBlock(Utils.copyPropertySafe(stoneType.stone));
    }

    @Override
    public Block newLayeredBlock(StoneType stoneType) {
        return new LayeredBlock(Utils.copyPropertySafe(stoneType.stone));
    }
}