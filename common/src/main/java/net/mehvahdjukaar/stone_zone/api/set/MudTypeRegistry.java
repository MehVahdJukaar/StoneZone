package net.mehvahdjukaar.stone_zone.api.set;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MudBlock;

import java.util.Collection;
import java.util.Optional;

public class MudTypeRegistry extends BlockTypeRegistry<MudType> {
    public static final MudTypeRegistry INSTANCE = new MudTypeRegistry();

    public static final MudType MUD_TYPE = new MudType(new ResourceLocation("mud"), Blocks.MUD);

    static {
        // Reason: getPaletteFrom... requires that the child exists on the base type
        MUD_TYPE.addChild("bricks", Blocks.MUD_BRICKS);
    }

    public static Collection<MudType> getTypes() {
        return INSTANCE.getValues();
    }

    public static MudType getValue(ResourceLocation name) {
        return INSTANCE.get(name);
    }

    public static MudType getBaseMudType() {
        return MUD_TYPE;
    }

    protected MudTypeRegistry() {
        super(MudType.class, "mud");
    }

    @Override
    public MudType getDefaultType() {
        return MUD_TYPE;
    }

    @Override
    public Optional<MudType> detectTypeFromBlock(Block block, ResourceLocation resourceLocation) {
        if (block instanceof MudBlock) {
            return Optional.of(new MudType(resourceLocation, block));
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
