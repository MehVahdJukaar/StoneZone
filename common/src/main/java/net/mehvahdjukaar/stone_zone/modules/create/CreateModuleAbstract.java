package net.mehvahdjukaar.stone_zone.modules.create;

import com.simibubi.create.content.decoration.MetalScaffoldingBlockItem;
import com.simibubi.create.content.decoration.encasing.EncasableBlock;
import com.simibubi.create.content.decoration.encasing.EncasedBlock;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.decoration.palettes.LayeredBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorRenderer;
import com.simibubi.create.content.logistics.funnel.FunnelItem;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockEntity;
import com.simibubi.create.content.logistics.tableCloth.TableClothRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.data.Couple;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.UtilityRecipe;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.simibubi.create.AllPartialModels.FOLDING_DOORS;
import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.makePaletteFromChild;
import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.getChildrenBlockSafe;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;
import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.isKnownVanillaStone;

///SUPPORT: v6.0.10+
public abstract class CreateModuleAbstract extends StoneZoneModule {

    public final ItemOnlyEntrySet<StoneType, Item> alloy;

    public final SimpleEntrySet<StoneType, Block> casing, encased_shaft, encased_cogwheel, encased_large_cogwheel,
            funnel, belt_funnel;

    /// Required BeltBlockEntity to have a list of BlockType instead of ENUM that contains NONE, ANDESITE, BRASS
    /// This applied to CASING where it can be applied to belt_block, too.
//    public final SimpleEntrySet<MetalType, Block> tunnel/*, belt_funnel*/;
//    public final static ArrayList<Block> tunnelList = new ArrayList<>();

    public final SimpleEntrySet<StoneType, Block>
            pillars,
            layereds,

            // Required alloy
            door,
            ladder,
            bar,
            scaffolding,
            table_cloth;


