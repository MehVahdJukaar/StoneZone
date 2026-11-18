package net.mehvahdjukaar.stone_zone.modules.waystones;

import net.blay09.mods.waystones.api.WaystoneType;
import net.blay09.mods.waystones.block.BuiltinWaystoneType;
import net.blay09.mods.waystones.block.WaystoneBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;

///SUPPORT: v26.1.2.17+
public class WaystonesModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, WaystoneBlock> waystone;

    public WaystonesModule(String modId) {
        super(modId, "wys");
        Supplier<CreativeModeTab> tab = getModTab(modId);
        
        waystone = StoneZoneEntrySet.of(StoneType.class, "waystone",
                        getModBlock("deepslate_waystone", WaystoneBlock.class), () -> VanillaStoneTypes.DEEPSLATE,
                        (stoneType, properties) -> new WaystoneBlock(createWaystoneType(stoneType),
                                properties
                                        .sound(stoneType.getSound())
                                        .strength(5.0F, 2000F)
                                        .lightLevel(state -> state.getValue(WaystoneBlock.SEEN) ? 10 : 0)
                        )
                )
                .addCondition(stoneType -> PlatHelper.isModLoaded("create_waystones_recipes") && stoneType.hasChild(BRICKS))
                .addTile(getModTile("waystone"))
                .addTexture(modRes("block/deepslate_waystone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("waystones"), Registries.BLOCK)
                .addTag(modRes("waystone"), Registries.BLOCK)
                .addTag(modRes("is_teleport_target"), Registries.BLOCK)
                .addTag(modRes("waystones"), Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(waystone);

    }

    public Supplier<Block> getWaystoneBlockSupplier(StoneType stoneType) {
        return () -> waystone.blocks.get(stoneType);
    }

    public WaystoneType createWaystoneType(StoneType stoneTYpe) {
        return WaystoneType.register(
               new BuiltinWaystoneType(stoneTYpe.getAppendableId(),
                       -1,
                       stoneTYpe.getSound(), stoneTYpe
                       .block,
                       () -> Ingredient.of(stoneTYpe.bricksOrStone().asItem()), getWaystoneBlockSupplier(stoneTYpe))
        );
    }
}