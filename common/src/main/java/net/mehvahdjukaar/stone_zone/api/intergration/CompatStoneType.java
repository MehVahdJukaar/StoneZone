package net.mehvahdjukaar.stone_zone.api.intergration;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys;

/// StoneType Detection detect a StoneType that doesn't met 3 requirements:
        /// blockID: nameStoneType_bricks - only 1 word, the "nameStoneType"
        /// CHECK for POLISHED: polished_nameStoneType
        /// CHECK for BRICKS: nameStoneType_bricks
// Put all undetected StoneTypes (including hardcoded ones) from mods in here to be included
public class CompatStoneType {

    /* Defintion of REASONS:
     * 2-Words: Name of StoneType has 2 words instead of 1 word
     *
     * Id-Stone: the Id of STONE has an affix
     *
     * Spelling-Convention: a typo in the Id, no underscore. Example: bricks_stairs instead of brick_stairs
     *
     * Naming-Convention: blocks has unique names that doesn't have "_block", "_stone", or has different affix
     *
     * No-Children: There is no children for StoneType, just Stone.
     *
     * Undetected-Bricks: StoneType's BRICKS has different Id aside an suffix of "_bricks" like "_brick"
     *
     * Undetectable: The StoneType have no BRICKS or POLISHED to be detected
     */
    public static void init() {

        StoneTypeRegistry stoneReg = StoneTypeRegistry.INSTANCE;

        // Blocks You Need - Luna
            //NOTE: not really a StoneType
        stoneReg.addSimpleFinder("blocksyouneed_luna", "sodalite");


        stoneReg.addSimpleFinder("blocksyouneed_luna", "sunstone") //REASON: Undetected-Bricks, Spelling-Convention
                .childBlockSuffix(VanillaRockChildKeys.BRICKS, "_bricks_ornate")
                .childBlockSuffix(VanillaRockChildKeys.BRICK_STAIRS, "_bricks_stairs")
                .childBlockSuffix(VanillaRockChildKeys.BRICK_SLAB, "_bricks_slab")
                .childBlockSuffix(VanillaRockChildKeys.BRICK_WALL, "_bricks_wall");


        stoneReg.addSimpleFinder("blocksyouneed_luna", "glance") //REASON: Spelling-Convention
                .childBlockSuffix(VanillaRockChildKeys.BRICK_STAIRS, "_bricks_stairs")
                .childBlockSuffix(VanillaRockChildKeys.BRICK_SLAB, "_bricks_slab")
                .childBlockSuffix(VanillaRockChildKeys.BRICK_WALL, "_bricks_wall");

        // The-Twiligth-Forest
        stoneReg.addSimpleFinder("twilightforest", "deadrock"); //REASON: Undetectable
        stoneReg.addSimpleFinder("twilightforest", "mazestone") //REASON: Undetected-Bricks
                .childBlockSuffix(VanillaRockChildKeys.BRICKS, "_brick");

        // Nature's Spirit - REASON: 2-Words
        stoneReg.addSimpleFinder("natures_spirit", "white_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "light_gray_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "gray_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "black_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "brown_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "red_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "orange_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "yellow_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "lime_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "green_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "cyan_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "light_blue_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "blue_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "purple_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "magenta_kaolin");
        stoneReg.addSimpleFinder("natures_spirit", "pink_kaolin");

        // Galosphere - REASON: 2-Words
        stoneReg.addSimpleFinder("galosphere", "pink_salt");
        stoneReg.addSimpleFinder("galosphere", "rose_pink_salt");
        stoneReg.addSimpleFinder("galosphere", "pastel_pink_salt");

        // Aerial Hell - REASON: 2-Words
        stoneReg.addSimpleFinder("aerialhell", "dark_lunatic_stone");
        stoneReg.addSimpleFinder("aerialhell", "slippery_sand_stone");

        stoneReg.addSimpleFinder("aerialhell", "lunatic_stone"); //REASON: Undetectable
        stoneReg.addSimpleFinder("aerialhell", "volucite_stone"); //REASON: Undetectable

        stoneReg.addSimpleFinder("aerialhell", "smoky_quartz") //REASON: Id-Stone
                .stoneSuffix("_block");

        stoneReg.addSimpleFinder("aerialhell", "aerial_netherack") //REASON: Spelling-Convention
                .childBlock(VanillaRockChildKeys.BRICKS, "golden_nether_bricks")
                .childBlock(VanillaRockChildKeys.BRICK_STAIRS, "golden_nether_bricks_stairs")
                .childBlock(VanillaRockChildKeys.BRICK_SLAB, "golden_nether_bricks_slab")
                .childBlock(VanillaRockChildKeys.BRICK_WALL, "golden_nether_bricks_wall");

        // Rocky Minerals - REASON: 2-words
        stoneReg.addSimpleFinder("rockymineral", "worn_granite");

        // Project-Reds-Exploration - REASON: Undetected-Bricks
        stoneReg.addSimpleFinder("projectred_exploration", "marble")
                .childBlockSuffix(VanillaRockChildKeys.BRICKS, "_brick");

        // Atmospheric - REASON: 2-Words
        stoneReg.addSimpleFinder("atmospheric", "ivory_travertine");
        stoneReg.addSimpleFinder("atmospheric", "peach_travertine");
        stoneReg.addSimpleFinder("atmospheric", "persimmon_travertine");
        stoneReg.addSimpleFinder("atmospheric", "saffron_travertine");

        // Arts-And-Crafts
        stoneReg.addSimpleFinder("arts_and_crafts", "white_chalk"); //REASON: 2-Words

        // Oh The Biomes We've Gone - REASON: 2-Words
        stoneReg.addSimpleFinder("biomeswevegone", "white_sandstone");
        stoneReg.addSimpleFinder("biomeswevegone", "blue_sandstone");
        stoneReg.addSimpleFinder("biomeswevegone", "black_sandstone");
        stoneReg.addSimpleFinder("biomeswevegone", "purple_sandstone");
        stoneReg.addSimpleFinder("biomeswevegone", "pink_sandstone");
        stoneReg.addSimpleFinder("biomeswevegone", "windswept_sandstone");
        stoneReg.addSimpleFinder("biomeswevegone", "red_rock");

        // What Is Stone - REASON: 2-Words
        stoneReg.addSimpleFinder("what_is_stone", "white_granite");
        stoneReg.addSimpleFinder("what_is_stone", "white_limestone");
        stoneReg.addSimpleFinder("what_is_stone", "arkosic_sandstone");
        stoneReg.addSimpleFinder("what_is_stone", "black_marble");
        stoneReg.addSimpleFinder("what_is_stone", "grey_limestone");
        stoneReg.addSimpleFinder("what_is_stone", "anthracite"); //REASON: No-Children

        // BetterEnd - REASON: 2-Words
        stoneReg.addSimpleFinder("betterend", "azure_jadestone");
        stoneReg.addSimpleFinder("betterend", "sandy_jadestone");
        stoneReg.addSimpleFinder("betterend", "virid_jadestone");
        stoneReg.addSimpleFinder("betterend", "sulphuric_rock");

        // Create - REASON: Undetectable
        stoneReg.addSimpleFinder("create", "limestone");
        stoneReg.addSimpleFinder("create", "asurine");
        stoneReg.addSimpleFinder("create", "crimsite");
        stoneReg.addSimpleFinder("create", "ochrum");
        stoneReg.addSimpleFinder("create", "veridium");
        stoneReg.addSimpleFinder("create", "scoria");
        stoneReg.addSimpleFinder("create", "scorchia");

            // Create Dreams & Desires - REASON: Undetectable
        stoneReg.addSimpleFinder("create_dd", "gabbro");
        stoneReg.addSimpleFinder("create_dd", "aethersite");
        stoneReg.addSimpleFinder("create_dd", "potassic");
        stoneReg.addSimpleFinder("create_dd", "weathered_limestone"); //REASON: 2-Words

        // Bountiful Fares (FABRIC) - REASON: Id-Stone
        stoneReg.addSimpleFinder("bountifulfares", "feldspar")
                .stoneSuffix("_block");

        // Aether Redux - REASON: Undetectable
        stoneReg.addSimpleFinder("aether_redux", "driftshale");

        // Deep Aether
        stoneReg.addSimpleFinder("deep_aether", "clorite"); //REASON: Undetectable
        stoneReg.addSimpleFinder("deep_aether", "raw_clorite"); //REASON: 2-words

        // Alex's Caves - REASON: Undetectable
        stoneReg.addSimpleFinder("alexscave", "limestone");

        // Enlightened End - REASON: 2-Words
        stoneReg.addSimpleFinder("enlightened_end", "void_shale");

        // Quark - REASON: 2-Words
        stoneReg.addSimpleFinder("quark", "soul_sandstone");

        // Nature's Spirit - REASON: 2-Words
        stoneReg.addSimpleFinder("natures_spirit", "pink_sandstone");
        stoneReg.addSimpleFinder("natures_spirit", "white_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "light_gray_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "gray_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "black_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "brown_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "red_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "orange_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "yellow_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "lime_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "green_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "cyan_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "light_blue_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "blue_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "purple_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "magenta_chalk");
        stoneReg.addSimpleFinder("natures_spirit", "pink_chalk");

        // Added by Nature's Spirit with Arts and Crafts Compatibility - REASON: 2-Words
        if (PlatHelper.isModLoaded("arts_and_crafts")) stoneReg.addSimpleFinder("natures_spirit", "bleached_chalk");

        // Regions unexplored - REASON: Undetectable
        stoneReg.addSimpleFinder("regions_unexplored", "argillite");

        // Biomes o'plenty - REASON: 2-Words
        stoneReg.addSimpleFinder("biomesoplenty", "black_sandstone");
        stoneReg.addSimpleFinder("biomesoplenty", "orange_sandstone");
        stoneReg.addSimpleFinder("biomesoplenty", "white_sandstone");

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                         MUDTYPE                          │
        //      └──────────────────────────────────────────────────────────┘

        MudTypeRegistry mudReg = MudTypeRegistry.INSTANCE;

        // Deeper And Darker - REASON: 2-Words
        mudReg.addSimpleFinder("deeperdarker:sculk_grime");
    }

}
