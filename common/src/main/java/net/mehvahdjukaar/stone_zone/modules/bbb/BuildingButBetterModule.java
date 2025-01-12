package net.mehvahdjukaar.stone_zone.modules.bbb;

import com.starfish_studios.bbb.block.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.PushReaction;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;

//SUPPORT: v1.0.1+
public class BuildingButBetterModule extends SZModule {

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
        ResourceLocation tab = modRes("item_group");

        columns = StonezoneEntrySet.of(StoneType.class, "column",
                        getModBlock("stone_column"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ColumnBlock(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .createPaletteFromStone()
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
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_column_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(columns);

        fences = StonezoneEntrySet.of(StoneType.class, "fence",
                        getModBlock("stone_fence"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StoneFenceBlock(Utils.copyPropertySafe(stoneType.bricksOrStone())
                                .noOcclusion()
                        )
                )
                .createPaletteFromStone()
                .addTexture(modRes("block/fence/stone_fence"))
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_fences"), Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_fences"), Registries.ITEM)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_fence_from_stonecutting"))
                .build();
        this.addEntry(fences);

        urns = StonezoneEntrySet.of(StoneType.class, "urn",
                        getModBlock("stone_urn"), StoneTypeRegistry::getStoneType,
                        stoneType -> new UrnBlock(Utils.copyPropertySafe(stoneType.bricksOrStone())
                                .noOcclusion()
                                .pushReaction(PushReaction.DESTROY))
                )
                .createPaletteFromStone()
                //TEXTURES: stones
                .addTexture(modRes("block/urn/stone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("urns"), Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_urn_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(urns);

        tiles = StonezoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .createPaletteFromStone()
                .addTexture(modRes("block/stone_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(new ResourceLocation("domum_ornamentum:default"), Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_tiles_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tiles_from_bricks_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(tiles);

        tile_stairs = StonezoneEntrySet.of(StoneType.class, "tile_stairs",
                        getModBlock("stone_tile_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairBlock(stoneType.bricksOrStone().defaultBlockState(),
                                Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .addCondition(s -> Objects.nonNull(tiles.blocks.get(s))) //REASON: recipes & textures
                //TEXTURES: tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_tile_stairs_from_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_stairs_from_bricks_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(tile_stairs);

        tile_slabs = StonezoneEntrySet.of(StoneType.class, "tile_slab",
                        getModBlock("stone_tile_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addCondition(s -> Objects.nonNull(tiles.blocks.get(s))) //REASON: recipes & textures
                //TEXTURES: tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_tile_slab_from_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_stone_stonecutting"))
                .addRecipe(modRes("stone_tile_slab_from_bricks_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(tile_slabs);

        layers = StonezoneEntrySet.of(StoneType.class, "layer",
                        getModBlock("stone_layer"), StoneTypeRegistry::getStoneType,
                        stoneType -> new LayerBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: stones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.ITEM)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_layer_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(layers);

        brick_layers = StonezoneEntrySet.of(StoneType.class, "brick_layer",
                        getModBlock("stone_brick_layer"), StoneTypeRegistry::getStoneType,
                        stoneType -> new LayerBlock(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .requiresChildren("brick_slab", "bricks") //REASON: recipes & textures
                //TEXTURES: brick_stones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("stone_layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.BLOCK)
                .addTag(modRes("layers"), Registries.ITEM)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_layer_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(brick_layers);

        smooth_layers = StonezoneEntrySet.of(StoneType.class, "layer", "smooth",
                        getModBlock("smooth_stone_layer"), StoneTypeRegistry::getStoneType,
                        stoneType -> new LayerBlock(copyChildrenPropertySafe("smooth_stone", stoneType))
                )
                .requiresChildren("smooth_slab", "smooth_stone") //REASON: recipes & textures
                //TEXTURES: smooth_stones
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_layer_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(smooth_layers);

        mouldings = StonezoneEntrySet.of(StoneType.class, "moulding",
                        getModBlock("stone_moulding"), StoneTypeRegistry::getStoneType,
                        stoneType -> new MouldingBlock(
                                stoneType.bricksOrStone().defaultBlockState(),
                                Utils.copyPropertySafe(stoneType.bricksOrStone()).noOcclusion()
                        )
                )
                .createPaletteFromStone()
                //TEXTURES: stones
                .addTexture(modRes("block/moulding/stone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_blocks"), Registries.BLOCK)
                .addTag(modRes("mouldings"), Registries.BLOCK)
                .addTag(modRes("mouldings"), Registries.ITEM)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_moulding_from_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(mouldings);

        //!! The ENUM via BlockBlock.Types is not useful but it has an interface, idk a way to solve it.
//        blocks = StonezoneEntrySet.of(StoneType.class, "block",
//                        getModBlock("stone_block"), StoneTypeRegistry::STONE_TYPE,
//                        stoneType -> new BlockBlock(BlockBlock.Types.STONE, Utils.copyPropertySafe(stoneType.stone))
//                )
//                .addTile(getModTile("block"))
//                .createPaletteFromStone()
//                .addTexture(modRes("block/block/stone"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .addTag(modRes("stone_blocks"), Registries.BLOCK)
//                .addTag(modRes("blocks"), Registries.BLOCK)
//                .setTabKey(tab)
//                .addRecipe(modRes("stone_block_from_stonecutting"))
//                .build();
//        this.addEntry(blocks);

    }


}
