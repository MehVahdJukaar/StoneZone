package net.mehvahdjukaar.stone_zone.modules.forge.macaws;

import com.mcwwindows.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static net.mehvahdjukaar.stone_zone.misc.ModelUtils.removeTintIndexFromParentModel;


//SUPPORT: v2.3.0+
public class MacawWindowsModule extends SZModule {

    public final SimpleEntrySet<StoneType, Block> windows;
    public final SimpleEntrySet<StoneType, Block> window2s;
    public final SimpleEntrySet<StoneType, Block> four_windows;
    public final SimpleEntrySet<StoneType, Block> brick_gothics;
    public final SimpleEntrySet<StoneType, Block> brick_arrow_slits;
    public final SimpleEntrySet<StoneType, Block> pane_windows;

    public MacawWindowsModule(String modId) {
        super(modId, "mcw");
        ResourceLocation tab = modRes(modId);

        windows = StonezoneEntrySet.of(StoneType.class, "window",
                        getModBlock("stone_window"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ConnectedWindow(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.6F, 1.2F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(windows);

        window2s = StonezoneEntrySet.of(StoneType.class, "window2",
                        getModBlock("stone_window2"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WindowBarred(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.6F, 1.2F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(window2s);

        four_windows = StonezoneEntrySet.of(StoneType.class, "four_window",
                        getModBlock("stone_four_window"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WindowBarred(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.6F, 1.2F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(four_windows);

        brick_gothics = StonezoneEntrySet.of(StoneType.class, "brick_gothic",
                        getModBlock("stone_brick_gothic"), StoneTypeRegistry::getStoneType,
                        stoneType -> new GothicWindow(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.5F, 2.0F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("gothic"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_gothics);

        brick_arrow_slits = StonezoneEntrySet.of(StoneType.class, "brick_arrow_slit",
                        getModBlock("stone_brick_arrow_slit"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ArrowSill(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.5F, 2.0F)
                                .requiresCorrectToolForDrops()
                        )

                )
                .requiresChildren("bricks", "brick_slab") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("arrow_slit"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_arrow_slits);

        pane_windows = StonezoneEntrySet.of(StoneType.class, "pane_window",
                        getModBlock("stone_pane_window"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Window(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.6F, 1.2F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(pane_windows);

    }

    @Override
    // MODELS
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

// Gothic Model
        String pathGothic = "mcwwindows/parent/gothic/gothic_";

        removeTintIndexFromParentModel(pathGothic + "small", "#3", handler, manager);
        removeTintIndexFromParentModel(pathGothic + "tall_lower", "#3", handler, manager);
        removeTintIndexFromParentModel(pathGothic + "tall_middle", "#3", handler, manager);
        removeTintIndexFromParentModel(pathGothic + "tall_upper", "#3", handler, manager);

// Window Model
        String pathWindow = "mcwwindows/parent/window/";

        removeTintIndexFromParentModel(pathWindow + "mid_l", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "middle", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "single", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "single_l", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "single_m", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "top", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "top_l", "#1", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "top_m", "#1", handler, manager);

        removeTintIndexFromParentModel(pathWindow + "window_above", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_above_open", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_barred", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_barred_open", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_base", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_base_open", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_below", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_below_open", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_four", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_four_open", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_middle", "#0", handler, manager);
        removeTintIndexFromParentModel(pathWindow + "window_middle_open", "#0", handler, manager);

    }
}