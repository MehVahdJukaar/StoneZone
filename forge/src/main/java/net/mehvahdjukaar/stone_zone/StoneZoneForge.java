package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.stone_zone.forge.modules.macaws.MacawBridgesModule;
import net.mehvahdjukaar.stone_zone.forge.modules.rechiseled.RechiseledModule;
import net.minecraftforge.fml.common.Mod;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

/**
 * Author: MehVahdJukaar
 */
@Mod(StoneZone.MOD_ID)
public class StoneZoneForge extends StoneZoneCommon {

    public StoneZoneForge() {
        this.initialize();
    }

    @Override
    protected void addModules() {
        super.addModules();


//!! =============================================== Add Other Compat Mods ========================================== \\


//!! =================================================== Add Modules ================================================ \\

        // Macaw's
//        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
        addIfLoaded("rechiseled", () -> RechiseledModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

}
