package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class MudType extends BlockType {

    /**
     * Childkey Availability:
     * packed, bricks, brick_stairs, brick_slab, brick_wall
     **/

    public final Block mud;

    public MudType(ResourceLocation id, Block mud) {
        super(id);
        this.mud = mud;
    }

    public Block bricks() {
        Block bricks = (Block) this.getChild("bricks");
        return (bricks != null) ? bricks : Blocks.MUD_BRICKS;
    }

    @Override
    public String getTranslationKey() {
        return "mud_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("mud", this.mud);
        this.addChild("packed", this.findRelatedEntry("packed", "", BuiltInRegistries.BLOCK));
        Block bricks = this.findRelatedEntry("bricks", BuiltInRegistries.BLOCK);
        this.addChild("bricks", bricks);
        if (bricks != null) {
            this.addChild("brick_stairs", this.findRelatedEntry("brick_stairs", BuiltInRegistries.BLOCK));
            this.addChild("brick_slab", this.findRelatedEntry("brick_slab", BuiltInRegistries.BLOCK));
            this.addChild("brick_wall", this.findRelatedEntry("brick_wall", BuiltInRegistries.BLOCK));
        }
    }

    @Override
    protected void initializeChildrenItems() {

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

        public static MudType.Finder vanilla(String nameMud){
            return simple("minecraft", nameMud, nameMud);
        }

        public static MudType.Finder simple(String modId, String nameMudType, String nameMud) {
            return simple(new ResourceLocation(modId, nameMudType), new ResourceLocation(modId, nameMud));
        }

        public static MudType.Finder simple(ResourceLocation nameMudType, ResourceLocation nameMud) {
            return new MudType.Finder(nameMudType,
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
                    var d = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (mud != d && mud != null) {
                        var w = new MudType(id, mud);
                        childNames.forEach((key, value) -> w.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(w);
                    }
                } catch (Exception ignored) {
                }
                StoneZone.LOGGER.warn("Failed to find custom mud type {}", id);
            }
            return Optional.empty();
        }
    }
}
