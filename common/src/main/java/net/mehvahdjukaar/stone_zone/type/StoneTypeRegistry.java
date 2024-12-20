package net.mehvahdjukaar.stone_zone.type;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public static final StoneType STONE_TYPE = new StoneType(new ResourceLocation("stone"), Blocks.STONE);
    public static final StoneType ANDESITE_TYPE = new StoneType(new ResourceLocation("andesite"), Blocks.ANDESITE);

//    public static Collection<StoneType> getType() {
//        return INSTANCE.getValues();
//    }

    public StoneTypeRegistry() {
        super(StoneType.class, "stone_type");

        this.addFinder(StoneType.Finder.vanilla("andesite"));
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
            if (path.matches("rock/bricks/\\w+") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM) {
                int index = path.lastIndexOf("/");
                String stoneName = path.substring(index + 1); // Get granite from tfc:rock/bricks/granite
                var opt = BuiltInRegistries.BLOCK.getOptional(
                        new ResourceLocation(baseRes.getNamespace(), path.replace("bricks", "raw"))
                );
//                Moonlight.LOGGER.warn("STONER: {} - {}", stoneName, baseRes);
                if (opt.isPresent()) {
                    return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
                }
            }
        }

        if (path.matches("[a-z]+_bricks") && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM) {
            String stoneName = path.substring(0, path.length() - 7);
            var opt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(stoneName));
            if (opt.isPresent()) {
                return Optional.of(new StoneType(baseRes.withPath(stoneName), opt.get()));
            }
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
