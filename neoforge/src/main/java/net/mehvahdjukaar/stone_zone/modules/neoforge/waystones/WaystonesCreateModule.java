package net.mehvahdjukaar.stone_zone.modules.neoforge.waystones;

//import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingRecipe;
//import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
//import net.mehvahdjukaar.moonlight.api.resources.RecipeTemplate;
//import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
//import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
//import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
//import net.minecraft.core.RegistryAccess;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.ShapedRecipePattern;

//import java.util.Map;

///SUPPORT Create: Waystones Recipe
public class WaystonesCreateModule extends StoneZoneModule {
    // Check this: https://www.curseforge.com/minecraft/mc-mods/create-waystones-recipes for when Create 26.1.2 is out

    public WaystonesCreateModule(String modId) {
        super(modId, "wys");
    }

    @Override
    public void onModSetup() {
        super.onModSetup();

//        if (PlatHelper.isModLoaded("create_waystones_recipes")) {
//            RecipeTemplate.register(MechanicalCraftingRecipe.class, (baseRecipe, oldStoneType, newStoneType) -> {
//
//                var originalData = baseRecipe.pattern.data.get().key();
//                Map<Character, Ingredient> newKey = new java.util.HashMap<>(Map.copyOf(originalData));
//
//                for (var set : baseRecipe.pattern.data.get().key().entrySet()) {
//
//                    var key = set.getKey();
//                    var oldItem = set.getValue().getItems()[0];
//
//                    if (BlockSetAPI.getBlockTypeOf(oldItem.getItem(), StoneType.class) == VanillaStoneTypes.DEEPSLATE
//                            && StoneType.changeItemType(oldItem.getItem(), oldStoneType, newStoneType) != null) {
//
//                        var newItem = RecipeTemplate.convertItemStack(oldItem, oldStoneType, newStoneType);
//
//                        if (newItem != null) newKey.put(key, Ingredient.of(newItem));
//                    }
//                }
//
//
//                ItemStack originalResult = baseRecipe.getResultItem(RegistryAccess.EMPTY);
//                ItemStack newResult = RecipeTemplate.convertItemStack(originalResult, oldStoneType, newStoneType);
//
//                ShapedRecipePattern pattern = ShapedRecipePattern.of(newKey, baseRecipe.pattern.data.get().pattern());
//
//                if (newResult == null) {
//                    throw new UnsupportedOperationException("[Waystones Module @ stonezone] Failed to convert recipe result for MechanicalCraftingRecipe");
//                }
//                else
//                    return new MechanicalCraftingRecipe(baseRecipe.getGroup(), baseRecipe.category(), pattern, newResult, baseRecipe.acceptsMirrored());
//            });
//        }
    }

}