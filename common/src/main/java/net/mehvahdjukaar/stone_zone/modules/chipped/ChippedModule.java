package net.mehvahdjukaar.stone_zone.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import earth.terrarium.chipped.common.blocks.DirectionBlock;
import earth.terrarium.chipped.common.blocks.SpecialPointedDripstoneBlock;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.createAndAddCustomTags;


//SUPPORT: v3.0.7+
public class ChippedModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> bricks;
    public final SimpleEntrySet<StoneType, Block> mini_tiles;
    public final SimpleEntrySet<StoneType, Block> pillars;
    public final SimpleEntrySet<StoneType, Block> pillar_tops;
    public final SimpleEntrySet<StoneType, Block> scales;
    public final SimpleEntrySet<StoneType, Block> angry;
    public final SimpleEntrySet<StoneType, Block> blank_carving;
    public final SimpleEntrySet<StoneType, Block> carved;
    public final SimpleEntrySet<StoneType, Block> checkered_tiles;
    public final SimpleEntrySet<StoneType, Block> cobbled;
    public final SimpleEntrySet<StoneType, Block> cracked_bricks;
    public final SimpleEntrySet<StoneType, Block> crackeddisordered__bricks;
    public final SimpleEntrySet<StoneType, Block> cracked_flat_tiles;
    public final SimpleEntrySet<StoneType, Block> creeper_carving;
    public final SimpleEntrySet<StoneType, Block> crying;
    public final SimpleEntrySet<StoneType, Block> cut_blank;
    public final SimpleEntrySet<StoneType, Block> glad;
    public final SimpleEntrySet<StoneType, Block> duh;
    public final SimpleEntrySet<StoneType, Block> engraved;
    public final SimpleEntrySet<StoneType, Block> eroded;
    public final SimpleEntrySet<StoneType, Block> etched_bricks;
    public final SimpleEntrySet<StoneType, Block> flat_tiles;
    public final SimpleEntrySet<StoneType, Block> inlayed;
    public final SimpleEntrySet<StoneType, Block> inscribed;
    public final SimpleEntrySet<StoneType, Block> layed_bricks;
    public final SimpleEntrySet<StoneType, Block> loded;
    public final SimpleEntrySet<StoneType, Block> offset_bricks;
    public final SimpleEntrySet<StoneType, Block> pillar_bricks;
    public final SimpleEntrySet<StoneType, Block> prismal_remnants;
    public final SimpleEntrySet<StoneType, Block> rough;
    public final SimpleEntrySet<StoneType, Block> rounded_bricks;
    public final SimpleEntrySet<StoneType, Block> runic_carved;
    public final SimpleEntrySet<StoneType, Block> sad;
    public final SimpleEntrySet<StoneType, Block> sanded;
    public final SimpleEntrySet<StoneType, Block> small_bricks;
    public final SimpleEntrySet<StoneType, Block> smooth_inlayed;
    public final SimpleEntrySet<StoneType, Block> smooth_ringed;
    public final SimpleEntrySet<StoneType, Block> smoothed_double_inlayed;
    public final SimpleEntrySet<StoneType, Block> spider_carving;
    public final SimpleEntrySet<StoneType, Block> pointed_dripstone;
    public final SimpleEntrySet<StoneType, Block> stacked_bricks;
    public final SimpleEntrySet<StoneType, Block> tiled;
    public final SimpleEntrySet<StoneType, Block> tiny_bricks;
    public final SimpleEntrySet<StoneType, Block> tiny_layered_bricks;
    public final SimpleEntrySet<StoneType, Block> tiny_layered_slabs;
    public final SimpleEntrySet<StoneType, Block> trodden;
    public final SimpleEntrySet<StoneType, Block> unamused;
    public final SimpleEntrySet<StoneType, Block> vertical_cut;
    public final SimpleEntrySet<StoneType, Block> vertical_disordered_bricks;
    public final SimpleEntrySet<StoneType, Block> weathered;
    public final SimpleEntrySet<StoneType, Block> bordered;
    public final SimpleEntrySet<StoneType, Block> brick_bordered;
    public final SimpleEntrySet<StoneType, Block> cut_column;
    public final SimpleEntrySet<StoneType, Block> edged_bricks;
    public final SimpleEntrySet<StoneType, Block> overlapping_tiles;
    public final SimpleEntrySet<StoneType, Block> polished;
    public final SimpleEntrySet<StoneType, Block> smooth_column;
    public final SimpleEntrySet<StoneType, Block> thick_inlayed;
    public final SimpleEntrySet<StoneType, Block> tiled_column;
    public final SimpleEntrySet<StoneType, Block> tiled_bordered;
    public final SimpleEntrySet<StoneType, Block> tiny_brick_bordered;
    public final SimpleEntrySet<StoneType, Block> curly_pillar;
    public final SimpleEntrySet<StoneType, Block> fine_pillar;
    public final SimpleEntrySet<StoneType, Block> ornate_pillar;
    public final SimpleEntrySet<StoneType, Block> simple_pillar;
    public final SimpleEntrySet<StoneType, Block> massive_bricks;
    public final SimpleEntrySet<StoneType, Block> spiraled;

    public ChippedModule(String modId) {
        super(modId, "ch");
        ResourceLocation tab = modRes("main");


        bricks = StoneZoneEntrySet.of(StoneType.class, "bricks",
                        getModBlock("andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bricks);

        mini_tiles = StoneZoneEntrySet.of(StoneType.class, "mini_tiles",
                        getModBlock("andesite_mini_tiles"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/andesite_mini_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(mini_tiles);

        pillars = StoneZoneEntrySet.of(StoneType.class, "pillar",
                        getModBlock("andesite_pillar"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/andesite_pillar"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(pillars);

        pillar_tops = StoneZoneEntrySet.of(StoneType.class, "pillar_top",
                        getModBlock("andesite_pillar_top"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/andesite_pillar_top"))
                .addTexture(modRes("block/andesite/ctm/common_textures/0")) //REQUIRED BY: Other Blocks, BlockType-pillar
                .addTexture(modRes("block/andesite/ctm/common_textures/1")) //REQUIRED BY: Other Blocks, BlockType-pillar
                .addTexture(modRes("block/andesite/ctm/common_textures/2")) //REQUIRED BY: Other Blocks, BlockType-pillar
                .addTexture(modRes("block/andesite/ctm/common_textures/3")) //REQUIRED BY: Other Blocks, BlockType-pillar
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(pillar_tops);

        scales = StoneZoneEntrySet.of(StoneType.class, "scales",
                        getModBlock("andesite_scales"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/andesite_scales"))
                .addTexture(modRes("block/andesite/polished_andesite")) //REQUIRED BY: Other blocks
                .addTexture(modRes("block/andesite/ctm/polished_andesite_ctm/1")) //REQUIRED BY: polished blocks
                .addTexture(modRes("block/andesite/ctm/polished_andesite_ctm/2")) //REQUIRED BY: polished blocks
                .addTexture(modRes("block/andesite/ctm/polished_andesite_ctm/3")) //REQUIRED BY: polished blocks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(scales);

        angry = StoneZoneEntrySet.of(StoneType.class, "", "angry",
                        getModBlock("angry_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/angry_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(angry);

        blank_carving = StoneZoneEntrySet.of(StoneType.class, "carving", "blank",
                        getModBlock("blank_andesite_carving"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/blank_andesite_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(blank_carving);

        carved = StoneZoneEntrySet.of(StoneType.class, "", "carved",
                        getModBlock("carved_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/carved_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(carved);

        checkered_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "checkered",
                        getModBlock("checkered_andesite_tiles"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/checkered_andesite_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(checkered_tiles);

        cobbled = StoneZoneEntrySet.of(StoneType.class, "", "cobbled",
                        getModBlock("cobbled_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/cobbled_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(cobbled);

        cracked_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "cracked",
                        getModBlock("cracked_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/cracked_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(cracked_bricks);

        crackeddisordered__bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "cracked_disordered",
                        getModBlock("cracked_disordered_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/cracked_disordered_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crackeddisordered__bricks);

        cracked_flat_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "cracked_flat",
                        getModBlock("cracked_flat_andesite_tiles"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/cracked_flat_andesite_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(cracked_flat_tiles);

        creeper_carving = StoneZoneEntrySet.of(StoneType.class, "carving", "creeper",
                        getModBlock("creeper_andesite_carving"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/creeper_andesite_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(creeper_carving);

        crying = StoneZoneEntrySet.of(StoneType.class, "", "crying",
                        getModBlock("crying_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/crying_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crying);

        cut_blank = StoneZoneEntrySet.of(StoneType.class, "", "cut_blank",
                        getModBlock("cut_blank_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/cut_blank_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(cut_blank);

        glad = StoneZoneEntrySet.of(StoneType.class, "", "glad",
                        getModBlock("glad_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/glad_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(glad);

        duh = StoneZoneEntrySet.of(StoneType.class, "", "duh",
                        getModBlock("duh_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/duh_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(duh);

        engraved = StoneZoneEntrySet.of(StoneType.class, "", "engraved",
                        getModBlock("engraved_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/engraved_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(engraved);

        eroded = StoneZoneEntrySet.of(StoneType.class, "", "eroded",
                        getModBlock("eroded_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/eroded_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(eroded);

        etched_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "etched",
                        getModBlock("etched_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/etched_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(etched_bricks);

        flat_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "flat",
                        getModBlock("flat_andesite_tiles"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/flat_andesite_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(flat_tiles);

        inlayed = StoneZoneEntrySet.of(StoneType.class, "", "inlayed",
                        getModBlock("inlayed_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/inlayed_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(inlayed);

        inscribed = StoneZoneEntrySet.of(StoneType.class, "", "inscribed",
                        getModBlock("inscribed_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/inscribed_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(inscribed);

        layed_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "layed",
                        getModBlock("layed_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/layed_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(layed_bricks);

        loded = StoneZoneEntrySet.of(StoneType.class, "", "loded",
                        getModBlock("loded_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/loded_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(loded);

        offset_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "offset",
                        getModBlock("offset_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/offset_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(offset_bricks);

        pillar_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "pillar",
                        getModBlock("pillar_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/pillar_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(pillar_bricks);

        prismal_remnants = StoneZoneEntrySet.of(StoneType.class, "remnants", "prismal",
                        getModBlock("prismal_andesite_remnants"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/prismal_andesite_remnants"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(prismal_remnants);

        rough = StoneZoneEntrySet.of(StoneType.class, "", "rough",
                        getModBlock("rough_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/rough_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rough);

        rounded_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "rounded",
                        getModBlock("rounded_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/rounded_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rounded_bricks);

        runic_carved = StoneZoneEntrySet.of(StoneType.class, "", "runic_carved",
                        getModBlock("runic_carved_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/runic_carved_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(runic_carved);

        sad = StoneZoneEntrySet.of(StoneType.class, "", "sad",
                        getModBlock("sad_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/sad_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(sad);

        sanded = StoneZoneEntrySet.of(StoneType.class, "", "sanded",
                        getModBlock("sanded_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/sanded_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(sanded);

        small_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "small",
                        getModBlock("small_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/small_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks);

        smooth_inlayed = StoneZoneEntrySet.of(StoneType.class, "", "smooth_inlayed",
                        getModBlock("smooth_inlayed_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/smooth_inlayed_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_inlayed);

        smooth_ringed = StoneZoneEntrySet.of(StoneType.class, "", "smooth_ringed",
                        getModBlock("smooth_ringed_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/smooth_ringed_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_ringed);

        smoothed_double_inlayed = StoneZoneEntrySet.of(StoneType.class, "", "smoothed_double_inlayed",
                        getModBlock("smoothed_double_inlayed_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/smoothed_double_inlayed_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smoothed_double_inlayed);

        spider_carving = StoneZoneEntrySet.of(StoneType.class, "carving", "spider",
                        getModBlock("spider_andesite_carving"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/spider_andesite_carving"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(spider_carving);

        pointed_dripstone = StoneZoneEntrySet.of(StoneType.class, "pointed_dripstone",
                        getModBlock("andesite_pointed_dripstone"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new SpecialPointedDripstoneBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_down_base"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_down_frustum"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_down_middle"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_down_tip"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_down_tip_merge"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_up_base"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_up_frustum"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_up_middle"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_up_tip"))
                .addTexture(modRes("block/pointed_dripstone/andesite_pointed_dripstone_up_tip_merge"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(pointed_dripstone);

        stacked_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "stacked",
                        getModBlock("stacked_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/stacked_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(stacked_bricks);

        tiled = StoneZoneEntrySet.of(StoneType.class, "", "tiled",
                        getModBlock("tiled_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/tiled_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiled);

        tiny_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "tiny",
                        getModBlock("tiny_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/tiny_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiny_bricks);

        tiny_layered_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "tiny_layered",
                        getModBlock("tiny_layered_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/tiny_layered_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiny_layered_bricks);

        tiny_layered_slabs = StoneZoneEntrySet.of(StoneType.class, "slabs", "tiny_layered",
                        getModBlock("tiny_layered_andesite_slabs"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/tiny_layered_andesite_slabs"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiny_layered_slabs);

        trodden = StoneZoneEntrySet.of(StoneType.class, "", "trodden",
                        getModBlock("trodden_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/trodden_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(trodden);

        unamused = StoneZoneEntrySet.of(StoneType.class, "", "unamused",
                        getModBlock("unamused_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/unamused_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(unamused);

        vertical_cut = StoneZoneEntrySet.of(StoneType.class, "", "vertical_cut",
                        getModBlock("vertical_cut_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/vertical_cut_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(vertical_cut);

        vertical_disordered_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "vertical_disordered",
                        getModBlock("vertical_disordered_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/vertical_disordered_andesite_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(vertical_disordered_bricks);

        weathered = StoneZoneEntrySet.of(StoneType.class, "", "weathered",
                        getModBlock("weathered_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/weathered_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(weathered);

        bordered = StoneZoneEntrySet.of(StoneType.class, "", "bordered",
                        getModBlock("bordered_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/bordered_andesite"))
                .addTexture(modRes("block/andesite/ctm/bordered_andesite_ctm/0"))
                .addTexture(modRes("block/andesite/ctm/bordered_andesite_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/bordered_andesite_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/bordered_andesite_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered);

        brick_bordered = StoneZoneEntrySet.of(StoneType.class, "", "brick_bordered",
                        getModBlock("brick_bordered_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
//                .addTexture(modRes("block/andesite/common_textures/0"))
                .addTexture(modRes("block/andesite/brick_bordered_andesite"))
                .addTexture(modRes("block/andesite/ctm/brick_bordered_andesite_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/brick_bordered_andesite_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/brick_bordered_andesite_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_bordered);

        cut_column = StoneZoneEntrySet.of(StoneType.class, "column", "cut",
                        getModBlock("cut_andesite_column"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/cut_andesite_column"))
                .addTexture(modRes("block/andesite/ctm/cut_andesite_column_ctm/0"))
                .addTexture(modRes("block/andesite/ctm/cut_andesite_column_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/cut_andesite_column_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/cut_andesite_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(cut_column);

        edged_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "edged",
                        getModBlock("edged_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/edged_andesite_bricks"))
                .addTexture(modRes("block/andesite/ctm/edged_andesite_bricks_ctm/0"))
                .addTexture(modRes("block/andesite/ctm/edged_andesite_bricks_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/edged_andesite_bricks_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/edged_andesite_bricks_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(edged_bricks);

        overlapping_tiles = StoneZoneEntrySet.of(StoneType.class, "tiles", "overlapping",
                        getModBlock("overlapping_andesite_tiles"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: common_textures/0 (generated via pillar_top)
                .addTexture(modRes("block/andesite/overlapping_andesite_tiles"))
                .addTexture(modRes("block/andesite/ctm/overlapping_andesite_tiles_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/overlapping_andesite_tiles_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/overlapping_andesite_tiles_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(overlapping_tiles);

        polished = StoneZoneEntrySet.of(StoneType.class, "", "polished",
                        getModBlock("polished_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: common_textures/0 (generated via pillar_top), polished (generated via scaled)
                //TEXTURES: polished_andesite_ctm/1, polished_andesite_ctm/2, polished_andesite_ctm/3 (generated via scaled)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(polished);

        smooth_column = StoneZoneEntrySet.of(StoneType.class, "column", "smooth",
                        getModBlock("smooth_andesite_column"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/smooth_andesite_column"))
                .addTexture(modRes("block/andesite/ctm/smooth_andesite_column_ctm/0"))
                .addTexture(modRes("block/andesite/ctm/smooth_andesite_column_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/smooth_andesite_column_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/smooth_andesite_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_column);

        thick_inlayed = StoneZoneEntrySet.of(StoneType.class, "", "thick_inlayed",
                        getModBlock("thick_inlayed_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: common_textures/1, common_textures/2 (generated via pillar_top)
                .addTexture(modRes("block/andesite/thick_inlayed_andesite"))
                .addTexture(modRes("block/andesite/ctm/thick_inlayed_andesite_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/thick_inlayed_andesite_ctm/2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(thick_inlayed);

        tiled_column = StoneZoneEntrySet.of(StoneType.class, "column", "tiled",
                        getModBlock("tiled_andesite_column"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/tiled_andesite_column"))
                .addTexture(modRes("block/andesite/ctm/tiled_andesite_column_ctm/0"))
                .addTexture(modRes("block/andesite/ctm/tiled_andesite_column_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/tiled_andesite_column_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/tiled_andesite_column_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiled_column);

        tiled_bordered = StoneZoneEntrySet.of(StoneType.class, "", "tiled_bordered",
                        getModBlock("tiled_bordered_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: common_textures/0 (generated via pillar_top)
                .addTexture(modRes("block/andesite/tiled_bordered_andesite"))
                .addTexture(modRes("block/andesite/ctm/tiled_bordered_andesite_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/tiled_bordered_andesite_ctm/2"))
                .addTexture(modRes("block/andesite/ctm/tiled_bordered_andesite_ctm/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiled_bordered);

        tiny_brick_bordered = StoneZoneEntrySet.of(StoneType.class, "", "tiny_brick_bordered",
                        getModBlock("tiny_brick_bordered_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                //TEXTURES: common_textures/0, common_textures/1, common_textures/2 (generated via pillar_top)
                .addTexture(modRes("block/andesite/tiny_brick_bordered_andesite"))
                .addTexture(modRes("block/andesite/ctm/tiny_brick_bordered_andesite_ctm/1"))
                .addTexture(modRes("block/andesite/ctm/tiny_brick_bordered_andesite_ctm/2"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiny_brick_bordered);

        curly_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "curly",
                        getModBlock("curly_andesite_pillar"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/curly_andesite_pillar"))
                .addTexture(modRes("block/andesite/ctm/curly_andesite_pillar/0"))
                .addTexture(modRes("block/andesite/ctm/curly_andesite_pillar/1"))
                .addTexture(modRes("block/andesite/ctm/curly_andesite_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(curly_pillar);

        fine_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "fine",
                        getModBlock("fine_andesite_pillar"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                //TEXTURES: polished (generated via scaled), common_textures/3 (generated via pillar_top)
                .addTexture(modRes("block/andesite/fine_andesite_pillar"))
                .addTexture(modRes("block/andesite/ctm/fine_andesite_pillar/0"))
                .addTexture(modRes("block/andesite/ctm/fine_andesite_pillar/1"))
                .addTexture(modRes("block/andesite/ctm/fine_andesite_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(fine_pillar);

        ornate_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "ornate",
                        getModBlock("ornate_andesite_pillar"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                //TEXTURES: polished (generated via scaled), common_textures/3 (generated via pillar_top)
                .addTexture(modRes("block/andesite/ornate_andesite_pillar"))
                .addTexture(modRes("block/andesite/ctm/ornate_andesite_pillar/0"))
                .addTexture(modRes("block/andesite/ctm/ornate_andesite_pillar/1"))
                .addTexture(modRes("block/andesite/ctm/ornate_andesite_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ornate_pillar);

        simple_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "simple",
                        getModBlock("simple_andesite_pillar"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new RotatedPillarBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                //TEXTURES: polished (generated via scaled), common_textures/2, common_textures/3 (generated via pillar_top)
                .addTexture(modRes("block/andesite/simple_andesite_pillar"))
                .addTexture(modRes("block/andesite/ctm/simple_andesite_pillar/0"))
                .addTexture(modRes("block/andesite/ctm/simple_andesite_pillar/1"))
                .addTexture(modRes("block/andesite/ctm/simple_andesite_pillar/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(simple_pillar);

        massive_bricks = StoneZoneEntrySet.of(StoneType.class, "bricks", "massive",
                        getModBlock("massive_andesite_bricks"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .addTexture(modRes("block/andesite/massive_andesite_bricks"))
                .addTexture(modRes("block/andesite/ctm/massive_andesite_bricks/0"))
                .addTexture(modRes("block/andesite/ctm/massive_andesite_bricks/1"))
                .addTexture(modRes("block/andesite/ctm/massive_andesite_bricks/2"))
                .addTexture(modRes("block/andesite/ctm/massive_andesite_bricks/3"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(massive_bricks);

        spiraled = StoneZoneEntrySet.of(StoneType.class, "", "spiraled",
                        getModBlock("spiraled_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new DirectionBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/andesite/spiraled_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(spiraled);

    }


    @Override
    // RECIPES & TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {

            addMasonTableRecipe(sink);

        });

    }

    private void addMasonTableRecipe(ResourceSink sink) {
        JsonArray listTag = new JsonArray();

        // Creating tag for every stonetype
        for (StoneType stoneType : StoneTypeRegistry.getTypes()) {
            boolean isTagCreated = false;

            if (stoneType.isVanilla()) continue;

            ResourceLocation tagResLoc = StoneZone.res(shortenedId() +"/"+ stoneType.getAppendableId());
            createAndAddCustomTags(tagResLoc, sink, stoneType.stone);

            for (EntrySet<?> entry : this.getEntries()) {

                SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);

                isTagCreated = createAndAddCustomTags(tagResLoc, sink, currentEntry.blocks.get(stoneType));

            }

            if (isTagCreated) listTag.add(tagResLoc.toString());
        }

        JsonObject recipeJO = new JsonObject();
        recipeJO.addProperty("type", "chipped:mason_table");
        recipeJO.add("tags", listTag);
        sink.addJson(StoneZone.res(shortenedId() + "/mason_table"), recipeJO, ResType.RECIPES);

    }

}