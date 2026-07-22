package net.mehvahdjukaar.stone_zone.modules.decorative_blocks;

import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;


//SUPPORT: v4.1.3+
public class DecorativeBlocksModule extends StoneZoneModule {

//    public final SimpleEntrySet<StoneType, Block> pillar;

    public DecorativeBlocksModule(String modId) {
        super(modId, "db");
        Supplier<CreativeModeTab> tab = getModTab("general");
//
//        pillar = StoneZoneEntrySet.of(StoneType.class, "pillar",
//                        getModBlock("stone_pillar"), () -> VanillaStoneTypes.STONE,
//                        stoneType -> new PillarBlock(Utils.copyPropertySafe(stoneType.stone)
//                                .strength(1.5F, 6.5F)
//                                .sound(stoneType.getSound())
//                        )
//                )
//                .addTexture(modRes("block/stone_pillar_side"))
//                .addTexture(modRes("block/stone_pillar_end"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .setTab(tab)
//                .defaultRecipe()
//                .build();
//        this.addEntry(pillar);

    }
}