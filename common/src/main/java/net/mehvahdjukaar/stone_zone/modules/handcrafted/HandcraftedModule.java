package net.mehvahdjukaar.stone_zone.modules.handcrafted;

import earth.terrarium.handcrafted.common.blocks.trims.CornerTrimBlock;
import earth.terrarium.handcrafted.common.blocks.trims.PillarTrimBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;


//SUPPORT: v4.0.3+
public class HandcraftedModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> pillar_trim;
    public final SimpleEntrySet<StoneType, Block> corner_trim;

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        Supplier<CreativeModeTab> tab = getModTab("main");

        pillar_trim = StoneZoneEntrySet.of(StoneType.class, "pillar_trim",
                        getModBlock("stone_pillar_trim"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PillarTrimBlock(false, Utils.copyPropertySafe(stoneType.stone).noOcclusion())
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
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((s, block, properties) -> new BlockItem(block, properties))
                .build();
        this.addEntry(pillar_trim);

        corner_trim = StoneZoneEntrySet.of(StoneType.class, "corner_trim",
                        getModBlock("stone_corner_trim"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new CornerTrimBlock(false, Utils.copyPropertySafe(stoneType.stone).noOcclusion())
                )
                .addTexture(modRes("block/trim/corner/stone_corner_trim_normal"))
                .addTexture(modRes("block/trim/corner/stone_corner_trim_thicc"))
                .addTexture(modRes("block/trim/corner/stone_corner_trim_thin"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.ITEM)
                .addTag(modRes("trims"), Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((s, block, properties) -> new BlockItem(block, properties))
                .build();
        this.addEntry(corner_trim);

    }
}