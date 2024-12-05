package net.mehvahdjukaar.stone_zone.modules;

import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class SZModule extends SimpleModule {
    public SZModule(String modId, String shortId) {
        super(modId, shortId);
    }

    @Override
    public ResourceKey<CreativeModeTab> getUniqueTab(){
        return StoneZone.TAB.key();
    }

    @Override
    public String toString() {
        return "StoneZone " + LangBuilder.getReadableName(modId) + " Module";
    }
}
