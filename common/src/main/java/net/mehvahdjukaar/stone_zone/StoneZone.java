package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.api.intergration.CompatStoneType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.mehvahdjukaar.stone_zone.misc.ModelUtils;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

public class StoneZone extends EveryCompat {
    public static final String MOD_ID = "stonezone";
    public static final Logger LOGGER = LogManager.getLogger("Stone Zone");

    public static void init() {
        SZConfigs.init();
        SZRegistry.init();

        BlockSetAPI.registerBlockSetDefinition(StoneTypeRegistry.INSTANCE);
        BlockSetAPI.registerBlockSetDefinition(MudTypeRegistry.INSTANCE);
        CompatStoneType.init();

        ClientHelper.addClientReloadListener(() -> (preparationBarrier, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor) ->
                        CompletableFuture.completedFuture(null)
                                .thenCompose(preparationBarrier::wait)
                                .thenAcceptAsync((object) -> ModelUtils.reset(), gameExecutor),
                res("stonezone_reloader"));
    }

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }


}