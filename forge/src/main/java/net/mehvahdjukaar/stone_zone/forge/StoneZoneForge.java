package net.mehvahdjukaar.stone_zone.forge;

import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.StoneZoneClient;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.mehvahdjukaar.stone_zone.modules.forge.additional_lights.AdditionalLightsModule;
import net.mehvahdjukaar.stone_zone.modules.forge.blocks_plus.BlocksPlusModule;
import net.mehvahdjukaar.stone_zone.modules.forge.blocks_you_need.BlocksYouNeedModule;
import net.mehvahdjukaar.stone_zone.modules.forge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.stone_zone.modules.forge.create.CreateModule;
import net.mehvahdjukaar.stone_zone.modules.forge.macaws.*;
import net.mehvahdjukaar.stone_zone.modules.forge.rechiseled.RechiseledModule;
import net.mehvahdjukaar.stone_zone.modules.forge.stone_chest.StoneChestModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

/**
 * Author: Xel'Bayria
 */
@Mod(StoneZone.MOD_ID)
public class StoneZoneForge extends StoneZoneCommon {

    public StoneZoneForge() {
        this.initialize();

        MinecraftForge.EVENT_BUS.register(this);
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
        addIfLoaded("blocksplus", () -> BlocksPlusModule::new);
        addIfLoaded("blocksyouneed_luna", () -> BlocksYouNeedModule::new);
        addIfLoaded("buildersaddition", () -> BuildersAdditionModule::new);
        addIfLoaded("create", () -> CreateModule::new);
        addIfLoaded("rechiseled", () -> RechiseledModule::new);
        addIfLoaded("stonechest", () -> StoneChestModule::new);

//!! ====================================================== OTHERS ================================================== \\

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        StoneZoneClient.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

}
