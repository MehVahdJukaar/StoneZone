package net.mehvahdjukaar.stone_zone.modules.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.decoration.palettes.LayeredBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.common_classes.RecipeUtility;
import net.mehvahdjukaar.every_compat.common_classes.TagUtility;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.modules.SZModule;
import net.mehvahdjukaar.stone_zone.modules.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.type.StoneType;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;

import java.util.ArrayList;
import java.util.Objects;

import static net.mehvahdjukaar.stone_zone.modules.SZEntryBuilder.copyChildrenProperties;

//SUPPORT: v0.5.1+
public class CreateModule extends SZModule {

    public final SimpleEntrySet<StoneType, Block> cut_andesites;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_stairs;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_slabs;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_walls;

    public final SimpleEntrySet<StoneType, Block> cut_andesite_bricks;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_brick_stairs;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_brick_slabs;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_brick_walls;

    public final SimpleEntrySet<StoneType, Block> polished_cut_andesites;
    public final SimpleEntrySet<StoneType, Block> polished_cut_andesite_stairs;
    public final SimpleEntrySet<StoneType, Block> polished_cut_andesite_slabs;
    public final SimpleEntrySet<StoneType, Block> polished_cut_andesite_walls;

    public final SimpleEntrySet<StoneType, Block> small_andesite_bricks;
    public final SimpleEntrySet<StoneType, Block> small_andesite_brick_stairs;
    public final SimpleEntrySet<StoneType, Block> small_andesite_brick_slabs;
    public final SimpleEntrySet<StoneType, Block> small_andesite_brick_walls;
    public final SimpleEntrySet<StoneType, Block> pillars;
    public final SimpleEntrySet<StoneType, Block> layereds;

