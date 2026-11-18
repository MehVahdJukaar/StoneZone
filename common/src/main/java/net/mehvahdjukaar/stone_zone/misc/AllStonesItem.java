package net.mehvahdjukaar.stone_zone.misc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class AllStonesItem extends Item {

    public AllStonesItem() {
        super(new Item.Properties());
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, level, entity, slot);
        stack.setCount(0);

    }

//    @Override
//    @ClientOnly
//    public Supplier<ItemStackRenderer> getRendererFactory() {
//        return StoneTypeCycleItemModel::new;
//    }
}
