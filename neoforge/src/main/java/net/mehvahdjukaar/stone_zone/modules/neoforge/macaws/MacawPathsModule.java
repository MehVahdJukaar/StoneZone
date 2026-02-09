package net.mehvahdjukaar.stone_zone.modules.neoforge.macaws;

import com.mcwpaths.kikoz.objects.EngravedBlock;
import com.mcwpaths.kikoz.objects.PathBlock;
import net.mehvahdjukaar.stone_zone.modules.macaw.MacawPathsModuleAbstract;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


//See MacawPathsModuleAbstract's SUPPORTED VERSION
public class MacawPathsModule extends MacawPathsModuleAbstract {

    public MacawPathsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newPathBlock(BlockBehaviour.Properties properties) {
        return new PathBlock(properties);
    }

    @Override
    public Block newEngravedBlock(BlockBehaviour.Properties properties) {
        return new EngravedBlock(properties);
    }

    @Override
    public ResourceLocation getTab() {
        return modRes("pathsitemgroup");
    }

}
