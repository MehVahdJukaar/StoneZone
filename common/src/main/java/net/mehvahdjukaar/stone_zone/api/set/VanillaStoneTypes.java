package net.mehvahdjukaar.stone_zone.api.set;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaStoneTypes {

    public static final StoneType STONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("stone"), Blocks.STONE)
    );

    public static final StoneType ANDESITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("andesite"), Blocks.ANDESITE)
    );

    public static final StoneType GRANITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("granite"), Blocks.GRANITE)
    );

    public static final StoneType TUFF = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("tuff"), Blocks.TUFF)
    );

    public static final StoneType CALCITE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("calcite"), Blocks.CALCITE)
    );

    public static final StoneType BLACKSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("blackstone"), Blocks.BLACKSTONE)
    );

    public static final StoneType SANDSTONE = StoneTypeRegistry.INSTANCE.register(
            new StoneType(new ResourceLocation("sandstone"), Blocks.SANDSTONE)
    );
}
