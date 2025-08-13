package net.mehvahdjukaar.stone_zone.modules.forge.blocks_you_need;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.common_classes.Utilities.copyChildrenPropertySafe;
import static net.mehvahdjukaar.stone_zone.StoneZone.MOD_ID;


//SUPPORT: v1.10+
public class BlocksYouNeedModule extends StoneZoneModule {

//    public final SimpleEntrySet<StoneType, Block> path;
    public final SimpleEntrySet<StoneType, Block> laid;
    public final SimpleEntrySet<StoneType, Block> laid_slab;
    public final SimpleEntrySet<StoneType, Block> laid_stairs;
    public final SimpleEntrySet<StoneType, Block> laid_wall;

    public BlocksYouNeedModule(String modId) {
        super(modId, "byn");
        ResourceLocation tab = modRes("blocks_you_need_ad_infinitum");

        //!! REASON: the recipe is using "laid_cobblestone" as an ingredient, no way to make it possible
//        path = StoneZoneEntrySet.of(StoneType.class, "path",
//                        getModBlock("stone_path"), StoneTypeRegistry::getStoneType,
//                        stoneType -> new StonePathBlock()
//                )
//                .requiresChildren(VanillaRockTypeChildKeys.COBBLESTONE) //REASON: recipes
//                .addTexture(modRes("block/stone_path"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .setTabKey(tab)
//                .addRecipe(modRes("rec_stone_path"))
//                .setRenderType(RenderLayer.CUTOUT_MIPPED)
//                .build();
//        this.addEntry(path);

        laid = StoneZoneEntrySet.of(StoneType.class, "", "laid",
                        getModBlock("laid_sandstone"), () -> VanillaStoneTypes.SANDSTONE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone)
                                .sound(stoneType.getSound())
                                .strength(0.8F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, stoneType) ->
                                s.replace("\"blocksyouneed_luna:block/sandcobblelayers\"",
                                        "\""+blockId.withPrefix("block/")+"\"")
                        ))
                .addTextureC(modRes("block/sandcobblelayers"), "block/laid_sandstone")
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("sc_laid_sandstone"))
                .build();
        this.addEntry(laid);

        laid_stairs = StoneZoneEntrySet.of(StoneType.class, "stairs", "laid",
                        getModBlock("laid_sandstone_stairs"), () -> VanillaStoneTypes.SANDSTONE,
                        stoneType -> new StairBlock(laid.blocks.get(stoneType).defaultBlockState(),
                                copyChildrenPropertySafe("stairs", stoneType)
                                        .strength(0.8F, 0.8F)
                                        .requiresCorrectToolForDrops()
                        )
                )
                .requiresFromMap(laid.blocks) //REASON: textures, recipes
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, stoneType) ->
                                s.replace("\"blocksyouneed_luna:block/sandcobblelayers\"",
                                        "\""+stoneType.createFullIdWith(MOD_ID, "block", shortenedId(), "laid", "" )+"\"")
                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("sc_laid_sandstone_stairs"))
                .build();
        this.addEntry(laid_stairs);

        laid_slab = StoneZoneEntrySet.of(StoneType.class, "slab", "laid",
                        getModBlock("laid_sandstone_slab"), () -> VanillaStoneTypes.SANDSTONE,
                        stoneType -> new SlabBlock(copyChildrenPropertySafe("slab", stoneType)
                                .strength(0.8F, 0.8F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .requiresFromMap(laid.blocks) //REASON: textures, recipes
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, stoneType) ->
                                s.replace("\"blocksyouneed_luna:block/sandcobblelayers\"",
                                        "\""+stoneType.createFullIdWith(MOD_ID, "block", shortenedId(), "laid", "" )+"\"")
                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("sc_laid_sandstone_slab"))
                .build();
        this.addEntry(laid_slab);

        laid_wall = StoneZoneEntrySet.of(StoneType.class, "wall", "laid",
                        getModBlock("laid_sandstone_wall"), () -> VanillaStoneTypes.SANDSTONE,
                        stoneType -> new WallBlock(copyWallSafe("laid_sandstone_wall", stoneType)
                                .strength(0.8F, 0.8F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .requiresFromMap(laid.blocks) //REASON: textures, recipes
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, stoneType) ->
                                s.replace("\"blocksyouneed_luna:block/sandcobblelayers\"",
                                        "\""+stoneType.createFullIdWith(MOD_ID, "block", shortenedId(), "laid", "" )+"\"")
                ))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.NEEDS_STONE_TOOL, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("sc_laid_sandstone_wall"))
                .build();
        this.addEntry(laid_wall);

    }

    public BlockBehaviour.Properties copyWallSafe(String baseType, StoneType stoneType) {
        Block wall = stoneType.getBlockOfThis("wall");

        return Utils.copyPropertySafe(
                (Objects.nonNull(wall)) ? Objects.requireNonNull(wall) : getModBlock(baseType).get()
        );
    }
}
