package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class HardcodedBlockType {

    public static String stoneidentify;
    public static String StoneTypeFromMod;
    public static String modId;
    public static String supportedBlockName;

    public static final Set<String> BLACKLISTED_MODS = Set.of(
            "immersive_weathering", "chipped", "create_confectionery", "rgbblocks"
    );

    public static final Set<String> BLACKLISTED_STONETYPES = Set.of(
            //REASON: is a terracotta
            "quark:shingles",
            //REASON: not a stonetype
            "outer_end:himmel", "quark:midori", "twigs:silt", "supplementaries:ash", "blue_skies:brumble",
            "nifty:concrete", "blocksyouneed_luna:bluestone", "blocksyouneed_luna:scorchcobble",
            //REASON: shouldn't be detected
            "desire:polished_stone", "desire:chiseled_stone", "create_dd:cut_stone",

            //REASON: The StoneType's texture is only white and no way for blocks to copy its color behavior
            "rgbblocks:prismarine"
    );

    @Nullable
    public static Boolean isStoneBlockAlreadyRegistered(String blockName, StoneType stoneType, String ModId) {
        stoneidentify = stoneType.getId().toString();
        StoneTypeFromMod = stoneType.getNamespace();
        modId = ModId;
        supportedBlockName = blockName;

            /// ========== INCLUDE VANILLA TYPE ========== \\\
        // Include minecraft's PRISMARINE with Waystones
        if (isStoneFrom("waystones", "", "", "prismarine_waystone")) return false;

            /// ========== EXCLUDE ========== \\\
        // Exclude all of Vanilla Types
        if (stoneType.isVanilla()) return true;

        // Stone Expansion's stone is based on Minecraft's stone and shouldn't be included
        if (isStoneFrom("", "", "stoneexpansion:(cut|mossy|smooth|polished)_stone", "")) return true;

            /// ========== INCLUDE ========== \\\
        // EXAMPLE
//        if (isStoneRegistryOf("create", "c", "create", "create:limestone", "limestone_pillar")) return false;

        // Create's cut wasn't generated due to Quark's cut_soul_sandstone
        if (isStoneFrom("create", "quark", "", "cut_soul_sandstone")) return false;

        // The stone_squares block from Blockus is why stone_squares from Rechiseled got skipped
        if (isStoneFrom("rechiseled", "blockus", "", "squares")) return false;

        // Create's blocks aren't generated for Quark, Wetland-Whimsy, Geologic-Expansion because they both have LIMESTONE & Also fix the tag issue (#64)
        if (isStoneFrom("create", "", "quark:limestone|wetland_whimsy:limestone|geologicexpansion:limestone", "")) return false;


        return null;
    }

    public static Boolean isStoneFrom(String supportedModId, String stonetypeFromMod, String stoneTypeId, String whichSupportedBlockName) {

        String[] expressions = {
                supportedModId,
                stonetypeFromMod,
                stoneTypeId,
                whichSupportedBlockName
        };

        String[] values = {
                modId,
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
