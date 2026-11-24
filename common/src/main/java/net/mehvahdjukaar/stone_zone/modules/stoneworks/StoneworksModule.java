package net.mehvahdjukaar.stone_zone.modules.stoneworks;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.api.BlockTypeChildrenHelper.copyChildProperties;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.SLAB;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.STAIRS;

///SUPPORT: v26.1.0+
public class StoneworksModule extends StoneZoneModule {

    public final BlockSetEntries<StoneType> tilesSet,
            platesSet,
            paversSet,
            shinglesSet;

    public final SimpleEntrySet<StoneType, Block> cracked_tiles;
    public final SimpleEntrySet<StoneType, Block> pillars;

    public StoneworksModule(String modId) {
        super(modId, "sw");
        Supplier<CreativeModeTab> tab = getModTab("main");

        tilesSet = BlockSetEntries.of4Blocks(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_tiles",
                        "stone_tile_slab",
                        "stone_tile_stairs",
                        "stone_tile_wall",
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_tiles
                        //RECIPES: stone
                        .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                        // copyProperties: stoneType.stone
                )
                .slab(config -> config
                        //TEXTURES: stone_tiles (above)
                        .addRecipe(modRes("stone_tile_slab_from_stone_or_stone_tiles_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, SLAB))
                )
                .stairs(config -> config
                        //TEXTURES: stone_tiles (above)
                        .addRecipe(modRes("stone_tile_stairs_from_stone_or_stone_tiles_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, STAIRS))
                )
                .wall(config -> config
                        //TEXTURES: stone_tiles (above)
                        .addRecipe(modRes("stone_tile_wall_from_stone_or_stone_tiles_stonecutting"))
                        .setProperties(stoneType -> copyWallSafe("stone_tile_wall", stoneType))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .build();
        this.addEntries(tilesSet);

        platesSet = BlockSetEntries.of4Blocks(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_plates",
                        "stone_plate_slab",
                        "stone_plate_stairs",
                        "stone_plate_wall",
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_plates
                        //RECIPES: stone
                        .addRecipe(modRes("stone_plates_from_stone_stonecutting"))
                        // copyProperties: stoneType.stone
                )
                .slab(config -> config
                        //TEXTURES: stone_plates (above)
                        .addRecipe(modRes("stone_plate_slab_from_stone_or_stone_plates_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, SLAB))
                )
                .stairs(config -> config
                        //TEXTURES: stone_plates (above)
                        .addRecipe(modRes("stone_plate_stairs_from_stone_or_stone_plates_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, STAIRS))
                )
                .wall(config -> config
                        //TEXTURES: stone_plates (above)
                        .addRecipe(modRes("stone_plate_wall_from_stone_or_stone_plates_stonecutting"))
                        .setProperties(stoneType -> copyWallSafe("stone_tile_wall", stoneType))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .build();
        this.addEntries(platesSet);

        paversSet = BlockSetEntries.of4Blocks(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_pavers",
                        "stone_paver_slab",
                        "stone_paver_stairs",
                        "stone_paver_wall",
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_pavers
                        //RECIPES: stone
                        .addRecipe(modRes("stone_pavers_from_stone_stonecutting"))
                        // copyProperties: stoneType.stone
                )
                .slab(config -> config
                        //TEXTURES: stone_pavers (above)
                        .addRecipe(modRes("stone_paver_slab_from_stone_or_stone_pavers_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, SLAB))
                )
                .stairs(config -> config
                        //TEXTURES: stone_pavers (above)
                        .addRecipe(modRes("stone_paver_stairs_from_stone_or_stone_pavers_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, STAIRS))
                )
                .wall(config -> config
                        //TEXTURES: stone_pavers (above)
                        .addRecipe(modRes("stone_paver_wall_from_stone_or_stone_pavers_stonecutting"))
                        .setProperties(stoneType -> copyWallSafe("stone_tile_wall", stoneType))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .build();
        this.addEntries(paversSet);

        shinglesSet = BlockSetEntries.of4Blocks(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_shingles",
                        "stone_shingle_slab",
                        "stone_shingle_stairs",
                        "stone_shingle_wall",
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_shingles
                        //RECIPES: stone
                        .addRecipe(modRes("stone_shingles_from_stone_stonecutting"))
                        // copyProperties: stoneType.stone
                )
                .slab(config -> config
                        //TEXTURES: stone_shingles (above)
                        .addRecipe(modRes("stone_shingle_slab_from_stone_or_stone_shingles_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, SLAB))
                )
                .stairs(config -> config
                        //TEXTURES: stone_shingles (above)
                        .addRecipe(modRes("stone_shingle_stairs_from_stone_or_stone_shingles_stonecutting"))
                        .setProperties(stoneType -> copyChildProperties(stoneType, STAIRS))
                )
                .wall(config -> config
                        //TEXTURES: stone_shingles (above)
                        .addRecipe(modRes("stone_shingle_wall_from_stone_or_stone_shingles_stonecutting"))
                        .setProperties(stoneType -> copyWallSafe("stone_tile_wall", stoneType))
                )
                .defaultBlockTexture()
                .pickaxeTag()
                .build();
        this.addEntries(shinglesSet);

        cracked_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "cracked",
                        getModBlock("cracked_stone_tiles"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new Block(properties)
                )
                .addTexture(modRes("block/cracked_stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .addRecipe(modRes("cracked_stone_tiles_from_stone_stonecutting"))
                .build();
        this.addEntry(cracked_tiles);

        pillars = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("stone_pillar"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RotatedPillarBlock(properties)
                )
                .addTexture(modRes("block/stone_pillar_top"))
                .addTexture(modRes("block/stone_pillar_side"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .addRecipe(modRes("stone_pillar_from_stone_stonecutting"))
                .build();
        this.addEntry(pillars);

    }

    public BlockBehaviour.Properties copyWallSafe(String baseType, StoneType stoneType) {
        return Utils.copyPropertySafe(
                (Objects.nonNull(stoneType.getBlockOfThis("wall")))
                ? Objects.requireNonNull(stoneType.getBlockOfThis("wall"))
                : getModBlock(baseType).get()
        );
    }
}