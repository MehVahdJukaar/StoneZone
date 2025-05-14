package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.stone_zone.modules.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.stone_zone.modules.better_archeology.BetterArcheologyModule;
import net.mehvahdjukaar.stone_zone.modules.blockus.BlockusModule;
import net.mehvahdjukaar.stone_zone.modules.chipped.ChippedModule;
import net.mehvahdjukaar.stone_zone.modules.more_beautiful_torches.MoreBeautifulTorches;
import net.mehvahdjukaar.stone_zone.modules.quark.QuarkModule;
import net.mehvahdjukaar.stone_zone.modules.quark.QuarkMudModule;
import net.mehvahdjukaar.stone_zone.modules.stoneworks.StoneworksModule;
import net.mehvahdjukaar.stone_zone.modules.twigs.TwigsModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addOtherCompatMod;
import static net.mehvahdjukaar.stone_zone.StoneZone.addIfLoaded;

public class StoneZoneCommon {

    protected void initialize() {
        StoneZone.init();

        this.addModules();
    }

    protected void addModules() {


//!! =============================================== Add Other Compat Mods ========================================== \\
        addOtherCompatMod("rechiseledcreate", "create", "rechiseled");
        addOtherCompatMod("rechiseledae2", "ae2", "rechiseled");

//!! =================================================== Add Modules ================================================ \\

        addIfLoaded("betterarcheology", () -> BetterArcheologyModule::new);
        addIfLoaded("blockus", () -> BlockusModule::new);
        addIfLoaded("building_but_better", () -> BuildingButBetterModule::new);
        addIfLoaded("chipped", () -> ChippedModule::new);
        addIfLoaded("quark", () -> QuarkModule::new);
        addIfLoaded("quark", () -> QuarkMudModule::new);
        addIfLoaded("more_beautiful_torches", () -> MoreBeautifulTorches::new);
        addIfLoaded("stoneworks", () -> StoneworksModule::new);
        addIfLoaded("twigs", () -> TwigsModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
