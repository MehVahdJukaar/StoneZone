package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.misc.MapRegistry;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;

/**
 * Childkey Availability:
 * <Ul>
 * STONE, STAIRS, SLAB, WALL, BUTTON, PRESSURE_PLATE,
 * SMOOTH, SMOOTH_STAIRS, SMOOTH_SLAB, SMOOTH_WALL,
 * COBBLESTONE, MOSSY_COBBLESTONE,
 * POLISHED, POLISHED_STAIRS, POLISHED_SLAB, POLISHED_WALL,
 * BRICKS, BRICK_STAIRS, BRICK_SLAB, BRICK_WALL, CRACKED_BRICKS, BRICK_TILES,
 * MOSSY_BRICKS, MOSSY_BRICK_SLAB, MOSSY_BRICK_STAIRS, MOSSY_BRICK_WALL
 * </Ul>
**/
public abstract class RockType extends BlockType{

    public final Block block;

    protected RockType(ResourceLocation id, Block block) {
        super(id);
        this.block = block;
    }

    @Override
    public String getAppendableIdWith(String prefix, String suffix) {
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;
        String prefixed = (prefix.isEmpty()) ? "" : prefix + "_";
        return  this.getNamespace() +"/"+ prefixed + this.getTypeName() + suffixed;
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("block", this.block);
        this.addChild(STAIRS, this.findRelatedBlock("", "stairs"));
        this.addChild(SLAB, this.findRelatedBlock("", "slab"));
        this.addChild(WALL, this.findRelatedBlock("", "wall"));
        this.addChild(BUTTON, this.findRelatedBlock("", "button"));
        this.addChild(PRESSURE_PLATE, this.findRelatedBlock("", "pressure_plate"));

        Block cobblestone = this.findCobblestoneEntry("", "");
        if (Objects.nonNull(cobblestone)) {
            this.addChild(COBBLESTONE, cobblestone);
            this.addChild(MOSSY_COBBLESTONE, this.findRelatedBlock("mossy",""));
        }

        Block polished = this.findRelatedBlock("polished", "");
        if (Objects.nonNull(polished)) {
            this.addChild(POLISHED, polished);
            this.addChild(POLISHED_STAIRS, findRelatedBlock("polished", "stairs"));
            this.addChild(POLISHED_SLAB, findRelatedBlock("polished", "slab"));
            this.addChild(POLISHED_WALL, findRelatedBlock("polished", "wall"));
        }

        Block smooth = this.findRelatedBlock("smooth", "");
        if (Objects.nonNull(smooth)) {
            this.addChild(SMOOTH, smooth);
            this.addChild(SMOOTH_STAIRS, findRelatedBlock("smooth", "stairs"));
            this.addChild(SMOOTH_SLAB, findRelatedBlock("smooth", "slab"));
            this.addChild(SMOOTH_WALL, findRelatedBlock("smooth", "wall"));
        }

        Block tiles = this.findRelatedBlock("", "tiles");
        if (Objects.nonNull(tiles)) {
            this.addChild(TILES, tiles);
            this.addChild(TILE_STAIRS, findRelatedBlock("", "tile_stairs"));
            this.addChild(TILE_SLAB, findRelatedBlock("", "tile_slab"));
            this.addChild(TILE_WALL, findRelatedBlock("", "tile_wall"));
        }

        Block bricks = this.findBrickEntry("", "");
        Block bricksTFC = this.findRelatedBlock("", "bricks");
        if (Objects.nonNull(bricks) || Objects.nonNull(bricksTFC)) {
            // Support TFC & AFC
            if (this.id.getNamespace().matches("tfc|afc|dfc")) {
                this.addChild(BRICKS, bricksTFC);
                this.addChild(BRICK_STAIRS, findRelatedBlock("bricks", "stairs"));
                this.addChild(BRICK_SLAB, findRelatedBlock("bricks", "slab"));
                this.addChild(BRICK_WALL, findRelatedBlock("bricks", "wall"));
                this.addChild(CRACKED_BRICKS, findRelatedBlock("cracked_bricks", ""));
            }
            else {
                this.addChild(BRICKS, bricks);
                this.addChild(BRICK_STAIRS, findBrickEntry("", "stairs"));
                this.addChild(BRICK_SLAB, findBrickEntry("", "slab"));
                this.addChild(BRICK_WALL, findBrickEntry("", "wall"));
                this.addChild(BRICK_TILES, findBrickEntry("", "tiles"));
                this.addChild(CRACKED_BRICKS, findBrickEntry("cracked", ""));
                this.addChild(MOSSY_BRICKS, findBrickEntry("mossy", ""));
                this.addChild(MOSSY_BRICK_SLAB, findBrickEntry("mossy", "slab"));
                this.addChild(MOSSY_BRICK_STAIRS, findBrickEntry("mossy", "stairs"));
                this.addChild(MOSSY_BRICK_WALL, findBrickEntry("mossy", "wall"));
            }
        }

    }

    @Override
    protected void initializeChildrenItems() {}

