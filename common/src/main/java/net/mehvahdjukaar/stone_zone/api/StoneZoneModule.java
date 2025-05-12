package net.mehvahdjukaar.stone_zone.api;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType;
import net.mehvahdjukaar.stone_zone.misc.ModelUtils;
import net.mehvahdjukaar.stone_zone.misc.TintConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class StoneZoneModule extends SimpleModule {
    public StoneZoneModule(String modId, String shortId) {
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
    public void registerItems(Registrator<Item> registry) {
        super.registerItems(registry);
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((resourceManager, resourceSink) -> {
            getEntries().forEach(entrySetParent -> {
                if (entrySetParent instanceof StoneZoneEntrySet<?,?> entrySet) {
                    entrySet.generateModels(this, resourceManager, resourceSink);
                }
            });

            // Creating custom parent model files
            Map<ResourceLocation, JsonObject> models = ModelUtils.readAllModelsAndParents(resourceManager, modelsToModify.keySet());
            for (var e : models.entrySet()) {
                // Modifying the contents
                JsonObject json = e.getValue();
                ResourceLocation oldRes = e.getKey();
                var tintConfig = modelsToModify.getOrDefault(oldRes, TintConfiguration.EMPTY);
                ModelUtils.addTintIndexToModelAndReplaceParent(oldRes, json, null, null, tintConfig);
                ResourceLocation newRes = ModelUtils.transformModelID(e.getKey());

                // Add custom models to the resources
                resourceSink.addJson(newRes, json, ResType.MODELS);
            }
        });
    }

    private final Map<ResourceLocation, TintConfiguration> modelsToModify = new HashMap<>();

    public void markModelForModification(ResourceLocation oldRes, TintConfiguration config) {
        modelsToModify.put(oldRes, config);
    }

}
