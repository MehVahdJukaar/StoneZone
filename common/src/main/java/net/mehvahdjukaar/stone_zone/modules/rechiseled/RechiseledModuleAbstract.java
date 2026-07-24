package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

///SUPPORT: v1.2.5+
public abstract class RechiseledModuleAbstract extends StoneZoneModule {

    public final Supplier<CreativeModeTab> tab = getModTab(getModId());

    public RechiseledModuleAbstract(String modId) {
        super(modId, "rcd");
    }

    public Block getParentBlock(StoneType stoneType, String path) {
        return stoneType.getBlockOfThis(getModId() + ":" + path);
    }
}
