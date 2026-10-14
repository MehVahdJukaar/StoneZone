package net.mehvahdjukaar.stone_zone.modules.create;

import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.getChildrenBlockSafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;


///SUPPORT: v2.3.5+
public class BitsNBobsModuleAbstract extends StoneZoneModule {

    public BitsNBobsModuleAbstract(String modId) {
        super(modId, "cbnb");
        Supplier<CreativeModeTab> palettesTab = getModTab("bnb_palettes");

        var blockSet = BlockSetEntry.of(StoneType.class, this, VanillaStoneTypes.ANDESITE,
                        "andesite_tiles", stoneType -> stoneType.stone,
                        "andesite_tile_slab", stoneType -> getChildrenBlockSafe(SLAB, stoneType),
                        "andesite_tile_stairs", stoneType -> getChildrenBlockSafe(STAIRS, stoneType),
                        "andesite_tile_wall", stoneType -> getChildrenBlockSafe(WALL, stoneType),
                        palettesTab
                )
                .block(builder -> builder
                        .addTexture(modRes("block/palettes/stone_types/tiles/andesite_cut_tiles"))
                        .addTexture(modRes("block/palettes/stone_types/tiles_flipped/andesite_cut_tiles_flipped"))
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                        //RECIPES: andesite_tiles_from_stone_types_andesite_stonecutting - manually created in CreateModuleAbstract
                )
                .slab(builder -> builder
                        //TEXTURES: andesite_tiles, andesite_cut_tiles_flipped
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        .defaultRecipe()
                        .addRecipe(ResourceLocation.parse("create:andesite_tile_slab_recycling")) //Crafts andesite_tiles
                )
                .stairs(builder -> builder
                        //TEXTURES: andesite_tiles, andesite_cut_tiles_flipped
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                        .defaultRecipe()
                )
                .wall(builder -> builder
                        //TEXTURES: andesite_tiles, andesite_cut_tiles_flipped
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                        .defaultRecipe()
                )
                .build();
        this.addEntries(blockSet);

    }
}