package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.misc.ModelUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static net.mehvahdjukaar.stone_zone.misc.ModelUtils.modifiedParent;
import static net.mehvahdjukaar.stone_zone.misc.ModelUtils.replaceCubePath;


public class SZModule extends SimpleModule {
    public SZModule(String modId, String shortId) {
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

        var isAlreadyRegistered = super.isEntryAlreadyRegistered(blockId, blockType, registry);
        if (isAlreadyRegistered) {
            //noinspection ConstantValue
            return isAlreadyRegistered;
        }

        // blockId: stonezone:twigs/strata/<stonetype>_column | blockName: <stonetype>_column
        String blockName = blockId.substring(blockId.lastIndexOf("/") + 1);

        /// DISABLED FOR NOW, There is no hardcoded Blocks yet
//        if (blockType instanceof StoneType stoneType) {
//            Boolean hardcoded = HardcodedBlockType.isStoneBlockAlreadyRegistered(blockName, stoneType, compatModId, shortenedId());
//            if (hardcoded != null) return hardcoded;
//        }

        return false;
    }


    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        // Creating custom models
        for (var r : parentsToReplace.entrySet()) {
            StaticResource res = StaticResource.getOrLog(manager, ResType.MODELS.getPath(r.getKey()));
            if (res != null) {
                // Read resource in string
                String json = new String(res.data);

                // Modifying the contents
                json = ModelUtils.forceSetTintIndex(json);
                if (json.contains("block/cube\"")) json = replaceCubePath(json, modifiedParent.get(new ResourceLocation("minecraft:block/cube")));

                // Add custom models to the resources
                handler.dynamicPack.addBytes(r.getValue(), json.getBytes(), ResType.MODELS);
                modifiedParent.put(r.getKey(), r.getValue());
            }
        }
    }

    private final Map<ResourceLocation, ResourceLocation> parentsToReplace = new HashMap<>();

    public void addParentModelToMap(ResourceLocation oldRes, ResourceLocation newRes) {
        if (!modifiedParent.containsKey(new ResourceLocation("block/cube"))) {
            parentsToReplace.put(new ResourceLocation("block/cube"), StoneZone.res("block/minecraft/cube"));
            modifiedParent.put(new ResourceLocation("block/cube"), StoneZone.res("block/minecraft/cube"));
        }

        if (!modifiedParent.containsKey(oldRes)) parentsToReplace.put(oldRes, newRes);
    }

}
