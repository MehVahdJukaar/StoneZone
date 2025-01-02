package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.stone_zone.modules.bbb.BuildingButBetterModule;
import net.mehvahdjukaar.stone_zone.modules.betterarcheology.BetterArcheologyModule;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.quark.QuarkModule;
import net.mehvahdjukaar.stone_zone.modules.quark.QuarkMudModule;
import net.mehvahdjukaar.stone_zone.modules.rechiseled.RechiseledModule;
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

        addIfLoaded("twigs", () -> TwigsModule::new);
        addIfLoaded("create", () -> CreateModule::new);
        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
        addIfLoaded("stoneworks", () -> StoneworksModule::new);
        addIfLoaded("quark", () -> QuarkModule::new);
        addIfLoaded("quark", () -> QuarkMudModule::new);
        addIfLoaded("rechiseled", () -> RechiseledModule::new);
        addIfLoaded("betterarcheology", () -> BetterArcheologyModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
