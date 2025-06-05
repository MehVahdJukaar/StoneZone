package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.stone_zone.api.set.MudType;
import net.mehvahdjukaar.stone_zone.api.set.MudTypeRegistry;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class StoneZoneClient {

    public static void onItemTooltip(ItemStack stack, TooltipFlag tooltipFlag, List<Component> components) {
        boolean modTooltip = ECConfigs.MOD_TOOPTIP.get();
        boolean blockTooltip = ECConfigs.BLOCK_TYPE_TOOLTIP.get();

        if (modTooltip || blockTooltip && (tooltipFlag.isAdvanced() || !ECConfigs.TOOLTIPS_ADVANCED.get())) {
            Item item = stack.getItem();
            var compatModule = StoneZone.getModuleOfItem(item);
            if (compatModule != null) {
                if (blockTooltip) {
                    StoneType metalType = StoneTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (metalType != null) {
                        components.add(Component.translatable("tooltip.stonezone.stone_type", metalType.toString()).withStyle(ChatFormatting.GRAY));
                    }
                    MudType gemType = MudTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (gemType != null) {
                        components.add(Component.translatable("tooltip.stonezone.mud_type", gemType.toString()).withStyle(ChatFormatting.GRAY));
                    }
                }
            }
        }
    }
}
