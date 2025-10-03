package net.mehvahdjukaar.stone_zone.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.fabric.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.fabric.rechiseled.RechiseledModule;

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;

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
        addIfLoaded("mcwbridges", () -> MacawBridgesModule.class);
        addIfLoaded("mcwfences", () -> MacawFencesModule.class);
        addIfLoaded("mcwwindows", () -> MacawWindowsModule.class);
        addIfLoaded("mcwroofs", () -> MacawRoofsModule.class);
        addIfLoaded("mcwstairs", () -> MacawStairsModule.class);
        addIfLoaded("mcwpaths", () -> MacawPathsModule.class);

        // General
        addIfLoaded("create", () -> CreateModule.class);
        addIfLoaded("rechiseled", () -> RechiseledModule.class);


//!! ====================================================== OTHERS ================================================== \\

    }
}
