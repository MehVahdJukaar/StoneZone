package net.mehvahdjukaar.stone_zone.api.set.stone;

import com.google.common.base.Preconditions;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.RockType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Childkey Availability:
 * <Ul>
 * stone, stairs, slab, wall, button, pressure_plate,
 * smooth, smooth_stairs, smooth_slab, smooth_wall,
 * cobblestone, mossy_cobblestone,
 * polished, polished_stairs, polished_slab,
 * bricks, brick_stairs, brick_slab, brick_wall, cracked_bricks, brick_tiles,
 * mossy_bricks, mossy_brick_slab, mossy_brick_stairs, mossy_brick_wall
 * </Ul>
**/
public class StoneType extends RockType {

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
    protected void initializeChildrenBlocks() {
        super.initializeChildrenBlocks();
    }

    @Override
    public ItemLike mainChild() {
        return this.stone;
    }

    public Block bricksOrStone() {
        Block bricks= this.getBlockOfThis("bricks");
        return bricks != null ? bricks : this.stone;
    }

    public static Block findStone(ResourceLocation id) {
        ResourceLocation[] tests = makeKnownIDConventions(id,  "", "stone");
        return Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);
    }

    public static class Finder extends SetFinderBuilder<StoneType> {

        private Supplier<Block> stoneFinder;

        public Finder(ResourceLocation id) {
            super(id, StoneTypeRegistry.INSTANCE);
            this.stone(() -> findStone(id));
        }

        public Finder stone(Supplier<Block> stoneFinder) {
            this.stoneFinder = stoneFinder;
            return this;
        }

        /// @param id Full Id of MudType as ResourceLocation
        public Finder stone(ResourceLocation id) {
            return this.stone(() -> BuiltInRegistries.BLOCK.getOptional(id)
                    .orElseThrow(() -> new IllegalStateException("Failed to find stone block: " + id))
            );
        }

        /// /// @param stoneName name of Stone Block without modId or namespace
        public Finder stone(String nameStone) {
            return this.stone(Utils.idWithOptionalNamespace(nameStone, id.getNamespace()));
        }

        /**
         * @param prefix include the underscore, "_" if the blockId has one
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public Finder stoneAffix(String prefix, String suffix) {
            return stone(prefix + id.getPath() + suffix);
        }

        /**
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public Finder stoneSuffix(String suffix) {
            return stone(id.getPath() + suffix);
        }

        @Override
        @ApiStatus.Internal
        public Optional<StoneType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block stone = Preconditions.checkNotNull(stoneFinder.get(), "Manual Finder - failed to find a stone block for {}", id);
                    var stoneType = new StoneType(id, stone);
                    childNames.forEach((key, value) -> {
                        try {
                            ItemLike obj = Preconditions.checkNotNull(value.get());
                            stoneType.addChild(key, obj);
                        } catch (Exception e) {
                            StoneZone.LOGGER.warn("Failed to get children for StoneType: {} - {}. Ignored! ERROR: {}", id, key, e.getMessage());
                        }
                    });
                    return Optional.of(stoneType);
                } catch (Exception e) {
                    StoneZone.LOGGER.warn("Failed to find custom StoneType: {} - ", id, e);
                }
            }
            return Optional.empty();
        }
    }

}
