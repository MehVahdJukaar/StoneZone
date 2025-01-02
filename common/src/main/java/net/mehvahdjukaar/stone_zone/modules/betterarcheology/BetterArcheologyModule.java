package net.mehvahdjukaar.stone_zone.modules.betterarcheology;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

public class BetterArcheologyModule extends SZModule {
    public final SimpleEntrySet<MudType, Block> cracked_bricks;

    public final SimpleEntrySet<MudType, Block> cracked_brick_stairs;

    public final SimpleEntrySet<MudType, Block> cracked_brick_slabs;

    public BetterArcheologyModule(String modId) {
        super(modId, "ba");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        cracked_bricks = StonezoneEntrySet.of(MudType.class, "bricks", "cracked",
                getModBlock("cracked_mud_bricks"), MudTypeRegistry::getBaseMudType,
                mudType -> new Block(Utils.copyPropertySafe(mudType.bricks())))
                .createPaletteFromBricks()
                .requiresChildren("bricks") // Reason recipes & palette
                .addTexture(modRes("block/cracked_mud_bricks"))
                .setTabKey(tab)
                //.addRecipe(modRes("cracked_mud_bricks")) TODO unsupported by moonlight
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();
        this.addEntry(cracked_bricks);

        cracked_brick_stairs = StonezoneEntrySet.of(MudType.class, "brick_stairs", "cracked",
                        getModBlock("cracked_mud_brick_stairs"), MudTypeRegistry::getBaseMudType,
                        mudType -> new StairBlock(mudType.bricks().defaultBlockState(), Utils.copyPropertySafe(mudType.bricks())))
                .requiresFromMap(cracked_bricks.blocks)
                // textures from above
                .setTabKey(tab)
                .addRecipe(modRes("cracked_mud_brick_stairs"))
                //.addRecipe(modRes("cracked_mud_brick_stairs_from_cracked_mud_bricks_stonecutting")) // Fabric has the recipe here
                //.addRecipe(new ResourceLocation("cracked_mud_brick_stairs_from_cracked_mud_bricks_stonecutting")) // Forge has it under minecraft
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();
        this.addEntry(cracked_brick_stairs);

        cracked_brick_slabs = StonezoneEntrySet.of(MudType.class, "brick_slab", "cracked",
                        getModBlock("cracked_mud_brick_slab"), MudTypeRegistry::getBaseMudType,
                        mudType -> new SlabBlock(Utils.copyPropertySafe(mudType.bricks())))
                .requiresFromMap(cracked_bricks.blocks)
                // textures from above
                .setTabKey(tab)
                .addRecipe(modRes("cracked_mud_brick_slab"))
                //.addRecipe(modRes("cracked_mud_brick_slab_from_cracked_mud_bricks_stonecutting")) // Fabric has the recipe here
                //.addRecipe(new ResourceLocation("cracked_mud_brick_slab_from_cracked_mud_bricks_stonecutting")) // Forge has it under minecraft
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();
        this.addEntry(cracked_brick_slabs);
    }
}
