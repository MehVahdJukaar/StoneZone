package net.mehvahdjukaar.stone_zone.api.set.mud;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.Optional;

import static net.mehvahdjukaar.stone_zone.api.set.RockType.newSubBlockType;
import static net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType.BLACKLISTED_MODS;

@SuppressWarnings("UnusedReturnValue")
public class MudTypeRegistry extends BlockTypeRegistry<MudType> {

    public static final MudTypeRegistry INSTANCE = new MudTypeRegistry();

    public MudTypeRegistry() {
        super(MudType.class, "mud_type");
    }

    @Override
    public MudType register(MudType vanillaType) {
        return super.register(vanillaType);
    }

    @Override
    public MudType getDefaultType() {
        return VanillaMudTypes.MUD;
    }

    @Override
    public Optional<MudType> detectTypeFromBlock(Block baseblock, ResourceLocation blockId) {
        String namespace = blockId.getNamespace();
//        String blockPath = blockId.getPath();

        if (!BLACKLISTED_MODS.contains(namespace)) {
            /// check for mudType without "_mud"
            Optional<MudType> detectedBlockType =
                    newSubBlockType(MudType::new, blockId, "(?<typename>[a-z]+)_mud_bricks",
                            valuesReg
                    );

            /// checking for mudType with "_mud"
            if (detectedBlockType.isEmpty())
                return newSubBlockType(MudType::new, blockId, "(?<typename>[a-z]+_mud)_bricks",
                        valuesReg
                );

        }
        return Optional.empty();
    }


    //shorthand for add finder. Gives a builder-like object that's meant to be configured inline
    public MudType.Finder addSimpleFinder(ResourceLocation mudTypeId) {
        MudType.Finder finder = new MudType.Finder(mudTypeId);
        this.addFinder(finder);
        return finder;
    }

    public MudType.Finder addSimpleFinder(String typeId) {
        return addSimpleFinder(ResourceLocation.parse(typeId));
    }

    public MudType.Finder addSimpleFinder(String namespace, String nameMudType) {
        return addSimpleFinder(ResourceLocation.fromNamespaceAndPath(namespace, nameMudType));
    }

    @Override
    public int priority() {
        return 110;
    }

    //!! ───────────────────────────────────────────── Marked For Removal ──────────────────────────────────────────────
    /// USE {@link VanillaMudTypes#MUD}
    @Deprecated(forRemoval = true)
    public static MudType getMudType() {
        return VanillaMudTypes.MUD;
    }

    /// USE {@link VanillaMudTypes}
    @Deprecated(forRemoval = true)
    public static MudType getValue(String mudTypeId) {
        return INSTANCE.get(ResourceLocation.parse(mudTypeId));
    }

    /// USE {@link StoneTypeRegistry#INSTANCE} - can be used in FOR Loop statement
    @Deprecated(forRemoval = true)
    public static Collection<MudType> getTypes() {
        return INSTANCE.getValues();
    }
}
