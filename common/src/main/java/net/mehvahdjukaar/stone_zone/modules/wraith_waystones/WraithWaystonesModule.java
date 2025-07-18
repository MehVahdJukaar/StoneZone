package net.mehvahdjukaar.stone_zone.modules.wraith_waystones;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import wraith.fwaystones.FabricWaystones;
import wraith.fwaystones.block.WaystoneBlock;
import wraith.fwaystones.item.WaystoneItem;

import static net.mehvahdjukaar.stone_zone.StoneZone.res;


//SUPPORT: v3.3.3+
public class WraithWaystonesModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> brick_waystone;

    public WraithWaystonesModule(String modId) {
        super(modId, "wws");
        ResourceLocation tab = modRes(modId);

        brick_waystone = StoneZoneEntrySet.of(StoneType.class, "brick_waystone",
                        getModBlock("stone_brick_waystone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WaystoneBlock(Utils.copyPropertySafe(stoneType.stone)
                                .strength(FabricWaystones.CONFIG.waystone_block_hardness(), 3600000.0F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .addTile(getModTile("waystone"))
                .addTextureM(modRes("block/stone_brick_waystone_active"),
                        res("block/wws/stone_brick_waystone_active_m"))
                .addTextureM(modRes("block/stone_brick_waystone_inactive"),
                        res("block/wws/stone_brick_waystone_inactive_m.png"))

                .addTextureM(modRes("block/mossy_stone_brick_waystone_active"),
                        res("block/wws/mossy_stone_brick_waystone_active_m"))
                .addTextureM(modRes("block/mossy_stone_brick_waystone_inactive"),
                        res("block/wws/mossy_stone_brick_waystone_inactive_m.png"))

                .addTextureM(modRes("item/stone_brick_waystone"),
                        res("item/wws/stone_brick_waystone_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(modRes("waystones"), Registries.BLOCK)
                .addTag(modRes("waystones"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                //RECIPES: Manully created below
                .addCustomItem((stoneType, block, properties) -> new WaystoneItem(block, properties))
                .copyParentDrop()
                .build();
        this.addEntry(brick_waystone);

    }

}