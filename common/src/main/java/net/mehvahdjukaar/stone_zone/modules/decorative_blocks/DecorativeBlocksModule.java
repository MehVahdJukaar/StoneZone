package net.mehvahdjukaar.stone_zone.modules.decorative_blocks;

import lilypuree.decorative_blocks.blocks.PillarBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;


//SUPPORT: v4.1.3+
public class DecorativeBlocksModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> pillar;

    public DecorativeBlocksModule(String modId) {
        super(modId, "db");
        ResourceLocation tab = modRes("general");

        pillar = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("stone_pillar"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PillarBlock(Utils.copyPropertySafe(stoneType.stone)
                                .strength(1.5F, 6.5F)
                                .sound(stoneType.getSound())
                        )
                )
                .addTexture(modRes("block/stone_pillar_side"))
                .addTexture(modRes("block/stone_pillar_end"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(pillar);

    }
}