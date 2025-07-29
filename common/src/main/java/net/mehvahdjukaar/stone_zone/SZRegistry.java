package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.mehvahdjukaar.stone_zone.misc.AllStonesItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

import static net.mehvahdjukaar.stone_zone.configs.SZConfigs.TAB_ITEM_SEARCH_ENABLED;

public class SZRegistry {

    public static void init() {}

    public static final Supplier<AllStonesItem> ALL_STONES = RegHelper.registerItem(StoneZone.res("all_stones"), AllStonesItem::new);

    @Nullable
    public static final RegSupplier<CreativeModeTab> MOD_TAB = SZConfigs.TAB_ENABLED.get() ?
            RegHelper.registerCreativeModeTab(StoneZone.res("stonezone"),
                    TAB_ITEM_SEARCH_ENABLED.get(), // searchBar
                    builder -> builder.icon(() -> ALL_STONES.get().getDefaultInstance())
                            .backgroundSuffix((TAB_ITEM_SEARCH_ENABLED.get()) ? "item_search.png" : "items.png")
                            .title(Component.translatable("itemGroup.stonezone.stonezone"))
                            .build())
            : null;
}
