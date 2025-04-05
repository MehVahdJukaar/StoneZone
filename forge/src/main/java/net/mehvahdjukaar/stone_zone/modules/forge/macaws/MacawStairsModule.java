package net.mehvahdjukaar.stone_zone.modules.forge.macaws;

import com.mcwstairs.kikoz.objects.BalconyRailing;
import com.mcwstairs.kikoz.objects.StairPlatform;
import com.mcwstairs.kikoz.objects.StairRailing;
import com.mcwstairs.kikoz.objects.stair_types.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StonezoneModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

//SUPPORT: v1.0.0+
public class MacawStairsModule extends StonezoneModule {

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

        brick_terrace_stairs = StonezoneEntrySet.of(StoneType.class, "brick_terrace_stairs",
                        getModBlock("stone_brick_terrace_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TerraceStairs(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("terrace_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_terrace_stairs);

        brick_skyline_stairs = StonezoneEntrySet.of(StoneType.class, "brick_skyline_stairs",
                        getModBlock("stone_brick_skyline_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SkylineStairs(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("skyline_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_skyline_stairs);

        brick_compact_stairs = StonezoneEntrySet.of(StoneType.class, "brick_compact_stairs",
                        getModBlock("stone_brick_compact_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new CompactStairs(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("compact_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_compact_stairs);

        brick_bulk_stairs = StonezoneEntrySet.of(StoneType.class, "brick_bulk_stairs",
                        getModBlock("stone_brick_bulk_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new BulkStairs(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bulk_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_bulk_stairs);

        brick_loft_stairs = StonezoneEntrySet.of(StoneType.class, "brick_loft_stairs",
                        getModBlock("stone_brick_loft_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new LoftStairs(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("loft_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_loft_stairs);

        brick_balconies = StonezoneEntrySet.of(StoneType.class, "brick_balcony",
                        getModBlock("stone_brick_balcony"), StoneTypeRegistry::getStoneType,
                        stoneType -> new BalconyRailing(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("balconies"), Registries.BLOCK)
                //TEXTURES: bricks
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_balconies);

        brick_railings = StonezoneEntrySet.of(StoneType.class, "brick_railing",
                        getModBlock("stone_brick_railing"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairRailing(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("railings"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_railings);

        brick_platforms = StonezoneEntrySet.of(StoneType.class, "brick_platform",
                        getModBlock("stone_brick_platform"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairPlatform(copyProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("platforms"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_platforms);


        mossy_brick_terrace_stairs = StonezoneEntrySet.of(StoneType.class, "brick_terrace_stairs", "mossy",
                        getModBlock("mossy_stone_brick_terrace_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TerraceStairs(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("terrace_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_terrace_stairs);

        mossy_brick_skyline_stairs = StonezoneEntrySet.of(StoneType.class, "brick_skyline_stairs", "mossy",
                        getModBlock("mossy_stone_brick_skyline_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SkylineStairs(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("skyline_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_skyline_stairs);

        mossy_brick_compact_stairs = StonezoneEntrySet.of(StoneType.class, "brick_compact_stairs", "mossy",
                        getModBlock("mossy_stone_brick_compact_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new CompactStairs(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("compact_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_compact_stairs);

        mossy_brick_bulk_stairs = StonezoneEntrySet.of(StoneType.class, "brick_bulk_stairs", "mossy",
                        getModBlock("mossy_stone_brick_bulk_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new BulkStairs(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bulk_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_bulk_stairs);

        mossy_brick_loft_stairs = StonezoneEntrySet.of(StoneType.class, "brick_loft_stairs", "mossy",
                        getModBlock("mossy_stone_brick_loft_stairs"), StoneTypeRegistry::getStoneType,
                        stoneType -> new LoftStairs(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("loft_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_loft_stairs);

        mossy_brick_balconies = StonezoneEntrySet.of(StoneType.class, "brick_balcony", "mossy",
                        getModBlock("mossy_stone_brick_balcony"), StoneTypeRegistry::getStoneType,
                        stoneType -> new BalconyRailing(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("balconies"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_balconies);

        mossy_brick_railings = StonezoneEntrySet.of(StoneType.class, "brick_railing", "mossy",
                        getModBlock("mossy_stone_brick_railing"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairRailing(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("railings"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_brick_railings);

        mossy_brick_platforms = StonezoneEntrySet.of(StoneType.class, "brick_platform", "mossy",
                        getModBlock("mossy_stone_brick_platform"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StairPlatform(copyProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
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
    public BlockBehaviour.Properties copyProperties(StoneType stoneType) {
        return Utils.copyPropertySafe(stoneType.stone)
                .strength(2.0F, 2.3F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops();
    }
}