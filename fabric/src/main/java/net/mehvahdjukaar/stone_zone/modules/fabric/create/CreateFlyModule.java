package net.mehvahdjukaar.stone_zone.modules.fabric.create;

import com.zurrtum.create.client.AllModels;
import com.zurrtum.create.client.foundation.block.connected.*;
import com.zurrtum.create.client.infrastructure.model.CTModel;
import com.zurrtum.create.content.decoration.MetalLadderBlock;
import com.zurrtum.create.content.decoration.MetalScaffoldingBlock;
import com.zurrtum.create.content.decoration.encasing.CasingBlock;
import com.zurrtum.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.zurrtum.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.zurrtum.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.zurrtum.create.content.logistics.funnel.BeltFunnelBlock;
import com.zurrtum.create.content.logistics.funnel.FunnelBlock;
import com.zurrtum.create.content.logistics.tableCloth.TableClothBlock;
import com.zurrtum.create.content.logistics.tableCloth.TableClothBlockItem;
import net.mehvahdjukaar.every_compat.api.CompatModuleClient;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.UtilityRecipe;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModuleClient;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneTypeRegistry;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModuleAbstract;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;


///SUPPORT: v6.0.9+
public class CreateFlyModule extends CreateModuleAbstract {

    public CreateFlyModule(String modId) {
        super(modId);
    }

