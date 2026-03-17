package net.mehvahdjukaar.stone_zone.misc;

import net.mehvahdjukaar.every_compat.misc.BlockTypeCycleItemRenderer;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.configs.SZConfigs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class StoneTypeCycleItemRenderer extends BlockTypeCycleItemRenderer<StoneType> {

    public StoneTypeCycleItemRenderer() {
        super(StoneType.class);
    }

    @Override
    @SuppressWarnings("ConstantValue")
    public ItemStack getItemIcon() {
        var itemId = SZConfigs.CREATIVE_TAB_ICON.get();
        ItemStack item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId)).getDefaultInstance();

        if (Objects.nonNull(item)) return item;
        else return Items.BARRIER.getDefaultInstance();
    }

    @Override
    public boolean getDisableCycleItemRenderer() {
        return SZConfigs.DISABLE_CYCLE_ITEM_RENDERER.get();
    }
}
