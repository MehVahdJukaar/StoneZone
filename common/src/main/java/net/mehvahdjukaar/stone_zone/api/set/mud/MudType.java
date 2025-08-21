package net.mehvahdjukaar.stone_zone.api.set.mud;

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

public class MudType extends RockType {

    public final Block mud;

    protected MudType(ResourceLocation id, Block mud) {
        super(id, mud);
        this.mud = mud;
    }

    @Override
    public String getTranslationKey() {
        return "mud_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
        super.initializeChildrenBlocks();
        this.addChild("packed", this.findRelatedEntry("packed", BuiltInRegistries.BLOCK));
    }

    @Override
    public ItemLike mainChild() {
        return this.mud;
    }

    public static Block findMud(ResourceLocation id) {
        ResourceLocation[] tests = makeKnownIDConventions(id,  "", "mud");
        return Utils.findFirstInRegistry(BuiltInRegistries.BLOCK, tests);
    }

    public static class Finder extends SetFinderBuilder<MudType> {

        private Supplier<Block> mudFinder;

        public Finder(ResourceLocation id) {
            super(id, MudTypeRegistry.INSTANCE);
            this.mud(() -> findMud(id));
        }

        public MudType.Finder mud(Supplier<Block> mudFinder) {
            this.mudFinder = mudFinder;
            return this;
        }

        /// @param id Full Id of MudType as ResourceLocation
        public MudType.Finder mud(ResourceLocation id) {
            return this.mud(() -> BuiltInRegistries.BLOCK.getOptional(id)
                    .orElseThrow(() -> new IllegalStateException("Failed to find mud block: " + id))
            );
        }

        /// @param nameMud name of Mud Block without modId or namespace
        public MudType.Finder mud(String nameMud) {
            return this.mud(Utils.idWithOptionalNamespace(nameMud, id.getNamespace()));
        }

        /**
         * @param prefix include the underscore, "_" if the blockId has one
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public MudType.Finder mudAffix(String prefix, String suffix) {
            return mud(prefix + id.getPath() + suffix);
        }

        /**
         * @param suffix include the underscore, "_" if the blockId has one
         */
        public MudType.Finder mudSuffix(String suffix) {
            return mud(id.getPath() + suffix);
        }

        @Override
        @ApiStatus.Internal
        public Optional<MudType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block mud = Preconditions.checkNotNull(mudFinder.get(), "Manual Finder - failed to find a mud block for {}", id);
                    var mudType = new MudType(id, mud);
                    childNames.forEach((key, value) -> {
                        try {
                            ItemLike obj = Preconditions.checkNotNull(value.get());
                            mudType.addChild(key, obj);
                        } catch (Exception e) {
                            StoneZone.LOGGER.warn("Failed to get children for MudType: {} - {}. Ignored! ERROR: {}", id, key, e.getMessage());
                        }
                    });
                    return Optional.of(mudType);
                } catch (Exception e) {
                    StoneZone.LOGGER.warn("Failed to find custom MudType: {} - ", id, e);
                }
            }
            return Optional.empty();
        }
    }

}
