package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static net.mehvahdjukaar.stone_zone.configs.UnsafeDisablerConfigs.*;

public class HardcodedBlockType {

    public static String stoneFullId;
    public static String mudIdentify;
    public static String stoneTypeNamespace;
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

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                        StoneType                         │
    //      └──────────────────────────────────────────────────────────┘
    @Nullable
    public static Boolean isStoneBlockAlreadyRegistered(String entrySetId, String blockName, StoneType stoneType, String supportedModId) {
        stoneFullId = stoneType.getId().toString();
        stoneTypeNamespace = stoneType.getNamespace();

        PendingBlockInfo pendingInfo = PendingBlockInfo.of(stoneTypeNamespace, stoneFullId, blockName, supportedModId);

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ SPECIAL EXCLUSION ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one StoneType from a Stone mod
        if (stoneTypeList.get().stream().anyMatch(stoneFullId::matches)) return true;

        // Exclude one EntrySet from a module
        if (entrySetList.get().stream().anyMatch(entrySetId::matches)) return true;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        // Include minecraft's PRISMARINE with Waystones
        if (pendingInfo.isSupportedModId("waystones") && pendingInfo.isBlockName("prismarine_waystone")) return false;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude all of Vanilla Types
        if (isKnownVanillaStone(stoneType)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Ensure Rechiseled's TILES get generated blc one case with Deeper-Darker already having TILES prevent it from being generated (there may be more mods)
        if (pendingInfo.isSupportedModId("rechiseled") && pendingInfo.isBlockName("\\w+_tiles")) return false;

        // pillar from Decorative-Blocks, Quark, Create should be always generated
        if (pendingInfo.isSupportedModId("quark|create|decorative_blocks") && pendingInfo.isBlockName("pillar")) return false;

        // Create's cut wasn't generated due to Quark's cut_soul_sandstone
        if (pendingInfo.isSupportedModId("create") && pendingInfo.isTypeNamespace("quark") && pendingInfo.isBlockName("cut_soul_sandstone")) return false;

        // The stone_squares block from Blockus is why stone_squares from Rechiseled got skipped
        if (pendingInfo.isSupportedModId("rechiseled") && pendingInfo.isTypeNamespace("blockus") && pendingInfo.isBlockName("squares")) return false;

        // Create's blocks aren't generated for Quark, Wetland-Whimsy, Geologic-Expansion, TerraFirmaCraft because they both have LIMESTONE & Also fix the tag issue (#64)
        if (pendingInfo.isSupportedModId("create") && pendingInfo.isTypeFullId("quark:limestone|wetland_whimsy:limestone|geologicexpansion:limestone|tfc:limestone")) return false;

        // Ensure blocks to be generated because TerraFirmaCraft has similar name of Vanilla StoneType (andesite, granite, diorite, so on...)
        if (pendingInfo.isTypeNamespace("tfc")) return false;

        return null;
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         MudType                          │
    //      └──────────────────────────────────────────────────────────┘
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

    /**
     * @param modThatTheBlockIsFrom Id of mod that supported block is from
     * @param TypeNamespace The mod that new BlockType is from
     * @param TypeFullId Full Id of BlockType (StoneType, MudType)
     * @param blockName Gems Realm's Id of block
     */
    protected record PendingBlockInfo(String TypeNamespace, String TypeFullId, String blockName, String modThatTheBlockIsFrom) {

        public static PendingBlockInfo of(String typeNamespace, String typeFullId, String blockName, String modThatTheBlockIsFrom) {
            return new PendingBlockInfo(typeNamespace, typeFullId, blockName, modThatTheBlockIsFrom);
        }

        /// Id of mod that supported-block is from
        public boolean isSupportedModId(String modId) {
            return this.modThatTheBlockIsFrom.matches(modId);
        }

        /// The mod that new BlockType is from
        public boolean isTypeNamespace(String namespace) {
            return this.TypeNamespace.matches(namespace);
        }


        /// Full Id of BlockType (StoneType, MudType)
        public boolean isTypeFullId(String fullId) {
            return this.TypeFullId.matches(fullId);
        }

        /**
         * @param BlockNameOrKeyword keyword or Regex or Block Name
         *                                  <br>Example (keyword): "chair"
         *                                  <br>Example (Blockname): "gold_chair"
         */
        public boolean isBlockName(String BlockNameOrKeyword) {
            return this.blockName.matches(BlockNameOrKeyword) || this.blockName.contains(BlockNameOrKeyword);
        }

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
