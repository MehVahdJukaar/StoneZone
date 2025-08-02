package net.mehvahdjukaar.stone_zone.modules.lets_do;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.common_classes.TagUtility;
import net.mehvahdjukaar.stone_zone.api.StoneZoneSimpleModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.PushReaction;
import net.satisfy.candlelight.core.block.CStoveBlock;
import net.satisfy.farm_and_charm.core.block.LineConnectingBlock;
import net.satisfy.farm_and_charm.core.block.SinkBlock;


//SUPPORT: v2.0.4+
public class CandlelightModule extends StoneZoneSimpleModule {

    public final SimpleEntrySet<StoneType, Block> counter;
    public final SimpleEntrySet<StoneType, Block> stove;
    public final SimpleEntrySet<StoneType, Block> kitchen_sink;

    public CandlelightModule(String modId) {
        super(modId, "ldcl");
        ResourceLocation tab = modRes(modId);

        counter = StoneZoneEntrySet.of(StoneType.class, "counter",
                        getModBlock("sandstone_counter"), StoneTypeRegistry::getSandstoneType,
                        stoneType -> new LineConnectingBlock(Utils.copyPropertySafe(stoneType.stone)
                                .noOcclusion()
                        )
                )
                //TEXTURES: sandstone_sink_side, sandstone_sink_bottom, sandstone_stove_top
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(counter);

        kitchen_sink = StoneZoneEntrySet.of(StoneType.class, "kitchen_sink",
                        getModBlock("sandstone_kitchen_sink"), StoneTypeRegistry::getSandstoneType,
                        stoneType -> new SinkBlock(Utils.copyPropertySafe(stoneType.stone)
                                .noOcclusion()
                                .pushReaction(PushReaction.IGNORE)
                        )
                )
                //TEXTURES: sandstone_stove_top
                .addTexture(modRes("block/sandstone_sink_side"))
                .addTexture(modRes("block/sandstone_sink_bottom"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(kitchen_sink);

        stove = StoneZoneEntrySet.of(StoneType.class, "stove",
                        getModBlock("sandstone_stove"), StoneTypeRegistry::getSandstoneType,
                        stoneType -> new CStoveBlock(Utils.copyPropertySafe(stoneType.stone)
                                .lightLevel(s -> 12)
                        )
                )
                .addTile(getModTile("stove_block"))
                .addTexture(modRes("block/sandstone_woodfiredoven_front"))
                .addTexture(modRes("block/sandstone_woodfiredoven_front_on"))
                .addTexture(modRes("block/sandstone_woodfiredoven_top"))
                .addTexture(modRes("block/sandstone_woodfiredoven_top_on"))
                .addTexture(modRes("block/sandstone_stove_side"))
                .addTexture(modRes("block/sandstone_stove_bottom"))
                .addTexture(modRes("block/sandstone_stove_top"))
                .addTexture(modRes("block/sandstone_stove_mid"))
                .addTexture(modRes("block/sandstone_stove_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stoves"), Registries.BLOCK)
                .addTag(new ResourceLocation("farmersdelight:heat_sources"), Registries.BLOCK)
                .addTag(TagUtility.fabricTag("allows_cooking"), Registries.BLOCK)
                .addTag(TagUtility.forgeTag("allows_cooking"), Registries.BLOCK)
                .addTag(modRes("allows_cooking"), Registries.BLOCK)
                .addTag(new ResourceLocation("farm_and_charm:allows_cooking"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stove);

    }
}