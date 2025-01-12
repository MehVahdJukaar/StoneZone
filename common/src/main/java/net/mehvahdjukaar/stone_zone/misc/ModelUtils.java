package net.mehvahdjukaar.stone_zone.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//!! Tools to create a custom model for StoneZone's blocks to have tinted color
public final class ModelUtils {
    // dont skip any arbitrary parent
    private static final Pattern PATH_PATTERN = Pattern.compile("(.*?)(\\/.*$)");
    // just replace models once
    private static final Set<ResourceLocation> RESOLVED_PARENTS = new HashSet<>();

    public static void reset() {
        RESOLVED_PARENTS.clear();
    }

    // make model id SZ namespace
    // oldRes: minecraft:block/aa -> newRes: stonezone:block/minecraft/aa
    public static ResourceLocation transformModelID(ResourceLocation id) {
        Matcher matcher = PATH_PATTERN.matcher(id.getPath());

        if (!matcher.find() || id.getNamespace().contains("stonezone")) {
            //error
            return id;
        }
        return StoneZone.res(matcher.group(1) + "/" + id.getNamespace() + matcher.group(2));
    }

    public static void addTintIndexToModelAndReplaceParent(JsonObject jsonObject, @Nullable SimpleModule module) {
        replaceParent(jsonObject, module);
        addTintIndexToModel(jsonObject, 0);
    }

    //same as above but with JsonObject. we could merge these 2 eventually. Just done this way so we dont have to parse those top layer models twice
    private static void replaceParent(JsonObject jsonObject, @Nullable SimpleModule module) {
        if (jsonObject.has("parent")) {
            ResourceLocation oldRes = new ResourceLocation(jsonObject.get("parent").getAsString());
            ResourceLocation newRes = transformModelID(oldRes);
            jsonObject.addProperty("parent", newRes.toString());

            if (module instanceof SZModule szModule && !(RESOLVED_PARENTS.contains(oldRes) && oldRes.getNamespace().matches("stonezone"))) {
                szModule.markModelForModification(oldRes);
                RESOLVED_PARENTS.add(oldRes);
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void addTintIndexToModel(JsonObject jsonObject, int tintIndex) {
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
                                face.addProperty("tintindex", tintIndex);
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
                ResourceLocation parent = new ResourceLocation(json.get("parent").getAsString());
                //ugly, eh
                if (RESOLVED_PARENTS.contains(parent)) return;
                RESOLVED_PARENTS.add(parent);
                readJsonsRecursive(manager, parent, jsonObjects);
            }
        }
    }
}
