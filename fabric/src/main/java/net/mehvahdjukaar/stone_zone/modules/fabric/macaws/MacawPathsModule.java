package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import com.mcwpaths.kikoz.objects.PathBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StonePaletteStrategies;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;

import java.util.Objects;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;


//SUPPORT v1.0.4+
public class MacawPathsModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> running_bond_paths;
    public final SimpleEntrySet<StoneType, Block> running_bond_slabs;
    public final SimpleEntrySet<StoneType, Block> running_bonds;

    public final SimpleEntrySet<StoneType, Block> mossy_running_bond_paths;
    public final SimpleEntrySet<StoneType, Block> mossy_running_bond_slabs;
    public final SimpleEntrySet<StoneType, Block> mossy_running_bonds;

    public final SimpleEntrySet<StoneType, Block> windmill_weave_paths;
    public final SimpleEntrySet<StoneType, Block> windmill_weave_slabs;
    public final SimpleEntrySet<StoneType, Block> windmill_weaves;

    public final SimpleEntrySet<StoneType, Block> mossy_windmill_weave_paths;
    public final SimpleEntrySet<StoneType, Block> mossy_windmill_weave_slabs;
    public final SimpleEntrySet<StoneType, Block> mossy_windmill_weaves;

    public final SimpleEntrySet<StoneType, Block> flagstone_paths;
    public final SimpleEntrySet<StoneType, Block> flagstone_slabs;
    public final SimpleEntrySet<StoneType, Block> flagstones;

    public final SimpleEntrySet<StoneType, Block> mossy_flagstone_paths;
    public final SimpleEntrySet<StoneType, Block> mossy_flagstone_slabs;
    public final SimpleEntrySet<StoneType, Block> mossy_flagstones;

    public final SimpleEntrySet<StoneType, Block> crystal_floor_paths;
    public final SimpleEntrySet<StoneType, Block> crystal_floor_slabs;
    public final SimpleEntrySet<StoneType, Block> crystal_floors;

    public final SimpleEntrySet<StoneType, Block> mossy_crystal_floor_paths;
    public final SimpleEntrySet<StoneType, Block> mossy_crystal_floor_slabs;
    public final SimpleEntrySet<StoneType, Block> mossy_crystal_floors;

    public final SimpleEntrySet<StoneType, Block> strewn_rocky_paths;
    public final SimpleEntrySet<StoneType, Block> mossy_strewn_rocky_paths;

    public MacawPathsModule(String modId) {
        super(modId, "mcp");
        ResourceLocation tab = modRes("pathgroup");
        running_bond_paths = StoneZoneEntrySet.of(StoneType.class, "running_bond_path",
                        getModBlock("stone_running_bond_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(Blocks.DARK_PRISMARINE))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(running_bond_paths);

        running_bond_slabs = StoneZoneEntrySet.of(StoneType.class, "running_bond_slab",
                        getModBlock("stone_running_bond_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(Blocks.DARK_PRISMARINE))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(running_bond_slabs);

        running_bonds = StoneZoneEntrySet.of(StoneType.class, "running_bond",
                        getModBlock("stone_running_bond"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(Blocks.DARK_PRISMARINE))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_running_bond"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(running_bonds);

        mossy_running_bond_paths = StoneZoneEntrySet.of(StoneType.class, "running_bond_path", "mossy",
                        getModBlock("mossy_stone_running_bond_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(Blocks.DARK_PRISMARINE))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_running_bond_paths);

        mossy_running_bond_slabs = StoneZoneEntrySet.of(StoneType.class, "running_bond_slab", "mossy",
                        getModBlock("mossy_stone_running_bond_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(Blocks.DARK_PRISMARINE))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_running_bond_slabs);

        mossy_running_bonds = StoneZoneEntrySet.of(StoneType.class, "running_bond", "mossy",
                        getModBlock("mossy_stone_running_bond"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(Blocks.DARK_PRISMARINE))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                .addTextureM(modRes("block/mossy_stone_running_bond"),
                        StoneZone.res("block/mcp/mossy_stone_running_bond_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_running_bonds);

        windmill_weave_paths = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_path",
                        getModBlock("stone_windmill_weave_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(windmill_weave_paths);

        windmill_weave_slabs = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_slab",
                        getModBlock("stone_windmill_weave_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(windmill_weave_slabs);

        windmill_weaves = StoneZoneEntrySet.of(StoneType.class, "windmill_weave",
                        getModBlock("stone_windmill_weave"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_windmill_weave"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(windmill_weaves);

        mossy_windmill_weave_paths = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_path", "mossy",
                        getModBlock("mossy_stone_windmill_weave_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_windmill_weave_paths);

        mossy_windmill_weave_slabs = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_slab", "mossy",
                        getModBlock("mossy_stone_windmill_weave_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_windmill_weave_slabs);

        mossy_windmill_weaves = StoneZoneEntrySet.of(StoneType.class, "windmill_weave", "mossy",
                        getModBlock("mossy_stone_windmill_weave"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                .addTextureM(modRes("block/mossy_stone_windmill_weave"),
                        StoneZone.res("block/mcp/mossy_stone_windmill_weave_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_windmill_weaves);

        flagstone_paths = StoneZoneEntrySet.of(StoneType.class, "flagstone_path",
                        getModBlock("stone_flagstone_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: stone_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(flagstone_paths);

        flagstone_slabs = StoneZoneEntrySet.of(StoneType.class, "flagstone_slab",
                        getModBlock("stone_flagstone_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: stone_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(flagstone_slabs);

        flagstones = StoneZoneEntrySet.of(StoneType.class, "flagstone",
                        getModBlock("stone_flagstone"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_flagstone"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(flagstones);

        mossy_flagstone_paths = StoneZoneEntrySet.of(StoneType.class, "flagstone_path", "mossy",
                        getModBlock("mossy_stone_flagstone_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_flagstone_paths);

        mossy_flagstone_slabs = StoneZoneEntrySet.of(StoneType.class, "flagstone_slab", "mossy",
                        getModBlock("mossy_stone_flagstone_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_flagstone_slabs);

        mossy_flagstones = StoneZoneEntrySet.of(StoneType.class, "flagstone", "mossy",
                        getModBlock("mossy_stone_flagstone"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                .addTextureM(modRes("block/mossy_stone_flagstone"),
                        StoneZone.res("block/mcp/mossy_stone_flagstone_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_flagstones);

        crystal_floor_paths = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_path",
                        getModBlock("stone_crystal_floor_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(crystal_floor_paths);

        crystal_floor_slabs = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_slab",
                        getModBlock("stone_crystal_floor_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(crystal_floor_slabs);

        crystal_floors = StoneZoneEntrySet.of(StoneType.class, "crystal_floor",
                        getModBlock("stone_crystal_floor"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                .addTexture(modRes("block/stone_crystal_floor"), StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(crystal_floors);

        mossy_crystal_floor_paths = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_path", "mossy",
                        getModBlock("mossy_stone_crystal_floor_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_crystal_floor_paths);

        mossy_crystal_floor_slabs = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_slab", "mossy",
                        getModBlock("mossy_stone_crystal_floor_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                //TEXTURES: mossy_stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_crystal_floor_slabs);

        mossy_crystal_floors = StoneZoneEntrySet.of(StoneType.class, "crystal_floor", "mossy",
                        getModBlock("mossy_stone_crystal_floor"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Block(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_BRICKS) //REASON: recipes
                .addTextureM(modRes("block/mossy_stone_crystal_floor"),
                        StoneZone.res("block/mcp/mossy_stone_crystal_floor_m"),
                        StonePaletteStrategies.BRICKS_STANDARD)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_crystal_floors);

        strewn_rocky_paths = StoneZoneEntrySet.of(StoneType.class, "strewn_rocky_path",
                        getModBlock("stone_strewn_rocky_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren(BRICKS) //REASON: recipes
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(strewn_rocky_paths);

        mossy_strewn_rocky_paths = StoneZoneEntrySet.of(StoneType.class, "strewn_rocky_path", "mossy",
                        getModBlock("mossy_stone_strewn_rocky_path"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(getMossyCobblestoneSafe(stoneType)))
                )
                .requiresChildren(MOSSY_COBBLESTONE, MOSSY_BRICKS) //REASON: textures, recipes
                //TEXTURES: mossy_cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_strewn_rocky_paths);

    }

    public Block getMossyCobblestoneSafe(StoneType stoneType) {
        var block = stoneType.getBlockOfThis("mossy_cobblestone");
        return (Objects.nonNull(block)) ? block : stoneType.stone;
    }

}
