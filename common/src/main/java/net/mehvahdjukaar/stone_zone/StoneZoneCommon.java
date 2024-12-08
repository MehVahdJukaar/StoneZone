package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.stone_zone.modules.bbb.BuildingButBetterModule;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.stoneworks.StoneworksModule;
import net.mehvahdjukaar.stone_zone.modules.twigs.TwigsModule;

import static net.mehvahdjukaar.stone_zone.StoneZone.addIfLoaded;

public class StoneZoneCommon {

    protected void initialize() {
        StoneZone.init();

        this.addModules();
    }

    protected void addModules() {


//!! =============================================== Add Other Compat Mods ========================================== \\


//!! =================================================== Add Modules ================================================ \\

        addIfLoaded("twigs", () -> TwigsModule::new);
        addIfLoaded("create", () -> CreateModule::new);
        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
        addIfLoaded("stoneworks", () -> StoneworksModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
