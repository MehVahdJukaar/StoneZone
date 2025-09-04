package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.api.set.VanillaRockChildKeys;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

public class StonePaletteStrategies extends PaletteStrategies {

    // ──────────────────────────────── Below Can Be Used In addTexture() or addTextureM() -────────────────────────────────
    public static final PaletteStrategy BRICKS_STANDARD = registerCached((blockType, manager) ->
            paletteFromChildOrDefault(blockType, manager, VanillaRockChildKeys.BRICKS));

    public static final PaletteStrategy SMOOTH_STANDARD = registerCached((blockType, manager) ->
            paletteFromChildOrDefault(blockType, manager, VanillaRockChildKeys.SMOOTH));

    public static final PaletteStrategy POLISHED_STANDARD = registerCached((blockType, manager) ->
            paletteFromChildOrDefault(blockType, manager, VanillaRockChildKeys.POLISHED));


    private static PaletteStrategy.@NotNull PaletteAndAnimation paletteFromChildOrDefault(BlockType blockType, ResourceManager manager, String childKey) {
        var child = blockType.getChild(childKey);
        if (child == null) {
            return PaletteStrategies.makePaletteFromMainChild(blockType, manager);
        }
        return PaletteStrategies.makePaletteFromChild(blockType, manager, childKey, null, null);
    }
}
