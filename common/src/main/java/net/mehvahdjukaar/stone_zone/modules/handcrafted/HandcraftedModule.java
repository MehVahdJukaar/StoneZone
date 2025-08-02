package net.mehvahdjukaar.stone_zone.modules.handcrafted;

import earth.terrarium.handcrafted.common.blocks.trims.CornerTrimBlock;
import earth.terrarium.handcrafted.common.blocks.trims.PillarTrimBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneSimpleModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;


//SUPPORT: v3.0.6+
public class HandcraftedModule extends StoneZoneSimpleModule {

    public final SimpleEntrySet<StoneType, Block> pillar_trim;
    public final SimpleEntrySet<StoneType, Block> corner_trim;

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        ResourceLocation tab = modRes("main");

        pillar_trim = StoneZoneEntrySet.of(StoneType.class, "pillar_trim",
                        getModBlock("stone_pillar_trim"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PillarTrimBlock(Utils.copyPropertySafe(stoneType.stone).noOcclusion(), false)
                )
                .addTexture(modRes("block/trim/pillar/stone_pillar_trim_normal"))
                .addTexture(modRes("block/trim/pillar/stone_pillar_trim_thicc"))
                .addTexture(modRes("block/trim/pillar/stone_pillar_trim_thin"))
                .addTexture(modRes("block/trim/pillar/stone_pillar_trim_2_normal"))
                .addTexture(modRes("block/trim/pillar/stone_pillar_trim_2_thicc"))
                .addTexture(modRes("block/trim/pillar/stone_pillar_trim_2_thin"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("pillar_trims"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("pillar_trims"), Registries.ITEM)
                .addTag(modRes("trims"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((s, block, properties) -> new BlockItem(block, properties))
                .build();
        this.addEntry(pillar_trim);

        corner_trim = StoneZoneEntrySet.of(StoneType.class, "corner_trim",
                        getModBlock("stone_corner_trim"), StoneTypeRegistry::getStoneType,
                        stoneType -> new CornerTrimBlock(Utils.copyPropertySafe(stoneType.stone).noOcclusion(), false)
                )
                .addTexture(modRes("block/trim/corner/stone_corner_trim_normal"))
                .addTexture(modRes("block/trim/corner/stone_corner_trim_thicc"))
                .addTexture(modRes("block/trim/corner/stone_corner_trim_thin"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.ITEM)
                .addTag(modRes("trims"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((s, block, properties) -> new BlockItem(block, properties))
                .build();
        this.addEntry(corner_trim);

    }
}