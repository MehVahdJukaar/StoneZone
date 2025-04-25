package net.mehvahdjukaar.stone_zone.misc;


import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class SpriteHelper {

    public final static Map<ResourceLocation, String> modelID = new HashMap<>();

    public static void addHardcodedSprites() {

                //!! MODELS !!\\
/// NOTE: Used to identify StoneTypes' model only based off their name or path
        // Naturalist
        addToModelId("bbb/naturalist/shellstone_layer", "naturalist:block/shellstone/shellstone");
        addToModelId("bbb/naturalist/shellstone_brick_layer", "naturalist:block/shellstone/shellstone_bricks");
        addToModelId("bbb/naturalist/smooth_shellstone_layer", "naturalist:block/shellstone/smooth_shellstone");

        // BetterEnd
        addToModelId("bbb/betterend/umbralith_layer", "betterend:block/umbralith_1");

        // Eternal Tales
        addToModelId("bbb/eternal_tales/smooth_purgatorium_stone_layer", "eternal_tales:block/purgatorium_stone_smooth");
        addToModelId("bbb/eternal_tales/smooth_rajiit_layer", "eternal_tales:block/rajiit_smooth");

                //!! TEXTURES !!\\
/// NOTE: Used to identify StoneTypes' texture only based off their name
        // BetterEnd
        addOptional("betterend:umbralith", "all", "betterend:block/umbralith");

        // Eternal Tales
        addOptional("eternal_tales:purgatorium_stone", "all", "eternal_tales:block/purgstone");
    }

    @SuppressWarnings("SameParameterValue")
    private static void addOptional(String blockId, String textureId, String texturePath) {
        BuiltInRegistries.BLOCK.getOptional(ResourceLocation.parse(blockId))
                .ifPresent(b -> TextureCache.registerSpecialTextureForBlock(b, textureId, ResourceLocation.parse(texturePath)));
    }

    private static void addToModelId(String blockid, String pathModel) {
        modelID.put(StoneZone.res(blockid), pathModel);
    }
}
