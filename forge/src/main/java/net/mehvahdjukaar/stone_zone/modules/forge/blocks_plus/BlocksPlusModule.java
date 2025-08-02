package net.mehvahdjukaar.stone_zone.modules.forge.blocks_plus;

import blocks_plus.blocks.BPFurnace;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneSimpleModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;

import java.util.Objects;

//SUPPORT: v1.7+
public class BlocksPlusModule extends StoneZoneSimpleModule {

    public final SimpleEntrySet<StoneType, Block> wall;
    public final SimpleEntrySet<StoneType, Block> pillar;
    public final SimpleEntrySet<StoneType, Block> brick_pillar;
    public final SimpleEntrySet<StoneType, Block> smooth_stairs,
                                                  smooth_wall,
                                                  smooth_pillar,
                                                  smooth_bricks,
                                                  smooth_brick_stairs,
                                                  smooth_brick_slab,
                                                  smooth_brick_wall,
                                                  smooth_brick_pillar;
    public final SimpleEntrySet<StoneType, Block> cracked_smooth_bricks;
    public final SimpleEntrySet<StoneType, Block> mossy_smooth_bricks;
    public final SimpleEntrySet<StoneType, Block> chiseled_smooth_bricks;

    public final SimpleEntrySet<StoneType, Block> polished_wall,
                                                  polished_bricks,
                                                  polished_brick_stairs,
                                                  polished_brick_slab,
                                                  polished_brick_wall,
                                                  polished_brick_pillar;
    public final SimpleEntrySet<StoneType, Block> chiseled_polished;
    public final SimpleEntrySet<StoneType, Block> cracked_polished_bricks;
    public final SimpleEntrySet<StoneType, Block> mossy_polished_bricks;
    public final SimpleEntrySet<StoneType, Block> furnace;

