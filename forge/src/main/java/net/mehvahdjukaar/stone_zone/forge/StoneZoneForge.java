package net.mehvahdjukaar.stone_zone.forge;

import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.forge.macaws.MacawBridgesModule;
import net.mehvahdjukaar.stone_zone.modules.forge.macaws.MacawFencesModule;
import net.mehvahdjukaar.stone_zone.modules.forge.macaws.MacawRoofsModule;
import net.mehvahdjukaar.stone_zone.modules.forge.macaws.MacawWindowsModule;
import net.mehvahdjukaar.stone_zone.modules.forge.rechiseled.RechiseledModule;
import net.mehvahdjukaar.stone_zone.modules.forge.stone_chest.StoneChestModule;
import net.minecraftforge.fml.common.Mod;

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

        // General
        addIfLoaded("rechiseled", () -> RechiseledModule::new);
        addIfLoaded("stonechest", () -> StoneChestModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

}
