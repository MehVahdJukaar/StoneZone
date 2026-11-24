package net.mehvahdjukaar.stone_zone.modules.building_but_better;

import com.starfish_studios.bbb.block.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.PushReaction;

import java.util.Objects;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.api.BlockTypeChildrenHelper.copyChildProperties;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.SLAB;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.SMOOTH;

///SUPPORT: v1.0.1+
public class BuildingButBetterModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> columns;
    public final SimpleEntrySet<StoneType, Block> fences;
    public final SimpleEntrySet<StoneType, Block> urns;
    public final SimpleEntrySet<StoneType, Block> tiles;
    public final SimpleEntrySet<StoneType, Block> tile_stairs;
    public final SimpleEntrySet<StoneType, Block> tile_slabs;
    public final SimpleEntrySet<StoneType, Block> layers;
    public final SimpleEntrySet<StoneType, Block> brick_layers;
    public final SimpleEntrySet<StoneType, Block> smooth_layers;
    public final SimpleEntrySet<StoneType, Block> mouldings;
//    public final SimpleEntrySet<StoneType, Block> blocks;

    public BuildingButBetterModule(String modId) {
        super(modId, "bbb");
        Supplier<CreativeModeTab> tab = getModTab("item_group");

        columns = StoneZoneEntrySet.of(StoneType.class, "column",
                        getModBlock("stone_column"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new ColumnBlock(properties)
                )
                .addTexture(modRes("block/column/stone_lower"))
                .addTexture(modRes("block/column/stone_upper"))
                .addTexture(modRes("block/column/stone_top"))
                .addTexture(modRes("block/column/stone_middle"))
                .addTexture(modRes("block/column/stone_none"))
                .addTexture(modRes("block/polished_stone")) //REASON: particle
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_columns"), Registries.BLOCK)
                .addTag(modRes("columns"), Registries.ITEM)
                .setTab(tab)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_column_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(columns);

        fences = StoneZoneEntrySet.of(StoneType.class, "fence",
                        getModBlock("stone_fence"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new StoneFenceBlock(properties.noOcclusion())
                )
                .addTexture(modRes("block/fence/stone_fence"))
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_fences"), Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_fences"), Registries.ITEM)
                .setTab(tab)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_fence_from_stonecutting"))
                .build();
        this.addEntry(fences);

        urns = StoneZoneEntrySet.of(StoneType.class, "urn",
                        getModBlock("stone_urn"), () -> VanillaStoneTypes.STONE,
                        (_, properties) ->
                                new UrnBlock(properties
                                        .noOcclusion()
                                        .pushReaction(PushReaction.DESTROY)
                                )
                )
                //TEXTURES: stones
                .addTexture(modRes("block/urn/stone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("urns"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_urn_from_stonecutting"))
                .build();
        this.addEntry(urns);

        tiles = StoneZoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new Block(properties)
                )
                .addTexture(modRes("block/stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(Identifier.parse("domum_ornamentum:default"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tiles_from_bricks_stonecutting"))
                .build();
        this.addEntry(tiles);

        tile_stairs = StoneZoneEntrySet.of(StoneType.class, "tile_stairs",
                        getModBlock("stone_tile_stairs"), () -> VanillaStoneTypes.STONE,
                        (stoneType, properties) ->
                                new StairBlock(stoneType.bricksOrStone().defaultBlockState(), properties)
                )
                .addCondition(s -> Objects.nonNull(tiles.blocks.get(s))) //REASON: recipes & textures
                //TEXTURES: tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_tile_stairs_from_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_bricks_stonecutting"))
                .build();
        this.addEntry(tile_stairs);

        tile_slabs = StoneZoneEntrySet.of(StoneType.class, "tile_slab",
                        getModBlock("stone_tile_slab"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new SlabBlock(properties)
                )
                .addCondition(s -> Objects.nonNull(tiles.blocks.get(s))) //REASON: recipes & textures
                //TEXTURES: tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addRecipe(modRes("stone_tile_slab_from_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_bricks_stonecutting"))
                .build();
        this.addEntry(tile_slabs);

        layers = StoneZoneEntrySet.of(StoneType.class, "layer",
                        getModBlock("stone_layer"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new LayerBlock(properties)
                )
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: stones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.ITEM)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addRecipe(modRes("stone_layer_from_stonecutting"))
                .build();
        this.addEntry(layers);

        brick_layers = StoneZoneEntrySet.of(StoneType.class, "brick_layer",
                        getModBlock("stone_brick_layer"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new LayerBlock(properties)
                )
                .requiresChildren("brick_slab", "bricks") //REASON: recipes & textures
                //TEXTURES: brick_stones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.ITEM)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_layer_from_stonecutting"))
                .build();
        this.addEntry(brick_layers);

        smooth_layers = StoneZoneEntrySet.of(StoneType.class, "layer", "smooth",
                        getModBlock("smooth_stone_layer"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new LayerBlock(properties)
                )
                .requiresChildren("smooth_slab", "smooth") //REASON: recipes & textures
                //TEXTURES: smooth_stones
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setProperties(stoneType -> copyChildProperties(stoneType, SMOOTH))
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_layer_from_stonecutting"))
                .build();
        this.addEntry(smooth_layers);

        mouldings = StoneZoneEntrySet.of(StoneType.class, "moulding",
                        getModBlock("stone_moulding"), () -> VanillaStoneTypes.STONE,
                        (stoneType, properties) -> new MouldingBlock(
                                stoneType.bricksOrStone().defaultBlockState(),
                                properties.noOcclusion()
                        )
                )
                //TEXTURES: stones
                .addTexture(modRes("block/moulding/stone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("mouldings"), Registries.BLOCK)
                .addTag(modRes("mouldings"), Registries.ITEM)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setProperties(stoneType -> Utils.copyPropertySafe(stoneType.bricksOrStone()))
                .defaultRecipe()
                .addRecipe(modRes("stone_moulding_from_stonecutting"))
                .build();
        this.addEntry(mouldings);

        //!! The ENUM via BlockBlock.Types is not useful but it has an interface, idk a way to solve it.
//        blocks = StonezoneEntrySet.of(StoneType.class, "block",
//                        getModBlock("stone_block"), StoneTypeRegistry::STONE_TYPE,
//                        (stoneType, properties) -> new BlockBlock(BlockBlock.Types.STONE, properties)
//                )
//                .addTile(getModTile("block"))
//                .addTexture(modRes("block/block/stone"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .addTag(modRes("stone_blocks"), Registries.BLOCK)
//                .addTag(modRes("blocks"), Registries.BLOCK)
//                .setTab(tab)
//                .addRecipe(modRes("stone_block_from_stonecutting"))
//                .build();
//        this.addEntry(blocks);

    }


}
