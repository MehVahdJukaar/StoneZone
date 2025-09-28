package net.mehvahdjukaar.stone_zone.modules.wraith_waystones;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import wraith.fwaystones.FabricWaystones;
import wraith.fwaystones.block.WaystoneBlock;
import wraith.fwaystones.item.WaystoneItem;


//SUPPORT: v3.3.3+
public class WraithWaystonesModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> brick_waystone;

    public WraithWaystonesModule(String modId) {
        super(modId, "wws");
        ResourceLocation tab = modRes(modId);

        brick_waystone = StoneZoneEntrySet.of(StoneType.class, "brick_waystone",
                        getModBlock("stone_brick_waystone"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WaystoneBlock(Utils.copyPropertySafe(stoneType.stone)
                                .strength(FabricWaystones.CONFIG.waystone_block_hardness(), 3600000.0F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .requiresChildren(VanillaRockChildKeys.BRICKS) //REASON: recipes
                .addTile(getModTile("waystone"))
                .addTextureM(modRes("block/stone_brick_waystone_active"),
                        EveryCompat.res("block/wws/stone_brick_waystone_active_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTextureM(modRes("block/stone_brick_waystone_inactive"),
                        EveryCompat.res("block/wws/stone_brick_waystone_inactive_m.png"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTextureM(modRes("block/mossy_stone_brick_waystone_active"),
                        EveryCompat.res("block/wws/mossy_stone_brick_waystone_active_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTextureM(modRes("block/mossy_stone_brick_waystone_inactive"),
                        EveryCompat.res("block/wws/mossy_stone_brick_waystone_inactive_m.png"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTextureM(modRes("item/stone_brick_waystone"),
                        EveryCompat.res("item/wws/stone_brick_waystone_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
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