package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.block.BlockProperties;
import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.*;

//SUPPORT: v1.1.6+
public class RechiseledModule extends SZModule {

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

    public RechiseledModule(String modId) {
        super(modId, "rcd");
        ResourceLocation tab = modRes(modId);

        big_tiles = StonezoneEntrySet.of(StoneType.class, "big_tiles",
                        getModBlock("stone_big_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_big_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles);

        big_tiles_connecting = StonezoneEntrySet.of(StoneType.class, "big_tiles_connecting",
                        getModBlock("stone_big_tiles_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles_connecting);

        bordered = StonezoneEntrySet.of(StoneType.class, "bordered",
                        getModBlock("stone_bordered"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_bordered")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered);

        bordered_connecting = StonezoneEntrySet.of(StoneType.class, "bordered_connecting",
                        getModBlock("stone_bordered_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered_connecting);

        brick_pattern = StonezoneEntrySet.of(StoneType.class, "brick_pattern",
                        getModBlock("stone_brick_pattern"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_brick_pattern")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern);

        brick_pattern_connecting = StonezoneEntrySet.of(StoneType.class, "brick_pattern_connecting",
                        getModBlock("stone_brick_pattern_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern_connecting);

        brick_paving = StonezoneEntrySet.of(StoneType.class, "brick_paving",
                        getModBlock("stone_brick_paving"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_brick_paving")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving);

        brick_paving_connecting = StonezoneEntrySet.of(StoneType.class, "brick_paving_connecting",
                        getModBlock("stone_brick_paving_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving_connecting);

        crushed = StonezoneEntrySet.of(StoneType.class, "crushed",
                        getModBlock("stone_crushed"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_crushed")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed);

        crushed_connecting = StonezoneEntrySet.of(StoneType.class, "crushed_connecting",
                        getModBlock("stone_crushed_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed_connecting);

        diagonal_bricks = StonezoneEntrySet.of(StoneType.class, "diagonal_bricks",
                        getModBlock("stone_diagonal_bricks"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_diagonal_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks);

        diagonal_bricks_connecting = StonezoneEntrySet.of(StoneType.class, "diagonal_bricks_connecting",
                        getModBlock("stone_diagonal_bricks_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks_connecting);

        path = StonezoneEntrySet.of(StoneType.class, "path",
                        getModBlock("stone_path"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_path")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path);

        path_connecting = StonezoneEntrySet.of(StoneType.class, "path_connecting",
                        getModBlock("stone_path_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path_connecting);

        rotated_bricks = StonezoneEntrySet.of(StoneType.class, "rotated_bricks",
                        getModBlock("stone_rotated_bricks"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_rotated_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks);

        rotated_bricks_connecting = StonezoneEntrySet.of(StoneType.class, "rotated_bricks_connecting",
                        getModBlock("stone_rotated_bricks_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks_connecting);

        small_bricks = StonezoneEntrySet.of(StoneType.class, "small_bricks",
                        getModBlock("stone_small_bricks"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_small_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks);

        small_bricks_connecting = StonezoneEntrySet.of(StoneType.class, "small_bricks_connecting",
                        getModBlock("stone_small_bricks_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks_connecting);

        small_tiles = StonezoneEntrySet.of(StoneType.class, "small_tiles",
                        getModBlock("stone_small_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_small_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles);

        small_tiles_connecting = StonezoneEntrySet.of(StoneType.class, "small_tiles_connecting",
                        getModBlock("stone_small_tiles_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles_connecting);

        smooth_brick_paving = StonezoneEntrySet.of(StoneType.class, "smooth_brick_paving",
                        getModBlock("stone_smooth_brick_paving"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_brick_paving")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving);

        smooth_brick_paving_connecting = StonezoneEntrySet.of(StoneType.class, "smooth_brick_paving_connecting",
                        getModBlock("stone_smooth_brick_paving_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving_connecting);

        smooth_large_tiles = StonezoneEntrySet.of(StoneType.class, "smooth_large_tiles",
                        getModBlock("stone_smooth_large_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_large_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles);

        smooth_large_tiles_connecting = StonezoneEntrySet.of(StoneType.class, "smooth_large_tiles_connecting",
                        getModBlock("stone_smooth_large_tiles_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles_connecting);

        smooth_rotated_bricks = StonezoneEntrySet.of(StoneType.class, "smooth_rotated_bricks",
                        getModBlock("stone_smooth_rotated_bricks"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_rotated_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks);

        smooth_rotated_bricks_connecting = StonezoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_connecting",
                        getModBlock("stone_smooth_rotated_bricks_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_connecting);

        smooth_tiles = StonezoneEntrySet.of(StoneType.class, "smooth_tiles",
                        getModBlock("stone_smooth_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles);

        smooth_tiles_connecting = StonezoneEntrySet.of(StoneType.class, "smooth_tiles_connecting",
                        getModBlock("stone_smooth_tiles_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles_connecting);

        squares = StonezoneEntrySet.of(StoneType.class, "squares",
                        getModBlock("stone_squares"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_squares")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares);

        squares_connecting = StonezoneEntrySet.of(StoneType.class, "squares_connecting",
                        getModBlock("stone_squares_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares_connecting);

        waves = StonezoneEntrySet.of(StoneType.class, "waves",
                        getModBlock("stone_waves"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_waves")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves);

        waves_connecting = StonezoneEntrySet.of(StoneType.class, "waves_connecting",
                        getModBlock("stone_waves_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves_connecting);

        tiles = StonezoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles);

        tiles_connecting = StonezoneEntrySet.of(StoneType.class, "tiles_connecting",
                        getModBlock("stone_tiles_connecting"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(true, BlockProperties.copy(type.stone))
                )
                .requiresFromMap(tiles.blocks)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles_connecting);

        slated = StonezoneEntrySet.of(StoneType.class, "slated",
                        getModBlock("stone_slated"), () -> StoneTypeRegistry.STONE_TYPE,
                        type -> new RechiseledBlock(false, BlockProperties.copy(type.stone))
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
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        big_tiles.blocks.forEach((stoneType, block) -> {

            // Adding all supported-blocks of a StoneType to Array
            JsonArray entriesArray = new JsonArray();

            for (var entry : this.getEntries()) {
                JsonObject entryObj = new JsonObject();

                SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                String currentName = currentEntry.getName();

                // Get the other block with "_connecting"
                String entryKey = currentName + "_connecting";

                if (!currentName.contains("_connecting")) { // Skip the blocks with "_connecting"
                    Block currentBlock = currentEntry.blocks.get(stoneType);
                    if (Objects.nonNull(currentBlock)) {
                        entryObj.addProperty("item", Utils.getID(currentBlock).toString());

                        if (!currentName.matches("slated")) { // Skip blc it don't have "_connecting" block
                            StonezoneEntrySet<?, ?> otherEntry = (StonezoneEntrySet<?, ?>) this.getEntry(entryKey);
                            Block otherBlock = otherEntry.blocks.get(stoneType);
                            if (Objects.nonNull(otherBlock))
                                entryObj.addProperty("connecting_item", Utils.getID(otherBlock).toString());
                        }

                        entriesArray.add(entryObj);
                    }
                }
            }

            // Adding vanilla blocks to Array
            String[] vanillaBlocks = {
                    "stone",
                    "bricks",
                    "mossy_bricks",
                    "cracked_bricks",
                    "smooth_stone"
            };

            for (var key : vanillaBlocks) {
                Block currentBlock = stoneType.getBlockOfThis(key);
                if (Objects.nonNull(currentBlock)) {
                    JsonObject entryObj = new JsonObject();
                    entryObj.addProperty("item", Utils.getID(currentBlock).toString());
                    entriesArray.add(entryObj);
                }
            }

            // Recipes
            JsonObject chiseling_recipe = new JsonObject();
            chiseling_recipe.addProperty("type", "rechiseled:chiseling");
            chiseling_recipe.addProperty("overwrite", false);
            chiseling_recipe.add("entries", entriesArray);

            // Adding to resources
            ResourceLocation resLoc = StoneZone.res("chiseling_recipes/" + stoneType.getAppendableId());
            handler.dynamicPack.addJson(resLoc, chiseling_recipe, ResType.JSON);

        });
    }
}