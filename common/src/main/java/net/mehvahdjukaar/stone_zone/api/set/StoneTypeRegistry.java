package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;
import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_STONETYPES;


@SuppressWarnings("unused")
public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public StoneTypeRegistry() {
        super(StoneType.class, "stone_type");

        this.addFinder(StoneType.Finder.vanilla("stone"));
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
        return INSTANCE.get(new ResourceLocation(stoneTypeId));
    }

    @Override
    public StoneType getDefaultType() {
        return this.get(new ResourceLocation("stone"));
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
            // Check for <type>_bricks | <type>_stairs | <type>_stone_bricks | <type>_stone_stairs
            if (blockPath.matches("[a-z]+(?:_(?:bricks?|stairs)|_stone_(?:bricks?|stairs))") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM ) {
                String stoneName = blockPath.substring(0, blockPath.length() - 7); // get stoneName from namespace:stoneName_bricks
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Ensure that detected BlockType is actually StoneType
                boolean isStoneTypeBlacklisted = !(BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneName).toString()) || BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneAlt).toString()));

                boolean noOreType = !BuiltInRegistries.ITEM.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replaceAll("([a-z]+_)\\w+", "$1ore"))
                );
                boolean noWoodType = !BuiltInRegistries.ITEM.containsKey(
                        new ResourceLocation(baseRes.getNamespace(), blockPath.replace("block", "log"))
                );

                // Check if a BlockType is already added
                if (( Objects.isNull(get(idBlockType)) && Objects.isNull(get(idBlockTypeAlt)) )
                        && isStoneTypeBlacklisted
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
            else if (blockPath.matches("polished_[a-z]+(?:_stone)?") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM ) {
                String stoneName = blockPath.replace("polished_", ""); // get stoneName from namespace:polished_stoneName
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Ensure that detected BlockType is actually StoneType
                boolean isStoneTypeBlacklisted = !(BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneName).toString()) || BLACKLISTED_STONETYPES.contains(baseRes.withPath(stoneAlt).toString()));

                // Check if a BlockType is already added
                if (( Objects.isNull(get(idBlockType)) && Objects.isNull(get(idBlockTypeAlt)) )
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
