package net.mehvahdjukaar.stone_zone.api.intergration;

import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;

    /// StoneType Detection detect a StoneType that met 2 requirements:
        /// BASS_DRUM (sound)
        /// blockID: nameStoneType_bricks - only 2 words
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatStoneType {

    public static void init() {

        // Create - undetected due to no BRICKS
        simpleFinder("create", "limestone");
        simpleFinder("create", "asurine");
        simpleFinder("create", "crimsite");
        simpleFinder("create", "ochrum");
        simpleFinder("create", "veridium");
        simpleFinder("create", "scoria");
        simpleFinder("create", "scorchia");

        // Create Dreams & Desires
        simpleFinder("create_dd", "gabbro");
        simpleFinder("create_dd", "weathered_limestone");
        simpleFinder("create_dd", "aethersite");
        simpleFinder("create_dd", "potassic");

        // Bountiful Fares - nameStone of feldspar is feldspar_block
        stoneBlockFinder("bountifulfares", "feldspar");

        // Aether Redux - undetected due to HARP sound
        simpleFinder("aether_redux", "sentrite");
        simpleFinder("aether_redux", "divinite");
        simpleFinder("aether_redux", "driftshale"); // - due to no BRICKS

        // Aether Works - undetected due to HARP sound
        simpleFinder("aetherworks", "suevite");

        // Deep Aether - undetected due to no BRICKS
        simpleFinder("deep_aether", "clorite");
        simpleFinder("deep_aether", "raw_clorite");

        // Deeper And Darker - undetected due to 3 words (sculk_stone_bricks)
        simpleFinder("deeperdarker", "sculk_stone");

        // Rubinated Nether - undetected due to 3 words (altar_stone_bricks)
        simpleFinder("rubinated_nether", "altar_stone");

        // Alex's Caves - undetected due to HARP sound
        simpleFinder("alexscaves", "galena");
        simpleFinder("alexscaves", "radrock");
        simpleFinder("alexscaves", "abyssmarine");
        simpleFinder("alexscaves", "guanostone");
        simpleFinder("alexscaves", "limestone"); // - due to no BRICKS

        // Enlightened End - undetected due to 3 words (void_shale_bricks)
        simpleFinder("enlightened_end", "void_shale");

        // Ars Nouveau
        simpleFinder("ars_nouveau", "sourcestone");

        // Quark
        simpleFinder("quark", "soul_sandstone");

    }

    public static void simpleFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }

    /// nameStone has "_block" as suffix
    public static void stoneBlockFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType +"_block");

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
    }
}
