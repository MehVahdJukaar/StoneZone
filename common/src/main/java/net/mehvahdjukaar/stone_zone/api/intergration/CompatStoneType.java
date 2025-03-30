package net.mehvahdjukaar.stone_zone.api.intergration;

import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;

    /// StoneType Detection detect a StoneType that met 2 requirements:
        /// BASS_DRUM (sound)
        /// blockID: nameStoneType_bricks - only 2 words
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatStoneType {

    public static void init() {

        // Project-Reds-Exploration
        simpleStoneFinder("projectred_exploration", "marble"); // HARP

        // Twigs
        simpleStoneFinder("twigs", "rhyolite"); // HARP
        simpleStoneFinder("twigs", "schist"); // HARP
        simpleStoneFinder("twigs", "bloodstone"); // HARP

        // Atmospheric
        simpleStoneFinder("atmospheric", "dolerite"); // HARP
        simpleStoneFinder("atmospheric", "ivory_travertine");
        simpleStoneFinder("atmospheric", "peach_travertine");
        simpleStoneFinder("atmospheric", "persimmon_travertine");
        simpleStoneFinder("atmospheric", "saffron_travertine");

        // Arts-And-Crafts
        simpleStoneFinder("arts_and_crafts", "gypsum"); // HARP

        // Caverns-And-Chasms
        simpleStoneFinder("caverns_and_chasms", "sugilite"); // HARP
        simpleStoneFinder("caverns_and_chasms", "cassiterite"); // HARP

        // DivineRPG
        simpleStoneFinder("divinerpg", "twilight_stone");

        // Outer End
        stoneBlockFinder("outer_end", "violite"); // HARP

        // Deeper And Darker
        simpleMudFinder("deeperdarker", "sculk_grime");

        // Oh The Biomes We've Gone
        simpleStoneFinder("biomeswevegone", "white_sandstone");
        simpleStoneFinder("biomeswevegone", "blue_sandstone");
        simpleStoneFinder("biomeswevegone", "black_sandstone");
        simpleStoneFinder("biomeswevegone", "purple_sandstone");
        simpleStoneFinder("biomeswevegone", "pink_sandstone");
        simpleStoneFinder("biomeswevegone", "windswept_sandstone");
        simpleStoneFinder("biomeswevegone", "red_rock");

        // What Is Stone
        simpleStoneFinder("what_is_stone", "anthracite"); // No other children
        simpleStoneFinder("what_is_stone", "white_granite");
        simpleStoneFinder("what_is_stone", "white_limestone");
        simpleStoneFinder("what_is_stone", "arkosic_sandstone");
        simpleStoneFinder("what_is_stone", "black_marble");
        simpleStoneFinder("what_is_stone", "grey_limestone");

        // BetterEnd
        simpleStoneFinder("betterend", "azure_jadestone");
        simpleStoneFinder("betterend", "sandy_jadestone");
        simpleStoneFinder("betterend", "virid_jadestone");
        simpleStoneFinder("betterend", "sulphuric_rock");

        // Create
        simpleStoneFinder("create", "limestone"); // NO BRICKS or POLISHED
        simpleStoneFinder("create", "asurine"); // NO BRICKS or POLISHED
        simpleStoneFinder("create", "crimsite"); // NO BRICKS or POLISHED
        simpleStoneFinder("create", "ochrum"); // NO BRICKS or POLISHED
        simpleStoneFinder("create", "veridium"); // NO BRICKS or POLISHED
        simpleStoneFinder("create", "scoria"); // NO BRICKS or POLISHED
        simpleStoneFinder("create", "scorchia"); // NO BRICKS or POLISHED

            // Create Dreams & Desires
        simpleStoneFinder("create_dd", "gabbro"); // NO BRICKS or POLISHED
        simpleStoneFinder("create_dd", "aethersite"); // NO BRICKS or POLISHED
        simpleStoneFinder("create_dd", "potassic"); // NO BRICKS or POLISHED
        simpleStoneFinder("create_dd", "weathered_limestone");

        // Bountiful Fares - nameStone of feldspar is feldspar_block
        stoneBlockFinder("bountifulfares", "feldspar");

        // Aether Redux
        simpleStoneFinder("aether_redux", "sentrite"); // HARP
        simpleStoneFinder("aether_redux", "divinite"); // HARP
        simpleStoneFinder("aether_redux", "driftshale"); // NO BRICKS

        // Aether Works
        simpleStoneFinder("aetherworks", "suevite"); // HARP

        // Deep Aether
        simpleStoneFinder("deep_aether", "clorite"); // NO BRICKS
        simpleStoneFinder("deep_aether", "raw_clorite");

        // Alex's Caves
        simpleStoneFinder("alexscaves", "galena"); // HARP
        simpleStoneFinder("alexscaves", "radrock"); // HARP
        simpleStoneFinder("alexscaves", "abyssmarine"); // HARP
        simpleStoneFinder("alexscaves", "guanostone"); // HARP
        simpleStoneFinder("alexscaves", "limestone"); // NO BRICKS

        // Enlightened End
        simpleStoneFinder("enlightened_end", "void_shale");

        // Ars Nouveau
        simpleStoneFinder("ars_nouveau", "sourcestone"); // HARP

        // Quark
        simpleStoneFinder("quark", "soul_sandstone");
        stoneBlockFinder("quark", "duskbound", true, true); // STONE: duskbound_block

        // Nature's Spirit
        simpleStoneFinder("natures_spirit", "pink_sandstone");

        // Regions unexplored
        simpleStoneFinder("regions_unexplored", "argillite");

        // Biomes o'plenty
        simpleStoneFinder("biomesoplenty", "black_sandstone");
        simpleStoneFinder("biomesoplenty", "orange_sandstone");
        simpleStoneFinder("biomesoplenty", "white_sandstone");

    }

//!! StoneType
    public static void simpleStoneFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

    public static void stoneFinder(String modId, String nameStoneType, String nameStone) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStone);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

    /// nameStone has "_block" as suffix
    public static void stoneBlockFinder(String modId, String nameStoneType, boolean includeSlab, boolean includeStairs) {
        String baseName = nameStoneType + "_block";

        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, baseName);
        if (includeSlab) stonetypeFinder.addChild("slab", baseName +"_slab");
        if (includeStairs) stonetypeFinder.addChild("slab", baseName +"_stairs");

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }
    /** nameStone has "_block" as suffix
     * default parameter
     * includeSlab: false
     * includeStairs: false
    */
    public static void stoneBlockFinder(String modId, String nameStoneType) {
        stoneBlockFinder(modId, nameStoneType, false, false);
    }

//!! MudType
    public static void simpleMudFinder(String modId, String nameStoneType) {
        var stonetypeFinder = MudType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(MudType.class, stonetypeFinder);
    }

}
