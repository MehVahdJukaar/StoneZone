package net.mehvahdjukaar.stone_zone.api.set;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaMudTypes {

    public static final MudType MUD = MudTypeRegistry.INSTANCE.register(
            new MudType(new ResourceLocation("mud"), Blocks.MUD)
    );

}
