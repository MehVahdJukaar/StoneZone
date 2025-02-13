package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class RockType extends BlockType{
    /**
     * Childkey Availability:
     * stone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles,
     * mossy_bricks, mossy_brick_slab, mossy_brick_stairs, mossy_brick_wall
     **/

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
        this.addChild("stairs", this.findRelatedEntry("stairs", BuiltInRegistries.BLOCK));
        this.addChild("slab", this.findRelatedEntry("slab", BuiltInRegistries.BLOCK));
        this.addChild("wall", this.findRelatedEntry("wall", BuiltInRegistries.BLOCK));
        this.addChild("button", this.findRelatedEntry("button", BuiltInRegistries.BLOCK));
        this.addChild("pressure_plate", this.findRelatedEntry("pressure_plate", BuiltInRegistries.BLOCK));
        this.addChild("smooth_stone", this.findRelatedEntry("smooth", "stone", BuiltInRegistries.BLOCK));
        this.addChild("cobblestone", this.findRelatedEntry("", "cobblestone", BuiltInRegistries.BLOCK));

        Block polished = this.findRelatedEntry("polished", BuiltInRegistries.BLOCK);
        this.addChild("polished", polished);
        if (Objects.nonNull(polished)) {
            this.addChild("polished_stairs", findRelatedEntry("polished", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("polished_slab", findRelatedEntry("polished", "slab", BuiltInRegistries.BLOCK));
        }

        Block bricks = this.findRelatedEntry("bricks", BuiltInRegistries.BLOCK);
        this.addChild("bricks", bricks);
        if (bricks != null) {
            // Support TFC & AFC
            if (this.id.getNamespace().matches("tfc|afc")) {
                this.addChild("brick_stairs", findRelatedEntry("bricks", "stairs", BuiltInRegistries.BLOCK));
                this.addChild("brick_slab", findRelatedEntry("bricks", "slab", BuiltInRegistries.BLOCK));
                this.addChild("brick_wall", findRelatedEntry("bricks", "wall", BuiltInRegistries.BLOCK));
                this.addChild("cracked_bricks", findRelatedEntry("cracked_bricks",  BuiltInRegistries.BLOCK));
            }
            else {
                this.addChild("brick_stairs", findChildBrickEntry("stairs"));
                this.addChild("brick_slab", findChildBrickEntry("slab"));
                this.addChild("brick_wall", findChildBrickEntry("wall"));
                this.addChild("brick_tiles", findChildBrickEntry("tiles"));
                this.addChild("cracked_bricks", findBrickEntry("cracked", ""));
                this.addChild("mossy_bricks", findBrickEntry("mossy", ""));
                this.addChild("mossy_brick_slab", findBrickEntry("mossy", "slab"));
                this.addChild("mossy_brick_stairs", findBrickEntry("mossy", "stairs"));
                this.addChild("mossy_brick_wall", findBrickEntry("mossy", "wall"));
            }
        }

        // Support TFC & AFC
        Block smoothTFC = findRelatedEntry("smooth", BuiltInRegistries.BLOCK);
        this.addChild("smooth_stone", smoothTFC);
        if (Objects.nonNull(smoothTFC)) {
            this.addChild("smooth_stairs", findRelatedEntry("smooth", "stairs", BuiltInRegistries.BLOCK));
            this.addChild("smooth_slab", findRelatedEntry("smooth", "slab", BuiltInRegistries.BLOCK));
            this.addChild("smooth_wall", findRelatedEntry("smooth", "wall", BuiltInRegistries.BLOCK));
        }
    }

    @Override
    protected void initializeChildrenItems() {
        this.addChild("brick", this.findRelatedEntry("brick", BuiltInRegistries.ITEM));
    }

    /// @param suffix concatenation of "brick_" + suffix
    private @Nullable Block findChildBrickEntry(String suffix) {
        var first = this.findRelatedEntry("brick_" + suffix, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("bricks_" + suffix, BuiltInRegistries.BLOCK);
    }

    @SuppressWarnings("SameParameterValue")
    private @Nullable Block findBrickEntry(String pre, String post) {
        post = (post.isEmpty()) ? "" : "_" + post;

        var first = this.findRelatedEntry(pre,"brick" + post, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(pre,"bricks" + post, BuiltInRegistries.BLOCK);
    }

    @Override
    protected @Nullable <V> V findRelatedEntry(String prefix, String suffix, Registry<V> reg) {
        if (!suffix.isEmpty()) suffix = "_" + suffix;
        ResourceLocation[] targets = {
                new ResourceLocation(id.getNamespace(), id.getPath() + "_" + prefix + suffix),
                new ResourceLocation(id.getNamespace(), prefix + "_" + id.getPath() + suffix),
                // TFC & AFC: Include children of stone_type: stairs, slab...
                new ResourceLocation(id.getNamespace(), "rock/raw/" + id.getPath() + "_" + prefix),
                // TFC & AFC: Include children of smooth, cobblestone, button, pressure_plate, bricks, cracked_bricks
                new ResourceLocation(id.getNamespace(), "rock/" + prefix + suffix + "/" + id.getPath()),
                // TFC & AFC: Include children of brick_slab, smooth_slab, brick_stairs, smooth_stairs
                new ResourceLocation(id.getNamespace(), "rock/"+ prefix +"/" + id.getPath() + suffix)
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

    @Override
    protected <V> V findRelatedEntry(String prefix, Registry<V> reg) {
        return findRelatedEntry(prefix, "", reg);
    }

    @Override
    public ItemLike mainChild() {
        return block;
    }

    public Block bricksOrStone() {
        Block bricks= this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.block;
    }

}
