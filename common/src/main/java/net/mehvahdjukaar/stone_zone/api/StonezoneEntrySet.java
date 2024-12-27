package net.mehvahdjukaar.stone_zone.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.*;

public class StonezoneEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    protected StonezoneEntrySet(Class<T> type, String name, @Nullable String prefix, Function<T, B> blockSupplier,
                                Supplier<B> baseBlock, Supplier<T> baseType,
                                @NotNull Supplier<ResourceKey<CreativeModeTab>> tab,
                                TabAddMode tabMode, LootTableMode lootMode,
                                @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                                @Nullable ITileHolder tileFactory, @Nullable Object renderType,
                                @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>,
                                        @Nullable AnimationMetadataSection>> paletteSupplier,
                                @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                boolean mergedPalette, boolean copyTint, Predicate<T> condition) {

        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tabMode, lootMode, itemFactory, tileFactory,
                renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new Builder<>(type, name, prefix, baseType, baseBlock, blockSupplier);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(Class<T> type, String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new Builder<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    @Override
    protected BlockTypeResTransformer<T> makeModelTransformer(SimpleModule module, ResourceManager manager) {

        String nameStoneType = baseType.get().getTypeName();
        return super.makeModelTransformer(module, manager)
                .replaceWithTextureFromChild("minecraft:block/" + nameStoneType, "stone")
                .replaceWithTextureFromChild("minecraft:block/" + nameStoneType + "_bricks", "bricks")
                .replaceWithTextureFromChild("minecraft:block/smooth_" + nameStoneType, "smooth_stone")
                .replaceWithTextureFromChild("minecraft:block/polished_" + nameStoneType, "polished");
    }

    @Override
    protected BlockTypeResTransformer<T> makeBlockStateTransformer(SimpleModule module, ResourceManager manager) {
        String originalStoneName = baseType.get().getTypeName();
        return super.makeBlockStateTransformer(module, manager).addModifier((s, id, stoneType) ->
                BlockTypeResTransformer.replaceFullGenericType(s, stoneType, stoneType.getId(), originalStoneName, "minecraft", "block"));
    }


    //!! SUB-CLASS
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        public StonezoneEntrySet.Builder<T, B> createPaletteFromStone() {
            return (Builder<T, B>) createPaletteFromChild("stone");
        }

        public StonezoneEntrySet.Builder<T, B> createPaletteFromBricks() {
            StoneType stoneType = (StoneType) baseType.get();
            if (Objects.nonNull(stoneType.getBlockOfThis("bricks")))
                return (Builder<T, B>) createPaletteFromChild("bricks");
            else
                return createPaletteFromStone();
        }

        public StonezoneEntrySet.Builder<T, B> createPaletteFromStoneChild(String blockType) {
            StoneType stoneType = (StoneType) baseType.get();
            if (Objects.nonNull(stoneType.getBlockOfThis(blockType)))
                return (Builder<T, B>) createPaletteFromChild(blockType);
            else
                return createPaletteFromStone();
        }

        @Override
        public StonezoneEntrySet<T, B> build() {
            if (this.tab == null && PlatHelper.isDev()) {
                throw new IllegalStateException("Tab for module " + this.name + " was null!");
            } else {
                // all blocks could have tint as stone could be tinted themselves
                this.copyParentTint();

                StonezoneEntrySet<T, B> e = new StonezoneEntrySet<>(this.type, this.name, this.prefix, this.blockFactory, this.baseBlock,
                        this.baseType, this.tab, this.tabMode, this.lootMode, this.itemFactory,
                        this.tileHolder, this.renderType, this.palette, this.extraModelTransform,
                        this.useMergedPalette, this.copyTint, this.condition);
                e.recipeLocations.addAll(this.recipes);
                e.tags.putAll(this.tags);
                e.textures.addAll(this.textures);

                return e;
            }
        }
    }
}
