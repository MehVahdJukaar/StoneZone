package net.mehvahdjukaar.stone_zone.modules.waystones;

import net.blay09.mods.waystones.block.WaystoneBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneSimpleModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;


//SUPPORT: v
public class WaystonesModule extends StoneZoneSimpleModule {

    public final SimpleEntrySet<StoneType, Block> waystone;

    public WaystonesModule(String modId) {
        super(modId, "wys");
        ResourceLocation tab = modRes(modId);
        
        waystone = StoneZoneEntrySet.of(StoneType.class, "waystone",
                        getModBlock("blackstone_waystone"), () -> StoneTypeRegistry.getValue("blackstone"),
                        stoneType -> new WaystoneBlock(Utils.copyPropertySafe(stoneType.stone)
                                .sound(stoneType.getSound())
                                .strength(5.0F, 2000.0F)
                        )
                )
                .addTile(getModTile("waystone"))
                .addTexture(modRes("block/blackstone_waystone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("waystones"), Registries.BLOCK)
                .addTag(modRes("waystone"), Registries.BLOCK)
                .addTag(modRes("is_teleport_target"), Registries.BLOCK)
                .addTag(modRes("waystones"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(waystone);
        
    }
}