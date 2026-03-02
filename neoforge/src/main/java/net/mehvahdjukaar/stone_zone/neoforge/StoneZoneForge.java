package net.mehvahdjukaar.stone_zone.neoforge;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.StoneZoneClient;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.neoforge.additional_lights.AdditionalLightsModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.neoforge.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.neoforge.stone_chest.StoneChestModule;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;

/**
 * Author: Xel'Bayria
 */
@Mod(StoneZone.MOD_ID)
public class StoneZoneForge extends StoneZoneCommon {

    public StoneZoneForge(IEventBus bus) {
        RegHelper.startRegisteringFor(bus);
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
        addIfLoaded("additional_lights", () -> AdditionalLightsModule.class);
        addIfLoaded("create", () -> CreateModule.class);
        addIfLoaded("stonechest", () -> StoneChestModule.class);

//!! ====================================================== OTHERS ================================================== \\

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        StoneZoneClient.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

}
