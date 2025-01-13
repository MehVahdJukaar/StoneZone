package net.mehvahdjukaar.stone_zone.modules.quark;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.api.StonezoneEntrySet;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
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

import java.util.List;
import java.util.Objects;
import java.util.function.*;

public class QuarkEntrySet<T extends BlockType, B extends Block> extends StonezoneEntrySet<T, B> {

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
                         @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                         @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                         boolean mergedPalette,
                         boolean copyTint,
                         Predicate<T> condition) {

        super(type, name, prefix, blockSupplier, baseBlock, baseType, Objects.requireNonNull(tab), tabMode, tableMode, itemFactory,
                tileFactory, renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition);
        var m = Preconditions.checkNotNull(module);
        this.zetaModule = Suppliers.memoize(() -> Quark.ZETA.modules.get(m));
    }


    @Override
    public void generateRecipes(SimpleModule module, DynamicDataPack pack, ResourceManager manager) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.enabled) {
            super.generateRecipes(module, pack, manager);
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
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        private final Function<T, B> blockSupplier;
        private final Class<? extends ZetaModule> quarkModule;

        protected Builder(Class<T> type, String name, @Nullable String prefix,
                          Class<? extends ZetaModule> quarkModule,
                          Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> factory) {
            super(type, name, prefix, baseType, baseBlock, null);
            this.quarkModule = quarkModule;
            this.blockSupplier = factory;
        }

        public QuarkEntrySet.Builder<T, B> createPaletteFromStone() {
            return (QuarkEntrySet.Builder<T, B>) createPaletteFromChild("stone");
        }

        public QuarkEntrySet.Builder<T, B> createPaletteFromBricks() {
            this.setPalette(new BiFunction<T, ResourceManager, Pair<List<Palette>, AnimationMetadataSection>>() {
                @Override
                public Pair<List<Palette>, AnimationMetadataSection> apply(T t, ResourceManager resourceManager) {
                    if(t.getChild("bricks") != null){
                        return AbstractSimpleEntrySet.makePaletteFromChild(t, resourceManager, "bricks");
                    }
                    return AbstractSimpleEntrySet.makePaletteFromChild(t, resourceManager, "stone");
                }
            });
            return this;
        }

        @Override
        public QuarkEntrySet<T, B> build() {
            var e = new QuarkEntrySet<>(type, name, prefix, quarkModule,
                    baseBlock, baseType, blockSupplier, tab, tabMode, lootMode,
                    itemFactory, tileHolder, renderType, palette, extraModelTransform, useMergedPalette, copyTint, condition);

            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }
    }

}