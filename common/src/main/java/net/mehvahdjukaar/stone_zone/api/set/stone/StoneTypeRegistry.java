package net.mehvahdjukaar.stone_zone.api.set.stone;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Optional;

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
    public Optional<StoneType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String blockPath = baseRes.getPath();
        /// Support TerraFirmaCraft (TFC) & ArborFirmaCraft (AFC)
        if (baseRes.getNamespace().matches("tfc|afc")) {
            if (blockPath.matches("rock/bricks/\\w+") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM ) {
                int index = blockPath.lastIndexOf("/");
                String stoneName = blockPath.substring(index + 1); // Get granite from tfc:rock/bricks/granite
                var opt = BuiltInRegistries.BLOCK.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replace("bricks", "raw"))
                );
                if (opt.isPresent()) {
                    return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                }
            }
        }

        /// DEFAULT
        if (!BLACKLISTED_MODS.contains(baseRes.getNamespace())) {
            // Check for <type>_bricks | <type>_stone_bricks
            if (blockPath.matches("[a-z]+_(stone_)?bricks?")) {
                String stoneName = blockPath.substring(0, blockPath.length() - 7); // get stoneName from namespace:stoneName_bricks
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                /// Ensure that detected BlockType is actually StoneType
                boolean isStoneTypeNotBlacklisted = !(BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneName).toString()) || BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneAlt).toString()));

                boolean noDustType = !BuiltInRegistries.ITEM.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)\\w+", "${name}dust"))
                );
                boolean noOreType = !BuiltInRegistries.BLOCK.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)\\w+", "${name}ore"))
                );
                boolean noWoodType = !BuiltInRegistries.BLOCK.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)[a-z]+", "${name}log"))
                );

                /// Check if a BlockType is already added
                if (!valuesReg.containsKey(idBlockType) && !valuesReg.containsKey(idBlockTypeAlt)
                        && isStoneTypeNotBlacklisted
                        && noDustType
                        && noOreType
                        && noWoodType
                ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    var alt = BuiltInRegistries.BLOCK.getOptional(idBlockTypeAlt);
                    if (opt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                    else if (alt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneAlt), alt.get()));
                }

            }
            // Check for polished_<type> | polished_<type>_stone
            else if (blockPath.matches("polished_[a-z]+(?:_stone)?")) {
                String stoneName = blockPath.replace("polished_", ""); // get stoneName from namespace:polished_stoneName
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Ensure that detected BlockType is actually StoneType
                boolean isStoneTypeBlacklisted = !(BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneName).toString()) || BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneAlt).toString()));

                // Check if a BlockType is already added
                if (!valuesReg.containsKey(idBlockType) && !valuesReg.containsKey(idBlockTypeAlt)
                        && isStoneTypeBlacklisted
                ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    var alt = BuiltInRegistries.BLOCK.getOptional(idBlockTypeAlt);
                    if (opt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                    else if (alt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneAlt), alt.get()));
                }
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
}
