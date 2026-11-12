package net.mehvahdjukaar.stone_zone.fabric;

import net.mehvahdjukaar.every_compat.api.PluginEvent;
import net.mehvahdjukaar.stone_zone.StoneZonePlugin;
import net.mehvahdjukaar.stone_zone.modules.fabric.blockus.BlockusModule;
import net.mehvahdjukaar.stone_zone.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.fabric.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.fabric.wraith_waystones.WraithWaystonesModule;

public class StoneZoneFabricPlugin extends StoneZonePlugin {

    @Override
    public void register(PluginEvent event) {
        super.register(event);

//!! =================================================== Add Modules ================================================ \\

        // Macaw's
        event.addModule("mcwbridges", () -> MacawBridgesModule.class);
        event.addModule("mcwfences", () -> MacawFencesModule.class);
        event.addModule("mcwwindows", () -> MacawWindowsModule.class);
        event.addModule("mcwroofs", () -> MacawRoofsModule.class);
        event.addModule("mcwstairs", () -> MacawStairsModule.class);
        event.addModule("mcwpaths", () -> MacawPathsModule.class);

        // General
        event.addModule("blockus", () -> BlockusModule.class);
        event.addModule("create", () -> CreateModule.class);
        event.addModule("fwaystones", () -> WraithWaystonesModule.class);


//!! ====================================================== OTHERS ================================================== \\

    }
}
