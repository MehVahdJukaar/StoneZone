package net.mehvahdjukaar.stone_zone.neoforge;

import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.neoforge.additional_lights.AdditionalLightsModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.neoforge.rechiseled.RechiseledModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.stone_chest.StoneChestModule;
import net.neoforged.fml.common.Mod;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

/**
 * Author: Xel'Bayria
 */
@Mod(StoneZone.MOD_ID)
public class StoneZoneForge extends StoneZoneCommon {

    public StoneZoneForge() {
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
        addIfLoaded("additional_lights", () -> AdditionalLightsModule::new);
        addIfLoaded("create", () -> CreateModule::new);
        addIfLoaded("rechiseled", () -> RechiseledModule::new);
        addIfLoaded("stonechest", () -> StoneChestModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

}
