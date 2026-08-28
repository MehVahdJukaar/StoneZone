package net.mehvahdjukaar.stone_zone.modules.macaw;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.Objects;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

///SUPPORT: v3.1.2+
public abstract class MacawBridgesModuleAbstract extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> brick_bridges;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_bridges;
    public final SimpleEntrySet<StoneType, Block> balustrade_bricks_bridges;
    public final SimpleEntrySet<StoneType, Block> balustrade_mossy_bricks_bridges;
    public final SimpleEntrySet<StoneType, Block> bridge_piers;
    public final SimpleEntrySet<StoneType, Block> mossy_bridge_piers;
    public final SimpleEntrySet<StoneType, Block> brick_bridge_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_bridge_stairs;
    public final SimpleEntrySet<StoneType, Block> bridges;

    public MacawBridgesModuleAbstract(String modId) {
        super(modId, "mcb");
        ResourceLocation tab = modRes("bridges");

        brick_bridges = StoneZoneEntrySet.of(StoneType.class, "brick_bridge",
                        getModBlock("stone_brick_bridge"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Block(stoneType, standardProperties(stoneType, BRICKS))
                )
                .requiresChildren(BRICKS, BRICK_SLAB, BRICK_WALL) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_stone_brick_bridge"))
                .build();
        this.addEntry(brick_bridges);

        mossy_brick_bridges = StoneZoneEntrySet.of(StoneType.class, "brick_bridge", "mossy",
                        getModBlock("mossy_stone_brick_bridge"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Block(stoneType, standardProperties(stoneType, MOSSY_BRICKS))
                )
                .requiresChildren(MOSSY_BRICKS, MOSSY_BRICK_SLAB, MOSSY_BRICK_WALL) //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_mossy_stone_brick_bridge"))
                .build();
        this.addEntry(mossy_brick_bridges);

        balustrade_bricks_bridges = StoneZoneEntrySet.of(StoneType.class, "bricks_bridge", "balustrade",
                        getModBlock("balustrade_stone_bricks_bridge"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Block(stoneType, balustradeProperties(stoneType, BRICKS))
                )
                .requiresChildren(BRICKS, BRICK_SLAB, BRICK_WALL) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_balustrade_stone_bricks_bridge"))
                .build();
        this.addEntry(balustrade_bricks_bridges);

        balustrade_mossy_bricks_bridges = StoneZoneEntrySet.of(StoneType.class, "bricks_bridge", "balustrade_mossy",
                        getModBlock("balustrade_mossy_stone_bricks_bridge"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Block(stoneType, balustradeProperties(stoneType, MOSSY_BRICKS))
                )
                .requiresChildren(MOSSY_BRICKS, MOSSY_BRICK_SLAB, MOSSY_BRICK_WALL) //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_balustrade_mossy_stone_bricks_bridge"))
                .build();
        this.addEntry(balustrade_mossy_bricks_bridges);

        bridge_piers = StoneZoneEntrySet.of(StoneType.class, "bridge_pier",
                        getModBlock("stone_bridge_pier"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Support(stoneType, standardProperties(stoneType, BRICKS))
                )
                .requiresChildren(BRICKS, BRICK_WALL) //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_piers"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_stone_bridge_pier"))
                .build();
        this.addEntry(bridge_piers);

        mossy_bridge_piers = StoneZoneEntrySet.of(StoneType.class, "bridge_pier", "mossy",
                        getModBlock("mossy_stone_bridge_pier"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Support(stoneType, standardProperties(stoneType, MOSSY_BRICKS))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_piers"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_mossy_stone_bridge_pier"))
                .build();
        this.addEntry(mossy_bridge_piers);

        brick_bridge_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_bridge_stair",
                        getModBlock("stone_brick_bridge_stair"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Stairs(stoneType, standardProperties(stoneType, BRICKS))
                )
                .requiresChildren(BRICKS) //REASON: textures, recipes
                .requiresFromMap(brick_bridges.blocks) //REASON: recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stone_brick_bridge_stair_recycle"))
                .addRecipe(modRes("stonecutter_stone_brick_bridge_stair"))
                .build();
        this.addEntry(brick_bridge_stairs);

        mossy_bridge_stairs = StoneZoneEntrySet.of(StoneType.class, "bridge_stair", "mossy",
                        getModBlock("mossy_stone_bridge_stair"), () -> VanillaStoneTypes.STONE,
                        stoneType -> newBridge_Stairs(stoneType, standardProperties(stoneType, MOSSY_BRICKS))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: textures, recipes
                .requiresFromMap(mossy_brick_bridges.blocks) //REASON: recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("mossy_stone_bridge_stair_recycle"))
                .addRecipe(modRes("stonecutter_mossy_stone_bridge_stair"))
                .build();
        this.addEntry(mossy_bridge_stairs);

//!! ANDESITE
        bridges = StoneZoneEntrySet.of(StoneType.class, "bridge",
                        getModBlock("andesite_bridge"), () -> VanillaStoneTypes.ANDESITE,
                        stoneType -> newBridge_Block(stoneType, standardProperties(stoneType, POLISHED))
                )
                .requiresChildren(POLISHED, SLAB, WALL) //REASON: textures, recipes
                //TEXTURES: stone, polished_stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_andesite_bridge"))
                .build();
        this.addEntry(bridges);

    }



    public abstract Block newBridge_Block(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newBridge_Support(StoneType stoneType, BlockBehaviour.Properties properties);
    public abstract Block newBridge_Stairs(StoneType stoneType, BlockBehaviour.Properties properties);

    public BlockBehaviour.Properties standardProperties(StoneType stoneType, String childkey) {
        if (PlatHelper.getPlatform().isForge()) {
            return (stoneType.getBlockOfThis(childkey) != null)
                    ? Utils.copyPropertySafe(Objects.requireNonNull(stoneType.getBlockOfThis(childkey)))
                    : Utils.copyPropertySafe(stoneType.bricksOrStone());
        }
        else
            return BlockBehaviour.Properties.of()
                    .mapColor(stoneType.stone.defaultMapColor())
                    .strength(1.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(stoneType.getSound());

    }

    public BlockBehaviour.Properties balustradeProperties(StoneType stoneType, String childkey) {
        if (PlatHelper.getPlatform().isForge()) {
            return (stoneType.getBlockOfThis(childkey) != null)
                    ? Utils.copyPropertySafe(Objects.requireNonNull(stoneType.getBlockOfThis(childkey)))
                    : Utils.copyPropertySafe(stoneType.bricksOrStone());
        }
        else
            return BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(stoneType.getSound());

    }

}