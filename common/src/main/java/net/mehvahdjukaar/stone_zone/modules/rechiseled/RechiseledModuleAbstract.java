package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

///SUPPORT: v1.2.4+
public abstract class RechiseledModuleAbstract extends StoneZoneModule {

    public final ResourceLocation tab = modRes(getModId());

    public RechiseledModuleAbstract(String modId) {
        super(modId, "rcd");
    }

    public Block getParentBlock(StoneType stoneType, String path) {
        return stoneType.getBlockOfThis(getModId() + ":" + path);
    }
}
