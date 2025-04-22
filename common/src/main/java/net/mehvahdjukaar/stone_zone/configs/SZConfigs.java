package net.mehvahdjukaar.stone_zone.configs;

import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.stone_zone.StoneZone;

import java.util.function.Supplier;

public class SZConfigs {

    public static final ModConfigHolder SPEC;

    public static final Supplier<Boolean> TAB_ENABLED;

    static {
        ConfigBuilder builder = ConfigBuilder.create(StoneZone.MOD_ID, ConfigType.COMMON);

        builder.push("general");

        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);

        builder.pop();

        SPEC = builder.build();

        SPEC.loadFromFile(); //manually load early
    }

    public static void init() {

    }
}
