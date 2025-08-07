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

public class MudTypeRegistry extends BlockTypeRegistry<MudType> {

    public static final MudTypeRegistry INSTANCE = new MudTypeRegistry();

    /* NOTE:
     * Do not remove below because crsah will occurred and I do not know why
     * the error is Cannot read field "id" because "newType" is null
     */
    public static final MudType MUD_TYPE = new MudType(ResourceLocation.withDefaultNamespace("mud"), Blocks.MUD);

    public MudTypeRegistry() {
        super(MudType.class, "mud_type");

//        this.addFinder(MudType.Finder.vanilla("mud")); // currently disabled for now
    }

    public static MudType getMudType() {
        return getValue("mud");
    }

    @SuppressWarnings("unused")
    public static Collection<MudType> getTypes() {
        return INSTANCE.getValues();
    }

    public static MudType getValue(String mudTypeId) {
        return INSTANCE.get(ResourceLocation.parse(mudTypeId));
    }

    @Override
    public MudType getDefaultType() {
        return MUD_TYPE;
    }

    @Override
    public Optional<MudType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String path = baseRes.getPath();

        if (path.matches("[a-z]+_mud_bricks")
                && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
                && !path.matches("(cracked|infested)_mud_bricks") // shouldn't be detected
        ) {
            String mudName = path.substring(0, path.length() - 7); // get mudName from namespace:mudName_bricks
            String mudAlt = mudName + "_mud"; // Some mods included "_mud" as the suffix
            ResourceLocation idBlockType = baseRes.withPath(mudName);
            ResourceLocation idBlockTypeAlt = baseRes.withPath(mudAlt);

            if (Objects.isNull(get(idBlockType)) && Objects.isNull(get(idBlockTypeAlt))) {
                var opt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(mudName));
                var alt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(mudAlt));
                if (opt.isPresent()) return Optional.of(new MudType(baseRes.withPath(mudName), opt.get()));
                else if (alt.isPresent()) return Optional.of(new MudType(baseRes.withPath(mudAlt), alt.get()));
            }

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
