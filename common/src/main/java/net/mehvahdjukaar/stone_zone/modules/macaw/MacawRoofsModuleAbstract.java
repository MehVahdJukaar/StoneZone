package net.mehvahdjukaar.stone_zone.modules.macaw;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;


///SUPPORT: v2.3.1+
public abstract class MacawRoofsModuleAbstract extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> roofs;
    public final SimpleEntrySet<StoneType, Block> attic_roofs;
    public final SimpleEntrySet<StoneType, Block> top_roofs;
    public final SimpleEntrySet<StoneType, Block> lower_roofs;
    public final SimpleEntrySet<StoneType, Block> steep_roofs;
    public final SimpleEntrySet<StoneType, Block> upper_lower_roofs;
    public final SimpleEntrySet<StoneType, Block> upper_steep_roofs;

    public final SimpleEntrySet<StoneType, Block> bricks_roofs;
    public final SimpleEntrySet<StoneType, Block> bricks_attic_roofs;
    public final SimpleEntrySet<StoneType, Block> bricks_top_roofs;
    public final SimpleEntrySet<StoneType, Block> bricks_lower_roofs;
    public final SimpleEntrySet<StoneType, Block> bricks_steep_roofs;
    public final SimpleEntrySet<StoneType, Block> bricks_upper_lower_roofs;
    public final SimpleEntrySet<StoneType, Block> bricks_upper_steep_roofs;

    public MacawRoofsModuleAbstract(String modId) {
        super(modId, "mcr");
        Supplier<CreativeModeTab> tab = getModTab(modId);

        roofs = StoneZoneEntrySet.of(StoneType.class, "roof",
                        getModBlock("stone_roof"), () -> VanillaStoneTypes.STONE,
                        this::newBaseRoof
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(roofs);

        attic_roofs = StoneZoneEntrySet.of(StoneType.class, "attic_roof",
                        getModBlock("stone_attic_roof"), () -> VanillaStoneTypes.STONE,
                        this::newRoofGlass
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(attic_roofs);

        top_roofs = StoneZoneEntrySet.of(StoneType.class, "top_roof",
                        getModBlock("stone_top_roof"), () -> VanillaStoneTypes.STONE,
                        this::newRoofTopNew
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(top_roofs);

        lower_roofs = StoneZoneEntrySet.of(StoneType.class, "lower_roof",
                        getModBlock("stone_lower_roof"), () -> VanillaStoneTypes.STONE,
                        this::newBaseRoof
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(lower_roofs);

        steep_roofs = StoneZoneEntrySet.of(StoneType.class, "steep_roof",
                        getModBlock("stone_steep_roof"), () -> VanillaStoneTypes.STONE,
                        this::newSteepRoof
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(steep_roofs);

        upper_lower_roofs = StoneZoneEntrySet.of(StoneType.class, "upper_lower_roof",
                        getModBlock("stone_upper_lower_roof"), () -> VanillaStoneTypes.STONE,
                        this::newLower
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(upper_lower_roofs);

        upper_steep_roofs = StoneZoneEntrySet.of(StoneType.class, "upper_steep_roof",
                        getModBlock("stone_upper_steep_roof"), () -> VanillaStoneTypes.STONE,
                        this::newSteep
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(upper_steep_roofs);

        bricks_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_roof",
                        getModBlock("stone_bricks_roof"), () -> VanillaStoneTypes.STONE,
                        this::newBaseRoof
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_roofs);

        bricks_attic_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_attic_roof",
                        getModBlock("stone_bricks_attic_roof"), () -> VanillaStoneTypes.STONE,
                        this::newRoofGlass
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_attic_roofs);

        bricks_top_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_top_roof",
                        getModBlock("stone_bricks_top_roof"), () -> VanillaStoneTypes.STONE,
                        this::newRoofTopNew
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_top_roofs);

        bricks_lower_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_lower_roof",
                        getModBlock("stone_bricks_lower_roof"), () -> VanillaStoneTypes.STONE,
                        this::newBaseRoof
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_lower_roofs);

        bricks_steep_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_steep_roof",
                        getModBlock("stone_bricks_steep_roof"), () -> VanillaStoneTypes.STONE,
                        this::newSteepRoof
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_steep_roofs);

        bricks_upper_lower_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_upper_lower_roof",
                        getModBlock("stone_bricks_upper_lower_roof"), () -> VanillaStoneTypes.STONE,
                        this::newLower
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_upper_lower_roofs);

        bricks_upper_steep_roofs = StoneZoneEntrySet.of(StoneType.class, "bricks_upper_steep_roof",
                        getModBlock("stone_bricks_upper_steep_roof"), () -> VanillaStoneTypes.STONE,
                        this::newSteep
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .setProperties(this::standardProperties)
                .defaultRecipe()
                .build();
        this.addEntry(bricks_upper_steep_roofs);

    }

    public abstract Block newBaseRoof(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newRoofGlass(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newRoofTopNew(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newSteepRoof(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newLower(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newSteep(StoneType stoneType, BlockBehaviour.Properties properties);

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           METHODS                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
    public BlockBehaviour.Properties standardProperties(StoneType stoneType) {
        return Utils.copyPropertySafe(stoneType.stone)
                .strength(1.5F, 3.0F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops();
    }

}
