package net.mehvahdjukaar.stone_zone.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.minecraft.resources.Identifier;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ClientHelper.getMoonlightConfigScreen(StoneZone.MOD_ID, parent,
                Identifier.withDefaultNamespace("textures/block/stone.png"));
    }
}
