package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        Block bricks = this.findBrickEntry("", "");
        Block bricksTFC = this.findRelatedBlock("", "bricks");
        if (Objects.nonNull(bricks) || Objects.nonNull(bricksTFC)) {
            // Support TFC & AFC
            if (this.id.getNamespace().matches("tfc|afc")) {
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

        String prefixed = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
        String infixed = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;

        ResourceLocation[] targets = {
                // DEFAULT
                new ResourceLocation(id.getNamespace(), id.getPath() + infixed + suffixed),
                new ResourceLocation(id.getNamespace(), prefixed + id.getPath() + suffixed),
                // TFC & AFC: Include children of stone_type: stairs, slab...
                new ResourceLocation(id.getNamespace(), "rock/raw/" + id.getPath() + suffixed),
                // TFC & AFC: Include children of smooth, cobblestone, button, pressure_plate, bricks, cracked_bricks
                new ResourceLocation(id.getNamespace(), "rock/" + prefixed + suffix +"/"+ id.getPath()),
                // TFC & AFC: Include children of brick_slab, smooth_slab, brick_stairs, smooth_stairs
                new ResourceLocation(id.getNamespace(), "rock/"+ prefixOrInfix +"/"+ id.getPath() + suffixed)
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

    protected static ResourceLocation[] makeKnownIDConventions(ResourceLocation id, String... suffixKeyword) {
        List<ResourceLocation> resources = new ArrayList<>();
        for (String keyword : suffixKeyword) {
            String path = id.getPath();
            String namespace = id.getNamespace();

            String suffixed = (keyword.isEmpty()) ? "" : "_" + keyword;
            String prefixed = (keyword.isEmpty()) ? "" : keyword + "_";

            resources.add(new ResourceLocation(namespace, path + suffixed));
            resources.add(new ResourceLocation(namespace, prefixed + path));
        }
        return resources.toArray(new ResourceLocation[0]);
    }

}
