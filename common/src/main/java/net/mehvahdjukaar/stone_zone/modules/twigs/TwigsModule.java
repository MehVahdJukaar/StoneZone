package net.mehvahdjukaar.stone_zone.modules.twigs;

import com.ninni.twigs.block.ColumnBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.type.StoneType;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<StoneType, Block> columns;

    public TwigsModule(String modId) {
        super(modId, "tw");

        //noinspection DataFlowIssue
        columns = addEntry(SimpleEntrySet.builder(StoneType.class, "column",
                        getModBlock("stone_column"), () -> StoneTypeRegistry.STONE_TYPE,
                        stoneType -> new ColumnBlock(Utils.copyPropertySafe(
                                        (stoneType.getBlockOfThis("bricks") != null)
                                        ? stoneType.getBlockOfThis("bricks")
                                        : Blocks.STONE_BRICKS)
                                )
                        )
                .createPaletteFromChild(
                        "bricks")
                .addTexture(modRes("block/stone_column"))
                .addTexture(modRes("block/stone_column_bottom"))
                .addTexture(modRes("block/stone_column_tip"))
                .addTexture(modRes("block/stone_column_top"))
                .setTabKey(modRes("twig"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addRecipe(modRes("stone_column_stonecutting"))
                .build()
        );

    }

}
