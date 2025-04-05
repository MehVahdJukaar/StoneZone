package net.mehvahdjukaar.stone_zone.modules.forge.additional_lights;

import com.mgen256.al.blocks.*;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;


//SUPPORT: v
public class AdditionalLightsModule extends SZModule {

    public final SimpleEntrySet<StoneType, Block> al_lamp;

    ///: the class, ModBlockList need to have an interface class, below can't be supported for now
//    public final SimpleEntrySet<StoneType, Block> al_torch;
//    public final SimpleEntrySet<StoneType, Block> al_wall_torch;

//    public final SimpleEntrySet<StoneType, Block> al_torch_bricks;
//    public final SimpleEntrySet<StoneType, Block> al_wall_torch_bricks;

//    public final SimpleEntrySet<StoneType, Block> al_torch_mossy_bricks;
//    public final SimpleEntrySet<StoneType, Block> al_wall_torch_mossy_bricks;

//    public final SimpleEntrySet<StoneType, Block> al_torch_smooth;
//    public final SimpleEntrySet<StoneType, Block> al_wall_torch_smooth;

    public final SimpleEntrySet<StoneType, Block> standing_torch_s_bricks;
    public final SimpleEntrySet<StoneType, Block> standing_torch_s_mossy_bricks;
    public final SimpleEntrySet<StoneType, Block> standing_torch_s_smooth;
    public final SimpleEntrySet<StoneType, Block> standing_torch_s;

    public final SimpleEntrySet<StoneType, Block> stnading_torch_l_bricks;
    public final SimpleEntrySet<StoneType, Block> standing_torch_l_mossy_bricks;
    public final SimpleEntrySet<StoneType, Block> standing_torch_l_smooth;
    public final SimpleEntrySet<StoneType, Block> standing_torch_l;

    public final SimpleEntrySet<StoneType, Block> firepit_s_bricks;
    public final SimpleEntrySet<StoneType, Block> firepit_s_mossy_bricks;
    public final SimpleEntrySet<StoneType, Block> firepit_s_smooth;
    public final SimpleEntrySet<StoneType, Block> firepit_s;

    public final SimpleEntrySet<StoneType, Block> firepit_l_bricks;
    public final SimpleEntrySet<StoneType, Block> firepit_l_mossy_bricks;
    public final SimpleEntrySet<StoneType, Block> firepit_l_smooth;
    public final SimpleEntrySet<StoneType, Block> firepit_l;

    public final SimpleEntrySet<StoneType, Block> polished_standing_torch;
    public final SimpleEntrySet<StoneType, Block> polished_standing_torch_l;
    public final SimpleEntrySet<StoneType, Block> polished_firepit;
    public final SimpleEntrySet<StoneType, Block> polished_firepit_l;

