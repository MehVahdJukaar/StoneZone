package net.mehvahdjukaar.stone_zone.modules.forge.stone_chest;

import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestBlock;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import static net.mehvahdjukaar.stone_zone.common_classes.CompatChestTexture.generateChestTexture;


//SUPPORT: v1.0.0+
public class StoneChestModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> chests;
    public final ItemOnlyEntrySet<StoneType, Item> parts;

    public StoneChestModule(String modId) {
        super(modId, "sc");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        chests = StoneZoneEntrySet.of(StoneType.class, "","chest",
                        getModBlock("chest_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new CompatChestBlock(this::getTile, Utils.copyPropertySafe(stoneType.stone))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .addTile(VariantChestBlockEntity::new)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chests);

        parts = ItemOnlyEntrySet.builder(StoneType.class, "", "part",
                        getModItem("part_stone"), StoneTypeRegistry::getStoneType,
                        stoneType -> new Item(new Item.Properties())
                )
                .copyParentTint()
                .createPaletteFromChild(p -> {
                    while (p.size() > 4) p.reduceUp();
                }, "stone")
                .addTextureM(modRes("item/part_stone"), StoneZone.res("item/sc/part_stone_m"))
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, stoneType) ->
                                s.replace("\"stonechest:item/part_stone\"",
                                        "\""+ StoneZone.res("item/"+shortenedId()+"/"+ stoneType.getAppendableIdWith("part", "")) +"\"")
                ))
                .setTabKey(tab)
//                .addRecipe(modRes("part_stone"))
                .defaultRecipe()
                .build();
        this.addEntry(parts);

    }

    // GetTiles
    private BlockEntityType<? extends ChestBlockEntity> getTile() {
        return chests.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity
    private class VariantChestBlockEntity extends CompatChestBlockEntity {
        public VariantChestBlockEntity(BlockPos pos, BlockState state) {
            super(chests.getTile(), pos, state);
        }
    }

    // Registry
    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        chests.blocks.forEach((stoneType, block) -> {

            // SINGLE
            generateChestTexture(handler, manager, shortenedId(), stoneType, block,
                    modRes("entity/chest/stone"),
                    StoneZone.res("entity/sc/stone_m"),
                    StoneZone.res("entity/sc/stone_o"),
                    null
            );
            // LEFT
            generateChestTexture(handler, manager, shortenedId(), stoneType, block,
                    modRes("entity/chest/stone_left"),
                    StoneZone.res("entity/sc/stone_left_m"),
                    StoneZone.res("entity/sc/stone_left_o"),
                    null
            );
            // RIGHT
            generateChestTexture(handler, manager, shortenedId(), stoneType, block,
                    modRes("entity/chest/stone_right"),
                    StoneZone.res("entity/sc/stone_right_m"),
                    StoneZone.res("entity/sc/stone_right_o"),
                    null
            );

        });
    }
}