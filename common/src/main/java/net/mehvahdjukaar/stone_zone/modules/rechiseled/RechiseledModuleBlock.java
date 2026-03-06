package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import com.supermartijn642.rechiseled.blocks.RechiseledPillarBlock;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;

// See RechiseledModuleAbstract's Supported Version
public class RechiseledModuleBlock extends RechiseledModuleAbstract {

    public final SimpleEntrySet<StoneType, Block> big_tiles, big_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> bordered, bordered_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_pattern, brick_pattern_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_paving, brick_paving_connecting;
    public final SimpleEntrySet<StoneType, Block> crushed, crushed_connecting;
    public final SimpleEntrySet<StoneType, Block> diagonal_bricks, diagonal_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> path, path_connecting;
    public final SimpleEntrySet<StoneType, Block> rotated_bricks, rotated_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> small_bricks, small_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> small_tiles, small_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_brick_paving, smooth_brick_paving_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_large_tiles, smooth_large_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_rotated_bricks, smooth_rotated_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_tiles, smooth_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> squares, squares_connecting;
    public final SimpleEntrySet<StoneType, Block> waves, waves_connecting;
    public final SimpleEntrySet<StoneType, Block> tiles, tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> slated;

    /* Skip them for now.
     * minecraft:smooth_stone
     * rechiseled:stone_smooth_connecting
     * minecraft:chiseled_stone_bricks
     * rechiseled:stone_chiseled_bricks_connecting
    */

