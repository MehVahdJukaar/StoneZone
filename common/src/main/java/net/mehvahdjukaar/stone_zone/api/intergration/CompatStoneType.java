package net.mehvahdjukaar.stone_zone.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// StoneType Detection detect a StoneType that met 2 requirements:
        /// BASS_DRUM (sound)
        /// blockID: nameStoneType_bricks - only 2 words
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatStoneType {

    public static void init() {

        // Aerial Hell
        simpleStoneFinder("aerialhell", "glaucophanite"); // HARP
        simpleStoneFinder("aerialhell", "lunatic_stone"); // HARP
        simpleStoneFinder("aerialhell", "volucite_stone"); // HARP
        simpleStoneFinder("aerialhell", "dark_lunatic_stone"); // HARP
        simpleStoneFinder("aerialhell", "slippery_sand_stone");
        stoneBlockFinder("aerialhell", "smoky_quartz");

        advancedStoneFinder("aerialhell", "aerial_netherack",
                "golden_nether_bricks", "golden_nether_bricks_slab",
                "golden_nether_bricks_stairs", "golden_nether_bricks_wall"); // HARP

        // Rocky Minerals
        simpleStoneFinder("rockyminerals", "worn_granite");

        // Prehistoric Fauna
        simpleStoneFinder("prehistoricfauna", "chalk"); // HARP
        simpleStoneFinder("prehistoricfauna", "henostone"); // HARP
        simpleStoneFinder("prehistoricfauna", "sandstone"); // HARP
        simpleStoneFinder("prehistoricfauna", "siltstone"); // HARP

        // Project-Reds-Exploration
        advancedStoneFinder("projectred_exploration", "marble", "marble_brick"); // HARP

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
        simpleStoneFinder("arts_and_crafts", "white_chalk");

        // Caverns-And-Chasms
        simpleStoneFinder("caverns_and_chasms", "sugilite"); // HARP
        simpleStoneFinder("caverns_and_chasms", "cassiterite"); // HARP

        // DivineRPG
        simpleStoneFinder("divinerpg", "twilight_stone");

        // Outer End
        simpleStoneFinder("outer_end", "violite"); // HARP

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
//        stoneBlockFinder("quark", "duskbound", true, true); // STONE: duskbound_block

        // Nature's Spirit
        simpleStoneFinder("natures_spirit", "pink_sandstone");
        simpleStoneFinder("natures_spirit", "white_chalk");
        simpleStoneFinder("natures_spirit", "light_gray_chalk");
        simpleStoneFinder("natures_spirit", "gray_chalk");
        simpleStoneFinder("natures_spirit", "black_chalk");
        simpleStoneFinder("natures_spirit", "brown_chalk");
        simpleStoneFinder("natures_spirit", "red_chalk");
        simpleStoneFinder("natures_spirit", "orange_chalk");
        simpleStoneFinder("natures_spirit", "yellow_chalk");
        simpleStoneFinder("natures_spirit", "lime_chalk");
        simpleStoneFinder("natures_spirit", "green_chalk");
        simpleStoneFinder("natures_spirit", "cyan_chalk");
        simpleStoneFinder("natures_spirit", "light_blue_chalk");
        simpleStoneFinder("natures_spirit", "blue_chalk");
        simpleStoneFinder("natures_spirit", "purple_chalk");
        simpleStoneFinder("natures_spirit", "magenta_chalk");
        simpleStoneFinder("natures_spirit", "pink_chalk");
        // Added by Nature's Spirit with Arts and Crafts Compatibility
        if (PlatHelper.isModLoaded("arts_and_crafts")) simpleStoneFinder("natures_spirit", "bleached_chalk");

        // Regions unexplored
        simpleStoneFinder("regions_unexplored", "argillite");

        // Biomes o'plenty
        simpleStoneFinder("biomesoplenty", "black_sandstone");
        simpleStoneFinder("biomesoplenty", "orange_sandstone");
        simpleStoneFinder("biomesoplenty", "white_sandstone");

    }

//!! StoneType
    public static void simpleStoneFinder(String modId, String nameStoneType) {
        if (PlatHelper.isModLoaded(modId)) {
            var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

            BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
        }
    }

    /**
     * @param nameStoneType name of StoneType
     * @param nameStone name of StoneType with "_stone" or any words
    **/
    @SuppressWarnings("unused")
    public static void stoneFinder(String modId, String nameStoneType, String nameStone) {
        if (PlatHelper.isModLoaded(modId)) {
            var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStone);

            BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
        }
    }

    /// nameStone has "_block" as suffix
    public static void stoneBlockFinder(String modId, String nameStoneType, boolean includeSlab, boolean includeStairs) {
        if (PlatHelper.isModLoaded(modId)) {
            String baseName = nameStoneType + "_block";

            var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, baseName);
            if (includeSlab) stonetypeFinder.addChild("slab", baseName +"_slab");
            if (includeStairs) stonetypeFinder.addChild("slab", baseName +"_stairs");

            BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
        }
    }
    /** nameStone has "_block" as suffix
     * default parameter
     * includeSlab: false
     * includeStairs: false
    */
    public static void stoneBlockFinder(String modId, String nameStoneType) {
        stoneBlockFinder(modId, nameStoneType, false, false);
    }

    public static void advancedStoneFinder(String modId, String nameStoneType, String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameStoneType);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                if (childKeySafe.contains(childKey)) stonetypeFinder.addChild(childKey, currentChild);
                else StoneZone.LOGGER.warn("CompatStoneType: Incorrect childKey - {} for {}", childKey, currentChild);
            }

            BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
        }
    }
//!! MudType
    public static void simpleMudFinder(String modId, String nameStoneType) {
        if (PlatHelper.isModLoaded(modId)) {
            var stonetypeFinder = MudType.Finder.simple(modId, nameStoneType, nameStoneType);

            BlockSetAPI.addBlockTypeFinder(MudType.class, stonetypeFinder);
        }
    }

        /// Get the keyword from block: stone_bricks, key: bricks
        @SuppressWarnings("RegExpAnonymousGroup")
        public static String getChildKeyFrom(String childBlock) {
            String lastword = childBlock.substring(childBlock.lastIndexOf("_") + 1);

            // With "bricks"
            if (childBlock.matches("\\w+_bricks?(?:_[a-z]+)?")) {
                Pattern pattern = Pattern.compile("\\w+_(bricks?)(_[a-z]+)?");
                Matcher matcher = pattern.matcher(childBlock);
                if (matcher.find()) {
                    String suffix = (Objects.isNull(matcher.group(2))) ? matcher.group(1) : matcher.group(1) + matcher.group(2);
                    return switch (suffix) {
                        case "brick", "bricks" -> "bricks";
                        case "brick_slab", "bricks_slab" -> "brick_slab";
                        case "brick_stairs", "bricks_stairs" -> "brick_stairs";
                        case "brick_wall", "bricks_wall" -> "brick_wall";
                        default -> lastword;
                    };
                }
            }
            // Default
            return lastword;
        }

    private static final Set<String> childKeySafe = Set.of(
            "stone", "stairs", "slab", "wall", "button", "pressure_plate", "smooth_stone",
            "cobblestone", "mossy_cobblestone",
            "polished", "polished_stairs", "polished_slab",
            "bricks", "brick_stairs", "brick_slab", "brick_wall", "cracked_bricks", "brick_tiles",
            "mossy_bricks", "mossy_brick_slab", "mossy_brick_stairs", "mossy_brick_wall"
    );

}
