package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import net.kikoz.mcwfences.objects.FenceHitbox;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneSimpleModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

//SUPPORT: v1.1.2+
public class MacawFencesModule extends StoneZoneSimpleModule {

    public final SimpleEntrySet<StoneType, Block> modern_brick_walls;
    public final SimpleEntrySet<StoneType, Block> railing_brick_gates;
    public final SimpleEntrySet<StoneType, Block> railing_brick_walls;
    public final SimpleEntrySet<StoneType, Block> grass_topped_walls;

    public final SimpleEntrySet<StoneType, Block> pillar_walls;
    public final SimpleEntrySet<StoneType, Block> railing_gates;
    public final SimpleEntrySet<StoneType, Block> railing_walls;
    public final SimpleEntrySet<StoneType, Block> modern_walls;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");
        ResourceLocation tab = modRes("fenceitemgroup");

        modern_brick_walls = StoneZoneEntrySet.of(StoneType.class, "brick_wall", "modern",
                        getModBlock("modern_stone_brick_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FenceBlock(standardCopyProperties(stoneType))
                )
                .requiresChildren("bricks", "cobblestone") //REASON: testures, recipes
                //TEXTURES: bricks, cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("modern_stone_brick_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(modern_brick_walls);

        railing_brick_gates = StoneZoneEntrySet.of(StoneType.class, "brick_railing_gate",
                        getModBlock("stone_brick_railing_gate"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FenceGateBlock(WoodType.OAK, standardCopyProperties(stoneType))
                )
                .excludeTextureFromTinting("#4")
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_railing_gate_stonecutter"))
                .build();
        this.addEntry(railing_brick_gates);

        railing_brick_walls = StoneZoneEntrySet.of(StoneType.class, "brick_wall", "railing",
                        getModBlock("railing_stone_brick_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FenceBlock(standardCopyProperties(stoneType))
                )
                .excludeTextureFromTinting("#2")
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("railing_stone_brick_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(railing_brick_walls);

        grass_topped_walls = StoneZoneEntrySet.of(StoneType.class, "grass_topped_wall",
                        getModBlock("stone_grass_topped_wall"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FenceHitbox(standardCopyProperties(stoneType))
                )
                //REASON: grass
                .excludeMultipleTextureFromTinting(modRes("block/parent/grass_topped_wall_middle"), "#0", "#1")
                .excludeMultipleTextureFromTinting(modRes("block/parent/grass_topped_wall_post"), "#3", "#4")
                .excludeMultipleTextureFromTinting(modRes("block/parent/grass_topped_wall_corner"), "#2", "#3")
                .excludeMultipleTextureFromTinting(modRes("block/parent/grass_topped_wall_cross"), "#2", "#3")
                .excludeMultipleTextureFromTinting(modRes("block/parent/grass_topped_wall_side"), "#2", "#3")
                .excludeMultipleTextureFromTinting(modRes("block/parent/grass_topped_wall_triple"), "#2", "#3")
                .requiresChildren("bricks", "cobblestone") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks, cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(grass_topped_walls);

//!! ANDESITE
        pillar_walls = StoneZoneEntrySet.of(StoneType.class, "pillar_wall",
                        getModBlock("andesite_pillar_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceBlock(standardCopyProperties(stoneType))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: stone, polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(pillar_walls);

        railing_gates = StoneZoneEntrySet.of(StoneType.class, "railing_gate",
                        getModBlock("andesite_railing_gate"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceGateBlock(WoodType.OAK, standardCopyProperties(stoneType))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("andesite_railing_gate_stonecutter"))
                .build();
        this.addEntry(railing_gates);

        railing_walls = StoneZoneEntrySet.of(StoneType.class, "wall", "railing",
                        getModBlock("railing_andesite_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceBlock(standardCopyProperties(stoneType))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("railing_andesite_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(railing_walls);

        modern_walls = StoneZoneEntrySet.of(StoneType.class, "wall", "modern",
                        getModBlock("modern_andesite_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceBlock(standardCopyProperties(stoneType))
                )
                .requiresChildren("polished") //REASON: testures, recipes
                //TEXTURES: stone, polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("modern_andesite_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(modern_walls);


    }

    private BlockBehaviour.Properties standardCopyProperties(StoneType stoneType) {
        return Utils.copyPropertySafe(stoneType.bricksOrStone())
                .mapColor(MapColor.STONE)
                .sound(SoundType.STONE)
                .strength(2.0F, 6.0F)
                .noOcclusion()
                .requiresCorrectToolForDrops();
    }

}