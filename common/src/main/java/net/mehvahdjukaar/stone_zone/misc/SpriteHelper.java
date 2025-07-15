package net.mehvahdjukaar.stone_zone.misc;


import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static net.mehvahdjukaar.every_compat.misc.SpriteExtra.addOptional;

public class SpriteHelper {

    public final static Map<ResourceLocation, String> modelID = new HashMap<>();

    // TEXTURES \\
/// NOTE: Used to identify StoneTypes' texture only based off their name
    public static void initHardcodedSprite() {

        // BetterEnd
        addOptional("betterend:umbralith", "all", "betterend:block/umbralith");

        // Eternal Tales
        addOptional("eternal_tales:purgatorium_stone", "all", "eternal_tales:block/purgstone");
    }

    // MODELS \\
/// NOTE: Used to identify StoneTypes' model only based off their name or path
    public static void addHardcodedModel() {

        // Naturalist
        addToModelId("bbb/naturalist/shellstone_layer", "naturalist:block/shellstone/shellstone");
        addToModelId("bbb/naturalist/shellstone_brick_layer", "naturalist:block/shellstone/shellstone_bricks");
        addToModelId("bbb/naturalist/smooth_shellstone_layer", "naturalist:block/shellstone/smooth_shellstone");

        // BetterEnd
        addToModelId("bbb/betterend/umbralith_layer", "betterend:block/umbralith_1");

        // Eternal Tales
        addToModelId("bbb/eternal_tales/smooth_purgatorium_stone_layer", "eternal_tales:block/purgatorium_stone_smooth");
        addToModelId("bbb/eternal_tales/smooth_rajiit_layer", "eternal_tales:block/rajiit_smooth");
    }

    private static void addToModelId(String blockid, String pathModel) {
        modelID.put(StoneZone.res(blockid), pathModel);
    }
}
