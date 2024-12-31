package net.mehvahdjukaar.stone_zone.modules.quark;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import org.violetmoon.quark.content.building.module.MoreMudBlocksModule;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.block.ZetaPillarBlock;

// Why is this not in QuarkModule: both stonetype and mudtype have a PILLAR and that causes issues with models/textures
// as the same module can't have two blocks with the same name even if they're for a different BlockType
public class QuarkMudModule extends SZModule {

    public final SimpleEntrySet<MudType, Block> carved_bricks;

    public final SimpleEntrySet<MudType, Block> pillars;

    public final SimpleEntrySet<MudType, Block> brick_lattices;

    public QuarkMudModule(String modId) {
        super(modId, "q");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        carved_bricks = QuarkEntrySet.of(MudType.class, "bricks", "carved", MoreMudBlocksModule.class,
                        getModBlock("carved_mud_bricks"), MudTypeRegistry::getBaseMudType,
                        mudType -> {
                            String name = shortenedId() + "/" + mudType.getAppendableIdWith("carved", "bricks");
                            return new ZetaBlock(name, null, Utils.copyPropertySafe(mudType.bricks()));
                        }
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks", "brick_slab") // REASON: recipes, textures & palette
                .addTexture(modRes("block/carved_mud_bricks"))
                .setTabKey(tab)
                .addRecipe(modRes("building/crafting/carved_mud_bricks"))
                .addRecipe(modRes("building/stonecutting/carved_mud_bricks_stonecutter"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();
        this.addEntry(carved_bricks);

        pillars = QuarkEntrySet.of(MudType.class, "pillar", MoreMudBlocksModule.class,
                        getModBlock("mud_pillar"), MudTypeRegistry::getBaseMudType,
                        mudType -> {
                            String name = shortenedId() + "/" + mudType.getAppendableIdWith("pillar");
                            return new ZetaPillarBlock(name, null, Utils.copyPropertySafe(mudType.bricks()));
                        }
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks", "brick_slab") // REASON: recipes & palette
                .addTexture(modRes("block/mud_pillar"))
                .addTexture(modRes("block/mud_pillar_top"))
                .setTabKey(tab)
                .addRecipe(modRes("building/crafting/mud_pillar"))
                .addRecipe(modRes("building/stonecutting/mud_pillar_stonecutter"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();
        this.addEntry(pillars);

        brick_lattices = QuarkEntrySet.of(MudType.class, "brick_lattice", MoreMudBlocksModule.class,
                        getModBlock("mud_brick_lattice"), MudTypeRegistry::getBaseMudType,
                        mudType -> {
                            String name = shortenedId() + "/" + mudType.getAppendableIdWith("brick_lattice");
                            return new CompatBrickLatticeBlock(name, null, Utils.copyPropertySafe(mudType.bricks()));
                        }
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") // REASON: recipes & palette
                .addTexture(modRes("block/mud_brick_lattice"))
                .setTabKey(tab)
                .addRecipe(modRes("building/crafting/mud_brick_lattice"))
                .addRecipe(modRes("building/stonecutting/mud_brick_lattice_stonecutter"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(brick_lattices);
    }
}
