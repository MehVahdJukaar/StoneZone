package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import net.kikoz.mcwbridges.objects.Bridge_Block;
import net.kikoz.mcwbridges.objects.Bridge_Stairs;
import net.kikoz.mcwbridges.objects.Bridge_Support;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


//SUPPORT: v3.0.0+
public class MacawBridgesModule extends SZModule{

    public final SimpleEntrySet<StoneType, Block> brick_bridges;
    public final SimpleEntrySet<StoneType, Block> mossy_brick_bridges;
    public final SimpleEntrySet<StoneType, Block> balustrade_bricks_bridges;
    public final SimpleEntrySet<StoneType, Block> balustrade_mossy_bricks_bridges;
    public final SimpleEntrySet<StoneType, Block> bridge_piers;
    public final SimpleEntrySet<StoneType, Block> mossy_bridge_piers;
    public final SimpleEntrySet<StoneType, Block> brick_bridge_stairs;
    public final SimpleEntrySet<StoneType, Block> mossy_bridge_stairs;

    public MacawBridgesModule(String modId) {
        super(modId, "mcb");
        ResourceLocation tab = modRes("bridges");
        
        brick_bridges = StonezoneEntrySet.of(StoneType.class, "brick_bridge",
                getModBlock("stone_brick_bridge"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Block(standardProperties(stoneType))
                )
                .requiresChildren("mossy_bricks")
                //TEXTURES: mossy_bricks,
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_bridges);

        mossy_brick_bridges = StonezoneEntrySet.of(StoneType.class, "brick_bridge", "mossy",
                getModBlock("mossy_stone_brick_bridge"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Block(standardProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(mossy_brick_bridges);

        balustrade_bricks_bridges = StonezoneEntrySet.of(StoneType.class, "bricks_bridge", "balustrade",
                getModBlock("balustrade_stone_bricks_bridge"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Block(balustradeProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(balustrade_bricks_bridges);

        balustrade_mossy_bricks_bridges = StonezoneEntrySet.of(StoneType.class, "bricks_bridge", "balustrade_mossy",
                getModBlock("balustrade_mossy_stone_bricks_bridge"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Block(balustradeProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(balustrade_mossy_bricks_bridges);

        bridge_piers = StonezoneEntrySet.of(StoneType.class, "bridge_pier",
                getModBlock("stone_bridge_pier"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Support(standardProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bridge_piers);

        mossy_bridge_piers = StonezoneEntrySet.of(StoneType.class, "bridge_pier", "mossy",
                getModBlock("mossy_stone_bridge_pier"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Support(standardProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(mossy_bridge_piers);

        brick_bridge_stairs = StonezoneEntrySet.of(StoneType.class, "brick_bridge_stair",
                getModBlock("stone_brick_bridge_stair"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Stairs(standardProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_bridge_stairs);

        mossy_bridge_stairs = StonezoneEntrySet.of(StoneType.class, "bridge_stair", "mossy",
                getModBlock("mossy_stone_bridge_stair"), StoneTypeRegistry::getStoneType,
                stoneType -> new Bridge_Stairs(standardProperties(stoneType))
                )
                //TEXTURES:
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(mossy_bridge_stairs);

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