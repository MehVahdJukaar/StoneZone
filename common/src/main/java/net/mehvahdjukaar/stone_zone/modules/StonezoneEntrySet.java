package net.mehvahdjukaar.stone_zone.modules;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.type.StoneType;
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
        var original = super.makeModelTransformer(module, manager);
        BlockTypeResTransformer<T> transformer = BlockTypeResTransformer.create(module.getModId(), manager);
        var stoneType = baseType.get();

        replaceStoneTextures((StoneType) stoneType, transformer);

        return original;
    }

    public BlockTypeResTransformer<T> replaceStoneTextures(StoneType stoneType, BlockTypeResTransformer<T> m) {
        String n = stoneType.getTypeName();
        return m.replaceWithTextureFromChild("minecraft:block/" + n, "stone")
                .replaceWithTextureFromChild("minecraft:block/" + n + "_bricks", "bricks")
                .replaceWithTextureFromChild("minecraft:block/smooth_" + n, "smooth_stone")
                .replaceWithTextureFromChild("minecraft:block/polished_" + n, "polished");
    }

    //!! SUB-CLASS
    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        public SimpleEntrySet.Builder<T, B> createPaletteFromStone() {
            return createPaletteFromChild("stone");
        }

        public SimpleEntrySet.Builder<T, B> createPaletteFromBricks() {
            StoneType stoneType = (StoneType) baseType.get();
            if (Objects.nonNull(stoneType.getBlockOfThis("bricks")))
                return createPaletteFromChild("bricks");
            else
                return createPaletteFromStone();
        }

        public SimpleEntrySet.Builder<T, B> createPaletteFromStoneChild(String blockType) {
            StoneType stoneType = (StoneType) baseType.get();
            if (Objects.nonNull(stoneType.getBlockOfThis(blockType)))
                return createPaletteFromChild(blockType);
            else
                return createPaletteFromStone();
        }

    }
}
