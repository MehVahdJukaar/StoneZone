package net.mehvahdjukaar.stone_zone;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.stone_zone.fabric.modules.rechiseled.RechiseledModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

public class StoneZoneFabric extends StoneZoneCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();
    }

    @Override
    protected void addModules() {
        super.addModules();


//!! =============================================== Add Other Compat Mods ========================================== \\


//!! =================================================== Add Modules ================================================ \\

        //!! Macaw's
//        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
        addIfLoaded("rechiseled", () -> RechiseledModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
