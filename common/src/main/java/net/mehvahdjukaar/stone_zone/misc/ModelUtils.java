package net.mehvahdjukaar.stone_zone.misc;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.stone_zone.api.SZModule;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ModelUtils {

    private static final Pattern PARENT_PATTERN = Pattern.compile("\"parent\"\\s*:\\s*\"((.*?):(.*?))\"");

    public static String replaceParent(String input, SimpleModule module) {
        Matcher matcher = PARENT_PATTERN.matcher(input);
        if (matcher.find()) {

            //  minecraft:block/aa -> stonezone:minecraft/block/aa
            return matcher.replaceAll(m -> {
                String newRes = "stonezone:" + m.group(2) + "/" + m.group(3);
                if (module instanceof SZModule szModule) {
                    szModule.hackAddParentModel(new ResourceLocation(matcher.group(1)),
                            new ResourceLocation(newRes));
                }
                return "\"parent\": \"" + newRes + "\"";
            });
        }
        return input;
    }

    // parsing and then unparsing. will be sub optimal
    public static String forceSetTintIndex(String jsonText) {
        JsonObject jsonObject = GsonHelper.parse(jsonText);
        addTintIndexToModels(jsonObject, 0);
        return jsonObject.toString();
    }

    public static void addTintIndexToModels(JsonObject jsonObject, int tintIndex) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            if (key.equals("faces") && value instanceof JsonObject facesObject) {

                // Process child objects within "faces"
                for (String childKey : facesObject.keySet()) {
                    JsonObject childObject = facesObject.getAsJsonObject(childKey);

                    // Add "tintindex": 1 if not present
                    if (childObject != null && !childObject.has("tintindex")) {
                        childObject.addProperty("tintindex", tintIndex);
                    }
                }
            } else if (value instanceof JsonObject jo) {
                // Recursively process nested objects
                addTintIndexToModels(jo, tintIndex);
            }
        }
    }


}
