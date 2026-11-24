package net.mehvahdjukaar.stone_zone.modules.more_beautiful_torches;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;

import java.util.function.Supplier;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;


///SUPPORT: v3.1.1+
public class MoreBeautifulTorches extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> wall_torch,
            torch,

            soul_wall_torch,
            soul_torch,

            redstone_wall_torch,
            redstone_torch,

            bricks_wall_torch,
            bricks_torch,

            bricks_soul_wall_torch,
            bricks_soul_torch,

            bricks_redstone_wall_torch,
            bricks_redstone_torch,

            smooth_wall_torch,
            smooth_torch,

            smooth_soul_wall_torch,
            smooth_soul_torch,

            smooth_redstone_wall_torch,
            smooth_redstone_torch,

            polished_wall_torch,
            polished_torch,
            polished_soul_wall_torch,
            polished_soul_torch,

            polished_redstone_wall_torch,
            polished_redstone_torch;

    public MoreBeautifulTorches(String modId) {
        super(modId, "mbt");
        Supplier<CreativeModeTab> tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? getModTab("goldenfoods_tab")
                : getModTab("morebeautifultorches_tab");

        wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch",
                        getModBlock("stone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties)
                )
                .addTextureM(modRes("block/stone_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.WALL_TORCH).noCollision().instabreak().lightLevel(_ -> 14))
                .build();
        this.addEntry(wall_torch);

        torch = StoneZoneEntrySet.of(StoneType.class, "torch",
                        getModBlock("stone_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new TorchBlock(ParticleTypes.FLAME, properties)
                )
                //TEXTURES: wall_torch
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.TORCH).noCollision().instabreak().lightLevel(_ -> 14))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(torch);

        soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch",
                        getModBlock("stone_soul_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .addTextureM(modRes("block/stone_soul_torch"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                .build();
        this.addEntry(soul_wall_torch);

        soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch",
                        getModBlock("stone_soul_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                //TEXTURES: soul_wall_torch
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .defaultRecipe()
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, soul_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(soul_torch);

        redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch",
                        getModBlock("stone_redstone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RedstoneWallTorchBlock(properties)
                )
                .addTextureM(modRes("block/stone_redstone_torch"), StoneZone.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/stone_redstone_torch_off"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                .build();
        this.addEntry(redstone_wall_torch);

        redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch",
                        getModBlock("stone_redstone_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RedstoneTorchBlock(properties)
                )
                //TEXTURES: redstone_wall_torch
                .defaultRecipe()
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, redstone_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(redstone_torch);

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                            BRICKS                                            │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        bricks_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_wall_torch",
                        getModBlock("stone_bricks_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties)
                )
                .requiresChildren(BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.WALL_TORCH))
                .build();
        this.addEntry(bricks_wall_torch);

        bricks_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_torch",
                        getModBlock("stone_bricks_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new TorchBlock(ParticleTypes.FLAME, properties)
                )
                //TEXTURES: bricks_wall_torch
                .requiresChildren(BRICKS) //REASON: recipes
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.TORCH))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, bricks_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(bricks_torch);

        bricks_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_soul_wall_torch",
                        getModBlock("stone_bricks_soul_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                .requiresChildren(BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_soul_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                .build();
        this.addEntry(bricks_soul_wall_torch);

        bricks_soul_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_soul_torch",
                        getModBlock("stone_bricks_soul_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                //TEXTURES: bricks_soul_wall_torch
                .requiresChildren(BRICKS) //REASON: recipes
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, bricks_soul_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(bricks_soul_torch);

        bricks_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_redstone_wall_torch",
                        getModBlock("stone_bricks_redstone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RedstoneWallTorchBlock(properties)
                )
                .requiresChildren(BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_redstone_torch"),
                        StoneZone.res("block/common_redstone_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTextureM(modRes("block/stone_bricks_redstone_torch_off"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                .build();
        this.addEntry(bricks_redstone_wall_torch);

        bricks_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_redstone_torch",
                        getModBlock("stone_bricks_redstone_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RedstoneTorchBlock(properties)
                )
                //TEXTURES: bricks_redstone_wall_torch
                .requiresChildren(BRICKS) //REASON: recipes
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, bricks_redstone_wall_torch.blocks.get(w), Direction.DOWN, p))
                .build();
        this.addEntry(bricks_redstone_torch);

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                            SMOOTH                                            │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        smooth_wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch", "smooth",
                        getModBlock("smooth_stone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties)
                )
                .requiresChildren(SMOOTH) //REASON: textures
                .addTextureM(modRes("block/smooth_stone_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.WALL_TORCH))
                .build();
        this.addEntry(smooth_wall_torch);

        smooth_torch = StoneZoneEntrySet.of(StoneType.class, "torch", "smooth",
                        getModBlock("smooth_stone_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new TorchBlock(ParticleTypes.FLAME, properties)
                )
                //TEXTURES: smooth_wall_torch
                .requiresChildren(SMOOTH) //REASON: recipes
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.TORCH))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, smooth_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(smooth_torch);

        smooth_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch", "smooth",
                        getModBlock("smooth_stone_soul_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                .requiresChildren(SMOOTH) //REASON: textures
                .addTextureM(modRes("block/smooth_stone_soul_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.SMOOTH_STANDARD)
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                .build();
        this.addEntry(smooth_soul_wall_torch);

        smooth_soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch", "smooth",
                        getModBlock("smooth_stone_soul_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                //TEXTURES: smooth_soul_wall_torch
                .requiresChildren(SMOOTH) //REASON: recipes
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, smooth_soul_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(smooth_soul_torch);

        smooth_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch", "smooth",
                        getModBlock("smooth_stone_redstone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RedstoneWallTorchBlock(properties)
                )
                .requiresChildren(SMOOTH) //REASON: textures
                .addTextureM(modRes("block/smooth_stone_redstone_torch"),
                        StoneZone.res("block/common_redstone_torch_m"),
                        StonePaletteStrategies.SMOOTH_STANDARD)
                .addTextureM(modRes("block/smooth_stone_redstone_torch_off"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.SMOOTH_STANDARD)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                .build();
        this.addEntry(smooth_redstone_wall_torch);

        smooth_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch", "smooth",
                        getModBlock("smooth_stone_redstone_torch"), () -> VanillaStoneTypes.STONE,
                        (_, properties) -> new RedstoneTorchBlock(properties)
                )
                //TEXTURES: smooth_redstone_wall_torch
                .requiresChildren(SMOOTH) //REASON: recipes
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, smooth_redstone_wall_torch.blocks.get(w), Direction.DOWN, p))
                .build();
        this.addEntry(smooth_redstone_torch);

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                           POLISHED                                           │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘
        polished_wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch", "polished",
                        getModBlock("polished_andesite_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties)
                )
                .requiresChildren(POLISHED) //REASON: textures
                .addTextureM(modRes("block/polished_andesite_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.POLISHED_STANDARD)
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.WALL_TORCH))
                .build();
        this.addEntry(polished_wall_torch);

        polished_torch = StoneZoneEntrySet.of(StoneType.class, "torch", "polished",
                        getModBlock("polished_andesite_torch"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new TorchBlock(ParticleTypes.FLAME, properties)
                )
                //TEXTURES: polished_wall_torch
                .requiresChildren(POLISHED) //REASON: recipes
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.TORCH))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, polished_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(polished_torch);

        polished_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch", "polished",
                        getModBlock("polished_andesite_soul_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                .requiresChildren(POLISHED) //REASON: textures
                .addTextureM(modRes("block/polished_andesite_soul_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.POLISHED_STANDARD)
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                .build();
        this.addEntry(polished_soul_wall_torch);

        polished_soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch", "polished",
                        getModBlock("polished_andesite_soul_torch"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties)
                )
                //TEXTURES: polished_soul_wall_torch
                .requiresChildren(POLISHED) //REASON: recipes
                .addTag(Identifier.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, polished_soul_wall_torch.blocks.get(s), Direction.DOWN, p))
                .build();
        this.addEntry(polished_soul_torch);

        polished_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch", "polished",
                        getModBlock("polished_andesite_redstone_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new RedstoneWallTorchBlock(properties)
                )
                .requiresChildren(POLISHED) //REASON: textures
                .addTextureM(modRes("block/polished_andesite_redstone_torch"),
                        StoneZone.res("block/common_redstone_torch_m"),
                        StonePaletteStrategies.POLISHED_STANDARD)
                .addTextureM(modRes("block/polished_andesite_redstone_torch_off"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.POLISHED_STANDARD)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                .build();
        this.addEntry(polished_redstone_wall_torch);

        polished_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch", "polished",
                        getModBlock("polished_andesite_redstone_torch"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new RedstoneTorchBlock(properties)
                )
                //TEXTURES: polished_redstone_wall_torch
                .requiresChildren(POLISHED) //REASON: recipes
                .setTab(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .setProperties(_ -> Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, polished_redstone_wall_torch.blocks.get(w), Direction.DOWN, p))
                .build();
        this.addEntry(polished_redstone_torch);

    }
}
