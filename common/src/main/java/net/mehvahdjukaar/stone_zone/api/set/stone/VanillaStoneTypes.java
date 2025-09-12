package net.mehvahdjukaar.stone_zone.api.set.stone;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaStoneTypes {

    public static final StoneType STONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("stone"), Blocks.STONE)
    );

    public static final StoneType ANDESITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("andesite"), Blocks.ANDESITE)
    );

    public static final StoneType GRANITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("granite"), Blocks.GRANITE)
    );

    public static final StoneType TUFF = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("tuff"), Blocks.TUFF)
    );

    public static final StoneType CALCITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("calcite"), Blocks.CALCITE)
    );

    public static final StoneType BLACKSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("blackstone"), Blocks.BLACKSTONE)
    );

    public static final StoneType SANDSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("sandstone"), Blocks.SANDSTONE)
    );

    public static final StoneType DEEPSLATE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(ResourceLocation.withDefaultNamespace("deepslate"), Blocks.DEEPSLATE)
    );
}