    @SuppressWarnings("SameParameterValue")
    /// Checking the id for "cobbled" or "cobblestone"
    private @Nullable Block findCobblestoneEntry(String prefix, String suffix) {
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;

        Block first = this.findRelatedEntry("cobbled", suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(prefix, "cobblestone" + suffixed, BuiltInRegistries.BLOCK);
    }

    /// Checking the id for "bricks" or "brick"
    private @Nullable Block findBrickEntry(String prefix, String suffix) {
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;

        Block first = this.findRelatedEntry(prefix, "brick" + suffixed, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(prefix, "bricks" + suffixed, BuiltInRegistries.BLOCK);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefixOrInfix, String suffix, Registry<V> reg) {
        if (id.toString().equals("minecraft:stone") && prefixOrInfix.equals("cobblestone")) {
            return reg.get(new ResourceLocation("cobblestone"));
        }

        String prefix_ = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
        String _infix = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
        String _suffix = (suffix.isEmpty()) ? "" : "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() + _infix + _suffix),
                new ResourceLocation(id.getNamespace(), prefix_ + id.getPath() + _suffix),

                // TFC, AFC, DFC: Include children of stone_type: stairs, slab...
                new ResourceLocation(id.getNamespace(), "rock/raw/" + id.getPath() + _suffix),
                // TFC, AFC, DFC: Include children of smooth, cobblestone, button, pressure_plate, bricks, cracked_bricks
                new ResourceLocation(id.getNamespace(), "rock/" + prefix_ + suffix +"/"+ id.getPath()),
                // TFC, AFC, DFC: Include children of brick_slab, smooth_slab, brick_stairs, smooth_stairs
                new ResourceLocation(id.getNamespace(), "rock/"+ prefixOrInfix +"/"+ id.getPath() + _suffix)
        };
        V found = null;
        for (var r : targets) {
            if (reg.containsKey(r)) {
                found = reg.get(r);
                break;
            }
        }
        return found;
    }

    private @Nullable Block findRelatedBlock(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.BLOCK);
    }

    @SuppressWarnings("unused")
    private @Nullable Item findRelatedItem(String prefixOrInfix, String suffix) {
        return findRelatedEntry(prefixOrInfix, suffix, BuiltInRegistries.ITEM);
    }

    @Override
    public ItemLike mainChild() {
        return block;
    }

    public Block bricksOrStone() {
        Block bricks = this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.block;
    }

    protected static ResourceLocation[] makeKnownIDConventions(ResourceLocation id, String... affixKeyword) {
        List<ResourceLocation> resources = new ArrayList<>();
        for (String keyword : affixKeyword) {
            String path = id.getPath();
            String namespace = id.getNamespace();

            String _suffix = (keyword.isEmpty()) ? "" : "_" + keyword;
            String prefix_ = (keyword.isEmpty()) ? "" : keyword + "_";

            resources.add(new ResourceLocation(namespace, path + _suffix));
            resources.add(new ResourceLocation(namespace, prefix_ + path));
            if (PlatHelper.isModLoaded("tfc") | PlatHelper.isModLoaded("afc") | PlatHelper.isModLoaded("dfc")) {
                resources.add(new ResourceLocation(namespace, "rock/raw/" + path));
            }
        }
        return resources.toArray(new ResourceLocation[0]);
    }

    /// Create an Id stonezone:shortenedId/namespace/prefix_ stonetype _suffix
    public String CreateStandardId(String shortenedId, String prefix, String suffix) {
        return createFullIdWith(StoneZone.MOD_ID, "", shortenedId, prefix, suffix);
    }

    /// Create new BlockType using the subclass: CrystalType, DustType, GemType, Or MetalType
    /// @param factory use 1 of 4 subclass, example: CrystalType::new
    /// @param blockPathRegex Must have (?\<typename\>) in RegEx for the name of StoneType
    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    @Nullable Set<String> blockTypeBlacklist,
                                                                    @Nullable Set<String> modBlacklist,
                                                                    boolean ... checks
    ) {
        Pattern regex = Pattern.compile(blockPathRegex);
        Matcher matcher = regex.matcher(blockPath);

        if (matcher.find()) {

            String blocktypeName = matcher.group("typename");

            ResourceLocation idBlockType = blockId.withPath(blocktypeName);

            boolean isModNotBlacklisted = !(modBlacklist != null && modBlacklist.contains(blockId.getNamespace()));
            boolean isBlockTypeNotBlacklisted = !(blockTypeBlacklist != null && blockTypeBlacklist.contains(idBlockType.toString()));

            if (!valuesReg.containsKey(idBlockType) && isModNotBlacklisted && isBlockTypeNotBlacklisted) {

                for (boolean pass : checks) {
                    if (!pass) return Optional.empty();
                }

                ResourceLocation[] tests = makeKnownIDConventions(idBlockType, "", "block");
                Block block = Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);

                if (block != null) {
                    return Optional.of(factory.apply(idBlockType, block));
                }
            }
        }
        return Optional.empty();
    }

    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    Set<String> blockTypeBlacklist
    ) {
        return newSubBlockType(factory, blockId, blockPath, blockPathRegex, valuesReg, blockTypeBlacklist, true);
    }

    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    Set<String> blockTypeBlacklist,
                                                                    boolean ... checks
    ) {
        return newSubBlockType(factory, blockId, blockPath, blockPathRegex, valuesReg, blockTypeBlacklist, null, checks);
    }

    public static <T extends BlockType> Optional<T> newSubBlockType(BiFunction<ResourceLocation, Block, T> factory,
                                                                    ResourceLocation blockId,
                                                                    String blockPath, String blockPathRegex,
                                                                    MapRegistry<T> valuesReg,
                                                                    boolean ... checks
    ) {
        return newSubBlockType(factory, blockId, blockPath, blockPathRegex, valuesReg, null, null, checks);
    }

    public static boolean isInItemRegistry(String namespace, String prefix, String blockPath, String target, String replacement) {
        return BuiltInRegistries.ITEM.containsKey(
                new ResourceLocation(namespace,prefix + blockPath.replace(target, replacement))
        );
    }
    public static boolean isInItemRegistry(String namespace, String blockPath, String target, String replacement) {
        return BuiltInRegistries.ITEM.containsKey(
                new ResourceLocation(namespace, blockPath.replace(target, replacement))
        );
    }



}
