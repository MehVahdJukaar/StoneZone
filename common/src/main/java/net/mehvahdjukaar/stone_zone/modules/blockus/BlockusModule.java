package net.mehvahdjukaar.stone_zone.modules.blockus;

import com.brand.blockus.blocks.base.OrientableBlockBase;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.copyChildrenPropertySafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

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
                        getModBlock("herringbone_stone_bricks"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/herringbone_stone_bricks"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("herringbone_stone_bricks_from_stone_stonecutting"))
                .addRecipe(modRes("herringbone_stone_bricks_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(herringbone_bricks);

        smooth_stairs = StoneZoneEntrySet.of(StoneType.class, "stairs", "smooth",
                        getModBlock("smooth_stone_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new StairBlock(
                                Objects.requireNonNull(stoneType.getBlockOfThis("smooth")).defaultBlockState(),
                                Utils.copyPropertySafe(stoneType.stone)
                        )
                )
                .requiresChildren(SMOOTH) //REASON: textures, recipes
                //TEXTURES: smooth_stone, smooth_stone_slab_side
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_stairs_from_smooth_stone_stonecutting"))
                .build();
        this.addEntry(smooth_stairs);

        brick_pillars = StoneZoneEntrySet.of(StoneType.class, "brick_pillar",
                        getModBlock("stone_brick_pillar"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_brick_pillar_top"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTexture(modRes("block/stone_brick_pillar"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_brick_pillar_from_stone_stonecutting"))
                .addRecipe(modRes("stone_brick_pillar_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(brick_pillars);

        circular_pavings = StoneZoneEntrySet.of(StoneType.class, "circular_paving",
                        getModBlock("stone_circular_paving"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new OrientableBlockBase(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_circular_paving"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_circular_paving_from_stone_stonecutting"))
                .addRecipe(modRes("stone_circular_paving_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(circular_pavings);

        doors = StoneZoneEntrySet.of(StoneType.class, "door",
                        getModBlock("stone_door"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new DoorBlock(
                                BlockSetType.STONE,
                                BlockBehaviour.Properties.of()
                                        .mapColor(stoneType.bricksOrStone().defaultMapColor())
                                        .sound(stoneType.getSound())
                                        .strength(1.5F, 20.0F)
                                        .noOcclusion()
                                        .requiresCorrectToolForDrops()
                        )
                )
                .addTexture(modRes("block/stone_door_bottom"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTexture(modRes("block/stone_door_top"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTexture(modRes("item/stone_door"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK)
                .addTag(ItemTags.DOORS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(doors);

        tiles = StoneZoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_tiles"), StonePaletteStrategies.BRICKS_STANDARD)
                .defaultRecipe()
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("stone_tiles_from_stone_bricks_stonecutting"))
                .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_slabs = StoneZoneEntrySet.of(StoneType.class, "tile_slab",
                        getModBlock("stone_tile_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(copyChildrenPropertySafe("slab", stoneType))
                )
                .requiresFromMap(tiles.blocks) //REASON: textures, recipes
                .requiresChildren(BRICKS) //REASON: recipes
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
                        getModBlock("stone_tile_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new StairBlock(tiles.blocks.get(stoneType).defaultBlockState(),
                                copyChildrenPropertySafe("stairs", stoneType))
                )
                .requiresFromMap(tiles.blocks) //REASON: textures, recipes
                .requiresChildren(BRICKS) //REASON: recipes
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
                        getModBlock("stone_tile_wall"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallBlock(copyWallSafe("stone_tile_wall", stoneType))
                )
                .requiresFromMap(tiles.blocks) //REASON: textures, recipes
                .requiresChildren(BRICKS) //REASON: recipes
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
                        getModBlock("stone_trapdoor"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TrapDoorBlock(
                                BlockSetType.STONE,
                                BlockBehaviour.Properties.of()
                                        .mapColor(stoneType.bricksOrStone().defaultMapColor())
                                        .sound(stoneType.getSound())
                                        .strength(1.5F, 20.0F)
                                        .noOcclusion()
                                        .requiresCorrectToolForDrops()
                        )
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/stone_trapdoor"), StonePaletteStrategies.BRICKS_STANDARD)
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
//                .addTexture(modRes("block/purpur_squares"), StonePaletteStrategies.BRICKS_STANDARD)
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