package net.mehvahdjukaar.stone_zone.api.set.stone;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaStoneTypes {

    public static final StoneType STONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("stone"), Blocks.STONE)
    );

    public static final StoneType ANDESITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("andesite"), Blocks.ANDESITE)
    );

    public static final StoneType GRANITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("granite"), Blocks.GRANITE)
    );

    public static final StoneType TUFF = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("tuff"), Blocks.TUFF)
    );

    public static final StoneType CALCITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("calcite"), Blocks.CALCITE)
    );

    public static final StoneType BLACKSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("blackstone"), Blocks.BLACKSTONE)
    );

    public static final StoneType SANDSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("sandstone"), Blocks.SANDSTONE)
    );

    public static final StoneType DEEPSLATE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(Identifier.withDefaultNamespace("deepslate"), Blocks.DEEPSLATE)
    );
}
