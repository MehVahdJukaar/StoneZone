package net.mehvahdjukaar.stone_zone.modules.stoneworks;

import fuzs.stoneworks.Stoneworks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.modules.SZModule;
import net.mehvahdjukaar.stone_zone.type.StoneType;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;

import java.util.Objects;

//SUPPORT: v8.0.0+
public class StoneworksModule extends SZModule {

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

        tiles = SimpleEntrySet.builder(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_stairs = SimpleEntrySet.builder(StoneType.class, "tile_stairs",
                        getModBlock("stone_tile_stairs"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new StairBlock(s.stone.defaultBlockState(), Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_stone_tiles_stonecutting"))
                .build();
        this.addEntry(tile_stairs);

        tile_slabs = SimpleEntrySet.builder(StoneType.class, "tile_slab",
                        getModBlock("stone_tile_slab"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new SlabBlock(Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_slab_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_stone_tiles_stonecutting"))
                .build();
        this.addEntry(tile_slabs);

        tile_walls = SimpleEntrySet.builder(StoneType.class, "tile_wall",
                        getModBlock("stone_tile_wall"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new WallBlock(Utils.copyPropertySafe(
                                (Objects.nonNull(s.getBlockOfThis("wall")))
                                        ? Objects.requireNonNull(s.getBlockOfThis("wall"))
                                        : Blocks.STONE_BRICK_WALL
                        ))
                )
                //TEXTURES: stone_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_wall_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_wall_from_stone_tiles_stonecutting"))
                .build();
        this.addEntry(tile_walls);

        cracked_tiles = SimpleEntrySet.builder(StoneType.class, "tiles", "cracked",
                        getModBlock("cracked_stone_tiles"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/cracked_stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cracked_stone_tiles_from_stone_stonecutting"))
                .build();
        this.addEntry(cracked_tiles);

        pillars = SimpleEntrySet.builder(StoneType.class, "pillar",
                        getModBlock("stone_pillar"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_pillar_top"))
                .addTexture(modRes("block/stone_pillar_side"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_pillar_from_stone_stonecutting"))
                .build();
        this.addEntry(pillars);

        plates = SimpleEntrySet.builder(StoneType.class, "plates",
                        getModBlock("stone_plates"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_plates"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plates_from_stone_stonecutting"))
                .build();
        this.addEntry(plates);

        plate_stairs = SimpleEntrySet.builder(StoneType.class, "plate_stairs",
                        getModBlock("stone_plate_stairs"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new StairBlock(s.stone.defaultBlockState(), Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_plates
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plate_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_plate_stairs_from_stone_plate_stonecutting"))
                .build();
        this.addEntry(plate_stairs);

        plate_slabs = SimpleEntrySet.builder(StoneType.class, "plate_slab",
                        getModBlock("stone_plate_slab"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new SlabBlock(Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_plates
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plate_slab_from_stone_stonecutting"))
                .addRecipe(modRes("stone_plate_slab_from_stone_plate_stonecutting"))
                .build();
        this.addEntry(plate_slabs);

        plate_walls = SimpleEntrySet.builder(StoneType.class, "plate_wall",
                        getModBlock("stone_plate_wall"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new WallBlock(Utils.copyPropertySafe(
                                (Objects.nonNull(s.getBlockOfThis("wall")))
                                        ? Objects.requireNonNull(s.getBlockOfThis("wall"))
                                        : Blocks.STONE_BRICK_WALL
                        ))
                )
                //TEXTURES: stone_plates
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_plate_wall_from_stone_stonecutting"))
                .addRecipe(modRes("stone_plate_wall_from_stone_plate_stonecutting"))
                .build();
        this.addEntry(plate_walls);

        pavers = SimpleEntrySet.builder(StoneType.class, "pavers",
                        getModBlock("stone_pavers"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_pavers"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_pavers_from_stone_stonecutting"))
                .build();
        this.addEntry(pavers);

        paver_stairs = SimpleEntrySet.builder(StoneType.class, "paver_stairs",
                        getModBlock("stone_paver_stairs"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new StairBlock(s.stone.defaultBlockState(), Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_pavers
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_paver_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_paver_stairs_from_stone_pavers_stonecutting"))
                .build();
        this.addEntry(paver_stairs);

        paver_slabs = SimpleEntrySet.builder(StoneType.class, "paver_slab",
                        getModBlock("stone_paver_slab"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new SlabBlock(Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_pavers
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_paver_slab_from_stone_stonecutting"))
                .addRecipe(modRes("stone_paver_slab_from_stone_pavers_stonecutting"))
                .build();
        this.addEntry(paver_slabs);

        paver_walls = SimpleEntrySet.builder(StoneType.class, "paver_wall",
                        getModBlock("stone_paver_wall"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new WallBlock(Utils.copyPropertySafe(
                                (Objects.nonNull(s.getBlockOfThis("wall")))
                                        ? Objects.requireNonNull(s.getBlockOfThis("wall"))
                                        : Blocks.STONE_BRICK_WALL
                        ))
                )
                //TEXTURES: stone_pavers
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_paver_wall_from_stone_stonecutting"))
                .addRecipe(modRes("stone_paver_wall_from_stone_pavers_stonecutting"))
                .build();
        this.addEntry(paver_walls);

        shingles = SimpleEntrySet.builder(StoneType.class, "shingles",
                        getModBlock("stone_shingles"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new Block(Utils.copyPropertySafe(s.stone))
                )
                .addTexture(modRes("block/stone_shingles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingles_from_stone_stonecutting"))
                .build();
        this.addEntry(shingles);

        shingle_stairs = SimpleEntrySet.builder(StoneType.class, "shingle_stairs",
                        getModBlock("stone_shingle_stairs"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new StairBlock(s.stone.defaultBlockState(), Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_shingles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingle_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_shingle_stairs_from_stone_shingles_stonecutting"))
                .build();
        this.addEntry(shingle_stairs);

        shingle_slabs = SimpleEntrySet.builder(StoneType.class, "shingle_slab",
                        getModBlock("stone_shingle_slab"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new SlabBlock(Utils.copyPropertySafe(s.stone))
                )
                //TEXTURES: stone_shingles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingle_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_shingle_stairs_from_stone_shingles_stonecutting"))
                .build();
        this.addEntry(shingle_slabs);

        shingle_walls = SimpleEntrySet.builder(StoneType.class, "shingle_wall",
                        getModBlock("stone_shingle_wall"), () -> StoneTypeRegistry.STONE_TYPE,
                        s -> new WallBlock(Utils.copyPropertySafe(
                                (Objects.nonNull(s.getBlockOfThis("wall")))
                                        ? Objects.requireNonNull(s.getBlockOfThis("wall"))
                                        : Blocks.STONE_BRICK_WALL
                        ))
                )
                //TEXTURES: stone_shingles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_shingle_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_shingle_stairs_from_stone_shingles_stonecutting"))
                .build();
        this.addEntry(shingle_walls);

    }
}