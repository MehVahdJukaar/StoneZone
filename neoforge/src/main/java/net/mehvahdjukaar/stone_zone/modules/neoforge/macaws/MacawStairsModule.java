package net.mehvahdjukaar.stone_zone.modules.neoforge.macaws;

import com.mcwstairs.kikoz.objects.BalconyRailing;
import com.mcwstairs.kikoz.objects.StairPlatform;
import com.mcwstairs.kikoz.objects.StairRailing;
import com.mcwstairs.kikoz.objects.stair_types.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.MOSSY_BRICKS;

//SUPPORT: v1.0.0+
public class MacawStairsModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> brick_terrace_stairs;
    public final SimpleEntrySet<StoneType, Block> brick_skyline_stairs;
    public final SimpleEntrySet<StoneType, Block> brick_compact_stairs;
    public final SimpleEntrySet<StoneType, Block> brick_bulk_stairs;
    public final SimpleEntrySet<StoneType, Block> brick_loft_stairs;
    public final SimpleEntrySet<StoneType, Block> brick_balconies;
    public final SimpleEntrySet<StoneType, Block> brick_railings;
    public final SimpleEntrySet<StoneType, Block> brick_platforms;

    public final SimpleEntrySet<StoneType, Block> mossy_brick_terrace_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_skyline_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_compact_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_bulk_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_loft_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_balconies;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_railings;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_platforms;

    public MacawStairsModule(String modId) {
        super(modId, "mcs");
        ResourceLocation tab = modRes(modId);

        brick_terrace_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_terrace_stairs",
                        getModBlock("stone_brick_terrace_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TerraceStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("terrace_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_terrace_stairs);

        brick_skyline_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_skyline_stairs",
                        getModBlock("stone_brick_skyline_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SkylineStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("skyline_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_skyline_stairs);

        brick_compact_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_compact_stairs",
                        getModBlock("stone_brick_compact_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new CompactStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("compact_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_compact_stairs);

        brick_bulk_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_bulk_stairs",
                        getModBlock("stone_brick_bulk_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new BulkStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bulk_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_bulk_stairs);

        brick_loft_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_loft_stairs",
                        getModBlock("stone_brick_loft_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new LoftStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("loft_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_loft_stairs);

        brick_balconies = StoneZoneEntrySet.of(StoneType.class, "brick_balcony",
                        getModBlock("stone_brick_balcony"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new BalconyRailing(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("balconies"), Registries.BLOCK)
                //TEXTURES: bricks
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_balconies);

        brick_railings = StoneZoneEntrySet.of(StoneType.class, "brick_railing",
                        getModBlock("stone_brick_railing"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new StairRailing(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("railings"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_railings);

        brick_platforms = StoneZoneEntrySet.of(StoneType.class, "brick_platform",
                        getModBlock("stone_brick_platform"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new StairPlatform(copyStandardProperties(stoneType))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("platforms"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_platforms);


        mossy_brick_terrace_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_terrace_stairs", "mossy",
                        getModBlock("mossy_stone_brick_terrace_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TerraceStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("terrace_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_terrace_stairs);

        mossy_brick_skyline_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_skyline_stairs", "mossy",
                        getModBlock("mossy_stone_brick_skyline_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SkylineStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("skyline_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_skyline_stairs);

        mossy_brick_compact_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_compact_stairs", "mossy",
                        getModBlock("mossy_stone_brick_compact_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new CompactStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("compact_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_compact_stairs);

        mossy_brick_bulk_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_bulk_stairs", "mossy",
                        getModBlock("mossy_stone_brick_bulk_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new BulkStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bulk_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_bulk_stairs);

        mossy_brick_loft_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_loft_stairs", "mossy",
                        getModBlock("mossy_stone_brick_loft_stairs"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new LoftStairs(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("loft_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_loft_stairs);

        mossy_brick_balconies = StoneZoneEntrySet.of(StoneType.class, "brick_balcony", "mossy",
                        getModBlock("mossy_stone_brick_balcony"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new BalconyRailing(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("balconies"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_balconies);

        mossy_brick_railings = StoneZoneEntrySet.of(StoneType.class, "brick_railing", "mossy",
                        getModBlock("mossy_stone_brick_railing"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new StairRailing(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("railings"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_railings);

        mossy_brick_platforms = StoneZoneEntrySet.of(StoneType.class, "brick_platform", "mossy",
                        getModBlock("mossy_stone_brick_platform"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new StairPlatform(copyStandardProperties(stoneType))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("platforms"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_platforms);

    }

    // METHODS
    public BlockBehaviour.Properties copyStandardProperties(StoneType stoneType) {
        Block block = stoneType.bricksOrStone();

        return Utils.copyPropertySafe(block)
                .strength(2.0F, 2.3F)
                .sound(block.defaultBlockState().getSoundType())
                .mapColor(block.defaultMapColor())
                .requiresCorrectToolForDrops();
    }
}