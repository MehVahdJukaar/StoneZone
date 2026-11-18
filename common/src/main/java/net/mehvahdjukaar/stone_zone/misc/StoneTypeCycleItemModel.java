package net.mehvahdjukaar.stone_zone.misc;

import com.mojang.serialization.MapCodec;
import net.mehvahdjukaar.every_compat.misc.BlockTypeCycleItemModel;
import net.mehvahdjukaar.every_compat.misc.WoodTypeCycleItemModel;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.ResolvableModel;
import org.joml.Matrix4fc;
import org.jspecify.annotations.NonNull;

public class StoneTypeCycleItemModel extends BlockTypeCycleItemModel<StoneType> {

    private static final StoneTypeCycleItemModel INSTANCE = new StoneTypeCycleItemModel();

    public StoneTypeCycleItemModel() {
        super(StoneType.class);
    }

    public record Unbaked() implements ItemModel.Unbaked {
        public static final MapCodec<WoodTypeCycleItemModel.Unbaked> MAP_CODEC = MapCodec.unit(new WoodTypeCycleItemModel.Unbaked());

        @Override
        public @NonNull MapCodec<WoodTypeCycleItemModel.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public @NonNull ItemModel bake(ItemModel.@NonNull BakingContext context, @NonNull Matrix4fc transformation) {
            return INSTANCE;
        }

        @Override
        public void resolveDependencies(ResolvableModel.@NonNull Resolver resolver) {
        }
    }

}
