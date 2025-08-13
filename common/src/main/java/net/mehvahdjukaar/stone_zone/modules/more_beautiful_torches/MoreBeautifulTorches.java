package net.mehvahdjukaar.stone_zone.modules.more_beautiful_torches;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockTypeChildKeys;
import net.mehvahdjukaar.stone_zone.api.set.VanillaStoneTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;


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
        ResourceLocation tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? modRes("goldenfoods_tab")
                : modRes("morebeautifultorches_tab");

        wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch",
                        getModBlock("stone_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME)
                )
                .addTextureM(modRes("block/stone_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(wall_torch);

        torch = StoneZoneEntrySet.of(StoneType.class, "torch",
                        getModBlock("stone_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME)
                )
                //TEXTURES: wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(torch);

        soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch",
                        getModBlock("stone_soul_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .addTextureM(modRes("block/stone_soul_torch"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .build();
        this.addEntry(soul_wall_torch);

        soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch",
                        getModBlock("stone_soul_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(soul_torch);

        redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch",
                        getModBlock("stone_redstone_wall_torch"), StoneTypeRegistry::getStoneType,
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
                        getModBlock("stone_redstone_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, redstone_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(redstone_torch);

//!!--BRICKS--
        bricks_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_wall_torch",
                        getModBlock("stone_bricks_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH), ParticleTypes.FLAME)
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_wall_torch);

        bricks_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_torch",
                        getModBlock("stone_bricks_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH), ParticleTypes.FLAME)
                )
                //TEXTURES: bricks_wall_torch
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, bricks_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_torch);

        bricks_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_soul_wall_torch",
                        getModBlock("stone_bricks_soul_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_soul_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_soul_wall_torch);

        bricks_soul_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_soul_torch",
                        getModBlock("stone_bricks_soul_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: bricks_soul_wall_torch
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, bricks_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_soul_torch);

        bricks_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_redstone_wall_torch",
                        getModBlock("stone_bricks_redstone_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: textures
                .addTextureM(modRes("block/stone_bricks_redstone_torch"), StoneZone.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/stone_bricks_redstone_torch_off"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_redstone_wall_torch);

        bricks_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "bricks_redstone_torch",
                        getModBlock("stone_bricks_redstone_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: bricks_redstone_wall_torch
                .requiresChildren(VanillaRockTypeChildKeys.BRICKS) //REASON: recipes
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, bricks_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bricks_redstone_torch);

//!!--SMOOTH--
        smooth_wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch", "smooth",
                        getModBlock("smooth_stone_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH), ParticleTypes.FLAME)
                )
                .createPaletteFromBricks()
                .requiresChildren(VanillaRockTypeChildKeys.SMOOTH) //REASON: textures
                .addTextureM(modRes("block/smooth_stone_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_wall_torch);

        smooth_torch = StoneZoneEntrySet.of(StoneType.class, "torch", "smooth",
                        getModBlock("smooth_stone_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH), ParticleTypes.FLAME)
                )
                //TEXTURES: smooth_wall_torch
                .requiresChildren(VanillaRockTypeChildKeys.SMOOTH) //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, smooth_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_torch);

        smooth_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch", "smooth",
                        getModBlock("smooth_stone_soul_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .createPaletteFromBricks()
                .requiresChildren("smooth") //REASON: textures
                .addTextureM(modRes("block/smooth_stone_soul_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_soul_wall_torch);

        smooth_soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch", "smooth",
                        getModBlock("smooth_stone_soul_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: smooth_soul_wall_torch
                .requiresChildren("smooth") //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, smooth_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_soul_torch);

        smooth_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch", "smooth",
                        getModBlock("smooth_stone_redstone_wall_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .createPaletteFromBricks()
                .requiresChildren("smooth") //REASON: textures
                .addTextureM(modRes("block/smooth_stone_redstone_torch"), StoneZone.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/smooth_stone_redstone_torch_off"), StoneZone.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_redstone_wall_torch);

        smooth_redstone_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_torch", "smooth",
                        getModBlock("smooth_stone_redstone_torch"), StoneTypeRegistry::getStoneType,
                        stoneType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: smooth_redstone_wall_torch
                .requiresChildren("smooth") //REASON: recipes
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, smooth_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(smooth_redstone_torch);

//!!--POLISHED--
        polished_wall_torch = StoneZoneEntrySet.of(StoneType.class, "wall_torch", "polished",
                        getModBlock("polished_andesite_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH), ParticleTypes.FLAME)
                )
                .createPaletteFromBricks()
                .requiresChildren("polished") //REASON: textures
                .addTextureM(modRes("block/polished_andesite_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_wall_torch);

        polished_torch = StoneZoneEntrySet.of(StoneType.class, "torch", "polished",
                        getModBlock("polished_andesite_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH), ParticleTypes.FLAME)
                )
                //TEXTURES: polished_wall_torch
                .requiresChildren("polished") //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, polished_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_torch);

        polished_soul_wall_torch = StoneZoneEntrySet.of(StoneType.class, "soul_wall_torch", "polished",
                        getModBlock("polished_andesite_soul_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .createPaletteFromBricks()
                .requiresChildren("polished") //REASON: textures
                .addTextureM(modRes("block/polished_andesite_soul_torch"), StoneZone.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_soul_wall_torch);

        polished_soul_torch = StoneZoneEntrySet.of(StoneType.class, "soul_torch", "polished",
                        getModBlock("polished_andesite_soul_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: polished_soul_wall_torch
                .requiresChildren("polished") //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, polished_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_soul_torch);

        polished_redstone_wall_torch = StoneZoneEntrySet.of(StoneType.class, "redstone_wall_torch", "polished",
                        getModBlock("polished_andesite_redstone_wall_torch"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .createPaletteFromBricks()
                .requiresChildren("polished") //REASON: textures
                .addTextureM(modRes("block/polished_andesite_redstone_torch"), StoneZone.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/polished_andesite_redstone_torch_off"), StoneZone.res("block/common_torch_m"))
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
                .requiresChildren("polished") //REASON: recipes
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, polished_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(polished_redstone_torch);


    }
}