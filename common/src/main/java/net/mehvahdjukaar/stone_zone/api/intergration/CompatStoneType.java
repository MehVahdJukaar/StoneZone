package net.mehvahdjukaar.stone_zone.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

/// StoneType Detection detect a StoneType that met 2 requirements:
        /// BASS_DRUM (sound)
        /// blockID: nameStoneType_bricks - only 2 words
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatStoneType {

    public static void init() {

        // Marioverse
        simpleStoneFinder("marioverse", "deep_fungal_stone");

        // Nature's Spirit
        simpleStoneFinder("natures_spirit", "white_kaolin");
        simpleStoneFinder("natures_spirit", "light_gray_kaolin");
        simpleStoneFinder("natures_spirit", "gray_kaolin");
        simpleStoneFinder("natures_spirit", "black_kaolin");
        simpleStoneFinder("natures_spirit", "brown_kaolin");
        simpleStoneFinder("natures_spirit", "red_kaolin");
        simpleStoneFinder("natures_spirit", "orange_kaolin");
        simpleStoneFinder("natures_spirit", "yellow_kaolin");
        simpleStoneFinder("natures_spirit", "lime_kaolin");
        simpleStoneFinder("natures_spirit", "green_kaolin");
        simpleStoneFinder("natures_spirit", "cyan_kaolin");
        simpleStoneFinder("natures_spirit", "light_blue_kaolin");
        simpleStoneFinder("natures_spirit", "blue_kaolin");
        simpleStoneFinder("natures_spirit", "purple_kaolin");
        simpleStoneFinder("natures_spirit", "magenta_kaolin");
        simpleStoneFinder("natures_spirit", "pink_kaolin");

        // Galosphere
        simpleStoneFinder("galosphere", "pink_salt"); // HARP
        simpleStoneFinder("galosphere", "rose_pink_salt"); // HARP
        simpleStoneFinder("galosphere", "pastel_pink_salt"); // HARP

        // Aerial Hell
        simpleStoneFinder("aerialhell", "glaucophanite"); // HARP
        simpleStoneFinder("aerialhell", "lunatic_stone"); // HARP
        simpleStoneFinder("aerialhell", "volucite_stone"); // HARP
        simpleStoneFinder("aerialhell", "dark_lunatic_stone"); // HARP
        simpleStoneFinder("aerialhell", "slippery_sand_stone");
        advancedStoneFinder("aerialhell", "smoky_quartz", "smoky_quartz_block");

        advancedStoneFinder("aerialhell", "aerial_netherack","aerial_netherack",
                "BRICKS-golden_nether_bricks", "BRICK_SLAB-golden_nether_bricks_slab",
                "BRICK_STAIRS-golden_nether_bricks_stairs", "BRICK_WALL-golden_nether_bricks_wall"); // HARP

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

        // Bountiful Fares (FABRIC)
        advancedStoneFinder("bountifulfares", "feldspar", "feldspar_block");

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
    /**
     * @param modId - mod id of the mod
     * @param nameStoneType - name of StoneType
     * @param nameChildren - childkey-ID_of_the_children or nameGemType_ingot
     */
    public static void simpleStoneFinder(String modId, String nameStoneType, String... nameChildren) {
        advancedStoneFinder(modId, nameStoneType, nameStoneType, nameChildren);
    }

    /**
     * @param modId - mod id of the mod
     * @param nameStoneType - name of stoneType without "_block"
     * @param nameBlock - name of block for stoneType. Usually with "_block"
     * @param nameChildren - childkey-ID_of_the_children or stoneType_nugget
     */
    public static void advancedStoneFinder(String modId, String nameStoneType, String nameBlock, String... nameChildren) {
        if (PlatHelper.isModLoaded(modId)) {
            var stonetypeFinder = StoneType.Finder.simple(modId, nameStoneType, nameBlock);

            for (String currentChild : nameChildren) {
                String childKey = getChildKeyFrom(currentChild);
                String blockId = currentChild.split("-")[1];
                ResourceLocation childId = (blockId.contains(":"))
                        ? ResourceLocation.parse(blockId)
                        : ResourceLocation.fromNamespaceAndPath(modId, blockId);

                if (currentChild.contains("-") && childKeySafe.contains(childKey)) {
                    stonetypeFinder.addChild(childKey, childId);
                }
                else if (childKeySafe.contains(childKey)) stonetypeFinder.addChild(childKey, currentChild);
                else StoneZone.LOGGER.warn("StoneTypeFinder: Incorrect childKey - {} for {}", childKey, currentChild);

            }

            BlockSetAPI.addBlockTypeFinder(StoneType.class, stonetypeFinder);
        }
    }

//!! MudType
    public static void simpleMudFinder(String modId, String nameMudType) {
        if (PlatHelper.isModLoaded(modId)) {
            var stonetypeFinder = MudType.Finder.simple(modId, nameMudType, nameMudType);

            BlockSetAPI.addBlockTypeFinder(MudType.class, stonetypeFinder);
        }
    }

    /// Get the keyword from block: stone_bricks, key: bricks
    public static String getChildKeyFrom(String childBlock) {
        if (childBlock.contains("-")) {
            return childBlock.split("-")[0];
        }

        // Default
        return childBlock.substring(childBlock.lastIndexOf("_") + 1);
    }

    private static final Set<String> childKeySafe = Set.of(
            "stone", "stairs", "slab", "wall", "button", "pressure_plate", "smooth_stone",
            "cobblestone", "mossy_cobblestone",
            "polished", "polished_stairs", "polished_slab",
            "bricks", "brick_stairs", "brick_slab", "brick_wall", "cracked_bricks", "brick_tiles",
            "mossy_bricks", "mossy_brick_slab", "mossy_brick_stairs", "mossy_brick_wall"
    );

}
