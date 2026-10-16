package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import com.supermartijn642.rechiseled.blocks.RechiseledPillarBlock;
import com.supermartijn642.rechiseled.blocks.RechiseledSlabBlock;
import com.supermartijn642.rechiseled.blocks.RechiseledStairBlock;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;

///SUPPORT: v1.2.5+
public class RechiseledModule extends StoneZoneModule {

    public final BlockSetEntry<StoneType> big_tilesSet, big_tiles_connectingSet;
    public final Supplier<CreativeModeTab> tab = getModTab(getModId());
    public static String mod_id = "";

    public RechiseledModule(String modId) {
        super(modId, "rcd");
        mod_id = modId;

        big_tilesSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_big_tiles",
                "stone_big_tiles_slab",
                "stone_big_tiles_stairs",
                tab
        )
                .build();
        this.addEntries(big_tilesSet);

        big_tiles_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_big_tiles_connecting",
                        "stone_big_tiles_slab_connecting",
                        "stone_big_tiles_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(big_tiles_connectingSet);


        var borderedSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_bordered",
                "stone_bordered_slab",
                "stone_bordered_stairs",
                tab
        )
                .build();
        this.addEntries(borderedSet);

        var bordered_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_bordered_connecting",
                        "stone_bordered_slab_connecting",
                        "stone_bordered_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(bordered_connectingSet);

        var brick_patternSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_brick_pattern",
                "stone_brick_pattern_slab",
                "stone_brick_pattern_stairs",
                tab
        )
                .build();
        this.addEntries(brick_patternSet);

        var brick_pattern_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_brick_pattern_connecting",
                        "stone_brick_pattern_slab_connecting",
                        "stone_brick_pattern_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(brick_pattern_connectingSet);

        var brick_pavingSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_brick_paving",
                "stone_brick_paving_slab",
                "stone_brick_paving_stairs",
                tab
        )
                .build();
        this.addEntries(brick_pavingSet);

        var brick_paving_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_brick_paving_connecting",
                        "stone_brick_paving_slab_connecting",
                        "stone_brick_paving_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(brick_paving_connectingSet);

        var crushedSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_crushed",
                "stone_crushed_slab",
                "stone_crushed_stairs",
                tab
        )
                .build();
        this.addEntries(crushedSet);

        var crushed_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_crushed_connecting",
                        "stone_crushed_slab_connecting",
                        "stone_crushed_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(crushed_connectingSet);

        var diagonal_bricksSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_diagonal_bricks",
                "stone_diagonal_bricks_slab",
                "stone_diagonal_bricks_stairs",
                tab
        )
                .build();
        this.addEntries(diagonal_bricksSet);

        var diagonal_bricks_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_diagonal_bricks_connecting",
                        "stone_diagonal_bricks_slab_connecting",
                        "stone_diagonal_bricks_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(diagonal_bricks_connectingSet);

        var pathSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_path",
                "stone_path_slab",
                "stone_path_stairs",
                tab
        )
                .build();
        this.addEntries(pathSet);

        var path_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_path_connecting",
                        "stone_path_slab_connecting",
                        "stone_path_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(path_connectingSet);

        var rotated_bricksSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_rotated_bricks",
                "stone_rotated_bricks_slab",
                "stone_rotated_bricks_stairs",
                tab
        )
                .build();
        this.addEntries(rotated_bricksSet);

        var rotated_bricks_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_rotated_bricks_connecting",
                        "stone_rotated_bricks_slab_connecting",
                        "stone_rotated_bricks_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(rotated_bricks_connectingSet);

        var small_bricksSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_small_bricks",
                "stone_small_bricks_slab",
                "stone_small_bricks_stairs",
                tab
        )
                .build();
        this.addEntries(small_bricksSet);

        var small_bricks_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_small_bricks_connecting",
                        "stone_small_bricks_slab_connecting",
                        "stone_small_bricks_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(small_bricks_connectingSet);

        var small_tilesSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_small_tiles",
                "stone_small_tiles_slab",
                "stone_small_tiles_stairs",
                tab
        )
                .build();
        this.addEntries(small_tilesSet);

        var small_tiles_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_small_tiles_connecting",
                        "stone_small_tiles_slab_connecting",
                        "stone_small_tiles_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(small_tiles_connectingSet);

        var smooth_brick_pavingSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_smooth_brick_paving",
                "stone_smooth_brick_paving_slab",
                "stone_smooth_brick_paving_stairs",
                tab
        )
                .build();
        this.addEntries(smooth_brick_pavingSet);

        var smooth_brick_paving_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_smooth_brick_paving_connecting",
                        "stone_smooth_brick_paving_slab_connecting",
                        "stone_smooth_brick_paving_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(smooth_brick_paving_connectingSet);

        var smooth_large_tilesSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_smooth_large_tiles",
                "stone_smooth_large_tiles_slab",
                "stone_smooth_large_tiles_stairs",
                tab
        )
                .build();
        this.addEntries(smooth_large_tilesSet);

        var smooth_large_tiles_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_smooth_large_tiles_connecting",
                        "stone_smooth_large_tiles_slab_connecting",
                        "stone_smooth_large_tiles_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(smooth_large_tiles_connectingSet);

        var smooth_rotated_bricksSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_smooth_rotated_bricks",
                "stone_smooth_rotated_bricks_slab",
                "stone_smooth_rotated_bricks_stairs",
                tab
        )
                .build();
        this.addEntries(smooth_rotated_bricksSet);

        var smooth_rotated_bricks_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_smooth_rotated_bricks_connecting",
                        "stone_smooth_rotated_bricks_slab_connecting",
                        "stone_smooth_rotated_bricks_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(smooth_rotated_bricks_connectingSet);

        var smooth_tilesSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_smooth_tiles",
                "stone_smooth_tiles_slab",
                "stone_smooth_tiles_stairs",
                tab
        )
                .build();
        this.addEntries(smooth_tilesSet);

        var smooth_tiles_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_smooth_tiles_connecting",
                        "stone_smooth_tiles_slab_connecting",
                        "stone_smooth_tiles_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(smooth_tiles_connectingSet);

        var squaresSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_squares",
                "stone_squares_slab",
                "stone_squares_stairs",
                tab
        )
                .build();
        this.addEntries(squaresSet);

        var squares_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_squares_connecting",
                        "stone_squares_slab_connecting",
                        "stone_squares_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(squares_connectingSet);

        var wavesSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_waves",
                "stone_waves_slab",
                "stone_waves_stairs",
                tab
        )
                .build();
        this.addEntries(wavesSet);

        var waves_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_waves_connecting",
                        "stone_waves_slab_connecting",
                        "stone_waves_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(waves_connectingSet);

        var tilesSet = RechiseledBlockSetEntry.defaultOf(
                this, VanillaStoneTypes.STONE,
                "stone_tiles",
                "stone_tiles_slab",
                "stone_tiles_stairs",
                tab
        )
                .build();
        this.addEntries(tilesSet);

        var tiles_connectingSet = RechiseledBlockSetEntry.withConnected(
                this, VanillaStoneTypes.STONE, shortenedId(),
                        "stone_tiles_connecting",
                        "stone_tiles_slab_connecting",
                        "stone_tiles_stairs_connecting",
                        tab
                )
                .build();
        this.addEntries(tiles_connectingSet);

        var slatedSet = RechiseledBlockSetEntry.defaultWithPillarBlock(
                this, VanillaStoneTypes.STONE,
                "stone_slated", "_side", "_end",
                "stone_slated_slab",
                "stone_slated_stairs",
                tab
        )
                .build();
        this.addEntries(slatedSet);

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                    Dynamic Resources                     │
    //      └──────────────────────────────────────────────────────────┘

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->

                big_tilesSet.block.blocks.keySet().forEach(stoneType -> {
                    JsonArray entriesArray = new JsonArray();

                    // Adding all supported-blocks of a StoneType to Array

                    for (EntrySet<?> entry : this.getEntries()) {
                        SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                        String blockId = currentEntry.getName();

                        // Skip the blocks with "_connecting", "_slab", "_stairs"
                        if ( !blockId.contains("_connecting") && !blockId.contains("_slab") && !blockId.contains("_stairs") ) {
                            createAndAddEntry(entriesArray, stoneType, getModId(), blockId, "_slab", "_stairs");
                        }
                    }

                    // Adding vanilla blocks to entriesArray
                    createAndAddEntry(entriesArray, stoneType, "", STONE, SLAB, STAIRS);
                    createAndAddEntry(entriesArray, stoneType, "", BRICKS, BRICK_SLAB, BRICK_STAIRS);
                    createAndAddEntry(entriesArray, stoneType, "", MOSSY_BRICKS, MOSSY_BRICK_SLAB, MOSSY_BRICK_STAIRS);
                    createAndAddEntry(entriesArray, stoneType, "", SMOOTH, SMOOTH_SLAB, SMOOTH_STAIRS);
                    createAndAddEntry(entriesArray, stoneType, "", TILES, TILE_SLAB, TILE_STAIRS);

                    Block cracked_bricks = stoneType.getBlockOfThis(CRACKED_BRICKS);
                    if (cracked_bricks != null) entriesArray.add(Utils.getID(cracked_bricks).toString());

                    // Recipes
                    JsonObject chiseling_recipe = new JsonObject();
                    chiseling_recipe.addProperty("type", "rechiseled:chiseling");
                    chiseling_recipe.addProperty("overwrite", false);
                    chiseling_recipe.add("entries", entriesArray);

                    // Adding to resources
                    ResourceLocation resLoc = StoneZone.res("chiseling_recipes/" + stoneType.getAppendableId());
                    if (!entriesArray.isEmpty()) sink.addJson(resLoc, chiseling_recipe, ResType.JSON);

                })
        );
    }

    public void createAndAddEntry(JsonArray array, StoneType stoneType, String modId, String blockKey, String slabSuffix, String stairsSuffix) {
        JsonObject entry = new JsonObject();

        blockKey = (modId.isEmpty())
                ? blockKey // Vanilla's childkey
                : modId + ":" + blockKey; // EntrySet's childkey

        String slabKey = (modId.isEmpty())
                ? slabSuffix // Vanilla's childkey
                : blockKey + slabSuffix; // EntrySet's childkey

        String stairsKey = (modId.isEmpty())
                ? stairsSuffix // Vanilla's childkey
                : blockKey + stairsSuffix; // EntrySet's childkey

        // Get the other block with "_connecting"
        String blockConnectingId = blockKey + "_connecting";
        String slabConnectingId = slabKey + "_connecting";
        String stairsConnectingId = stairsKey  + "_connecting";

        // Blocks
        Block currentBlock = stoneType.getBlockOfThis(blockKey);
        Block currentSlab = stoneType.getBlockOfThis(slabKey);
        Block currentStairs = stoneType.getBlockOfThis(stairsKey);

        // Connecting Blocks
        Block blockConnecting = stoneType.getBlockOfThis(blockConnectingId);
        Block slabConnecting = stoneType.getBlockOfThis(slabConnectingId);
        Block stairsConnecting = stoneType.getBlockOfThis(stairsConnectingId);

        if (currentBlock != null) {

            /// Block
            entry.addProperty("block", Utils.getID(currentBlock).toString());
            if (blockConnecting != null)
                entry.addProperty("connecting_block", Utils.getID(blockConnecting).toString());

            /// Slab
            if (currentSlab != null) {
                entry.addProperty("slab", Utils.getID(currentSlab).toString());
                entry.addProperty("slab_worth", 0.5);
                if (slabConnecting != null)
                    entry.addProperty("connecting_slab", Utils.getID(slabConnecting).toString());
                entry.addProperty("connecting_slab_worth", 0.5);
            }

            /// Stairs
            if (currentStairs != null) {
                entry.addProperty("stairs", Utils.getID(currentStairs).toString());
                if (stairsConnecting != null)
                    entry.addProperty("connecting_stairs", Utils.getID(stairsConnecting).toString());
            }
        }

        if (!entry.isEmpty()) array.add(entry);
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         CLASSES                          │
    //      └──────────────────────────────────────────────────────────┘

    public static class RechiseledBlockSetEntry extends BlockSetEntry<StoneType> {

        protected RechiseledBlockSetEntry(
                SimpleEntrySet<StoneType, Block> block,
                SimpleEntrySet<StoneType, Block> stairs,
                SimpleEntrySet<StoneType, Block> slab,
                @Nullable SimpleEntrySet<StoneType, Block> wall) {
            super(block, stairs, slab, wall);
        }

        public static BlockSetBuilder<StoneType> standardOf(
                StoneZoneModule module, StoneType baseTypeId,
                String getBlockPath, Function<StoneType, Block> blockFactory, String side, String end,
                String getSlabPath,
                String getStairsPath,
                Supplier<CreativeModeTab> tabSupplier)
        {
            String childKeyPath = getChildKeyPath(getBlockPath, baseTypeId);

            return BlockSetEntry.of(StoneType.class, module, baseTypeId,
                            getBlockPath, blockFactory,
                            getSlabPath, type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone)),
                            getStairsPath, type ->
                                    new RechiseledStairBlock(false, getParentBlockstate(type, childKeyPath),
                                            Utils.copyPropertySafe(type.stone)
                                    ),
                            tabSupplier
                    )
                    .block(builder -> {
                                PaletteStrategy paletteStrategy = (getBlockPath.contains("smooth")) ? StonePaletteStrategies.SMOOTH_STANDARD : PaletteStrategies.MAIN_CHILD;

                                if (!side.isBlank() && !end.isBlank()) {
                                    builder.addTexture(modLoc(mod_id, "block/" + getBlockPath + side), paletteStrategy);
                                    builder.addTexture(modLoc(mod_id, "block/" + getBlockPath + end), paletteStrategy);
                                }
                                else
                                    builder.addTexture(modLoc(mod_id, "block/" + getBlockPath), paletteStrategy);
                            }
                            //RECIPES: - manually created
                    )
                    // included SLAB & STAIRS
                    //TEXTURES: block's texture
                    //RECIPES: - manually created
                    .pickaxeTag()
                    .noWall();
        }

        //      ┌───────────────────────────────┐
        //      │  RechiseledBlock()            │
        //      │  RechiseledSlabBlock()        │
        //      │  RechiseledStairBlock()       │
        //      └───────────────────────────────┘
        /// the connecting is set to false
        public static BlockSetBuilder<StoneType> defaultOf(
                StoneZoneModule module, StoneType baseTypeId,
                String getBlockPath,
                String getSlabPath,
                String getStairsPath,
                Supplier<CreativeModeTab> tabSupplier) {

            return standardOf(module, baseTypeId,
                    getBlockPath, type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone)),
                    "", "",
                    getSlabPath,
                    getStairsPath,
                    tabSupplier);
        }

        /// the connecting is set to true
        public static BlockSetBuilder<StoneType> withConnected(
                StoneZoneModule module, StoneType baseTypeId, String shortenedId,
                String getBlockPath,
                String getSlabPath,
                String getStairsPath,
                Supplier<CreativeModeTab> tabSupplier)
        {
            String childKeyPath = getChildKeyPath(getBlockPath, baseTypeId);

            return BlockSetEntry.of(StoneType.class, module, baseTypeId,
                            getBlockPath, type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone)),
                            getSlabPath, type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone)),
                            getStairsPath, type ->
                                    new RechiseledStairBlock(true, getParentBlockstate(type, childKeyPath),
                                            Utils.copyPropertySafe(type.stone)
                                    ),
                            tabSupplier
                    )
                    .block(builder -> builder
                                    //TEXTURES: of()'s blockTexture
                                    .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                                            s.replace("\"rechiseled:"+getBlockPath+"\"", "\""+ blockId.toString() +"\"")
                                                    .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                                            "\""+ stoneType.CreateStandardId(shortenedId, "",  "") + "_$1\"")

                                    ))
                            //RECIPES: - manually created
                    )
                    // included SLAB & STAIRS
                    //TEXTURES: block's texture
                    //RECIPES: - manually created
                    .pickaxeTag()
                    .noWall();
        }

        //      ┌────────────────────────────────┐
        //      │  RechiseledPillarBlock()       │
        //      │  RechiseledSlabBlock()         │
        //      │  RechiseledStairBlock()        │
        //      └────────────────────────────────┘
        public static BlockSetBuilder<StoneType> defaultWithPillarBlock(
                StoneZoneModule module, StoneType baseTypeId,
                String getBlockPath, String side, String end,
                String getSlabPath,
                String getStairsPath,
                Supplier<CreativeModeTab> tabSupplier) {

            return standardOf(module, baseTypeId,
                    getBlockPath, type -> new RechiseledPillarBlock(false, Utils.copyPropertySafe(type.stone)),
                    side, end,
                    getSlabPath,
                    getStairsPath,
                    tabSupplier);
        }

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                        Utilities                         │
        //      └──────────────────────────────────────────────────────────┘

        public static String getChildKeyPath(String baseblockPath, StoneType oldStoneType) {
            String typeName = oldStoneType.getTypeName();

            Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
            Matcher matcher = pattern.matcher(baseblockPath);

            if (matcher.find()) {
                String prefix = matcher.group("prefix");
                String suffix = matcher.group("suffix");

                if (prefix == null && suffix != null) return suffix;
                else if (prefix != null && suffix == null) return prefix;
                else return prefix +"_"+ suffix;
            }
            else {
                StoneZone.LOGGER.error("RechiseledModule - Failed to get DecorativeName for {}", baseblockPath);
                return "big_tiles";
            }
        }

        protected static ResourceLocation modLoc(String mod_id, String path) {
            return ResourceLocation.fromNamespaceAndPath(mod_id, path);
        }

        protected static BlockState getParentBlockstate(StoneType stoneType, String path) {
            return Objects.requireNonNull(stoneType.getBlockOfThis(mod_id + ":" + path)).defaultBlockState();
        }
    }

}
