package net.mehvahdjukaar.stone_zone.modules.bbb;

import com.starfish_studios.bbb.block.ColumnBlock;
import com.starfish_studios.bbb.registry.BBBCreativeModeTab;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.modules.SZModule;
import net.mehvahdjukaar.stone_zone.type.StoneType;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Collection;
import java.util.Objects;

//SUPPORT: v3.1.0+
public class BuildingButBetterModule extends SZModule {

    public final SimpleEntrySet<StoneType, Block> columns;

    public BuildingButBetterModule(String modId) {
        super(modId, "bbb");

        columns = addEntry(SimpleEntrySet.builder(StoneType.class, "block",
                        getModBlock("stone_block"), () -> StoneTypeRegistry.STONE_TYPE,
                        stoneType -> new ColumnBlock(Utils.copyPropertySafe(stoneType.bricksOrStone())))
                .createPaletteFromChild("bricks")
                .addTexture(modRes("block/column/stone_lower"))
                .addTexture(modRes("block/column/stone_upper"))
                .addTexture(modRes("block/column/stone_top"))
                .addTexture(modRes("block/column/stone_middle"))
                .addTexture(modRes("block/column/stone_none"))
                .setTabKey(modRes("item_group"))
                .defaultRecipe()
                .addRecipe(modRes("stone_block_stonecutting"))
                .build()
        );

    }

    @Override
    public <T extends BlockType> void registerBlocks(Class<T> typeClass, Registrator<Block> registry, Collection<T> types) {
        super.registerBlocks(typeClass, registry, types);
    }
}
