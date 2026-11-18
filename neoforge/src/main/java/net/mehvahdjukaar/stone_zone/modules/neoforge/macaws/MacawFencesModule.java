package net.mehvahdjukaar.stone_zone.modules.neoforge.macaws;

import com.mcwfences.kikoz.objects.FenceHitbox;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawFencesModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

//See MacawFencesModuleAbstract's SUPPORTED VERSION
public class MacawFencesModule extends MacawFencesModuleAbstract {

    public MacawFencesModule(String modId) {
        super(modId);
    }

    public Block newFenceBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new FenceBlock(properties);
    }

    @Override
    public Block newFenceGateBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new FenceGateBlock(WoodType.OAK, properties);
    }

    @Override
    public Block newFenceHitbox(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new FenceHitbox(properties);
    }

}