    public CreateModuleAbstract(String modId) {
        super(modId, "c");
        Supplier<CreativeModeTab> tab = getModTab("base");
        Supplier<CreativeModeTab> palettesTab = getModTab("palettes");

        /// CUT
        var cutSet = BlockSetEntry.of(StoneType.class,this, VanillaStoneTypes.ANDESITE,
                        "cut_andesite", stoneType -> stoneType.stone,
                        "cut_andesite_slab", stoneType -> stoneType.stone,
                        "cut_andesite_stairs", stoneType -> stoneType.stone,
                        "cut_andesite_wall", stoneType -> stoneType.stone,
                        palettesTab
                )
                .block(builder -> builder
                                .addTexture(modRes("block/palettes/stone_types/cut/andesite_cut"))
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                )
                .slab(builder -> builder
                        //TEXTURES: cuts (above), polished_cut_slabs' andesite_cut_slab (below)
                                .addTexture(modRes("block/palettes/stone_types/slab/andesite_cut_slab"))
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                                .addRecipe(modRes("cut_andesite_slab_recycling")) //Crafts cut_andesite
                )
                .stairs(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .wall(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .build();
        this.addEntries(cutSet);

        /// CUT_BRICKS
        var cutBricksSet = BlockSetEntry.of(StoneType.class,this, VanillaStoneTypes.ANDESITE,
                        "cut_andesite_bricks", StoneType::bricksOrStone,
                        "cut_andesite_brick_slab", stoneType -> getChildrenBlockSafe(BRICK_SLAB, stoneType),
                        "cut_andesite_brick_stairs", stoneType -> getChildrenBlockSafe(BRICK_STAIRS, stoneType),
                        "cut_andesite_brick_wall", stoneType -> getChildrenBlockSafe(BRICK_WALL, stoneType),
                        palettesTab
                )
                .block(builder -> builder
                                .addTexture(modRes("block/palettes/stone_types/brick/andesite_cut_brick"))
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                )
                .slab(builder -> builder
                                .addTexture(modRes("block/palettes/stone_types/slab/andesite_cut_slab"))
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                                .addRecipe(modRes("cut_andesite_brick_slab_recycling")) //Crafts cut_andesite_bricks
                )
                .stairs(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .wall(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .build();
        this.addEntries(cutBricksSet);

        /// POLISHED_CUT
        var polishedCutSet = BlockSetEntry.of(StoneType.class,this, VanillaStoneTypes.ANDESITE,
                        "polished_cut_andesite", stoneType -> getChildrenBlockSafe(POLISHED, stoneType),
                        "polished_cut_andesite_slab", stoneType -> getChildrenBlockSafe(POLISHED_SLAB, stoneType),
                        "polished_cut_andesite_stairs", stoneType -> getChildrenBlockSafe(POLISHED_STAIRS, stoneType),
                        "polished_cut_andesite_wall", stoneType -> getChildrenBlockSafe(POLISHED_WALL, stoneType),
                        palettesTab
                )
                .block(builder -> builder
                                .addTexture(modRes("block/palettes/stone_types/polished/andesite_cut_polished"))
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                )
                .slab(builder -> builder
                        //REASON: below belong to cut_slabs but if it won't be generated, then the texture won't be, too
                                .addTexture(modRes("block/palettes/stone_types/slab/andesite_cut_slab")) // Used by CUT_SLAB
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                                .addRecipe(modRes("polished_cut_andesite_slab_recycling")) //Crafts cut_andesite_bricks
                )
                .stairs(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .wall(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .build();
        this.addEntries(polishedCutSet);

        /// SMALL_BRICKS
        var smallBricksSet = BlockSetEntry.of(StoneType.class,this, VanillaStoneTypes.ANDESITE,
                        "small_andesite_bricks", stoneType -> getChildrenBlockSafe(BRICKS, stoneType),
                        "small_andesite_brick_slab", stoneType -> getChildrenBlockSafe(BRICK_SLAB, stoneType),
                        "small_andesite_brick_stairs", stoneType -> getChildrenBlockSafe(BRICK_STAIRS, stoneType),
                        "small_andesite_brick_wall", stoneType -> getChildrenBlockSafe(BRICK_WALL, stoneType),
                        palettesTab
                )
                .block(builder -> builder
                                .addTexture(modRes("block/palettes/stone_types/small_brick/andesite_cut_small_brick"))
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                )
                .slab(builder -> builder
                        //TEXTURES: cut_slab (above)
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                                .addRecipe(modRes("small_andesite_brick_slab_recycling")) //Crafts small_andesite_bricks
                )
                .stairs(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .wall(builder -> builder
                                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                        //TAGS: create:stone_types/andesite - manually created
                                .defaultRecipe()
                )
                .build();
        this.addEntries(smallBricksSet);

// ─────────────────────────────────────────────────────────────────────────────

        alloy = ItemOnlyEntrySet.builder(StoneType.class, "alloy",
                        getModItem("andesite_alloy"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new Item(new Item.Properties())
                )
                .addTexture(modRes("item/andesite_alloy"), ALLOY_STRATEGY)
                //TAG: forge:plates/<type>
                .setTab(tab)
                .addRecipe(modRes("crafting/materials/andesite_alloy"))
                .addRecipe(modRes("crafting/materials/andesite_alloy_from_zinc"))
                //RECIPES: create:mixing/andesite_alloy - manually created
                //RECIPES: create:mixing/andesite_alloy_from_zinc - manually created
                .build();
        this.addEntry(alloy);

        casing = StoneZoneEntrySet.of(StoneType.class, "casing",
                        getModBlock("andesite_casing"), () -> VanillaStoneTypes.ANDESITE,
                        this::newCasingBlock
                )
//                .requiresFromMap(alloy.blocks) //REASON: recipes
                .addTextureM(modRes("block/andesite_casing"), StoneZone.res("block/c/andesite_casing_m"))
                .addTexture(modRes("block/andesite_casing_short"))
                .addTextureM(modRes("block/andesite_casing_connected"), StoneZone.res("block/c/andesite_casing_connected_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("casing"), Registries.BLOCK, Registries.ITEM)
                .setTab(tab)
                //RECIPES: item_application/andesite_casing_from_log - manually created
                //RECIPES: item_application/andesite_casing_from_wood - manually created
                .build();
        this.addEntry(casing);

        encased_shaft = StoneZoneEntrySet.of(StoneType.class, "encased_shaft",
                        getModBlock("andesite_encased_shaft"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> newEncasedShaftedBlock(stoneType, () -> casing.blocks.get(stoneType))
                )
                .requiresFromMap(casing.blocks) //REASON: It's one of the main block's blockstate
                .addTile(getModTile("encased_shaft"))
                .addModelTransform(m -> m.addModifier((s, ignored, stoneType) ->
                                s.replace(
                                        "create:block/gearbox",
                                        stoneType.createFullIdWith(StoneZone.MOD_ID, "block", shortenedId(), "", "gearbox")
                                )
                        )
                )
                //TEXTURES: brass_casing
                .addTextureMC(modRes("block/gearbox"), StoneZone.res("block/c/andesite_innerby2pixel_m"),
                        PaletteStrategies.MAIN_CHILD, "block/andesite_gearbox")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .noTab() //REASON: doesn't require item/tab
                .build();
        this.addEntry(encased_shaft);

        encased_cogwheel = StoneZoneEntrySet.of(StoneType.class, "encased_cogwheel",
                        getModBlock("andesite_encased_cogwheel"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> newEncasedCogwheelBlock(stoneType, () -> casing.blocks.get(stoneType))
                )
                .requiresFromMap(casing.blocks) //REASON: It's one of the main block's blockstate
                .addTile(getModTile("encased_cogwheel"))
                .addModelTransform(m -> m.addModifier((s, ignored, stoneType) ->
                                s.replace(
                                        "create:block/gearbox",
                                        stoneType.createFullIdWith(StoneZone.MOD_ID, "block", shortenedId(), "", "gearbox")
                                )
                        )
                )
                //TEXTURES: brass_casing, brass_gearbox
                .addTextureM(modRes("block/andesite_encased_cogwheel_side"), StoneZone.res("block/c/andesite_innerby2pixel_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop()
                .noTab() //REASON: doesn't require item/tab
                .build();
        this.addEntry(encased_cogwheel);

        encased_large_cogwheel = StoneZoneEntrySet.of(StoneType.class, "encased_large_cogwheel",
                        getModBlock("andesite_encased_large_cogwheel"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> newEncasedLargeCogwheelBlock(stoneType, () -> casing.blocks.get(stoneType))
                )
                .requiresFromMap(casing.blocks) //REASON: It's one of the main block's blockstate
                .addTile(getModTile("encased_large_cogwheel"))
                .addModelTransform(m -> m.addModifier((s, ignored, stoneType) ->
                                s.replace(
                                        "create:block/gearbox",
                                        stoneType.createFullIdWith(StoneZone.MOD_ID, "block", shortenedId(), "", "gearbox")
                                )
                        )
                )
                //TEXTURES: brass_casing, brass_gearbox
                .addTextureM(modRes("block/andesite_encased_cogwheel_side_connected"), StoneZone.res("block/c/andesite_encased_cogwheel_side_connected_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop()
                .noTab() //REASON: doesn't require item/tab
                .build();
        this.addEntry(encased_large_cogwheel);

        funnel = StoneZoneEntrySet.of(StoneType.class, "funnel",
                        getModBlock("andesite_funnel"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> newFunnelBlock(stoneType, beltFunnelSupplier(stoneType))
                )
//                .requiresFromMap(alloy.blocks) //REASON: recipes
                .addTile(getModTile("funnel"))
                .addModelTransform(m ->
                        m.replaceWithTextureFromChild("create:block/andesite_block", BLOCK)
                )
                //TEXTURES: andesite_block
                .addTextureM(modRes("block/funnel/andesite_funnel"), StoneZone.res("block/c/funnel/andesite_funnel_m"))
                .addTexture(modRes("block/funnel/andesite_funnel_push"))
                .addTexture(modRes("block/funnel/andesite_funnel_neutral"))
                .addTexture(modRes("block/funnel/andesite_funnel_pull"))
                .addTexture(modRes("block/funnel/andesite_funnel_frame"))
                .addTextureM(modRes("block/funnel/andesite_funnel_unpowered"), StoneZone.res("block/c/funnel/andesite_funnel_xpowered_m"))
                .addTextureM(modRes("block/funnel/andesite_funnel_powered"), StoneZone.res("block/c/funnel/andesite_funnel_xpowered_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("safe_nbt"), Registries.BLOCK)
                .addTag(modRes("contraption_controlled"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(modRes("crafting/logistics/andesite_funnel"))
                .addCustomItem((ignored, block, properties) -> new FunnelItem(block, properties))
                .build();
        this.addEntry(funnel);

        belt_funnel = StoneZoneEntrySet.of(StoneType.class, "belt_funnel",
                        getModBlock("andesite_belt_funnel"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> newBeltFunnelBlock(stoneType, funnel.blocks.get(stoneType))
                )
                .requiresFromMap(funnel.blocks) //REASON: textures & It's one of the main block's blockstate
                .addTile(getModTile("funnel"))
                .addModelTransform(m ->
                        m.replaceWithTextureFromChild("create:block/andesite_block", BLOCK)
                )
                //TEXTURES: andesite_block, funnel_push, funnel_powered, funnel_unpowered, funnel, funnel_neutral, funnel_frame, funnel_pull
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("safe_nbt"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop()
                .noTab().noItem()
                .build();
        this.addEntry(belt_funnel);

        /// Required BeltBlockEntity to have a list of BlockType instead of ENUM that contains NONE, ANDESITE, BRASS
        /// This applied to CASING where it can be applied to belt_block, too.
//        tunnel = GemsRealmEntrySet.of(MetalType.class, "tunnel",
//                        getModBlock("brass_tunnel"), () -> MetalTypeRegistry.getMetalType("create:brass"),
//                        metalType -> {
//                            Block block = newTunnelBlock(metalType);
//                            tunnelList.add(block);
//                            return block;
//                        }
//                )
//                .requiresFromMap(funnel.blocks) //REASON: textures
//                .addTile(getModTile("brass_tunnel"))
//                .addModelTransform(m ->
//                        m.replaceWithTextureFromChild("create:block/brass_block", BLOCK)
//                )
//                //TEXTURES: brass_block, funnel_neutral, funnel_frame
//                .addTextureM(modRes("block/tunnel/brass_tunnel"), GemsRealm.res("block/c/tunnel/brass_tunnel_m"))
//                .addTexture(modRes("block/tunnel/brass_tunnel_top"))
//                .addTexture(modRes("block/tunnel/brass_tunnel_top_connected"))
//                .addTextureM(modRes("block/tunnel/brass_tunnel_top_window"), GemsRealm.res("block/c/tunnel/brass_tunnel_top_window_m"))
//                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
//                .setTab(tab)
//                .setRenderType(RenderLayer.CUTOUT_MIPPED)
//                .addRecipe(modRes("crafting/logistics/andesite_tunnel"))
//                .build();
//        this.addEntry(tunnel);

// ─────────────────────────────────────────────────────────────────────────────

        pillars = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("andesite_pillar"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new ConnectedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/cap/andesite_cut_cap")) // andesite_cut_cap
                .addTexture(modRes("block/palettes/stone_types/cap/andesite_cut_cap_connected")) // andesite_cut_cap_connected
                .addTexture(modRes("block/palettes/stone_types/pillar/andesite_cut_pillar"))
                .addTexture(modRes("block/palettes/stone_types/pillar/andesite_cut_pillar_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: andesite_pillar_from_stone_types_andesite_stonecutting - manually created
                .build();
        this.addEntry(pillars);

        layereds = StoneZoneEntrySet.of(StoneType.class, "", "layered",
                        getModBlock("layered_andesite"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new LayeredBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: pillars' andesite_cut_cap & andesite_cut_cap_connected (above)
                .addTexture(modRes("block/palettes/stone_types/layered/andesite_cut_layered"))
                .addTexture(modRes("block/palettes/stone_types/layered/andesite_cut_layered_connected"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                //TAGS: create:stone_types/andesite - manually created
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //RECIPES: layered_andesite_from_stone_types_andesite_stonecutting - manually created
                .build();
        this.addEntry(layereds);

        door = StoneZoneEntrySet.of(StoneType.class, "door",
                        getModBlock("andesite_door"), () -> VanillaStoneTypes.ANDESITE,
                        this::makeSlidingDoorBlock
                )
                .requiresFromMap(casing.blocks) //REASON: recipes, textures
                .addTile(getModTile("sliding_door"))
                .includeModelsBlock(true, modRes("block/andesite_door/fold_left"), modRes("block/andesite_door/fold_right"))
                .addTextureM(modRes("block/andesite_door_bottom"), StoneZone.res("block/c/andesite_door_bottom_m"))
                .addTexture(modRes("block/andesite_door_side"))
                .addTextureM(modRes("block/andesite_door_top"), StoneZone.res("block/c/andesite_door_top_m"))
                .addTextureM(modRes("item/andesite_door"), StoneZone.res("item/c/andesite_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.DOORS, Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(BlockTags.MOB_INTERACTABLE_DOORS, Registries.BLOCK)
                .addTag(ResourceLocation.parse("quark:non_double_door"), Registries.BLOCK)
                .addTag(modRes("brittle"), Registries.BLOCK)
                .addTag(modRes("contraption_controlled"), Registries.ITEM)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(modRes("crafting/kinetics/andesite_door"))
                .copyParentDrop()
                .build();
        this.addEntry(door);

        ladder = StoneZoneEntrySet.of(StoneType.class, "ladder",
                        getModBlock("andesite_ladder"), () -> VanillaStoneTypes.ANDESITE,
                        this::makeMetalLadderBlock
                )
//                .requiresFromMap(alloy.blocks) //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/ladder_andesite"))
                .addTexture(modRes("block/ladder_andesite_hoop"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("copycat_deny"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(modRes("andesite_ladder_from_andesite_alloy_stonecutting"))
                .build();
        this.addEntry(ladder);

        bar = StoneZoneEntrySet.of(StoneType.class, "bars",
                        getModBlock("andesite_bars"), () -> VanillaStoneTypes.ANDESITE,
                        this::makeMetalBarsBlock
                )
//                .requiresFromMap(alloy.blocks) //REASON: recipes
                .addTexture(modRes("block/bars/andesite_bars"))
                .addTexture(modRes("block/bars/andesite_bars_edge"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("wrench_pickup"), Registries.BLOCK)
                .addTag(modRes("fan_transparent"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(modRes("andesite_bars_from_andesite_alloy_stonecutting"))
                .addCustomItem((stoneType, block, properties) -> new MetalScaffoldingBlockItem(block, properties))
                .build();
        this.addEntry(bar);

        scaffolding = StoneZoneEntrySet.of(StoneType.class, "scaffolding",
                        getModBlock("andesite_scaffolding"), () -> VanillaStoneTypes.ANDESITE,
                        this::makeMetalScaffoldingBlock
                )
//                .requiresFromMap(alloy.blocks) //REASON: recipes
                .addTexture(modRes("block/scaffold/andesite_scaffold"))
                .addTexture(modRes("block/scaffold/andesite_scaffold_connected"))
                .addTexture(modRes("block/scaffold/andesite_scaffold_inside"))
                .addTexture(modRes("block/scaffold/andesite_scaffold_inside_connected"))
                .addTexture(modRes("block/funnel/andesite_funnel_frame"))
                //TEXTURES: casing,
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("copycat_deny"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(modRes("andesite_scaffolding_from_andesite_alloy_stonecutting"))
                .addCustomItem((stoneType, block, properties) -> new MetalScaffoldingBlockItem(block, properties))
                .build();
        this.addEntry(scaffolding);

        table_cloth = StoneZoneEntrySet.of(StoneType.class, "table_cloth",
                        getModBlock("andesite_table_cloth"), () -> VanillaStoneTypes.ANDESITE,
                        this::newTableClothBlock
                )
//                .requiresFromMap(alloy.blocks) //REASON: recipes
                .addTile(getModTile("table_cloth"))
                .addTextureM(modRes("block/table_cloth/andesite"), StoneZone.res("block/c/andesite_table_cloth_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.INSIDE_STEP_SOUND_BLOCKS, Registries.BLOCK)
                .addTag(modRes("table_cloths"), Registries.BLOCK, Registries.ITEM)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addRecipe(modRes("andesite_table_cloth_from_andesite_alloy_stonecutting"))
                .addCustomItem((stoneType, block, properties) -> newTableClothBlockItem(block, properties))
                .build();
        this.addEntry(table_cloth);

    }

    protected abstract Block newCasingBlock(StoneType stoneType);
    protected abstract Block newEncasedShaftedBlock(StoneType stoneType, Supplier<Block> casingBlock);
    protected abstract Block newEncasedCogwheelBlock(StoneType stoneType, Supplier<Block> casingBlock);
    protected abstract Block newEncasedLargeCogwheelBlock(StoneType stoneType, Supplier<Block> casingBlock);
    protected abstract Block newFunnelBlock(StoneType stoneType, Supplier<Block> beltFunnelBlock);
    protected abstract Block newBeltFunnelBlock(StoneType stoneType, Block funnelBlock);
//    protected abstract Block newTunnelBlock(StoneType stoneType);

    protected abstract Block makeSlidingDoorBlock(StoneType stoneType);
    protected abstract Block makeMetalLadderBlock(StoneType stoneType);
    protected abstract Block makeMetalBarsBlock(StoneType stoneType);
    protected abstract Block makeMetalScaffoldingBlock(StoneType stoneType);
    protected abstract Block newTableClothBlock(StoneType stoneType);
    protected abstract Item newTableClothBlockItem(Block block, Item.Properties properties);

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);

        event.register(table_cloth.getTile(TableClothBlockEntity.class), TableClothRenderer::new);
        event.register(door.getTile(SlidingDoorBlockEntity.class), SlidingDoorRenderer::new);
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                          onMod                           │
    //      └──────────────────────────────────────────────────────────┘
    @Override
    public void onModSetup() {
        super.onModSetup();
        putFoldingDoor(this);
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                    Dynamic Resources                     │
    //      └──────────────────────────────────────────────────────────┘
    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->
            alloy.items.forEach((stoneType, item) -> {
                createMixingRecipe(stoneType, item, sink);
                createItemApplicationRecipe(stoneType, item, casing.blocks.get(stoneType), sink);
            }
        ));

        executor.accept((manager, sink) -> StoneTypeRegistry.INSTANCE.forEach(stoneType -> {

            if (!isKnownVanillaStone(stoneType) && !stoneType.getNamespace().equals("create")) {

                // Adding all blocks of a StoneType except "slab" to Array to be tagged
                ArrayList<Block> BlockMap = new ArrayList<>();

                for (EntrySet<?> entry : this.getEntries()) {
                    Block currentBlock = stoneType.getBlockOfThis(this.modId + ":" + entry.getName());
                    if (Objects.nonNull(currentBlock) && !currentBlock.toString().contains("slab")) {
                        BlockMap.add(currentBlock);
                    }
                }
                BlockMap.add(stoneType.stone);

                Block[] blocks = new Block[BlockMap.size()];
                BlockMap.toArray(blocks);

                // Tags
                ResourceLocation tagResLoc = StoneZone.res(stoneType.getNamespace() + "/" + stoneType.getTypeName());
                boolean isTagCreated = UtilityTag.createAndAddCustomTags(tagResLoc, sink, blocks);

                // Recipes - ???_andesite_from_stone_types_andesite_stonecutting
                if (isTagCreated) {
                    for (EntrySet<?> entry : this.getEntries()) {

                        if (entry.getName().contains("cut") || entry.getName().contains("small")) {
                            ResourceLocation blockId = Utils.getID(((SimpleEntrySet<?, ?>) entry).getBaseBlock());
                            String prefix = blockId.getPath();
                            Block output = stoneType.getBlockOfThis(this.modId + ":" + entry.getName());

                            ResourceLocation recipeFileLoc = modRes(prefix + "_from_stone_types_andesite_stonecutting");
                            ResourceLocation recipeResLoc = ResType.RECIPES.getPath(recipeFileLoc);
                            ResourceLocation newRecipeLoc = StoneZone.res(recipeFileLoc.getPath()
                                            .replace("andesite", stoneType.getTypeName())
                                            .replace("_stonecutting", "")
                                            .replace("_stone_types", ""))
                                    .withPrefix(shortenedId() + "/" + stoneType.getNamespace() + "/stonecutting/");

                            UtilityRecipe.stonecuttingWithTagRecipe(output, recipeResLoc, tagResLoc, newRecipeLoc, sink, manager);
                        }
                    }
                }
            }
        }));
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                     Palette Strategy                     │
    //      └──────────────────────────────────────────────────────────┘

    public static final PaletteStrategy ALLOY_STRATEGY = registerCached((blockType, manager) ->
            makePaletteFromChild(blockType, manager, BLOCK, null, p -> {
                p.forEach(paletteColor -> {
                    HCLColor oldHCL = paletteColor.hcl();
                    HCLColor newHCL = paletteColor.hcl().withLuminance(oldHCL.luminance() * 1.125F);
                    paletteColor.setOccurrence(new PaletteColor(newHCL).getOccurrence());
                });
            }));


    //      ┌──────────────────────────────────────────────────────────┐
    //      │                        UTilities                         │
    //      └──────────────────────────────────────────────────────────┘

    protected void putFoldingDoor(CreateModuleAbstract module) {
        module.door.blocks.forEach((stoneType, block) -> {
            String path = stoneType.createPathWith(module.shortenedId(), "door");
            FOLDING_DOORS.put(StoneZone.res(path),
                    Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
        });
    }

    private static PartialModel block(String path) {
        return PartialModel.of(StoneZone.res("block/"+ path));
    }

    /// Add encased blocks (create:encased_shaft) to ENCASED_VARIANTS so the create:casing can be applied to shaft or others
    protected <B extends Block & EncasableBlock> void registerEncasedBlock(B encaseable, SimpleEntrySet<StoneType, Block> entrySet) {
        entrySet.blocks.values().forEach(block -> EncasingRegistry.addVariant(encaseable, (Block & EncasedBlock) block));
    }

    protected void createMixingRecipe(StoneType stoneType, Item alloy, ResourceSink sink) {
        String original = """
        {
          "type": "create:mixing",
          "ingredients": [
            {
              "item": "[BLOCK]"
            },
            {
              "tag": "c:nuggets/[NUGGET]"
            }
          ],
          "results": [
            {
              "id": "[ALLOY]"
            }
          ]
        }
        """;

        String newRecipeWithIron = original
                .replace("[BLOCK]", Utils.getID(stoneType.block).toString())
                .replace("[NUGGET]", "iron")
                .replace("[ALLOY]", Utils.getID(alloy).toString());

        String newRecipeWithZinc = original
                .replace("[BLOCK]", Utils.getID(stoneType.block).toString())
                .replace("[NUGGET]", "iron")
                .replace("[ALLOY]", Utils.getID(alloy).toString());

        ResourceLocation newLoc = ResourceLocation.parse(stoneType.createFullIdWith(StoneZone.MOD_ID, "", shortenedId(), "mixing/", "alloy"));
        ResourceLocation newLocWithZinc = ResourceLocation.parse(stoneType.createFullIdWith(StoneZone.MOD_ID, "", shortenedId(), "mixing/", "alloy_from_zinc"));

        sink.addBytes(newLoc, newRecipeWithIron.getBytes(), ResType.RECIPES);
        sink.addBytes(newLocWithZinc, newRecipeWithZinc.getBytes(), ResType.RECIPES);
    }

    protected void createItemApplicationRecipe(StoneType stoneType, Item alloyItem, Block casingBlock, ResourceSink sink) {
        String original = """
        {
          "type": "create:item_application",
          "ingredients": [
            {
              "tag": "c:stripped_[TYPE]s"
            },
            {
              "item": "[ALLOY]"
            }
          ],
          "results": [
            {
              "id": "[CASING]"
            }
          ]
        }
        """;

        String newRecipeWithIron = original
                .replace("[TYPE]", "log")
                .replace("[ALLOY]", Utils.getID(alloyItem).toString())
                .replace("[CASING]", Utils.getID(casingBlock).toString());

        String newRecipeWithZinc = original
                .replace("[TYPE]", "wood")
                .replace("[ALLOY]", Utils.getID(alloyItem).toString())
                .replace("[CASING]", Utils.getID(casingBlock).toString());

        ResourceLocation newLoc = ResourceLocation.parse(stoneType.createFullIdWith(StoneZone.MOD_ID, "", shortenedId(), "item_application/", "casing_from_log"));
        ResourceLocation newLocWithWood = ResourceLocation.parse(stoneType.createFullIdWith(StoneZone.MOD_ID, "", shortenedId(), "item_application/", "casing_from_wood"));

        sink.addBytes(newLoc, newRecipeWithIron.getBytes(), ResType.RECIPES);
        sink.addBytes(newLocWithWood, newRecipeWithZinc.getBytes(), ResType.RECIPES);
    }

    // ─────────────────────────────────── Supplier ────────────────────────────────────
    protected Supplier<Block> beltFunnelSupplier(StoneType stoneType) {
        return () -> belt_funnel.blocks.get(stoneType);
    }

}
