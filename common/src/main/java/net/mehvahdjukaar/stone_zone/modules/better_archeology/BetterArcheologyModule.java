package net.mehvahdjukaar.stone_zone.modules.better_archeology;

import net.Pandarix.BACommon;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.mud.VanillaMudTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.copyBlockStateSafe;
import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.copyChildrenPropertySafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICK_SLAB;

//SUPPORT: v1.3.2
public class BetterArcheologyModule extends StoneZoneModule {

    public final SimpleEntrySet<MudType, Block> cracked_bricks;
    public final SimpleEntrySet<MudType, Block> cracked_brick_stairs;
    public final SimpleEntrySet<MudType, Block> cracked_brick_slabs;

    public BetterArcheologyModule(String modId) {
        super(modId, "ba");
        ResourceLocation tab = modRes(BACommon.MOD_ID);

        cracked_bricks = StoneZoneEntrySet.of(MudType.class, "bricks", "cracked",
                        getModBlock("cracked_mud_bricks"), () -> VanillaMudTypes.MUD,
                        mudType -> new Block(copyChildrenPropertySafe(BRICKS, mudType))
                )
                .requiresChildren(BRICKS) //REASON: Recipes & palettes
                .addTexture(modRes("block/cracked_mud_bricks"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cracked_bricks);

        cracked_brick_stairs = StoneZoneEntrySet.of(MudType.class, "brick_stairs", "cracked",
                        getModBlock("cracked_mud_brick_stairs"), () -> VanillaMudTypes.MUD,
                        type -> new StairBlock(copyBlockStateSafe(cracked_bricks.blocks, type),
                                copyChildrenPropertySafe("brick_stairs", type))
                )
                .requiresFromMap(cracked_bricks.blocks) //REASON: Recipes, Textures
                //TEXTURES: cracked_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cracked_mud_brick_stairs"))
                .addRecipe(ResourceLocation.withDefaultNamespace("cracked_mud_brick_stairs_from_cracked_mud_bricks_stonecutting"))
                .build();
        this.addEntry(cracked_brick_stairs);

        cracked_brick_slabs = StoneZoneEntrySet.of(MudType.class, "brick_slab", "cracked",
                        getModBlock("cracked_mud_brick_slab"), () -> VanillaMudTypes.MUD,
                        mudType -> new SlabBlock(copyChildrenPropertySafe(BRICK_SLAB, mudType))
                )
                .requiresFromMap(cracked_bricks.blocks) //REASON: Recipes, Textures
                //TEXTURES: cracked_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cracked_mud_brick_slab"))
                .addRecipe(ResourceLocation.withDefaultNamespace("cracked_mud_brick_slab_from_cracked_mud_bricks_stonecutting"))
                .build();
        this.addEntry(cracked_brick_slabs);
    }
}