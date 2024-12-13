package net.mehvahdjukaar.stone_zone.modules;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.type.StoneType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class SZEntryBuilder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {


    protected SZEntryBuilder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
        super(type, name, prefix, baseType, baseBlock, blockFactory);
    }

    public static <T extends BlockType, B extends Block> SZEntryBuilder<T, B> of(Class<T> type, String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new SZEntryBuilder<>(type, name, prefix, baseType, baseBlock, blockSupplier);
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

    @Override
    public SimpleEntrySet<T, B> build() {
        this.addModelTransform(m -> {
            var stoneType = baseType.get();
            replaceStoneTextures((StoneType) stoneType, m);
        });
        return super.build();
    }

    public void replaceStoneTextures(StoneType stoneType, BlockTypeResTransformer<T> m) {
        String n = stoneType.getTypeName();
        m.replaceWithTextureFromChild("minecraft:block/" + n, "stone")
                .replaceWithTextureFromChild("minecraft:block/" + n + "_bricks", "bricks")
                .replaceWithTextureFromChild("minecraft:block/smooth_" + n, "smooth_stone")
                .replaceWithTextureFromChild("minecraft:block/polished_" + n, "polished");
    }

    //!! Utilities
    public static BlockBehaviour.Properties copyChildrenProperties(String blockType, StoneType stoneType) {
        Block block = stoneType.getBlockOfThis(blockType);
        Block blockAlt = null;
        if (blockType.contains("_")) {
            String[] split = blockType.split("_");
            blockAlt = stoneType.getBlockOfThis(split[1]);
        }

        if (Objects.nonNull(block)) return Utils.copyPropertySafe(block);
        else if (Objects.nonNull(blockAlt)) return Utils.copyPropertySafe(blockAlt);
        else return Utils.copyPropertySafe(stoneType.stone);
    }
}
