package net.mehvahdjukaar.stone_zone.modules.quark;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.quark.content.building.module.MoreStoneVariantsModule;
import org.violetmoon.quark.content.building.module.VerticalSlabsModule;
import org.violetmoon.zeta.block.ZetaPillarBlock;

import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.copyChildrenPropertySafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

//SUPPORT: v4.0-4.6.0+
public class QuarkModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> vertical_slabs;
    public final SimpleEntrySet<StoneType, Block> polished_vertical_slabs;
    public final SimpleEntrySet<StoneType, Block> pillars;

    public QuarkModule(String modId) {
        super(modId, "q");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        vertical_slabs = QuarkEntrySet.of(StoneType.class, "vertical_slab",
                        VerticalSlabsModule.class,
                        getModBlock("andesite_vertical_slab"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new VerticalSlabBlock(() -> stoneType.getBlockOfThis("slab"),
                                copyChildrenPropertySafe("slab", stoneType))
                )
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/vertslabs/andesite_vertical_slab_stonecutter"))
                .addRecipe(modRes("building/crafting/vertslabs/andesite_vertical_slab"))
                .addRecipe(modRes("building/crafting/vertslabs/andesite_vertical_slab_revert"))
                .copyParentDrop()
                .addCondition(stoneType -> !PlatHelper.isModLoaded("v_slab_compat"))
                .build();
        this.addEntry(vertical_slabs);

        polished_vertical_slabs = QuarkEntrySet.of(StoneType.class, "vertical_slab", "polished",
                        VerticalSlabsModule.class,
                        getModBlock("polished_andesite_vertical_slab"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new VerticalSlabBlock(() -> stoneType.getBlockOfThis(POLISHED_SLAB),
                                copyChildrenPropertySafe(POLISHED_SLAB, stoneType))
                )
                .requiresChildren(POLISHED_SLAB, POLISHED) //REASON: recipes, textures
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/stonecutting/vertslabs/polished_andesite_vertical_slab_stonecutter"))
                .addRecipe(modRes("building/crafting/vertslabs/polished_andesite_vertical_slab"))
                .addRecipe(modRes("building/crafting/vertslabs/polished_andesite_vertical_slab_revert"))
                .copyParentDrop()
                .addCondition(stoneType -> !PlatHelper.isModLoaded("v_slab_compat"))
                .build();
        this.addEntry(polished_vertical_slabs);

        pillars = QuarkEntrySet.of(StoneType.class, "pillar", MoreStoneVariantsModule.class,
                        getModBlock("andesite_pillar"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> {
                            String name = shortenedId() + "/" + stoneType.getAppendableIdWith("pillar");
                            return new ZetaPillarBlock(name, null, Utils.copyPropertySafe(stoneType.stone));
                        }
                )
                .requiresChildren(POLISHED_SLAB) //REASON: recipes
                .addTexture(modRes("block/andesite_pillar"))
                .addTexture(modRes("block/andesite_pillar_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("building/crafting/stonevariants/andesite_pillar"))
                .addRecipe(modRes("building/stonecutting/stonevariants/andesite_pillar_stonecutter"))
                .build();
        this.addEntry(pillars);

    }
}