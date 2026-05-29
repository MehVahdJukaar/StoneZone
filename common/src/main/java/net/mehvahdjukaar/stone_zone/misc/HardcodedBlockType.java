package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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
            //REASON: shouldn't be detected
            "immersive_weathering", "chipped", "create_confectionery", "rgbblocks",

            //REASON: It has a tinted StoneType but impossible to support because it has tintedIndex where color changes based on coordinates
            "opalescence"
    );

    public static final Set<String> BLACKLISTED_STONETYPES = Set.of(
            //REASON: is a terracotta
            "quark:shingles",

            //REASON: not a stonetype
            "outer_end:himmel", "quark:midori", "twigs:silt", "supplementaries:ash", "blue_skies:brumble",
            "nifty:concrete", "blocksyouneed_luna:bluestone", "blocksyouneed_luna:scorchcobble", "sullysmod:amber",
            "endergetic:eumus", "minecraft:mud", "enlightened_end:chorloam",

            //REASON: shouldn't be detected
            "desire:polished_stone", "desire:chiseled_stone", "create_dd:cut_stone",
            "stoneexpansion:cut_stone", "stoneexpansion:mossy_stone", "stoneexpansion:smooth_stone", "stoneexpansion:polished_stone",
            "minecraft:infested_stone", "ars_nouveau:sconce",

            //REASON: The StoneType's texture is only white and no way for blocks to copy its color behavior
            "rgbblocks:prismarine"
    );

    private static final List<String> FRAMED_BLOCKS_SUFFIX = List.of(

            // Slabs
            "slab", "slab_edge", "slab_corner",
            "divided_slab", "adj_double_slab", "adj_double_copycat_slab",
            "centered_slab", "pyramid_slab", "checkered_slab",

            // Stairs
            "stairs", "double_stairs", "half_stairs",
            "divided_stairs", "double_half_stairs", "sliced_stairs_panel",
            "vertical_stairs", "vertical_double_stairs", "vertical_half_stairs",
            "vertical_divided_stairs", "vertical_double_half_stairs", "vertical_sliced_stairs",
            "sliced_stairs_slab", "vertical_sloped_stairs",

            // Walls
            "wall", "floor_board", "wall_board",

            // Fences & Gates
            "fence", "fence_gate", "gate", "iron_gate",

            // Panels
            "panel", "divided_panel_horizontal", "divided_panel_vertical", "centered_panel",

            // Pillars & Posts
            "pillar", "half_pillar", "post",
            "corner_pillar", "threeway_corner_pillar", "double_threeway_corner_pillar",

            // Doors & Trapdoors
            "door", "trapdoor",

            // Buttons, Levers & Plates
            "pressure_plate", "large_button", "lever",

            // Torches
            "torch", "soul_torch", "redstone_torch",
            "wall_torch", "soul_wall_torch", "redstone_wall_torch",

            // Chests & Storage
            "chest", "secret_storage",

            // Misc blocks
            "cube", "bouncy_cube", "glowing_cube",
            "pyramid", "bookshelf", "chiseled_bookshelf",
            "flower_pot", "item_frame", "glowing_item_frame",
            "ladder", "bars", "pane",
            "horizontal_pane"
    );

    @Nullable
    public static Boolean isStoneBlockAlreadyRegistered(String entrySetId, String blockName, StoneType stoneType, String ModId) {
        stoneIdentify = stoneType.getId().toString();
        stoneTypeFromMod = stoneType.getNamespace();
        modId = ModId;
        supportedBlockName = blockName;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ SPECIAL EXCLUSION ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (stoneTypeList.get().stream().anyMatch(stoneIdentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        // Excude Supported-Mods' blocks that are similar to blocks from Framed-Blocks
        if (PlatHelper.isModLoaded("framedblocks") && FRAMED_BLOCKS_SUFFIX.stream().anyMatch(suffix -> supportedBlockName.contains(suffix))) return true;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        // Include minecraft's PRISMARINE with Waystones
        if (isStoneFrom("waystones", "", "", "prismarine_waystone")) return false;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude all of Vanilla Types
        if (isKnownVanillaStone(stoneType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Ensure all of Create's Supported-Block With any Stone Mod is generated
        // Create's blocks aren't generated for Quark, Wetland-Whimsy, Geologic-Expansion, TerraFirmaCraft because they both have LIMESTONE & Also fix the tag issue (#64)
        if (isStoneFrom("create", "", "", "")) return false;

        // Architect's-Palette has MOONSHALE_FLAGSTONE that prevent the similar block from Macaw's-Paths-&-Pavings from being generated
        if (isStoneFrom("mcwpaths", "", "", "moonshale_flagstone")) return false;

        // pillar from Decorative-Blocks & Quark should be always generated
        if (isStoneFrom("quark|decorative_blocks", "", "", "pillar")) return false;

        // Create's cut wasn't generated due to Quark's cut_soul_sandstone
        if (isStoneFrom("create", "quark", "", "cut_soul_sandstone")) return false;

        // The stone_squares block from Blockus is why stone_squares from Rechiseled got skipped
        if (isStoneFrom("rechiseled", "blockus", "", "squares")) return false;

        // Ensure blocks to be generated because TerraFirmaCraft has similar name of Vanilla StoneType (andesite, granite, diorite, so on...)
        if (isStoneFrom("", "tfc", "", "")) return false;

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

        // Exclude all of Vanilla Types
        if (isKnownVanillaMud(mudType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━


        return null;
    }

    public static Boolean isStoneFrom(String supportedModId, String stonetypeFromMod, String stoneTypeId, String supportedBlockId) {
        // Excluding blocks from a mod that are both supported-Mod and Stone-Mod
        if (stoneTypeFromMod.matches(modId)) return false;

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

    //for mods that might add in vanilla namespace
    // StoneType
    public static boolean isKnownVanillaStone(StoneType stoneType){
        var id = stoneType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_STONES.contains(id.getPath());
        }
        return false;
    }

    private static final Set<String> VANILLA_STONES = Set.of(
            "stone", "andesite", "granite", "diorite", "tuff", "calcite", "blackstone", "sandstone",
            "basalt", "deepslate", "prismarine", "nether", "end_stone"
    );

    // MudType
    public static boolean isKnownVanillaMud(MudType mudType){
        var id = mudType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_MUDS.contains(id.getPath());
        }
        return false;
    }

    private static final Set<String> VANILLA_MUDS = Set.of(
            "mud"
    );

}
