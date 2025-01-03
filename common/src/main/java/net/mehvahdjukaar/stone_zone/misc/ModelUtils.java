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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//!! Tools to create a custom model for StoneZone's blocks to have tinted color
public final class ModelUtils {
    // dont skip any arbitrary parent
    private static final Pattern PARENT_PATTERN = Pattern.compile("\"parent\"\\s*:\\s*\"(.*?:.*?\\/.*?)\"");
    private static final Pattern PATH_PATTERN = Pattern.compile("(.*?)(\\/.*?)");
    // just replace models once
    private static final Set<ResourceLocation> RESOLVED_PARENTS = new HashSet<>();

    public static void reset() {
        RESOLVED_PARENTS.clear();
    }

    public static String replaceParent(String input, SimpleModule module) {
        Matcher matcher = PARENT_PATTERN.matcher(input);
        if (matcher.find()) {

            // oldRes: minecraft:block/aa -> newRes: stonezone:block/minecraft/aa
            return matcher.replaceAll(m -> {
                ResourceLocation oldRes = new ResourceLocation(matcher.group(1));
                ResourceLocation newRes = transformModelID(oldRes);
                if (module instanceof SZModule szModule && !RESOLVED_PARENTS.contains(oldRes)) {
                    szModule.markModelForModification(oldRes);
                    RESOLVED_PARENTS.add(oldRes);
                }
                return "\"parent\": \"" + newRes + "\"";
            });
        }
        return input;
    }

    // make model id SZ namespace
    public static ResourceLocation transformModelID(ResourceLocation id) {
        Matcher matcher = PATH_PATTERN.matcher(id.getPath());

        if (!matcher.find()) {
            //error
            return id;
        }
        return StoneZone.res(matcher.group(1) + "/" + id.getNamespace() + matcher.group(2));
    }

    public static String addTintIndexToModel(String jsonText) {
        JsonObject jsonObject = GsonHelper.parse(jsonText);
        addTintIndexToModel(jsonObject, 0);
        return jsonObject.toString();
    }

    public static void addTintIndexToModel(JsonObject jsonObject, int tintIndex) {
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
            readJsonsRecursive(manager, res, jsonObjects);
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
