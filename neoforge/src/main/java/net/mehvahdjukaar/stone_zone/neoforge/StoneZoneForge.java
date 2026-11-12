package net.mehvahdjukaar.stone_zone.neoforge;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.StoneZoneClient;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * Author: Xel'Bayria
 */
@Mod(StoneZone.MOD_ID)
public class StoneZoneForge extends StoneZoneCommon {

    public StoneZoneForge(IEventBus bus) {
        this.initialize();

        NeoForge.EVENT_BUS.register(this); // Ensure that iTemTooltipEvent() get called
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        if (PlatHelper.getPhysicalSide().isClient())
            StoneZoneClient.onItemTooltip(event.getItemStack(), event.getContext(), event.getFlags(), event.getToolTip());
    }

}
