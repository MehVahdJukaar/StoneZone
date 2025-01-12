package net.mehvahdjukaar.stone_zone.modules.quark;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import org.violetmoon.quark.content.building.block.MudBrickLatticeBlock;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.quark.content.building.module.MoreMudBlocksModule;
import org.violetmoon.quark.content.building.module.MoreStoneVariantsModule;
import org.violetmoon.quark.content.building.module.VerticalSlabsModule;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.block.ZetaPillarBlock;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;

//SUPPORT: v4.0-4.6.0+
public class QuarkModule extends SZModule {

    public final SimpleEntrySet<StoneType, Block> vertical_slabs;
    public final SimpleEntrySet<StoneType, Block> polished_vertical_slabs;
    public final SimpleEntrySet<StoneType, Block> pillars;
    public final SimpleEntrySet<MudType, Block> brick_lattices;
    public final SimpleEntrySet<MudType, Block> carved_bricks;

    public QuarkModule(String modId) {
        super(modId, "q");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        vertical_slabs = QuarkEntrySet.of(StoneType.class, "vertical_slab",
                        VerticalSlabsModule.class,
                        getModBlock("andesite_vertical_slab"), StoneTypeRegistry::ANDESITE_TYPE,
                        stoneType -> new VerticalSlabBlock(() -> stoneType.getBlockOfThis("slab"),
                                copyChildrenPropertySafe("slab", stoneType))
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/vertslabs/andesite_vertical_slab_stonecutter"))
                .addRecipe(modRes("building/crafting/vertslabs/andesite_vertical_slab"))
                .addRecipe(modRes("building/crafting/vertslabs/andesite_vertical_slab_revert"))
                .addCondition(stoneType -> !PlatHelper.isModLoaded("v_slab_compat"))
                .build();
        this.addEntry(vertical_slabs);

        polished_vertical_slabs = QuarkEntrySet.of(StoneType.class, "vertical_slab", "polished",
                        VerticalSlabsModule.class,
                        getModBlock("polished_andesite_vertical_slab"), StoneTypeRegistry::ANDESITE_TYPE,
                        stoneType -> new VerticalSlabBlock(() -> stoneType.getBlockOfThis("polished_slab"),
                                copyChildrenPropertySafe("polished_slab", stoneType))
                )
                .requiresChildren("polished_slab", "polished") //REASON: recipes & textures
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/vertslabs/polished_andesite_vertical_slab_stonecutter"))
                .addRecipe(modRes("building/crafting/vertslabs/polished_andesite_vertical_slab"))
                .addRecipe(modRes("building/crafting/vertslabs/polished_andesite_vertical_slab_revert"))
                .addCondition(stoneType -> !PlatHelper.isModLoaded("v_slab_compat"))
                .build();
        this.addEntry(polished_vertical_slabs);

        pillars = QuarkEntrySet.of(StoneType.class, "pillar", MoreStoneVariantsModule.class,
                        getModBlock("andesite_pillar"), StoneTypeRegistry::ANDESITE_TYPE,
                        stoneType -> {
                            String name = shortenedId() + "/" + stoneType.getAppendableIdWith("pillar");
                            return new ZetaPillarBlock(name, null, Utils.copyPropertySafe(stoneType.stone));
                        }
                )
                .requiresChildren("polished_slab") //REASON: recipes
                .addTexture(modRes("block/andesite_pillar"))
                .addTexture(modRes("block/andesite_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/crafting/stonevariants/andesite_pillar"))
                .addRecipe(modRes("building/stonecutting/stonevariants/andesite_pillar_stonecutter"))
                .build();
        this.addEntry(pillars);

//!! MUDTYPE
        brick_lattices = QuarkEntrySet.of(MudType.class, "brick_lattice", MoreMudBlocksModule.class,
                        getModBlock("mud_brick_lattice"), () -> MudTypeRegistry.MUD_TYPE,
                        mudType -> new MudBrickLatticeBlock(null, copyChildrenPropertySafe("bricks", mudType))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .addTexture(modRes("block/mud_brick_lattice"))
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/mud_brick_lattice_stonecutter"))
                .addRecipe(modRes("building/crafting/mud_brick_lattice"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(brick_lattices);

        carved_bricks = QuarkEntrySet.of(MudType.class, "bricks", "carved", MoreMudBlocksModule.class,
                        getModBlock("carved_mud_bricks"), () -> MudTypeRegistry.MUD_TYPE,
                        mudType -> {
                            String name = shortenedId() + "/" + mudType.getAppendableIdWith("carved", "bricks");
                            return new ZetaBlock(name, null, copyChildrenPropertySafe("bricks", mudType));
                        }
                )
                .createPaletteFromBricks()
                .requiresChildren("brick_slab", "bricks") //REASON: recipes & textures
                //TEXTURES: bricks
                .addTexture(modRes("block/carved_mud_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("architects_palette:wizard_blocks"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/carved_mud_bricks_stonecutter"))
                .addRecipe(modRes("building/crafting/carved_mud_bricks"))
                .build();
        this.addEntry(carved_bricks);

    }
}