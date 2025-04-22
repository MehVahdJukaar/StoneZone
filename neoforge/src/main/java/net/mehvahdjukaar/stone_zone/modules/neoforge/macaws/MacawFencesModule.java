package net.mehvahdjukaar.stone_zone.modules.neoforge.macaws;

import com.mcwfences.kikoz.objects.FenceHitbox;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StonezoneModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import static net.mehvahdjukaar.stone_zone.misc.ModelUtils.removeTintIndexFromParentModel;

//SUPPORT: v1.1.2+
public class MacawFencesModule extends StonezoneModule {

    public final SimpleEntrySet<StoneType, Block> modern_brick_walls;
    public final SimpleEntrySet<StoneType, Block> railing_brick_gates;
    public final SimpleEntrySet<StoneType, Block> railing_brick_walls;
    public final SimpleEntrySet<StoneType, Block> grass_topped_walls;

    public final SimpleEntrySet<StoneType, Block> pillar_walls;
    public final SimpleEntrySet<StoneType, Block> railing_gates;
    public final SimpleEntrySet<StoneType, Block> railing_walls;
    public final SimpleEntrySet<StoneType, Block> modern_walls;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");
        ResourceLocation tab = modRes("fenceitemgroup");
        
        modern_brick_walls = StonezoneEntrySet.of(StoneType.class, "brick_wall", "modern",
                getModBlock("modern_stone_brick_wall"), StoneTypeRegistry::getStoneType,
                stoneType -> new FenceBlock(standardCopyProperies(stoneType))
                )
                .requiresChildren("bricks", "cobblestone") //REASON: testures, recipes
                //TEXTURES: bricks, cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("modern_stone_brick_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(modern_brick_walls);

        railing_brick_gates = StonezoneEntrySet.of(StoneType.class, "brick_railing_gate",
                getModBlock("stone_brick_railing_gate"), StoneTypeRegistry::getStoneType,
                stoneType -> new FenceGateBlock(WoodType.OAK,standardCopyProperies(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_railing_gate_stonecutter"))
                .build();
        this.addEntry(railing_brick_gates);

        railing_brick_walls = StonezoneEntrySet.of(StoneType.class, "brick_wall", "railing",
                getModBlock("railing_stone_brick_wall"), StoneTypeRegistry::getStoneType,
                stoneType -> new FenceBlock(standardCopyProperies(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("railing_stone_brick_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(railing_brick_walls);

//!! ANDESITE
        grass_topped_walls = StonezoneEntrySet.of(StoneType.class, "grass_topped_wall",
                getModBlock("stone_grass_topped_wall"), StoneTypeRegistry::getStoneType,
                stoneType -> new FenceHitbox(standardCopyProperies(stoneType))
                )
                .requiresChildren("bricks", "cobblestone") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: bricks, cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(grass_topped_walls);

//!! ANDESITE
        pillar_walls = StonezoneEntrySet.of(StoneType.class, "pillar_wall",
                getModBlock("andesite_pillar_wall"), StoneTypeRegistry::getAndesiteType,
                stoneType -> new FenceBlock(standardCopyProperies(stoneType))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: stone, polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(pillar_walls);

        railing_gates = StonezoneEntrySet.of(StoneType.class, "railing_gate",
                        getModBlock("andesite_railing_gate"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceGateBlock(WoodType.OAK,standardCopyProperies(stoneType))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("andesite_railing_gate_stonecutter"))
                .build();
        this.addEntry(railing_gates);

        railing_walls = StonezoneEntrySet.of(StoneType.class, "wall", "railing",
                        getModBlock("railing_andesite_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceBlock(standardCopyProperies(stoneType))
                )
                .requiresChildren("polished") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("railing_andesite_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(railing_walls);

        modern_walls = StonezoneEntrySet.of(StoneType.class, "wall", "modern",
                        getModBlock("modern_andesite_wall"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FenceBlock(standardCopyProperies(stoneType))
                )
                .requiresChildren("polished") //REASON: testures, recipes
                //TEXTURES: stone, polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("modern_andesite_wall_stonecutter"))
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .build();
        this.addEntry(modern_walls);


    }

    private BlockBehaviour.Properties standardCopyProperies(StoneType stoneType) {
        return Utils.copyPropertySafe(stoneType.bricksOrStone())
                .mapColor(MapColor.STONE)
                .sound(SoundType.STONE)
                .strength(2.0F, 6.0F)
                .noOcclusion()
                .requiresCorrectToolForDrops();
    }

    @Override
    // MODELS
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        if (!grass_topped_walls.blocks.isEmpty()) {
            String pathGrassToppedWall = "mcwfences/parent/inventory/grass_topped_wall";
            removeTintIndexFromParentModel(pathGrassToppedWall, "#3", handler, manager);
            removeTintIndexFromParentModel(pathGrassToppedWall, "#4", handler, manager);

            String[] filenames = {
                    "corner", "cross", "side",
                    "triple", "middle", "post"
            };
            for (String filename : filenames) {
                String pathGrassTopped = "mcwfences/parent/grass_topped_wall_";
                String targetTexture = switch (filename) {
                    case "middle" -> "#0";
                    case "post" -> "#3";
                    default -> "#2";
                };
                String target2ndTexture = switch (filename) {
                    case "middle" -> "#1";
                    case "post" -> "#4";
                    default -> "#3"; // corner|cross|side|triple
                };
                removeTintIndexFromParentModel(pathGrassTopped + filename, targetTexture, handler, manager);
                removeTintIndexFromParentModel(pathGrassTopped + filename, target2ndTexture, handler, manager);
            }
        }

        if (!railing_brick_walls.blocks.isEmpty()) {
            String pathRailingWall = "mcwfences/parent/railing_wall";
            removeTintIndexFromParentModel(pathRailingWall + "_post", "#2", handler, manager);
            removeTintIndexFromParentModel(pathRailingWall + "_side", "#2", handler, manager);
        }

        if (!railing_brick_gates.blocks.isEmpty()) {
            String pathRailingGate = "mcwfences/parent/railing_gate";
            removeTintIndexFromParentModel(pathRailingGate, "#4", handler, manager);
            removeTintIndexFromParentModel(pathRailingGate + "_open", "#4", handler, manager);
        }
    }
}