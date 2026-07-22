package net.mehvahdjukaar.stone_zone.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.StoneZoneClient;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.fabric.blockus.BlockusModule;
import net.mehvahdjukaar.stone_zone.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.fabric.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.fabric.wraith_waystones.WraithWaystonesModule;

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;

public class StoneZoneFabric extends StoneZoneCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();

        if (PlatHelper.getPhysicalSide().isClient()) {
            ItemTooltipCallback.EVENT.register(StoneZoneClient::onItemTooltip);
        }
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
        addIfLoaded("blockus", () -> BlockusModule.class);
        addIfLoaded("create", () -> CreateModule.class);
        addIfLoaded("fwaystones", () -> WraithWaystonesModule.class);


//!! ====================================================== OTHERS ================================================== \\

    }
}
