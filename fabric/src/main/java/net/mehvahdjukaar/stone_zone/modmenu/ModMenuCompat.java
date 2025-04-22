package net.mehvahdjukaar.stone_zone.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.moonlight.api.platform.configs.fabric.FabricConfigListScreen;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return p -> new FabricConfigListScreen(StoneZone.MOD_ID, Items.STONE.getDefaultInstance(),
                Component.literal("§6Stone Zone Configs"), p, SZConfigs.SPEC);
    }
}