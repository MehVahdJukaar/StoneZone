package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Optional;

import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;

public class MudTypeRegistry extends BlockTypeRegistry<MudType> {

    public static final MudTypeRegistry INSTANCE = new MudTypeRegistry();

    public static MudType getMudType() {
        return VanillaMudTypes.MUD;
    }

    public MudTypeRegistry() {
        super(MudType.class, "mud_type");
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public MudType register(MudType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public MudType getDefaultType() {
        return VanillaMudTypes.MUD;
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public Optional<MudType> detectTypeFromBlock(Block baseblock, ResourceLocation baseRes) {
        String path = baseRes.getPath();

        if (path.matches("[a-z]+_mud_bricks")
                && baseblock.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM
                && !BLACKLISTED_MODS.contains(baseRes.getNamespace())
        ) {
            String mudName = path.substring(0, path.length() - 7); // get mudName from namespace:mudName_bricks
            String mudAlt = mudName + "_mud"; // Some mods included "_mud" as the suffix
            ResourceLocation idBlockType = baseRes.withPath(mudName);
            ResourceLocation idBlockTypeAlt = baseRes.withPath(mudAlt);

            if (!valuesReg.containsKey(idBlockType) && !valuesReg.containsKey(idBlockTypeAlt)) {
                var opt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(mudName));
                var alt = BuiltInRegistries.BLOCK.getOptional(baseRes.withPath(mudAlt));
                if (opt.isPresent()) return Optional.of(new MudType(baseRes.withPath(mudName), opt.get()));
                else if (alt.isPresent()) return Optional.of(new MudType(baseRes.withPath(mudAlt), alt.get()));
            }

        }
        return Optional.empty();
    }


    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public MudType.Finder addSimpleFinder(ResourceLocation stoneTypeId) {
        MudType.Finder finder = new MudType.Finder(stoneTypeId);
        this.addFinder(finder);
        return finder;
    }

    public MudType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(new ResourceLocation(typeId));
    }

    public MudType.Finder addSimpleFinder(String namespace, String nameStoneType) {
        return addSimpleFinder(new ResourceLocation(namespace, nameStoneType));
    }

    @Override
    public int priority() {
        return 110;
    }
}
