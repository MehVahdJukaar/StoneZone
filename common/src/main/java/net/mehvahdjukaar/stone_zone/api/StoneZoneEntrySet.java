package net.mehvahdjukaar.stone_zone.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.ModelConfiguration;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.*;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.addTagToAllBlocks;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;
import static net.mehvahdjukaar.stone_zone.misc.ResourceUtils.getChildModelId;

public class StoneZoneEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    protected StoneZoneEntrySet(Class<T> type, String name, @Nullable String prefix, Function<T, B> blockSupplier,
                                Supplier<B> baseBlock, Supplier<T> baseType,
                                @NotNull Supplier<ResourceKey<CreativeModeTab>> tab,
                                TabAddMode tabMode, LootTableMode lootMode,
                                @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                                @Nullable ITileHolder tileFactory, @Nullable Object renderType,
                                @Nullable BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> paletteSupplier,
                                @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                boolean mergedPalette, boolean copyTint,
                                Predicate<T> condition, ModelConfiguration modelConfig
    ) {
        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tabMode, lootMode, itemFactory, tileFactory,
                renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition, modelConfig);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new Builder<>(type, name, prefix, baseType, baseBlock, blockSupplier);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new Builder<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    @Override
    protected BlockTypeResTransformer<T> makeModelTransformer(SimpleModule module, ResourceManager manager) {
        String nameBaseStone = baseType.get().getTypeName();
        return BlockTypeResTransformer.<T>create(module.getModId(), manager)
                //these need to be run first. idk why but its like that
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseStone, STONE)
                .replaceWithTextureFromChild("minecraft:block/cobblestone", COBBLESTONE)
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseStone + "_bricks", BRICKS)
                .replaceWithTextureFromChild("minecraft:block/smooth_" + nameBaseStone, SMOOTH)
                .replaceWithTextureFromChild("minecraft:block/smooth_" + nameBaseStone + "_slab_side", SMOOTH_SLAB)
                .replaceWithTextureFromChild("minecraft:block/polished_" + nameBaseStone, POLISHED)
                .replaceWithTextureFromChild("minecraft:block/mossy_" + nameBaseStone + "_bricks", MOSSY_BRICKS)
                .andThen(super.makeModelTransformer(module, manager));
    }


    @Override
    protected BlockTypeResTransformer<T> makeBlockStateTransformer(SimpleModule module, ResourceManager manager) {
        String nameBaseStone = baseType.get().getTypeName();
        return BlockTypeResTransformer.<T>create(module.getModId(), manager)
                .replaceWithTextureFromChild("minecraft:block/"+nameBaseStone, STONE)
                .replaceWithTextureFromChild("minecraft:block/polished_"+nameBaseStone, POLISHED)
                .addModifier((s, blockId, stoneType) ->
                        s.replace("minecraft:block/" + nameBaseStone, getChildModelId(STONE, stoneType, blockId)))
                .addModifier((s, blockId, stoneType) ->
                        s.replace("minecraft:block/" + nameBaseStone + "_bricks", getChildModelId(BRICKS, stoneType, blockId)))
                .addModifier((s, blockId, stoneType) ->
                        s.replace("minecraft:block/smooth_" + nameBaseStone, getChildModelId(SMOOTH, stoneType, blockId)))
                .andThen(super.makeBlockStateTransformer(module, manager));
    }

    @Override
    public void generateTags(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        super.generateTags(module, manager, sink);

        /// IMPORTANT: modId must be included in StoneZone's addModToDynamicPack() so the tags will be loaded into world first time
        // Adding tag to a specific StoneType of all generated blocks
        // Architect's Palette
        addTagToAllBlocks(blocks, "wardstone", "architects_palette", "wizard_blocks", true, false, sink);

        // Tinker's Construct
        addTagToAllBlocks(blocks, "seared_stone", "tconstruct", "seared_blocks", true, true, sink);
        addTagToAllBlocks(blocks, "scorched_stone", "tconstruct", "scorched_blocks", true, true, sink);

        // Caverns And Chasms
        addTagToAllBlocks(blocks, "sugilite", "caverns_and_chasms", "static_note_blocks", true, true, sink);
        addTagToAllBlocks(blocks, "cassiterite", "caverns_and_chasms", "deflects_projectiles", true, false, sink);
        addTagToAllBlocks(blocks, "cassiterite", "caverns_and_chasms", "weaker_deflect_velocity", true, false, sink);

    }

    //!! SUB-CLASS
    @SuppressWarnings("DataFlowIssue") // McMetaFile is nullable
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public StoneZoneEntrySet.Builder<T, B> createPaletteFromStone() {
            return (Builder<T, B>) createPaletteFromChild("stone");
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public StoneZoneEntrySet.Builder<T, B> createPaletteFromBricks() {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild(BRICKS) != null) {
                    var paletteAnimation = PaletteStrategies.makePaletteFromChild(blockType, manager, BRICKS, null, p -> {});
                    return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
                }
                var paletteAnimation = PaletteStrategies.makePaletteFromMainChild(blockType, manager);
                return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
            });
            return this;
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public StoneZoneEntrySet.Builder<T, B> createPaletteFromStoneChild(String childKey) {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild(BRICKS) != null) {
                    var paletteAnimation = PaletteStrategies.makePaletteFromChild(blockType, manager, childKey, null, p -> {});
                    return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
                }
                var paletteAnimation = PaletteStrategies.makePaletteFromMainChild(blockType, manager);
                return Pair.of(paletteAnimation.palette(), paletteAnimation.animation());
            });
            return this;
        }

        @Override
        public StoneZoneEntrySet<T, B> build() {
            if (this.tab == null && PlatHelper.isDev()) {
                throw new IllegalStateException("Tab for module " + this.name + " was null!");
            } else {
                // all blocks could have tint as stone could be tinted themselves
                this.copyParentTint();

                StoneZoneEntrySet<T, B> e = new StoneZoneEntrySet<>(this.type, this.name, this.prefix, this.blockFactory, this.baseBlock,
                        this.baseType, this.tab, this.tabMode, this.lootMode, this.itemFactory,
                        this.tileHolder, this.renderType, this.palette, this.extraModelTransform,
                        this.useMergedPalette, this.copyTint,
                        this.condition, this.modelConfig);
                e.recipeLocations.addAll(this.recipes);
                e.tags.putAll(this.tags);
                e.textures.addAll(this.textures);

                return e;
            }
        }

    }

}
