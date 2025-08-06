package net.mehvahdjukaar.stone_zone.api.example;

import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * StoneZoneModule is subclass of SimpleModule
 * StoneZoneEntry is subclass of SimpleEntrySet
 * <p>
 * There are 2 BlockTypes that StoneZone can detect: StoneType, MudType
 * You could name the class "StoneModule" for supporting StoneType Blocks and "WoodGoodModule" for WoodType & LeavesType
**/
@SuppressWarnings("unused")
public class ExampleModule extends StoneZoneModule {
/// Make sure it's StoneZoneSimpleModule, not SimpleModule

    /// For Blocks - NOTE: it's using SimpleEntrySet
    public final SimpleEntrySet<StoneType, Block> sampleBlock, sampleBlock_2;

    /// For Items - NOTE: it's using ItemOnlyEntrySet
    public final ItemOnlyEntrySet<WoodType, Item> sampleItem;

    public ExampleModule(String modId) {
        super(modId, "tfc"); // If the mod's name is TerraFirmaCraft, then you could use 3 upper letter

        //  your mod's tab or minecraft's tab can be used for setTabKey() - it can use either ResourceKey or ResourceLocation
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;
        ResourceLocation yourModTab = modRes("twig");

        /// NOTE: it's using StoneZoneEntrySet
        sampleBlock_2 = StoneZoneEntrySet.of(StoneType.class,"suffix", "prefix",
                        getModBlock("oak_table"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                .build();
        this.addEntry(sampleBlock_2);

        /// NOTE: it's using StoneZoneEntrySet
        sampleBlock = StoneZoneEntrySet.of(StoneType.class,"table",
                        getModBlock("oak_table"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Block(Utils.copyPropertySafe(stoneType.stone))
                )
                ///OPTIONAL: Without this, the default texture is stone's texture
                .createPaletteFromStoneChild("bricks") // in case of no "childkey", then "stone" will be used
                .createPaletteFromBricks() // Is same as above but in case of no "bricks", then "stone" will be used

                ///OPTIONAL: Check if a StoneType has the children required, then block will be generated
                .requiresChildren("slab", "other_childkey") //REASON: can be for recipes or textures
                .requiresFromMap(sampleBlock_2.blocks) // If your block required another block for crafting or texturing
                //NOTE: sampleBlock_2 has to be above of this EntrySet for .requiresFromMap to work properly

                ///OPTIONAL: Add the block to EntityBlockType
                .addTile(getModTile("id_of_EntityType"))

                ///OPTIONAL: Adding block's textures to be generated
                .addTexture(modRes("block/oak_table"))
                .addTexture(ResourceLocation.parse("twigs:block/oak_table_bottom"))
                .addTextureM(modRes("block/oak_table_top"), modRes("block/mask/oak_table_top_m")) // If the texture has parts that shouldn't be recolored, the mask (black color) can be used to exclude them

                ///OPTIONAL: Adding tags to the block
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("twigs:tables"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("twigs:tables"), Registries.ITEM)

                ///REQUIRED: Add block to your mod's tab or Minecraft's tab
                .setTabKey(yourModTab)

                ///OPTIONAL: Creating recipes for the block
                .defaultRecipe() // default: new ResourceLocation("twigs:oak_table") via recipes folder
                ///OPTIONAL: if the recipe has a different path unlike above
                .addRecipe(modRes("path/to/recipeFile")) // Do not use "recipes/"

                ///OPTIONAL: Special cases
                .copyParentDrop() // copy the loot_table of the baseBlock (oak_table)
                .setRenderType(RenderLayer.CUTOUT) //USAGE: CUTOUT, CUTOUT_MIPPED, SOLID, TRANSLUCENT

                ///OPTIONAL: Exclude a BlockType from being generated
                .excludeBlockTypes("minecraft", "stone", "diorite")
                .excludeBlockTypes("minecraft:(stone|diorite)") // Is same as above
                .excludeBlockTypes("minecraft:.*") // Exclude all of Vanilla StoneType

                .build();
        this.addEntry(sampleBlock);

        sampleItem = ItemOnlyEntrySet.builder(WoodType.class,"table",
                        getModItem("oak_table"), ()-> WoodTypeRegistry.OAK_TYPE,
                        w -> new Item(new Item.Properties())
                )
                .addTexture(modRes("item/itemTexture"))
                .addTag(ResourceLocation.parse("twigs:tables"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(sampleItem);
    }
}