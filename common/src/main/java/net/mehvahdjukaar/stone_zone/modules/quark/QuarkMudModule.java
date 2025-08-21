package net.mehvahdjukaar.stone_zone.modules.quark;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.mud.VanillaMudTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import org.violetmoon.quark.content.building.block.MudBrickLatticeBlock;
import org.violetmoon.quark.content.building.module.MoreMudBlocksModule;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.block.ZetaPillarBlock;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICK_SLAB;


//SUPPORT: v4.0-4.6.0+
public class QuarkMudModule extends StoneZoneModule {

    public final SimpleEntrySet<MudType, Block> brick_lattices;
    public final SimpleEntrySet<MudType, Block> carved_bricks;
    public final SimpleEntrySet<MudType, Block> pillars;

    public QuarkMudModule(String modId) {
        super(modId, "q");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        brick_lattices = QuarkEntrySet.of(MudType.class, "brick_lattice", MoreMudBlocksModule.class,
                        getModBlock("mud_brick_lattice"), () -> VanillaMudTypes.MUD,
                        mudType -> new MudBrickLatticeBlock(null, copyChildrenPropertySafe(BRICKS, mudType))
                )
                .createPaletteFromBricks()
                .requiresChildren(BRICKS) //REASON: recipes, palettes
                .addTexture(modRes("block/mud_brick_lattice"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/mud_brick_lattice_stonecutter"))
                .addRecipe(modRes("building/crafting/mud_brick_lattice"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(brick_lattices);

        carved_bricks = QuarkEntrySet.of(MudType.class, "bricks", "carved", MoreMudBlocksModule.class,
                        getModBlock("carved_mud_bricks"), () -> VanillaMudTypes.MUD,
                        mudType -> {
                            String name = shortenedId() + "/" + mudType.getAppendableIdWith("carved", "bricks");
                            return new ZetaBlock(name, null, copyChildrenPropertySafe(BRICKS, mudType));
                        }
                )
                .createPaletteFromBricks()
                .requiresChildren(BRICK_SLAB, BRICKS) //REASON: recipes, textures, palettes
                //TEXTURES: bricks
                .addTexture(modRes("block/carved_mud_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/carved_mud_bricks_stonecutter"))
                .addRecipe(modRes("building/crafting/carved_mud_bricks"))
                .build();
        this.addEntry(carved_bricks);

        pillars = QuarkEntrySet.of(MudType.class, "pillar", MoreMudBlocksModule.class,
                        getModBlock("mud_pillar"), () -> VanillaMudTypes.MUD,
                        mudType -> {
                            String name = shortenedId() + "/" + mudType.getAppendableIdWith("pillar");
                            return new ZetaPillarBlock(name, null, Utils.copyPropertySafe(mudType.mud));
                        }
                )
                .createPaletteFromBricks()
                .requiresChildren(BRICK_SLAB, BRICKS) //REASON: recipes, palettes
                .addTexture(modRes("block/mud_pillar"))
                .addTexture(modRes("block/mud_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/crafting/mud_pillar"))
                .addRecipe(modRes("building/stonecutting/mud_pillar_stonecutter"))
                .build();
        this.addEntry(pillars);

    }
}