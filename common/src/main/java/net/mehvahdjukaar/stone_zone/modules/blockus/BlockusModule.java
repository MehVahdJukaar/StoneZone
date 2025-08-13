package net.mehvahdjukaar.stone_zone.modules.blockus;

import com.brand.blockus.blocks.base.OrientableBlockBase;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockTypeChildKeys;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;

//TODO: Add purpur
//SUPPORT: v2.7.20+
//NOTE: Can be supported via Sinytra-Connector
public class BlockusModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> herringbone_bricks;
    public final SimpleEntrySet<StoneType, Block> smooth_stairs;
    public final SimpleEntrySet<StoneType, Block> brick_pillars;
    public final SimpleEntrySet<StoneType, Block> circular_pavings;
    public final SimpleEntrySet<StoneType, Block> doors;
    public final SimpleEntrySet<StoneType, Block> tiles;
    public final SimpleEntrySet<StoneType, Block> tile_slabs;
    public final SimpleEntrySet<StoneType, Block> tile_stairs;
    public final SimpleEntrySet<StoneType, Block> tile_walls;
    public final SimpleEntrySet<StoneType, Block> trapdoors;
//    public final SimpleEntrySet<PurpurType, Block> squares;

    public BlockusModule(String modId) {
        super(modId, "bus");
        ResourceLocation tab = modRes("blockus_building_blocks");

        herringbone_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "herringbone",
                        getModBlock("herringbone_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .addTexture(modRes("block/herringbone_stone_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("herringbone_stone_bricks_from_stone_stonecutting"))
                .addRecipe(modRes("herringbone_stone_bricks_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(herringbone_bricks);

        smooth_stairs = StoneZoneEntrySet.of(StoneType.class, "stairs", "smooth",
                        getModBlock("smooth_stone_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairBlock(
                                Objects.requireNonNull(stoneType.getBlockOfThis("smooth")).defaultBlockState(),
                                Utils.copyPropertySafe(stoneType.stone)
                        )
                )
                .requiresChildren(VanillaRockTypeChildKeys.SMOOTH) //REASON: textures, recipes
                //TEXTURES: smooth_stone, smooth_stone_slab_side
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_stairs_from_smooth_stone_stonecutting"))
                .build();
        this.addEntry(smooth_stairs);

        brick_pillars = StoneZoneEntrySet.of(StoneType.class, "brick_pillar",
                        getModBlock("stone_brick_pillar"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_brick_pillar_top"))
                .addTexture(modRes("block/stone_brick_pillar"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_brick_pillar_from_stone_stonecutting"))
                .addRecipe(modRes("stone_brick_pillar_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(brick_pillars);

        circular_pavings = StoneZoneEntrySet.of(StoneType.class, "circular_paving",
                        getModBlock("stone_circular_paving"), StoneTypeRegistry::getStoneType,
                        stoneType -> new OrientableBlockBase(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_circular_paving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_circular_paving_from_stone_stonecutting"))
                .addRecipe(modRes("stone_circular_paving_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(circular_pavings);

        doors = StoneZoneEntrySet.of(StoneType.class, "door",
                        getModBlock("stone_door"), StoneTypeRegistry::getStoneType,
                        stoneType -> new DoorBlock(
                                BlockBehaviour.Properties.of()
                                        .mapColor(stoneType.bricksOrStone().defaultMapColor())
                                        .sound(stoneType.getSound())
                                        .strength(1.5F, 20.0F)
                                        .noOcclusion()
                                        .requiresCorrectToolForDrops(),
                                BlockSetType.STONE
                        )
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/stone_door_bottom"))
                .addTexture(modRes("block/stone_door_top"))
                .addTexture(modRes("item/stone_door"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(doors);

        tiles = StoneZoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_tiles"))
                .defaultRecipe()
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tiles_from_stone_bricks_stonecutting"))
                .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_slabs = StoneZoneEntrySet.of(StoneType.class, "tile_slab",
                        getModBlock("stone_tile_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(copyChildrenPropertySafe("slab", stoneType))
                )
                .requiresFromMap(tiles.blocks) //REASON: textures, recipes
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .defaultRecipe()
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_slab_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_stone_bricks_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_stone_tiles_stonecutting"))
                .build();
        this.addEntry(tile_slabs);

        tile_stairs = StoneZoneEntrySet.of(StoneType.class, "tile_stairs",
                        getModBlock("stone_tile_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairBlock(tiles.blocks.get(stoneType).defaultBlockState(),
                                copyChildrenPropertySafe("stairs", stoneType))
                )
                .requiresFromMap(tiles.blocks) //REASON: textures, recipes
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .defaultRecipe()
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_stone_bricks_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_stone_tiles_stonecutting"))
                .build();
        this.addEntry(tile_stairs);

        tile_walls = StoneZoneEntrySet.of(StoneType.class, "tile_wall",
                        getModBlock("stone_tile_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallBlock(copyWallSafe("stone_tile_wall", stoneType))
                )
                .requiresFromMap(tiles.blocks) //REASON: textures, recipes
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .defaultRecipe()
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tile_wall_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_wall_from_stone_bricks_stonecutting"))
                .addRecipe(modRes("stone_tile_wall_from_stone_tiles_stonecutting"))
                .build();
        this.addEntry(tile_walls);

        trapdoors = StoneZoneEntrySet.of(StoneType.class, "trapdoor",
                        getModBlock("stone_trapdoor"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TrapDoorBlock(
                                BlockBehaviour.Properties.of()
                                        .mapColor(stoneType.bricksOrStone().defaultMapColor())
                                        .sound(stoneType.getSound())
                                        .strength(1.5F, 20.0F)
                                        .noOcclusion()
                                        .requiresCorrectToolForDrops(),
                                BlockSetType.STONE
                        )
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.SLAB) //REASON: recipes
                .addTexture(modRes("block/stone_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.TRAPDOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(trapdoors);

        //!! REQUIRE PurpurType
//        squares = StonezoneEntrySet.of(PurpurType.class, "squares",
//                        getModBlock("purpur_squares"), PurpurTypeRegistry::getPurpurType,
//                        purpurType -> new Block(Utils.copyPropertySafe(purpurType.stone))
//                )
//                .addTexture(modRes("block/purpur_squares"))
//                .addRecipe(modRes("purpur_squares_from_purpur_block_stonecutting"))
//                .setTabKey(tab)
//                .build();
//        this.addEntry(squares);


    }

    public BlockBehaviour.Properties copyWallSafe(String baseType, StoneType stoneType) {
        return Utils.copyPropertySafe(
                (Objects.nonNull(stoneType.getBlockOfThis("wall")))
                        ? Objects.requireNonNull(stoneType.getBlockOfThis("wall"))
                        : getModBlock(baseType).get()
        );
    }
}