    public RechiseledModuleBlock(String modId) {
        super(modId);

        big_tiles = StoneZoneEntrySet.of(StoneType.class, "big_tiles",
                        getModBlock("stone_big_tiles"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_big_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles);

        big_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "big_tiles_connecting",
                        getModBlock("stone_big_tiles_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_big_tiles_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles_connecting);

        bordered = StoneZoneEntrySet.of(StoneType.class, "bordered",
                        getModBlock("stone_bordered"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_bordered"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered);

        bordered_connecting = StoneZoneEntrySet.of(StoneType.class, "bordered_connecting",
                        getModBlock("stone_bordered_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: bordered (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_bordered_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered_connecting);

        brick_pattern = StoneZoneEntrySet.of(StoneType.class, "brick_pattern",
                        getModBlock("stone_brick_pattern"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_brick_pattern"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern);

        brick_pattern_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_pattern_connecting",
                        getModBlock("stone_brick_pattern_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: brick_pattern (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_brick_pattern_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern_connecting);

        brick_paving = StoneZoneEntrySet.of(StoneType.class, "brick_paving",
                        getModBlock("stone_brick_paving"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_brick_paving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving);

        brick_paving_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_paving_connecting",
                        getModBlock("stone_brick_paving_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: brick_paving (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_brick_paving_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving_connecting);

        crushed = StoneZoneEntrySet.of(StoneType.class, "crushed",
                        getModBlock("stone_crushed"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_crushed"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed);

        crushed_connecting = StoneZoneEntrySet.of(StoneType.class, "crushed_connecting",
                        getModBlock("stone_crushed_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: crushed (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_crushed_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed_connecting);

        diagonal_bricks = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks",
                        getModBlock("stone_diagonal_bricks"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_diagonal_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks);

        diagonal_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks_connecting",
                        getModBlock("stone_diagonal_bricks_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: diagonal_bricks (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_diagonal_bricks_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks_connecting);

        path = StoneZoneEntrySet.of(StoneType.class, "path",
                        getModBlock("stone_path"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_path"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path);

        path_connecting = StoneZoneEntrySet.of(StoneType.class, "path_connecting",
                        getModBlock("stone_path_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: path (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_path_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path_connecting);

        rotated_bricks = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks",
                        getModBlock("stone_rotated_bricks"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_rotated_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks);

        rotated_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks_connecting",
                        getModBlock("stone_rotated_bricks_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: rotated_bricks (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_rotated_bricks_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks_connecting);

        small_bricks = StoneZoneEntrySet.of(StoneType.class, "small_bricks",
                        getModBlock("stone_small_bricks"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_small_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks);

        small_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "small_bricks_connecting",
                        getModBlock("stone_small_bricks_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: small_bricks (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_small_bricks_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks_connecting);

        small_tiles = StoneZoneEntrySet.of(StoneType.class, "small_tiles",
                        getModBlock("stone_small_tiles"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_small_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles);

        small_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "small_tiles_connecting",
                        getModBlock("stone_small_tiles_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: small_tiles (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_small_tiles_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles_connecting);

        smooth_brick_paving = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving",
                        getModBlock("stone_smooth_brick_paving"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_smooth_brick_paving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving);

        smooth_brick_paving_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving_connecting",
                        getModBlock("stone_smooth_brick_paving_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: smooth_brick_paving (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_smooth_brick_paving_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving_connecting);

        smooth_large_tiles = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles",
                        getModBlock("stone_smooth_large_tiles"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_smooth_large_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles);

        smooth_large_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles_connecting",
                        getModBlock("stone_smooth_large_tiles_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: smooth_large_tiles (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_smooth_large_tiles_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles_connecting);

        smooth_rotated_bricks = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks",
                        getModBlock("stone_smooth_rotated_bricks"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_smooth_rotated_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks);

        smooth_rotated_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_connecting",
                        getModBlock("stone_smooth_rotated_bricks_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: smooth_rotated_bricks (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_smooth_rotated_bricks_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_connecting);

        smooth_tiles = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles",
                        getModBlock("stone_smooth_tiles"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_smooth_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles);

        smooth_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles_connecting",
                        getModBlock("stone_smooth_tiles_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: smooth_tiles (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_smooth_tiles_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles_connecting);

        squares = StoneZoneEntrySet.of(StoneType.class, "squares",
                        getModBlock("stone_squares"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_squares"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares);

        squares_connecting = StoneZoneEntrySet.of(StoneType.class, "squares_connecting",
                        getModBlock("stone_squares_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: squares (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_squares_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares_connecting);

        waves = StoneZoneEntrySet.of(StoneType.class, "waves",
                        getModBlock("stone_waves"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_waves"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves);

        waves_connecting = StoneZoneEntrySet.of(StoneType.class, "waves_connecting",
                        getModBlock("stone_waves_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: waves (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_waves_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves_connecting);

        tiles = StoneZoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles);

        tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "tiles_connecting",
                        getModBlock("stone_tiles_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: tiles (above)
                                .addModelTransform(m -> m.addModifier((s, blockId, stoneType) ->
                        s.replace("\"rechiseled:stone_tiles_connecting\"", "\""+ blockId.toString() +"\"")
                                .replaceAll("\"rechiseled:stone_(\\w+)\"",
                                        "\""+ stoneType.CreateStandardId(shortenedId(), "",  "") + "_$1\"")

                ))
                .requiresFromMap(tiles.blocks)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles_connecting);

        slated = StoneZoneEntrySet.of(StoneType.class, "slated",
                        getModBlock("stone_slated"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledPillarBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_slated_end"))
                .addTexture(modRes("block/stone_slated_side"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(slated);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->

            big_tiles.blocks.forEach((stoneType, block) -> {
                JsonArray entriesArray = new JsonArray();

                // Adding all supported-blocks of a StoneType to Array

                for (EntrySet<?> entry : this.getEntries()) {
                    SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                    String blockId = currentEntry.getName();

                    if (!blockId.contains("_connecting")) { // Skip the blocks with "_connecting"
                        createAndAddEntry(entriesArray, stoneType, modId, blockId, "_slab", "_stairs");
                    }
                }

                // Adding vanilla blocks to entriesArray
                createAndAddEntry(entriesArray, stoneType, "", STONE, SLAB, STAIRS);
                createAndAddEntry(entriesArray, stoneType, "", BRICKS, BRICK_SLAB, BRICK_STAIRS);
                createAndAddEntry(entriesArray, stoneType, "", MOSSY_BRICKS, MOSSY_BRICK_SLAB, MOSSY_BRICK_STAIRS);
                createAndAddEntry(entriesArray, stoneType, "", SMOOTH, SMOOTH_SLAB, SMOOTH_STAIRS);
                createAndAddEntry(entriesArray, stoneType, "", TILES, TILE_SLAB, TILE_STAIRS);

                Block cracked_bricks = stoneType.getBlockOfThis(CRACKED_BRICKS);
                if (Objects.nonNull(cracked_bricks)) entriesArray.add(Utils.getID(cracked_bricks).toString());

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

    public void createAndAddEntry(JsonArray array, StoneType stoneType, String modId, String blockId, String slabSuffix, String stairsSuffix) {
        JsonObject entry = new JsonObject();

        blockId = (modId.isEmpty()) ? blockId : modId + ":" + blockId;
        String slabId = (slabSuffix.contains("_")) ? blockId + slabSuffix : slabSuffix;
        String stairsId = (stairsSuffix.contains("_")) ? blockId + stairsSuffix : stairsSuffix;

        // Get the other block with "_connecting"
        String blockConnectingId = blockId + "_connecting";
        String slabConnectingId = slabId + "_connecting";
        String stairsConnectingId = stairsId  + "_connecting";

        // Blocks
        Block currentBlock = stoneType.getBlockOfThis(blockId);
        Block currentSlab = stoneType.getBlockOfThis(slabId);
        Block currentStairs = stoneType.getBlockOfThis(stairsId);

        // Connecting Blocks
        Block blockConnecting = stoneType.getBlockOfThis(blockConnectingId);
        Block slabConnecting = stoneType.getBlockOfThis(slabConnectingId);
        Block stairsConnecting = stoneType.getBlockOfThis(stairsConnectingId);

        if (Objects.nonNull(currentBlock)) {

            entry.addProperty("block", Utils.getID(currentBlock).toString());
            if (Objects.nonNull(blockConnecting))
                entry.addProperty("connecting_block", Utils.getID(blockConnecting).toString());
            if (Objects.nonNull(currentSlab)) {
                entry.addProperty("slab", Utils.getID(currentSlab).toString());
                entry.addProperty("slab_worth", 0.5);
                if (Objects.nonNull(slabConnecting))
                    entry.addProperty("connecting_slab", Utils.getID(slabConnecting).toString());
                entry.addProperty("connecting_slab_worth", 0.5);
            }
            if (Objects.nonNull(currentStairs)) {
                entry.addProperty("stairs", Utils.getID(currentStairs).toString());
                if (Objects.nonNull(stairsConnecting))
                    entry.addProperty("connecting_stairs", Utils.getID(stairsConnecting).toString());
            }
        }

        if (!entry.isEmpty()) array.add(entry);
    }


}