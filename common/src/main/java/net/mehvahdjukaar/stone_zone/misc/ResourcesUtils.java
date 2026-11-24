package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.Identifier;

public class ResourcesUtils {

    @SuppressWarnings("DataFlowIssue")
    public static <T extends BlockType> String getChildModelId(String childkey, T stoneType, Identifier blockId) {
        if (CompatSpritesHelper.CUSTOM_MODEL_ID.containsKey(blockId)) return CompatSpritesHelper.CUSTOM_MODEL_ID.get(blockId);

        return Utils.getID(stoneType.getBlockOfThis(childkey)).withPrefix("block/").toString();
    }
}
