package net.mehvahdjukaar.stone_zone.modules.better_archeology;

import net.Pandarix.betterarcheology.BetterArcheology;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyBlockStateSafe;
import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;

public class BetterArcheologyModule extends StoneZoneModule {

    public final SimpleEntrySet<MudType, Block> cracked_bricks;
    public final SimpleEntrySet<MudType, Block> cracked_brick_stairs;
    public final SimpleEntrySet<MudType, Block> cracked_brick_slabs;

    public BetterArcheologyModule(String modId) {
        super(modId, "ba");
        ResourceLocation tab = modRes(BetterArcheology.MOD_ID);

        cracked_bricks = StoneZoneEntrySet.of(MudType.class, "bricks", "cracked",
                        getModBlock("cracked_mud_bricks"), MudTypeRegistry::getMudType,
                        type -> new Block(copyChildrenPropertySafe("bricks", type))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes & palettes
                .addTexture(modRes("block/cracked_mud_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cracked_bricks);

        cracked_brick_stairs = StoneZoneEntrySet.of(MudType.class, "brick_stairs", "cracked",
                        getModBlock("cracked_mud_brick_stairs"), MudTypeRegistry::getMudType,
                        type -> new StairBlock(copyBlockStateSafe(cracked_bricks.blocks, type),
                                copyChildrenPropertySafe("brick_stairs", type))
                )
                .createPaletteFromBricks()
                .requiresFromMap(cracked_bricks.blocks) //REASON: recipes & textures
                //TEXTURES: cracked_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cracked_mud_brick_stairs"))
                .addRecipe(modRes("cracked_mud_brick_stairs_stonecutting"))
                .build();
        this.addEntry(cracked_brick_stairs);

        cracked_brick_slabs = StoneZoneEntrySet.of(MudType.class, "brick_slab", "cracked",
                        getModBlock("cracked_mud_brick_slab"), MudTypeRegistry::getMudType,
                        type -> new SlabBlock(copyChildrenPropertySafe("brick_slab", type))
                )
                .createPaletteFromBricks()
                .requiresFromMap(cracked_bricks.blocks) //REASON: recipes & textures
                //TEXTURES: cracked_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("cracked_mud_brick_slabs"))
                .addRecipe(modRes("cracked_mud_brick_slabs_stonecutting"))
                .build();
        this.addEntry(cracked_brick_slabs);
    }
}