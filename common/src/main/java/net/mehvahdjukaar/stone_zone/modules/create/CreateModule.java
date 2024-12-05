package net.mehvahdjukaar.stone_zone.modules.create;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.modules.SZModule;
import net.mehvahdjukaar.stone_zone.type.StoneType;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

//SUPPORT: v0.5.1+
public class CreateModule extends SZModule {

    public final SimpleEntrySet<StoneType, Block> cut_andesite;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_stairs;
    public final SimpleEntrySet<StoneType, Block> cut_andesite_slab;

    public CreateModule(String modId) {
        super(modId, "");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        cut_andesite = SimpleEntrySet.builder(StoneType.class, "", "cut",
                        getModBlock("cut_andesite"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .addTexture(modRes("block/palettes/stone_types/cut/andesite_cut"))
                .setTabKey(tab)
//                .defaultRecipe()
                .build();
        this.addEntry(cut_andesite);

        cut_andesite_stairs = SimpleEntrySet.builder(StoneType.class, "slab", "cut",
                        getModBlock("cut_andesite_slab"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new StairBlock(stoneType.stone.defaultBlockState(), Utils.copyPropertySafe(stoneType.stone)))
                //TEXTURES: Using cut_andesite's from above
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
//                .defaultRecipe()
                .build();

        this.addEntry(cut_andesite_stairs);

        cut_andesite_slab = SimpleEntrySet.builder(StoneType.class, "stairs", "cut",
                        getModBlock("cut_andesite_stairs"), () -> StoneTypeRegistry.ANDESITE_TYPE,
                        stoneType -> new SlabBlock(Utils.copyPropertySafe(stoneType.stone)))
                //TEXTURES: Using cut_andesite's from above
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
//                .defaultRecipe()
                .build();
        this.addEntry(cut_andesite_slab);

    }
}