    public BlocksPlusModule(String modId) {
        super(modId, "bp");
        ResourceLocation tab = modRes("creative_tab");

        wall = StoneZoneEntrySet.of(StoneType.class, "wall",
                        getModBlock("stone_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_wall_from_stone_stonecutting"))
                .build();
        this.addEntry(wall);

        pillar = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("stone_pillar"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/stone_pillar"))
                .addTexture(modRes("block/stone_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_pillar_from_stone_stonecutting"))
                .build();
        this.addEntry(pillar);

        brick_pillar = StoneZoneEntrySet.of(StoneType.class, "brick_pillar",
                        getModBlock("stone_brick_pillar"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: textures, recipes
                .addTexture(modRes("block/stone_brick_pillar"))
                .addTexture(modRes("block/stone_brick_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_pillar_from_stone_stonecutting"))
                .addRecipe(modRes("stone_brick_pillar_from_stone_bricks_stonecutting"))
                .build();
        this.addEntry(brick_pillar);

        smooth_stairs = StoneZoneEntrySet.of(StoneType.class, "stairs", "smooth",
                        getModBlock("smooth_stone_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairBlock(Objects.requireNonNull(stoneType.getBlockOfThis("smooth"))::defaultBlockState,
                                Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren("smooth") //REASON: textures, recipes
                //TEXTURES: smooth
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_stairs_from_smooth_stone_stonecutting"))
                .build();
        this.addEntry(smooth_stairs);

        smooth_wall = StoneZoneEntrySet.of(StoneType.class, "wall", "smooth",
                        getModBlock("smooth_stone_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren("smooth") //REASON: textures, recipes
                //TEXTURES: smooth
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_wall_from_smooth_stone_stonecutting"))
                .build();
        this.addEntry(smooth_wall);

        smooth_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "smooth",
                        getModBlock("smooth_stone_pillar"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromStoneChild("smooth")
                .addTexture(modRes("block/smooth_stone_pillar"))
                .addTexture(modRes("block/smooth_stone_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_pillar_from_smooth_stone_stonecutting"))
                .build();
        this.addEntry(smooth_pillar);

        smooth_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "smooth",
                        getModBlock("smooth_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/smooth_stone_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_bricks_from_smooth_stone_stonecutting"))
                .build();
        this.addEntry(smooth_bricks);

        smooth_brick_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_stairs", "smooth",
                        getModBlock("smooth_stone_brick_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairBlock(smooth_bricks.blocks.get(stoneType)::defaultBlockState,
                                Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresFromMap(smooth_bricks.blocks) //REASON: textures, recipes
                //TEXTURES: smooth_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_brick_stairs_from_smooth_stone_stonecutting"))
                .addRecipe(modRes("smooth_stone_brick_stairs_from_smooth_stone_bricks_stonecutting"))
                .build();
        this.addEntry(smooth_brick_stairs);

        smooth_brick_slab = StoneZoneEntrySet.of(StoneType.class, "brick_slab", "smooth",
                        getModBlock("smooth_stone_brick_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresFromMap(smooth_bricks.blocks) //REASON: textures, recipes
                //TEXTURES: smooth_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_brick_slab_from_smooth_stone_stonecutting"))
                .addRecipe(modRes("smooth_stone_brick_slab_from_smooth_stone_bricks_stonecutting"))
                .build();
        this.addEntry(smooth_brick_slab);

        smooth_brick_wall = StoneZoneEntrySet.of(StoneType.class, "brick_wall", "smooth",
                        getModBlock("smooth_stone_brick_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresFromMap(smooth_bricks.blocks) //REASON: textures, recipes
                //TEXTURES: smooth_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_brick_wall_from_smooth_stone_stonecutting"))
                .addRecipe(modRes("smooth_stone_brick_wall_from_smooth_stone_bricks_stonecutting"))
                .build();
        this.addEntry(smooth_brick_wall);

        smooth_brick_pillar = StoneZoneEntrySet.of(StoneType.class, "brick_pillar", "smooth",
                        getModBlock("smooth_stone_brick_pillar"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/smooth_stone_brick_pillar"))
                .addTexture(modRes("block/smooth_stone_brick_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("smooth_stone_brick_pillar_from_smooth_stone_stonecutting"))
                .addRecipe(modRes("smooth_stone_brick_pillar_from_smooth_stone_bricks_stonecutting"))
                .build();
        this.addEntry(smooth_brick_pillar);

        cracked_smooth_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "cracked_smooth",
                        getModBlock("cracked_smooth_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/cracked_smooth_stone_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cracked_smooth_bricks);

        mossy_smooth_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "mossy_smooth",
                        getModBlock("mossy_smooth_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTextureM(modRes("block/mossy_smooth_stone_bricks"), StoneZone.res("block/bp/mossy_smooth_stone_bricks_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_smooth_bricks);

        chiseled_smooth_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "chiseled_smooth",
                        getModBlock("chiseled_smooth_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/chiseled_smooth_stone_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("chiseled_smooth_stone_bricks_from_smooth_stone_stonecutting"))
                .addRecipe(modRes("chiseled_smooth_stone_bricks_from_smooth_stone_bricks_stonecutting"))
                .build();
        this.addEntry(chiseled_smooth_bricks);

//!! ANDESITE
        polished_wall = StoneZoneEntrySet.of(StoneType.class, "wall", "polished",
                        getModBlock("polished_andesite_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new WallBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_andesite_wall_from_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_wall_from_polished_andesite_stonecutting"))
                .build();
        this.addEntry(polished_wall);

        polished_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "polished",
                        getModBlock("polished_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("polished") //REASON: recipes
                .addTexture(modRes("block/polished_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_andesite_bricks_from_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_bricks_from_polished_andesite_stonecutting"))
                .build();
        this.addEntry(polished_bricks);

        polished_brick_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_stairs", "polished",
                        getModBlock("polished_andesite_brick_stairs"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new StairBlock(polished_bricks.blocks.get(stoneType)::defaultBlockState,
                                Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresFromMap(polished_bricks.blocks) //REASON: textures, recipes
                //TEXTURES: polished_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_andesite_brick_stairs_from_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_stairs_from_polished_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_stairs_from_polished_andesite_bricks_stonecutting"))
                .build();
        this.addEntry(polished_brick_stairs);

        polished_brick_slab = StoneZoneEntrySet.of(StoneType.class, "brick_slab", "polished",
                        getModBlock("polished_andesite_brick_slab"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresFromMap(polished_bricks.blocks) //REASON: textures, recipes
                //TEXTURES: polished_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_andesite_brick_slab_from_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_slab_from_polished_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_slab_from_polished_andesite_bricks_stonecutting"))
                .build();
        this.addEntry(polished_brick_slab);

        polished_brick_wall = StoneZoneEntrySet.of(StoneType.class, "brick_wall", "polished",
                        getModBlock("polished_andesite_brick_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new WallBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresFromMap(polished_bricks.blocks) //REASON: textures, recipes
                //TEXTURES: polished_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_andesite_brick_wall_from_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_wall_from_polished_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_wall_from_polished_andesite_bricks_stonecutting"))
                .build();
        this.addEntry(polished_brick_wall);

        polished_brick_pillar = StoneZoneEntrySet.of(StoneType.class, "brick_pillar", "polished",
                        getModBlock("polished_andesite_brick_pillar"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("polished") //REASON: recipes
                .addTexture(modRes("block/polished_andesite_brick_pillar"))
                .addTexture(modRes("block/polished_andesite_brick_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_andesite_brick_pillar_from_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_pillar_from_polished_andesite_stonecutting"))
                .addRecipe(modRes("polished_andesite_brick_pillar_from_polished_andesite_bricks_stonecutting"))
                .build();
        this.addEntry(polished_brick_pillar);

        chiseled_polished = StoneZoneEntrySet.of(StoneType.class, "","chiseled_polished",
                        getModBlock("chiseled_polished_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromStoneChild("polished")
                .requiresChildren("polished") //REASON: textures
                .addTexture(modRes("block/chiseled_polished_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("chiseled_polished_andesite_from_andesite_stonecutting"))
                .addRecipe(modRes("chiseled_polished_andesite_from_polished_andesite_stonecutting"))
                .build();
        this.addEntry(chiseled_polished);

        cracked_polished_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "cracked_polished",
                        getModBlock("cracked_polished_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/cracked_polished_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cracked_polished_bricks);

        mossy_polished_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "mossy_polished",
                        getModBlock("mossy_polished_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTextureM(modRes("block/mossy_polished_andesite_bricks"), StoneZone.res("block/bp/mossy_polished_andesite_bricks_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_polished_bricks);

        furnace = StoneZoneEntrySet.of(StoneType.class, "furnace",
                        getModBlock("granite_furnace"), StoneTypeRegistry::getGraniteType,
                        stoneType -> new BPFurnace(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTile(getModTile("furnace"))
                .addTexture(modRes("block/granite_furnace_top"))
                .addTexture(modRes("block/granite_furnace_side"))
                .addTextureM(modRes("block/granite_furnace_front"), StoneZone.res("block/bp/granite_furnace_front_m"))
                .addTextureM(modRes("block/granite_furnace_front_on"), StoneZone.res("block/bp/granite_furnace_front_on_m"))
//                .addTexture(modRes("block/granite_furnace_front"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(furnace);

    }

}