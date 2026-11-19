package net.mehvahdjukaar.stone_zone.modules.stone_chest;

import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestBlock;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.stone_zone.common_classes.CompatChestBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;
import static net.mehvahdjukaar.stone_zone.common_classes.CompatChestTexture.generateChestTexture;


///SUPPORT: v1.0.0+
public class StoneChestModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> chests;
    public final ItemOnlyEntrySet<StoneType, Item> parts;

    public StoneChestModule(String modId) {
        super(modId, "sc");
        Supplier<CreativeModeTab> tab = getTab(CreativeModeTabs.FUNCTIONAL_BLOCKS);

        chests = StoneZoneEntrySet.of(StoneType.class, "","chest",
                        getModBlock("chest_andesite"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new CompatChestBlock(this::getTile, properties)
                )
                .addTile(getModTile("chest_tile"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(UtilityTag.commonTag("chests"), Registries.BLOCK, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chests);

        parts = ItemOnlyEntrySet.builder(StoneType.class, "", "part",
                        getModItem("part_andesite"), () -> VanillaStoneTypes.ANDESITE,
                        (_, properties) -> new Item(properties)
                )
                .addTextureM(modRes("item/part_andesite"),
                        StoneZone.res("item/sc/part_stone_m"),
                        customPalette)
                .addModelTransform(m -> m.addModifier(
                        (s, blockId, stoneType) ->
                                s.replace("\"stonechest:item/part_andesite\"",
                                        "\"" + stoneType.createFullIdWith(StoneZone.MOD_ID, "item", shortenedId(), "part", "") + "\"")
                ))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(parts);

    }

    public static final PaletteStrategy customPalette = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, STONE, null,
                    p -> {
                        while (p.size() > 4) p.reduceUp();
                    }));

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

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                            CLIENT                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    @Override
    public CompatModuleClient<?> createClient() {
        return new Client();
    }

    private class Client extends SimpleModuleClient<StoneChestModule> {

        private Client() {
            super(StoneChestModule.this);
        }

        // Registry
        @Override
        @OnlyIn(Dist.CLIENT)
        public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
            CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        }

        @Override
        // Textures
        public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
            super.addDynamicClientResources(executor);

            executor.accept((manager, sink) ->

                    chests.blocks.forEach((stoneType, block) -> {

                        // SINGLE
                        generateChestTexture(sink, manager, shortenedId(), stoneType,
                                modRes("entity/chest/andesite"),
                                StoneZone.res("entity/sc/stone_m"),
                                StoneZone.res("entity/sc/stone_o"),
                                null
                        );
                        // LEFT
                        generateChestTexture(sink, manager, shortenedId(), stoneType,
                                modRes("entity/chest/andesite_left"),
                                StoneZone.res("entity/sc/stone_left_m"),
                                StoneZone.res("entity/sc/stone_left_o"),
                                null
                        );
                        // RIGHT
                        generateChestTexture(sink, manager, shortenedId(), stoneType,
                                modRes("entity/chest/andesite_right"),
                                StoneZone.res("entity/sc/stone_right_m"),
                                StoneZone.res("entity/sc/stone_right_o"),
                                null
                        );

                    })
            );
        }
    }
}