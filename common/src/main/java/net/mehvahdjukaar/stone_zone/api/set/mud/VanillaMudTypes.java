package net.mehvahdjukaar.stone_zone.api.set.mud;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class VanillaMudTypes {

    public static final MudType MUD = MudTypeRegistry.INSTANCE.register(
            new MudType(Identifier.parse("mud"), Blocks.MUD)
    );

}
