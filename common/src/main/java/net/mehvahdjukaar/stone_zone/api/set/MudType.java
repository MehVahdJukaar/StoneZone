package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.core.ClientConfigs;
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

import static net.mehvahdjukaar.moonlight.api.set.DebugBlockTypes.appendToDebugFile;

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
public class MudType extends RockType {

    public final Block mud;

    protected MudType(ResourceLocation id, Block mud) {
        super(id, mud);
        this.mud = mud;

        if (ClientConfigs.BLOCKTYPES_DEBUG.get() && !this.isVanilla()) appendToDebugFile(getTranslationKey());
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

    public static class Finder implements SetFinder<MudType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> mudFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> mud) {
            this.id = id;
            this.mudFinder = mud;
        }

        public static Finder vanilla(String nameMud){
            return simple("minecraft", nameMud, nameMud);
        }

        public static Finder simple(String modId, String nameMudType, String nameMud) {
            return simple(new ResourceLocation(modId, nameMudType), new ResourceLocation(modId, nameMud));
        }

        public static Finder simple(ResourceLocation nameMudTYpe, ResourceLocation nameMud) {
            return new Finder(nameMudTYpe,
                    () -> BuiltInRegistries.BLOCK.get(nameMud));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, new ResourceLocation(id.getNamespace(), childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @Override
        @ApiStatus.Internal
        public Optional<MudType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block mud = mudFinder.get();
                    var defaultKey = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey()); // minecraft:air
                    if (mud != defaultKey && mud != null) {
                        var mudType = new MudType(id, mud);
                        childNames.forEach((key, value) -> {
                            if (BuiltInRegistries.ITEM.containsKey(value)) mudType.addChild(key, BuiltInRegistries.ITEM.get(value));
                            else if (BuiltInRegistries.BLOCK.containsKey(value)) mudType.addChild(key, BuiltInRegistries.BLOCK.get(value));
                            else StoneZone.LOGGER.warn("Failed to get children for MudType: {} - {}", id, key);

                        });
                        return Optional.of(mudType);
                    }
                } catch (Exception ignored) {
                }
                StoneZone.LOGGER.warn("Failed to find custom mud type {}", id);
            }
            return Optional.empty();
        }
    }

}
