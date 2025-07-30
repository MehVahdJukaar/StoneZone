package net.mehvahdjukaar.stone_zone.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.stone_zone.StoneZone;

import java.util.List;
import java.util.function.Supplier;

public class UnsafeDisablerConfigs {

    public static Supplier<List<String>> stoneTypeList;
    public static Supplier<List<String>> mudTypeList;
    public static Supplier<List<String>> entrySetList;

    public static ConfigSpec CONFIG_SPEC;

    public static void init() {}

    static {

        ConfigBuilder builder = ConfigBuilder.create(StoneZone.res("hazardous"), ConfigType.COMMON);

        String comment = """
                    ═════════════════════════ Attention ═════════════════════════
                    Don't use this if you don't know what you are doing
                        REASON:
                    This file is a conditional registration. This is harmless in Singleplayer World,
                    but harmful in SERVER because you won't able to join.

                    ══════════════════════════ Detail ═══════════════════════════
                    This file allow you to exclude StoneTypes, MudType, EntrySet
                    1) You can find their names for StoneTypes, MudType, or EntrySet in `everycomp-entries.toml`
                    2) Leave a value empty to disable that rule.

                    Module - is a Supported Mod, just a modId is sufficient.
                    EntrySet - is a FurnitureType or DecorativeType that Stone-Zone is supporting via the mod. it is either block or item.

                    NOTE: blacklisting a Module will be applied to Wood-Good, Stone-Zone, Gems-Realm - if you want to blacklist, then use everycomp-hazardous.toml
                """;
        builder.comment(comment);

        builder.push("stonetype");
        String stoneTypeExample = """
                    EXAMPLE: blacklist = [
                        "forestry:.*fireproof.*",\t\tCOMMENT: .* is an RegEx, it exclude all of StoneType containing "fireproof" from Forestry
                        "biomesoplenty:.*",\t\t\t\tCOMMENT: .* is an RegEx, it exclude all of StoneType from Wood Mod for any Module
                        "biomesoplenty:redwood"\t\tCOMMENT: exclude redwood from Stone Mod for any module
                    ]
                """;
        stoneTypeList = builder.comment("Exclude StoneType from all of Modules\n"+stoneTypeExample).define("blacklist", List.of());
        builder.pop();

        builder.push("mudtype");
        mudTypeList = builder.comment("Exclude MudType from all of Modules\n\tThe example is same as StoneType's").define("blacklist", List.of());
        builder.pop();

        builder.push("entryset");
        String entrysetExample = """
                    This is only applied to Stone-Zone.
                    EXAMPLE: blacklist = [
                        "chipped:checkered_trapdoor",\tCOMMENT: chipped:checkered_oak_trapdoor without "oak"
                        "variantvanillablocks:chest",\tCOMMENT: variantvanillablocks:oak_chest without "oak"
                        "chipped:.*"\t\t\t\t\tCOMMENT: .* is an regex which will exclude all of EntrySets from one Module
                    ]
                """;
        entrySetList = builder.comment("Exclude EntrySet from the module for All of StoneType or MudType\n"+entrysetExample).define("blacklist", List.of());
        builder.pop();

        builder.setSynced();

        CONFIG_SPEC = builder.buildAndRegister();

        CONFIG_SPEC.loadFromFile();

        // Warning Message
        if (!stoneTypeList.get().isEmpty() || !mudTypeList.get().isEmpty() || !entrySetList.get().isEmpty()) {
            EveryCompat.LOGGER.warn("""
                            \n
                            ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                            ┃                        ATTENTION                         ┃
                            ┃  You are using conditional registration via Stone-Zone.  ┃
                            ┃  Proceed at your own risk and do not complain if you     ┃
                            ┃  CANNOT connect to servers                               ┃
                            ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                    """
            );
        }

    }

}
