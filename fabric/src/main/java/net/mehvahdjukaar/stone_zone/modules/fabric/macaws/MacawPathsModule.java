package net.mehvahdjukaar.stone_zone.modules.fabric.macaws;

import com.mcwpaths.kikoz.objects.PathBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneSimpleModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;


//SUPPORT v1.0.4+
public class MacawPathsModule extends StoneZoneSimpleModule {

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
                        getModBlock("stone_running_bond_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(running_bond_paths);

        running_bond_slabs = StoneZoneEntrySet.of(StoneType.class, "running_bond_slab",
                        getModBlock("stone_running_bond_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(running_bond_slabs);

        running_bonds = StoneZoneEntrySet.of(StoneType.class, "running_bond",
                        getModBlock("stone_running_bond"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/stone_running_bond"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(running_bonds);

        mossy_running_bond_paths = StoneZoneEntrySet.of(StoneType.class, "running_bond_path", "mossy",
                        getModBlock("mossy_stone_running_bond_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_running_bond_paths);

        mossy_running_bond_slabs = StoneZoneEntrySet.of(StoneType.class, "running_bond_slab", "mossy",
                        getModBlock("mossy_stone_running_bond_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_running_bonds
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_running_bond_slabs);

        mossy_running_bonds = StoneZoneEntrySet.of(StoneType.class, "running_bond", "mossy",
                        getModBlock("mossy_stone_running_bond"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTextureM(modRes("block/mossy_stone_running_bond"), StoneZone.res("block/mcp/mossy_stone_running_bond_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_running_bonds);

        windmill_weave_paths = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_path",
                        getModBlock("stone_windmill_weave_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(windmill_weave_paths);

        windmill_weave_slabs = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_slab",
                        getModBlock("stone_windmill_weave_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(windmill_weave_slabs);

        windmill_weaves = StoneZoneEntrySet.of(StoneType.class, "windmill_weave",
                        getModBlock("stone_windmill_weave"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/stone_windmill_weave"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(windmill_weaves);

        mossy_windmill_weave_paths = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_path", "mossy",
                        getModBlock("mossy_stone_windmill_weave_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_windmill_weave_paths);

        mossy_windmill_weave_slabs = StoneZoneEntrySet.of(StoneType.class, "windmill_weave_slab", "mossy",
                        getModBlock("mossy_stone_windmill_weave_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_windmill_weaves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_windmill_weave_slabs);

        mossy_windmill_weaves = StoneZoneEntrySet.of(StoneType.class, "windmill_weave", "mossy",
                        getModBlock("mossy_stone_windmill_weave"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTextureM(modRes("block/mossy_stone_windmill_weave"), StoneZone.res("block/mcp/mossy_stone_windmill_weave_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_windmill_weaves);

        flagstone_paths = StoneZoneEntrySet.of(StoneType.class, "flagstone_path",
                        getModBlock("stone_flagstone_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(flagstone_paths);

        flagstone_slabs = StoneZoneEntrySet.of(StoneType.class, "flagstone_slab",
                        getModBlock("stone_flagstone_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(flagstone_slabs);

        flagstones = StoneZoneEntrySet.of(StoneType.class, "flagstone",
                        getModBlock("stone_flagstone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/stone_flagstone"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(flagstones);

        mossy_flagstone_paths = StoneZoneEntrySet.of(StoneType.class, "flagstone_path", "mossy",
                        getModBlock("mossy_stone_flagstone_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_flagstone_paths);

        mossy_flagstone_slabs = StoneZoneEntrySet.of(StoneType.class, "flagstone_slab", "mossy",
                        getModBlock("mossy_stone_flagstone_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_flagstones
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_flagstone_slabs);

        mossy_flagstones = StoneZoneEntrySet.of(StoneType.class, "flagstone", "mossy",
                        getModBlock("mossy_stone_flagstone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTextureM(modRes("block/mossy_stone_flagstone"), StoneZone.res("block/mcp/mossy_stone_flagstone_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_flagstones);

        crystal_floor_paths = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_path",
                        getModBlock("stone_crystal_floor_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(crystal_floor_paths);

        crystal_floor_slabs = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_slab",
                        getModBlock("stone_crystal_floor_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(crystal_floor_slabs);

        crystal_floors = StoneZoneEntrySet.of(StoneType.class, "crystal_floor",
                        getModBlock("stone_crystal_floor"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/stone_crystal_floor"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(crystal_floors);

        mossy_crystal_floor_paths = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_path", "mossy",
                        getModBlock("mossy_stone_crystal_floor_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_crystal_floor_paths);

        mossy_crystal_floor_slabs = StoneZoneEntrySet.of(StoneType.class, "crystal_floor_slab", "mossy",
                        getModBlock("mossy_stone_crystal_floor_slab"), StoneTypeRegistry::getStoneType,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_stone_crystal_floors
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_crystal_floor_slabs);

        mossy_crystal_floors = StoneZoneEntrySet.of(StoneType.class, "crystal_floor", "mossy",
                        getModBlock("mossy_stone_crystal_floor"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromBricks()
                .requiresChildren("mossy_bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTextureM(modRes("block/mossy_stone_crystal_floor"), StoneZone.res("block/mcp/mossy_stone_crystal_floor_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_crystal_floors);

        strewn_rocky_paths = StoneZoneEntrySet.of(StoneType.class, "strewn_rocky_path",
                        getModBlock("stone_strewn_rocky_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren("bricks") //REASON: recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strewn_rocky_paths);

        mossy_strewn_rocky_paths = StoneZoneEntrySet.of(StoneType.class, "strewn_rocky_path", "mossy",
                        getModBlock("mossy_stone_strewn_rocky_path"), StoneTypeRegistry::getStoneType,
                        stoneType -> new PathBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .requiresChildren("mossy_cobblestone", "mossy_bricks") //REASON: textures, recipes
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: mossy_cobblestone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_strewn_rocky_paths);

    }

}
