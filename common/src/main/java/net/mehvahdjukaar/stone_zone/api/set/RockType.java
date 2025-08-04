package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Childkey Availability:
 * <Ul>
 * stone, stairs, slab, wall, button, pressure_plate,
 * smooth, smooth_stairs, smooth_slab, smooth_wall,
 * cobblestone, mossy_cobblestone,
 * polished, polished_stairs, polished_slab, polished_wall
 * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles,
 * mossy_bricks, mossy_brick_slab, mossy_brick_stairs, mossy_brick_wall
 * </Ul>
**/
public abstract class RockType extends BlockType{

    public final Block block;

    protected RockType(ResourceLocation id, Block stone) {
        super(id);
        this.block = stone;
    }

    @Override
    public String getAppendableIdWith(String prefix, String suffix) {
        String suffixed = (suffix.isEmpty()) ? "" : "_" + suffix;
        String prefixed = (prefix.isEmpty()) ? "" : prefix + "_";
        return  this.getNamespace() +"/"+ prefixed + this.getTypeName() + suffixed;
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("stone", this.block);
        this.addChild("stairs", this.findRelatedBlock("", "stairs"));
        this.addChild("slab", this.findRelatedBlock("", "slab"));
        this.addChild("wall", this.findRelatedBlock("", "wall"));
        this.addChild("button", this.findRelatedBlock("", "button"));
        this.addChild("pressure_plate", this.findRelatedBlock("", "pressure_plate"));

        Block cobblestone = this.findCobblestoneEntry("", "");
        if (Objects.nonNull(cobblestone)) {
            this.addChild("cobblestone", cobblestone);
            this.addChild("mossy_cobblestone", this.findRelatedBlock("mossy",""));
        }

        Block polished = this.findRelatedBlock("polished", "");
        if (Objects.nonNull(polished)) {
            this.addChild("polished", polished);
            this.addChild("polished_stairs", findRelatedBlock("polished", "stairs"));
            this.addChild("polished_slab", findRelatedBlock("polished", "slab"));
            this.addChild("polished_wall", findRelatedBlock("polished", "wall"));
        }

        Block smooth = this.findRelatedBlock("smooth", "");
        if (Objects.nonNull(smooth)) {
            this.addChild("smooth", smooth);
            this.addChild("smooth_stairs", findRelatedBlock("smooth", "stairs"));
            this.addChild("smooth_slab", findRelatedBlock("smooth", "slab"));
            this.addChild("smooth_wall", findRelatedBlock("smooth", "wall"));
        }

        Block bricks = this.findBrickEntry("", "");
        Block bricksTFC = this.findRelatedBlock("", "bricks");
        if (Objects.nonNull(bricks) || Objects.nonNull(bricksTFC)) {
            // Support TFC & AFC
            if (this.id.getNamespace().matches("tfc|afc")) {
                this.addChild("bricks", bricksTFC);
                this.addChild("brick_stairs", findRelatedBlock("bricks", "stairs"));
                this.addChild("brick_slab", findRelatedBlock("bricks", "slab"));
                this.addChild("brick_wall", findRelatedBlock("bricks", "wall"));
                this.addChild("cracked_bricks", findRelatedBlock("cracked_bricks", ""));
            }
            else {
                this.addChild("bricks", bricks);
                this.addChild("brick_stairs", findBrickEntry("", "stairs"));
                this.addChild("brick_slab", findBrickEntry("", "slab"));
                this.addChild("brick_wall", findBrickEntry("", "wall"));
                this.addChild("brick_tiles", findBrickEntry("", "tiles"));
                this.addChild("cracked_bricks", findBrickEntry("cracked", ""));
                this.addChild("mossy_bricks", findBrickEntry("mossy", ""));
                this.addChild("mossy_brick_slab", findBrickEntry("mossy", "slab"));
                this.addChild("mossy_brick_stairs", findBrickEntry("mossy", "stairs"));
                this.addChild("mossy_brick_wall", findBrickEntry("mossy", "wall"));
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
            return reg.get(ResourceLocation.withDefaultNamespace("cobblestone"));
        }

        String prefix = (prefixOrInfix.isEmpty()) ? "" : prefixOrInfix + "_";
        String infix = (prefixOrInfix.isEmpty()) ? "" : "_" + prefixOrInfix;
        String withoutUnderscore = suffix;

        if (!suffix.isEmpty() && !prefixOrInfix.isEmpty()) suffix = "_" + suffix;
        ResourceLocation[] targets = {
                // DEFAULT
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + infix + suffix),
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), prefix + id.getPath() + suffix),
                // TFC & AFC: Include children of stone_type: stairs, slab...
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "rock/raw/" + id.getPath() +"_"+ prefixOrInfix),
                // TFC & AFC: Include children of smooth, cobblestone, button, pressure_plate, bricks, cracked_bricks
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "rock/" + prefixOrInfix + suffix +"/"+ id.getPath()),
                // TFC & AFC: Include children of brick_slab, smooth_slab, brick_stairs, smooth_stairs
                ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "rock/"+ prefixOrInfix +"/"+ id.getPath() + suffix)
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

}
