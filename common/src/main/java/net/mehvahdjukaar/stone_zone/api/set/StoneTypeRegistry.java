package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;
import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_STONETYPES;


@SuppressWarnings("unused")
public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    /* NOTE:
     * Do not remove below because crsah will occurred and I do not know why
     * the error is Cannot read field "id" because "newType" is null
     */
    public static final StoneType STONE_TYPE = new StoneType(ResourceLocation.withDefaultNamespace("stone"), Blocks.STONE);

    public StoneTypeRegistry() {
        super(StoneType.class, "stone_type");

//        this.addFinder(StoneType.Finder.vanilla("stone")); // currently disabled for now
        this.addFinder(StoneType.Finder.vanilla("andesite"));
        this.addFinder(StoneType.Finder.vanilla("diorite"));
        this.addFinder(StoneType.Finder.vanilla("granite"));
        this.addFinder(StoneType.Finder.vanilla("tuff"));
        this.addFinder(StoneType.Finder.vanilla("calcite"));
        this.addFinder(StoneType.Finder.vanilla("blackstone"));
    }

    public static StoneType getStoneType() {
        return getValue("stone");
    }

    public static StoneType getAndesiteType() {
        return getValue("andesite");
    }

    public static StoneType getGraniteType() {
        return getValue("granite");
    }

    public static Collection<StoneType> getTypes() {
        return INSTANCE.getValues();
    }

    public static StoneType getValue(String stoneTypeId) {
        return INSTANCE.get(ResourceLocation.parse(stoneTypeId));
    }

    @Override
    public StoneType getDefaultType() {
        return STONE_TYPE;
    }

    @Override
    public Optional<StoneType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String blockPath = baseRes.getPath();
        /// Support TerraFirmaCraft (TFC) & ArborFirmaCraft (AFC)
        if (baseRes.getNamespace().matches("tfc|afc")) {

            if (blockPath.matches("rock/bricks/\\w+")) {
                int index = blockPath.lastIndexOf("/");
                String stoneName = blockPath.substring(index + 1); // Get granite from tfc:rock/bricks/granite
                var opt = BuiltInRegistries.BLOCK.getOptional(
                        baseRes.withPath(blockPath.replace("bricks", "raw"))
                );
                if (opt.isPresent()) {
                    return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                }
            }
        }

        /// DEFAULT
        if (!BLACKLISTED_MODS.contains(baseRes.getNamespace())) {
            /// Check for TYPE_bricks | TYPE_stairs | TYPE_stone_bricks | TYPE_stone_stairs
            if (blockPath.matches("[a-z]+_(stone_)?bricks?")) {

                String stoneName = (blockPath.matches("[a-z]+_(stone_)?brick")) ? blockPath.substring(0, blockPath.length() - 6) : blockPath.substring(0, blockPath.length() - 7); // get stoneName from namespace:stoneName_bricks
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Ensure that detected BlockType is actually StoneType
                boolean isStoneTypeNotBlacklisted = !(BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneName).toString()) || BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneAlt).toString()));

                boolean noDustType = !BuiltInRegistries.ITEM.containsKey(
                        ResourceLocation.fromNamespaceAndPath(baseRes.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)\\w+", "${name}dust"))
                );
                boolean noOreType = !BuiltInRegistries.BLOCK.containsKey(
                        ResourceLocation.fromNamespaceAndPath(baseRes.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)\\w+", "${name}ore"))
                );
                boolean noWoodType = !BuiltInRegistries.BLOCK.containsKey(
                        ResourceLocation.fromNamespaceAndPath(baseRes.getNamespace(), blockPath.replaceAll("(?<name>[a-z]+_)[a-z]+", "${name}log"))
                );

                /// Check if a BlockType is already added
                if (Objects.isNull(get(idBlockType)) && Objects.isNull(get(idBlockTypeAlt))
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
            /// Check for polished_TYPE | polished_TYPE_stone
            else if (blockPath.matches("polished_[a-z]+(?:_stone)?")) {
                String stoneName = blockPath.replace("polished_", ""); // get stoneName from namespace:polished_stoneName
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Ensure that detected BlockType is actually StoneType
                boolean isStoneTypeBlacklisted = !(BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneName).toString()) || BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneAlt).toString()));

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType))
                        && Objects.isNull(get(idBlockTypeAlt))
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

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((stoneType) -> {
            if (language.isDefault()) language.addEntry(stoneType.getTranslationKey(), stoneType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
