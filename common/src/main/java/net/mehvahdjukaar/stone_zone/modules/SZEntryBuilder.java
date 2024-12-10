package net.mehvahdjukaar.stone_zone.modules;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class SZEntryBuilder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {


    protected SZEntryBuilder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
        super(type, name, prefix, baseType, baseBlock, blockFactory);
    }

    public static <T extends BlockType, B extends Block> SZEntryBuilder<T, B> of(Class<T> type, String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new SZEntryBuilder<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    public SimpleEntrySet.Builder<T, B> createPaletteFromStone() {
        return createPaletteFromChild("stone");
    }

    // remember to add a condition for bricks. Alternatively one could create a brick palette from stone here via palette modification
    public SimpleEntrySet.Builder<T, B> createPaletteFromBricks() {
        return createPaletteFromChild("bricks");
    }
}
