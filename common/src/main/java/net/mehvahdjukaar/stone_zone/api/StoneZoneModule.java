package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;


public class StoneZoneModule extends EveryCompatModule {

    public StoneZoneModule(String modId, String shortId) {
        super(modId, shortId, StoneZone.MOD_ID);
    }

    @Override
    @SuppressWarnings({"DataFlowIssue", "unchecked"})
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return (ResourceKey<CreativeModeTab>) SZRegistry.MOD_TAB.getKey();
    }

    @Override
    public String toString() {
        return "StoneZone: " + LangBuilder.getReadableName(modId) + " Module";
    }

    @Override
    public boolean isEntryAlreadyRegistered(String entrySetId, ResourceLocation blockId, BlockType blockType, Registry<?> registry) {

        String blockPath = blockId.getPath();
        //short block name - blockName: <stonetype>_column from the blockId: stonezone:twigs/strata/<stonetype>_column
        String blockName = blockPath.substring(blockPath.lastIndexOf("/") + 1);

        if (blockType instanceof StoneType stoneType) {
            Boolean hardcoded = HardcodedBlockType.isStoneBlockAlreadyRegistered(entrySetId, blockName, stoneType, modId);
            if (hardcoded != null) return hardcoded;
        }
        else if (blockType instanceof MudType mudType) {
            Boolean hardcoded = HardcodedBlockType.isMudBlockAlreadyRegistered(entrySetId, blockName, mudType, modId);
            if (hardcoded != null) return hardcoded;
        }

        return super.isEntryAlreadyRegistered(entrySetId, blockId, blockType, registry);
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
                    try {
                        entrySet.generateModels(this, resourceManager, resourceSink);
                    } catch (Exception ex) {
                        StoneZone.LOGGER.error("Failed to generate client resources for EntrySet: {} from module {}:", entrySet, this, ex);
                        if (PlatHelper.isDev()) throw ex;
                    }
                }
            });
        });
    }

}
