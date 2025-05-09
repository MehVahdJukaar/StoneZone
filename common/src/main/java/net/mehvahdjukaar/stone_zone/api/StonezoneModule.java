package net.mehvahdjukaar.stone_zone.api;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType;
import net.mehvahdjukaar.stone_zone.misc.ModelUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;


public class StonezoneModule extends SimpleModule {
    public StonezoneModule(String modId, String shortId) {
        super(modId, shortId, StoneZone.MOD_ID);
    }


    @Override
    @SuppressWarnings("DataFlowIssue")
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return SZRegistry.MOD_TAB.getKey();
    }

    @Override
    public String toString() {
        return "StoneZone " + LangBuilder.getReadableName(modId) + " Module";
    }

    @Override
    public ResourceLocation makeMyRes(String name) {
        return super.makeMyRes(name);
    }

    @Override
    public boolean isEntryAlreadyRegistered(String blockId, BlockType blockType, Registry<?> registry) {

        // blockId: stonezone:twigs/strata/<stonetype>_column | blockName: <stonetype>_column
        String blockName = blockId.substring(blockId.lastIndexOf("/") + 1);

        if (blockType instanceof StoneType stoneType) {
            Boolean hardcoded = HardcodedBlockType.isStoneBlockAlreadyRegistered(blockName, stoneType, modId, shortenedId());
            if (hardcoded != null) return hardcoded;
        }

        var isAlreadyRegistered = super.isEntryAlreadyRegistered(blockId, blockType, registry);
        if (isAlreadyRegistered) {
            //noinspection ConstantValue
            return isAlreadyRegistered;
        }

        return false;
    }


    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((resourceManager, resourceSink) -> {
            getEntries().forEach(entrySetParent -> {
                if (entrySetParent instanceof StonezoneEntrySet<?,?> entrySet) {
                    entrySet.generateModels(this, resourceManager, resourceSink);
                }
            });

            // Creating custom models
            Map<ResourceLocation, JsonObject> models = ModelUtils.readAllModelsAndParents(resourceManager, modelsToModify);
            for (var e : models.entrySet()) {
                // Modifying the contents
                JsonObject json = e.getValue();
                ModelUtils.addTintIndexToModelAndReplaceParent(json, null, null);
                ResourceLocation newId = ModelUtils.transformModelID(e.getKey());

                // Add custom models to the resources
                resourceSink.addJson(newId, json, ResType.MODELS);
            }
        });
    }

    private final Set<ResourceLocation> modelsToModify = new HashSet<>();

    public void markModelForModification(ResourceLocation oldRes) {
        modelsToModify.add(oldRes);
    }

}
