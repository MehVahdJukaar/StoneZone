package net.mehvahdjukaar.stone_zone.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.stone_zone.StoneZone;

public class StoneZoneFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        StoneZone.init();
    }
}
