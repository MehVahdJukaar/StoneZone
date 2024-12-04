package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

public class StoneZone {
    public static final String MOD_ID = "stonezone";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final Logger LOGGER = LogManager.getLogger("Stone Zone");


    public static void init() {
        BlockSetAPI.registerBlockSetDefinition(StoneTypeRegistry.INSTANCE);


        //addIfLoaded("every_compat", EveryCompatModule::new);
    }

    private void addIfLoaded(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatHelper.isModLoaded(modId)) {
            CompatModule module = moduleFactory.get().apply(modId);
            EveryCompatAPI.registerModule(module);
        }
    }

}