    public CreateModule(String modId) {
        super(modId, "c");
        ResourceLocation tab = modRes("palettes");

        cut_andesites = StonezoneEntrySet.of(StoneType.class, "", "cut",
                        getModBlock("cut_andesite"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/cut/andesite_cut"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .build();
        this.addEntry(cut_andesites);

        cut_andesite_stairs = StonezoneEntrySet.of(StoneType.class, "stairs", "cut",
                        getModBlock("cut_andesite_stairs"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new StairBlock(stoneType.stone.defaultBlockState(),
                                Utils.copyPropertySafe(stoneType.stone))
                )
                .addCondition(s -> Objects.nonNull(cut_andesites.blocks.get(s))) //REASON: recipes
                //TEXTURES: cut_andesite (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_stairs);

        cut_andesite_slabs = StonezoneEntrySet.of(StoneType.class, "slab", "cut",
                        getModBlock("cut_andesite_slab"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addCondition(s -> Objects.nonNull(cut_andesites.blocks.get(s))) //REASON: recipes
                //TEXTURES: cut_andesite (above)
                .addTexture(modRes("block/palettes/stone_types/slab/andesite_cut_slab"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_andesite_slab_recycling")) //Crafts cut_andesite
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_slabs);

        cut_andesite_walls = StonezoneEntrySet.of(StoneType.class, "wall", "cut",
                        getModBlock("cut_andesite_wall"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new WallBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addCondition(s -> Objects.nonNull(cut_andesites.blocks.get(s))) //REASON: recipes
                //TEXTURES: cut_andesite (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_walls);

        cut_andesite_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "cut",
                        getModBlock("cut_andesite_bricks"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/brick/andesite_cut_brick"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_bricks);

        cut_andesite_brick_stairs = StonezoneEntrySet.of(StoneType.class, "brick_stairs", "cut",
                        getModBlock("cut_andesite_brick_stairs"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new StairBlock(stoneType.stone.defaultBlockState(), copyChildrenProperties("brick_stairs", stoneType))
                )
                .addCondition(s -> Objects.nonNull(cut_andesite_bricks.blocks.get(s))) //REASON: recipes
                //TEXTURES: cut_andesite_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_brick_stairs);

        cut_andesite_brick_slabs = StonezoneEntrySet.of(StoneType.class, "brick_slab", "cut",
                        getModBlock("cut_andesite_brick_slab"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new SlabBlock(copyChildrenProperties("brick_slab", stoneType))
                )
                .addCondition(s -> Objects.nonNull(cut_andesite_bricks.blocks.get(s))) //REASON: recipes
                //TEXTURES: cut_andesite_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("cut_andesite_slab_recycling")) //Crafts cut_andesite_bricks
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_brick_slabs);

        cut_andesite_brick_walls = StonezoneEntrySet.of(StoneType.class, "brick_wall", "cut",
                        getModBlock("cut_andesite_brick_wall"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new WallBlock(copyChildrenProperties("brick_wall", stoneType))
                )
                .addCondition(s -> Objects.nonNull(cut_andesite_bricks.blocks.get(s))) //REASON: recipes
                //TEXTURES: cut_andesite_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(cut_andesite_brick_walls);

        polished_cut_andesites = StonezoneEntrySet.of(StoneType.class, "", "polished_cut",
                        getModBlock("polished_cut_andesite"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/polished/andesite_cut_polished"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(polished_cut_andesites);

        polished_cut_andesite_stairs = StonezoneEntrySet.of(StoneType.class, "stairs", "polished_cut",
                        getModBlock("polished_cut_andesite_stairs"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new StairBlock(stoneType.stone.defaultBlockState(),
                                copyChildrenProperties("polished_stairs", stoneType))
                )
                .addCondition(s -> Objects.nonNull(polished_cut_andesites.blocks.get(s))) //REASON: recipes
                //TEXTURES: polished_cut_andesites (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(polished_cut_andesite_stairs);

        polished_cut_andesite_slabs = StonezoneEntrySet.of(StoneType.class, "slab", "polished_cut",
                        getModBlock("polished_cut_andesite_slab"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new SlabBlock(copyChildrenProperties("polished_slab", stoneType))
                )
                .addCondition(s -> Objects.nonNull(polished_cut_andesites.blocks.get(s))) //REASON: recipes
                //TEXTURES: polished_cut_andesites, andesite_cut_slabs (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("polished_cut_andesite_slab_recycling")) //Crafts polished_cut_andesite
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(polished_cut_andesite_slabs);

        polished_cut_andesite_walls = StonezoneEntrySet.of(StoneType.class, "wall", "polished_cut",
                        getModBlock("polished_cut_andesite_wall"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new WallBlock(copyChildrenProperties("polished_wall", stoneType))
                )
                .addCondition(s -> Objects.nonNull(polished_cut_andesites.blocks.get(s))) //REASON: recipes
                //TEXTURES: polished_cut_andesites (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(polished_cut_andesite_walls);

        small_andesite_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "small",
                        getModBlock("small_andesite_bricks"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/small_brick/andesite_cut_small_brick"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(small_andesite_bricks);

        small_andesite_brick_stairs = StonezoneEntrySet.of(StoneType.class, "brick_stairs", "small",
                        getModBlock("small_andesite_brick_stairs"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new StairBlock(stoneType.stone.defaultBlockState(),
                                copyChildrenProperties("polished_stairs", stoneType))
                )
                .addCondition(s -> Objects.nonNull(small_andesite_bricks.blocks.get(s))) //REASON: recipes
                //TEXTURES: small_andesite_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(small_andesite_brick_stairs);

        small_andesite_brick_slabs = StonezoneEntrySet.of(StoneType.class, "brick_slab", "small",
                        getModBlock("small_andesite_brick_slab"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new SlabBlock(copyChildrenProperties("polished_slab", stoneType))
                )
                .addCondition(s -> Objects.nonNull(small_andesite_bricks.blocks.get(s))) //REASON: recipes
                //TEXTURES: small_andesite_bricks, andesite_cut_slabs (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("small_andesite_brick_slab_recycling")) //Crafts small_andesite_bricks
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(small_andesite_brick_slabs);

        small_andesite_brick_walls = StonezoneEntrySet.of(StoneType.class, "brick_wall", "small",
                        getModBlock("small_andesite_brick_wall"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new WallBlock(copyChildrenProperties("polished_wall", stoneType))
                )
                .addCondition(s -> Objects.nonNull(small_andesite_bricks.blocks.get(s))) //REASON: recipes
                //TEXTURES: small_andesite_bricks (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(small_andesite_brick_walls);

        pillars = StonezoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("andesite_pillar"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new ConnectedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/pillar/andesite_cut_pillar"))
                .addTexture(modRes("block/palettes/stone_types/pillar/andesite_cut_pillar_connected"))
                .addTexture(modRes("block/palettes/stone_types/cap/andesite_cut_cap")) // andesite_cut_cap
                .addTexture(modRes("block/palettes/stone_types/cap/andesite_cut_cap_connected")) // andesite_cut_cap_connected
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(pillars);

        layereds = StonezoneEntrySet.of(StoneType.class, "", "layered",
                        getModBlock("layered_andesite"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new LayeredBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: andesite_cut_cap, andesite_cut_cap_connected (above)
                .addTexture(modRes("block/palettes/stone_types/layered/andesite_cut_layered"))
                .addTexture(modRes("block/palettes/stone_types/layered/andesite_cut_layered_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(layereds);

    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        cut_andesites.blocks.forEach((stoneType, block) -> {

            // Adding all blocks of a StoneType to Array to be tagged
            var BlockMap = new ArrayList<Block>();

            for (var entry : this.getEntries()) {
                var currentBlock = ((SimpleEntrySet<?, ?>) entry).blocks.get(stoneType);
                BlockMap.add(currentBlock);
            }
            BlockMap.add(stoneType.stone);

            Block[] blocks = new Block[BlockMap.size()];
            BlockMap.toArray(blocks);

            // Tags
            ResourceLocation tagResLoc = StoneZone.res(stoneType.getNamespace() +"/"+ stoneType.getTypeName());
            boolean isTagCreated = TagUtility.createAndAddCustomTags(tagResLoc, handler, blocks);

            // Recipes
            if (isTagCreated) {
                for (var entry : this.getEntries()) {
                    var blockId = Utils.getID(((SimpleEntrySet<?, ?>)entry).getBaseBlock());
                    String prefix = blockId.getPath();
                    var output = ((SimpleEntrySet<?,?>) entry).blocks.get(stoneType);

                    ResourceLocation recipeFileLoc = modRes(prefix + "_from_stone_types_andesite_stonecutting");
                    ResourceLocation recipeResLoc = ResType.RECIPES.getPath(recipeFileLoc);
                    ResourceLocation newRecipeLoc = StoneZone.res(recipeFileLoc.getPath().replace("andesite", stoneType.getTypeName()));

                    RecipeUtility.stonecuttingWithTagRecipe(output, recipeResLoc, tagResLoc, newRecipeLoc, handler, manager);
                }

            }
        });
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.registerConnectedBlock(this);
    }

    @Environment(EnvType.CLIENT)
    private static class CreateClientModule {
        private static void registerConnectedBlock(CreateModule module) {
            module.pillars.blocks.forEach((stone, block) -> {
                String capPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/cap/" + stone.getTypeName() + "_cut_cap";
                String pillarPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/pillar/" + stone.getTypeName() + "_cut_pillar";
                String layeredPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/layered/" + stone.getTypeName() + "_cut_layered";

                CTSpriteShiftEntry capShift = CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL,
                        StoneZone.res(capPath), StoneZone.res(capPath + "_connected"));

                CTSpriteShiftEntry pillarShift = CTSpriteShifter.getCT(AllCTTypes.RECTANGLE,
                        StoneZone.res(pillarPath), StoneZone.res(pillarPath + "_connected"));
                CTSpriteShiftEntry layeredShift = CTSpriteShifter.getCT(AllCTTypes.HORIZONTAL_KRYPPERS,
                        StoneZone.res(layeredPath), StoneZone.res(layeredPath + "_connected"));

                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                        (model) -> new CTModel(model, new RotatedPillarCTBehaviour(pillarShift, capShift)));

                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(module.layereds.blocks.get(stone)),
                        (model) -> new CTModel(model, new HorizontalCTBehaviour(layeredShift, capShift)));

            });
        }
    }
}