package net.mehvahdjukaar.stone_zone.modules.macaw;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

///SUPPORT: v1.1.2+
public abstract class MacawFencesModuleAbstract extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> modern_brick_walls,
            railing_brick_gates,
            railing_brick_walls,
            grass_topped_walls;

    public final SimpleEntrySet<StoneType, Block> pillar_walls,
            railing_gates,
            railing_walls,
            modern_walls;

    public MacawFencesModuleAbstract(String modId) {
        super(modId, "mcf");
        Supplier<CreativeModeTab> tab = (PlatHelper.getPlatform().isFabric()) ? getModTab("fencesgroup") : getModTab("fenceitemgroup");

        modern_brick_walls = StoneZoneEntrySet.of(StoneType.class, "brick_wall", "modern",
                        getModBlock("modern_stone_brick_wall"), () -> VanillaStoneTypes.STONE,
                        this::newFenceBlock
                )
                .requiresChildren(BRICKS, COBBLESTONE) //REASON: testures, recipes
                //TEXTURES: bricks, cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("modern_stone_brick_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(modern_brick_walls);

        railing_brick_gates = StoneZoneEntrySet.of(StoneType.class, "brick_railing_gate",
                        getModBlock("stone_brick_railing_gate"), () -> VanillaStoneTypes.STONE,
                        this::newFenceGateBlock
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_railing_gate_stonecutter"))
                .build();
        this.addEntry(railing_brick_gates);

        railing_brick_walls = StoneZoneEntrySet.of(StoneType.class, "brick_wall", "railing",
                        getModBlock("railing_stone_brick_wall"), () -> VanillaStoneTypes.STONE,
                        this::newFenceBlock
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("railing_stone_brick_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(railing_brick_walls);

        grass_topped_walls = StoneZoneEntrySet.of(StoneType.class, "grass_topped_wall",
                        getModBlock("stone_grass_topped_wall"), () -> VanillaStoneTypes.STONE,
                        this::newFenceHitbox
                )
                .requiresChildren(BRICKS, COBBLESTONE) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks, cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(grass_topped_walls);

//!! ANDESITE
        pillar_walls = StoneZoneEntrySet.of(StoneType.class, "pillar_wall",
                        getModBlock("andesite_pillar_wall"), () -> VanillaStoneTypes.ANDESITE,
                        this::newFenceBlock
                )
                .requiresChildren(POLISHED) //REASON: textures, recipes
                //TEXTURES: stone, polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(pillar_walls);

        railing_gates = StoneZoneEntrySet.of(StoneType.class, "railing_gate",
                        getModBlock("andesite_railing_gate"), () -> VanillaStoneTypes.ANDESITE,
                        this::newFenceGateBlock
                )
                .requiresChildren(POLISHED) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("andesite_railing_gate_stonecutter"))
                .build();
        this.addEntry(railing_gates);

        railing_walls = StoneZoneEntrySet.of(StoneType.class, "wall", "railing",
                        getModBlock("railing_andesite_wall"), () -> VanillaStoneTypes.ANDESITE,
                        this::newFenceBlock
                )
                .requiresChildren(POLISHED) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("railing_andesite_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(railing_walls);

        modern_walls = StoneZoneEntrySet.of(StoneType.class, "wall", "modern",
                        getModBlock("modern_andesite_wall"), () -> VanillaStoneTypes.ANDESITE,
                        this::newFenceBlock
                )
                .requiresChildren(POLISHED) //REASON: testures, recipes
                //TEXTURES: stone, polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addRecipe(modRes("modern_andesite_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(modern_walls);


    }

    protected BlockBehaviour.Properties standardCopyProperties(StoneType stoneType) {
        return Utils.copyPropertySafe(stoneType.bricksOrStone())
                .mapColor(MapColor.STONE)
                .sound(SoundType.STONE)
                .strength(2.0F, 6.0F)
                .noOcclusion()
                .requiresCorrectToolForDrops();
    }

    public abstract Block newFenceBlock(StoneType stoneType);
    public abstract Block newFenceGateBlock(StoneType stoneType);
    public abstract Block newFenceHitbox(StoneType stoneType);

}