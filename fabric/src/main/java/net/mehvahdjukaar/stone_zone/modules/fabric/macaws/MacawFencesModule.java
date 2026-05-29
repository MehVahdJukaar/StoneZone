package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import net.kikoz.mcwfences.objects.FenceHitbox;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawFencesModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

//See MacawFencesModuleAbstract's SUPPORTED VERSION
public class MacawFencesModule extends MacawFencesModuleAbstract {

    public MacawFencesModule(String modId) {
        super(modId);
    }

    @Override
    public Block newFenceBlock(StoneType stoneType) {
        return new FenceBlock(standardCopyProperties(stoneType));
    }

    @Override
    public Block newFenceGateBlock(StoneType stoneType) {
        return new FenceGateBlock(standardCopyProperties(stoneType), WoodType.OAK);
    }

    @Override
    public Block newFenceHitbox(StoneType stoneType) {
        return new FenceHitbox(standardCopyProperties(stoneType));
    }
}