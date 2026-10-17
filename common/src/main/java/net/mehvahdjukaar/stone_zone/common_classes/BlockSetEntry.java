package net.mehvahdjukaar.stone_zone.common_classes;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.modules.create.BitsNBobsModuleAbstract;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// A helper to add block, slab, stairs and wall. This reduce the repetitive codes.
public class BlockSetEntry<BT extends BlockType> {

    public final SimpleEntrySet<BT, Block> block;
    public final SimpleEntrySet<BT, Block> stairs;
    public final SimpleEntrySet<BT, Block> slab;
    public final @Nullable SimpleEntrySet<BT, Block> wall;

    protected BlockSetEntry(
            SimpleEntrySet<BT, Block> block,
            SimpleEntrySet<BT, Block> stairs,
            SimpleEntrySet<BT, Block> slab,
            @Nullable SimpleEntrySet<BT, Block> wall)
    {
        this.block = block;
        this.stairs = stairs;
        this.slab = slab;
        this.wall = wall;
    }

    /**
     * Block, Slab, &amp; Stairs are enabled by default, Wall is optional
     * @param getBlockPath BlockId's path for Block
     * @param propertyBlock use Block's property to be copied
     * @param getSlabPath BlockId's path for Slab
     * @param propertySlab use Slab or Block's property to be copied
     * @param getStairsPath BlockId's path for Stairs
     * @param propertyStairs use Stairs or Block's property to be copied
     * @param getWallPath BlockId's path for Wall
     * @param propertyWall use Wall or Block's property to be copied
     * Example: See {@link BitsNBobsModuleAbstract}
     */
    @SuppressWarnings("DataFlowIssue")
    public static <T extends BlockType> BlockSetBuilder<T> of(
            Class<T> typeClass, StoneZoneModule module, T baseTypeId,
            String getBlockPath, Function<T, Block> propertyBlock,
            String getSlabPath, Function<T, Block> propertySlab,
            String getStairsPath, Function<T, Block> propertyStairs,
            @Nullable String getWallPath, @Nullable Function<T, Block> propertyWall,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                getBlockPath, type -> new Block(Utils.copyPropertySafe(propertyBlock.apply(type))),
                getSlabPath, type -> new SlabBlock(Utils.copyPropertySafe(propertySlab.apply(type))),
                getStairsPath, type -> {
                        Block getBlock = propertyStairs.apply(type);

                        return new StairBlock(
                                getBlock.defaultBlockState(),
                                Utils.copyPropertySafe(getBlock)
                        );
                },
                getWallPath, type -> new WallBlock(Utils.copyPropertySafe(propertyWall.apply(type))),
                tabSupplier
        );
    }

    public static <T extends BlockType> BlockSetBuilder<T> withFactory(
            Class<T> typeClass, StoneZoneModule module, T baseTypeId,
            String getBlockPath, Function<T, Block> blockFactory,
            String getSlabPath, Function<T, Block> slabFactory,
            String getStairsPath, Function<T, Block> stairsFactory,
            @Nullable String getWallPath, @Nullable Function<T, Block> wallFactory,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                getBlockPath, blockFactory,
                getSlabPath, slabFactory,
                getStairsPath, stairsFactory,
                getWallPath, wallFactory,
                tabSupplier
        );
    }

    /**
     * Block, Slab, &amp; Stairs are enabled by default
     * @param getBlockPath BlockId's path for Block
     * @param propertyBlock use Utils.copyPropertySafe(...) or Property.of(...)
     * @param getSlabPath BlockId's path for Slab
     * @param propertySlab use Utils.copyPropertySafe(...) or Property.of(...)
     * @param getStairsPath BlockId's path for Stairs
     * @param propertyStairs use Utils.copyPropertySafe(...) or Property.of(...)
     */
    public static <T extends BlockType> BlockSetBuilder<T> of(
            Class<T> typeClass, StoneZoneModule module, T baseTypeId,
            String getBlockPath, Function<T, Block> propertyBlock,
            String getSlabPath, Function<T, Block> propertySlab,
            String getStairsPath, Function<T, Block> propertyStairs,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                getBlockPath, propertyBlock,
                getSlabPath, propertySlab,
                getStairsPath, propertyStairs,
                null, null,
                tabSupplier
        );
    }

    public static <T extends BlockType> BlockSetBuilder<T> withFactory(
            Class<T> typeClass, StoneZoneModule module, T baseTypeId,
            String getBlockPath, Function<T, Block> propertyBlock,
            String getSlabPath, Function<T, Block> propertySlab,
            String getStairsPath, Function<T, Block> propertyStairs,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return new BlockSetBuilder<>(typeClass, module, baseTypeId,
                getBlockPath, type -> new Block(Utils.copyPropertySafe(propertyBlock.apply(type))),
                getSlabPath, type -> new SlabBlock(Utils.copyPropertySafe(propertySlab.apply(type))),
                getStairsPath, type -> {
            Block getBlock = propertyStairs.apply(type);

            return new StairBlock(
                    getBlock.defaultBlockState(),
                    Utils.copyPropertySafe(getBlock)
            );
        },
                null, null,
                tabSupplier
        );
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         SubClass                         │
    //      └──────────────────────────────────────────────────────────┘
    @SuppressWarnings("unused") // noSlab() or noStairs() will be used in a rare case
    public static class BlockSetBuilder<T extends BlockType> extends StoneZoneModule {

        private final T baseType;

        private final Class<T> typeClass;

        private final String slabPath;
        private final String stairsPath;
        private final String wallPath;
        private final Function<T, Block> slabFunction;
        private final Function<T, Block> stairsFunction;
        private final Function<T, Block> wallFunction;

        private final Supplier<CreativeModeTab> tabSupplier;

        private final SimpleEntrySet.Builder<T, Block> blockBuilder;

        private Consumer<StoneZoneEntrySet.Builder<T, Block>> stairsConfig =
                builder -> {};

        private Consumer<StoneZoneEntrySet.Builder<T, Block>> slabConfig =
                builder -> {};

        private Consumer<StoneZoneEntrySet.Builder<T, Block>> wallConfig =
                builder -> {};
// ─────────────────────────────────────────────────────────────────────────────────────
        private boolean createStairs = true;
        private boolean createSlabs = true;
        private boolean createWall = false;

        /// Adding the tags like #minecraft:slabs or #minecraft:stairs for slab, or stairs
        private boolean addSlabAndStairsTag = true;
        private boolean addPickaxeTag = false;
        private boolean addAxeTag = false;

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                       Constructor                        │
        //      └──────────────────────────────────────────────────────────┘
        public BlockSetBuilder(Class<T> typeClass, StoneZoneModule module, T baseType,
                               String blockPath, Function<T, Block> blockFunction,
                               String slabPath, Function<T, Block> slabFunction,
                               String stairsPath, Function<T, Block> stairsFunction,
                               @Nullable String wallPath, @Nullable Function<T, Block> wallFunction,
                               Supplier<CreativeModeTab> tabSupplier
        ) {
            super(module.getModId(), module.shortenedId());

            this.typeClass = typeClass;
            this.baseType = baseType;
            this.slabPath = slabPath;
            this.stairsPath = stairsPath;
            this.wallPath = wallPath;
            this.slabFunction = slabFunction;
            this.stairsFunction = stairsFunction;
            this.wallFunction = wallFunction;

            this.tabSupplier = tabSupplier;

            // ───────────────────────────── Blockbuilder ──────────────────────────────
            String prefix = getAffix(blockPath).getFirst();
            String suffix = getAffix(blockPath).getSecond();

            this.blockBuilder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                            getModBlock(blockPath), () -> baseType,
                            blockFunction
                    )
                    .setTab(tabSupplier)
                    .setRenderType(RenderLayer.CUTOUT_MIPPED);

            if (addPickaxeTag) {
                this.blockBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
            }
            if (addAxeTag) {
                this.blockBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
            }
        }

        // ───────────────────────────────── Add Tags ──────────────────────────────────
        /// Not adding the tags like #minecraft;slabs or #minecraft:stairs for slab, or stairs
        public BlockSetBuilder<T> noSlabAndStairsTag() {
            this.addSlabAndStairsTag = false;
            return this;
        }

        public BlockSetBuilder<T> pickaxeTag() {
            this.addPickaxeTag = true;
            return this;
        }
        public BlockSetBuilder<T> AxeTag() {
            this.addPickaxeTag = true;
            return this;
        }

        //!! ─────────────────────────────────── Block ───────────────────────────────────
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

        public BlockSetBuilder<T> wall() {
            this.createWall = true;
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
            /// ───────────────────────────────── Block ─────────────────────────────────
            SimpleEntrySet<T, Block> block = blockBuilder.build();

            StoneZoneEntrySet<T, Block> slab = null;
            StoneZoneEntrySet<T, Block> stairs = null;
            StoneZoneEntrySet<T, Block> wall = null;

            /// ───────────────────────────────── Slab ──────────────────────────────────
            if (createSlabs && slabFunction != null) {
                String prefix = getAffix(slabPath).getFirst();
                String suffix = getAffix(slabPath).getSecond();

                var builder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(slabPath), () -> baseType,
                                slabFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) {
                    builder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                }
                if (addAxeTag) {
                    builder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                }
                if (addSlabAndStairsTag) {
                    builder.addTag(BlockTags.SLABS, Registries.BLOCK)
                            .addTag(ItemTags.SLABS, Registries.ITEM);
                }

                this.slabConfig.accept((StoneZoneEntrySet.Builder<T, Block>) builder);
                slab = (StoneZoneEntrySet<T, Block>) builder.build();
            }

            /// ──────────────────────────────── Stairs ─────────────────────────────────
            if (createStairs && stairsFunction != null) {
                String prefix = getAffix(stairsPath).getFirst();
                String suffix = getAffix(stairsPath).getSecond();

                SimpleEntrySet.Builder<T, Block> builder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(stairsPath), () -> baseType,
                                stairsFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) {
                    builder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                }
                if (addAxeTag) {
                    builder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                }
                if (addSlabAndStairsTag) {
                    builder.addTag(BlockTags.STAIRS, Registries.BLOCK)
                            .addTag(ItemTags.STAIRS, Registries.ITEM);
                }

                this.stairsConfig.accept((StoneZoneEntrySet.Builder<T, Block>) builder);
                stairs = (StoneZoneEntrySet<T, Block>) builder.build();
            }

            /// ───────────────────────────────── Wall ──────────────────────────────────
            if (createWall && wallFunction != null) {
                String prefix = getAffix(wallPath).getFirst();
                String suffix = getAffix(wallPath).getSecond();

                var builder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(wallPath), () -> baseType,
                                wallFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) {
                    builder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                }
                if (addAxeTag) {
                    builder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                }
                if (addSlabAndStairsTag) {
                    builder.addTag(BlockTags.WALLS, Registries.BLOCK)
                            .addTag(ItemTags.WALLS, Registries.ITEM);
                }

                wallConfig.accept((StoneZoneEntrySet.Builder<T, Block>) builder);
                wall = (StoneZoneEntrySet<T, Block>) builder.build();
            }

            return new BlockSetEntry<>(
                    block,
                    stairs,
                    slab,
                    wall
            );
        }

        //      ┌──────────────────────────────────────────────────────────┐
        //      │                        Utilities                         │
        //      └──────────────────────────────────────────────────────────┘
        public Pair<@Nullable String, @NotNull String> getAffix(String baseblockPath) {
            String typeName = baseType.getTypeName();

            Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
            Matcher matcher = pattern.matcher(baseblockPath);

            if (matcher.find()) {
                String prefix = matcher.group("prefix");
                String suffix = matcher.group("suffix");

                if (prefix == null && suffix != null) return Pair.of(null, suffix);
                else if (prefix != null && suffix == null) return Pair.of(prefix, "");
                else return Pair.of(prefix, suffix);
            }

            return Pair.of("", "");
        }
    }
}
