package net.mehvahdjukaar.stone_zone.modules.mrcrayfish;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.refurbished.block.FurnitureHorizontalBlock;
import com.mrcrayfish.furniture.refurbished.crafting.StackedIngredient;
import com.mrcrayfish.furniture.refurbished.crafting.WorkbenchContructingRecipe;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs;
import net.mehvahdjukaar.moonlight.api.resources.RecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.BlockTypeSwapIngredient;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;


///SUPPORT: v1.0.12+
public class RefurbishedFurnitureModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> stepping_stones;

    public RefurbishedFurnitureModule(String modId) {
        super(modId, "rfm");
        Supplier<CreativeModeTab> tab = getModTab("creative_tab");

        stepping_stones = StoneZoneEntrySet.of(StoneType.class, "stepping_stones",
                        getModBlock("stone_stepping_stones"), () -> VanillaStoneTypes.STONE,
                        stoneType -> new CompatSteppingStoneBlock(Utils.copyPropertySafe(stoneType.stone).strength(1.5F).sound(stoneType.getSound()).noOcclusion())
                )
                .addTexture(modRes("block/stone_stepping_stones"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.COMBINATION_STEP_SOUND_BLOCKS, Registries.BLOCK)
                .addTag(modRes("outdoors"), Registries.ITEM)
                .setTab(tab)
                .addRecipe(modRes("constructing/stone_stepping_stones"))
                .build();
        this.addEntry(stepping_stones);

    }

    @Override
    public void onModSetup() {
        super.onModSetup();

        if (!UnsafeDisablerConfigs.INCLUDE_ALL_WOOD_MODULES.get()) {
            RecipeTemplate.register(WorkbenchContructingRecipe.class, (recipe, from, to) -> {
                List<StackedIngredient> newList = convertStackedIngredients(recipe.getMaterials(), from, to);
                ItemStack originalResult = recipe.getResultItem(RegistryAccess.EMPTY);
                ItemStack newResult = RecipeTemplate.convertItemStack(originalResult, from, to);
                if (newResult == null) {
                    throw new UnsupportedOperationException("Failed to convert recipe result");
                } else {
                    NonNullList<StackedIngredient> ingredients = NonNullList.of(StackedIngredient.EMPTY, newList.toArray(StackedIngredient[]::new));
                    return new WorkbenchContructingRecipe(ingredients, newResult, recipe.showNotification());
                }
            });
        }
    }

    private static <T extends BlockType> @NotNull List<StackedIngredient> convertStackedIngredients(
            NonNullList<StackedIngredient> original, T from, T to) {

        List<StackedIngredient> newList = new ArrayList<>();
        for (StackedIngredient si : original) {
            if (si.ingredient().isEmpty()) {
                newList.add(si);
            } else {
                newList.add(StackedIngredient.of(
                        BlockTypeSwapIngredient.create(si.ingredient(), from, to),
                        si.count()));
            }
        }
        return newList;
    }

//      ┌──────────────────────────────────────────────────────────┐
//      │                         CLASSES                          │
//      └──────────────────────────────────────────────────────────┘
    //NOTE: The class, SteppingStoneBlock has a few unnecessary codes that are not really used at all.
    public class CompatSteppingStoneBlock extends FurnitureHorizontalBlock {

        public CompatSteppingStoneBlock(BlockBehaviour.Properties properties) {
            super(properties);
            this.registerDefaultState(this.getStateDefinition().any().setValue(DIRECTION, Direction.NORTH));
        }

        protected Map<BlockState, VoxelShape> generateShapes(ImmutableList<BlockState> states) {
            VoxelShape baseShape = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 1.0F, 16.0F);
            return ImmutableMap.copyOf((Map)states.stream().collect(Collectors.toMap((state) -> state, (o) -> baseShape)));
        }
    }

}