package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;


public class SZModule extends SimpleModule {
    public SZModule(String modId, String shortId) {
        super(modId, shortId, StoneZone.MOD_ID);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return SZRegistry.MOD_TAB.getKey();
    }

    @Override
    public String toString() {
        return "StoneZone " + LangBuilder.getReadableName(modId) + " Module";
    }

    @Override
    public ResourceLocation makeMyRes(String name) {
        return super.makeMyRes(name);
    }

    @Override
    public boolean isEntryAlreadyRegistered(String name, BlockType stoneType, Registry<?> registry) {
        if (stoneType.isVanilla()) return true;

        name = name.substring(name.lastIndexOf("/") + 1);
        String fromWoodMod = stoneType.getNamespace();
        String stoneID = stoneType.getId().toString();

        return false;
    }

}
