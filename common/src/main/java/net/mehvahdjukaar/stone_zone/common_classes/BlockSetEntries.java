package net.mehvahdjukaar.stone_zone.common_classes;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.RockType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.BLOCK;

/// A helper to add block, slab, stairs and wall. This reduce the repetitive codes.
public class BlockSetEntries<BT extends RockType> {

    public final SimpleEntrySet<BT, Block> block;
    /// extra Block or Slab
    public final SimpleEntrySet<BT, Block> extra;
    public final SimpleEntrySet<BT, Block> stairs;
    public final @Nullable SimpleEntrySet<BT, Block> wall;

    protected BlockSetEntries(
            SimpleEntrySet<BT, Block> block,
            SimpleEntrySet<BT, Block> stairs,
            SimpleEntrySet<BT, Block> extra,
            @Nullable SimpleEntrySet<BT, Block> wall)
    {
        this.block = block;
        this.stairs = stairs;
        this.extra = extra;
        this.wall = wall;
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                            CUSTOM                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
    /**
     * <p style="color: yellow;">The parameters with "Factory", Custom Block can be used.</p>
     * Example: new RechiseledBlock(...) can be used for blockFactory
     * @param getBlockPath BlockId's path for Block
     * @param getSlabPath BlockId's path for Slab
     * @param getStairsPath BlockId's path for Stairs
     * @param getWallPath  BlockId's path for Wall
     */
    public static <T extends RockType> BlockSetBuilder<T> of4BlocksFactory(
            StoneZoneModule module, Class<T> typeClass, T baseTypeId,
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
     * <p style="color: yellow;">The parameters with "Factory", Custom Block can be used.</p>
     * Example: new RechiseledBlock(...) can be used for blockFactory
     * @param getBlockPath BlockId's path for Block
     * @param getSlabPath BlockId's path for Slab
     * @param getStairsPath BlockId's path for Stairs
     */
    public static <T extends RockType> BlockSetBuilder<T> of3BlocksFactory(
            StoneZoneModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, Block> blockFactory,
            String getSlabPath, Function<T, Block> slabFactory,
            String getStairsPath, Function<T, Block> stairsFactory,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of4BlocksFactory(module, typeClass, baseTypeId,
                getBlockPath, blockFactory,
                getSlabPath, slabFactory,
                getStairsPath, stairsFactory,
                null, null,
                tabSupplier
        );
    }

    public static <T extends RockType> BlockSetBuilder<T> of4BlocksProperty(
            Class<T> typeClass, StoneZoneModule module, T baseTypeId,
            String getBlockPath, Function<T, BlockBehaviour.Properties> blockProperty,
            String getSlabPath, Function<T, BlockBehaviour.Properties> slabOrExtraProperty,
            String getStairsPath, Function<T, BlockBehaviour.Properties> stairsProperty,
            String getWallPath, Function<T, BlockBehaviour.Properties> wallProperty,
            Supplier<CreativeModeTab> tabSupplier)
    {
        return of4BlocksFactory(module, typeClass, baseTypeId,
                getBlockPath, type -> new Block(blockProperty.apply(type)),
                getSlabPath, type -> new SlabBlock(slabOrExtraProperty.apply(type)),
                getStairsPath, type -> new StairBlock(
                        requireNonNull(type.getBlockOfThis(getChildKey(module.getModId(), getBlockPath, baseTypeId))).defaultBlockState(),
                        stairsProperty.apply(type)
                ),
                getWallPath, type -> new WallBlock(wallProperty.apply(type)),
                tabSupplier
        );
    }

    /**
     * The first 3 factories by default are: Block, SlabBlock, and StairBlock, BUT wall are excluded
     * <p style="color: yellow;">The parameters with "Property" is for copying Block's BlockBehaviour.Properties</p>
     * @param getBlockPath BlockId's path for Block
     * @param getSlabPath BlockId's path for Slab
     * @param getStairsPath BlockId's path for Stairs
     */
    public static <T extends RockType> BlockSetBuilder<T> of3BlocksProperty(
            StoneZoneModule module, Class<T> typeClass, T baseTypeId,
            String getBlockPath, Function<T, BlockBehaviour.Properties> blockProperty,
            String getSlabPath, Function<T, BlockBehaviour.Properties> slabProperty,
            String getStairsPath, Function<T, BlockBehaviour.Properties> stairsProperty,
            Supplier<CreativeModeTab> tabSupplier) {
        return of3BlocksFactory(module, typeClass, baseTypeId,
                getBlockPath, type -> new Block(blockProperty.apply(type)),
                getSlabPath, type -> new SlabBlock(slabProperty.apply(type)),
                getStairsPath, type -> new StairBlock(
                        requireNonNull(type.getBlockOfThis(getChildKey(module.getModId(), getBlockPath, baseTypeId))).defaultBlockState(),
                        stairsProperty.apply(type)
                ),
                tabSupplier
        );
    }

    //      ┌──────────────────────────────────────────────────────────────────────────┐
    //      │                                Utilities                                 │
    //      └──────────────────────────────────────────────────────────────────────────┘

    /**
     * get Childkey from baseBlockPath so it can be used in type.getBlockOfThis(...)
     * @return modId:childkey<br>
     * In Case of failure, defaulting to BLOCK
    **/
    public static <T extends RockType> String getChildKey(String modId, String baseblockPath, T baseBlockType) {
        String typeName = baseBlockType.getTypeName();

        Pattern pattern = Pattern.compile("(?<prefix>(?:[a-z]+_)?[a-z]+)?_?"+typeName+"_?(?<suffix>\\w+)?");
        Matcher matcher = pattern.matcher(baseblockPath);

        if (matcher.find()) {
            String prefix = matcher.group("prefix");
            String suffix = matcher.group("suffix");

            if (prefix == null && suffix != null) return modId +":"+ suffix;
            else if (prefix != null && suffix == null) return modId +":"+ prefix;
            else return modId +":"+ prefix +"_"+ suffix;
        }
        else {
            StoneZone.LOGGER.error("BlockSetEntry - Failed to get Childkey for {} via {}", baseblockPath, modId);
            return BLOCK;
        }
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           SubClass                                           │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
    @SuppressWarnings({"unused", "FieldMayBeFinal"}) // noSlab() or noStairs() will be used in a rare case
    public static class BlockSetBuilder<T extends RockType> extends StoneZoneModule {

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
        private boolean includeDefaultRecipe = false;

        private boolean includeBlockTexture = false;
        private boolean includeBlockTextureTop = false;

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

            // Add tags
            if (addPickaxeTag) {
                this.blockBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
            }
            if (addAxeTag) {
                this.blockBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
            }

            // Add defaultRecipes using
            if (includeDefaultRecipe) blockBuilder.defaultRecipe();

            // Add Textures
            if (includeBlockTexture) this.blockBuilder.addTexture(modRes("block/" + blockPath));
            if (includeBlockTextureTop) this.blockBuilder.addTexture(modRes("block/" + blockPath + "_top"));
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

        /// Applying defaultRecipe() to all 4 blocks
        public BlockSetBuilder<T> defaultRecipe() {
            this.includeDefaultRecipe = true;
            return this;
        }

        //!! ─────────────────────────────────── Block ───────────────────────────────────
        public BlockSetBuilder<T> block(
                Consumer<SimpleEntrySet.Builder<T, Block>> config
        ) {
            config.accept(blockBuilder);
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath"
        public BlockSetBuilder<T> defaultBlockTexture() {
            this.includeBlockTexture = true;
            return this;
        }

        /// the default ResourceLocation for texture is "block/getBlockPath" + _top
        public BlockSetBuilder<T> defaultBlockTextureTop() {
            this.includeBlockTextureTop = true;
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
        public BlockSetEntries<T> build() {
            /// ───────────────────────────────── Block ─────────────────────────────────
            SimpleEntrySet<T, Block> block = blockBuilder.build();

            StoneZoneEntrySet<T, Block> slab = null;
            StoneZoneEntrySet<T, Block> stairs = null;
            StoneZoneEntrySet<T, Block> wall = null;

            /// ───────────────────────────────── Slab ──────────────────────────────────
            if (createSlabs && slabFunction != null) {
                String prefix = getAffix(slabPath).getFirst();
                String suffix = getAffix(slabPath).getSecond();

                var slabBuilder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(slabPath), () -> baseType,
                                slabFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) {
                    slabBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                }
                if (addAxeTag) {
                    slabBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                }
                if (addSlabAndStairsTag) {
                    slabBuilder.addTag(BlockTags.SLABS, Registries.BLOCK)
                            .addTag(ItemTags.SLABS, Registries.ITEM);
                }

                if (includeDefaultRecipe) slabBuilder.defaultRecipe();

                this.slabConfig.accept((StoneZoneEntrySet.Builder<T, Block>) slabBuilder);
                slab = (StoneZoneEntrySet<T, Block>) slabBuilder.build();
            }

            /// ──────────────────────────────── Stairs ─────────────────────────────────
            if (createStairs && stairsFunction != null) {
                String prefix = getAffix(stairsPath).getFirst();
                String suffix = getAffix(stairsPath).getSecond();

                SimpleEntrySet.Builder<T, Block> stairsBuilder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(stairsPath), () -> baseType,
                                stairsFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) {
                    stairsBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                }
                if (addAxeTag) {
                    stairsBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                }
                if (addSlabAndStairsTag) {
                    stairsBuilder.addTag(BlockTags.STAIRS, Registries.BLOCK)
                            .addTag(ItemTags.STAIRS, Registries.ITEM);
                }

                if (includeDefaultRecipe) stairsBuilder.defaultRecipe();

                this.stairsConfig.accept((StoneZoneEntrySet.Builder<T, Block>) stairsBuilder);
                stairs = (StoneZoneEntrySet<T, Block>) stairsBuilder.build();
            }

            /// ───────────────────────────────── Wall ──────────────────────────────────
            if (createWall && wallFunction != null) {
                String prefix = getAffix(wallPath).getFirst();
                String suffix = getAffix(wallPath).getSecond();

                SimpleEntrySet.Builder<T, Block> wallBuilder = StoneZoneEntrySet.of(typeClass, suffix, prefix,
                                getModBlock(wallPath), () -> baseType,
                                wallFunction
                        )
                        .requiresFromMap(block.blocks) //REASON: Recipes, Textures
                        .setTab(tabSupplier)
                        .setRenderType(RenderLayer.CUTOUT_MIPPED);

                if (addPickaxeTag) {
                    wallBuilder.addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK);
                }
                if (addAxeTag) {
                    wallBuilder.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK);
                }
                if (addSlabAndStairsTag) {
                    wallBuilder.addTag(BlockTags.WALLS, Registries.BLOCK)
                            .addTag(ItemTags.WALLS, Registries.ITEM);
                }

                if (includeDefaultRecipe) wallBuilder.defaultRecipe();

                wallConfig.accept((StoneZoneEntrySet.Builder<T, Block>) wallBuilder);
                wall = (StoneZoneEntrySet<T, Block>) wallBuilder.build();
            }

            return new BlockSetEntries<>(
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
