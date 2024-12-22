package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoneZone extends EveryCompat {
    public static final String MOD_ID = "stonezone";
    public static final Logger LOGGER = LogManager.getLogger("Stone Zone");

    public static void init() {
        SZConfigs.init();
        SZRegistry.init();

        BlockSetAPI.registerBlockSetDefinition(StoneTypeRegistry.INSTANCE);
    }

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }


}