package net.mehvahdjukaar.stone_zone.type;

import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.Optional;

public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public static final StoneType STONE_TYPE = new StoneType(new ResourceLocation("stone"), Blocks.STONE);
    public static final StoneType ANDESITE_TYPE = new StoneType(new ResourceLocation("andesite"), Blocks.ANDESITE);

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
    public Optional<StoneType> detectTypeFromBlock(Block block, ResourceLocation blockId) {
        String path = blockId.getPath();
        if (path.endsWith("_bricks") && block.defaultBlockState().instrument() == NoteBlockInstrument.BASEDRUM) {
            String stoneName = path.substring(0, path.length() - 7);
            var opt = BuiltInRegistries.BLOCK.getOptional(blockId.withPath(stoneName));
            if (opt.isPresent()) {
                return Optional.of(new StoneType(blockId.withPath(stoneName), block, opt.get()));
            }
        }
        return Optional.empty();
    }


}
