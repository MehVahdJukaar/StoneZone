package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;

public class ResourcesUtils {

    @SuppressWarnings("DataFlowIssue")
    public static <T extends BlockType> String getChildModelId(String childkey, T stoneType, ResourceLocation blockId) {
        if (CompatSpritesHelper.modelID.containsKey(blockId)) return CompatSpritesHelper.modelID.get(blockId);

        return Utils.getID(stoneType.getBlockOfThis(childkey)).withPrefix("block/").toString();
    }
}
