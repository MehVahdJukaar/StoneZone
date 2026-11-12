package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.api.EveryCompatPlugin;
import net.mehvahdjukaar.every_compat.api.PluginEvent;
import net.mehvahdjukaar.stone_zone.modules.better_archeology.BetterArcheologyModule;
import net.mehvahdjukaar.stone_zone.modules.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.stone_zone.modules.chipped.ChippedModule;
import net.mehvahdjukaar.stone_zone.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.stone_zone.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.stone_zone.modules.lets_do.CandlelightModule;
import net.mehvahdjukaar.stone_zone.modules.more_beautiful_torches.MoreBeautifulTorches;
import net.mehvahdjukaar.stone_zone.modules.mrcrayfish.RefurbishedFurnitureModule;
import net.mehvahdjukaar.stone_zone.modules.rechiseled.RechiseledModule;
import net.mehvahdjukaar.stone_zone.modules.stoneworks.StoneworksModule;
import net.mehvahdjukaar.stone_zone.modules.twigs.TwigsModule;
import net.mehvahdjukaar.stone_zone.modules.waystones.WaystonesModule;

public class StoneZonePlugin implements EveryCompatPlugin {

    @Override
    public void register(PluginEvent event) {
//!! =============================================== Add Other Compat Mods ========================================== \\
        // Rechiseled
        event.addOtherCompatMod("rechiseledae2", "ae2", "rechiseled");
        event.addOtherCompatMod("rechiseledcreate", "create", "rechiseled");

        // Create
        event.addOtherCompatMod("bits_n_bobs", "create", "bits_n_bobs");

//!! =================================================== Add Modules ================================================ \\

        event.addModule("bbb", () -> BuildingButBetterModule.class);
        event.addModule("betterarcheology", () -> BetterArcheologyModule.class);
        event.addModule("candlelight", () -> CandlelightModule.class); //!! [Let's Do]
        event.addModule("chipped", () -> ChippedModule.class);
        event.addModule("decorative_blocks", () -> DecorativeBlocksModule.class);
        event.addModule("handcrafted", () -> HandcraftedModule.class);
        event.addModule("more_beautiful_torches", () -> MoreBeautifulTorches.class);
        event.addModule("rechiseled", () -> RechiseledModule.class);
        event.addModule("refurbished_furniture", () -> RefurbishedFurnitureModule.class);
        event.addModule("stoneworks", () -> StoneworksModule.class);
        event.addModule("twigs", () -> TwigsModule.class);
        event.addModule("waystones", () -> WaystonesModule.class);

//!! ====================================================== OTHERS ================================================== \\
    }
}
