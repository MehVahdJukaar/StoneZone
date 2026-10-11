package net.mehvahdjukaar.stone_zone.modules.better_archeology;

import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.mud.VanillaMudTypes;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.getChildrenBlockSafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

///SUPPORT: v1.3.9
public class BetterArcheologyModule extends StoneZoneModule {

    public BetterArcheologyModule(String modId) {
        super(modId, "ba");
        Supplier<CreativeModeTab> tab = getModTab(modId);

        var blockSet = BlockSetEntry.of(MudType.class, this, VanillaMudTypes.MUD,
                "cracked_mud_bricks", MudType::bricksOrStone,
                "cracked_mud_brick_slab", mudType -> getChildrenBlockSafe(BRICK_SLAB, mudType),
                "cracked_mud_brick_stairs", mudType -> getChildrenBlockSafe(BRICK_STAIRS, mudType),
                tab
        )
                .block(builder ->
                        builder.requiresChildren(BRICKS) //REASON: Recipes & Palettes
                                .addTexture(modRes("block/cracked_mud_bricks"), StonePaletteStrategies.BRICKS_STANDARD)
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                                .defaultRecipe()
                )
                .slab(builder ->
                        builder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                                .addRecipe(modRes("cracked_mud_brick_slab"))
                                .addRecipe(ResourceLocation.withDefaultNamespace("cracked_mud_brick_slab_from_cracked_mud_bricks_stonecutting"))
                )
                .stairs(builder ->
                        builder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                                .addRecipe(modRes("cracked_mud_brick_stairs"))
                                .addRecipe(ResourceLocation.withDefaultNamespace("cracked_mud_brick_stairs_from_cracked_mud_bricks_stonecutting"))
                )
                .noSlabAndStairsTag()
                .noWall()
                .build();
        this.addEntry(blockSet.block);
        this.addEntry(blockSet.slab);
        this.addEntry(blockSet.stairs);

    }
}