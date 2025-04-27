package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class HardcodedBlockType {

    public static String stoneidentify;
    public static String StoneTypeFromMod;
    public static String modId;
    public static String supportedBlockName;
    public static String shortenedIdenfity;

    public static final Set<String> BLACKLISTED_MODS = Set.of(
            "immersive_weathering", "chipped", "create_confectionery"
    );

    public static final Set<String> BLACKLISTED_STONETYPES = Set.of(
            "quark:shingles", //REASON: is a terracotta
            //REASON: not a stonetype
            "outer_end:himmel", "quark:midori", "twigs:silt", "supplementaries:ash",
            //REASON: Shouldn't be detected
            "minecraft:cobblestone"
    );

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

        // Exclude all of Vanilla Types
        if (stoneType.isVanilla()) return true;

        // The StoneType's texture is only white and no way for blocks to copy its color behavior
        if (isStoneFrom("", "", "", "rgbblocks:prismarine", "")) return true;

        // Create: Dreams & Desires' cut_stone_bricks shouldn't be detected but was
        if (isStoneFrom("", "", "", "create_dd:cut_stone", "")) return true;

        // Stone Expansion's stone is based on Minecraft's stone and shouldn't be included
        if (isStoneFrom("", "", "", "stoneexpansion:(cut|mossy|smooth|polished)_stone", "")) return true;

            /// ========== INCLUDE ========== \\\
        // EXAMPLE
//        if (isStoneRegistryOf("create", "c", "create", "create:limestone", "limestone_pillar")) return false;

        // Create's cut wasn't generated due to Quark's cut_soul_sandstone
        if (isStoneFrom("create", "", "quark", "", "cut_soul_sandstone")) return false;

        // The stone_squares block from Blockus is why stone_squares from Rechiseled got skipped
        if (isStoneFrom("rechiseled", "", "blockus", "", "squares")) return false;

        // Create's blocks aren't generated for Quark, Wetland-Whimsy, Geologic-Expansion because they both have LIMESTONE & Also fix the tag issue (#64)
        if (isStoneFrom("create", "", "", "quark:limestone|wetland_whimsy:limestone|geologicexpansion:limestone", "")) return false;


        return null;
    }

    public static Boolean isStoneFrom(String whichSupportedModId, String shortenedId, String stonetypeFromMod, String stoneTypeId, String whichSupportedBlockName) {

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
                boolean isNotMatched = !(values[idx].matches(expressions[idx]) | values[idx].contains(expressions[idx]));
                if (isNotMatched) return false;
            }
        }

        return true;
    }

}
