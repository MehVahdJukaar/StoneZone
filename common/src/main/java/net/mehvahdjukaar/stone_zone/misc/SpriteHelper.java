package net.mehvahdjukaar.stone_zone.misc;


import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class SpriteHelper {

    public static void addHardcodedSprites() {

        // The-Outer-End
        addOptional("outerend:himmel_block", "all", "outerend:block/himmel_block");

        // BetterEnd
        addOptional("betterend:umbralith", "all", "betterend:block/umbralith_1");

        // Eternal Tales
        addOptional("eternal_tales:purgatorium_stone", "all", "eternal_tales:block/purgstone");
    }

    @SuppressWarnings("SameParameterValue")
    private static void addOptional(String blockId, String textureId, String texturePath) {
        BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(blockId))
                .ifPresent(b -> TextureCache.registerSpecialTextureForBlock(b, textureId, new ResourceLocation(texturePath)));
    }
}
