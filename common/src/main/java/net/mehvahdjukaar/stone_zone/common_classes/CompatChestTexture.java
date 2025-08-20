package net.mehvahdjukaar.stone_zone.common_classes;

import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CompatChestTexture {

    public static void generateChestTexture(ResourceSink sink, ResourceManager manager,
                                            String shortenedID, StoneType stoneType, Block block,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc) {
        generateChestTexture(sink, manager, shortenedID, stoneType, block, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc, 2);
    }

    /**
    * Generate a texture for chest and trapped_chest
    * @param removeDarkest 0: none removed, 1: removed once, 2: removed twice
    */
    public static void generateChestTexture(ResourceSink sink, ResourceManager manager,
                                    String shortenedID, StoneType stoneType, Block block,
                                ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                    ResourceLocation trappedORLoc, int removeDarkest) {

        try (TextureImage normalTexture = TextureImage.open(manager, normalRLoc);
             TextureImage normalMask = TextureImage.open(manager, maskRLoc);
             TextureImage normalOverlay = TextureImage.open(manager, overlayRLoc)
            ) {

            TextureImage trapOverlay;
            if (trappedORLoc != null)  trapOverlay = TextureImage.open(manager, trappedORLoc);
            else trapOverlay = null;

            Respriter respriterNormal = Respriter.masked(normalTexture, normalMask);
            Respriter respriterOverlay = Respriter.of(normalOverlay);

                String path = "entity/chest/" + shortenedID + "/" + stoneType.getAppendableId() + "_chest";
                String trapped_path = "entity/chest/" + shortenedID + "/" + stoneType.getAppendableId() + "_trapped_chest";
                if (normalRLoc.toString().contains("left")) {
                    path += "_left";
                    trapped_path += "_left";
                }
                else if (normalRLoc.toString().contains("right")) {
                    path += "_right";
                    trapped_path += "_right";
                }

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, stoneType.stone))) {

                    List<Palette> plankPalette = Palette.fromAnimatedImage(plankTexture);

                    // Remove the lava color from brimwood_planks
                    if (stoneType.getId().toString().equals("regions_unexplored:brimwood")) {
                        plankPalette.forEach(p -> {
                            p.reduceUp();
                            p.reduceUp();
                            p.reduceUp();
                            p.reduceUp();
                        });
                    }

                    McMetaFile plankMeta = plankTexture.getMcMeta();

                    List<Palette> overlayPalette = new ArrayList<>();
                    for (var p : plankPalette) {
                        var d1 = p.getDarkest();
                        var d2 = p.getDarkest();

                        // brimwood_chest need to retain their darkness
                        if (!stoneType.getId().toString().equals("regions_unexplored:brimwood")) {
                            switch (removeDarkest) {
                                case 2:
                                    p.remove(d2);
                                case 1:
                                    p.remove(d1);
                            }
                        }

                        var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                        var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                        var pal = Palette.ofColors(List.of(n1, n2));
                        overlayPalette.add(pal);
                    }

                    // Generating textures
                    ResourceLocation res = StoneZone.res(path);
                    if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                        ResourceLocation trappedRes = StoneZone.res(trapped_path);

                        createChestTextures(sink, respriterNormal, respriterOverlay, plankMeta,
                                plankPalette, overlayPalette, res, trappedRes, trapOverlay);
                    }

                } catch (Exception ex) {
                    StoneZone.LOGGER.error("Failed to generate Chest block texture for for: {} - {}", block, ex);
                }
        } catch (Exception ex) {
            StoneZone.LOGGER.error("Could not generate any Chest block texture: ", ex);
        }
    }

    private static void createChestTextures(ResourceSink sink,
                                            Respriter respriter, Respriter respriterO,
                                            McMetaFile baseMeta, List<Palette> basePalette,
                                            List<Palette> overlayPalette, ResourceLocation normalRLoc,
                                            ResourceLocation trappedRLoc, TextureImage trappedOverlay) {

        TextureImage recoloredBase = respriter.recolorWithAnimation(basePalette, baseMeta);
        TextureImage recoloredOverlay = respriterO.recolorWithAnimation(overlayPalette, baseMeta);
        recoloredBase.applyOverlay(recoloredOverlay);

        if (trappedOverlay != null) {
            TextureImage trapped = recoloredBase.makeCopy();
            trapped.applyOverlay(trappedOverlay.makeCopy());
            sink.addAndCloseTexture(trappedRLoc, trapped);
        }

        sink.addAndCloseTexture(normalRLoc, recoloredBase);
    }

}
