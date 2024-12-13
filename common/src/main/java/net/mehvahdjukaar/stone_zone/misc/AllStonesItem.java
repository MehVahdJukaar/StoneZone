package net.mehvahdjukaar.stone_zone.misc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class AllStonesItem extends Item implements ICustomItemRendererProvider {

    public AllStonesItem() {
        super(new Item.Properties());
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Supplier<ItemStackRenderer> getRendererFactory() {
        return StoneTypeCycleItemRenderer::new;
    }
}
