package net.mehvahdjukaar.stone_zone.modules.neoforge.macaws;

import com.mcwwindows.kikoz.objects.*;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawWindowsModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


//See MacawWIndowsModuleAbstract's SUPPORTED VERSION
public class MacawWindowsModule extends MacawWindowsModuleAbstract {

    public MacawWindowsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newConnectedWindow(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new ConnectedWindow(properties);
    }

    @Override
    public Block newWindowBarred(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new WindowBarred(properties);
    }

    @Override
    public Block newGothicWindow(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new GothicWindow(properties);
    }

    @Override
    public Block newArrowSill(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new ArrowSill(properties);
    }

    @Override
    public Block newWindow(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new Window(properties);
    }

}
