package net.mehvahdjukaar.stone_zone.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;

public class StoneZoneFabric extends StoneZoneCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        StoneZone.init();
    }

    @Override
    protected void addModules() {


//!! =============================================== Add Other Compat Mods ========================================== \\


//!! =================================================== Add Modules ================================================ \\


//!! ====================================================== OTHERS ================================================== \\

    }
}
