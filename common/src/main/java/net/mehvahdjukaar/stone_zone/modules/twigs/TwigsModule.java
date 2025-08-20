package net.mehvahdjukaar.stone_zone.modules.twigs;

import com.ninni.twigs.block.ColumnBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//SUPPORT: v3.1.0+
public class TwigsModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> columns;

    public TwigsModule(String modId) {
        super(modId, "tw");

        columns = StoneZoneEntrySet.of(StoneType.class, "column",
                        getModBlock("stone_column"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new ColumnBlock(Utils.copyPropertySafe(stoneType.bricksOrStone()))
                )
                .createPaletteFromChild(p -> {
                    while (p.size() > 7) {
                        p.reduce();
                    }
                    p.reduceUp();
                    p.reduceUp();
                }, "stone")
                .addTexture(modRes("block/stone_column"))
                .addTexture(modRes("block/stone_column_bottom"))
                .addTexture(modRes("block/stone_column_tip"))
                .addTexture(modRes("block/stone_column_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(modRes("twig"))
                .defaultRecipe()
                .addRecipe(modRes("stone_column_stonecutting"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(columns);

    }

}
