package net.mehvahdjukaar.stone_zone.api.set.stone;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Optional;

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

    @Override
    public Optional<StoneType> detectTypeFromBlock(Block baseblock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
        String blockPath = blockId.getPath();

        /// Support TerraFirmaCraft (TFC) & ArborFirmaCraft (AFC)
        if (namespace.matches("tfc|afc")) {
            newSubBlockType(StoneType::new, blockId, blockPath, "rock/bricks/(?<typename>\\w+)", valuesReg);
        }

        /// DEFAULT
        boolean noDustType = !BuiltInRegistries.ITEM.containsKey(
                new ResourceLocation(blockId.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)\\w+", "${name}dust"))
        );
        boolean noOreType = !BuiltInRegistries.BLOCK.containsKey(
                new ResourceLocation(blockId.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)\\w+", "${name}ore"))
        );
        boolean noWoodType = !BuiltInRegistries.BLOCK.containsKey(
                new ResourceLocation(blockId.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)[a-z]+", "${name}log"))
        );

        if (blockPath.matches("[a-z]+_(?:stone_)?bricks?"))
            return newSubBlockType(StoneType::new, blockId, blockPath, "(?<typename>[a-z]+(?:_stone)?)_bricks?",
                    valuesReg, BLACKLISTED_STONETYPES, BLACKLISTED_MODS,
                    noDustType, noOreType, noWoodType);

            // Check for polished_<type> | polished_<type>_stone
        else if (blockPath.matches("polished_[a-z]+(?:_stone)?")) {
            return newSubBlockType(StoneType::new, blockId, blockPath, "polished_(?<typename>[a-z]+(?:_stone)?)",
                    valuesReg, BLACKLISTED_STONETYPES, BLACKLISTED_MODS,
                    noDustType, noOreType, noWoodType);
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
        return addSimpleFinder(new ResourceLocation(typeId));
    }

    public StoneType.Finder addSimpleFinder(String namespace, String nameStoneType) {
        return addSimpleFinder(new ResourceLocation(namespace, nameStoneType));
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
        return INSTANCE.get(new ResourceLocation(stoneTypeId));
    }

    /// USE {@link StoneTypeRegistry#INSTANCE} - can be used in FOR Loop statement
    @Deprecated(forRemoval = true)
    public static Collection<StoneType> getTypes() {
        return INSTANCE.getValues();
    }
}
