package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.mehvahdjukaar.stone_zone.misc.AllStonesItem;
import net.mehvahdjukaar.stone_zone.modules.twigs.TwigsModule;
import net.mehvahdjukaar.stone_zone.type.StoneTypeRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class StoneZone {
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

    public static void addIfLoaded(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatHelper.isModLoaded(modId)) {
            CompatModule module = moduleFactory.get().apply(modId);
            EveryCompatAPI.registerModule(module);
        }
    }

}