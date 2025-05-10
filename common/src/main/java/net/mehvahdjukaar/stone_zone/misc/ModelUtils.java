package net.mehvahdjukaar.stone_zone.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//!! Tools to create a custom model for StoneZone's blocks to have tinted color
public final class ModelUtils {
    // dont skip any arbitrary parent
    private static final Pattern PATH_PATTERN = Pattern.compile("(?<folder>.*?)(?<path>/.*$)");
    // just replace models once
    private static final Set<ResourceLocation> RESOLVED_PARENTS = new HashSet<>();

    public static void reset() {
        RESOLVED_PARENTS.clear();
    }

    // make model id SZ namespace
    // oldRes: minecraft:block/aa -> newRes: stonezone:block/minecraft/aa
    public static ResourceLocation transformModelID(ResourceLocation id) {
        Matcher matcher = PATH_PATTERN.matcher(id.getPath());

        // Skip the ResourceLocation/Id's modification
        if (!matcher.find() || id.getNamespace().contains("stonezone")) {
            return id;
        }
        return StoneZone.res(matcher.group("folder") + "/" + id.getNamespace() + matcher.group("path"));
    }

    public static void addTintIndexToModelAndReplaceParent(JsonObject jsonObject, @Nullable SimpleModule module,
                                                           @Nullable String ignoreIfFromStone,
                                                           TintConfiguration config) {
        replaceParent(jsonObject, module, ignoreIfFromStone, config.splitForParent());
        addTintIndexToModel(jsonObject, 0, config);
    }

    //same as above but with JsonObject. we could merge these 2 eventually. Just done this way so we dont have to parse those top layer models twice
    private static void replaceParent(JsonObject jsonObject, @Nullable SimpleModule module,
                                      @Nullable String ignoreIfFromStone,
                                      TintConfiguration config) {
        // Modify the value of parent's
        if (jsonObject.has("parent")) {
            ResourceLocation oldRes = ResourceLocation.parse(jsonObject.get("parent").getAsString());
            String path = oldRes.getPath();
            int idx = path.lastIndexOf("/");
            //this parent was already added as a block. This is very brittle ans should be done a better way by keeping track of the block we visited
            if (ignoreIfFromStone != null && (idx != -1) && path.substring(idx + 1).contains(ignoreIfFromStone) && !path.contains("/parent/") && !path.contains("template")) {
                return;
            }

            // Skip these models/item file
            if (!oldRes.toString().matches("minecraft:(?:item/generated|builtin/generated)")) {
                ResourceLocation newRes = transformModelID(oldRes);
                jsonObject.addProperty("parent", newRes.toString());

                if (module instanceof StoneZoneModule stonezoneModule &&
                        !(RESOLVED_PARENTS.contains(oldRes) && oldRes.getNamespace().matches("stonezone")
                        )) {
                    stonezoneModule.markModelForModification(oldRes, config);
                    RESOLVED_PARENTS.add(oldRes);
                }
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void addTintIndexToModel(JsonObject jsonObject, int tintIndex, TintConfiguration config) {
        JsonElement elements = jsonObject.get("elements");
        if (elements != null) {
            // Some model files (walls or stairs) have more than one array under Elements
            for (JsonElement element : elements.getAsJsonArray()) {
                if (element instanceof JsonObject elementObject) {
                    // Process child objects under "faces"
                    JsonObject faces = elementObject.getAsJsonObject("faces");
                    if (faces != null) {
                        for (String key : faces.keySet()) {
                            JsonObject face = faces.getAsJsonObject(key);
                            // Add "tintindex": 0 if not present
                            if (!face.has("tintindex")) {
                                // Check if the texture matches the one we want to tint
                                String texture = face.get("texture").getAsString();
                                if (!config.isExcluded(texture)) {
                                    face.addProperty("tintindex", tintIndex);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @SuppressWarnings("SameParameterValue")
    public static void removeTintIndexFromModel(JsonObject jsonObject, String target) {
        JsonElement elements = jsonObject.get("elements");
        if (elements != null) {
            // Some model files (walls or stairs) have more than one array under Elements
            for (JsonElement element : elements.getAsJsonArray()) {
                if (element instanceof JsonObject elementObject) {
                    // Process child objects under "faces"
                    JsonObject faces = elementObject.getAsJsonObject("faces");
                    if (faces != null) {
                        for (String key : faces.keySet()) {
                            JsonObject face = faces.getAsJsonObject(key);
                            // remove tintIndex
                            if (Objects.equals(face.get("texture").getAsString(), target)) {
                                face.remove("tintindex");
                            }
                        }
                    }
                }
            }
        }
    }

    public static Map<ResourceLocation, JsonObject> readAllModelsAndParents(ResourceManager manager, Collection<ResourceLocation> models) {
        Map<ResourceLocation, JsonObject> jsonObjects = new HashMap<>();
        for (ResourceLocation res : models) {
            if (!res.getNamespace().matches("stonezone")) {
                readJsonsRecursive(manager, res, jsonObjects);
            }
        }
        return jsonObjects;
    }

    //takes care of parents
    private static void readJsonsRecursive(ResourceManager manager, ResourceLocation res, Map<ResourceLocation, JsonObject> jsonObjects) {
        StaticResource resource = StaticResource.getOrLog(manager, ResType.MODELS.getPath(res));
        if (resource != null) {
            JsonObject json = GsonHelper.parse(new String(resource.data)).getAsJsonObject();
            jsonObjects.put(res, json);
            if (json.has("parent")) {
                ResourceLocation parent = ResourceLocation.parse(json.get("parent").getAsString());
                //ugly, eh
                if (RESOLVED_PARENTS.contains(parent)) return;
                RESOLVED_PARENTS.add(parent);
                readJsonsRecursive(manager, parent, jsonObjects);
            }
        }
    }

    /**
     * @deprecated in favor of {@link StoneZoneEntrySet.Builder#addTintIndexToParentModel}
     */
    @Deprecated(forRemoval = true)
    public static void removeTintIndexFromParentModel(String pathModel, String excludeTexture, ClientDynamicResourcesHandler handler, ResourceManager manager) {
        ResourceLocation modelResLoc = ResType.BLOCK_MODELS.getPath(StoneZone.res(pathModel));

        try (InputStream modelStream = manager.getResource(modelResLoc)
                .orElseThrow(FileNotFoundException::new).open()) {
            JsonObject model = RPUtils.deserializeJson(modelStream);

            removeTintIndexFromModel(model, excludeTexture);

            handler.dynamicPack.addJson(StoneZone.res(pathModel), model, ResType.BLOCK_MODELS);

        } catch (IOException e) {
            handler.getLogger().error("Failed to modify parent model @ {} : {}", modelResLoc, e);
        }
    }
}
