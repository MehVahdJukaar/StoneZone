package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static net.mehvahdjukaar.stone_zone.configs.UnsafeDisablerConfigs.*;

public class HardcodedBlockType {

    public static String stoneIdentify;
    public static String mudIdentify;
    public static String stoneTypeFromMod;
    public static String mudTypeFromMod;
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
            "nifty:concrete", "blocksyouneed_luna:bluestone", "blocksyouneed_luna:scorchcobble", "sullysmod:amber",
            "endergetic:eumus",
            //REASON: shouldn't be detected
            "desire:polished_stone", "desire:chiseled_stone", "create_dd:cut_stone",
            "stoneexpansion:cut_stone", "stoneexpansion:mossy_stone", "stoneexpansion:smooth_stone", "stoneexpansion:polished_stone",

            //REASON: The StoneType's texture is only white and no way for blocks to copy its color behavior
            "rgbblocks:prismarine"
    );

    @Nullable
    public static Boolean isStoneBlockAlreadyRegistered(String entrySetId, String blockName, StoneType stoneType, String ModId) {
        stoneIdentify = stoneType.getId().toString();
        stoneTypeFromMod = stoneType.getNamespace();
        modId = ModId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        // Include minecraft's PRISMARINE with Waystones
        if (isStoneFrom("waystones", "", "", "prismarine_waystone")) return false;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (stoneTypeList.get().stream().anyMatch(stoneIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (stoneType.isVanilla()) return true;

        // Exclude generated blocks that is just one mod that is both Supported Mods and StoneTypeFromMod
        if (isStoneFrom("quark", "quark", "", "pillar")) return true;
        if (isStoneFrom("create", "create", "", "pillar")) return true;
        if (isStoneFrom("decorative_blocks", "decorative_blocks", "", "pillar")) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // pillar from Decorative-Blocks, Quark, Create should be always generated
        if (isStoneFrom("quark|create|decorative_blocks", "", "", "pillar")) return false;

        // Create's cut wasn't generated due to Quark's cut_soul_sandstone
        if (isStoneFrom("create", "quark", "", "cut_soul_sandstone")) return false;

        // The stone_squares block from Blockus is why stone_squares from Rechiseled got skipped
        if (isStoneFrom("rechiseled", "blockus", "", "squares")) return false;

        // Create's blocks aren't generated for Quark, Wetland-Whimsy, Geologic-Expansion because they both have LIMESTONE & Also fix the tag issue (#64)
        if (isStoneFrom("create", "", "quark:limestone|wetland_whimsy:limestone|geologicexpansion:limestone", "")) return false;


        return null;
    }

    @Nullable
    public static Boolean isMudBlockAlreadyRegistered(String entrySetId, String blockName, MudType mudType, String ModId) {
        mudIdentify = mudType.getId().toString();
        mudTypeFromMod = mudType.getNamespace();
        modId = ModId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one MudType from a Stone mod
        if (mudTypeList.get().stream().anyMatch(mudIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


        return null;
    }

    public static Boolean isStoneFrom(String supportedModId, String stonetypeFromMod, String stoneTypeId, String supportedBlockId) {

        String[] expressions = {
                supportedModId,
                stonetypeFromMod,
                stoneTypeId,
                supportedBlockId
        };

        String[] values = {
                modId,
                stoneTypeFromMod,
                stoneIdentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++ ) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean mismatched = !(values[idx].matches(expressions[idx]) || values[idx].contains(expressions[idx]));
                if (mismatched) return false;
            }
        }

        return true;
    }

    public static Boolean isMudFrom(String supportedModId, String mudtypeFromMod, String mudTypeId, String supportedBlockId) {

        String[] expressions = {
                supportedModId,
                mudtypeFromMod,
                mudTypeId,
                supportedBlockId
        };

        String[] values = {
                modId,
                mudTypeFromMod,
                mudIdentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++ ) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean mismatched = !(values[idx].matches(expressions[idx]) || values[idx].contains(expressions[idx]));
                if (mismatched) return false;
            }
        }

        return true;
    }

}
