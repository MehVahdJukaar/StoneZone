package net.mehvahdjukaar.stone_zone.api.intergration;

import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;

// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatStoneType {

    public static void init() {

        // Aether Redux - undetected due to HARP sound
        simpleFinder("aether_redux", "sentrite");
        simpleFinder("aether_redux", "divinite");

        // Aether Works - undetected due to HARP sound
        simpleFinder("aetherworks", "suevite");

        // Deeper And Darker
        simpleFinder("deeperdarker", "sculk_stone");

        // Rebinated Nether
        simpleFinder("rubinated_nether", "altar_stone");

        // Alex's Caves - undetected due to HARP sound
        simpleFinder("alexscaves", "galena");
        simpleFinder("alexscaves", "radrock");
        simpleFinder("alexscaves", "abyssmarine");
        simpleFinder("alexscaves", "guanostone");

        // Enlightened End
        simpleFinder("enlightened_end", "void_shale");


    }

    public static void simpleFinder(String modId, String nameStoneType) {
        var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);
        stonetypeFinder.addChild("bricks", nameStoneType + "_bricks");

        BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);

    }

}
