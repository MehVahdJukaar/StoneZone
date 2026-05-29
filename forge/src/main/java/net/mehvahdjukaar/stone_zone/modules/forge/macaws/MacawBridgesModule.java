package net.mehvahdjukaar.stone_zone.modules.forge.macaws;

import com.mcwbridges.kikoz.objects.Bridge_Block;
import com.mcwbridges.kikoz.objects.Bridge_Stairs;
import com.mcwbridges.kikoz.objects.Bridge_Support;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawBridgesModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


//See MacawBridgesModuleAbstract's SUPPORTED VERSION
public class MacawBridgesModule extends MacawBridgesModuleAbstract {

    public MacawBridgesModule(String modId) {
        super(modId);
    }

    @Override
    public Block newBridge_Block(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Bridge_Block(properties);
    }

    @Override
    public Block newBridge_Support(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Bridge_Support(properties);
    }

    @Override
    public Block newBridge_Stairs(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Bridge_Stairs(properties);
    }
}