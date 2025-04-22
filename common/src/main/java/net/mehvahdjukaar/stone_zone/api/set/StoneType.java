package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StoneType extends RockType {

    /**
     * Childkey Availability:
     * stone, stairs, slab, wall, button, pressure_plate, smooth_stone
     * polished, polished_stairs, polished_slab
     * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles
     **/

    public final Block stone;

    protected StoneType(ResourceLocation id, Block stone) {
        super(id, stone);
        this.stone = stone;
    }

    @Override
    public String getTranslationKey() {
        return "stone_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    public ItemLike mainChild() {
        return this.stone;
    }

    public Block bricksOrStone() {
        Block bricks = this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.stone;
    }

    public static class Finder implements SetFinder<StoneType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> stoneFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> stone) {
            this.id = id;
            this.stoneFinder = stone;
        }

        public static Finder vanilla(String nameStone) {
            return simple("minecraft", nameStone, nameStone);
        }

        public static Finder simple(String modId, String nameStoneType, String nameStone) {
            return simple(ResourceLocation.fromNamespaceAndPath(modId, nameStoneType), ResourceLocation.fromNamespaceAndPath(modId, nameStone));
        }

        public static Finder simple(ResourceLocation nameStoneTYpe, ResourceLocation nameStone) {
            return new Finder(nameStoneTYpe,
                    () -> BuiltInRegistries.BLOCK.get(nameStone));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, id.withPath(childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<StoneType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block stone = stoneFinder.get();
                    var d = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (stone != d && stone != null) {
                        var w = new StoneType(id, stone);
                        childNames.forEach((key, value) -> w.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(w);
                    }
                } catch (Exception ignored) {
                }
                StoneZone.LOGGER.warn("Failed to find custom stone type {}", id);
            }
            return Optional.empty();
        }
    }

}
