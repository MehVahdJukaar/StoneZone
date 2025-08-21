package net.mehvahdjukaar.stone_zone.modules.forge.macaws;

import com.mcwwindows.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICK_SLAB;


//SUPPORT: v2.3.0+
public class MacawWindowsModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> windows;
    public final SimpleEntrySet<StoneType, Block> window2s;
    public final SimpleEntrySet<StoneType, Block> four_windows;
    public final SimpleEntrySet<StoneType, Block> brick_gothics;
    public final SimpleEntrySet<StoneType, Block> brick_arrow_slits;
    public final SimpleEntrySet<StoneType, Block> pane_windows;

    public MacawWindowsModule(String modId) {
        super(modId, "mcw");
        ResourceLocation tab = modRes(modId);

        windows = StoneZoneEntrySet.of(StoneType.class, "window",
                        getModBlock("stone_window"), () -> VanillaStoneTypes.STONE,
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

        window2s = StoneZoneEntrySet.of(StoneType.class, "window2",
                        getModBlock("stone_window2"), () -> VanillaStoneTypes.STONE,
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

        four_windows = StoneZoneEntrySet.of(StoneType.class, "four_window",
                        getModBlock("stone_four_window"), () -> VanillaStoneTypes.STONE,
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

        brick_gothics = StoneZoneEntrySet.of(StoneType.class, "brick_gothic",
                        getModBlock("stone_brick_gothic"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new GothicWindow(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.5F, 2.0F)
                                .requiresCorrectToolForDrops()
                        )
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("gothic"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_gothics);

        brick_arrow_slits = StoneZoneEntrySet.of(StoneType.class, "brick_arrow_slit",
                        getModBlock("stone_brick_arrow_slit"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new ArrowSill(Utils.copyPropertySafe(stoneType.stone)
                                .mapColor(MapColor.COLOR_GRAY)
                                .sound(SoundType.STONE)
                                .strength(0.5F, 2.0F)
                                .requiresCorrectToolForDrops()
                        )

                )
                .requiresChildren(BRICKS, BRICK_SLAB) //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("arrow_slit"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(brick_arrow_slits);

        pane_windows = StoneZoneEntrySet.of(StoneType.class, "pane_window",
                        getModBlock("stone_pane_window"), () -> VanillaStoneTypes.STONE,
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

}