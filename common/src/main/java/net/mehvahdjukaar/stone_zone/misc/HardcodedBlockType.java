package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import org.jetbrains.annotations.Nullable;

public class HardcodedBlockType {

    public static String stoneidentify;
    public static String StoneTypeFromMod;
    public static String modId;
    public static String supportedBlockName;
    public static String shortenedIdenfity;

    @Nullable
    public static Boolean isStoneBlockAlreadyRegistered(String blockName, StoneType stoneType, String ModId, String shortenedId) {
        stoneidentify = stoneType.getId().toString();
        StoneTypeFromMod = stoneType.getNamespace();
        modId = ModId;
        supportedBlockName = blockName;
        shortenedIdenfity = shortenedId;

            /// ========== EXCLUDE ========== \\\
        // EXAMPLE
//        if (isStoneRegistryOf("create", "c", "create", "create:limestone", "limestone_pillar")) return true;

        // The StoneType's texture is only white and no way for blocks to copy its color behavior
        if (isStoneRegistryOf("", "", "", "rgbblocks:prismarine", "")) return true;

        // Create: Dreams & Desires' cut_stone_bricks shouldn't be detected but was
        if (isStoneRegistryOf("", "", "", "create_dd:cut_stone", "")) return true;

        // Stone Expansion's stone is based on Minecraft's stone and shouldn't be included
        if (isStoneRegistryOf("", "", "", "stoneexpansion:(cut|mossy|smooth|polished)_stone", "")) return true;

            /// ========== INCLUDE ========== \\\
        // EXAMPLE
//        if (isStoneRegistryOf("", "", "", "", "")) return false;


        return null;
    }

    public static Boolean isStoneRegistryOf(String whichSupportedModId, String shortenedId, String stonetypeFromMod, String stoneTypeId, String whichSupportedBlockName) {

        String[] expressions = {
                whichSupportedModId,
                shortenedId,
                stonetypeFromMod,
                stoneTypeId,
                whichSupportedBlockName
        };

        String[] values = {
                modId,
                shortenedIdenfity,
                StoneTypeFromMod,
                stoneidentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++ ) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean isNotMatched = !values[idx].matches(expressions[idx]);
                if (isNotMatched) return false;
            }
        }

        return true;
    }

}
