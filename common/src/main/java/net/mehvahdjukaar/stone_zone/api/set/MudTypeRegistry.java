package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Optional;

import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;

public class MudTypeRegistry extends BlockTypeRegistry<MudType> {

    public static final MudTypeRegistry INSTANCE = new MudTypeRegistry();

    public MudTypeRegistry() {
        super(MudType.class, "mud_type");

        this.addFinder(MudType.Finder.vanilla("mud"));
    }

    public static MudType getMudType() {
        return getValue("mud");
    }

    public static MudType getValue(String mudTypeId) {
        return INSTANCE.get(new ResourceLocation(mudTypeId));
    }

    @Override
    public MudType getDefaultType() {
        return getMudType();
    }

    @Override
    public Optional<MudType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String path = baseRes.getPath();

        if (
                path.matches("[a-z]+_mud_bricks")
                && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM
                && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
        ) {
            String mudName = path.substring(0, path.length() - 7); // get mudName from namespace:mudName_bricks
            String mudAlt = mudName + "_mud"; // Some mods included "_mud" as the suffix
            var opt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(mudName));
            var alt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(mudAlt));
            if (opt.isPresent()) return Optional.of(new MudType(baseRes.withPath(mudName), opt.get()));
            else if (alt.isPresent()) return Optional.of(new MudType(baseRes.withPath(mudAlt), alt.get()));

        }
        return Optional.empty();
    }

    @Override
    public void addTypeTranslations(AfterLanguageLoadEvent language) {
        getValues().forEach((mudType) -> {
            if (language.isDefault()) language.addEntry(mudType.getTranslationKey(), mudType.getReadableName());
        });
    }

    @Override
    public int priority() {
        return 110;
    }
}
