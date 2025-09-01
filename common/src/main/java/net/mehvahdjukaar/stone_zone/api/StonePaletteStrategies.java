package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys;

public class StonePaletteStrategies extends PaletteStrategies {

    // ──────────────────────────────── Below Can Be Used In addTexture() or addTextureM() -────────────────────────────────
    public static final PaletteStrategy BRICKS_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaRockChildKeys.BRICKS, null, null));

    public static final PaletteStrategy SMOOTH_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaRockChildKeys.SMOOTH, null, null));

    public static final PaletteStrategy POLISHED_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaRockChildKeys.POLISHED, null, null));

}
