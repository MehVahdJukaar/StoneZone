package net.mehvahdjukaar.stone_zone.misc;


import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static net.mehvahdjukaar.every_compat.misc.SpriteExtra.addOptional;
import static net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys.*;
import static net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneChildKeys.STONE;

public class CompatSpritesHelper {

    public final static Map<ResourceLocation, String> modelID = new HashMap<>();

    // TEXTURES \\
/// NOTE: Used to identify StoneTypes' texture only based off their name
    public static void initHardcodedSprite() {

        // Blast From The Past
        addOptional("blastfromthepast:permafrost", "all", "blastfromthepast:block/permafrost");
        addOptional("blastfromthepast:permafrost_bricks", "all", "blastfromthepast:block/permafrost_bricks");

        // Thuamon
        addOptional("thaumon:amber", "all", "thaumon:block/amber_side");

        // Quark
        addOptional("quark:myalite", "all", StoneZone.MOD_ID + ":block/quark/myalite_tinted");
        addOptional("quark:myalite_bricks", "all", StoneZone.MOD_ID + ":block/quark/myalite_bricks_tinted");

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

    public final static Map<ResourceLocation, Pair<String, String>> tintedStoneType = Map.of(
            new ResourceLocation("quark:myalite"), Pair.of(":block/quark/myalite_tinted", ":block/quark/myalite_bricks_tinted")
    );


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
