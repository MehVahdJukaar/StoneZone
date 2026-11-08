package net.mehvahdjukaar.stone_zone.misc;


import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;

public class CompatSpritesHelper {

    public final static Map<Identifier, String> modelID = new HashMap<>();

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           TEXTURES                                           │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

/// NOTE: Used to identify StoneTypes' texture only based off their name
    public static void initHardcodedSprite() {

        // Thuamon
        addOptional("thaumon","amber", "all", "block/amber_side");

        // Quark
        addOptional("quark:myalite", "all", StoneZone.res("block/quark/myalite_tinted"));
        addOptional("quark:myalite_bricks", "all", StoneZone.res("block/quark/myalite_bricks_tinted"));

        // BetterEnd
        addOptional("betterend","umbralith", "all", "block/umbralith");

        // Eternal Tales
        addOptional("eternal_tales","purgatorium_stone", "all", "block/purgstone");
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                            MODELS                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

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

    public final static Map<Identifier, Pair<String, String>> tintedStoneType = Map.of(
            Identifier.parse("quark:myalite"), Pair.of(":block/quark/myalite_tinted", ":block/quark/myalite_bricks_tinted")
    );

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                        Sprite Methods                                        │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    public static void addOptional(String blockId, String keyId, String textureId) {
        TextureCache.registerSpecialTextureForBlock(Identifier.parse(blockId), keyId,
                Identifier.parse(textureId)
        );
    }
    public static void addOptional(String blockId, String keyId, Identifier textureId) {
        TextureCache.registerSpecialTextureForBlock(Identifier.parse(blockId), keyId,
                textureId
        );
    }

    public static void addOptional(String modId, String blockPath, String keyId, String texturePath) {
        TextureCache.registerSpecialTextureForBlock(
                Identifier.fromNamespaceAndPath(modId, blockPath),
                keyId,
                Identifier.fromNamespaceAndPath(modId, texturePath)
        );
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                     TEXTURE METHODS                      │
    //      └──────────────────────────────────────────────────────────┘

    public static <T extends BlockType> BlockTypeResTransformer<T> replaceStoneTextures(BlockTypeResTransformer<T> modelTransformer, String oldTypeName) {
        return modelTransformer
                .replaceWithTextureFromChild("minecraft:block/" + oldTypeName + "_bricks", BRICKS)
                .replaceWithTextureFromChild("minecraft:block/" + oldTypeName, STONE)
                .replaceWithTextureFromChild("minecraft:block/cobblestone", COBBLESTONE)
                .replaceWithTextureFromChild("minecraft:block/smooth_" + oldTypeName, SMOOTH)
                .replaceWithTextureFromChild("minecraft:block/smooth_" + oldTypeName + "_slab_side", SMOOTH_SLAB)
                .replaceWithTextureFromChild("minecraft:block/polished_" + oldTypeName, POLISHED)
                .replaceWithTextureFromChild("minecraft:block/mossy_" + oldTypeName + "_bricks", MOSSY_BRICKS);
    }
}
