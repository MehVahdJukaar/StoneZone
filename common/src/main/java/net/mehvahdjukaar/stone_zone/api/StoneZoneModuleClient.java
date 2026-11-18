package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.SimpleModuleClient;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.stone_zone.StoneZone;

import java.util.function.Consumer;

public class StoneZoneModuleClient<M extends SimpleModule> extends SimpleModuleClient<M> {

    public StoneZoneModuleClient(M module) {
        super(module);
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((resourceManager, resourceSink) ->
            getModule().getEntries().forEach(entrySetParent -> {
                if (entrySetParent instanceof StoneZoneEntrySet<?,?> entrySet) {
                    try {
                        entrySet.generateModels(getModule(), resourceManager, resourceSink);
                    } catch (Exception ex) {
                        StoneZone.LOGGER.error("Failed to generate client resources for EntrySet: {} from module {}:", entrySet, this, ex);
                        if (PlatHelper.isDev()) throw ex;
                    }
                }
            })
        );
    }

}
