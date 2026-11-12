package net.mehvahdjukaar.stone_zone.common_classes;

import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CompatChestTexture {

    public static void generateChestTexture(ResourceSink handler, ResourceManager manager,
                                            String shortenedID, StoneType stoneType,
                                            Identifier normalRLoc, Identifier maskRLoc, Identifier overlayRLoc,
                                            Identifier trappedORLoc,
                                            int darkControl
    ) {
        generateChestTexture(handler, manager, shortenedID, stoneType, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc,
                darkControl, 0);
    }

    public static void generateChestTexture(ResourceSink handler, ResourceManager manager,
                                            String shortenedID, StoneType stoneType,
                                            Identifier normalRLoc, Identifier maskRLoc, Identifier overlayRLoc,
                                            Identifier trappedORLoc
    ) {
        generateChestTexture(handler, manager, shortenedID, stoneType, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc,
                0, 0);
    }

    /**
     * Generate a texture for chest and trapped_chest
     *
     * @param darkControl
     * <Ol>
     *     <li>1: Darkening 2nd darkest and 3rd darkest by 10% - OPTIONAL: darkestOffset can offset</li>
     *     <li>2: Modifying Planks' texture to have Average Contrast</li>
     * </ol>
     */
    public static void generateChestTexture(ResourceSink sink, ResourceManager manager, String shortenedID, StoneType stoneType,
                                            Identifier normalRLoc, Identifier maskRLoc, Identifier overlayRLoc,
                                            Identifier trappedORLoc, int darkControl, int darkestOffset) {
        generateChestTexture(sink, manager, shortenedID, stoneType, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc,
                darkControl, darkestOffset, "_chest", "_trapped_chest");
    }

    public static void generateChestTexture(ResourceSink sink, ResourceManager manager, String shortenedID, StoneType stoneType,
                                            Identifier normalRLoc, Identifier maskRLoc, Identifier overlayRLoc,
                                            Identifier trappedORLoc, int darkControl, int darkestOffset,
                                            String normalSuffix, String trappedSuffix) {

        try (TextureImage texture = TextureImage.open(manager, normalRLoc);
             @Nullable TextureImage mask = (maskRLoc == null) ? null : TextureImage.open(manager, maskRLoc);
             TextureImage overlay = TextureImage.open(manager, overlayRLoc);
             @Nullable TextureImage trapOverlay = (trappedORLoc == null) ? null : TextureImage.open(manager, trappedORLoc);
        ) {

            Respriter respriterNormal = (mask == null) ? Respriter.of(texture) : Respriter.masked(texture, mask);
            Respriter respriterOverlay = Respriter.of(overlay);

            String path = "entity/chest/" + shortenedID + "/" + stoneType.getAppendableId() + normalSuffix;
            String trapped_path = "entity/chest/" + shortenedID + "/" + stoneType.getAppendableId() + trappedSuffix;
            if (normalRLoc.toString().contains("left")) {
                path += "_left";
                trapped_path += "_left";
            } else if (normalRLoc.toString().contains("right")) {
                path += "_right";
                trapped_path += "_right";
            }

            try (TextureImage plankTexture = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, stoneType.stone))) {

                List<Palette> mainPalette = Palette.fromAnimatedImage(plankTexture);

                mainPalette.forEach(palette -> {
                    int plankSize = mainPalette.size();
                    Palette texturePalette = Palette.fromAnimatedImage(texture, mask).getFirst();
                    int amount = texturePalette.size();

                    if (plankSize != amount) {
                        palette.matchSize(amount);
                    }

                    // brimwood_chest need to retain their darkness
                    if (!stoneType.getId().toString().equals("regions_unexplored:brimwood")) {
                        switch (darkControl) {
                            case 2: {
                                palette.multiplyContrast(0.5F);
                                break;
                            }
                            case 1: {
                                palette.getDarkest(1 + darkestOffset).getDarkened();
                                palette.getDarkest(2 + darkestOffset).getDarkened();
                                break;
                            }
                        }
                    }

                });

                List<Palette> overlayPalette = Palette.fromAnimatedImage(overlay);

                // Generating textures
                Identifier res = StoneZone.res(path);
                if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                    Identifier trappedRes = StoneZone.res(trapped_path);

                    createChestTextures(respriterNormal, respriterOverlay, plankTexture,
                            mainPalette, overlayPalette, res, trappedRes, trapOverlay, sink);
                }

            } catch (Exception ex) {
                StoneZone.LOGGER.error("Failed to generate Chest block texture for for: {} - {}", texture, ex);
            }
        } catch (Exception ex) {
            StoneZone.LOGGER.error("Could not generate any Chest block texture: ", ex);
        }
    }

    private static void createChestTextures(Respriter respriter, Respriter respriterO,
                                            TextureImage baseTexture, List<Palette> planksPalette,
                                            List<Palette> overlayPalette, Identifier normalRLoc,
                                            Identifier trappedRLoc, TextureImage trappedOverlay,
                                            ResourceSink sink) {

        try (TextureImage recoloredBase = respriter.recolorWithAnimation(planksPalette, baseTexture.getMcMeta());
             TextureImage recoloredOverlay = respriterO.recolorWithAnimation(overlayPalette, baseTexture.getMcMeta())) {

            TextureOps.applyOverlayOnExisting(recoloredBase, recoloredOverlay);

            if (trappedOverlay != null) {
                TextureImage trapped = recoloredBase.makeCopy();
                TextureOps.applyOverlay(trapped, trappedOverlay);
                sink.addTexture(trappedRLoc, trapped);
            }

            sink.addTexture(normalRLoc, recoloredBase);
        }
    }

}
