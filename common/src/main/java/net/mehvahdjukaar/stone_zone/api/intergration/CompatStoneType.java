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

        // Deeper And Darker
        simpleMudFinder("deeperdarker", "sculk_grime");

        // What Is Stone
        simpleStoneFinder("what_is_stone", "anthracite");
        simpleStoneFinder("what_is_stone", "breccia");
        simpleStoneFinder("what_is_stone", "conglomerate");
        simpleStoneFinder("what_is_stone", "pegmatite");
        simpleStoneFinder("what_is_stone", "scoria");

        // BetterEnd
        simpleStoneFinder("betterend", "azure_jadestone");
        simpleStoneFinder("betterend", "sandy_jadestone");
        simpleStoneFinder("betterend", "virid_jadestone");
        simpleStoneFinder("betterend", "sulphuric_rock");

        // Create - undetected due to no BRICKS
        simpleStoneFinder("create", "limestone");
        simpleStoneFinder("create", "asurine");
        simpleStoneFinder("create", "crimsite");
        simpleStoneFinder("create", "ochrum");
        simpleStoneFinder("create", "veridium");
        simpleStoneFinder("create", "scoria");
        simpleStoneFinder("create", "scorchia");

        // Create Dreams & Desires
        simpleStoneFinder("create_dd", "gabbro");
        simpleStoneFinder("create_dd", "weathered_limestone");
        simpleStoneFinder("create_dd", "aethersite");
        simpleStoneFinder("create_dd", "potassic");

        // Bountiful Fares - nameStone of feldspar is feldspar_block
        stoneBlockFinder("bountifulfares", "feldspar");

        // Aether Redux - undetected due to HARP sound
        simpleStoneFinder("aether_redux", "sentrite");
        simpleStoneFinder("aether_redux", "divinite");
        simpleStoneFinder("aether_redux", "driftshale"); // - due to no BRICKS

        // Aether Works - undetected due to HARP sound
        simpleStoneFinder("aetherworks", "suevite");

        // Deep Aether - undetected due to no BRICKS
        simpleStoneFinder("deep_aether", "clorite");
        simpleStoneFinder("deep_aether", "raw_clorite");

        // Alex's Caves - undetected due to HARP sound
        simpleStoneFinder("alexscaves", "galena");
        simpleStoneFinder("alexscaves", "radrock");
        simpleStoneFinder("alexscaves", "abyssmarine");
        simpleStoneFinder("alexscaves", "guanostone");
        simpleStoneFinder("alexscaves", "limestone"); // - due to no BRICKS

        // Enlightened End - undetected due to 3 words (void_shale_bricks)
        simpleStoneFinder("enlightened_end", "void_shale");

        // Ars Nouveau
        simpleStoneFinder("ars_nouveau", "sourcestone");

        // Quark
        simpleStoneFinder("quark", "soul_sandstone");

    }

//!! StoneType
    public static void simpleStoneFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

    /// nameStone has "_block" as suffix
    public static void stoneBlockFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType +"_block");

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

//!! MudType
    public static void simpleMudFinder(String modId, String nameStoneType) {
        var stonetypeFinder = MudType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(MudType.class, stonetypeFinder);
    }

}
