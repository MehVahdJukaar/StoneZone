package net.mehvahdjukaar.stone_zone.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.stone_zone.StoneZoneClient;
import net.mehvahdjukaar.stone_zone.StoneZoneCommon;

public class StoneZoneFabric extends StoneZoneCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();

        if (PlatHelper.getPhysicalSide().isClient()) {
            ItemTooltipCallback.EVENT.register(StoneZoneClient::onItemTooltip);
        }
    }
}