    public AdditionalLightsModule(String modId) {
        super(modId, "al");
        ResourceLocation tab = modRes("creative_tab");

        al_lamp = StonezoneEntrySet.of(StoneType.class, "","al_lamp",
                        getModBlock("al_lamp_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALLamp(stoneType.stone)
                )
                //TEXTURES: stone
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(al_lamp);

/*
        al_torch = StonezoneEntrySet.of(StoneType.class, "al_torch",
                        getModBlock("al_torch_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALTorch(stoneType.stone)
                )
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(al_torch);

        al_wall_torch = StonezoneEntrySet.of(StoneType.class, "al_wall_torch",
                        getModBlock("al_wall_torch_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALTorch_Wall(stoneType.stone, al_torch.blocks.get(stoneType))
                )
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .noItem()
                .defaultRecipe()
                .build();
        this.addEntry(al_wall_torch);

        al_torch_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "al_torch",
                        getModBlock("al_torch_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALTorch(stoneType.bricksOrStone())
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(al_torch_bricks);

        al_wall_torch_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "al_wall_torch",
                        getModBlock("al_wall_torch_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALTorch(stoneType.bricksOrStone())
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .noItem()
                .defaultRecipe()
                .build();
        this.addEntry(al_wall_torch_bricks);

        al_torch_mossy_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "al_torch_mossy",
                        getModBlock("al_torch_mossy_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALTorch(Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(al_torch_mossy_bricks);

        al_wall_torch_mossy_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "al_wall_torch_mossy",
                        getModBlock("al_wall_torch_mossy_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new ALTorch(Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .noItem()
                .defaultRecipe()
                .build();
        this.addEntry(al_wall_torch_mossy_bricks);

        al_torch_smooth = StonezoneEntrySet.of(StoneType.class, "al_torch_smooth",
                        getModBlock("al_torch_smooth_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(stoneType.stone)
                )
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(al_torch_smooth);

        al_wall_torch_smooth = StonezoneEntrySet.of(StoneType.class, "al_wall_torch_smooth",
                        getModBlock("al_wall_torch_smooth_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(stoneType.stone)
                )
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .noItem()
                .defaultRecipe()
                .build();
        this.addEntry(al_wall_torch_smooth);
*/

        standing_torch_s_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "standing_torch_s",
                        getModBlock("standing_torch_s_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_S(stoneType.stone)
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_s_bricks);

        standing_torch_s_mossy_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "standing_torch_s_mossy",
                        getModBlock("standing_torch_s_mossy_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_S(Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_s_mossy_bricks);

        standing_torch_s_smooth = StonezoneEntrySet.of(StoneType.class, "","standing_torch_s_smooth",
                        getModBlock("standing_torch_s_smooth_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_S(Objects.requireNonNull(stoneType.getBlockOfThis("smooth")))
                )
                .requiresChildren("smooth") //REASON: textures, recipes
                //TEXTURES: smooth
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_s_smooth);

        standing_torch_s = StonezoneEntrySet.of(StoneType.class, "","standing_torch_s",
                        getModBlock("standing_torch_s_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_S(stoneType.stone)
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_s);

        stnading_torch_l_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "standing_torch_l",
                        getModBlock("standing_torch_l_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_L(Objects.requireNonNull(stoneType.getBlockOfThis("bricks")))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stnading_torch_l_bricks);

        standing_torch_l_mossy_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "standing_torch_l_mossy",
                        getModBlock("standing_torch_l_mossy_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_L(Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_l_mossy_bricks);

        standing_torch_l_smooth = StonezoneEntrySet.of(StoneType.class, "","standing_torch_l_smooth",
                        getModBlock("standing_torch_l_smooth_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_L(Objects.requireNonNull(stoneType.getBlockOfThis("smooth")))
                )
                .requiresChildren("smooth") //REASON: textures, recipes
                //TEXTURES: smooth
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_l_smooth);

        standing_torch_l = StonezoneEntrySet.of(StoneType.class,"","standing_torch_l",
                        getModBlock("standing_torch_l_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new StandingTorch_L(stoneType.stone)
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(standing_torch_l);

        firepit_s_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "fire_pit_s",
                        getModBlock("fire_pit_s_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_S(Objects.requireNonNull(stoneType.getBlockOfThis("bricks")))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_s_bricks);

        firepit_s_mossy_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "fire_pit_s_mossy",
                        getModBlock("fire_pit_s_mossy_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_S(Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_s_mossy_bricks);

        firepit_s_smooth = StonezoneEntrySet.of(StoneType.class,"","fire_pit_s_smooth",
                        getModBlock("fire_pit_s_smooth_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_S(Objects.requireNonNull(stoneType.getBlockOfThis("smooth")))
                )
                .requiresChildren("smooth") //REASON: textures, recipes
                //TEXTURES: smooth
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_s_smooth);

        firepit_s = StonezoneEntrySet.of(StoneType.class,"","fire_pit_s",
                        getModBlock("fire_pit_s_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_S(stoneType.stone)
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_s);

        firepit_l_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "fire_pit_l",
                        getModBlock("fire_pit_l_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_L(Objects.requireNonNull(stoneType.getBlockOfThis("bricks")))
                )
                .requiresChildren("bricks") //REASON: textures, recipes
                //TEXTURES: bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_l_bricks);

        firepit_l_mossy_bricks = StonezoneEntrySet.of(StoneType.class, "bricks", "fire_pit_l_mossy",
                        getModBlock("fire_pit_l_mossy_stone_bricks"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_L(Objects.requireNonNull(stoneType.getBlockOfThis("mossy_bricks")))
                )
                .requiresChildren("mossy_bricks") //REASON: textures, recipes
                //TEXTURES: mossy_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_l_mossy_bricks);

        firepit_l_smooth = StonezoneEntrySet.of(StoneType.class,"","fire_pit_l_smooth",
                        getModBlock("fire_pit_l_smooth_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_L(Objects.requireNonNull(stoneType.getBlockOfThis("smooth")))
                )
                .requiresChildren("smooth") //REASON: textures, recipes
                //TEXTURES: smooth
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_l_smooth);

        firepit_l = StonezoneEntrySet.of(StoneType.class,"","fire_pit_l",
                        getModBlock("fire_pit_l_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new FirePit_L(stoneType.stone)
                )
                //TEXTURES: stone
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(firepit_l);

//!! ANDESITE
        polished_standing_torch = StonezoneEntrySet.of(StoneType.class,"","standing_torch_s_polished",
                        getModBlock("standing_torch_s_polished_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new StandingTorch_S(stoneType.stone)
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(polished_standing_torch);

        polished_standing_torch_l = StonezoneEntrySet.of(StoneType.class,"","standing_torch_l_polished",
                        getModBlock("standing_torch_l_polished_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new StandingTorch_L(stoneType.stone)
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(polished_standing_torch_l);

        polished_firepit = StonezoneEntrySet.of(StoneType.class,"","fire_pit_s_polished",
                        getModBlock("fire_pit_s_polished_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FirePit_S(stoneType.stone)
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(polished_firepit);

        polished_firepit_l = StonezoneEntrySet.of(StoneType.class,"","fire_pit_l_polished",
                        getModBlock("fire_pit_l_polished_andesite"), StoneTypeRegistry::getAndesiteType,
                        stoneType -> new FirePit_L(stoneType.stone)
                )
                .requiresChildren("polished") //REASON: textures, recipes
                //TEXTURES: polished
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(polished_firepit_l);

    }


}