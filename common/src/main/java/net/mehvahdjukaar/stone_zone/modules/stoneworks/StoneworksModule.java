package net.mehvahdjukaar.stone_zone.modules.stoneworks;

import fuzs.stoneworks.Stoneworks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyBlockStateSafe;
import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;


//SUPPORT: v21.1.0+
public class StoneworksModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> tiles;
    public final SimpleEntrySet<StoneType, Block> tile_stairs;
    public final SimpleEntrySet<StoneType, Block> tile_slabs;
    public final SimpleEntrySet<StoneType, Block> tile_walls;

    public final SimpleEntrySet<StoneType, Block> cracked_tiles;
    public final SimpleEntrySet<StoneType, Block> pillars;

    public final SimpleEntrySet<StoneType, Block> plates;
    public final SimpleEntrySet<StoneType, Block> plate_stairs;
    public final SimpleEntrySet<StoneType, Block> plate_slabs;
    public final SimpleEntrySet<StoneType, Block> plate_walls;

    public final SimpleEntrySet<StoneType, Block> pavers;
    public final SimpleEntrySet<StoneType, Block> paver_stairs;
    public final SimpleEntrySet<StoneType, Block> paver_slabs;
    public final SimpleEntrySet<StoneType, Block> paver_walls;

    public final SimpleEntrySet<StoneType, Block> shingles;
    public final SimpleEntrySet<StoneType, Block> shingle_stairs;
    public final SimpleEntrySet<StoneType, Block> shingle_slabs;
    public final SimpleEntrySet<StoneType, Block> shingle_walls;

    public StoneworksModule(String modId) {
        super(modId, "sw");
        ResourceLocation tab = modRes(Stoneworks.MOD_ID);

        tiles = StoneZoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), StoneTypeRegistry::getStoneType,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .requiresChildren("stone") //REASON: recipes
                .addTexture(modRes("block/stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(tiles);

        tile_stairs = StoneZoneEntrySet.of(StoneType.class, "tile_stairs",
                        getModBlock("stone_tile_stairs"), StoneTypeRegistry::getStoneType,
                        s -> new StairBlock(copyBlockStateSafe(tiles.blocks, s),
                                copyChildrenPropertySafe("stairs", s)
                        )
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes & textures
                //TEXTURES: stone_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_stairs_from_stone_or_stone_tiles_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(tile_stairs);

        tile_slabs = StoneZoneEntrySet.of(StoneType.class, "tile_slab",
                        getModBlock("stone_tile_slab"), StoneTypeRegistry::getStoneType,
                        s -> new SlabBlock(copyChildrenPropertySafe("slab", s))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes & textures
                //TEXTURES: stone_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_slab_from_stone_or_stone_tiles_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(tile_slabs);

        tile_walls = StoneZoneEntrySet.of(StoneType.class, "tile_wall",
                        getModBlock("stone_tile_wall"), StoneTypeRegistry::getStoneType,
                        s -> new WallBlock(copyWallSafe("stone_tile_wall", s))
                )
                .requiresFromMap(tiles.blocks) //REASON: recipes & textures
                //TEXTURES: stone_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_wall_from_stone_or_stone_tiles_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(tile_walls);

        cracked_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "cracked",
                        getModBlock("cracked_stone_tiles"), StoneTypeRegistry::getStoneType,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/cracked_stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cracked_stone_tiles_from_stone_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(cracked_tiles);

        pillars = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("stone_pillar"), StoneTypeRegistry::getStoneType,
                        s -> new RotatedPillarBlock(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_pillar_top"))
                .addTexture(modRes("block/stone_pillar_side"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_pillar_from_stone_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(pillars);

        plates = StoneZoneEntrySet.of(StoneType.class, "plates",
                        getModBlock("stone_plates"), StoneTypeRegistry::getStoneType,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_plates"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plates_from_stone_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(plates);

        plate_stairs = StoneZoneEntrySet.of(StoneType.class, "plate_stairs",
                        getModBlock("stone_plate_stairs"), StoneTypeRegistry::getStoneType,
                        s -> new StairBlock(s.stone.defaultBlockState(),
                                copyChildrenPropertySafe("stairs", s)
                        )
                )
                .requiresFromMap(plates.blocks) //REASON: recipes & textures
                //TEXTURES: stone_plates (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plate_stairs_from_stone_or_stone_plates_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(plate_stairs);

        plate_slabs = StoneZoneEntrySet.of(StoneType.class, "plate_slab",
                        getModBlock("stone_plate_slab"), StoneTypeRegistry::getStoneType,
                        s -> new SlabBlock(copyChildrenPropertySafe("slab", s))
                )
                .requiresFromMap(plates.blocks) //REASON: recipes & textures
                //TEXTURES: stone_plates (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plate_slab_from_stone_or_stone_plates_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(plate_slabs);

        plate_walls = StoneZoneEntrySet.of(StoneType.class, "plate_wall",
                        getModBlock("stone_plate_wall"), StoneTypeRegistry::getStoneType,
                        s -> new WallBlock(copyWallSafe("stone_plate_wall", s))
                )
                .requiresFromMap(plates.blocks) //REASON: recipes & textures
                //TEXTURES: stone_plates (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plate_wall_from_stone_or_stone_plates_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(plate_walls);

        pavers = StoneZoneEntrySet.of(StoneType.class, "pavers",
                        getModBlock("stone_pavers"), StoneTypeRegistry::getStoneType,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_pavers"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_pavers_from_stone_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(pavers);

        paver_stairs = StoneZoneEntrySet.of(StoneType.class, "paver_stairs",
                        getModBlock("stone_paver_stairs"), StoneTypeRegistry::getStoneType,
                        s -> new StairBlock(s.stone.defaultBlockState(),
                                copyChildrenPropertySafe("stairs", s)
                        )
                )
                .requiresFromMap(pavers.blocks) //REASON: recipes & textures
                //TEXTURES: stone_pavers (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_paver_stairs_from_stone_or_stone_pavers_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(paver_stairs);

        paver_slabs = StoneZoneEntrySet.of(StoneType.class, "paver_slab",
                        getModBlock("stone_paver_slab"), StoneTypeRegistry::getStoneType,
                        s -> new SlabBlock(copyChildrenPropertySafe("slab", s))
                )
                .requiresFromMap(pavers.blocks) //REASON: recipes & textures
                //TEXTURES: stone_pavers (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_paver_slab_from_stone_or_stone_pavers_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(paver_slabs);

        paver_walls = StoneZoneEntrySet.of(StoneType.class, "paver_wall",
                        getModBlock("stone_paver_wall"), StoneTypeRegistry::getStoneType,
                        s -> new WallBlock(copyWallSafe("stone_paver_wall", s))
                )
                .requiresFromMap(pavers.blocks) //REASON: recipes & textures
                //TEXTURES: stone_pavers (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_paver_wall_from_stone_or_stone_pavers_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(paver_walls);

        shingles = StoneZoneEntrySet.of(StoneType.class, "shingles",
                        getModBlock("stone_shingles"), StoneTypeRegistry::getStoneType,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_shingles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingles_from_stone_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(shingles);

        shingle_stairs = StoneZoneEntrySet.of(StoneType.class, "shingle_stairs",
                        getModBlock("stone_shingle_stairs"), StoneTypeRegistry::getStoneType,
                        s -> new StairBlock(s.stone.defaultBlockState(),
                                copyChildrenPropertySafe("stairs", s)
                        )
                )
                .requiresFromMap(shingles.blocks) //REASON: recipes & textures
                //TEXTURES: stone_shingles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingle_stairs_from_stone_or_stone_shingles_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(shingle_stairs);

        shingle_slabs = StoneZoneEntrySet.of(StoneType.class, "shingle_slab",
                        getModBlock("stone_shingle_slab"), StoneTypeRegistry::getStoneType,
                        s -> new SlabBlock(copyChildrenPropertySafe("slab", s))
                )
                .requiresFromMap(shingles.blocks) //REASON: recipes & textures
                //TEXTURES: stone_shingles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingle_slab_from_stone_or_stone_shingles_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(shingle_slabs);

        shingle_walls = StoneZoneEntrySet.of(StoneType.class, "shingle_wall",
                        getModBlock("stone_shingle_wall"), StoneTypeRegistry::getStoneType,
                        s -> new WallBlock(copyWallSafe("stone_shingle_wall", s)
                ))
                .requiresFromMap(shingles.blocks) //REASON: recipes & textures
                //TEXTURES: stone_shingles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingle_wall_from_stone_or_stone_shingles_stonecutting"))
                .copyParentDrop()
                .build();
        this.addEntry(shingle_walls);

    }

    public BlockBehaviour.Properties copyWallSafe(String baseType, StoneType stoneType) {
        return Utils.copyPropertySafe(
                (Objects.nonNull(stoneType.getBlockOfThis("wall")))
                ? Objects.requireNonNull(stoneType.getBlockOfThis("wall"))
                : getModBlock(baseType).get()
        );
    }
}