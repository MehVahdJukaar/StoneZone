package net.mehvahdjukaar.stone_zone.type;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class StoneType extends BlockType {

    public final Block stone;

    protected StoneType(ResourceLocation id, Block stone) {
        super(id);
        this.stone = stone;
    }

    @Override
    public String getTranslationKey() {
        return "stone_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("stone", this.stone);
        this.addChild("stairs", this.findRelatedEntry("stairs", BuiltInRegistries.BLOCK));
        this.addChild("slab", this.findRelatedEntry("slab", BuiltInRegistries.BLOCK));
        this.addChild("wall", this.findRelatedEntry("wall", BuiltInRegistries.BLOCK));
        this.addChild("button", this.findRelatedEntry("button", BuiltInRegistries.BLOCK));
        this.addChild("pressure_plate", this.findRelatedEntry("pressure_plate", BuiltInRegistries.BLOCK));
        this.addChild("smooth_stone", this.findRelatedEntry("smooth", "stone", BuiltInRegistries.BLOCK));

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
                this.addChild("brick_stairs", findBrickEntry("stairs"));
                this.addChild("brick_slab", findBrickEntry("slab"));
                this.addChild("brick_wall", findBrickEntry("wall"));
                this.addChild("cracked_bricks", findBrickEntry("cracked", "bricks"));
                this.addChild("brick_tiles", findBrickEntry("brick_tiles"));
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

    private @Nullable Block findBrickEntry(String name) {
        var first = this.findRelatedEntry("brick_" + name, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("bricks_" + name, BuiltInRegistries.BLOCK);
    }

    @SuppressWarnings("SameParameterValue")
    private @Nullable Block findBrickEntry(String pre, String post) {
        var first = this.findRelatedEntry(pre,"brick_" + post, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(pre,"bricks_" + post, BuiltInRegistries.BLOCK);
    }

    @Nullable
    protected <V> V findRelatedEntry(String prefix, String suffix, Registry<V> reg) {
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

    protected <V> V findRelatedEntry(String prefix, Registry<V> reg) {
        return findRelatedEntry(prefix, "", reg);
    }

    @Override
    public ItemLike mainChild() {
        return stone;
    }

    public Block bricksOrStone() {
        Block bricks= this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.stone;
    }

    public static class Finder implements SetFinder<StoneType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> planksFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> planks) {
            this.id = id;
            this.planksFinder = planks;
        }

        static Finder vanilla(String stoneName){
            return simple("minecraft", stoneName, stoneName);
        }

        public static Finder simple(String modId, String stoneTypeName, String stoneName) {
            return simple(new ResourceLocation(modId, stoneTypeName), new ResourceLocation(modId, stoneName));
        }

        public static Finder simple(ResourceLocation stoneTypeName, ResourceLocation stoneName) {
            return new Finder(stoneTypeName,
                    () -> BuiltInRegistries.BLOCK.get(stoneName));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<StoneType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block plank = planksFinder.get();
                    var d = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (plank != d && plank != null) {
                        var w = new StoneType(id, plank);
                        childNames.forEach((key, value) -> w.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(w);
                    }
                } catch (Exception ignored) {
                }
                Moonlight.LOGGER.warn("Failed to find custom stone type {}", id);
            }
            return Optional.empty();
        }
    }

}
