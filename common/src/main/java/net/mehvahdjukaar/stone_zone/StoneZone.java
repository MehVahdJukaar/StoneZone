package net.mehvahdjukaar.stone_zone;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoneZone {
    public static final String MOD_ID = "stonezone";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final Logger LOGGER = LogManager.getLogger("Stone Zone");


    public static void init(){

    }

}