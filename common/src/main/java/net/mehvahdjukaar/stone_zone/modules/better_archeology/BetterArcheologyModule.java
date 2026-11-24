package net.mehvahdjukaar.stone_zone.modules.better_archeology;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.mud.VanillaMudTypes;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.api.BlockTypeChildrenHelper.copyChildProperties;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

///SUPPORT: v1.3.9
public class BetterArcheologyModule extends StoneZoneModule {

    public final BlockSetEntries<MudType> cracked_bricksSet;

    public BetterArcheologyModule(String modId) {
        super(modId, "ba");
        Supplier<CreativeModeTab> tab = getModTab(modId);

        cracked_bricksSet = BlockSetEntries.of3Blocks(this, MudType.class, VanillaMudTypes.MUD,
                "cracked_mud_bricks",
                "cracked_mud_brick_slab",
                "cracked_mud_brick_stairs",
                tab
        )
                .block(builder -> builder
                        .requiresChildren(BRICKS) //REASON: Recipes & Palettes
                        .addTexture(modRes("block/cracked_mud_bricks"), StonePaletteStrategies.BRICKS_STANDARD)
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        .setProperties(mudType -> Utils.copyPropertySafe(mudType.bricksOrStone()))
                )
                .slab(builder -> builder
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        .addRecipe(Identifier.withDefaultNamespace("cracked_mud_brick_slab_from_cracked_mud_bricks_stonecutting"))
                        .setProperties(mudType -> copyChildProperties(mudType, BRICK_SLAB))
                )
                .stairs(builder -> builder
                        .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        .addRecipe(Identifier.withDefaultNamespace("cracked_mud_brick_stairs_from_cracked_mud_bricks_stonecutting"))
                        .setProperties(mudType -> copyChildProperties(mudType, BRICK_STAIRS))
                )
                .noWall()
                .noSlabAndStairsTag()
                .defaultRecipe()
                .build();
        this.addEntries(cracked_bricksSet);

    }
}
