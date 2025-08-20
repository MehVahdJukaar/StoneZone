package net.mehvahdjukaar.stone_zone.modules.forge.buildersaddition;

import com.mrh0.buildersaddition.blocks.Pillar;
import com.mrh0.buildersaddition.blocks.VerticalSlab;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;


//SUPPORT: v20230928a+
public class BuildersAdditionModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> vertical_slab;
    public final SimpleEntrySet<StoneType, Block> smooth_vertical_slab;
    public final SimpleEntrySet<StoneType, Block> bricks_vertical_slab;
    public final SimpleEntrySet<StoneType, Block> mossy_bricks_vertical_slab;
    public final SimpleEntrySet<StoneType, Block> cut_pillar;
    public final SimpleEntrySet<StoneType, Block> cut_smooth_pillar;
    public final SimpleEntrySet<StoneType, Block> cut_bricks_pillar;
    public final SimpleEntrySet<StoneType, Block> cut_mossy_bricks_pillar;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        ResourceLocation tab = modRes("builders_addition_group");

        vertical_slab = StoneZoneEntrySet.of(StoneType.class, "vertical_slab",
                        getModBlock("stone_vertical_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new VerticalSlab(
                                Utils.getID(stoneType.stone).getPath(),
                                stoneType.stone)
                )
                .addCondition(w -> !(PlatHelper.isModLoaded("v_slab_compat") || PlatHelper.isModLoaded("additionalplacements")) )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("vertical_slab/stone_vertical_slab"))
                .addRecipe(modRes("vertical_slab/stone_vertical_slab_stonecutting"))
                .build();
        this.addEntry(vertical_slab);

        smooth_vertical_slab = StoneZoneEntrySet.of(StoneType.class, "vertical_slab", "smooth",
                        getModBlock("smooth_stone_vertical_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new VerticalSlab(
                                Utils.getID(stoneType.getBlockOfThis("smooth")).getPath(),
                                Objects.requireNonNull(stoneType.getBlockOfThis("smooth")))
                )
                .requiresChildren(VanillaRockChildKeys.SMOOTH)  //REASON: recipes, textures
                .addCondition(w -> !(PlatHelper.isModLoaded("v_slab_compat") || PlatHelper.isModLoaded("additionalplacements")) )
                //TEXTURES: smooth, smooth_slab
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.ITEM)
                .setTabKey(tab)
                //RECIPES: missing
                .build();
        this.addEntry(smooth_vertical_slab);

        bricks_vertical_slab = StoneZoneEntrySet.of(StoneType.class, "bricks_vertical_slab",
                        getModBlock("stone_bricks_vertical_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new VerticalSlab(
                                Utils.getID(stoneType.getBlockOfThis("bricks")).getPath(),
                                stoneType.bricksOrStone())
                )
                .requiresChildren(VanillaRockChildKeys.BRICKS) //REASON: recipes, textures
                .addCondition(w -> !(PlatHelper.isModLoaded("v_slab_compat") || PlatHelper.isModLoaded("additionalplacements")) )
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("vertical_slab/stone_bricks_vertical_slab"))
                .addRecipe(modRes("vertical_slab/stone_bricks_vertical_slab_stonecutting"))
                .build();
        this.addEntry(bricks_vertical_slab);

        mossy_bricks_vertical_slab = StoneZoneEntrySet.of(StoneType.class, "bricks_vertical_slab", "mossy",
                        getModBlock("mossy_stone_bricks_vertical_slab"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new VerticalSlab(
                                Utils.getID(stoneType.getBlockOfThis("mossy_bricks")).getPath(),
                                Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren(VanillaRockChildKeys.MOSSY_BRICKS) //REASON: recipes, textures
                .addCondition(w -> !(PlatHelper.isModLoaded("v_slab_compat") || PlatHelper.isModLoaded("additionalplacements")) )
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.BLOCK)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("vertical_slab/mossy_stone_bricks_vertical_slab"))
                .addRecipe(modRes("vertical_slab/mossy_stone_bricks_vertical_slab_stonecutting"))
                .build();
        this.addEntry(mossy_bricks_vertical_slab);

        cut_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "cut",
                        getModBlock("cut_stone_pillar"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Pillar(
                                Utils.getID(stoneType.stone).getPath(),
                                Objects.requireNonNull(stoneType.stone)
                        )
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("pillar/cut_stone_pillar_stonecutting"))
                .build();
        this.addEntry(cut_pillar);

        cut_smooth_pillar = StoneZoneEntrySet.of(StoneType.class, "pillar", "cut_smooth",
                        getModBlock("cut_smooth_stone_pillar"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Pillar(
                                Utils.getID(stoneType.getBlockOfThis("smooth")).getPath(),
                                Objects.requireNonNull(stoneType.getBlockOfThis("smooth"))
                        )
                )
                .requiresChildren("smooth", "smooth_slab") //REASON: recipes, textures
                //TEXTURES: smooth, smooth_slab
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("pillar/cut_smooth_stone_pillar_stonecutting_0"))
                .addRecipe(modRes("pillar/cut_smooth_stone_pillar_stonecutting_1"))
                .build();
        this.addEntry(cut_smooth_pillar);

        cut_bricks_pillar = StoneZoneEntrySet.of(StoneType.class, "bricks_pillar", "cut",
                        getModBlock("cut_stone_bricks_pillar"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Pillar(
                                Utils.getID(stoneType.getBlockOfThis("bricks")).getPath(),
                                Objects.requireNonNull(stoneType.getBlockOfThis("bricks"))
                        )
                )
                .requiresChildren(VanillaRockChildKeys.BRICKS)
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("pillar/cut_stone_bricks_pillar_stonecutting"))
                .addRecipe(modRes("pillar/cut_stone_bricks_pillar_stonecutting_0"))
                .addRecipe(modRes("pillar/cut_stone_bricks_pillar_stonecutting_1"))
                .build();
        this.addEntry(cut_bricks_pillar);

        cut_mossy_bricks_pillar = StoneZoneEntrySet.of(StoneType.class, "bricks_pillar", "cut_mossy",
                        getModBlock("cut_mossy_stone_bricks_pillar"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new Pillar(
                                Utils.getID(stoneType.getBlockOfThis("mossy_bricks")).getPath(),
                                Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks"))
                        )
                )
                .requiresChildren(VanillaRockChildKeys.MOSSY_BRICKS) //REASON: recipes, textures
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("pillar/cut_mossy_stone_bricks_pillar_stonecutting"))
                .build();
        this.addEntry(cut_mossy_bricks_pillar);


    }
}