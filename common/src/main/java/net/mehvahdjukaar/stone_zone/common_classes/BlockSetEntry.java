package net.mehvahdjukaar.stone_zone.common_classes;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class BlockSetEntry<BT extends BlockType> extends StoneZoneModule {

    public final SimpleEntrySet<BT, Block> block;
    public final SimpleEntrySet<BT, Block> stairs;
    public final SimpleEntrySet<BT, Block> slab;

    @Nullable
    public final SimpleEntrySet<BT, Block> wall;

    private BlockSetEntry(
            StoneZoneModule module,
            SimpleEntrySet<BT, Block> block,
            SimpleEntrySet<BT, Block> stairs,
            SimpleEntrySet<BT, Block> slab,
            @Nullable SimpleEntrySet<BT, Block> wall)
    {
        super(module.getModId(), module.shortenedId());

        this.block = block;
        this.stairs = stairs;
        this.slab = slab;
        this.wall = wall;
    }

    public <S extends BlockType> BlockSetBuilder<S> of(
            Class<S> typeClass, StoneZoneModule module, S baseTypeId,
            String blockSupplier, Function<S, Block> propertyBlock,
            String slabSupplier, Function<S, Block> propertySlab,
            String StairsSupplier, Function<S, Block> propertyStairs,
            @Nullable String wallSupplier, @Nullable Function<S, Block> propertyWall,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                blockSupplier, propertyBlock,
                slabSupplier, propertySlab,
                StairsSupplier, propertyStairs,
                wallSupplier, propertyWall,
                tabSupplier
        );
    }

    public <S extends BlockType> BlockSetBuilder<S> of(
            Class<S> typeClass, StoneZoneModule module, S baseTypeId,
            String blockSupplier, Function<S, Block> propertyBlock,
            String slabSupplier, Function<S, Block> propertySlab,
            String StairsSupplier, Function<S, Block> propertyStairs,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                blockSupplier, propertyBlock,
                slabSupplier, propertySlab,
                StairsSupplier, propertyStairs,
                null, null,
                tabSupplier
        );
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         SubClass                         │
    //      └──────────────────────────────────────────────────────────┘
    public class BlockSetBuilder<T extends BlockType> {

        private final StoneZoneModule module;
        private final T baseType;

        private final Class<T> typeClass;

//        private final Supplier<Block> blockSupplier;
        private final String slabSupplier;
        private final String stairsSupplier;
        private final String wallSupplier;
        private final Function<T, Block> propertySlab;
        private final Function<T, Block> propertyStairs;
        private final Function<T, Block> propertyWall;

        private final Supplier<CreativeModeTab> tabSupplier;

        private final SimpleEntrySet.Builder<T, Block> blockBuilder;

        private Consumer<StoneZoneEntrySet.Builder<T, Block>> stairsConfig =
                builder -> {};

        private Consumer<StoneZoneEntrySet.Builder<T, Block>> slabConfig =
                builder -> {};

        private Consumer<StoneZoneEntrySet.Builder<T, Block>> wallConfig =
                builder -> {};

        private boolean createStairs = true;
        private boolean createSlabs = true;
        private boolean createWall = false;

//        private final Class<T> typeClass = StoneType.class;

        public BlockSetBuilder(Class<T> typeClass, StoneZoneModule module, T baseType,
                               String blockSupplier, Function<T, Block> propertyBlock,
                               String slabSupplier, Function<T, Block> propertySlab,
                               String StairsSupplier, Function<T, Block> propertyStairs,
                               @Nullable String wallSupplier, @Nullable Function<T, Block> propertyWall,
                               Supplier<CreativeModeTab> tabSupplier
        ) {
            this.module = module;
            this.typeClass = typeClass;
            this.baseType = baseType;
//            this.blockSupplier = blockSupplier;
            this.slabSupplier = slabSupplier;
            this.stairsSupplier = StairsSupplier;
            this.wallSupplier = wallSupplier;

            this.propertySlab = propertySlab;
            this.propertyStairs = propertyStairs;
            this.propertyWall = propertyWall;

            this.tabSupplier = tabSupplier;

            // ───────────────────────────── Blockbuilder ──────────────────────────────
            String prefix = getAffix(blockSupplier).getFirst();
            String suffix = getAffix(blockSupplier).getSecond();

            this.blockBuilder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                            getModBlock("cracked_mud_bricks"), () -> baseType,
                            blockType -> new Block(Utils.copyPropertySafe(propertyBlock.apply(blockType)))
                    )
                    .setTab(tabSupplier)
                    .setRenderType(RenderLayer.CUTOUT_MIPPED);
        }

        public BlockSetBuilder<T> block(
                Consumer<SimpleEntrySet.Builder<T, Block>> config
        ) {
            config.accept(blockBuilder);
            return this;
        }

        //!! ────────────────────────────────── Stairs ───────────────────────────────────
        public BlockSetBuilder<T> stairs(Consumer<StoneZoneEntrySet.Builder<T, Block>> config) {
            this.createStairs = true;
            this.stairsConfig = config;
            return this;
        }

        public BlockSetBuilder<T> noStairs() {
            this.createStairs = false;
            return this;
        }

        //!! ─────────────────────────────────── Slab ────────────────────────────────────
        public BlockSetBuilder<T> slab(Consumer<StoneZoneEntrySet.Builder<T, Block>> config) {
            this.createSlabs = true;
            this.slabConfig = config;
            return this;
        }

        public BlockSetBuilder<T> noSlabs() {
            this.createSlabs = false;
            return this;
        }

        //!! ─────────────────────────────────── Wall ────────────────────────────────────
        public BlockSetBuilder<T> wall(Consumer<StoneZoneEntrySet.Builder<T, Block>> config) {
            this.createWall = true;
            this.wallConfig = config;
            return this;
        }

        public BlockSetBuilder<T> noWall() {
            this.createWall = false;
            return this;
        }

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                          Build                           │
        //      └──────────────────────────────────────────────────────────┘
        public BlockSetEntry<T> build() {
            // Build the base entry first because variants depend on its map.
            SimpleEntrySet<T, Block> blockEntry = blockBuilder.build();
            module.addEntry(blockEntry);

            StoneZoneEntrySet<T, Block> slab = null;
            StoneZoneEntrySet<T, Block> stairs = null;
            StoneZoneEntrySet<T, Block> wall = null;

            if (createSlabs) {
                String prefix = getAffix(slabSupplier).getFirst();
                String suffix = getAffix(slabSupplier).getSecond();

                var builder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(slabSupplier), () -> baseType,
                                blockType -> new SlabBlock(Utils.copyPropertySafe(propertySlab.apply(blockType)))
                        )
                        .requiresFromMap(blockEntry.blocks) //REASON: Recipes, Textures
                        .addTag(BlockTags.SLABS, Registries.BLOCK)
                        .addTag(ItemTags.SLABS, Registries.ITEM)
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                slabConfig.accept((StoneZoneEntrySet.Builder<T, Block>) builder);
                slab = (StoneZoneEntrySet<T, Block>) builder.build();
                if (slab != null) module.addEntry(slab);
            }

            if (createStairs) {
                String prefix = getAffix(stairsSupplier).getFirst();
                String suffix = getAffix(stairsSupplier).getSecond();

                var builder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(stairsSupplier), () -> baseType,
                                blockType -> {
                                    Block getBlock = propertyStairs.apply(blockType);

                                    return new StairBlock(
                                            getBlock.defaultBlockState(),
                                            Utils.copyPropertySafe(getBlock)
                                    );
                                }
                        )
                        .requiresFromMap(blockEntry.blocks) //REASON: Recipes, Textures
                        .addTag(BlockTags.STAIRS, Registries.BLOCK)
                        .addTag(ItemTags.STAIRS, Registries.ITEM)
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                stairsConfig.accept((StoneZoneEntrySet.Builder<T, Block>) builder);
                stairs = (StoneZoneEntrySet<T, Block>) builder.build();
                if (stairs != null) module.addEntry(stairs);
            }

            if (createWall) {
                String prefix = getAffix(wallSupplier).getFirst();
                String suffix = getAffix(wallSupplier).getSecond();

                var builder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(wallSupplier), () -> baseType,
                                blockType -> new WallBlock(Utils.copyPropertySafe(propertyWall.apply(blockType)))
                        )
                        .requiresFromMap(blockEntry.blocks) //REASON: Recipes, Textures
                        .addTag(BlockTags.WALLS, Registries.BLOCK)
                        .addTag(ItemTags.WALLS, Registries.ITEM)
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                wallConfig.accept((StoneZoneEntrySet.Builder<T, Block>) builder);
                wall = (StoneZoneEntrySet<T, Block>) builder.build();
                if (wall != null) module.addEntry(wall);
            }

            return new BlockSetEntry<>(
                    module,
                    blockEntry,
                    stairs,
                    slab,
                    wall
            );
        }

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                        Utilities                         │
        //      └──────────────────────────────────────────────────────────┘
        public Pair<String, String> getAffix(String baseblockPath) {
            String typeName = baseType.getTypeName();

            Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
            Matcher matcher = pattern.matcher(baseblockPath);

            if (matcher.find()) return Pair.of(matcher.group("prefix"), matcher.group("suffix"));

            return Pair.of("", "");
        }
    }
}
