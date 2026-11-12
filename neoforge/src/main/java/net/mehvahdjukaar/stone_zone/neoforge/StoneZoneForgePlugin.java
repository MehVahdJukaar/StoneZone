package net.mehvahdjukaar.stone_zone.neoforge;

import net.mehvahdjukaar.every_compat.api.ECPlugin;
import net.mehvahdjukaar.every_compat.api.PluginEvent;
import net.mehvahdjukaar.stone_zone.StoneZonePlugin;
import net.mehvahdjukaar.stone_zone.modules.neoforge.additional_lights.AdditionalLightsModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.create.BitsNBobsModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.neoforge.quark.QuarkModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.quark.QuarkMudModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.stone_chest.StoneChestModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.waystones.WaystonesCreateModule;

@ECPlugin
public class StoneZoneForgePlugin extends StoneZonePlugin {

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
        event.addModule("additional_lights", () -> AdditionalLightsModule.class);
        event.addModule("create", () -> CreateModule.class);
        event.addModule("bits_n_bobs", () -> BitsNBobsModule.class);
        event.addModule("stonechest", () -> StoneChestModule.class);
        event.addModules("quark", () -> QuarkModule.class, () -> QuarkMudModule.class);
        event.addModule("waystones", () -> WaystonesCreateModule.class);

//!! ====================================================== OTHERS ================================================== \\

    }
}
