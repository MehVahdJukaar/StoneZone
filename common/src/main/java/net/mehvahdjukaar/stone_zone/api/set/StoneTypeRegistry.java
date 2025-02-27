package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.*;

import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;


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
        String path = baseRes.getPath();
        // Support TerraFirmaCraft (TFC) & ArborFirmaCraft (AFC)
        if (baseRes.getNamespace().matches("tfc|afc")) {
            if (path.matches("rock/bricks/\\w+") && (baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM || baseblock.defaultBlockState().instrument() == NoteBlockInstrument.HARP)) {
                int index = path.lastIndexOf("/");
                String stoneName = path.substring(index + 1); // Get granite from tfc:rock/bricks/granite
                var opt = BuiltInRegistries.BLOCK.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), path.replace("bricks", "raw"))
                );
                if (opt.isPresent()) {
                    return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                }
            }
        }

        if (!BLACKLISTED_MODS.contains(baseRes.getNamespace())) {
            // Check for <type>_bricks | <type>_stone_bricks
            if (path.matches("[a-z]+(?:_(bricks|stairs)|_stone_(bricks|stairs))") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM) {
                String stoneName = path.substring(0, path.length() - 7); // get stoneName from namespace:stoneName_bricks
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Check if a BlockType is already added
                if ( Objects.isNull(get(idBlockType)) || Objects.isNull(get(idBlockTypeAlt)) ) {
                    var opt = BuiltInRegistries.BLOCK.getOptional(idBlockType);
                    var alt = BuiltInRegistries.BLOCK.getOptional(idBlockTypeAlt);
                    if (opt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                    else if (alt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneAlt), alt.get()));
                }

            }
            // Check for polished_<type> | polished_<type>_stone
            else if (path.matches("polished_[a-z]+(?:_stone)?") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM) {
                String stoneName = path.replace("polished_", ""); // get stoneName from namespace:polished_stoneName
                String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix
                ResourceLocation idBlockType = baseRes.withPath(stoneName);
                ResourceLocation idBlockTypeAlt = baseRes.withPath(stoneAlt);

                // Check if a BlockType is already added
                if (Objects.isNull(get(idBlockType)) || Objects.isNull(get(idBlockTypeAlt))) {
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
