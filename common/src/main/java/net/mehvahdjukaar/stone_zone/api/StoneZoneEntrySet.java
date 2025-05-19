package net.mehvahdjukaar.stone_zone.api;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.mehvahdjukaar.stone_zone.misc.ModelUtils;
import net.mehvahdjukaar.stone_zone.misc.TintConfiguration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.*;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.addTagToAllBlocks;
import static net.mehvahdjukaar.stone_zone.misc.ResourceUtils.getChildModelId;

public class StoneZoneEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    protected TintConfiguration tintConfiguration;

    protected StoneZoneEntrySet(Class<T> type, String name, @Nullable String prefix, Function<T, B> blockSupplier,
                                Supplier<B> baseBlock, Supplier<T> baseType,
                                @NotNull Supplier<ResourceKey<CreativeModeTab>> tab,
                                TabAddMode tabMode, LootTableMode lootMode,
                                @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                                @Nullable ITileHolder tileFactory, @Nullable Object renderType,
                                @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>,
                                        @Nullable McMetaFile>> paletteSupplier,
                                @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                boolean mergedPalette, TintConfiguration tintConfig, boolean copyTint,
                                Predicate<T> condition) {

        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tabMode, lootMode, itemFactory, tileFactory,
                renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition);
        this.tintConfiguration = tintConfig;
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
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseStone, "stone")
                .replaceWithTextureFromChild("minecraft:block/cobblestone", "cobblestone")
                .replaceWithTextureFromChild("minecraft:block/" + nameBaseStone + "_bricks", "bricks")
                .replaceWithTextureFromChild("minecraft:block/smooth_" + nameBaseStone, "smooth")
                .replaceWithTextureFromChild("minecraft:block/polished_" + nameBaseStone, "polished")
                .replaceWithTextureFromChild("minecraft:block/mossy_" + nameBaseStone + "_bricks", "mossy_bricks")
                // Modifying models' parent & "elements"
                .addModifier((s, blockId, blockType) -> {
                    JsonObject jsonObject = GsonHelper.parse(s);
                    ModelUtils.addTintIndexToModelAndReplaceParent(ResourceLocation.parse("none"), jsonObject, module, nameBaseStone, tintConfiguration);
                    return jsonObject.toString();
                })
                .andThen(super.makeModelTransformer(module, manager));

    }


    @Override
    protected BlockTypeResTransformer<T> makeBlockStateTransformer(SimpleModule module, ResourceManager manager) {
        String nameBaseStone = baseType.get().getTypeName();
        return BlockTypeResTransformer.<T>create(module.getModId(), manager)
                .replaceWithTextureFromChild("minecraft:block/"+nameBaseStone, "stone")
                .replaceWithTextureFromChild("minecraft:block/polished_"+nameBaseStone, "polished")
                .addModifier((s, blockId, stoneType) ->
                        s.replace("minecraft:block/" + nameBaseStone, getChildModelId("stone", stoneType, blockId)))
                .addModifier((s, blockId, stoneType) ->
                        s.replace("minecraft:block/" + nameBaseStone + "_bricks", getChildModelId("bricks", stoneType, blockId)))
                .addModifier((s, blockId, stoneType) ->
                        s.replace("minecraft:block/smooth_" + nameBaseStone, getChildModelId("smooth", stoneType, blockId)))
                .andThen(super.makeBlockStateTransformer(module, manager));
    }

    @Override
    public void generateTags(SimpleModule module, DynamicDataPack pack, ResourceManager manager) {
        super.generateTags(module, pack, manager);

        /// IMPORTANT: modId must be included in StoneZone's addModToDynamicPack() so the tags will be loaded into world first time
        // Adding tag to a specific StoneType of all generated blocks
        // Architect's Palette
        addTagToAllBlocks(blocks, "wardstone", "architects_palette", "wizard_blocks", true, false, pack);

        // Tinker's Construct
        addTagToAllBlocks(blocks, "seared_stone", "tconstruct", "seared_blocks", true, true, pack);
        addTagToAllBlocks(blocks, "scorched_stone", "tconstruct", "scorched_blocks", true, true, pack);

        // Caverns And Chasms
        addTagToAllBlocks(blocks, "sugilite", "caverns_and_chasms", "static_note_blocks", true, true, pack);
        addTagToAllBlocks(blocks, "cassiterite", "caverns_and_chasms", "deflects_projectiles", true, false, pack);
        addTagToAllBlocks(blocks, "cassiterite", "caverns_and_chasms", "weaker_deflect_velocity", true, false, pack);

    }

    //!! SUB-CLASS
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected TintConfiguration tintConfig = TintConfiguration.EMPTY;

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        public StoneZoneEntrySet.Builder<T, B> createPaletteFromStone() {
            return (Builder<T, B>) createPaletteFromChild("stone");
        }

        public StoneZoneEntrySet.Builder<T, B> createPaletteFromBricks() {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild("bricks") != null) {
                    return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                    }, "bricks", null, blockType, manager);
                }
                return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                }, "stone", null, blockType, manager);
            });
            return this;
        }

        public StoneZoneEntrySet.Builder<T, B> createPaletteFromStoneChild(String childKey) {
            this.setPalette((blockType, manager) -> {
                if (blockType.getChild(childKey) != null) {
                    return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                    }, childKey, null, blockType, manager);
                }
                return AbstractSimpleEntrySet.makePaletteFromChild(p -> {
                }, "stone", null, blockType, manager);
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
                        this.useMergedPalette, this.tintConfig, this.copyTint,
                        this.condition);
                e.recipeLocations.addAll(this.recipes);
                e.tags.putAll(this.tags);
                e.textures.addAll(this.textures);

                return e;
            }
        }

        /// Exclude mutiple textures in one parent file
        public Builder<T, B> excludeMultipleTextureFromTinting(ResourceLocation parentId, String... textureKeys) {
            if (this.tintConfig == TintConfiguration.EMPTY) {
                this.tintConfig = TintConfiguration.createNew();
            }
            this.tintConfig.addParentAndTextureValues(parentId, textureKeys);
            return this;
        }

        /// Exclude multiple textures in all parent files
        public Builder<T, B> excludeTextureFromTinting(String... textureKeys) {
            if (this.tintConfig == TintConfiguration.EMPTY) {
                this.tintConfig = TintConfiguration.createNew();
            }
            this.tintConfig.addTextureValues(textureKeys);
            return this;
        }
    }


}
