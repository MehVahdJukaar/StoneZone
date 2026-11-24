package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.ExtraModelConfiguration;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.misc.CompatSpritesHelper;
import net.minecraft.core.Holder;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.*;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.SMOOTH;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;
import static net.mehvahdjukaar.stone_zone.misc.CompatSpritesHelper.CUSTOM_MODEL_ID;
import static net.mehvahdjukaar.stone_zone.misc.CompatSpritesHelper.tintedStoneType;
import static net.mehvahdjukaar.stone_zone.misc.ResourcesUtils.getChildModelId;

public class StoneZoneEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    protected StoneZoneEntrySet(Class<T> type,
                                String name, @Nullable String prefix,
                                BiFunction<T, BlockBehaviour.Properties, B> blockSupplier,
                                Function<T, BlockBehaviour.Properties> propertiesFactory,
                                Supplier<@Nullable B> baseBlock,
                                Supplier<T> baseType,
                                @NotNull Supplier<Holder<CreativeModeTab>> tab,
                                TabAddMode tabMode,
                                LootTableMode lootMode,
                                @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                                @Nullable SimpleEntrySet.ITileHolder<?> tileFactory,
                                @Nullable Object renderType,
                                @Nullable
                                BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> paletteSupplier,
                                @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                boolean mergedPalette, boolean copyTint,
                                Predicate<T> condition,
                                ExtraModelConfiguration extraModelConfig
    ) {
        super(type, name, prefix, blockSupplier, propertiesFactory, baseBlock, baseType, tab, tabMode, lootMode, itemFactory, tileFactory,
                renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition, extraModelConfig);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, BiFunction<T, BlockBehaviour.Properties, B> blockFactory) {
        return new Builder<>(type, name, prefix, baseType, baseBlock, blockFactory);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, Supplier<B> baseBlock, Supplier<T> baseType, BiFunction<T, BlockBehaviour.Properties, B> blockFactory) {
        return new Builder<>(type, name, null, baseType, baseBlock, blockFactory);
    }

    @Override
    public void generateModels(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        ResourcesUtils.generateStandardBlockFiles(manager, sink, blocks, baseType.get(),
                makeModelTransformer(module, manager), makeBlockStateTransformer(module, manager), this.modelConfiguration
        );
        ResourcesUtils.generateStandardItemModels(manager, sink, items, baseType.get(), this.getBaseBlock().asItem(),
                makeModelTransformer(module, manager), this.modelConfiguration
        );
    }

    @Override
    protected BlockTypeResTransformer<T> makeModelTransformer(SimpleModule module, ResourceManager manager) {
        String oldTypeName = baseType.get().getTypeName();
        BlockTypeResTransformer<T> modelTransformer = BlockTypeResTransformer.create(module.getModId(), manager);

        if (this.extraModelTransform != null) this.extraModelTransform.accept(modelTransformer);

        // Modifying models' filename & ResourceLocation
        modelTransformer.setIDModifier((s, blockId, blockType) ->
            BlockTypeResTransformer.replaceFullGenericType(s, blockType, blockId, oldTypeName, null, 2)
        );

        // Modifying the model files' content
        modelTransformer.addModifier((s, blockId, blockType) -> {
            s = BlockTypeResTransformer.replaceFullGenericType(s, blockType, blockId, oldTypeName, module.getModId(), "block(?!.*(?:parent|template))");

            if (tintedStoneType.containsKey(blockType.getId())) {
                String stonePath = tintedStoneType.get(blockType.getId()).getFirst();
                String bricksPath = tintedStoneType.get(blockType.getId()).getSecond();
                return s.replace("minecraft:block/" + oldTypeName + "_bricks", StoneZone.MOD_ID + stonePath)
                        .replace("minecraft:block/" + oldTypeName, StoneZone.MOD_ID + bricksPath);
            }
            return s;
        });

        modelTransformer = CompatSpritesHelper.replaceStoneTextures(modelTransformer, oldTypeName);

        //NOTE: do not use super.makeModelTransformer(module, manager) because it will screw up the model files from Macaw's Bridges
        // Stone Zone is better off not using EveryCompat's ModelTransformer
        return modelTransformer;
    }

    @Override
    protected BlockTypeResTransformer<T> makeBlockStateTransformer(SimpleModule module, ResourceManager manager) {
        String oldTypeName = baseType.get().getTypeName();
        BlockTypeResTransformer<T> transformer = BlockTypeResTransformer.create(module.getModId(), manager);

        transformer.addModifier((s, blockId, blockType) -> {
                    s = s.replace("stonebrick", "stone_brick");
                    BlockTypeResTransformer.replaceFullGenericType(s, blockType, blockId, oldTypeName, module.getModId(), "block");
                    return s;
                })
                .addModifier((json, blockId, stoneType) -> {
                    if (CUSTOM_MODEL_ID.containsKey(blockId)) {
                        return json
                                .replace("minecraft:block/" + oldTypeName, getChildModelId(STONE, stoneType, blockId))
                                .replace("minecraft:block/" + oldTypeName + "_bricks", getChildModelId(BRICKS, stoneType, blockId))
                                .replace("minecraft:block/smooth_" + oldTypeName, getChildModelId(SMOOTH, stoneType, blockId));
                    }
                    return json;
                })
                .replaceBlockType(oldTypeName)
                .IDReplaceType(oldTypeName);

        transformer = CompatSpritesHelper.replaceStoneTextures(transformer, oldTypeName);

        return transformer;
    }

    //!! SUB-CLASS
//    @SuppressWarnings("DataFlowIssue") // McMetaFile is nullable
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected Builder(Class<T> type, String name, @Nullable String prefix,
                          Supplier<T> baseType, Supplier<B> baseBlock,
                          BiFunction<T, BlockBehaviour.Properties, B> blockFactory)
        {

            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        @Override
        public StoneZoneEntrySet<T, B> build() {
            if (this.addToTab && this.tab == null) {
                throw new IllegalStateException("Tab for: " + getEntryName() + " was null!");
            } else {
                StoneZoneEntrySet<T, B> e = new StoneZoneEntrySet<>(this.type, this.name, this.prefix,
                        this.blockFactory,
                        this.propertiesFactory,
                        this.baseBlock,
                        this.baseType, this.tab, this.tabMode, this.lootMode, this.itemFactory,
                        this.tileHolder, this.renderType, null, this.extraModelTransform,
                        this.useMergedPalette, this.copyTint,
                        this.condition, this.extraModelConfig
                );
                e.recipeLocations.addAll(this.recipes);
                e.tags.putAll(this.tags);
                e.textures.addAll(this.textures);

                return e;
            }
        }

    }

}
