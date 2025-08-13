package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import net.kikoz.mcwbridges.objects.Bridge_Block;
import net.kikoz.mcwbridges.objects.Bridge_Stairs;
import net.kikoz.mcwbridges.objects.Bridge_Support;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


//SUPPORT: v3.0.0+
public class MacawBridgesModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> brick_bridges;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_bridges;
    public final SimpleEntrySet<StoneType, Block> balustrade_bricks_bridges;
    public final SimpleEntrySet<StoneType, Block> balustrade_mossy_bricks_bridges;
    public final SimpleEntrySet<StoneType, Block> bridge_piers;
    public final SimpleEntrySet<StoneType, Block> mossy_bridge_piers;
    public final SimpleEntrySet<StoneType, Block> brick_bridge_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_bridge_stairs;
    public final SimpleEntrySet<StoneType, Block> bridges;

    public MacawBridgesModule(String modId) {
        super(modId, "mcb");
        ResourceLocation tab = modRes("bridges");

        brick_bridges = StoneZoneEntrySet.of(StoneType.class, "brick_bridge",
                        getModBlock("stone_brick_bridge"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Block(standardProperties(stoneType))
                )
                .excludeMultipleTextureFromTinting(modRes("block/bridge/bridge_stone/parent/base"), "#2")
                .excludeMultipleTextureFromTinting(modRes("block/bridge/bridge_stone/parent/corner"), "#5")
                .excludeMultipleTextureFromTinting(modRes("block/bridge/bridge_stone/parent/middle"), "#5")
                .excludeMultipleTextureFromTinting(modRes("block/bridge/bridge_stone/parent/side"), "#5")
                .requiresChildren("bricks", "brick_slab", "brick_wall") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_stone_brick_bridge"))
                .build();
        this.addEntry(brick_bridges);

        mossy_brick_bridges = StoneZoneEntrySet.of(StoneType.class, "brick_bridge", "mossy",
                        getModBlock("mossy_stone_brick_bridge"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Block(standardProperties(stoneType))
                )
                .requiresChildren("mossy_bricks", "mossy_brick_slab", "mossy_brick_wall") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_mossy_stone_brick_bridge"))
                .build();
        this.addEntry(mossy_brick_bridges);

        balustrade_bricks_bridges = StoneZoneEntrySet.of(StoneType.class, "bricks_bridge", "balustrade",
                        getModBlock("balustrade_stone_bricks_bridge"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Block(balustradeProperties(stoneType))
                )
                .excludeMultipleTextureFromTinting(modRes("block/bridge/balustrade/parent/base"), "#2")
                .excludeMultipleTextureFromTinting(modRes("block/bridge/balustrade/parent/middle"), "#1")
                .excludeMultipleTextureFromTinting(modRes("block/bridge/balustrade/parent/corner"), "#5")
                .excludeMultipleTextureFromTinting(modRes("block/bridge/balustrade/parent/side"), "#5")
                .requiresChildren("bricks", "brick_slab", "brick_wall") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_balustrade_stone_bricks_bridge"))
                .build();
        this.addEntry(balustrade_bricks_bridges);

        balustrade_mossy_bricks_bridges = StoneZoneEntrySet.of(StoneType.class, "bricks_bridge", "balustrade_mossy",
                        getModBlock("balustrade_mossy_stone_bricks_bridge"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Block(balustradeProperties(stoneType))
                )
                .requiresChildren("mossy_bricks", "mossy_brick_slab", "mossy_brick_wall") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_balustrade_mossy_stone_bricks_bridge"))
                .build();
        this.addEntry(balustrade_mossy_bricks_bridges);

        bridge_piers = StoneZoneEntrySet.of(StoneType.class, "bridge_pier",
                        getModBlock("stone_bridge_pier"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Support(standardProperties(stoneType))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_piers"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_stone_bridge_pier"))
                .build();
        this.addEntry(bridge_piers);

        mossy_bridge_piers = StoneZoneEntrySet.of(StoneType.class, "bridge_pier", "mossy",
                        getModBlock("mossy_stone_bridge_pier"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Support(standardProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_piers"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_mossy_stone_bridge_pier"))
                .build();
        this.addEntry(mossy_bridge_piers);

        brick_bridge_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_bridge_stair",
                        getModBlock("stone_brick_bridge_stair"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Stairs(standardProperties(stoneType))
                )
                .excludeMultipleTextureFromTinting(modRes("block/stair/stone/parent/base"), "#1")
                .excludeMultipleTextureFromTinting(modRes("block/stair/stone/parent/double"), "#1")
                .excludeMultipleTextureFromTinting(modRes("block/stair/stone/parent/left"), "#1")
                .excludeMultipleTextureFromTinting(modRes("block/stair/stone/parent/right"), "#1")
                .requiresChildren("bricks") //REASON: textures, recipes
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
                        getModBlock("mossy_stone_bridge_stair"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Bridge_Stairs(standardProperties(stoneType))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
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
                        stoneType -> new Bridge_Block(standardProperties(stoneType))
                )
                .requiresChildren("polished_stone", "slab", "wall") //REASON: textures, recipes
                //TEXTURES: stone, polished_stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("stone_bridges"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("stonecutter_andesite_bridge"))
                .build();
        this.addEntry(bridges);

    }

    public BlockBehaviour.Properties standardProperties(StoneType stoneType) {
        return BlockBehaviour.Properties.of()
                .mapColor(stoneType.stone.defaultMapColor())
                .strength(1.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(stoneType.getSound());
    }

    public BlockBehaviour.Properties balustradeProperties(StoneType stoneType) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLACK)
                .strength(1.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(stoneType.getSound());
    }

}