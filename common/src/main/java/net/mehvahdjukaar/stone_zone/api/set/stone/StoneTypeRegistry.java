package net.mehvahdjukaar.stone_zone.api.set.stone;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Optional;

import static net.mehvahdjukaar.stone_zone.api.set.RockType.isInItemRegistry;
import static net.mehvahdjukaar.stone_zone.api.set.RockType.newSubBlockType;
import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;
import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_STONETYPES;


@SuppressWarnings("unused")
public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public StoneTypeRegistry() {
        super(StoneType.class, "stone_type");
    }

    @Override
    protected StoneType register(StoneType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public StoneType getDefaultType() {
        return VanillaStoneTypes.STONE;
    }

    public static StoneType getStoneType(ResourceLocation id) {
        return INSTANCE.get(id);
    }

    @Override
    public Optional<StoneType> detectTypeFromBlock(Block baseblock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
        String blockPath = blockId.getPath();

        /// ─────────── Support Terrafirmacraft (tfc) & Arborfirmacraft (afc) ───────────
        if (namespace.matches("tfc|afc")) {
            return newSubBlockType(StoneType::new, baseblock, blockId,
                    "rock/raw/(?<typename>\\w+)(?<!slab|stairs)", valuesReg);
        }

        /// ────────────────────────────────── Default ──────────────────────────────────
        if (!BLACKLISTED_MODS.contains(blockId.getNamespace())) {
            /// Ensure that detected BlockType is actually StoneType
            boolean noDustType = !isInItemRegistry(namespace, blockPath, "(?<name>[a-z]+_)\\w+", "${name}dust");
            boolean noOreType = !isInItemRegistry(namespace, blockPath, "(?<name>[a-z]+_)\\w+", "${name}ore");
            boolean noWoodType = !isInItemRegistry(namespace, blockPath, "(?<name>[a-z]+_)[a-z]+", "${name}log");

            // Check for <type>_bricks | <type>_stone_bricks - cracked, chiseled, mossy are exclued
            if (blockPath.matches("[a-z]+_(?:(?<!(?:cracked|chiseled|mossy)_)stone_)?bricks?")) {

                return newSubBlockType(StoneType::new, baseblock, blockId,
                        "(?<typename>[a-z]+(_stone)?)_bricks?", valuesReg,
                        BLACKLISTED_STONETYPES, noDustType, noOreType, noWoodType
                );
            }
            // Check for polished_<type> | polished_<type>_stone
            else if (blockPath.matches("polished_[a-z]+(?:_stone)?")) {

                return newSubBlockType(StoneType::new, baseblock, blockId,
                        "polished_(?<typename>[a-z]+(_stone)?)", valuesReg,
                        BLACKLISTED_STONETYPES, noDustType, noOreType, noWoodType
                );
            }
        }
        return Optional.empty();
    }

    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public StoneType.Finder addSimpleFinder(ResourceLocation stoneTypeId) {
        StoneType.Finder finder = new StoneType.Finder(stoneTypeId);
        this.addFinder(finder);
        return finder;
    }

    public StoneType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(ResourceLocation.parse(typeId));
    }

    public StoneType.Finder addSimpleFinder(String namespace, String nameStoneType) {
        return addSimpleFinder(ResourceLocation.fromNamespaceAndPath(namespace, nameStoneType));
    }

    @Override
    public int priority() {
        return 110;
    }

    //!! ───────────────────────────────────────────── Marked For Removal ──────────────────────────────────────────────
    /// USE {@link VanillaStoneTypes#STONE}
    @Deprecated(forRemoval = true)
    public static StoneType getStoneType() {
        return getValue("stone");
    }

    /// USE {@link VanillaStoneTypes#ANDESITE}
    @Deprecated(forRemoval = true)
    public static StoneType getAndesiteType() {
        return getValue("andesite");
    }

    /// USE {@link VanillaStoneTypes}
    @Deprecated(forRemoval = true)
    public static StoneType getValue(String stoneTypeId) {
        return INSTANCE.get(ResourceLocation.parse(stoneTypeId));
    }

    /// USE {@link StoneTypeRegistry#INSTANCE} - can be used in FOR Loop statement
    @Deprecated(forRemoval = true)
    public static Collection<StoneType> getTypes() {
        return INSTANCE.getValues();
    }
}
