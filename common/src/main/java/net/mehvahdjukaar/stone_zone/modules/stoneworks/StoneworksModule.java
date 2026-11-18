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

//SUPPORT: v21.1.0+
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

        tilesSet = BlockSetEntries.of4BlocksProperty(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_tiles", stoneType -> Utils.copyPropertySafe(stoneType.stone),
                        "stone_tile_slab", stoneType -> copyChildProperties(stoneType, SLAB),
                        "stone_tile_stairs", stoneType -> copyChildProperties(stoneType, STAIRS),
                        "stone_tile_wall", stoneType -> copyWallSafe("stone_tile_wall", stoneType),
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_tiles
                        //RECIPES: stone
                        .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                )
                .slab(config -> config
                        //TEXTURES: stone_tiles (above)
                        .addRecipe(modRes("stone_tile_slab_from_stone_or_stone_tiles_stonecutting"))
                )
                .stairs(config -> config
                        //TEXTURES: stone_tiles (above)
                        .addRecipe(modRes("stone_tile_stairs_from_stone_or_stone_tiles_stonecutting"))
                )
                .wall(config -> config
                        //TEXTURES: stone_tiles (above)
                        .addRecipe(modRes("stone_tile_wall_from_stone_or_stone_tiles_stonecutting"))
                )
                .pickaxeTag()
                .build();
        this.addEntries(tilesSet);

        platesSet = BlockSetEntries.of4BlocksProperty(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_plates", stoneType -> Utils.copyPropertySafe(stoneType.stone),
                        "stone_plate_slab", stoneType -> copyChildProperties(stoneType, SLAB),
                        "stone_plate_stairs", stoneType -> copyChildProperties(stoneType, STAIRS),
                        "stone_plate_wall", stoneType -> copyWallSafe("stone_tile_wall", stoneType),
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_plates
                        //RECIPES: stone
                        .addRecipe(modRes("stone_plates_from_stone_stonecutting"))
                )
                .slab(config -> config
                        //TEXTURES: stone_plates (above)
                        .addRecipe(modRes("stone_plate_slab_from_stone_or_stone_plates_stonecutting"))
                )
                .stairs(config -> config
                        //TEXTURES: stone_plates (above)
                        .addRecipe(modRes("stone_plate_stairs_from_stone_or_stone_plates_stonecutting"))
                )
                .wall(config -> config
                        //TEXTURES: stone_plates (above)
                        .addRecipe(modRes("stone_plate_wall_from_stone_or_stone_plates_stonecutting"))
                )
                .pickaxeTag()
                .build();
        this.addEntries(platesSet);

        paversSet = BlockSetEntries.of4BlocksProperty(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_pavers", stoneType -> Utils.copyPropertySafe(stoneType.stone),
                        "stone_paver_slab", stoneType -> copyChildProperties(stoneType, SLAB),
                        "stone_paver_stairs", stoneType -> copyChildProperties(stoneType, STAIRS),
                        "stone_paver_wall", stoneType -> copyWallSafe("stone_tile_wall", stoneType),
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_pavers
                        //RECIPES: stone
                        .addRecipe(modRes("stone_pavers_from_stone_stonecutting"))
                )
                .slab(config -> config
                        //TEXTURES: stone_pavers (above)
                        .addRecipe(modRes("stone_paver_slab_from_stone_or_stone_pavers_stonecutting"))
                )
                .stairs(config -> config
                        //TEXTURES: stone_pavers (above)
                        .addRecipe(modRes("stone_paver_stairs_from_stone_or_stone_pavers_stonecutting"))
                )
                .wall(config -> config
                        //TEXTURES: stone_pavers (above)
                        .addRecipe(modRes("stone_paver_wall_from_stone_or_stone_pavers_stonecutting"))
                )
                .pickaxeTag()
                .build();
        this.addEntries(paversSet);

        shinglesSet = BlockSetEntries.of4BlocksProperty(StoneType.class, this, VanillaStoneTypes.STONE,
                        "stone_shingles", stoneType -> Utils.copyPropertySafe(stoneType.stone),
                        "stone_shingle_slab", stoneType -> copyChildProperties(stoneType, SLAB),
                        "stone_shingle_stairs", stoneType -> copyChildProperties(stoneType, STAIRS),
                        "stone_shingle_wall", stoneType -> copyWallSafe("stone_tile_wall", stoneType),
                        tab
                )
                .block(config -> config
                        //addTEXTURES: stone_shingles
                        //RECIPES: stone
                        .addRecipe(modRes("stone_shingles_from_stone_stonecutting"))
                )
                .slab(config -> config
                        //TEXTURES: stone_shingles (above)
                        .addRecipe(modRes("stone_shingle_slab_from_stone_or_stone_shingles_stonecutting"))
                )
                .stairs(config -> config
                        //TEXTURES: stone_shingles (above)
                        .addRecipe(modRes("stone_shingle_stairs_from_stone_or_stone_shingles_stonecutting"))
                )
                .wall(config -> config
                        //TEXTURES: stone_shingles (above)
                        .addRecipe(modRes("stone_shingle_wall_from_stone_or_stone_shingles_stonecutting"))
                )
                .pickaxeTag()
                .build();
        this.addEntries(shinglesSet);

        cracked_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "cracked",
                        getModBlock("cracked_stone_tiles"), () -> VanillaStoneTypes.STONE,
                        (stoneType, properties) -> new Block(properties)
                )
                .addTexture(modRes("block/cracked_stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .addRecipe(modRes("cracked_stone_tiles_from_stone_stonecutting"))
                .build();
        this.addEntry(cracked_tiles);

        pillars = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("stone_pillar"), () -> VanillaStoneTypes.STONE,
                        (stoneType, properties) -> new RotatedPillarBlock(properties)
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