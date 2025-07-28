package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.api.intergration.CompatStoneType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.mehvahdjukaar.stone_zone.configs.UnsafeDisablerConfigs;
import net.mehvahdjukaar.stone_zone.misc.ModelUtils;
import net.mehvahdjukaar.stone_zone.misc.SpriteHelper;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class StoneZone extends EveryCompat {
    public static final String MOD_ID = "stonezone";
    public static final Logger LOGGER = LogManager.getLogger("Stone Zone");

    public static void init() {
        SZConfigs.init();
        UnsafeDisablerConfigs.init();
        SZRegistry.init();
        SpriteHelper.initHardcodedSprite();

        BlockSetAPI.registerBlockSetDefinition(StoneTypeRegistry.INSTANCE);
        BlockSetAPI.registerBlockSetDefinition(MudTypeRegistry.INSTANCE);
        CompatStoneType.init();

        PlatHelper.addCommonSetup(SpriteHelper::addHardcodedModel);

        if (PlatHelper.getPhysicalSide().isClient()) {
            ClientHelper.addClientReloadListener(() -> (preparationBarrier, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor) ->
                            CompletableFuture.completedFuture(null)
                                    .thenCompose(preparationBarrier::wait)
                                    .thenAcceptAsync((object) -> ModelUtils.reset(), gameExecutor),
                    res("stonezone_reloader"));
        }

        // Add ModId to DynamicPack so the tags can be loaded into the world first time
        addModToDynamicPack("caverns_and_chasms");
        addModToDynamicPack("architects_palette");
        addModToDynamicPack("tconstruct");

    }

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    /// Will be added to DynamicPack if the mod is loaded
    public static void addModToDynamicPack(String modId) {
        if (PlatHelper.isModLoaded(modId)) {
            ServerDynamicResourcesHandler.INSTANCE.dynamicPack.addNamespaces(modId);
        }
    }

    @SafeVarargs
    public static void addMultipleIfLoaded(String modId, Supplier<Function<String, CompatModule>>... moduleFactories) {
        if (PlatHelper.isModLoaded(modId)) {
            CompatModule module;
            for (var moduleFactory : moduleFactories) {
                module = moduleFactory.get().apply(modId);
                addModule(module);
            }
        }

    }
}