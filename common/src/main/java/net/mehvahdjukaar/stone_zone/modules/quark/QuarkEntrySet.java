package net.mehvahdjukaar.stone_zone.modules.quark;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.ModelConfiguration;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.module.IDisableable;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.Objects;
import java.util.function.*;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BRICKS;

public class QuarkEntrySet<T extends BlockType, B extends Block> extends StoneZoneEntrySet<T, B> {

    private final Supplier<ZetaModule> zetaModule;

    public QuarkEntrySet(Class<T> type,
                         String name, @Nullable String prefix,
                         Class<? extends ZetaModule> module,
                         Supplier<B> baseBlock,
                         Supplier<T> baseType,
                         Function<T, B> blockSupplier,
                         @Nullable Supplier<ResourceKey<CreativeModeTab>> tab,
                         TabAddMode tabMode,
                         SimpleEntrySet.LootTableMode tableMode,
                         @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                         @Nullable SimpleEntrySet.ITileHolder<?> tileFactory,
                         @Nullable Object renderType,
                         @Nullable BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> paletteSupplier,
                         @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                         boolean mergedPalette,
                         boolean copyTint,
                         Predicate<T> condition, ModelConfiguration modelConfig
    ) {
        super(type, name, prefix, blockSupplier, baseBlock, baseType, Objects.requireNonNull(tab), tabMode, tableMode, itemFactory,
                tileFactory, renderType, paletteSupplier, extraTransform, mergedPalette, copyTint,
                condition, modelConfig);
        var m = Preconditions.checkNotNull(module);
        this.zetaModule = Suppliers.memoize(() -> Quark.ZETA.modules.get(m));
    }


    @Override
    public void generateRecipes(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.enabled) {
            super.generateRecipes(module, manager, sink);
        }
    }

    @Override
    public @Nullable Item getItemOf(T type) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.enabled) {
            var item = super.getItemOf(type);
            if (item instanceof IDisableable<?> d && !d.doesConditionApply()) {
                return null;
            }
            if (item instanceof BlockItem bi && bi.getBlock() instanceof IDisableable<?> d && !d.doesConditionApply()) {
                return null;
            }
            return item;
        }
        return null;
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(
            Class<T> type,
            String name,
            Class<? extends ZetaModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            Function<T, B> factory) {
        return new Builder<>(type, name, null, quarkModule, baseType, baseBlock, factory);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> of(
            Class<T> type,
            String name, String prefix,
            Class<? extends ZetaModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            Function<T, B> factory) {
        return new Builder<>(type, name, prefix, quarkModule, baseType, baseBlock, factory);
    }

    //!! SUBCLASS
    public static class Builder<T extends BlockType, B extends Block> extends StoneZoneEntrySet.Builder<T, B> {

        private final Function<T, B> blockSupplier;
        private final Class<? extends ZetaModule> quarkModule;

        protected Builder(Class<T> type, String name, @Nullable String prefix,
                          Class<? extends ZetaModule> quarkModule,
                          Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> factory) {
            super(type, name, prefix, baseType, baseBlock, null);
            this.quarkModule = quarkModule;
            this.blockSupplier = factory;
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public QuarkEntrySet.Builder<T, B> createPaletteFromStone() {
            return (QuarkEntrySet.Builder<T, B>) createPaletteFromChild("stone");
        }

        /// @deprecated new method haven't been implemented yet
        @Deprecated(forRemoval = true)
        public QuarkEntrySet.Builder<T, B> createPaletteFromBricks() {
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

        @Override
        public QuarkEntrySet<T, B> build() {
            var e = new QuarkEntrySet<>(this.type, this.name, this.prefix, this.quarkModule,
                    this.baseBlock, this.baseType, this.blockSupplier, this.tab, this.tabMode, this.lootMode,
                    this.itemFactory, this.tileHolder, this.renderType, this.palette, this.extraModelTransform, this.useMergedPalette,
                    this.copyTint, this.condition, this.modelConfig);

            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }
    }

}