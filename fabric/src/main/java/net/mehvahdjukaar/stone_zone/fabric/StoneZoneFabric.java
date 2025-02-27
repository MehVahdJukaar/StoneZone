package net.mehvahdjukaar.stone_zone.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.fabric.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.fabric.rechiseled.RechiseledModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

public class StoneZoneFabric extends StoneZoneCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();
    }

    @Override
    protected void addModules() {
        super.addModules();

//!! =================================================== Add Modules ================================================ \\

        // Macaw's
        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
        addIfLoaded("mcwfences", () -> MacawFencesModule::new);
        addIfLoaded("mcwwindows", () -> MacawWindowsModule::new);
        addIfLoaded("mcwroofs", () -> MacawRoofsModule::new);
        addIfLoaded("mcwstairs", () -> MacawStairsModule::new);
        addIfLoaded("mcwpaths", () -> MacawPathsModule::new);

        // General
        addIfLoaded("rechiseled", () -> RechiseledModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }
}
