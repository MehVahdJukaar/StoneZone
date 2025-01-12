package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public static final StoneType STONE_TYPE = new StoneType(new ResourceLocation("stone"), Blocks.STONE);
    public static final StoneType ANDESITE_TYPE = new StoneType(new ResourceLocation("andesite"), Blocks.ANDESITE);
    public static final StoneType BLACKSTONE_TYPE = new StoneType(new ResourceLocation("blackstone"), Blocks.ANDESITE);
    public static final StoneType MUD_TYPE = new StoneType(new ResourceLocation("mud"), Blocks.ANDESITE);


    public static Collection<StoneType> getTypes() {
        return INSTANCE.getValues();
    }

    public static StoneType getValue(String stoneTypeId) {
        return INSTANCE.get(new ResourceLocation(stoneTypeId));
    }

    public StoneTypeRegistry() {
        super(StoneType.class, "stone_type");

        this.addFinder(StoneType.Finder.vanilla("diorite"));
        this.addFinder(StoneType.Finder.vanilla("granite"));
        this.addFinder(StoneType.Finder.vanilla("tuff"));
        this.addFinder(StoneType.Finder.vanilla("calcite"));
    }

    @Override
    public StoneType getDefaultType() {
        return STONE_TYPE;
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

        // Check for <type>_bricks | <type>_stone_bricks
        if (path.matches("[a-z]+(_bricks|_stone_bricks)") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM) {
            String stoneName = path.substring(0, path.length() - 7); // get stoneName from namespace:stoneName_bricks
            String stoneAlt = stoneName + "_stone"; // Some mods included "_stone" as the suffix

            var opt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(stoneName));
            var alt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(stoneAlt));
            if (opt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
            else if (alt.isPresent()) return Optional.of(new StoneType(baseRes.withPath(stoneAlt), alt.get()));

        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        this.getValues().forEach((w) -> {
            if (language.isDefault()) language.addEntry(w.getTranslationKey(), w.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
