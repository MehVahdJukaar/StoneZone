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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;

import java.util.function.Supplier;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;


//SUPPORT: v3.0.0+
public class MoreBeautifulTorches extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> wall_torch,
                                                  torch;
    public final SimpleEntrySet<StoneType, Block> soul_wall_torch,
                                                  soul_torch;
    public final SimpleEntrySet<StoneType, Block> redstone_wall_torch,
                                                  redstone_torch;

    public final SimpleEntrySet<StoneType, Block> bricks_wall_torch,
                                                  bricks_torch;
    public final SimpleEntrySet<StoneType, Block> bricks_soul_wall_torch,
                                                  bricks_soul_torch;
    public final SimpleEntrySet<StoneType, Block> bricks_redstone_wall_torch,
                                                  bricks_redstone_torch;

    public final SimpleEntrySet<StoneType, Block> smooth_wall_torch,
                                                  smooth_torch;
    public final SimpleEntrySet<StoneType, Block> smooth_soul_wall_torch,
                                                  smooth_soul_torch;
    public final SimpleEntrySet<StoneType, Block> smooth_redstone_wall_torch,
                                                  smooth_redstone_torch;

    public final SimpleEntrySet<StoneType, Block> polished_wall_torch,
                                                  polished_torch;
    public final SimpleEntrySet<StoneType, Block> polished_soul_wall_torch,
                                                  polished_soul_torch;
    public final SimpleEntrySet<StoneType, Block> polished_redstone_wall_torch,
                                                  polished_redstone_torch;

    public MoreBeautifulTorches(String modId) {
        super(modId, "mbt");
        Supplier<CreativeModeTab> tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? getModTab("goldenfoods_tab")
                : getModTab("morebeautifultorches_tab");

        wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch",
                        getModBlock("stone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallTorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.WALL_TORCH).noCollission().instabreak().lightLevel(l -> 14))
                )
                .addTextureM(modRes("block/stone_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(wall_torch);

        torch = StoneZoneEntrySet.of(StoneType.class, "torch",
                        getModBlock("stone_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.TORCH).noCollission().instabreak().lightLevel(l -> 14))
                )
                //TEXTURES: wall_torch
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(torch);

        soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch",
                        getModBlock("stone_soul_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .addTextureM(modRes("block/stone_soul_torch"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .build();
        this.addEntry(soul_wall_torch);

        soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch",
                        getModBlock("stone_soul_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                )
                //TEXTURES: soul_wall_torch
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(soul_torch);

        redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch",
                        getModBlock("stone_redstone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .addTextureM(modRes("block/stone_redstone_torch"), StoneZone.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/stone_redstone_torch_off"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(redstone_wall_torch);

        redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch",
                        getModBlock("stone_redstone_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: redstone_wall_torch
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, redstone_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(redstone_torch);

//!!--BRICKS--
        bricks_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_wall_torch",
                        getModBlock("stone_bricks_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallTorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.WALL_TORCH))
                )
                .requiresChildren(BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_wall_torch);

        bricks_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_torch",
                        getModBlock("stone_bricks_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.TORCH))
                )
                //TEXTURES: bricks_wall_torch
                .requiresChildren(BRICKS) //REASON: recipes
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, bricks_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_torch);

        bricks_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_soul_wall_torch",
                        getModBlock("stone_bricks_soul_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                )
                .requiresChildren(BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_soul_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_soul_wall_torch);

        bricks_soul_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_soul_torch",
                        getModBlock("stone_bricks_soul_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                )
                //TEXTURES: bricks_soul_wall_torch
                .requiresChildren(BRICKS) //REASON: recipes
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, bricks_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_soul_torch);

        bricks_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_redstone_wall_torch",
                        getModBlock("stone_bricks_redstone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
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
                .build();
        this.addEntry(bricks_redstone_wall_torch);

        bricks_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_redstone_torch",
                        getModBlock("stone_bricks_redstone_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: bricks_redstone_wall_torch
                .requiresChildren(BRICKS) //REASON: recipes
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, bricks_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_redstone_torch);

//!!--SMOOTH--
        smooth_wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch", "smooth",
                        getModBlock("smooth_stone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallTorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.WALL_TORCH))
                )
                .requiresChildren(SMOOTH) //REASON: textures
                .addTextureM(modRes("block/smooth_stone_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_wall_torch);

        smooth_torch = StoneZoneEntrySet.of(StoneType.class, "torch", "smooth",
                        getModBlock("smooth_stone_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.TORCH))
                )
                //TEXTURES: smooth_wall_torch
                .requiresChildren(SMOOTH) //REASON: recipes
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, smooth_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_torch);

        smooth_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch", "smooth",
                        getModBlock("smooth_stone_soul_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                )
                .requiresChildren(SMOOTH) //REASON: textures
                .addTextureM(modRes("block/smooth_stone_soul_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.SMOOTH_STANDARD)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_soul_wall_torch);

        smooth_soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch", "smooth",
                        getModBlock("smooth_stone_soul_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                )
                //TEXTURES: smooth_soul_wall_torch
                .requiresChildren(SMOOTH) //REASON: recipes
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, smooth_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_soul_torch);

        smooth_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch", "smooth",
                        getModBlock("smooth_stone_redstone_wall_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
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
                .build();
        this.addEntry(smooth_redstone_wall_torch);

        smooth_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch", "smooth",
                        getModBlock("smooth_stone_redstone_torch"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: smooth_redstone_wall_torch
                .requiresChildren(SMOOTH) //REASON: recipes
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, smooth_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_redstone_torch);

//!!--POLISHED--
        polished_wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch", "polished",
                        getModBlock("polished_andesite_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new WallTorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.WALL_TORCH))
                )
                .requiresChildren(POLISHED) //REASON: textures
                .addTextureM(modRes("block/polished_andesite_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.POLISHED_STANDARD)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_wall_torch);

        polished_torch = StoneZoneEntrySet.of(StoneType.class, "torch", "polished",
                        getModBlock("polished_andesite_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new TorchBlock(ParticleTypes.FLAME, Utils.copyPropertySafe(Blocks.TORCH))
                )
                //TEXTURES: polished_wall_torch
                .requiresChildren(POLISHED) //REASON: recipes
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, polished_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_torch);

        polished_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch", "polished",
                        getModBlock("polished_andesite_soul_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH))
                )
                .requiresChildren(POLISHED) //REASON: textures
                .addTextureM(modRes("block/polished_andesite_soul_torch"),
                        StoneZone.res("block/common_torch_m"),
                        StonePaletteStrategies.POLISHED_STANDARD)
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_soul_wall_torch);

        polished_soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch", "polished",
                        getModBlock("polished_andesite_soul_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, Utils.copyPropertySafe(Blocks.SOUL_TORCH))
                )
                //TEXTURES: polished_soul_wall_torch
                .requiresChildren(POLISHED) //REASON: recipes
                .addTag(ResourceLocation.parse("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, polished_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_soul_torch);

        polished_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch", "polished",
                        getModBlock("polished_andesite_redstone_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
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
                .build();
        this.addEntry(polished_redstone_wall_torch);

        polished_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch", "polished",
                        getModBlock("polished_andesite_redstone_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: polished_redstone_wall_torch
                .requiresChildren(POLISHED) //REASON: recipes
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, polished_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_redstone_torch);


    }
}