    @Override
    protected Block newCasingBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new CasingBlock(properties);
    }

    @Override
    protected Block newEncasedShaftedBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> casingBlock) {
        return new EncasedShaftBlock(properties, casingBlock.get());
    }

    @Override
    protected Block newEncasedCogwheelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(properties, false, casingBlock.get());
    }

    @Override
    protected Block newEncasedLargeCogwheelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(properties, true, casingBlock.get());
    }

    @Override
    protected Block newFunnelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> beltFunnelBlock) {
        return new CompatFunnelBlock(properties, beltFunnelBlock);
    }

    @Override
    protected Block newBeltFunnelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Block funnelBlock) {
        return new BeltFunnelBlock((FunnelBlock) funnelBlock, properties);
    }

    @Override
    protected Block makeSlidingDoorBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new SlidingDoorBlock(properties, SlidingDoorBlock.STONE_SET_TYPE.get(), true);
    }

    @Override
    protected Block makeMetalLadderBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new MetalLadderBlock(properties);
    }

    @Override
    protected Block makeMetalBarsBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new IronBarsBlock(properties);
    }

    @Override
    protected Block makeMetalScaffoldingBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new MetalScaffoldingBlock(properties);
    }

    @Override
    protected Block newTableClothBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new TableClothBlock(properties.requiresCorrectToolForDrops(), stoneType.getAppendableId());
    }

    @Override
    protected Item newTableClothBlockItem(Block block, Item.Properties properties) {
        return new TableClothBlockItem(block, properties);
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                      Dynamic Resources                                       │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    @Override
    // RECIPES, TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->

                StoneTypeRegistry.INSTANCE.forEach(stoneType -> {

                if (!stoneType.isVanilla() && !stoneType.getNamespace().equals("create")) {

                    // Adding all blocks of a StoneType except "slab" to Array to be tagged
                    ArrayList<Block> BlockMap = new ArrayList<>();

                    for (EntrySet<?> entry : this.getEntries()) {
                        Block currentBlock = stoneType.getBlockOfThis(this.modId + ":" + entry.getName());
                        if (Objects.nonNull(currentBlock) && !currentBlock.toString().contains("slab")) {
                            BlockMap.add(currentBlock);
                        }
                    }
                    BlockMap.add(stoneType.stone);

                    Block[] blocks = new Block[BlockMap.size()];
                    BlockMap.toArray(blocks);

                    // Tags
                    Identifier tagResLoc = StoneZone.res(stoneType.getNamespace() + "/" + stoneType.getTypeName());
                    boolean isTagCreated = UtilityTag.createAndAddCustomTags(tagResLoc, sink, blocks);

                    // Recipes
                    if (isTagCreated) {
                        for (EntrySet<?> entry : this.getEntries()) {
                            Identifier blockId = Utils.getID(((SimpleEntrySet<?, ?>) entry).getBaseBlock());
                            String prefix = blockId.getPath();
                            Block output = stoneType.getBlockOfThis(this.modId + ":" + entry.getName());

                            Identifier recipeFileLoc = modRes(prefix + "_from_stone_types_andesite_stonecutting");
                            Identifier recipeResLoc = ResType.RECIPES.getPath(recipeFileLoc);
                            Identifier newRecipeLoc = StoneZone.res(recipeFileLoc.getPath()
                                            .replace("andesite", stoneType.getTypeName())
                                            .replace("_stonecutting", "")
                                            .replace("_stone_types", ""))
                                    .withPrefix(shortenedId() + "/" + stoneType.getNamespace() + "/stonecutting/");

                            UtilityRecipe.stonecuttingWithTagRecipe(output, recipeResLoc, tagResLoc, newRecipeLoc, sink, manager);
                        }
                    }
                }
            })

        );
    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                            Client                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘
    public CompatModuleClient<?> createClient() {
            return new Client();
        }

    private class Client extends StoneZoneModuleClient<CreateFlyModule> {
        private Client() {
            super(CreateFlyModule.this);
        }

        @Override
        public void onClientSetup() {
            super.onClientSetup();
            CreateClientModule.registerConnectedBlock(getModule());
        }

        private static class CreateClientModule {
            private static void registerConnectedBlock(CreateFlyModule module) {
                module.layereds.blocks.forEach((stone, block) -> {
                    String capPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/cap/" + stone.getTypeName() + "_cut_cap";
                    String layeredPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/layered/" + stone.getTypeName() + "_cut_layered";

                    CTSpriteShiftEntry capShift = CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL,
                            StoneZone.res(capPath), StoneZone.res(capPath + "_connected"));
                    CTSpriteShiftEntry layeredShift = CTSpriteShifter.getCT(AllCTTypes.HORIZONTAL_KRYPPERS,
                            StoneZone.res(layeredPath), StoneZone.res(layeredPath + "_connected"));

                    // LAYERED
                    AllModels.register(block, (blockState, unbakedRoot) ->
                            new CTModel(blockState, unbakedRoot, new HorizontalCTBehaviour(layeredShift, capShift)));

                    // PILLAR
                    Block pillarBlock = module.pillars.blocks.get(stone);
                    if (pillarBlock != null) {
                        String pillarPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/pillar/" + stone.getTypeName() + "_cut_pillar";

                        CTSpriteShiftEntry pillarShift = CTSpriteShifter.getCT(AllCTTypes.RECTANGLE,
                                StoneZone.res(pillarPath), StoneZone.res(pillarPath + "_connected"));

                        AllModels.register(pillarBlock, (blockState, unbakedRoot) ->
                                new CTModel(blockState, unbakedRoot, new RotatedPillarCTBehaviour(pillarShift, capShift)));

                    }

                });
            }
        }

    }

    //      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
    //      │                                           CLASSES                                            │
    //      └──────────────────────────────────────────────────────────────────────────────────────────────┘


    public static class CompatFunnelBlock extends FunnelBlock {

        private final Supplier<Block> beltFunnelBlock;

        public CompatFunnelBlock(Properties properties, Supplier<Block> beltFunnelBlock) {
            super(properties);
            this.beltFunnelBlock = beltFunnelBlock;
        }

        @Override
        public @NonNull BlockState getEquivalentBeltFunnel(BlockGetter world, BlockPos pos, @NonNull BlockState state) {
            Direction facing = getFunnelFacing(state);

            return beltFunnelBlock.get().defaultBlockState()
                    .setValue(BeltFunnelBlock.HORIZONTAL_FACING, facing)
                    .setValue(POWERED, state.getValue(POWERED));
        }
    }

}
